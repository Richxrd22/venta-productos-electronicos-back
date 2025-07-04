package com.neon.sve.service.ingresoStock;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.neon.sve.dto.ingresoStock.DatosActualizarIngresoStock;
import com.neon.sve.dto.ingresoStock.DatosListadoIngresoStock;
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

import jakarta.persistence.EntityNotFoundException;

@Service
public class IngresoStockServiceImpl implements IngresoStockService {
    /* 

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

        Optional<IngresoStock> ingresoStockOptional = ingresoStockRepository.findById(id);
        if (ingresoStockOptional.isPresent()) {
            IngresoStock ingresoStock = ingresoStockOptional.get();
            return new DatosRespuestaIngresoStock(ingresoStock);
        } else {
            throw new RuntimeException("Ingreso de stock no encontrado, verifique ID ingresado");
        }
    }

    @Override
    public Page<DatosListadoIngresoStock> getAllIngresoStock(Pageable pageable) {
        Page<IngresoStock> ingresoStockPage = ingresoStockRepository.findAll(pageable);
        return ingresoStockPage.map(DatosListadoIngresoStock::new);
    }

    @Transactional
    @Override
    public DatosRespuestaIngresoStock updateIngresoStock(DatosActualizarIngresoStock datosActualizarIngresoStock) {

        IngresoStock ingresoStock = ingresoStockRepository.findById(datosActualizarIngresoStock.id_ingreso())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ingreso no encontrado."));

        if (!Boolean.TRUE.equals(ingresoStock.getActivo())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El Ingreso stock está inactivo.");
        }

        Proveedor proveedor = proveedorRepository.findById(datosActualizarIngresoStock.id_proveedor())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Proveedor no encontrado."));

        Usuario usuario = usuarioRepository.findById(datosActualizarIngresoStock.id_usuario())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuario no encontrado."));

       // DetalleIngreso detalle = ingresoStock.getDetallesIngreso();
        //Producto producto = detalle.getId_producto();

        //actualizarCantidadYStockSiEsNecesario(detalle, producto, datosActualizarIngresoStock.cantidad_producto());

        ingresoStock.actualizar(datosActualizarIngresoStock, proveedor, usuario);
        //detalle.setPrecio_unitario(datosActualizarIngresoStock.precio_unitario());

        return new DatosRespuestaIngresoStock(ingresoStock);
    }
    @Override
    @Transactional
    public void activarIngresoStock(Long id) {
        IngresoStock ingresoStock = ingresoStockRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Ingreso de Stock no encontrado, verifique ID ingresado: " + id));

        if (Boolean.TRUE.equals(ingresoStock.getActivo())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "El ingreso de stock ya se encuentra activo.");
        }

        DetalleIngreso detalle = ingresoStock.getDetallesIngreso();
        if (detalle == null)
            return;

        Producto producto = detalle.getId_producto();
        validarStockMaximo(producto, detalle.getCantidad());

        activarSeries(detalle.getSeriesProductos());

        detalle.setActivo(true);
        ingresoStock.setActivo(true);

        producto.setStock_actual(producto.getStock_actual() + detalle.getCantidad());
        productoRepository.save(producto);
        ingresoStockRepository.save(ingresoStock);
    }

    @Override
    @Transactional
    public void desactivarIngresoStock(Long id) {
        IngresoStock ingresoStock = ingresoStockRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Ingreso de Stock no encontrado, verifique ID ingresado: " + id));

        if (!Boolean.TRUE.equals(ingresoStock.getActivo())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "El ingreso de stock ya está desactivado.");
        }

        DetalleIngreso detalle = ingresoStock.getDetallesIngreso();
        if (detalle == null)
            return;

        List<SerieProducto> series = detalle.getSeriesProductos();
        validarSeries(series);
        actualizarStock(detalle);
        desactivarSeries(series);

        detalle.setActivo(false);
        ingresoStock.setActivo(false);

        ingresoStockRepository.save(ingresoStock);
    }
*/
/* 
    @Transactional
    @Override
    public DatosRespuestaMensaje createIngresoStock(DatosRegistroIngresoStock datosRegistroIngresoStock) {
        String codigoLoteAutomatico = "L-" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")) + "-"
                + UUID.randomUUID().toString().substring(0, 6);

        Producto producto = productoRepository.findById(datosRegistroIngresoStock.id_producto())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Producto no encontrado."));

        Proveedor proveedor = proveedorRepository.findById(datosRegistroIngresoStock.id_proveedor())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Proveedor no encontrado."));

        Usuario usuario = usuarioRepository.findById(datosRegistroIngresoStock.id_usuario())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuario no encontrado."));

        int stockResultante = producto.getStock_actual() + datosRegistroIngresoStock.cantidad_producto();

        if (datosRegistroIngresoStock.precio_unitario().compareTo(producto.getPrecio_venta()) > 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "El precio unitario del ingreso no puede ser mayor al precio de venta del producto.");
        }

        if (stockResultante < producto.getMin_stock()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "El stock total después del ingreso no puede ser menor al stock mínimo permitido ("
                            + producto.getMin_stock() + ").");
        }

        if (stockResultante > producto.getMax_stock()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "El stock total después del ingreso no puede ser mayor al stock máximo permitido ("
                            + producto.getMax_stock() + ").");
        }

        IngresoStock ingresoStock = IngresoStock.builder()
                .id_proveedor(proveedor)
                .tipo_documento(datosRegistroIngresoStock.tipo_documento())
                .numero_documento(datosRegistroIngresoStock.numero_documento())
                .observaciones(datosRegistroIngresoStock.observaciones())
                .id_usuario(usuario)
                .activo(true)
                .build();

        DetalleIngreso detalleIngreso = DetalleIngreso.builder()
                .id_ingresoStock(ingresoStock)
                .id_producto(producto)
                .codigoLote(codigoLoteAutomatico)
                .cantidad(datosRegistroIngresoStock.cantidad_producto())
                .precio_unitario(datosRegistroIngresoStock.precio_unitario())
                .activo(true)
                .build();

        // Actualizar stock del producto
        producto.setStock_actual(stockResultante);
        productoRepository.save(producto);

        ingresoStockRepository.save(ingresoStock);
        detalleIngresoRepository.save(detalleIngreso);

        String tipoSerie = datosRegistroIngresoStock.tipo_serie();
        if ("CON_SERIE".equalsIgnoreCase(tipoSerie)) {
            procesarSerieIndividual(datosRegistroIngresoStock, detalleIngreso);
        }
        return new DatosRespuestaMensaje("Ingreso de stock registrado con éxito.");
    }
*/
    /* Metodo para updateIngresoStock 
    private void actualizarCantidadYStockSiEsNecesario(DetalleIngreso detalle, Producto producto, int nuevaCantidad) {
        int cantidadAnterior = detalle.getCantidad();
        boolean tieneSeries = detalle.getSeriesProductos() != null && !detalle.getSeriesProductos().isEmpty();

        if (tieneSeries && nuevaCantidad != cantidadAnterior) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "No se puede modificar la cantidad porque ya hay series de productos asociadas.");
        }

        if (!tieneSeries && nuevaCantidad != cantidadAnterior) {
            // Verificar si hay devoluciones manipuladas sin serie y sin reposición
            boolean existeDevolucionNoReposicionada = devolucionProductoRepository
                    .existsManipuladaSinSerie(detalle.getId());

            if (existeDevolucionNoReposicionada) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Este ingreso no puede modificarse porque tiene una devolución sin reposición.");
            }

            int diferencia = nuevaCantidad - cantidadAnterior;
            int nuevoStock = producto.getStock_actual() + diferencia;

            if (nuevoStock < 0) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "El stock del producto no puede quedar en negativo.");
            }

            if (nuevoStock > producto.getMax_stock()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "El stock del producto superaría el máximo permitido.");
            }

            producto.setStock_actual(nuevoStock);
            detalle.setCantidad(nuevaCantidad);
        }
    }
*/
    /* Metodo para createIngresoSotck 
    private void procesarSerieIndividual(DatosRegistroIngresoStock datos, DetalleIngreso detalleIngreso) {
        List<String> series = datos.series_individuales();

        if (series == null || series.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Debe proporcionar las series individuales.");
        }

        if (series.size() != datos.cantidad_producto()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "La cantidad de series individuales (" + series.size() +
                            ") no coincide con la cantidad de productos (" + datos.cantidad_producto() + ").");
        }

        List<EstadoSerie> estadosOcupados = List.of(
                EstadoSerie.ACTIVO,
                EstadoSerie.VENDIDO,
                EstadoSerie.DEVUELTO,
                EstadoSerie.REPARACION);

        Set<String> unicos = new HashSet<>();
        for (String serieInd : series) {
            if (unicos.contains(serieInd)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Las series individuales contienen duplicados.");
            }

            // Validar en la base de datos si la serie ya existe y está activa
            boolean existe = serieProductoRepository.existsByNumeroSerieAndEstadoIn(serieInd, estadosOcupados);
            if (existe) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "La serie " + serieInd + " ya está en uso y activa.");
            }

            unicos.add(serieInd);
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
*/
    /* Metodo para desactivarIngresoStock 
    private void validarSeries(List<SerieProducto> series) {
        if (series == null || series.isEmpty())
            return;

        boolean tieneSeriesComprometidas = series.stream().anyMatch(serie -> serie.getEstado() == EstadoSerie.VENDIDO ||
                serie.getEstado() == EstadoSerie.REPARACION ||
                serie.getEstado() == EstadoSerie.DEVUELTO);

        if (tieneSeriesComprometidas) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "No se puede desactivar este ingreso porque una o más series están en estado VENDIDO, REPARACION o DEVUELTO.");
        }

    }

    private void actualizarStock(DetalleIngreso detalle) {
        Producto producto = detalle.getId_producto();
        int nuevoStock = producto.getStock_actual() - detalle.getCantidad();

        if (nuevoStock < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "No se puede desactivar este ingreso porque el stock actual es menor que la cantidad ingresada.");
        }

        producto.setStock_actual(nuevoStock);
        productoRepository.save(producto);
    }

    private void desactivarSeries(List<SerieProducto> series) {

        if (series == null || series.isEmpty())
            return;
        series.forEach(serie -> {
            serie.setEstado(EstadoSerie.INACTIVO);
            serieProductoRepository.save(serie);
        });
    }
*/
    /* Metodo para activarIngresoStock 
    private void validarStockMaximo(Producto producto, int cantidadAgregar) {
        int stockFinal = producto.getStock_actual() + cantidadAgregar;
        if (stockFinal > producto.getMax_stock()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Activar este ingreso superaría el stock máximo permitido.");
        }
    }

    private void activarSeries(List<SerieProducto> series) {
        if (series != null && !series.isEmpty()) {
            series.forEach(serie -> {
                serie.setEstado(EstadoSerie.ACTIVO);
                serieProductoRepository.save(serie);
            });
        }
    }
*/
}
