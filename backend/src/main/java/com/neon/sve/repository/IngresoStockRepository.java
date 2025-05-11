package com.neon.sve.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.neon.sve.model.ventas.IngresoStock;

public interface IngresoStockRepository extends JpaRepository<IngresoStock, Long>{
    
}
