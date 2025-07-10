package com.neon.sve.service.ingresoStock;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.neon.sve.dto.detalleIngreso.DatosActualizarDetalleIngreso;
import com.neon.sve.dto.detalleIngreso.DatosListadoDetalleIngreso;
import com.neon.sve.dto.ingresoStock.DatosActualizarIngresoStock;
import com.neon.sve.dto.ingresoStock.DatosListadoIngresoStock;
import com.neon.sve.dto.ingresoStock.DatosProductoIngreso;
import com.neon.sve.dto.ingresoStock.DatosRegistroIngresoStock;
import com.neon.sve.dto.ingresoStock.DatosRespuestaIngresoStock;
import com.neon.sve.dto.login.DatosRespuestaMensaje;
import com.neon.sve.model.producto.Producto;
import com.neon.sve.model.producto.Proveedor;
import com.neon.sve.model.stock.DetalleIngreso;
import com.neon.sve.model.stock.EstadoSerie;
import com.neon.sve.model.stock.IngresoStock;
import com.neon.sve.model.stock.SerieProducto;
import com.neon.sve.model.usuario.Usuario;
import com.neon.sve.repository.DetalleIngresoRepository;
import com.neon.sve.repository.DevolucionProductoRepository;
import com.neon.sve.repository.IngresoStockRepository;
import com.neon.sve.repository.ProductoRepository;
import com.neon.sve.repository.ProveedorRepository;
import com.neon.sve.repository.SerieProductoRepository;
import com.neon.sve.repository.UsuarioRepository;

@Service
public class IngresoStockServiceImpl implements IngresoStockService {

        @Autowired
        private IngresoStockRepository ingresoStockRepository;

        @Autowired
        private UsuarioRepository usuarioRepository;

        @Autowired
        private ProductoRepository productoRepository;

        @Autowired
        private ProveedorRepository proveedorRepository;

        @Autowired
        private DetalleIngresoRepository detalleIngresoRepository;

        @Autowired
        private SerieProductoRepository serieProductoRepository;

        @Autowired
        private DevolucionProductoRepository devolucionProductoRepository;

