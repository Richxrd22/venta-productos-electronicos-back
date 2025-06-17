package com.neon.sve.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.neon.sve.model.producto.Categoria;
import com.neon.sve.model.ventas.Descuento;

public interface DescuentoRepository extends JpaRepository<Descuento, Long> {

    List<Descuento> findByActivoTrueAndCategoriaAndFechaFinAfter(Categoria categoria, LocalDate fecha);

}
