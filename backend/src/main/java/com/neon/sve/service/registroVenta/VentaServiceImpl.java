package com.neon.sve.service.registroVenta;

import com.neon.sve.dto.registroVenta.DatosActualizarRegistroVenta;
import com.neon.sve.dto.registroVenta.DatosActualizarVentaCompleta;
import com.neon.sve.dto.registroVenta.DatosListadoRegistroVenta;
import com.neon.sve.dto.registroVenta.DatosRegistroItemVenta;
import com.neon.sve.dto.registroVenta.DatosRegistroVenta;
import com.neon.sve.dto.registroVenta.DatosRegistroVentaCompleta;
import com.neon.sve.dto.registroVenta.DatosRespuestaRegistroVenta;
import com.neon.sve.model.producto.Producto;
import com.neon.sve.model.usuario.Usuario;
import com.neon.sve.model.ventas.Cliente;
import com.neon.sve.model.ventas.Cupon;
import com.neon.sve.model.ventas.DetalleVenta;
import com.neon.sve.model.ventas.MetodoPago;
import com.neon.sve.model.ventas.RegistroVenta;
import com.neon.sve.repository.ClienteRepository;
import com.neon.sve.repository.CuponRepository;
import com.neon.sve.repository.DetalleVentaRepository;
import com.neon.sve.repository.MetodoPagoRepository;
import com.neon.sve.repository.ProductoRepository;
import com.neon.sve.repository.RegistroVentaRepository;
import com.neon.sve.repository.UsuarioRepository;

