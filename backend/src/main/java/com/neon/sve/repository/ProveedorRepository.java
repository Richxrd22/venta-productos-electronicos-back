package com.neon.sve.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.neon.sve.model.producto.Proveedor;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, Long>{
    @Query("SELECT p FROM Proveedor p WHERE p.razon_social = :razonSocial")
    Optional<Proveedor> findByRazon_social(@Param("razonSocial") String razonSocial);

}
