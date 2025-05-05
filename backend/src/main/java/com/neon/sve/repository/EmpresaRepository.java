package com.neon.sve.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.neon.sve.model.Producto.Empresa;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
    
}
