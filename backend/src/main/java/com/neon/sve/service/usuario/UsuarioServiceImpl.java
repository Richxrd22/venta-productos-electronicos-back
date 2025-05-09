package com.neon.sve.service.usuario;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.neon.sve.dto.usuario.DatosActualizarUsuario;
import com.neon.sve.dto.usuario.DatosListadoUsuario;
import com.neon.sve.dto.usuario.DatosRegistroUsuario;
import com.neon.sve.dto.usuario.DatosRespuestaUsuario;
import com.neon.sve.model.Usuario.Empleado;
import com.neon.sve.model.Usuario.Rol;
import com.neon.sve.model.Usuario.Usuario;
import com.neon.sve.repository.EmpleadoRepository;
import com.neon.sve.repository.RolRepository;
import com.neon.sve.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private EmpleadoRepository empleadoRepository;
    @Autowired
    private RolRepository rolRepository;

    @Override
    public DatosRespuestaUsuario getUsuarioById(Long id) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            return new DatosRespuestaUsuario(usuario);
        } else {
            throw new RuntimeException("Usuario no encontrado");
        }
    }

   @Override
    public DatosRespuestaUsuario getUsuarioByCorreo(String correo) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByCorreo(correo);
        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            return new DatosRespuestaUsuario(usuario);
        } else {
            throw new RuntimeException("Usuario no encontrado");
        }
    }

    @Override
    public Page<DatosListadoUsuario> getAllUsuarios(Pageable pageable) {
        Page<Usuario> usuarioPage = usuarioRepository.findAll(pageable);
        return usuarioPage.map(DatosListadoUsuario::new);
    }

    @Override
    public DatosRespuestaUsuario createUsuario(DatosRegistroUsuario datosRegistroUsuario) {
        Empleado empleado = empleadoRepository.getReferenceById(datosRegistroUsuario.id_empleado());
        Rol rol = rolRepository.getReferenceById(datosRegistroUsuario.id_rol());
        Usuario usuario = usuarioRepository.save(new Usuario(datosRegistroUsuario, empleado, rol));
        return new DatosRespuestaUsuario(usuario);
    }

    @Override
    public DatosRespuestaUsuario updateUsuario(DatosActualizarUsuario datosActualizarUsuario) {
        Empleado empleado = empleadoRepository.getReferenceById(datosActualizarUsuario.id_empleado());
        Rol rol = rolRepository.getReferenceById(datosActualizarUsuario.id_rol());
        Usuario usuario = usuarioRepository.getReferenceById(datosActualizarUsuario.id_usuario());
        usuario.actualizar(datosActualizarUsuario, empleado, rol);
        usuario = usuarioRepository.save(usuario);
        return new DatosRespuestaUsuario(usuario);
    }

}
