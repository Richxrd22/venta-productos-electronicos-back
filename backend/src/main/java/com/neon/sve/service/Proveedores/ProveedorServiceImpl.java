package com.neon.sve.service.proveedores;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.neon.sve.dto.proveedores.DatosActualizarProveedores;
import com.neon.sve.dto.proveedores.DatosListadoProveedores;
import com.neon.sve.dto.proveedores.DatosRegistroProveedores;
import com.neon.sve.dto.proveedores.DatosRespuestaProveedores;
import com.neon.sve.model.producto.Proveedor;
import com.neon.sve.repository.ProveedorRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProveedorServiceImpl implements ProveedoreService {

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Override
    public DatosRespuestaProveedores getProveedroById(Long id) {

        Optional<Proveedor> proveedorOptional = proveedorRepository.findById(id);
        if (proveedorOptional.isPresent()) {
            Proveedor proveedor = proveedorOptional.get();
            return new DatosRespuestaProveedores(proveedor);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Proveedor no encontrado");

        }

    }

    @Override
    public Page<DatosListadoProveedores> getAllProveedores(Pageable pageable) {
        Page<Proveedor> proveedorPage = proveedorRepository.findAll(pageable);
        return proveedorPage.map(DatosListadoProveedores::new);

    }

    @Override
    public DatosRespuestaProveedores createProveedores(DatosRegistroProveedores datosRegistroProveedores) {

        Optional<Proveedor> proveedorOptional = proveedorRepository
                .findByRazon_social(datosRegistroProveedores.razon_social());

        if (proveedorOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Ya existe un proveedor con la razon social ingresada: " + datosRegistroProveedores.razon_social());
        }

        Proveedor proveedor = proveedorRepository.save(new Proveedor(datosRegistroProveedores));
        return new DatosRespuestaProveedores(proveedor);

    }

    @Override
    public DatosRespuestaProveedores updateProveedores(DatosActualizarProveedores datosActualizarProveedores) {

        Optional<Proveedor> proveedorOptional = proveedorRepository
                .findByRazon_social(datosActualizarProveedores.razon_social());

        if (proveedorOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Ya existe un proveedor con la razon social ingresada: "
                            + datosActualizarProveedores.razon_social());
        }

        Proveedor proveedor = proveedorRepository.getReferenceById(datosActualizarProveedores.id());
        proveedor.actualizar(datosActualizarProveedores);
        proveedor = proveedorRepository.save(proveedor);
        return new DatosRespuestaProveedores(proveedor);

    }

    @Override
    public void activarProveedor(Long id) {

        Proveedor proveedor = proveedorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Proveedor no encontrado con ID:" + id));

        if (Boolean.TRUE.equals(proveedor.getActivo())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El proveedor ya esta activo");

        }
        proveedor.setActivo(true);
        proveedorRepository.save(proveedor);
    }

    @Override
    public void desactivarProveedor(Long id) {
        Proveedor proveedor = proveedorRepository.findById(id)
                .orElseThrow(
                        () -> new EntityNotFoundException("Proveedor no encontrado, verifique el ID ingresado :" + id));

        if (!proveedor.getActivo()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El proveedor ya esta desactivado");
        }
        proveedor.setActivo(false);
        proveedorRepository.save(proveedor);
    }

}
