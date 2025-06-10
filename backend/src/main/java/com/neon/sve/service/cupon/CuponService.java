package com.neon.sve.service.cupon;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.neon.sve.dto.cupon.DatosActualizarCupon;
import com.neon.sve.dto.cupon.DatosListadoCupon;
import com.neon.sve.dto.cupon.DatosRegistroCupon;
import com.neon.sve.dto.cupon.DatosRespuestaCupon;
import com.neon.sve.model.ventas.Cupon;

public interface CuponService {
    
    DatosRespuestaCupon crearCupon(DatosRegistroCupon datosRegistroCupon);
    
    DatosRespuestaCupon actualizarCupon(DatosActualizarCupon datosActualizarCupon);

    Page<DatosListadoCupon> getAllCupones(Pageable pageable);
    
    DatosRespuestaCupon obtenerCuponPorId(Long id);
    
    //List<Cupon> buscarPorTipoDescuento(String tipoDescuento);

    void activarDescuento(Long id);

    void desactivarDescuento(Long id);

    void incrementarUsoCupon(String codigo);

}
