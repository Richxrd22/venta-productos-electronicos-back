package com.neon.sve.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.neon.sve.model.ventas.DetalleVenta;

public interface DetalleVentaRepository extends JpaRepository<DetalleVenta, Long> {
    
}
