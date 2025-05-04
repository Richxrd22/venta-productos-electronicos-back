package com.neon.sve.service.usuarioempleado;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.neon.sve.dto.login.DatosRespuestaLoginUsuario;
import com.neon.sve.dto.usuarioempleado.DatosRegistroUsuarioEmpleado;

@Service
public class UsuarioEmpleadoImpl implements UsuarioEmpleado{

    @Override
    public DatosRespuestaLoginUsuario createUsuarioEmpleado(DatosRegistroUsuarioEmpleado datosRegistroUsuarioEmpleado) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createUsuarioEmpleado'");
    }

    @Override
    public Page<DatosRegistroUsuarioEmpleado> getAllUsuarioEmpleados(Pageable pageable) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllUsuarioEmpleados'");
    }

    

}
