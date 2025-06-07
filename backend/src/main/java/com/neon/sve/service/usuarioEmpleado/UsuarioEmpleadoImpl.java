package com.neon.sve.service.usuarioEmpleado;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.neon.sve.dto.login.DatosRespuestaMensaje;
import com.neon.sve.dto.usuarioEmpleado.DatosActualizarUsuarioEmpleado;
import com.neon.sve.dto.usuarioEmpleado.DatosListadoUsuarioEmpleado;
import com.neon.sve.dto.usuarioEmpleado.DatosRegistroUsuarioEmpleado;
import com.neon.sve.dto.usuarioEmpleado.DatosRespuestaUsuarioEmpleado;
import com.neon.sve.model.usuario.Empleado;
import com.neon.sve.model.usuario.Rol;
import com.neon.sve.model.usuario.Usuario;
import com.neon.sve.repository.EmpleadoRepository;
import com.neon.sve.repository.RolRepository;
import com.neon.sve.repository.UsuarioRepository;
import com.neon.sve.service.EmailService;

@Service
public class UsuarioEmpleadoImpl implements UsuarioEmpleadoService {

        @Autowired
        private UsuarioRepository usuarioRepository;
        @Autowired
        private EmpleadoRepository empleadoRepository;
        @Autowired
        private PasswordEncoder passwordEncoder;
        @Autowired
        private RolRepository rolRepository;

        @Autowired
        private EmailService emailService;

        @Override
        public DatosRespuestaMensaje createUsuarioEmpleado(DatosRegistroUsuarioEmpleado datosRegistroUsuarioEmpleado) {

                Rol rol = rolRepository.findById(datosRegistroUsuarioEmpleado.id_rol())
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                "Rol no encontrado"));

                Empleado empleado = Empleado.builder()
                                .nombre(datosRegistroUsuarioEmpleado.nombre())
                                .apellido(datosRegistroUsuarioEmpleado.apellido())
                                .dni(datosRegistroUsuarioEmpleado.dni())
                                .celular(datosRegistroUsuarioEmpleado.celular())
                                .activo(true)
                                .build();

                Usuario usuario = Usuario.builder()
                                .clave(passwordEncoder.encode(datosRegistroUsuarioEmpleado.dni()))
                                .correo(datosRegistroUsuarioEmpleado.correo())
                                .id_empleado(empleado)
                                .id_rol(rol)
                                .activo(true)
                                .clave_cambiada(false)
                                .cuentaBloqueada(false)
                                .intentosFallidos(0)
                                .fechaBloqueo(null)
                                .build();

                empleadoRepository.save(empleado);
                usuarioRepository.save(usuario);

                emailService.enviarCredenciales(
                                empleado.getUsuario().getCorreo(), // destinatario
                                usuario.getCorreo(), // correo de acceso al sistema
                                datosRegistroUsuarioEmpleado.dni() // contraseña sin codificar
                );
                return new DatosRespuestaMensaje(
                                "Empleado y usuario creados con éxito. Las credenciales fueron enviadas por correo.");

        }

        @Override
        public Page<DatosListadoUsuarioEmpleado> getAllUsuarioEmpleados(Pageable pageable) {
                Page<Empleado> usuarioEmpleadoPage = empleadoRepository.findAll(pageable);
                return usuarioEmpleadoPage.map(DatosListadoUsuarioEmpleado::new);
        }

        @Override
        public void activarUsuarioEmpleado(Long id_usuario) {
                Usuario usuario = usuarioRepository.findById(id_usuario)
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                "Usuario no encontrado"));

                Empleado empleado = empleadoRepository.findByUsuarioId(id_usuario)
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                "Empleado no encontrado"));

                if (Boolean.TRUE.equals(usuario.getActivo()) && Boolean.TRUE.equals(empleado.getActivo())) {
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                                        "El usuario y el empleado ya están activos");
                }

                usuario.setActivo(true);
                usuarioRepository.save(usuario);

                empleado.setActivo(true);
                empleadoRepository.save(empleado);
        }

        @Override
        public void desactivarUsuarioEmpleado(Long id_usuario) {
                Usuario usuario = usuarioRepository.findById(id_usuario)
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                "Usuario no encontrado"));

                Empleado empleado = empleadoRepository.findByUsuarioId(id_usuario)
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                "Empleado no encontrado"));

                if (Boolean.FALSE.equals(usuario.getActivo()) && Boolean.FALSE.equals(empleado.getActivo())) {
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                                        "El usuario y el empleado ya están inactivos");
                }

                usuario.setActivo(false);
                usuarioRepository.save(usuario);

                empleado.setActivo(false);
                empleadoRepository.save(empleado);

        }

        @Override
        public DatosRespuestaUsuarioEmpleado getUsuarioEmpleadoById(Long id_usuario) {

                Empleado empleado = empleadoRepository.findByUsuarioId(id_usuario)
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                "Empleado no encontrado"));

                return new DatosRespuestaUsuarioEmpleado(empleado);
        }

        @Override
        public DatosRespuestaMensaje updateUsuarioEmpleado(
                        DatosActualizarUsuarioEmpleado datosActualizarUsuarioEmpleado) {
                Empleado empleado = empleadoRepository.findById(datosActualizarUsuarioEmpleado.id_empleado())
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                "Empleado no encontrado"));

                Rol rol = rolRepository.findById(datosActualizarUsuarioEmpleado.id_rol())
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                "Rol no encontrado"));

                Usuario usuario = usuarioRepository.findByEmpleado(empleado)
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                "Usuario asociado no encontrado"));

                empleadoRepository.findByDni(datosActualizarUsuarioEmpleado.dni())
                                .ifPresent(otroEmpleado -> {
                                        if (!otroEmpleado.getId()
                                                        .equals(datosActualizarUsuarioEmpleado.id_empleado())) {
                                                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                                                                "El DNI ya está en uso por otro empleado");
                                        }
                                });

                empleadoRepository.findByUsuarioCorreo(datosActualizarUsuarioEmpleado.correo())
                                .ifPresent(encontrado -> {
                                        if (!encontrado.getId().equals(datosActualizarUsuarioEmpleado.id_empleado())) {
                                                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                                                                "El correo ya está en uso por otro empleado");
                                        }
                                });

                empleadoRepository.findByCelular(datosActualizarUsuarioEmpleado.celular()).ifPresent(encontrado -> {
                        if (!encontrado.getId().equals(datosActualizarUsuarioEmpleado.id_empleado())) {
                                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                                                "El celular ya está en uso por otro empleado");
                        }
                });
                empleado.actualizar(datosActualizarUsuarioEmpleado);
                usuario.actualizar(datosActualizarUsuarioEmpleado, empleado, rol);
                empleadoRepository.save(empleado);
                usuarioRepository.save(usuario);
                return new DatosRespuestaMensaje("Usuario y empleado actualizados con éxito.");
        }

}
