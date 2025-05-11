package com.neon.sve.service.proveedor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.neon.sve.dto.proveedor.DatosActualizarProveedor;
import com.neon.sve.dto.proveedor.DatosListadoProveedor;
import com.neon.sve.dto.proveedor.DatosRegistroProveedor;
import com.neon.sve.dto.proveedor.DatosRespuestaProveedor;

public interface ProveedorService {

    DatosRespuestaProveedor getProveedroById(Long id);

    Page<DatosListadoProveedor> getAllProveedores(Pageable pageable);

    DatosRespuestaProveedor createProveedores(DatosRegistroProveedor datosRegistroProveedores);

    DatosRespuestaProveedor updateProveedores(DatosActualizarProveedor datosActualizarProveedores);

    void activarProveedor(Long id);

    void desactivarProveedor(Long id);

}
