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
        private static final BigDecimal CIEN = new BigDecimal("100.00");

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
        @Transactional
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
                // Para acumular el subtotal base (sin IGV)
                BigDecimal subtotalBaseCalculado = BigDecimal.ZERO;
                // Para acumular el IGV de todos los items
                BigDecimal igvTotalCalculado = BigDecimal.ZERO;
                // Para acumular el total (ya con IGV) de todos los items
                BigDecimal totalItemsConIgv = BigDecimal.ZERO;

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

                        // --- CÁLCULO DEL IGV POR PRODUCTO E INTEGRACIÓN DEL PRECIO ---
                        BigDecimal precioUnitarioBase = itemDTO.precio_unitario(); // Precio unitario sin IGV
                        BigDecimal igvPorItemUnitario = precioUnitarioBase.multiply(IGV_PORCENTAJE).divide(CIEN, 2,
                                        RoundingMode.HALF_UP);
                        BigDecimal precioUnitarioConIgv = precioUnitarioBase.add(igvPorItemUnitario);

                        // Crear el objeto DetalleVenta
                        DetalleVenta detalle = new DetalleVenta();
                        detalle.setId_registro_venta(registroVenta);
                        detalle.setId_producto(producto);
                        detalle.setCantidad(itemDTO.cantidad());
                        // Guardamos el precio unitario que ya incluye el IGV para este item
                        detalle.setPrecio_unitario(precioUnitarioConIgv);

                        // El total por item ya incluye el IGV
                        BigDecimal totalPorItemConIgv = precioUnitarioConIgv
                                        .multiply(BigDecimal.valueOf(detalle.getCantidad()));
                        detalle.setTotal(totalPorItemConIgv);

                        detallesAGuardar.add(detalle);

                        // Acumular para los totales de la cabecera
                        subtotalBaseCalculado = subtotalBaseCalculado
                                        .add(precioUnitarioBase.multiply(BigDecimal.valueOf(detalle.getCantidad()))); // Suma
                                                                                                                      // de
                                                                                                                      // precios
                                                                                                                      // base
                        igvTotalCalculado = igvTotalCalculado
                                        .add(igvPorItemUnitario.multiply(BigDecimal.valueOf(detalle.getCantidad()))); // Suma
                                                                                                                      // de
                                                                                                                      // IGVs
                                                                                                                      // individuales
                        totalItemsConIgv = totalItemsConIgv.add(totalPorItemConIgv); // Suma de totales de ítems (con
                                                                                     // IGV)
                }

                // Guardamos todos los detalles en la base de datos
                detalleVentaRepository.saveAll(detallesAGuardar);

                // 4. Calcular los totales finales para el RegistroVenta
                BigDecimal descuento = BigDecimal.ZERO;
                // El descuento se aplica sobre el total de los items que ya incluye IGV
                if (cupon != null) {
                        descuento = totalItemsConIgv.multiply(BigDecimal.valueOf(cupon.getDescuentoPorcentaje()))
                                        .divide(CIEN, 2, RoundingMode.HALF_UP);
                }

                // El total final es la suma de los totales de los ítems (que ya tienen IGV)
                // menos el descuento
                BigDecimal totalFinal = totalItemsConIgv.subtract(descuento);

                // 5. Actualizar el RegistroVenta con los valores finales y guardar
                // El subtotal de la venta será la suma de los totales de los ítems (que ya
                // incluyen IGV)
                registroVenta.setSubtotal(totalItemsConIgv);
                registroVenta.setDescuento(descuento);
                registroVenta.setIgv_porcentaje(IGV_PORCENTAJE);
                registroVenta.setIgv_total(igvTotalCalculado); // El IGV total es la suma de los IGV de cada item
                registroVenta.setTotal(totalFinal);

                RegistroVenta ventaGuardada = registroVentaRepository.save(registroVenta);

                return new DatosRespuestaRegistroVenta(ventaGuardada);
        }

        @Override
        @Transactional
        public DatosRespuestaRegistroVenta updateVentaCompleta(DatosActualizarVentaCompleta datosActualizar) {
                RegistroVenta venta = registroVentaRepository.findById(datosActualizar.id())
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                "Venta no encontrada con ID: " + datosActualizar.id()));

                // 1. Restaurar el stock de los productos de los detalles antiguos
                // IMPORTANTE: Iteramos una copia para evitar ConcurrentModificationException si
                // la lista original
                // estuviera siendo modificada por Hibernate al limpiar la colección.
                // Además, al manipular la colección directamente, no necesitamos llamar a
                // deleteAll en el repositorio de detalles.
                List<DetalleVenta> detallesAntiguosCopia = new ArrayList<>(venta.getDetallesVenta());
                for (DetalleVenta detalleAntiguo : detallesAntiguosCopia) {
                        Producto producto = detalleAntiguo.getId_producto();
                        producto.setStock_actual(producto.getStock_actual() + detalleAntiguo.getCantidad());
                        productoRepository.save(producto);
                }

                // 2. Limpiar la colección de detalles de la venta existente.
                // Debido a orphanRemoval=true, Hibernate eliminará los detalles de la base de
                // datos.
                venta.getDetallesVenta().clear();

                // 3. Obtener las nuevas entidades principales (cliente, usuario, etc.)
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

                // 4. Procesar los nuevos detalles de la venta (items) y agregarlos a la
                // colección de la venta
                BigDecimal subtotalBaseCalculado = BigDecimal.ZERO;
                BigDecimal igvTotalCalculado = BigDecimal.ZERO;
                BigDecimal totalItemsConIgv = BigDecimal.ZERO;

                for (DatosRegistroItemVenta itemDTO : datosActualizar.items()) {
                        Producto producto = productoRepository.findById(itemDTO.id_producto())
                                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                        "Producto con ID " + itemDTO.id_producto() + " no encontrado"));

                        if (producto.getStock_actual() < itemDTO.cantidad()) {
                                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                                                "Stock insuficiente para el producto: " + producto.getNombre()
                                                                + ". Stock disponible: " + producto.getStock_actual());
                        }
                        producto.setStock_actual(producto.getStock_actual() - itemDTO.cantidad());
                        productoRepository.save(producto);

                        BigDecimal precioUnitarioBase = itemDTO.precio_unitario();
                        BigDecimal igvPorItemUnitario = precioUnitarioBase.multiply(IGV_PORCENTAJE).divide(CIEN, 2,
                                        RoundingMode.HALF_UP);
                        BigDecimal precioUnitarioConIgv = precioUnitarioBase.add(igvPorItemUnitario);

                        DetalleVenta detalleNuevo = new DetalleVenta();
                        detalleNuevo.setId_registro_venta(venta); // Establece la relación bidireccional
                        detalleNuevo.setId_producto(producto);
                        detalleNuevo.setCantidad(itemDTO.cantidad());
                        detalleNuevo.setPrecio_unitario(precioUnitarioConIgv);

                        BigDecimal totalPorItemConIgv = precioUnitarioConIgv
                                        .multiply(BigDecimal.valueOf(detalleNuevo.getCantidad()));
                        detalleNuevo.setTotal(totalPorItemConIgv);

                        // AGREGAR DIRECTAMENTE A LA COLECCIÓN DE LA VENTA
                        venta.getDetallesVenta().add(detalleNuevo);

                        subtotalBaseCalculado = subtotalBaseCalculado.add(
                                        precioUnitarioBase.multiply(BigDecimal.valueOf(detalleNuevo.getCantidad())));
                        igvTotalCalculado = igvTotalCalculado.add(
                                        igvPorItemUnitario.multiply(BigDecimal.valueOf(detalleNuevo.getCantidad())));
                        totalItemsConIgv = totalItemsConIgv.add(totalPorItemConIgv);
                }

                // 5. Recalcular los totales finales
                BigDecimal descuento = BigDecimal.ZERO;
                if (cupon != null) {
                        descuento = totalItemsConIgv.multiply(BigDecimal.valueOf(cupon.getDescuentoPorcentaje()))
                                        .divide(CIEN, 2, RoundingMode.HALF_UP);
                }

                BigDecimal totalFinal = totalItemsConIgv.subtract(descuento);

                // 6. Actualizar la venta con los valores finales y guardar
                venta.setSubtotal(totalItemsConIgv);
                venta.setDescuento(descuento);
                venta.setIgv_porcentaje(IGV_PORCENTAJE);
                venta.setIgv_total(igvTotalCalculado);
                venta.setTotal(totalFinal);

                // Guardamos la venta. Hibernate se encargará de persistir los nuevos detalles y
                // eliminar los antiguos
                // gracias a la configuración de la relación @OneToMany y orphanRemoval=true.
                RegistroVenta ventaActualizada = registroVentaRepository.save(venta);

                return new DatosRespuestaRegistroVenta(ventaActualizada);
        }

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
