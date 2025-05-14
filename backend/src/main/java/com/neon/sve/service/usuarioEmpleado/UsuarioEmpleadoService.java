package com.neon.sve.service.usuarioEmpleado;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.neon.sve.dto.login.DatosRespuestaMensaje;
import com.neon.sve.dto.usuarioEmpleado.DatosListadoUsuarioEmpleado;
import com.neon.sve.dto.usuarioEmpleado.DatosRegistroUsuarioEmpleado;
import com.neon.sve.dto.usuarioEmpleado.DatosRespuestaUsuarioEmpleado;

public interface UsuarioEmpleadoService {

    DatosRespuestaUsuarioEmpleado getUsuarioEmpleadoById(Long id_usuario);

    DatosRespuestaMensaje createUsuarioEmpleado(DatosRegistroUsuarioEmpleado datosRegistroUsuarioEmpleado);

    Page<DatosListadoUsuarioEmpleado> getAllUsuarioEmpleados(Pageable pageable);

    void activarUsuarioEmpleado(Long id_usuario);

    void desactivarUsuarioEmpleado(Long id_usuario);

}
