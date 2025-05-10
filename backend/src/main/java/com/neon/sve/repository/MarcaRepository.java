package com.neon.sve.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.neon.sve.model.Producto.Marca;

public interface MarcaRepository extends JpaRepository<Marca, Long> {
    
}
