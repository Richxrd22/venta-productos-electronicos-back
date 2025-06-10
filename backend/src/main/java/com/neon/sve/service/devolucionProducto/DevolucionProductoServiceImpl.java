package com.neon.sve.service.devolucionProducto;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.neon.sve.dto.devolucionProducto.DatosActualizarDevolucionProducto;
import com.neon.sve.dto.devolucionProducto.DatosListadoDevolucionProducto;
import com.neon.sve.dto.devolucionProducto.DatosRegistroDevolucionProducto;
import com.neon.sve.dto.devolucionProducto.DatosRespuestaDevolucionProducto;
import com.neon.sve.dto.login.DatosRespuestaMensaje;
import com.neon.sve.model.producto.Producto;
import com.neon.sve.model.stock.DetalleIngreso;
import com.neon.sve.model.stock.DevolucionProducto;
import com.neon.sve.model.stock.EstadoSerie;
import com.neon.sve.model.stock.SerieProducto;
import com.neon.sve.model.usuario.Usuario;
import com.neon.sve.repository.DetalleIngresoRepository;
import com.neon.sve.repository.DevolucionProductoRepository;
import com.neon.sve.repository.ProductoRepository;
import com.neon.sve.repository.SerieProductoRepository;
import com.neon.sve.repository.UsuarioRepository;

@Service
public class DevolucionProductoServiceImpl implements DevolucionProductoService {

    @Autowired
    private SerieProductoRepository serieProductoRepository;

    @Autowired
    private DetalleIngresoRepository detalleIngresoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private DevolucionProductoRepository devolucionProductoRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public DatosRespuestaDevolucionProducto getDevolucionProductoById(Long id) {
        Optional<DevolucionProducto> devolucionOptional = devolucionProductoRepository.findById(id);
        if (devolucionOptional.isPresent()) {
            DevolucionProducto devolucion = devolucionOptional.get();
            return new DatosRespuestaDevolucionProducto(devolucion);
        } else {
            throw new RuntimeException("Devolución de producto no encontrada, verifique ID ingresado");
        }
    }

    @Transactional
    @Override
    public DatosRespuestaMensaje registrarDevolucionProducto(DatosRegistroDevolucionProducto datos) {
        // Validar existencia de usuario
        Usuario usuario = usuarioRepository.findById(datos.id_usuario())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        // Validar existencia de detalle ingreso
        DetalleIngreso detalleIngreso = detalleIngresoRepository.findById(datos.id_detalle_ingreso())
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Detalle de ingreso no encontrado"));

        // Validar tipo_serie
        if (datos.tipo_serie() == null || (!datos.tipo_serie().equalsIgnoreCase("CON_SERIE")
                && !datos.tipo_serie().equalsIgnoreCase("SIN_SERIE"))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "El tipo_serie debe ser 'CON_SERIE' o 'SIN_SERIE'.");
        }

        // Validar cantidad obligatoriamente
        if (datos.cantidad() == null || datos.cantidad() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Debe especificar una cantidad mayor a cero para la devolución.");
        }

        int cantidadADevolver = 0;

        if (datos.tipo_serie().equalsIgnoreCase("CON_SERIE")) {
            // Validar series
            if (datos.series() == null || datos.series().isEmpty()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Debe proporcionar las series a devolver.");
            }

            if (datos.series().size() != datos.cantidad()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "El número de series proporcionadas no coincide con la cantidad especificada.");
            }

            cantidadADevolver = datos.series().size();

            // Validar y procesar las series (cambia el estado a DEVUELTO)
            procesarSeriesDevueltas(datos.series(), detalleIngreso);

