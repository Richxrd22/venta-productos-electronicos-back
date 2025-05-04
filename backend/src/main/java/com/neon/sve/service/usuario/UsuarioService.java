package com.neon.sve.service.usuario;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.neon.sve.dto.usuario.DatosActualizarUsuario;
import com.neon.sve.dto.usuario.DatosListadoUsuario;
import com.neon.sve.dto.usuario.DatosRegistroUsuario;
import com.neon.sve.dto.usuario.DatosRespuestaUsuario;

public interface UsuarioService {
    DatosRespuestaUsuario getUsuarioById(Long id_usuario);

    //DatosRespuestaUsuario getUsuarioByCorreo(String correo);

    Page<DatosListadoUsuario> getAllUsuarios(Pageable pageable);

    DatosRespuestaUsuario createUsuario(DatosRegistroUsuario datosRegistroUsuario);

    DatosRespuestaUsuario updateUsuario(DatosActualizarUsuario datosActualizarUsuario);
} 