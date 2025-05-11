package com.neon.sve.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.neon.sve.model.Producto.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{
    Optional<Categoria> findByNombre(String nombre);
}
