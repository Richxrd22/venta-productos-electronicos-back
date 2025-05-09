package com.neon.sve.service.usuarioempleado;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.neon.sve.dto.login.DatosRespuestaMensaje;
import com.neon.sve.dto.usuarioempleado.DatosListadoUsuarioEmpleado;
import com.neon.sve.dto.usuarioempleado.DatosRegistroUsuarioEmpleado;

public interface UsuarioEmpleadoService {

    DatosRespuestaMensaje createUsuarioEmpleado(DatosRegistroUsuarioEmpleado datosRegistroUsuarioEmpleado);

    Page<DatosListadoUsuarioEmpleado> getAllUsuarioEmpleados(Pageable pageable);

}
