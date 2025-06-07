package com.neon.sve.config.userInitializer;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.neon.sve.model.usuario.Empleado;
import com.neon.sve.model.usuario.Rol;
import com.neon.sve.model.usuario.Usuario;
import com.neon.sve.repository.EmpleadoRepository;
import com.neon.sve.repository.RolRepository;
import com.neon.sve.repository.UsuarioRepository;

@Service
public class CreacionUsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public void crearUsuarioEmpleadoRol() {

        Optional<Rol> rolOptional = rolRepository.findByNombreRol("ADMINISTRADOR"); // Buscar el rol por nombre
        Rol rol;
        if (rolOptional.isEmpty()) {
            // Si el rol no existe, crear uno nuevo
            rol = new Rol();
            rol.setNombre("ADMINISTRADOR");
            rol.setActivo(true);
            rolRepository.save(rol);
            System.out.println("Rol creado.");
        } else {
            rol = rolOptional.get();
            System.out.println("El rol 'ROLE_ADMINISTRADOR' ya existe.");
        }


        // Paso 2: Crear un Empleado
        Optional<Empleado> empleadoOptional = empleadoRepository.findByUsuarioCorreo("jorge.tolentino@gmail.com");
        Empleado empleado;
        if (empleadoOptional.isEmpty()) {
            empleado = new Empleado();
            empleado.setNombre("Jorge");
            empleado.setApellido("Tolentino");
            empleado.setDni("12345678");
            empleado.setCelular("923456789");
            empleado.setActivo(true);
            empleadoRepository.save(empleado);
            System.out.println("Empleado creado.");
        } else {
            empleado = empleadoOptional.get();
            System.out.println("El empleado ya existe.");
        }
        // Paso 3: Crear un Usuario y asignar rol y empleado
        Optional<Usuario> usuarioOptional = usuarioRepository.findByCorreo("jorge.tolentino@gmail.com");
        if (usuarioOptional.isEmpty()) {
            Usuario usuario = new Usuario();
            usuario.setCorreo("jorge.tolentino@gmail.com");
            usuario.setClave_cambiada(true);
            String contrasenaSinEncriptar = "12345"; // Usualmente esta vendría del cliente
            String contrasenaEncriptada = passwordEncoder.encode(contrasenaSinEncriptar);
            usuario.setId_rol(rol); // Asignar el rol
            usuario.setId_empleado(empleado); // Asignar el empleado
            usuario.setClave(contrasenaEncriptada);
            usuario.setActivo(true);
            usuario.setCuentaBloqueada(false);
            usuario.setIntentosFallidos(0);
            usuario.setFechaBloqueo(null);
            usuarioRepository.save(usuario);

            System.out.println("Usuario creado.");

        } else {
            System.out.println("El usuario ya existe.");
        }

    }

}
