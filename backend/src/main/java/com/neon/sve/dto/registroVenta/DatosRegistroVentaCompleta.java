package com.neon.sve.dto.registroVenta;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record DatosRegistroVentaCompleta(

        @NotNull Long id_usuario,

        @NotNull Long id_cliente,

        @NotNull Long id_metodo_pago,

        Long id_cupon, // Puede ser nulo

        @NotNull @Size(min = 1, message = "La venta debe tener al menos un item.") @Valid
        List<DatosRegistroItemVenta> items

) {

}
