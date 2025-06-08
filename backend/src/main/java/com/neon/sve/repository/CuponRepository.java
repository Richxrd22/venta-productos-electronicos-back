package com.neon.sve.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.neon.sve.model.ventas.Cupon;

public interface CuponRepository extends JpaRepository<Cupon, Long> {
    
    /*
    // Buscar cupones por tipo de descuento
    List<Cupon> findByTipoDescuento(TipoDescuentoCupon tipoDescuento);

    // Buscar cupones que aún no han caducado (fechaFin es posterior o igual a hoy)
    List<Cupon> findByFechaFinAfterOrFechaFinEquals(LocalDate today);

    // Buscar cupones que no han alcanzado su uso máximo y están activos
    List<Cupon> findByUsosActualesLessThanAndMaxUsosGreaterThanAndActivoTrue(int maxUsosThreshold);
 */

}
