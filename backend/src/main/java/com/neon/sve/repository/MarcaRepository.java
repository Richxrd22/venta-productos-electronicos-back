package com.neon.sve.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.neon.sve.model.producto.Marca;

public interface MarcaRepository extends JpaRepository<Marca, Long> {
    
    Optional<Marca> findByNombre(String nombre);

}
