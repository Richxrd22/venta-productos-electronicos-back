package com.neon.sve.controller.Producto;

import org.springframework.data.domain.Page;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponentsBuilder;

import com.neon.sve.dto.empresa.DatosActualizarEmpresa;
import com.neon.sve.dto.empresa.DatosListadoEmpresa;
import com.neon.sve.dto.empresa.DatosRegistroEmpresa;
import com.neon.sve.dto.empresa.DatosRespuestaEmpresa;
import com.neon.sve.service.Empresa.EmpresaService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
@RequestMapping("/empresa")
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;

    @GetMapping("/listar")
    public ResponseEntity<Page<DatosListadoEmpresa>> listarEmpresa(
            @PageableDefault(direction = Sort.Direction.ASC) Pageable paginacion) {
        Page<DatosListadoEmpresa> empresaPage = empresaService.getAllEmpresa(paginacion);
        return ResponseEntity.ok(empresaPage);
    }

    @GetMapping("/buscar/{id_empresa}")
    public ResponseEntity<?> buscarEmpresa(@PathVariable Long id_empresa) {
        try {
            DatosRespuestaEmpresa empresa = empresaService.getEmpleadoById(id_empresa);
            return ResponseEntity.ok(empresa);
        } catch (Exception e) {

            String mensajeError = "Error al obtener la empresa con ID" + id_empresa;
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensajeError);

        }
    }

    @PostMapping("/registrar")
    @Transactional
    public ResponseEntity<DatosRespuestaEmpresa> registrarEmpresa(
            @Valid @RequestBody DatosRegistroEmpresa datosRegistroEmpresa,
            UriComponentsBuilder uriComponentsBuilder) {

        DatosRespuestaEmpresa datosRespuestaEmpresa = empresaService.createEmpresa(datosRegistroEmpresa);
        URI url = uriComponentsBuilder.path("/buscar/{id_empresa}")
                .buildAndExpand(datosRespuestaEmpresa.id_empresa())
                .toUri();

        return ResponseEntity.created(url).body(datosRespuestaEmpresa);

    }

    @PutMapping("/actualizar")
    @Transactional
    public ResponseEntity<DatosRespuestaEmpresa> actualizarEmpresa(
            @Valid @RequestBody DatosActualizarEmpresa actualizarEmpresa) {

        DatosRespuestaEmpresa datosRespuestaEmpresa = empresaService.updateEmpresa(actualizarEmpresa);
        return ResponseEntity.ok(datosRespuestaEmpresa);

    }

    @DeleteMapping("/eliminar/{id_empresa}")
    public ResponseEntity<?> eliminarEmpresa(@PathVariable Long id_empresa) {

        try {
            empresaService.deleteEmpresa(id_empresa);
            return ResponseEntity.ok("Eliminacion exitosa");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

}
