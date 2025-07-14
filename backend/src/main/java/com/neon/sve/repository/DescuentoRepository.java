package com.neon.sve.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.neon.sve.model.producto.Categoria;
import com.neon.sve.model.ventas.Descuento;

public interface DescuentoRepository extends JpaRepository<Descuento, Long> {

    List<Descuento> findByActivoTrueAndCategoriaAndFechaFinAfter(Categoria categoria, LocalDate fecha);

    // Este método busca todos los descuentos activos y vigentes para una categoría
    // específica
    List<Descuento> findByCategoriaAndActivoTrueAndFechaFinAfter(Categoria categoria, LocalDate fecha);

    // (Opcional pero recomendado) Este es útil para buscar el descuento activo de
    // una categoría
    Optional<Descuento> findFirstByCategoriaAndActivoTrueAndFechaFinAfterOrderByPorcentajeDesc(Categoria categoria,
            LocalDate fecha);

}
