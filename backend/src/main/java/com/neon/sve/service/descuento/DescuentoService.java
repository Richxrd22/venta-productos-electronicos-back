package com.neon.sve.service.descuento;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.neon.sve.dto.descuento.DatosActualizarDescuento;
import com.neon.sve.dto.descuento.DatosListadoDescuento;
import com.neon.sve.dto.descuento.DatosRegistroDescuento;
import com.neon.sve.dto.descuento.DatosRespuestaDescuento;

public interface DescuentoService {

    DatosRespuestaDescuento getDescuentoById(Long id);

    Page<DatosListadoDescuento> getAllDescuento(Pageable pageable);

    DatosRespuestaDescuento createDescuento(DatosRegistroDescuento datosRegistroDescuento);

    DatosRespuestaDescuento updateDescuento(DatosActualizarDescuento datosActualizarDescuento);

    void activarDescuento(Long id);

    void desactivarDescuento(Long id);
    

}
