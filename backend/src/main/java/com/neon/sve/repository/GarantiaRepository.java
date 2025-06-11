package com.neon.sve.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.neon.sve.model.ventas.Garantia;

public interface GarantiaRepository extends JpaRepository<Garantia, Long> {
    // Additional query methods can be defined here if needed
    
}
