package com.neon.sve.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.neon.sve.model.Usuario.Empleado;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {

    //Optional<Empleado> findByUsuarioCorreo(String correo);
}
