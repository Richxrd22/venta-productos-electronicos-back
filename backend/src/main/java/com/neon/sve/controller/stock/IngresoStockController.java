package com.neon.sve.controller.stock;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.neon.sve.dto.MensajeRespuesta;
import com.neon.sve.dto.ingresoStock.DatosActualizarIngresoStock;
import com.neon.sve.dto.ingresoStock.DatosListadoIngresoStock;
import com.neon.sve.dto.ingresoStock.DatosRegistroIngresoStock;
import com.neon.sve.dto.ingresoStock.DatosRespuestaIngresoStock;
import com.neon.sve.dto.login.DatosRespuestaMensaje;
import com.neon.sve.service.ingresoStock.IngresoStockService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/ingresoStock")
public class IngresoStockController {
    /*
    @Autowired
    private IngresoStockService ingresoStockService;

    @GetMapping("/listar")
    public ResponseEntity<List<DatosListadoIngresoStock>> listarIngresoStock() {
        Pageable paginacion = Pageable.unpaged();
        List<DatosListadoIngresoStock> listadoIngresoStocks = ingresoStockService.getAllIngresoStock(paginacion)
                .getContent();
        return ResponseEntity.ok(listadoIngresoStocks);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> buscarIngresoStock(@PathVariable Long id) {
        try {
            DatosRespuestaIngresoStock ingresoStock = ingresoStockService.getIngresoStockById(id);
            return ResponseEntity.ok(ingresoStock);
        } catch (Exception e) {
            String mensajeError = "Error al buscar el Ingreso Stock requerido, verifique ID ingresado : " + id;
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensajeError);

        }
    }

    @PostMapping("/registrar")
    public ResponseEntity<DatosRespuestaMensaje> registrarIngresoStock(
            @Valid @RequestBody DatosRegistroIngresoStock datosRegistroIngresoStock) {
        DatosRespuestaMensaje respuesta = ingresoStockService
                .createIngresoStock(datosRegistroIngresoStock);
        return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
    }

    @PutMapping("/actualizar")
    @Transactional
    public ResponseEntity<DatosRespuestaIngresoStock> actualizarIngresoStock(
            @Valid @RequestBody DatosActualizarIngresoStock datosActualizarIngresoStock) {
        DatosRespuestaIngresoStock datosRespuestaIngresoStock = ingresoStockService
                .updateIngresoStock(datosActualizarIngresoStock);
        return ResponseEntity.ok(datosRespuestaIngresoStock);
    }

    @PutMapping("/activar/{id}")
    public ResponseEntity<MensajeRespuesta> activarIngresoStock(@PathVariable Long id) {
        ingresoStockService.activarIngresoStock(id);
        return ResponseEntity.ok(new MensajeRespuesta("Ingreso de Stock activado correctamente"));
    }

    @PutMapping("/desactivar/{id}")
    public ResponseEntity<MensajeRespuesta> desactivarIngresoStock(@PathVariable Long id) {
        ingresoStockService.desactivarIngresoStock(id);
        return ResponseEntity.ok(new MensajeRespuesta("Ingreso de Stock desactivado correctamente"));
    }
 */
}
