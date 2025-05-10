package com.neon.sve.controller.Venta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.neon.sve.dto.ingresoStock.DatosListadoIngresoStock;
import com.neon.sve.service.ingresoStock.IngresoStockService;

@Controller
@RequestMapping("/ingresoStock")
public class IngresoStockController {

    @Autowired
    private IngresoStockService ingresoStockService;

    @GetMapping("/listar")
    public ResponseEntity<Page<DatosListadoIngresoStock>> listarIngresoStock(
            @PageableDefault(direction = Sort.Direction.ASC) Pageable paginacion) {
        Page<DatosListadoIngresoStock> ingresoStockPage = ingresoStockService.getAllIngresoStock(paginacion);
        return ResponseEntity.ok(ingresoStockPage);
    }

}
