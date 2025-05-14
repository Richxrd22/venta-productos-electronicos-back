package com.neon.sve.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.neon.sve.model.usuario.Empleado;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
    @Query("SELECT u FROM Empleado u WHERE u.correo = :correo")
    Optional<Empleado> findByCorreo(String correo);

    @Query("SELECT e FROM Empleado e WHERE e.usuario.correo = :correo")
    Optional<Empleado> findByUsuarioCorreo(String correo);

    @Query("SELECT e FROM Empleado e WHERE e.usuario.id = :idUsuario")
    Optional<Empleado> findByUsuarioId(Long idUsuario);

}
