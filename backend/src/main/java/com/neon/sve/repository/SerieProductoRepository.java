package com.neon.sve.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.neon.sve.model.stock.DetalleIngreso;
import com.neon.sve.model.stock.EstadoSerie;
import com.neon.sve.model.stock.SerieProducto;

public interface SerieProductoRepository extends JpaRepository<SerieProducto, Long> {
    /*
     * // Buscar una serie de producto por su número de serie (que es único)
     * Optional<SerieProducto> findByNumeroSerie(String numeroSerie);
     * 
     * // Buscar series de producto por detalle de ingreso
     * List<SerieProducto> findByDetalleIngreso(DetalleIngreso detalleIngreso);
     */
    @Query("SELECT s.numeroSerie FROM SerieProducto s WHERE s.numeroSerie IN :series AND s.estado = 'ACTIVO'")
    List<String> findActiveSeriesByNumeroSerieIn(@Param("series") List<String> series);

    boolean existsByNumeroSerieAndEstadoIn(String numeroSerie, List<EstadoSerie> estados);

    @Query("SELECT COUNT(s) FROM SerieProducto s WHERE s.id_detalle_ingreso = :detalleIngreso")
    long contarPorDetalleIngreso(@Param("detalleIngreso") DetalleIngreso detalleIngreso);

    @Query("SELECT s FROM SerieProducto s WHERE s.numeroSerie = :numeroSerie AND s.id_detalle_ingreso = :detalleIngreso")
    Optional<SerieProducto> findByNumeroSerieAndDetalleIngreso(@Param("numeroSerie") String numeroSerie,
            @Param("detalleIngreso") DetalleIngreso detalleIngreso);

    @Query("""
                SELECT COUNT(s) FROM SerieProducto s
                WHERE s.id_detalle_ingreso = :detalleIngreso
                  AND s.estado IN :estados
            """)
    long contarPorDetalleIngresoYEstados(@Param("detalleIngreso") DetalleIngreso detalleIngreso,
            @Param("estados") List<EstadoSerie> estados);

    // List<SerieProducto> findByIdDetalleIngresoId(Long idDetalleIngreso);
}
