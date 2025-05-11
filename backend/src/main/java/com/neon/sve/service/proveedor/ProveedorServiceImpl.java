package com.neon.sve.service.proveedor;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.neon.sve.dto.proveedor.DatosActualizarProveedor;
import com.neon.sve.dto.proveedor.DatosListadoProveedor;
import com.neon.sve.dto.proveedor.DatosRegistroProveedor;
import com.neon.sve.dto.proveedor.DatosRespuestaProveedor;
import com.neon.sve.model.producto.Proveedor;
import com.neon.sve.repository.ProveedorRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProveedorServiceImpl implements ProveedorService{

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Override
    public DatosRespuestaProveedor getProveedroById(Long id) {
        
        Optional<Proveedor> proveedorOptional = proveedorRepository.findById(id);
        if (proveedorOptional.isPresent()) {
            Proveedor proveedor = proveedorOptional.get();
            return new DatosRespuestaProveedor(proveedor);
        } else {
            throw new RuntimeException("Proveedor no encontrado");

        }

    }

    @Override
    public Page<DatosListadoProveedor> getAllProveedores(Pageable pageable) {
        Page<Proveedor> proveedorPage = proveedorRepository.findAll(pageable);
        return proveedorPage.map(DatosListadoProveedor::new);

    }

    @Override
    public DatosRespuestaProveedor createProveedores(DatosRegistroProveedor datosRegistroProveedores) {
        
        Proveedor proveedor = proveedorRepository.save(new Proveedor(datosRegistroProveedores));
        
        return new DatosRespuestaProveedor(proveedor);

    }

    @Override
    public DatosRespuestaProveedor updateProveedores(DatosActualizarProveedor datosActualizarProveedores) {
        
        Proveedor proveedor = proveedorRepository.getReferenceById(datosActualizarProveedores.id());
        proveedor.actualizar(datosActualizarProveedores);
        proveedor = proveedorRepository.save(proveedor);
        return new DatosRespuestaProveedor(proveedor);

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

        if (proveedor.getActivo()) {
            proveedor.setActivo(false);
            proveedorRepository.save(proveedor);

        }

        
    }
    
}
