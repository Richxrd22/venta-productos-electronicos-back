package com.neon.sve.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.neon.sve.model.producto.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

}
