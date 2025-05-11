package com.neon.sve.dto.rol;

import com.neon.sve.model.usuario.Rol;

public record DatosListadoRol(
    Long id,
    String nombre,
    int activo) {

  public DatosListadoRol(Rol rol) {
    this(
        rol.getId(),
        rol.getNombre(),
        rol.getActivo() != null && rol.getActivo() ? 1 : 0); // Convertir a 0 o 1
        
  }
}