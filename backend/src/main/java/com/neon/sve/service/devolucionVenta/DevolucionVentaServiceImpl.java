package com.neon.sve.service.devolucionVenta;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.neon.sve.dto.devolucionVenta.DatosActualizarDevolucionVenta;
import com.neon.sve.dto.devolucionVenta.DatosListadoDevolucionVenta;
import com.neon.sve.dto.devolucionVenta.DatosProductoDevolucion;
import com.neon.sve.dto.devolucionVenta.DatosRegistroDevolucionVenta;
import com.neon.sve.dto.devolucionVenta.DatosRespuestaDevolucionVenta;
import com.neon.sve.model.producto.Producto;
import com.neon.sve.model.usuario.Usuario;
import com.neon.sve.model.ventas.DetalleDevolucion;
import com.neon.sve.model.ventas.DetalleVenta;
import com.neon.sve.model.ventas.DevolucionVenta;
import com.neon.sve.model.ventas.RegistroVenta;
import com.neon.sve.model.ventas.Tipos.EstadoReclamo;
import com.neon.sve.repository.DetalleDevolucionRepository;
import com.neon.sve.repository.DetalleVentaRepository;
import com.neon.sve.repository.DevolucionVentaRepository;
import com.neon.sve.repository.ProductoRepository;
import com.neon.sve.repository.RegistroVentaRepository;
import com.neon.sve.repository.UsuarioRepository;

@Service
public class DevolucionVentaServiceImpl implements DevolucionVentaService {

        @Autowired
        private DevolucionVentaRepository devolucionVentaRepository;

        @Autowired
        private DetalleDevolucionRepository detalleDevolucionRepository;

        @Autowired
        private RegistroVentaRepository ventaRepository;

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

                // 1. Buscar entidades principales
                Usuario usuario = usuarioRepository.findById(datosRegistro.id_usuario())
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                "Usuario no encontrado"));

                RegistroVenta venta = ventaRepository.findById(datosRegistro.id_registro_venta())
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                "Venta no encontrada"));

                // 2. Crear la cabecera de la devolución (objeto limpio)
                DevolucionVenta devolucionVenta = new DevolucionVenta();

                // 3. Poblar SOLO los campos relevantes para una devolución AGRUPADA
                devolucionVenta.setVenta(venta); // Se asigna la Venta principal
                devolucionVenta.setUsuario(usuario);
                devolucionVenta.setMotivo(datosRegistro.motivo());
                devolucionVenta.setEstado(EstadoReclamo.PENDIENTE);

                // 4. Iterar sobre los productos del JSON para crear los detalles de la
                // devolución
                for (DatosProductoDevolucion prodDevolucion : datosRegistro.devolucion()) {
                        if (prodDevolucion.cantidad() <= 0) {
                                continue; // Ignorar productos con cantidad 0 o negativa
                        }

                        Producto producto = productoRepository.findById(prodDevolucion.id_producto())
                                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                        "Producto con ID " + prodDevolucion.id_producto()
                                                                        + " no encontrado"));

                        // Validación: Asegurarse de que el producto estaba en la venta original.
                        DetalleVenta detalleVentaOriginal = venta.getDetallesVenta().stream()
                                        .filter(detalle -> detalle.getId_producto().getId()
                                                        .equals(prodDevolucion.id_producto()))
                                        .findFirst()
                                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                                                        "El producto " + producto.getNombre()
                                                                        + " no pertenece a la venta original."));

                        // Validación de cantidad
                        if (prodDevolucion.cantidad() > detalleVentaOriginal.getCantidad()) {
                                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                                                "La cantidad a devolver de " + producto.getNombre() + " ("
                                                                + prodDevolucion.cantidad()
                                                                + ") excede la cantidad comprada ("
                                                                + detalleVentaOriginal.getCantidad() + ").");
                        }

                        // Crear el objeto de detalle y añadirlo a la lista de la devolución principal
                        DetalleDevolucion detalleDevolucion = new DetalleDevolucion();
                        detalleDevolucion.setDevolucionVenta(devolucionVenta);
                        detalleDevolucion.setProducto(producto);
                        detalleDevolucion.setCantidad(prodDevolucion.cantidad());

                        devolucionVenta.getDetallesDevolucion().add(detalleDevolucion);
                }

                if (devolucionVenta.getDetallesDevolucion().isEmpty()) {
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                                        "La devolución debe contener al menos un producto.");
                }

                DevolucionVenta devolucionGuardada = devolucionVentaRepository.save(devolucionVenta);

                return new DatosRespuestaDevolucionVenta(devolucionGuardada);
        }

        @Override
        @Transactional
        public DatosRespuestaDevolucionVenta updateDevolucionVenta(
                        DatosActualizarDevolucionVenta datosActualizar) {

                // 1. Buscar la devolución principal por su ID
                DevolucionVenta devolucionVenta = devolucionVentaRepository.findById(datosActualizar.id())
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                "Devolución no encontrada con ID: " + datosActualizar.id()));

                EstadoReclamo estadoActual = devolucionVenta.getEstado();
                EstadoReclamo nuevoEstado = datosActualizar.estado();

                // 2. Validar que no se pueda modificar una devolución ya finalizada
                if (estadoActual == EstadoReclamo.RESUELTO || estadoActual == EstadoReclamo.RECHAZADO) {
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                                        "No se puede actualizar una devolución que ya ha sido resuelta o rechazada.");
                }

                // 3. LÓGICA CLAVE: Reponer el stock si el estado cambia a RESUELTO
                if (nuevoEstado != null && nuevoEstado == EstadoReclamo.RESUELTO
                                && estadoActual != EstadoReclamo.RESUELTO) {

                        System.out.println("La devolución se marcó como RESUELTA. Reponiendo stock...");

                        // Iterar sobre cada detalle de la devolución
                        for (DetalleDevolucion detalle : devolucionVenta.getDetallesDevolucion()) {
                                Producto producto = detalle.getProducto();
                                int cantidadADevolver = detalle.getCantidad();

                                // Aumentar el stock del producto
                                producto.setStock_actual(producto.getStock_actual() + cantidadADevolver);

                                // Guardar el producto con el stock actualizado
                                productoRepository.save(producto);

                                System.out.println("Stock del producto '" + producto.getNombre()
                                                + "' actualizado. Nuevo stock: " + producto.getStock_actual());
                        }
                }

                // 4. Actualizar los campos de la devolución (motivo y estado)
                if (datosActualizar.motivo() != null) {
                        devolucionVenta.setMotivo(datosActualizar.motivo());
                }
                if (nuevoEstado != null) {
                        devolucionVenta.setEstado(nuevoEstado);
                }

                // 5. Guardar los cambios en la base de datos
                devolucionVentaRepository.save(devolucionVenta);

                // 6. Retornar la respuesta con los datos actualizados
                return new DatosRespuestaDevolucionVenta(devolucionVenta);
        }

}
