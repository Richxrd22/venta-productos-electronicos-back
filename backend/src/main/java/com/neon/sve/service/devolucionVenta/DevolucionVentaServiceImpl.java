package com.neon.sve.service.devolucionVenta;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.neon.sve.dto.cupon.DatosRespuestaCupon;
import com.neon.sve.dto.devolucionVenta.DatosActualizarDevolucionVenta;
import com.neon.sve.dto.devolucionVenta.DatosListadoDevolucionVenta;
import com.neon.sve.dto.devolucionVenta.DatosRegistroDevolucionVenta;
import com.neon.sve.dto.devolucionVenta.DatosRespuestaDevolucionVenta;
import com.neon.sve.dto.reclamoGarantia.DatosListadoReclamoGarantia;
import com.neon.sve.model.producto.Producto;
import com.neon.sve.model.usuario.Usuario;
import com.neon.sve.model.ventas.Cupon;
import com.neon.sve.model.ventas.DetalleVenta;
import com.neon.sve.model.ventas.DevolucionVenta;
import com.neon.sve.model.ventas.ReclamoGarantia;
import com.neon.sve.model.ventas.Tipos.EstadoReclamo;
import com.neon.sve.repository.DetalleVentaRepository;
import com.neon.sve.repository.DevolucionVentaRepository;
import com.neon.sve.repository.ProductoRepository;
import com.neon.sve.repository.UsuarioRepository;

@Service
public class DevolucionVentaServiceImpl implements DevolucionVentaService {

    @Autowired
    private DevolucionVentaRepository devolucionVentaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private DetalleVentaRepository detalleVentaRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public DatosRespuestaDevolucionVenta getDevolucionVentaById(Long id) {

        Optional<DevolucionVenta> devolucionVentaOptional = devolucionVentaRepository.findById(id);
        if (devolucionVentaOptional.isPresent()) {
            DevolucionVenta devolucionVenta = devolucionVentaOptional.get();
            return new DatosRespuestaDevolucionVenta(devolucionVenta);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Devolucion de venta no encontrada");
        }
    }

    @Override
    public Page<DatosListadoDevolucionVenta> getAllDevolucionVenta(Pageable pageable) {
        Page<DevolucionVenta> devolucionVentaPage = devolucionVentaRepository.findAll(pageable);
        return devolucionVentaPage.map(reclamo -> new DatosListadoDevolucionVenta(reclamo));
    }

    @Override
    @Transactional
    public DatosRespuestaDevolucionVenta createDevolucionVenta(
            DatosRegistroDevolucionVenta datosRegistro) {

        Usuario usuario = usuarioRepository.findById(datosRegistro.id_usuario())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        DetalleVenta detalleVenta = detalleVentaRepository.findById(datosRegistro.id_detalle_venta())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Detalle de venta no encontrado"));

        // 1. VALIDACIÓN: La cantidad a devolver no puede ser mayor a la comprada.
        if (datosRegistro.cantidad() > detalleVenta.getCantidad()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "La cantidad a devolver (" + datosRegistro.cantidad()
                            + ") no puede ser mayor a la cantidad comprada (" + detalleVenta.getCantidad() + ").");
        }

        // 2. VALIDACIÓN: Sumar devoluciones existentes para este detalle de venta.
        List<DevolucionVenta> devolucionesAnteriores = devolucionVentaRepository.findByDetalleVenta(detalleVenta);
        int cantidadYaDevuelta = devolucionesAnteriores.stream()
                .filter(d -> d.getEstado() != EstadoReclamo.RECHAZADO)
                .mapToInt(DevolucionVenta::getCantidad)
                .sum();

        if (cantidadYaDevuelta + datosRegistro.cantidad() > detalleVenta.getCantidad()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "La cantidad total a devolver supera la cantidad comprada originalmente. Ya se han devuelto "
                            + cantidadYaDevuelta + " unidades.");
        }

        DevolucionVenta devolucionVenta = new DevolucionVenta(datosRegistro, detalleVenta, usuario);
        devolucionVentaRepository.save(devolucionVenta);

        return new DatosRespuestaDevolucionVenta(devolucionVenta);
    }

    @Override
    @Transactional
    public DatosRespuestaDevolucionVenta updateDevolucionVenta(
            DatosActualizarDevolucionVenta datosActualizar) {

        DevolucionVenta devolucionVenta = devolucionVentaRepository.findById(datosActualizar.id())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Devolución no encontrada con ID: " + datosActualizar.id()));

        // Validar que no se pueda modificar una devolución ya completada o rechazada
        if (devolucionVenta.getEstado() == EstadoReclamo.RESUELTO
                || devolucionVenta.getEstado() == EstadoReclamo.RECHAZADO) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "No se puede actualizar una devolución que ya ha sido completada o rechazada.");
        }

        // Obtenemos el nuevo estado desde el DTO
        EstadoReclamo nuevoEstado = datosActualizar.estado();

        // Si el estado se actualiza a COMPLETADO y antes estaba PENDIENTE o APROBADO
        if (nuevoEstado == EstadoReclamo.RESUELTO && devolucionVenta.getEstado() != EstadoReclamo.RESUELTO) {

            // Obtenemos el producto asociado a esta devolución
            Producto productoADevolver = devolucionVenta.getDetalleVenta().getId_producto();
            int cantidadADevolver = devolucionVenta.getCantidad();

            // Buscamos el producto en el repositorio para obtener el stock actual
            Producto productoEnStock = productoRepository.findById(productoADevolver.getId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                            "El producto asociado a la venta ya no existe."));

            // Verificamos si hay suficiente stock para hacer el cambio
            if (productoEnStock.getStock_actual() < cantidadADevolver) {
                throw new ResponseStatusException(HttpStatus.CONFLICT,
                        "No hay suficiente stock para realizar el cambio. Stock actual: " + productoEnStock.getStock_actual()
                                + ", se necesitan: " + cantidadADevolver);
            }

            // Disminuimos el stock
            productoEnStock.setStock_actual(productoEnStock.getStock_actual() - cantidadADevolver);
            productoRepository.save(productoEnStock); // Guardamos el producto con el stock actualizado
        }

        // Actualizamos los datos de la devolución
        DetalleVenta detalleVenta = detalleVentaRepository.findById(datosActualizar.id_detalle_venta())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Detalle de venta no encontrado"));

        devolucionVenta.actualizar(datosActualizar, detalleVenta);

        devolucionVentaRepository.save(devolucionVenta);

        return new DatosRespuestaDevolucionVenta(devolucionVenta);
    }

}
