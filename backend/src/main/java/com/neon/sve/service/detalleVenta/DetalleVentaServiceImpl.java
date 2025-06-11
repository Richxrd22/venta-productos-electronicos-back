package com.neon.sve.service.detalleVenta;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neon.sve.dto.detalleVenta.DatosListadoDetalleVenta;
import com.neon.sve.dto.detalleVenta.DatosRegistroDetalleVenta;
import com.neon.sve.dto.detalleVenta.DatosRespuestDetalleVenta;
import com.neon.sve.model.producto.Producto;
import com.neon.sve.model.ventas.DetalleVenta;
import com.neon.sve.model.ventas.RegistroVenta;
import com.neon.sve.repository.DetalleVentaRepository;
import com.neon.sve.repository.ProductoRepository;
import com.neon.sve.repository.RegistroVentaRepository;

@Service
public class DetalleVentaServiceImpl implements DetalleVentaService {

    @Autowired
    private DetalleVentaRepository detalleVentaRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private RegistroVentaRepository registroVentaRepository;

    @Override
    public DatosRespuestDetalleVenta guardar(DatosRegistroDetalleVenta datosRegistro) {
        Producto producto = productoRepository.findById(datosRegistro.id_producto())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        RegistroVenta registroVenta = registroVentaRepository.findById(datosRegistro.id_registro_venta())
                .orElseThrow(() -> new RuntimeException("Registro de venta no encontrado"));

        BigDecimal precioUnitario = datosRegistro.precio_unitario();
        int cantidad = datosRegistro.cantidad();
        BigDecimal total = precioUnitario.multiply(BigDecimal.valueOf(cantidad));

        DetalleVenta detalle = new DetalleVenta();
        detalle.setCantidad(cantidad);
        detalle.setPrecio_unitario(precioUnitario);
        detalle.setTotal(total);
        detalle.setId_producto(producto);
        detalle.setId_registro_venta(registroVenta);
        detalle.setActivo(true);

        detalleVentaRepository.save(detalle);

        return new DatosRespuestDetalleVenta(detalle);
    }

    @Override
    public List<DatosListadoDetalleVenta> listarPorRegistroVenta(Long idRegistroVenta) {
        return detalleVentaRepository.findAll().stream()
                .filter(dv -> dv.getId_registro_venta().getId().equals(idRegistroVenta))
                .map(DatosListadoDetalleVenta::new)
                .toList();
    }

}
