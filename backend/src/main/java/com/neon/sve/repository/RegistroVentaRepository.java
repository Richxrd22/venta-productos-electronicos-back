package com.neon.sve.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.neon.sve.model.ventas.RegistroVenta;

public interface RegistroVentaRepository extends JpaRepository<RegistroVenta, Long> {
/* 
    // Buscar registros de venta por cliente
    List<RegistroVenta> findByCliente(Cliente cliente);

    // Buscar registros de venta por usuario
    List<RegistroVenta> findByUsuario(Usuario usuario);

    // Buscar registros de venta por método de pago
    List<RegistroVenta> findByMetodoPago(MetodoPago metodoPago);

    // Buscar registros de venta por rango de fechas
    List<RegistroVenta> findByFechaBetween(Timestamp startDate, Timestamp endDate);
*/
}
