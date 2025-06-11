package com.neon.sve.service.registroVenta;

import com.neon.sve.dto.registroVenta.DatosActualizarRegistroVenta;
import com.neon.sve.dto.registroVenta.DatosListadoRegistroVenta;
import com.neon.sve.dto.registroVenta.DatosRegistroVenta;
import com.neon.sve.dto.registroVenta.DatosRespuestaRegistroVenta;
import com.neon.sve.model.usuario.Usuario;
import com.neon.sve.model.ventas.Cliente;
import com.neon.sve.model.ventas.Cupon;
import com.neon.sve.model.ventas.MetodoPago;
import com.neon.sve.model.ventas.RegistroVenta;
import com.neon.sve.repository.ClienteRepository;
import com.neon.sve.repository.CuponRepository;
import com.neon.sve.repository.MetodoPagoRepository;
import com.neon.sve.repository.RegistroVentaRepository;
import com.neon.sve.repository.UsuarioRepository;

import java.math.BigDecimal;

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

    @Override
    public DatosRespuestaRegistroVenta getVentaById(Long id) {
        RegistroVenta venta = registroVentaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Venta no encontrada"));
        return new DatosRespuestaRegistroVenta(venta);
    }

    @Override
    public Page<DatosListadoRegistroVenta> getAllVentas(Pageable pageable) {
        Page<RegistroVenta> ventasPage = registroVentaRepository.findAll(pageable);
        return ventasPage.map(DatosListadoRegistroVenta::new);
    }

    @Override
    public DatosRespuestaRegistroVenta createVenta(DatosRegistroVenta datosRegistroVenta) {
        Cupon cupon = null;
        if (datosRegistroVenta.id_cupon() != null) {
            cupon = cuponRepository.findById(datosRegistroVenta.id_cupon())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cupón no encontrado"));
        }

        Usuario usuario = usuarioRepository.findById(datosRegistroVenta.id_usuario())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        Cliente cliente = clienteRepository.findById(datosRegistroVenta.id_cliente())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado"));

        MetodoPago metodoPago = metodoPagoRepository.findById(datosRegistroVenta.id_metodo_pago())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Método de pago no encontrado"));

        // Calcular el subtotal real a partir de los detalles (por implementar si fuera
        // necesario)
        BigDecimal subtotal = datosRegistroVenta.subtotal();

        // Aplicar descuento si hay cupón
        BigDecimal descuento = BigDecimal.ZERO;
        if (cupon != null) {
            descuento = subtotal
                    .multiply(BigDecimal.valueOf(cupon.getDescuentoPorcentaje()).divide(BigDecimal.valueOf(100)));
            subtotal = subtotal.subtract(descuento);
        }

        // IGV
        BigDecimal igvPorcentaje = datosRegistroVenta.igv_porcentaje();
        BigDecimal igvTotal = subtotal.multiply(igvPorcentaje).divide(BigDecimal.valueOf(100));

        // Total
        BigDecimal total = subtotal.add(igvTotal);

        // Crear la venta con los datos calculados
        RegistroVenta registroVenta = new RegistroVenta();
        registroVenta.setIgv_porcentaje(igvPorcentaje);
        registroVenta.setSubtotal(subtotal);
        registroVenta.setIgv_total(igvTotal);
        registroVenta.setDescuento(descuento);
        registroVenta.setTotal(total);
        registroVenta.setCancelado(datosRegistroVenta.cancelado());
        registroVenta.setId_usuario(usuario);
        registroVenta.setId_cliente(cliente);
        registroVenta.setId_metodo_pago(metodoPago);
        registroVenta.setId_cupon(cupon);

        registroVentaRepository.save(registroVenta);

        return new DatosRespuestaRegistroVenta(registroVenta);
    }

    @Override
    public DatosRespuestaRegistroVenta updateVenta(DatosActualizarRegistroVenta datosActualizarRegistroVenta) {
        // Buscar la venta existente
        RegistroVenta venta = registroVentaRepository.findById(datosActualizarRegistroVenta.id())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Venta no encontrada"));

        // Buscar entidades relacionadas
        Usuario usuario = usuarioRepository.findById(datosActualizarRegistroVenta.id_usuario())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        Cliente cliente = clienteRepository.findById(datosActualizarRegistroVenta.id_cliente())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado"));

        MetodoPago metodoPago = metodoPagoRepository.findById(datosActualizarRegistroVenta.id_metodo_pago())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Método de pago no encontrado"));

        Cupon cupon = null;
        if (datosActualizarRegistroVenta.id_cupon() != null) {
            cupon = cuponRepository.findById(datosActualizarRegistroVenta.id_cupon())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cupón no encontrado"));
        }

        // Calcular el subtotal directamente desde el DTO
        BigDecimal subtotal = datosActualizarRegistroVenta.subtotal();

        // Calcular descuento
        BigDecimal descuento = BigDecimal.ZERO;
        if (cupon != null) {
            descuento = subtotal
                    .multiply(BigDecimal.valueOf(cupon.getDescuentoPorcentaje()))
                    .divide(BigDecimal.valueOf(100));
            subtotal = subtotal.subtract(descuento);
        }

        // Calcular IGV
        BigDecimal igvPorcentaje = datosActualizarRegistroVenta.igv_porcentaje();
        BigDecimal igvTotal = subtotal.multiply(igvPorcentaje).divide(BigDecimal.valueOf(100));

        // Calcular total
        BigDecimal total = subtotal.add(igvTotal);

        // Actualizar la entidad con todos los nuevos valores
        venta.setSubtotal(subtotal);
        venta.setDescuento(descuento);
        venta.setIgv_porcentaje(igvPorcentaje);
        venta.setIgv_total(igvTotal);
        venta.setTotal(total);
        venta.setCancelado(datosActualizarRegistroVenta.cancelado());
        venta.setId_usuario(usuario);
        venta.setId_cliente(cliente);
        venta.setId_metodo_pago(metodoPago);
        venta.setId_cupon(cupon);

        // Guardar
        venta = registroVentaRepository.save(venta);

        return new DatosRespuestaRegistroVenta(venta);
    }

    @Override
    public void cancelarVenta(Long id) {
        RegistroVenta venta = registroVentaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Venta no encontrada"));
        if (venta.getCancelado()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La venta ya se encuentra cancelada");
        }
        venta.setCancelado(true);
        registroVentaRepository.save(venta);
    }

    @Override
    public void activarVenta(Long id) {
        RegistroVenta venta = registroVentaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Venta no encontrada"));
        if (!venta.getCancelado()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La venta ya se encuentra activa");
        }
        venta.setCancelado(false);
        registroVentaRepository.save(venta);
    }
}
