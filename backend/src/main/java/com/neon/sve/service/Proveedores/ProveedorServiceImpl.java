package com.neon.sve.service.Proveedores;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.neon.sve.dto.Proveedores.DatosActualizarProveedores;
import com.neon.sve.dto.Proveedores.DatosListadoProveedores;
import com.neon.sve.dto.Proveedores.DatosRegistroProveedores;
import com.neon.sve.dto.Proveedores.DatosRespuestaProveedores;
import com.neon.sve.model.Producto.Proveedor;
import com.neon.sve.repository.ProveedorRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProveedorServiceImpl implements ProveedoreService{

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Override
    public DatosRespuestaProveedores getProveedroById(Long id) {
        
        Optional<Proveedor> proveedorOptional = proveedorRepository.findById(id);
        if (proveedorOptional.isPresent()) {
            Proveedor proveedor = proveedorOptional.get();
            return new DatosRespuestaProveedores(proveedor);
        } else {
            throw new RuntimeException("Proveedor no encontrado");

        }

    }

    @Override
    public Page<DatosListadoProveedores> getAllProveedores(Pageable pageable) {
        Page<Proveedor> proveedorPage = proveedorRepository.findAll(pageable);
        return proveedorPage.map(DatosListadoProveedores::new);

    }

    @Override
    public DatosRespuestaProveedores createProveedores(DatosRegistroProveedores datosRegistroProveedores) {
        
        Proveedor proveedor = proveedorRepository.save(new Proveedor(datosRegistroProveedores));
        
        return new DatosRespuestaProveedores(proveedor);

    }

    @Override
    public DatosRespuestaProveedores updateProveedores(DatosActualizarProveedores datosActualizarProveedores) {
        
        Proveedor proveedor = proveedorRepository.getReferenceById(datosActualizarProveedores.id());
        proveedor.actualizar(datosActualizarProveedores);
        proveedor = proveedorRepository.save(proveedor);
        return new DatosRespuestaProveedores(proveedor);

    }

    @Override
    public void activarProveedor(Long id) {
        
        Proveedor proveedor = proveedorRepository.findById(id)
        .orElseThrow(()-> new EntityNotFoundException("Proveedor no encontrado con ID:" + id));

        if (!proveedor.getActivo()) {
            proveedor.setActivo(true);
            proveedorRepository.save(proveedor);

        }

    }

    @Override
    public void desactivarProveedor(Long id) {

        Proveedor proveedor = proveedorRepository.findById(id)
        .orElseThrow(()-> new EntityNotFoundException("Proveedor no encontrado con ID:" + id));

        if (!proveedor.getActivo()) {
            proveedor.setActivo(false);
            proveedorRepository.save(proveedor);

        }

        
    }
    
}
