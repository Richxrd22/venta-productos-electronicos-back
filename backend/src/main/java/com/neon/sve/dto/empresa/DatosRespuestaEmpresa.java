package com.neon.sve.dto.empresa;

import com.neon.sve.model.Producto.Empresa;

public record DatosRespuestaEmpresa(
        Long id_empresa,
        String nombre,
        String ruc,
        String website,
        int activo
        ) {

    public DatosRespuestaEmpresa(Empresa empresa) {
        this(
                empresa.getId_empresa(),
                empresa.getNombre(),
                empresa.getRuc(),
                empresa.getWebsite(),
                empresa.getActivo() != null && empresa.getActivo() ? 1 : 0);
                
    }
}