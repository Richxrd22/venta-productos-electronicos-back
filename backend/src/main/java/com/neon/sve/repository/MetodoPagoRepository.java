package com.neon.sve.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.neon.sve.model.ventas.MetodoPago;

public interface MetodoPagoRepository extends JpaRepository<MetodoPago, Long> {
    
    // Buscar un método de pago por su nombre (que es único)
    Optional<MetodoPago> findByMetodo(String metodo);

}
