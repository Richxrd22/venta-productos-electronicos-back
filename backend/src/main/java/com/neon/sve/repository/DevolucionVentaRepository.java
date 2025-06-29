package com.neon.sve.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.neon.sve.model.ventas.DetalleVenta;
import com.neon.sve.model.ventas.DevolucionVenta;

public interface DevolucionVentaRepository extends JpaRepository<DevolucionVenta, Long> {

    List<DevolucionVenta> findByDetalleVenta(DetalleVenta detalleVenta);

}
