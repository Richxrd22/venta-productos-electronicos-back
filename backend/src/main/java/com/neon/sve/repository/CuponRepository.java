package com.neon.sve.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.neon.sve.model.ventas.Cupon;
import com.neon.sve.model.ventas.Tipos.TipoDescuentoCupon;

public interface CuponRepository extends JpaRepository<Cupon, Long> {
    
}
