package com.neon.sve.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.neon.sve.model.ventas.ReclamoGarantia;

public interface ReclamoGarantiaRepository extends JpaRepository<ReclamoGarantia, Long> {

    @Query("SELECT COUNT(r) > 0 FROM ReclamoGarantia r WHERE r.id_garantia.id = :idGarantia")
    boolean existsByGarantiaId(@Param("idGarantia") Long idGarantia);
}
