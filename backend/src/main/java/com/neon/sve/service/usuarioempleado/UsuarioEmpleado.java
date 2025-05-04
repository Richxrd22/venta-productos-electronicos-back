package com.neon.sve.service.usuarioempleado;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.neon.sve.dto.login.DatosRespuestaLoginUsuario;
import com.neon.sve.dto.usuarioempleado.DatosRegistroUsuarioEmpleado;

public interface UsuarioEmpleado {

    DatosRespuestaLoginUsuario createUsuarioEmpleado(DatosRegistroUsuarioEmpleado datosRegistroUsuarioEmpleado);

    Page<DatosRegistroUsuarioEmpleado> getAllUsuarioEmpleados(Pageable pageable);

}
