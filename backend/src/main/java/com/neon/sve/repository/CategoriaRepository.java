package com.neon.sve.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.neon.sve.model.producto.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{
    Optional<Categoria> findByNombre(String nombre);

    // Método para obtener categorías por nivel
    List<Categoria> findByNivel(int nivel);
}
