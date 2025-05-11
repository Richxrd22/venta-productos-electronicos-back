package com.neon.sve.service.proveedores;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.neon.sve.dto.proveedores.DatosActualizarProveedores;
import com.neon.sve.dto.proveedores.DatosListadoProveedores;
import com.neon.sve.dto.proveedores.DatosRegistroProveedores;
import com.neon.sve.dto.proveedores.DatosRespuestaProveedores;

public interface ProveedoreService {

    DatosRespuestaProveedores getProveedroById(Long id);

    Page<DatosListadoProveedores> getAllProveedores(Pageable pageable);

    DatosRespuestaProveedores createProveedores(DatosRegistroProveedores datosRegistroProveedores);

    DatosRespuestaProveedores updateProveedores(DatosActualizarProveedores datosActualizarProveedores);

    void activarProveedor(Long id);

    void desactivarProveedor(Long id);

}
