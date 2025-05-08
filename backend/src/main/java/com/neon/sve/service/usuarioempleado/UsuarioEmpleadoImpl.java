package com.neon.sve.service.usuarioempleado;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.neon.sve.dto.login.DatosRespuestaLoginUsuario;
import com.neon.sve.dto.usuarioempleado.DatosListadoUsuarioEmpleado;
import com.neon.sve.dto.usuarioempleado.DatosRegistroUsuarioEmpleado;
import com.neon.sve.jwt.JwtService;
import com.neon.sve.model.Usuario.Empleado;
import com.neon.sve.model.Usuario.Rol;
import com.neon.sve.model.Usuario.Usuario;
import com.neon.sve.repository.EmpleadoRepository;
import com.neon.sve.repository.RolRepository;
import com.neon.sve.repository.UsuarioRepository;

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
    private JwtService jwtService;

    @Override
    public DatosRespuestaLoginUsuario createUsuarioEmpleado(DatosRegistroUsuarioEmpleado datosRegistroUsuarioEmpleado) {
    
        

        Rol rol = rolRepository.findById(datosRegistroUsuarioEmpleado.id_rol())
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));

        List<Empleado> listaEmpleados = empleadoRepository.findAll();
        for (Empleado empleadoBD : listaEmpleados) {
            if (empleadoBD.getCorreo().equals(datosRegistroUsuarioEmpleado.correo()) ||
                    empleadoBD.getCelular().equals(datosRegistroUsuarioEmpleado.celular()) ||
                    empleadoBD.getDni().equals(datosRegistroUsuarioEmpleado.dni())) {
                throw new RuntimeException("El correo, celular o DNI ya están registrados en otro empleado.");
            }
        }

        Empleado empleado = Empleado.builder()
                .nombre(datosRegistroUsuarioEmpleado.nombre())
                .apellido(datosRegistroUsuarioEmpleado.apellido())
                .dni(datosRegistroUsuarioEmpleado.dni())
                .correo(datosRegistroUsuarioEmpleado.correo())
                .celular(datosRegistroUsuarioEmpleado.celular())
                .estado(datosRegistroUsuarioEmpleado.estado())
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
                .build();

        empleadoRepository.save(empleado);
        usuarioRepository.save(usuario);

        String tokenGenerado = jwtService.getToken(usuario);
        return new DatosRespuestaLoginUsuario(tokenGenerado);

    }

    @Override
    public Page<DatosListadoUsuarioEmpleado> getAllUsuarioEmpleados(Pageable pageable) {
        Page<Empleado> usuarioEmpleadoPage = empleadoRepository.findAll(pageable);
        return usuarioEmpleadoPage.map(DatosListadoUsuarioEmpleado::new);
    }

    
}
