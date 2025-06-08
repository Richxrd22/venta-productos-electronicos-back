package com.neon.sve.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.neon.sve.model.stock.DevolucionProducto;

public interface DevolucionProductoRepository extends JpaRepository<DevolucionProducto, Long> {
    
    /*
    // Buscar devoluciones por serie de producto
    Optional<DevolucionProducto> findBySerieProducto(SerieProducto serieProducto);

    // Buscar devoluciones por detalle de ingreso (para productos sin serie)
    Optional<DevolucionProducto> findByDetalleIngreso(DetalleIngreso detalleIngreso);

    // Buscar devoluciones realizadas por un usuario
    List<DevolucionProducto> findByUsuario(Usuario usuario);

    // Buscar devoluciones en un rango de fechas
    List<DevolucionProducto> findByFechaDevolucionBetween(LocalDate startDate, LocalDate endDate);

 */
}
