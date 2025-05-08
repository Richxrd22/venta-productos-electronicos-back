package com.neon.sve.service.rol;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.neon.sve.dto.rol.DatosActualizarRol;
import com.neon.sve.dto.rol.DatosListadoRol;
import com.neon.sve.dto.rol.DatosRegistroRol;
import com.neon.sve.dto.rol.DatosRespuestaRol;
import com.neon.sve.model.Usuario.Rol;

public interface RolService {

    DatosRespuestaRol getRolById(Long id_rol);
    Page<DatosListadoRol> getAllRoles(Pageable pageable);
    DatosRespuestaRol createRol(DatosRegistroRol datosRegistroRol);
    DatosRespuestaRol updateRol(DatosActualizarRol datosActualizarRol);
    void deleteRol(Long id_rol);
    Optional<Rol> findByNombreRol(String nombre);

} 