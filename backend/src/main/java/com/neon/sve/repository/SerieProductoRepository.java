package com.neon.sve.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.neon.sve.model.stock.SerieProducto;

public interface SerieProductoRepository extends JpaRepository<SerieProducto, Long> {
    /* 
    // Buscar una serie de producto por su número de serie (que es único)
    Optional<SerieProducto> findByNumeroSerie(String numeroSerie);

    // Buscar series de producto por detalle de ingreso
    List<SerieProducto> findByDetalleIngreso(DetalleIngreso detalleIngreso);
*/
}
