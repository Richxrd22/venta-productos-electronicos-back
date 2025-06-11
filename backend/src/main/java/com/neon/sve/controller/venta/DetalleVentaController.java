package com.neon.sve.controller.venta;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neon.sve.dto.detalleVenta.DatosListadoDetalleVenta;
import com.neon.sve.dto.detalleVenta.DatosRegistroDetalleVenta;
import com.neon.sve.dto.detalleVenta.DatosRespuestDetalleVenta;
import com.neon.sve.model.ventas.DetalleVenta;
import com.neon.sve.service.detalleVenta.DetalleVentaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/detalles-venta")
public class DetalleVentaController {

    @Autowired
    private DetalleVentaService detalleVentaService;

    @PostMapping
    public DatosRespuestDetalleVenta registrar(@RequestBody @Valid DatosRegistroDetalleVenta datosRegistro) {
        return detalleVentaService.guardar(datosRegistro);
    }

    @GetMapping("/registro/{idRegistroVenta}")
    public List<DatosListadoDetalleVenta> listarPorRegistro(@PathVariable Long idRegistroVenta) {
        return detalleVentaService.listarPorRegistroVenta(idRegistroVenta);
    }

}
