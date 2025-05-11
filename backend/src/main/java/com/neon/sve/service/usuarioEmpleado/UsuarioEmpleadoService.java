package com.neon.sve.service.usuarioEmpleado;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.neon.sve.dto.login.DatosRespuestaMensaje;
import com.neon.sve.dto.usuarioEmpleado.DatosListadoUsuarioEmpleado;
import com.neon.sve.dto.usuarioEmpleado.DatosRegistroUsuarioEmpleado;

public interface UsuarioEmpleadoService {

    DatosRespuestaMensaje createUsuarioEmpleado(DatosRegistroUsuarioEmpleado datosRegistroUsuarioEmpleado);

    Page<DatosListadoUsuarioEmpleado> getAllUsuarioEmpleados(Pageable pageable);

}
