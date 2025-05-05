package com.neon.sve.service.Empresa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.neon.sve.dto.empresa.DatosActualizarEmpresa;
import com.neon.sve.dto.empresa.DatosListadoEmpresa;
import com.neon.sve.dto.empresa.DatosRegistroEmpresa;
import com.neon.sve.dto.empresa.DatosRespuestaEmpresa;

public interface EmpresaService {
    
    DatosRespuestaEmpresa getEmpleadoById(Long idempresa);

    Page<DatosListadoEmpresa> getAllEmpresa (Pageable pageable);

    DatosRespuestaEmpresa createEmpresa(DatosRegistroEmpresa datosRegistroEmpresa);

    DatosRespuestaEmpresa updateEmpresa(DatosActualizarEmpresa datosActualizarEmpresa);

    void deleteEmpresa(Long id_empresa);

}