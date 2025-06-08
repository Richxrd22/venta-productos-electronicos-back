package com.neon.sve.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.neon.sve.model.stock.DetalleIngreso;
import com.neon.sve.model.stock.DevolucionProducto;
import com.neon.sve.model.stock.SerieProducto;
import com.neon.sve.model.usuario.Usuario;

public interface DevolucionProductoRepository extends JpaRepository<DevolucionProducto, Long> {
    
    // Buscar devoluciones por serie de producto
    Optional<DevolucionProducto> findBySerieProducto(SerieProducto serieProducto);

    // Buscar devoluciones por detalle de ingreso (para productos sin serie)
    Optional<DevolucionProducto> findByDetalleIngreso(DetalleIngreso detalleIngreso);

    // Buscar devoluciones realizadas por un usuario
    List<DevolucionProducto> findByUsuario(Usuario usuario);

    // Buscar devoluciones en un rango de fechas
    List<DevolucionProducto> findByFechaDevolucionBetween(LocalDate startDate, LocalDate endDate);


}
