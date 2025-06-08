package com.neon.sve.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.neon.sve.model.stock.DetalleIngreso;

public interface DetalleIngresoRepository extends JpaRepository<DetalleIngreso, Long> {
    /*
    // Buscar un detalle de ingreso por código de lote y producto
    Optional<DetalleIngreso> findByCodigoLoteAndProducto(String codigoLote, Producto producto);

    // Buscar detalles de ingreso por ingreso y producto
    Optional<DetalleIngreso> findByIngresoStockAndProducto(IngresoStock ingresoStock, Producto producto);
 */
}
