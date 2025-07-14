package com.neon.sve.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.neon.sve.model.producto.Categoria;
import com.neon.sve.model.ventas.Descuento;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    Optional<Categoria> findByNombre(String nombre);

    // Método para obtener categorías por nivel
    List<Categoria> findByNivel(int nivel);

}
