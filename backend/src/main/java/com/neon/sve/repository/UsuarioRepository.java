package com.neon.sve.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.neon.sve.model.Usuario.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    //Optional<Usuario> findByCorreo(String correo_usuario);
}