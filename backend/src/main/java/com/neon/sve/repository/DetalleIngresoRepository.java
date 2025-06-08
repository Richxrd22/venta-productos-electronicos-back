package com.neon.sve.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.neon.sve.model.producto.Producto;
import com.neon.sve.model.stock.DetalleIngreso;
import com.neon.sve.model.stock.IngresoStock;

public interface DetalleIngresoRepository extends JpaRepository<DetalleIngreso, Long> {

    // Buscar un detalle de ingreso por código de lote y producto
    Optional<DetalleIngreso> findByCodigoLoteAndProducto(String codigoLote, Producto producto);

    // Buscar detalles de ingreso por ingreso y producto
    Optional<DetalleIngreso> findByIngresoStockAndProducto(IngresoStock ingresoStock, Producto producto);

}
