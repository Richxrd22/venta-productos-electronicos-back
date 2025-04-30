package com.neon.sve.dto.rol;

import com.neon.sve.model.Usuario.Rol;

public record DatosListadoRol(
    Long id_rol,
    String nombre_rol) {

  public DatosListadoRol(Rol rol) {
    this(
        rol.getId_rol(),
        rol.getNombre_rol());
  }
}