package com.neon.sve.service.Proveedores;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.neon.sve.dto.Proveedores.DatosActualizarProveedores;
import com.neon.sve.dto.Proveedores.DatosListadoProveedores;
import com.neon.sve.dto.Proveedores.DatosRegistroProveedores;
import com.neon.sve.dto.Proveedores.DatosRespuestaProveedores;

public interface ProveedoreService {

    DatosRespuestaProveedores getProveedroById(Long id_proveedor);

    Page<DatosListadoProveedores> getAllProveedores(Pageable pageable);

    DatosRespuestaProveedores createProveedores(DatosRegistroProveedores datosRegistroProveedores);

    DatosRespuestaProveedores updateProveedores(DatosActualizarProveedores datosActualizarProveedores);

    void activarProveedor(Long id_proveedor);

    void desactivarProveedor(Long id_proveedor);

}
