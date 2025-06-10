package com.neon.sve.service.cliente;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.neon.sve.dto.cliente.DatosActualizarCliente;
import com.neon.sve.dto.cliente.DatosListadoCliente;
import com.neon.sve.dto.cliente.DatosRegistroCliente;
import com.neon.sve.dto.cliente.DatosRespuestaCliente;
import com.neon.sve.model.ventas.Cliente;
import com.neon.sve.repository.ClienteRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public DatosRespuestaCliente getClienteById(Long id) {
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);
        if (clienteOptional.isPresent()) {
            Cliente cliente = clienteOptional.get();
            return new DatosRespuestaCliente(cliente);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado");
        }

    }

    @Override
    public Page<DatosListadoCliente> getAllCliente(Pageable pageable) {
        Page<Cliente> clientePage = clienteRepository.findAll(pageable);
        return clientePage.map(DatosListadoCliente::new);
    }

    @Override
    public DatosRespuestaCliente createCliente(DatosRegistroCliente datosRegistroCliente) {
        if (clienteRepository.findByDni(datosRegistroCliente.dni()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "DNI ya registrado.");
        }

        if (clienteRepository.findByCorreo(datosRegistroCliente.correo()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Correo ya registrado.");
        }

        if (clienteRepository.findByCelular(datosRegistroCliente.celular()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Celular ya registrado.");
        }

        Cliente cliente = clienteRepository.save(new Cliente(datosRegistroCliente));
        return new DatosRespuestaCliente(cliente);
    }

    @Override
    public DatosRespuestaCliente updateCliente(DatosActualizarCliente datosActualizarCliente) {

        Cliente cliente = clienteRepository.findById(datosActualizarCliente.id())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Cliente no encontrado con ID: " + datosActualizarCliente.id()));

        // Validar colisión de DNI con otro cliente
        clienteRepository.findByDni(datosActualizarCliente.dni()).ifPresent(c -> {
            if (!c.getId().equals(datosActualizarCliente.id())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Ya existe otro cliente con el DNI ingresado: " + datosActualizarCliente.dni());
            }
        });

        // Validar colisión de correo con otro cliente
        clienteRepository.findByCorreo(datosActualizarCliente.correo()).ifPresent(c -> {
            if (!c.getId().equals(datosActualizarCliente.id())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Ya existe otro cliente con el correo ingresado: " + datosActualizarCliente.correo());
            }
        });

        // Validar colisión de celular con otro cliente
        clienteRepository.findByCelular(datosActualizarCliente.celular()).ifPresent(c -> {
            if (!c.getId().equals(datosActualizarCliente.id())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Ya existe otro cliente con el celular ingresado: " + datosActualizarCliente.celular());
            }
        });

        cliente.actualizar(datosActualizarCliente);
        cliente = clienteRepository.save(cliente);
        return new DatosRespuestaCliente(cliente);
    }

    @Override
    public void activarCliente(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado, ID: " + id));
        if (Boolean.TRUE.equals(cliente.getActivo())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El cliente ya está activo");
        }
        cliente.setActivo(true);
        clienteRepository.save(cliente);
    }

    @Override
    public void desactivarCliente(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado, ID: " + id));
        if (!cliente.getActivo()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El cliente ya está desactivado");
        }
        cliente.setActivo(false);
        clienteRepository.save(cliente);
    }

}
