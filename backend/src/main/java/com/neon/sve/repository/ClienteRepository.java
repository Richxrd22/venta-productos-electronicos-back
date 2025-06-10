package com.neon.sve.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.neon.sve.model.ventas.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    //Optional<Cliente> findByNombre(String nombre);
    Optional<Cliente> findByDni(String dni);
    Optional<Cliente> findByCorreo(String correo);
    Optional<Cliente> findByCelular(String celular);

}
