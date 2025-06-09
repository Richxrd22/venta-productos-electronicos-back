package com.neon.sve.service.ingresoStock;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.neon.sve.dto.ingresoStock.DatosActualizarIngresoStock;
import com.neon.sve.dto.ingresoStock.DatosListadoIngresoStock;
import com.neon.sve.dto.ingresoStock.DatosRegistroIngresoStock;
import com.neon.sve.dto.ingresoStock.DatosRespuestaIngresoStock;
import com.neon.sve.dto.login.DatosRespuestaMensaje;

public interface IngresoStockService {
    
    DatosRespuestaIngresoStock getIngresoStockById(Long id);

    Page<DatosListadoIngresoStock> getAllIngresoStock(Pageable pageable);

    DatosRespuestaMensaje createIngresoStock(DatosRegistroIngresoStock datosRegistroIngresoStock);

    DatosRespuestaIngresoStock updateIngresoStock(DatosActualizarIngresoStock datosActualizarIngresoStock);

    void activarIngresoStock(Long id);

    void desactivarIngresoStock(Long id);

    

}
