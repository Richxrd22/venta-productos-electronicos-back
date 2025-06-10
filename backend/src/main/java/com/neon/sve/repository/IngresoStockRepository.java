package com.neon.sve.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.neon.sve.model.stock.IngresoStock;

public interface IngresoStockRepository extends JpaRepository<IngresoStock, Long> {
    /*
     * @Query("SELECT COUNT(i) FROM IngresoStock i WHERE i.id_producto.id = :idProducto"
     * )
     * Long contarPorProducto(@Param("idProducto") Long idProducto);
     */

   
}