            // Registrar una devolución por cada serie
            for (Long idSerie : datos.series()) {
                SerieProducto serie = serieProductoRepository.findById(idSerie).get(); // Ya validado previamente
                DevolucionProducto devolucion = new DevolucionProducto(serie, datos, usuario, detalleIngreso);
                devolucionProductoRepository.save(devolucion);
            }

        } else {
            // SIN_SERIE
            cantidadADevolver = datos.cantidad();

            DevolucionProducto devolucion = new DevolucionProducto(datos, usuario, detalleIngreso);
            devolucionProductoRepository.save(devolucion);
        }

        // 🔽 ACTUALIZAR STOCK DEL PRODUCTO 🔽
        Producto producto = detalleIngreso.getId_producto(); // Asegúrate que este método exista

        int stockActual = producto.getStock_actual();

        if (stockActual < cantidadADevolver) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "No hay suficiente stock para registrar la devolución.");
        }

        producto.setStock_actual(stockActual - cantidadADevolver);
        productoRepository.save(producto);

        // Mensaje final
        String mensaje = datos.tipo_serie().equalsIgnoreCase("CON_SERIE")
                ? "Se registraron " + datos.series().size() + " devoluciones con series"
                : "Devolución registrada sin series";

        return new DatosRespuestaMensaje(mensaje);
    }

    private void procesarSeriesDevueltas(List<Long> idsSeries, DetalleIngreso detalleIngreso) {
        if (idsSeries == null || idsSeries.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Debe proporcionar las series a devolver.");
        }

        Set<Long> unicos = new HashSet<>();
        for (Long idSerie : idsSeries) {
            if (!unicos.add(idSerie)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Hay series duplicadas en la solicitud.");
            }

            SerieProducto serie = serieProductoRepository.findById(idSerie)
                    .orElseThrow(
                            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Serie no encontrada: " + idSerie));

            if (!serie.getId_detalle_ingreso().getId().equals(detalleIngreso.getId())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "La serie con ID " + idSerie + " no pertenece al lote indicado.");
            }

            if (serie.getEstado().equals(EstadoSerie.DEVUELTO)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "La serie con ID " + idSerie + " ya ha sido devuelta.");
            }

            if (serie.getEstado().equals(EstadoSerie.REPARACION)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "La serie con ID " + idSerie + " está en reparación y no puede ser devuelta.");
            }

            if (serie.getEstado().equals(EstadoSerie.VENDIDO)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "La serie con ID " + idSerie + " ya ha sido vendida y no puede devolverse.");
            }

            if (!serie.getEstado().equals(EstadoSerie.ACTIVO)) { // Asumiendo que tienes un campo `boolean activo`
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "La serie con ID " + idSerie + " está inactiva y no puede devolverse.");
            }

            // Cambiar estado
            serie.setEstado(EstadoSerie.DEVUELTO);
            serieProductoRepository.save(serie);
        }
    }

    @Override
    public Page<DatosListadoDevolucionProducto> getAllDevoluciones(Pageable pageable) {
        Page<DevolucionProducto> devolucionesPage = devolucionProductoRepository.findAll(pageable);
        return devolucionesPage.map(DatosListadoDevolucionProducto::new);
    }

    @Override
    @Transactional
    public DatosRespuestaMensaje actualizarDevolucionProducto(DatosActualizarDevolucionProducto datos) {
        DevolucionProducto devolucion = devolucionProductoRepository.findById(datos.id_devolucion_producto())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Devolución no encontrada"));

        if (devolucion.getId_serie_producto() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "No se puede actualizar devoluciones con Series.");
        }

        Usuario usuario = usuarioRepository.findById(datos.id_usuario())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        if (datos.cantidad() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La cantidad debe ser mayor a cero.");
        }

        int cantidadAnterior = devolucion.getCantidad();
        int nuevaCantidad = datos.cantidad();
        int diferencia = nuevaCantidad - cantidadAnterior;

        DetalleIngreso detalleIngreso = devolucion.getId_detalle_ingreso();

        if (nuevaCantidad > detalleIngreso.getCantidad()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La cantidad supera el total ingresado.");
        }

        Producto producto = detalleIngreso.getId_producto();

        // ⚠️ Diferencia se RESTA del stock actual (si aumenta la devolución, se reduce
        // el stock)
        int nuevoStock = producto.getStock_actual() - diferencia;

        if (nuevoStock < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Stock insuficiente para aplicar esta actualización.");
        }

        producto.setStock_actual(nuevoStock);
        productoRepository.save(producto);

        devolucion.actualizar(datos, usuario);
        devolucionProductoRepository.save(devolucion);

        return new DatosRespuestaMensaje("Devolución actualizada correctamente");
    }

    @Override
    @Transactional
    public void activarDevolucionProducto(Long id) {
        DevolucionProducto devolucion = devolucionProductoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Devolución no encontrada"));

        if (devolucion.getActivo()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La devolución ya está activa.");
        }

        Producto producto = devolucion.getId_detalle_ingreso().getId_producto();
        int cantidad = devolucion.getCantidad();

        int nuevoStock = producto.getStock_actual() - cantidad;
        if (nuevoStock < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "No se puede activar la devolución, el stock resultante sería negativo.");
        }
        producto.setStock_actual(nuevoStock);
        productoRepository.save(producto);

        SerieProducto serie = devolucion.getId_serie_producto();
        if (serie != null) {
            serie.setEstado(EstadoSerie.DEVUELTO);
            serieProductoRepository.save(serie);
        }

        devolucion.setActivo(true);
        devolucionProductoRepository.save(devolucion);
    }

    @Override
    @Transactional
    public void desactivarDevolucionProducto(Long id) {
        DevolucionProducto devolucion = devolucionProductoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Devolución no encontrada"));

        if (!devolucion.getActivo()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La devolución ya está inactiva.");
        }

        Producto producto = devolucion.getId_detalle_ingreso().getId_producto();
        int cantidad = devolucion.getCantidad();

        producto.setStock_actual(producto.getStock_actual() + cantidad);
        productoRepository.save(producto);

        SerieProducto serie = devolucion.getId_serie_producto();
        if (serie != null) {
            serie.setEstado(EstadoSerie.ACTIVO);
            serieProductoRepository.save(serie);
        }

        devolucion.setActivo(false);
        devolucionProductoRepository.save(devolucion);
    }
}
