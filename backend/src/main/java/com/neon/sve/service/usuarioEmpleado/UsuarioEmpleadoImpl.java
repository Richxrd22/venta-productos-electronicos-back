package com.neon.sve.service.usuarioEmpleado;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.neon.sve.dto.login.DatosRespuestaMensaje;
import com.neon.sve.dto.usuarioEmpleado.DatosListadoUsuarioEmpleado;
import com.neon.sve.dto.usuarioEmpleado.DatosRegistroUsuarioEmpleado;
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
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Rol no encontrado"));

        List<Empleado> listaEmpleados = empleadoRepository.findAll();
        for (Empleado empleadoBD : listaEmpleados) {
            if (empleadoBD.getCorreo().equals(datosRegistroUsuarioEmpleado.correo()) ||
                    empleadoBD.getCelular().equals(datosRegistroUsuarioEmpleado.celular()) ||
                    empleadoBD.getDni().equals(datosRegistroUsuarioEmpleado.dni())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El correo, celular o DNI ya están registrados en otro empleado.");
            }
        }

        Empleado empleado = Empleado.builder()
                .nombre(datosRegistroUsuarioEmpleado.nombre())
                .apellido(datosRegistroUsuarioEmpleado.apellido())
                .dni(datosRegistroUsuarioEmpleado.dni())
                .correo(datosRegistroUsuarioEmpleado.correo())
                .celular(datosRegistroUsuarioEmpleado.celular())
                .activo(datosRegistroUsuarioEmpleado.activo())
                .build();

        String inicialNombre = datosRegistroUsuarioEmpleado.nombre().substring(0, 1).toUpperCase();
        String inicialApellido = datosRegistroUsuarioEmpleado.apellido().substring(0, 1).toUpperCase();
        String correoUsuario = inicialNombre + inicialApellido + datosRegistroUsuarioEmpleado.dni() + "@neon.com";

        Usuario usuario = Usuario.builder()
                .clave(passwordEncoder.encode(datosRegistroUsuarioEmpleado.dni()))
                .correo(correoUsuario)
                .id_empleado(empleado)
                .id_rol(rol)
                .clave_cambiada(false)
                .activo(datosRegistroUsuarioEmpleado.activo())
                .build();

        empleadoRepository.save(empleado);
        usuarioRepository.save(usuario);


        emailService.enviarCredenciales(
                empleado.getCorreo(), // destinatario
                usuario.getCorreo(), // correo de acceso al sistema
                datosRegistroUsuarioEmpleado.dni() // contraseña sin codificar
        );
        return new DatosRespuestaMensaje("Empleado y usuario creados con éxito. Las credenciales fueron enviadas por correo.");

    }

    @Override
    public Page<DatosListadoUsuarioEmpleado> getAllUsuarioEmpleados(Pageable pageable) {
        Page<Empleado> usuarioEmpleadoPage = empleadoRepository.findAll(pageable);
        return usuarioEmpleadoPage.map(DatosListadoUsuarioEmpleado::new);
    }

}
