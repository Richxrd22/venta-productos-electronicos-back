package com.neon.sve.service.cliente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.neon.sve.dto.cliente.DatosActualizarCliente;
import com.neon.sve.dto.cliente.DatosListadoCliente;
import com.neon.sve.dto.cliente.DatosRegistroCliente;
import com.neon.sve.dto.cliente.DatosRespuestaCliente;


public interface ClienteService {

    DatosRespuestaCliente getClienteById(Long id);

    Page<DatosListadoCliente> getAllCliente (Pageable pageable);

    DatosRespuestaCliente createCliente(DatosRegistroCliente datosRegistroCliente);

    DatosRespuestaCliente updateCliente(DatosActualizarCliente datosActualizarCliente);

    void activarCliente(Long id);

    void desactivarCliente(Long id);
    
}
