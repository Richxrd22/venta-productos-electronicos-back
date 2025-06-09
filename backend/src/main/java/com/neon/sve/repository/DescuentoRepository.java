package com.neon.sve.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;

import com.neon.sve.model.ventas.Descuento;

public interface DescuentoRepository extends JpaRepository<Descuento, Long> {

}