import jakarta.transaction.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class VentaServiceImpl implements VentaService {

        @Autowired
        private RegistroVentaRepository registroVentaRepository;
        @Autowired
        private UsuarioRepository usuarioRepository;
        @Autowired
        private ClienteRepository clienteRepository;
        @Autowired
        private MetodoPagoRepository metodoPagoRepository;
        @Autowired
        private CuponRepository cuponRepository;

        @Autowired
        private ProductoRepository productoRepository;

        @Autowired
        private DetalleVentaRepository detalleVentaRepository;

        private static final BigDecimal IGV_PORCENTAJE = new BigDecimal("18.00");

        @Override
        public DatosRespuestaRegistroVenta getVentaById(Long id) {
                RegistroVenta venta = registroVentaRepository.findById(id)
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                "Venta no encontrada"));
                return new DatosRespuestaRegistroVenta(venta);
        }

        @Override
        public Page<DatosListadoRegistroVenta> getAllVentas(Pageable pageable) {
                Page<RegistroVenta> ventasPage = registroVentaRepository.findAll(pageable);
                return ventasPage.map(DatosListadoRegistroVenta::new);
        }

        // METODO PARA LA VENTA COMPLETA, INCLUYENDO EL DESCUENTO DE STOCK
        @Override
        @Transactional // ¡CRÍTICO! Asegura que toda la operación sea atómica.
        public DatosRespuestaRegistroVenta createVentaCompleta(DatosRegistroVentaCompleta datosVenta) {
                // 1. Obtener entidades principales (Usuario, Cliente, etc.)
                Usuario usuario = usuarioRepository.findById(datosVenta.id_usuario())
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                "Usuario no encontrado"));

                Cliente cliente = clienteRepository.findById(datosVenta.id_cliente())
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                "Cliente no encontrado"));

                MetodoPago metodoPago = metodoPagoRepository.findById(datosVenta.id_metodo_pago())
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                "Método de pago no encontrado"));

                Cupon cupon = null;
                if (datosVenta.id_cupon() != null) {
                        cupon = cuponRepository.findById(datosVenta.id_cupon())
                                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                        "Cupón no encontrado"));
                }

                // 2. Crear la cabecera de la venta (RegistroVenta) sin totales aún
                RegistroVenta registroVenta = new RegistroVenta();
                registroVenta.setId_usuario(usuario);
                registroVenta.setId_cliente(cliente);
                registroVenta.setId_metodo_pago(metodoPago);
                registroVenta.setId_cupon(cupon);
                registroVenta.setCancelado(false);
                registroVenta.setActivo(true);
                // Guardamos para que JPA le asigne un ID que usaremos en los detalles
                registroVentaRepository.save(registroVenta);

                // 3. Procesar los detalles de la venta (items)
                BigDecimal subtotalCalculado = BigDecimal.ZERO;
                List<DetalleVenta> detallesAGuardar = new ArrayList<>();

                for (DatosRegistroItemVenta itemDTO : datosVenta.items()) {
                        Producto producto = productoRepository.findById(itemDTO.id_producto())
                                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                        "Producto con ID " + itemDTO.id_producto() + " no encontrado"));

                        // --- LÓGICA DE VALIDACIÓN Y DESCUENTO DE STOCK ---
                        if (producto.getStock_actual() < itemDTO.cantidad()) {
                                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                                                "Stock insuficiente para el producto: " + producto.getNombre()
                                                                + ". Stock disponible: " + producto.getStock_actual());
                        }
                        producto.setStock_actual(producto.getStock_actual() - itemDTO.cantidad());
                        productoRepository.save(producto); // Actualizamos el stock en la BD

                        // Crear el objeto DetalleVenta
                        DetalleVenta detalle = new DetalleVenta();
                        detalle.setId_registro_venta(registroVenta);
                        detalle.setId_producto(producto);
                        detalle.setCantidad(itemDTO.cantidad());
                        detalle.setPrecio_unitario(itemDTO.precio_unitario());

                        BigDecimal totalPorItem = detalle.getPrecio_unitario()
                                        .multiply(BigDecimal.valueOf(detalle.getCantidad()));
                        detalle.setTotal(totalPorItem);

                        detallesAGuardar.add(detalle);
                        subtotalCalculado = subtotalCalculado.add(totalPorItem);
                }

                // Guardamos todos los detalles en la base de datos
                detalleVentaRepository.saveAll(detallesAGuardar);

                // 4. Calcular los totales finales para el RegistroVenta
                BigDecimal descuento = BigDecimal.ZERO;
                if (cupon != null) {
                        descuento = subtotalCalculado.multiply(BigDecimal.valueOf(cupon.getDescuentoPorcentaje()))
                                        .divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP);
                }

                BigDecimal subtotalConDescuento = subtotalCalculado.subtract(descuento);
                BigDecimal igvTotal = subtotalConDescuento.multiply(IGV_PORCENTAJE)
                                .divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP);
                BigDecimal totalFinal = subtotalConDescuento.add(igvTotal);

                // 5. Actualizar el RegistroVenta con los valores finales y guardar
                registroVenta.setSubtotal(subtotalCalculado);
                registroVenta.setDescuento(descuento);
                registroVenta.setIgv_porcentaje(IGV_PORCENTAJE);
                registroVenta.setIgv_total(igvTotal);
                registroVenta.setTotal(totalFinal);

                RegistroVenta ventaGuardada = registroVentaRepository.save(registroVenta);

                return new DatosRespuestaRegistroVenta(ventaGuardada);
        }

        /*
         * @Override
         * public DatosRespuestaRegistroVenta createVenta(DatosRegistroVenta
         * datosRegistroVenta) {
         * Cupon cupon = null;
         * if (datosRegistroVenta.id_cupon() != null) {
         * cupon = cuponRepository.findById(datosRegistroVenta.id_cupon())
         * .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
         * "Cupón no encontrado"));
         * }
         * 
         * Usuario usuario = usuarioRepository.findById(datosRegistroVenta.id_usuario())
         * .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
         * "Usuario no encontrado"));
         * 
         * Cliente cliente = clienteRepository.findById(datosRegistroVenta.id_cliente())
         * .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
         * "Cliente no encontrado"));
         * 
         * MetodoPago metodoPago =
         * metodoPagoRepository.findById(datosRegistroVenta.id_metodo_pago())
         * .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
         * "Método de pago no encontrado"));
         * 
         * // Calcular el subtotal real a partir de los detalles (por implementar si
         * fuera
         * // necesario)
         * BigDecimal subtotal = datosRegistroVenta.subtotal();
         * 
         * // Aplicar descuento si hay cupón
         * BigDecimal descuento = BigDecimal.ZERO;
         * if (cupon != null) {
         * descuento = subtotal
         * .multiply(BigDecimal.valueOf(cupon.getDescuentoPorcentaje())
         * .divide(BigDecimal.valueOf(100)));
         * subtotal = subtotal.subtract(descuento);
         * }
         * 
         * // IGV
         * BigDecimal igvPorcentaje = datosRegistroVenta.igv_porcentaje();
         * BigDecimal igvTotal =
         * subtotal.multiply(igvPorcentaje).divide(BigDecimal.valueOf(100));
         * 
         * // Total
         * BigDecimal total = subtotal.add(igvTotal);
         * 
         * // Crear la venta con los datos calculados
         * RegistroVenta registroVenta = new RegistroVenta();
         * registroVenta.setIgv_porcentaje(igvPorcentaje);
         * registroVenta.setSubtotal(subtotal);
         * registroVenta.setIgv_total(igvTotal);
         * registroVenta.setDescuento(descuento);
         * registroVenta.setTotal(total);
         * registroVenta.setCancelado(datosRegistroVenta.cancelado());
         * registroVenta.setId_usuario(usuario);
         * registroVenta.setId_cliente(cliente);
         * registroVenta.setId_metodo_pago(metodoPago);
         * registroVenta.setId_cupon(cupon);
         * 
         * registroVentaRepository.save(registroVenta);
         * 
         * return new DatosRespuestaRegistroVenta(registroVenta);
         * }
         * 
         */

        @Override
        @Transactional
        public DatosRespuestaRegistroVenta updateVentaCompleta(DatosActualizarVentaCompleta datosActualizar) {
                // 1. Encontrar la venta existente
                RegistroVenta venta = registroVentaRepository.findById(datosActualizar.id())
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                "Venta no encontrada con ID: " + datosActualizar.id()));

                // 2. Restaurar el stock de los productos de los detalles antiguos
                // Obtener los detalles antes de eliminarlos
                List<DetalleVenta> detallesAntiguos = venta.getDetallesVenta();
                for (DetalleVenta detalleAntiguo : detallesAntiguos) {
                        Producto producto = detalleAntiguo.getId_producto();
                        producto.setStock_actual(producto.getStock_actual() + detalleAntiguo.getCantidad());
                        productoRepository.save(producto);
                }

                // 3. Eliminar los detalles de venta antiguos
                detalleVentaRepository.deleteAll(detallesAntiguos);

                // 4. Obtener las nuevas entidades principales (cliente, usuario, etc.)
                Usuario usuario = usuarioRepository.findById(datosActualizar.id_usuario())
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                "Usuario no encontrado"));
                Cliente cliente = clienteRepository.findById(datosActualizar.id_cliente())
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                "Cliente no encontrado"));
                MetodoPago metodoPago = metodoPagoRepository.findById(datosActualizar.id_metodo_pago())
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                "Método de pago no encontrado"));

                Cupon cupon = null;
                if (datosActualizar.id_cupon() != null) {
                        cupon = cuponRepository.findById(datosActualizar.id_cupon())
                                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                        "Cupón no encontrado"));
                }

                // Actualizar la cabecera de la venta
                venta.setId_usuario(usuario);
                venta.setId_cliente(cliente);
                venta.setId_metodo_pago(metodoPago);
                venta.setId_cupon(cupon);

                // 5. Procesar los nuevos detalles de la venta (items)
                BigDecimal subtotalCalculado = BigDecimal.ZERO;
                List<DetalleVenta> detallesNuevos = new ArrayList<>();

                for (DatosRegistroItemVenta itemDTO : datosActualizar.items()) {
                        Producto producto = productoRepository.findById(itemDTO.id_producto())
                                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                        "Producto con ID " + itemDTO.id_producto() + " no encontrado"));

                        // Validar y descontar el stock para los nuevos items
                        if (producto.getStock_actual() < itemDTO.cantidad()) {
                                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                                                "Stock insuficiente para el producto: " + producto.getNombre()
                                                                + ". Stock disponible: " + producto.getStock_actual());
                        }
                        producto.setStock_actual(producto.getStock_actual() - itemDTO.cantidad());
                        productoRepository.save(producto);

                        // Crear el nuevo objeto DetalleVenta
                        DetalleVenta detalleNuevo = new DetalleVenta();
                        detalleNuevo.setId_registro_venta(venta);
                        detalleNuevo.setId_producto(producto);
                        detalleNuevo.setCantidad(itemDTO.cantidad());
                        detalleNuevo.setPrecio_unitario(itemDTO.precio_unitario());
                        BigDecimal totalPorItem = detalleNuevo.getPrecio_unitario()
                                        .multiply(BigDecimal.valueOf(detalleNuevo.getCantidad()));
                        detalleNuevo.setTotal(totalPorItem);

                        detallesNuevos.add(detalleNuevo);
                        subtotalCalculado = subtotalCalculado.add(totalPorItem);
                }

                detalleVentaRepository.saveAll(detallesNuevos);

                // 6. Recalcular los totales finales
                BigDecimal descuento = BigDecimal.ZERO;
                if (cupon != null) {
                        descuento = subtotalCalculado.multiply(BigDecimal.valueOf(cupon.getDescuentoPorcentaje()))
                                        .divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP);
                }

                BigDecimal subtotalConDescuento = subtotalCalculado.subtract(descuento);
                BigDecimal igvTotal = subtotalConDescuento.multiply(IGV_PORCENTAJE)
                                .divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP);
                BigDecimal totalFinal = subtotalConDescuento.add(igvTotal);

                // 7. Actualizar la venta con los valores finales y guardar
                venta.setSubtotal(subtotalCalculado);
                venta.setDescuento(descuento);
                venta.setIgv_porcentaje(IGV_PORCENTAJE);
                venta.setIgv_total(igvTotal);
                venta.setTotal(totalFinal);

                RegistroVenta ventaActualizada = registroVentaRepository.save(venta);

                return new DatosRespuestaRegistroVenta(ventaActualizada);
        }

        /*
         * @Override
         * public DatosRespuestaRegistroVenta updateVenta(DatosActualizarRegistroVenta
         * datosActualizarRegistroVenta) {
         * // Buscar la venta existente
         * RegistroVenta venta =
         * registroVentaRepository.findById(datosActualizarRegistroVenta.id())
         * .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
         * "Venta no encontrada"));
         * 
         * // Buscar entidades relacionadas
         * Usuario usuario =
         * usuarioRepository.findById(datosActualizarRegistroVenta.id_usuario())
         * .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
         * "Usuario no encontrado"));
         * 
         * Cliente cliente =
         * clienteRepository.findById(datosActualizarRegistroVenta.id_cliente())
         * .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
         * "Cliente no encontrado"));
         * 
         * MetodoPago metodoPago =
         * metodoPagoRepository.findById(datosActualizarRegistroVenta.id_metodo_pago())
         * .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
         * "Método de pago no encontrado"));
         * 
         * Cupon cupon = null;
         * if (datosActualizarRegistroVenta.id_cupon() != null) {
         * cupon = cuponRepository.findById(datosActualizarRegistroVenta.id_cupon())
         * .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
         * "Cupón no encontrado"));
         * }
         * 
         * // Calcular el subtotal directamente desde el DTO
         * BigDecimal subtotal = datosActualizarRegistroVenta.subtotal();
         * 
         * // Calcular descuento
         * BigDecimal descuento = BigDecimal.ZERO;
         * if (cupon != null) {
         * descuento = subtotal
         * .multiply(BigDecimal.valueOf(cupon.getDescuentoPorcentaje()))
         * .divide(BigDecimal.valueOf(100));
         * subtotal = subtotal.subtract(descuento);
         * }
         * 
         * // Calcular IGV
         * BigDecimal igvPorcentaje = datosActualizarRegistroVenta.igv_porcentaje();
         * BigDecimal igvTotal =
         * subtotal.multiply(igvPorcentaje).divide(BigDecimal.valueOf(100));
         * 
         * // Calcular total
         * BigDecimal total = subtotal.add(igvTotal);
         * 
         * // Actualizar la entidad con todos los nuevos valores
         * venta.setSubtotal(subtotal);
         * venta.setDescuento(descuento);
         * venta.setIgv_porcentaje(igvPorcentaje);
         * venta.setIgv_total(igvTotal);
         * venta.setTotal(total);
         * venta.setCancelado(datosActualizarRegistroVenta.cancelado());
         * venta.setId_usuario(usuario);
         * venta.setId_cliente(cliente);
         * venta.setId_metodo_pago(metodoPago);
         * venta.setId_cupon(cupon);
         * 
         * // Guardar
         * venta = registroVentaRepository.save(venta);
         * 
         * return new DatosRespuestaRegistroVenta(venta);
         * }
         */

        @Override
        public void cancelarVenta(Long id) {
                RegistroVenta venta = registroVentaRepository.findById(id)
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                "Venta no encontrada"));
                if (venta.getCancelado()) {
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La venta ya se encuentra cancelada");
                }
                venta.setCancelado(true);
                registroVentaRepository.save(venta);
        }

        @Override
        public void activarVenta(Long id) {
                RegistroVenta venta = registroVentaRepository.findById(id)
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                "Venta no encontrada"));
                if (!venta.getCancelado()) {
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La venta ya se encuentra activa");
                }
                venta.setCancelado(false);
                registroVentaRepository.save(venta);
        }
}
