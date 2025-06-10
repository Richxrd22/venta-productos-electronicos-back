package com.neon.sve.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.neon.sve.model.ventas.Cupon;

public interface CuponRepository extends JpaRepository<Cupon, Long> {
    
}
