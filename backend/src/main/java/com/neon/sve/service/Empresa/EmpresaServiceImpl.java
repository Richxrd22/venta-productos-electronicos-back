package com.neon.sve.service.Empresa;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.neon.sve.dto.empresa.DatosActualizarEmpresa;
import com.neon.sve.dto.empresa.DatosListadoEmpresa;
import com.neon.sve.dto.empresa.DatosRegistroEmpresa;
import com.neon.sve.dto.empresa.DatosRespuestaEmpresa;
import com.neon.sve.model.Producto.Empresa;
import com.neon.sve.repository.EmpresaRepository;

@Service
public class EmpresaServiceImpl implements EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;

    @Override
    public Page<DatosListadoEmpresa> getAllEmpresa(Pageable pageable) {

        Page<Empresa> empresaPage = empresaRepository.findAll(pageable);
        return empresaPage.map(DatosListadoEmpresa::new);

    }

    @Override
    public DatosRespuestaEmpresa getEmpleadoById(Long idempresa) {

        Optional<Empresa> empresaOptional = empresaRepository.findById(idempresa);
        if (empresaOptional.isPresent()) {

            Empresa empresa = empresaOptional.get();
            return new DatosRespuestaEmpresa(empresa);

        } else {

            throw new RuntimeException("Empresa no encontrada");

        }

    }

    @Override
    public DatosRespuestaEmpresa createEmpresa(DatosRegistroEmpresa datosRegistroEmpresa) {

        Empresa empresa = empresaRepository.save(new Empresa(datosRegistroEmpresa));
        return new DatosRespuestaEmpresa(empresa);

    }

    @Override
    public DatosRespuestaEmpresa updateEmpresa(DatosActualizarEmpresa datosActualizarEmpresa) {

        Empresa empresa = empresaRepository.getReferenceById(datosActualizarEmpresa.id_empresa());
        empresa.actualizar(datosActualizarEmpresa);
        return new DatosRespuestaEmpresa(empresa);

    }

    @Override
    public void deleteEmpresa(Long id_empresa) {
        
        Empresa empresa = empresaRepository.findById(id_empresa).orElse(null);
        if (empresa != null) {
            
            empresaRepository.delete(empresa);
        } else {
            
            throw new IllegalArgumentException("No se encontro la empresa con el ID proporcionado");
        }

    }

}
