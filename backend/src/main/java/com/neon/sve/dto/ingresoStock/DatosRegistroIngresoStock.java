package com.neon.sve.dto.ingresoStock;


import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroIngresoStock(

                @NotNull Long id_proveedor,
                @NotBlank String tipo_documento,
                @NotBlank String numero_documento,
                String observaciones,
                @NotNull Long id_usuario,

                // Lista de productos
                @NotEmpty(message = "Debe ingresar al menos un producto") List<DatosProductoIngreso> productos

) {

}
