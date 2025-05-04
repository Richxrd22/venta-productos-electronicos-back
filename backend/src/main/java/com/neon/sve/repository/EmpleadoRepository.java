package com.neon.sve.repository;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.neon.sve.model.Usuario.Empleado;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
    @Query("SELECT u FROM Empleado u WHERE u.correo_empleado = :correo_empleado")
    Optional<Empleado> findByCorreo(String correo_empleado);

    @Query("SELECT e FROM Empleado e WHERE e.usuario.correo_usuario = :correo_usuario")
    Optional<Empleado> findByUsuarioCorreo(String correo_usuario);
}