        @Override
        public DatosRespuestaIngresoStock getIngresoStockById(Long id) {
                IngresoStock ingreso = ingresoStockRepository.findById(id)
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                "Ingreso no encontrado"));

                List<DatosListadoDetalleIngreso> detallesDTO = ingreso.getDetallesIngreso().stream()
                                .map(detalle -> {
                                        // Obtener series de ese detalle
                                        List<String> series = serieProductoRepository
                                                        .buscarPorIdDetalleIngreso(detalle.getId())
                                                        .stream()
                                                        .map(SerieProducto::getNumeroSerie)
                                                        .toList();

                                        String tipoSerie = series.isEmpty() ? "SIN_SERIE" : "CON_SERIE";

                                        return new DatosListadoDetalleIngreso(
                                                        detalle.getId(),
                                                        detalle.getId_producto().getNombre(),
                                                        detalle.getId_producto().getModelo(),
                                                        detalle.getCodigo_detalle(),
                                                        detalle.getCantidad(),
                                                        detalle.getPrecio_unitario(),
                                                        detalle.getPrecio_unitario().multiply(
                                                                        BigDecimal.valueOf(detalle.getCantidad())),
                                                        tipoSerie,
                                                        series);
                                })
                                .toList();

                return new DatosRespuestaIngresoStock(
                                ingreso.getId(),
                                ingreso.getId_proveedor().getId(),
                                ingreso.getId_proveedor().getRazon_social(),
                                ingreso.getCodigo_ingreso(),
                                ingreso.getFecha_ingreso(),
                                ingreso.getTipo_documento(),
                                ingreso.getNumero_documento(),
                                ingreso.getObservaciones(),
                                ingreso.getTotal(),
                                detallesDTO);
        }

        @Override
        public Page<DatosListadoIngresoStock> getAllIngresoStock(Pageable pageable) {
                Page<IngresoStock> ingresoStockPage = ingresoStockRepository.findAll(pageable);
                return ingresoStockPage.map(DatosListadoIngresoStock::new);
        }

        @Transactional
        @Override
        public DatosRespuestaIngresoStock updateIngresoStock(DatosActualizarIngresoStock datos) {
                IngresoStock ingreso = ingresoStockRepository.findById(datos.id_ingreso())
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                "Ingreso no encontrado."));

                if (!Boolean.TRUE.equals(ingreso.getActivo())) {
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El ingreso está inactivo.");
                }

                Proveedor proveedor = proveedorRepository.findById(datos.id_proveedor())
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                                                "Proveedor no encontrado."));

                Usuario usuario = usuarioRepository.findById(datos.id_usuario())
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                                                "Usuario no encontrado."));

                ingreso.actualizar(datos, proveedor, usuario);

                for (DatosActualizarDetalleIngreso detalleDTO : datos.detalles()) {
                        DetalleIngreso detalle = detalleIngresoRepository.findById(detalleDTO.id_detalle())
                                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                        "Detalle no encontrado."));

                        Producto producto = detalle.getId_producto();

                        actualizarCantidadYStockSiEsNecesario(detalle, producto, detalleDTO.cantidad());

                        detalle.setPrecio_unitario(detalleDTO.precio_unitario());

                        if ("CON_SERIE".equalsIgnoreCase(detalleDTO.tipo_serie())) {
                                actualizarSeriesDeDetalle(detalle, detalleDTO.series(), detalleDTO.cantidad());
                        }
                }

                return new DatosRespuestaIngresoStock(ingreso);
        }

        private void actualizarCantidadYStockSiEsNecesario(DetalleIngreso detalle, Producto producto,
                        int nuevaCantidad) {
                int cantidadAnterior = detalle.getCantidad();
                List<SerieProducto> series = serieProductoRepository.buscarPorIdDetalleIngreso(detalle.getId());

                boolean tieneSeries = !series.isEmpty();
                boolean algunaUsada = series.stream()
                                .anyMatch(serie -> serie.getEstado() != EstadoSerie.ACTIVO);

                if (tieneSeries && nuevaCantidad != cantidadAnterior) {
                        if (algunaUsada) {
                                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                                                "No se puede cambiar la cantidad porque hay series usadas.");
                        }

                        int diferencia = nuevaCantidad - cantidadAnterior;
                        int nuevoStock = producto.getStock_actual() + diferencia;

                        if (nuevoStock < 0) {
                                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                                                "El stock quedaría en negativo.");
                        }

                        if (nuevoStock > producto.getMax_stock()) {
                                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                                                "El stock superaría el máximo permitido.");
                        }

                        producto.setStock_actual(nuevoStock);
                        detalle.setCantidad(nuevaCantidad);
                }

                if (!tieneSeries && nuevaCantidad != cantidadAnterior) {
                        boolean devolucionNoRepuesta = devolucionProductoRepository
                                        .existsManipuladaSinSerie(detalle.getId());
                        if (devolucionNoRepuesta) {
                                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                                                "No se puede modificar este ingreso: tiene devoluciones sin reposición.");
                        }

                        int diferencia = nuevaCantidad - cantidadAnterior;
                        int nuevoStock = producto.getStock_actual() + diferencia;

                        if (nuevoStock < 0) {
                                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                                                "El stock quedaría en negativo.");
                        }

                        if (nuevoStock > producto.getMax_stock()) {
                                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                                                "El stock superaría el máximo permitido.");
                        }

                        producto.setStock_actual(nuevoStock);
                        detalle.setCantidad(nuevaCantidad);
                }
        }

        private void actualizarSeriesDeDetalle(DetalleIngreso detalle, List<String> seriesEnviadas, int nuevaCantidad) {
                List<SerieProducto> seriesActuales = serieProductoRepository.buscarPorIdDetalleIngreso(detalle.getId());

                Set<String> seriesActualesActivas = seriesActuales.stream()
                                .filter(s -> s.getEstado() == EstadoSerie.ACTIVO)
                                .map(s -> s.getNumeroSerie().trim().toUpperCase())
                                .collect(Collectors.toSet());

                Set<String> seriesEnviadasLimpias = seriesEnviadas.stream()
                                .filter(Objects::nonNull)
                                .map(s -> s.trim().toUpperCase())
                                .collect(Collectors.toSet());

                // Validar duplicados
                if (seriesEnviadasLimpias.size() != seriesEnviadas.size()) {
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Las series contienen duplicados.");
                }

                // Validar que no se estén quitando series ya usadas
                List<SerieProducto> seriesNoEditables = seriesActuales.stream()
                                .filter(s -> s.getEstado() != EstadoSerie.ACTIVO)
                                .filter(s -> !seriesEnviadasLimpias.contains(s.getNumeroSerie().trim().toUpperCase()))
                                .toList();

                if (!seriesNoEditables.isEmpty()) {
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                                        "No puedes eliminar/modificar series ya utilizadas: " +
                                                        seriesNoEditables.stream().map(SerieProducto::getNumeroSerie)
                                                                        .toList());
                }

                // Eliminar series activas que fueron quitadas en el request
                List<SerieProducto> seriesAEliminar = seriesActuales.stream()
                                .filter(s -> s.getEstado() == EstadoSerie.ACTIVO)
                                .filter(s -> !seriesEnviadasLimpias.contains(s.getNumeroSerie().trim().toUpperCase()))
                                .toList();

                if (!seriesAEliminar.isEmpty()) {
                        serieProductoRepository.deleteAll(seriesAEliminar);
                }

                // Validar cantidad final
                long seriesActivasMantenidas = seriesActuales.stream()
                                .filter(s -> s.getEstado() == EstadoSerie.ACTIVO)
                                .map(s -> s.getNumeroSerie().trim().toUpperCase())
                                .filter(seriesEnviadasLimpias::contains)
                                .count();

                long seriesNuevas = seriesEnviadasLimpias.stream()
                                .filter(s -> !seriesActualesActivas.contains(s))
                                .count();

                long totalSeriesFinales = seriesActivasMantenidas + seriesNuevas;

                if (totalSeriesFinales != nuevaCantidad) {
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                                        "La cantidad total de series proporcionadas (" + totalSeriesFinales +
                                                        ") no coincide con la cantidad especificada (" + nuevaCantidad
                                                        + ").");
                }

                // AGREGAR NUEVAS SERIES QUE NO EXISTÍAN
                for (String serie : seriesEnviadasLimpias) {
                        if (!seriesActualesActivas.contains(serie)) {
                                boolean enUso = serieProductoRepository.existsByNumeroSerieAndEstadoIn(
                                                serie,
                                                List.of(EstadoSerie.ACTIVO, EstadoSerie.VENDIDO, EstadoSerie.DEVUELTO,
                                                                EstadoSerie.REPARACION));
                                if (enUso) {
                                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                                                        "La serie '" + serie + "' ya está en uso.");
                                }

                                SerieProducto nuevaSerie = SerieProducto.builder()
                                                .id_detalle_ingreso(detalle)
                                                .numeroSerie(serie)
                                                .estado(EstadoSerie.ACTIVO)
                                                .build();
                                serieProductoRepository.save(nuevaSerie);
                        }
                }
        }

        /*
         * 
         * 
         * @Override
         * 
         * @Transactional
         * public void activarIngresoStock(Long id) {
         * IngresoStock ingresoStock = ingresoStockRepository.findById(id)
         * .orElseThrow(() -> new EntityNotFoundException(
         * "Ingreso de Stock no encontrado, verifique ID ingresado: " + id));
         * 
         * if (Boolean.TRUE.equals(ingresoStock.getActivo())) {
         * throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
         * "El ingreso de stock ya se encuentra activo.");
         * }
         * 
         * DetalleIngreso detalle = ingresoStock.getDetallesIngreso();
         * if (detalle == null)
         * return;
         * 
         * Producto producto = detalle.getId_producto();
         * validarStockMaximo(producto, detalle.getCantidad());
         * 
         * activarSeries(detalle.getSeriesProductos());
         * 
         * detalle.setActivo(true);
         * ingresoStock.setActivo(true);
         * 
         * producto.setStock_actual(producto.getStock_actual() + detalle.getCantidad());
         * productoRepository.save(producto);
         * ingresoStockRepository.save(ingresoStock);
         * }
         * 
         * @Override
         * 
         * @Transactional
         * public void desactivarIngresoStock(Long id) {
         * IngresoStock ingresoStock = ingresoStockRepository.findById(id)
         * .orElseThrow(() -> new EntityNotFoundException(
         * "Ingreso de Stock no encontrado, verifique ID ingresado: " + id));
         * 
         * if (!Boolean.TRUE.equals(ingresoStock.getActivo())) {
         * throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
         * "El ingreso de stock ya está desactivado.");
         * }
         * 
         * DetalleIngreso detalle = ingresoStock.getDetallesIngreso();
         * if (detalle == null)
         * return;
         * 
         * List<SerieProducto> series = detalle.getSeriesProductos();
         * validarSeries(series);
         * actualizarStock(detalle);
         * desactivarSeries(series);
         * 
         * detalle.setActivo(false);
         * ingresoStock.setActivo(false);
         * 
         * ingresoStockRepository.save(ingresoStock);
         * }
         */

        @Transactional
        @Override
        public DatosRespuestaMensaje createIngresoStock(DatosRegistroIngresoStock datosRegistroIngresoStock) {
                // Formatear fecha y generar código de ingreso/lote
                String fechaFormateada = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
                String shortUUID = UUID.randomUUID().toString().substring(0, 6).toUpperCase();

                String codigoIngreso = "ING-" + fechaFormateada + "-" + shortUUID;

                // Validar proveedor y usuario
                Proveedor proveedor = proveedorRepository.findById(datosRegistroIngresoStock.id_proveedor())
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                                                "Proveedor no encontrado."));

                Usuario usuario = usuarioRepository.findById(datosRegistroIngresoStock.id_usuario())
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                                                "Usuario no encontrado."));

                // Crear entidad IngresoStock
                IngresoStock ingresoStock = IngresoStock.builder()
                                .codigo_ingreso(codigoIngreso)
                                .id_proveedor(proveedor)
                                .tipo_documento(datosRegistroIngresoStock.tipo_documento())
                                .numero_documento(datosRegistroIngresoStock.numero_documento())
                                .observaciones(datosRegistroIngresoStock.observaciones())
                                .id_usuario(usuario)
                                .activo(true)
                                .build();

                ingresoStockRepository.save(ingresoStock);

                // Contador de correlativos por producto (en caso se repitan en el ingreso)

                int contadorDetalle = 1;

                for (DatosProductoIngreso productoDTO : datosRegistroIngresoStock.productos()) {
                        Producto producto = productoRepository.findById(productoDTO.id_producto())
                                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                                                        "Producto no encontrado."));

                        int nuevoStock = producto.getStock_actual() + productoDTO.cantidad();

                        // Validaciones de stock y precios
                        if (productoDTO.precio_unitario().compareTo(producto.getPrecio_venta()) > 0) {
                                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                                                "El precio unitario no puede ser mayor al precio de venta del producto "
                                                                + producto.getNombre());
                        }

                        if (nuevoStock < producto.getMin_stock()) {
                                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                                                "El stock resultante no puede ser menor al mínimo permitido del producto "
                                                                + producto.getNombre());
                        }

                        if (nuevoStock > producto.getMax_stock()) {
                                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                                                "El stock resultante supera el máximo permitido del producto "
                                                                + producto.getNombre());
                        }

                        // Generar código de detalle usando nuevo formato
                        String codigoDetalle = String.format("DI-%s-%s-%03d", fechaFormateada, shortUUID,
                                        contadorDetalle++);

                        // Crear DetalleIngreso
                        DetalleIngreso detalle = DetalleIngreso.builder()
                                        .ingresoStock(ingresoStock)
                                        .id_producto(producto)
                                        .codigo_detalle(codigoDetalle)
                                        .cantidad(productoDTO.cantidad())
                                        .precio_unitario(productoDTO.precio_unitario())
                                        .activo(true)
                                        .build();

                        detalleIngresoRepository.save(detalle);

                        // Actualizar stock del producto
                        producto.setStock_actual(nuevoStock);
                        productoRepository.save(producto);

                        // Procesar series si aplica
                        if ("CON_SERIE".equalsIgnoreCase(productoDTO.tipo_serie())) {
                                procesarSerieIndividual(productoDTO, detalle);
                        }
                }

                return new DatosRespuestaMensaje("Ingreso de stock registrado con éxito.");
        }

        private void procesarSerieIndividual(DatosProductoIngreso productoDTO, DetalleIngreso detalleIngreso) {
                List<String> series = productoDTO.series_individuales();

                if (series == null || series.isEmpty()) {
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                                        "Debe proporcionar las series individuales.");
                }

                if (series.size() != productoDTO.cantidad()) {
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                                        "La cantidad de series individuales (" + series.size() +
                                                        ") no coincide con la cantidad del producto.");
                }

                List<EstadoSerie> estadosOcupados = List.of(
                                EstadoSerie.ACTIVO,
                                EstadoSerie.VENDIDO,
                                EstadoSerie.DEVUELTO,
                                EstadoSerie.REPARACION);

                Set<String> unicos = new HashSet<>();
                for (String serieInd : series) {
                        if (!unicos.add(serieInd)) {
                                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                                                "Las series individuales contienen duplicados: " + serieInd);
                        }

                        boolean existe = serieProductoRepository.existsByNumeroSerieAndEstadoIn(serieInd,
                                        estadosOcupados);
                        if (existe) {
                                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                                                "La serie " + serieInd + " ya está en uso y activa.");
                        }
                }

                for (String serieInd : series) {
                        SerieProducto serie = SerieProducto.builder()
                                        .id_detalle_ingreso(detalleIngreso)
                                        .numeroSerie(serieInd)
                                        .estado(EstadoSerie.ACTIVO)
                                        .build();

                        serieProductoRepository.save(serie);
                }
        }

        @Override
        public void activarIngresoStock(Long id) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'activarIngresoStock'");
        }

        @Override
        public void desactivarIngresoStock(Long id) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'desactivarIngresoStock'");
        }

        /*
         * Metodo para desactivarIngresoStock
         * private void validarSeries(List<SerieProducto> series) {
         * if (series == null || series.isEmpty())
         * return;
         * 
         * boolean tieneSeriesComprometidas = series.stream().anyMatch(serie ->
         * serie.getEstado() == EstadoSerie.VENDIDO ||
         * serie.getEstado() == EstadoSerie.REPARACION ||
         * serie.getEstado() == EstadoSerie.DEVUELTO);
         * 
         * if (tieneSeriesComprometidas) {
         * throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
         * "No se puede desactivar este ingreso porque una o más series están en estado VENDIDO, REPARACION o DEVUELTO."
         * );
         * }
         * 
         * }
         * 
         * private void actualizarStock(DetalleIngreso detalle) {
         * Producto producto = detalle.getId_producto();
         * int nuevoStock = producto.getStock_actual() - detalle.getCantidad();
         * 
         * if (nuevoStock < 0) {
         * throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
         * "No se puede desactivar este ingreso porque el stock actual es menor que la cantidad ingresada."
         * );
         * }
         * 
         * producto.setStock_actual(nuevoStock);
         * productoRepository.save(producto);
         * }
         * 
         * private void desactivarSeries(List<SerieProducto> series) {
         * 
         * if (series == null || series.isEmpty())
         * return;
         * series.forEach(serie -> {
         * serie.setEstado(EstadoSerie.INACTIVO);
         * serieProductoRepository.save(serie);
         * });
         * }
         */
        /*
         * Metodo para activarIngresoStock
         * private void validarStockMaximo(Producto producto, int cantidadAgregar) {
         * int stockFinal = producto.getStock_actual() + cantidadAgregar;
         * if (stockFinal > producto.getMax_stock()) {
         * throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
         * "Activar este ingreso superaría el stock máximo permitido.");
         * }
         * }
         * 
         * private void activarSeries(List<SerieProducto> series) {
         * if (series != null && !series.isEmpty()) {
         * series.forEach(serie -> {
         * serie.setEstado(EstadoSerie.ACTIVO);
         * serieProductoRepository.save(serie);
         * });
         * }
         * }
         */

}
