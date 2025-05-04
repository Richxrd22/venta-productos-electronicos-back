package com.neon.sve.service.usuarioempleado;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.neon.sve.dto.login.DatosRespuestaLoginUsuario;
import com.neon.sve.dto.usuarioempleado.DatosListadoUsuarioEmpleado;
import com.neon.sve.dto.usuarioempleado.DatosRegistroUsuarioEmpleado;

public interface UsuarioEmpleadoService {

    DatosRespuestaLoginUsuario createUsuarioEmpleado(DatosRegistroUsuarioEmpleado datosRegistroUsuarioEmpleado);

    Page<DatosListadoUsuarioEmpleado> getAllUsuarioEmpleados(Pageable pageable);

}
