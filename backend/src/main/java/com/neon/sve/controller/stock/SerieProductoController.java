package com.neon.sve.controller.stock;

import java.net.URI;
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
import org.springframework.web.util.UriComponentsBuilder;

import com.neon.sve.dto.serie.DatosActualizarSerie;
import com.neon.sve.dto.serie.DatosListadoSerie;
import com.neon.sve.dto.serie.DatosListadoSerieDetalle;
import com.neon.sve.dto.serie.DatosRegistroSerie;
import com.neon.sve.dto.serie.DatosRespuestaSerie;
import com.neon.sve.service.serieProducto.SerieProductoService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/serie-producto")
public class SerieProductoController {
    @Autowired
    private SerieProductoService serieProductoService;

    @PostMapping("/registrar")
    public ResponseEntity<DatosRespuestaSerie> registrarSerieProducto(
            @Valid @RequestBody DatosRegistroSerie datosRegistroSerie,
            UriComponentsBuilder uriComponentsBuilder) {
        DatosRespuestaSerie datosRespuesta = serieProductoService.createSerieProductos(datosRegistroSerie);
        URI url = uriComponentsBuilder.path("/serie-producto/buscar/{id}")
                .buildAndExpand(datosRespuesta.id_serie_producto())
                .toUri();
        return ResponseEntity.created(url).body(datosRespuesta);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<DatosListadoSerie>> listarSeriesProducto() {
        Pageable paginacion = Pageable.unpaged();
        List<DatosListadoSerie> series = serieProductoService.getAllSerieProducto(paginacion).getContent();
        return ResponseEntity.ok(series);
    }

    @GetMapping("/listar/detalle/{idDetalleIngreso}")
    public ResponseEntity<List<DatosListadoSerieDetalle>> obtenerSeriesReducidas(@PathVariable Long idDetalleIngreso) {
        List<DatosListadoSerieDetalle> series = serieProductoService
                .getAllSerieProductoByDetalleIngresoId(idDetalleIngreso);
        return ResponseEntity.ok(series);
    }

    @PutMapping("/actualizar")
    @Transactional
    public ResponseEntity<DatosRespuestaSerie> actualizarSerieProducto(
            @Valid @RequestBody DatosActualizarSerie datosActualizarSerie) {
        DatosRespuestaSerie datosRespuesta = serieProductoService.updateSerieProductos(datosActualizarSerie);
        return ResponseEntity.ok(datosRespuesta);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> buscarSerieProducto(@PathVariable Long id) {
        try {
            DatosRespuestaSerie serie = serieProductoService.getSerieProductoById(id);
            return ResponseEntity.ok(serie);
        } catch (Exception e) {
            String mensajeError = "Error al obtener la serie de producto con ID " + id;
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensajeError);
        }
    }
}
