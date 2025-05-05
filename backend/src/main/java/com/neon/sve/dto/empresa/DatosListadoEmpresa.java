package com.neon.sve.dto.empresa;

import com.neon.sve.model.Producto.Empresa;

public record DatosListadoEmpresa(
        Long id_empresa,
        String nombre,
        String ruc,
        String website
        ) {

    public DatosListadoEmpresa(Empresa empresa) {
        this(
                empresa.getId_empresa(),
                empresa.getNombre(),
                empresa.getRuc(),
                empresa.getWebsite());

    }
}