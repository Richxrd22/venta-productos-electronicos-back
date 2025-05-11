package com.neon.sve.service.ingresoStock;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.neon.sve.dto.ingresoStock.DatosActualizarIngresoStock;
import com.neon.sve.dto.ingresoStock.DatosListadoIngresoStock;
import com.neon.sve.dto.ingresoStock.DatosRegistroIngresoStock;
import com.neon.sve.dto.ingresoStock.DatosRespuestaIngresoStock;
import com.neon.sve.model.producto.Producto;
import com.neon.sve.model.producto.Proveedor;
import com.neon.sve.model.usuario.Usuario;
import com.neon.sve.model.ventas.IngresoStock;
import com.neon.sve.repository.IngresoStockRepository;
import com.neon.sve.repository.ProductoRepository;
import com.neon.sve.repository.ProveedorRepository;
import com.neon.sve.repository.UsuarioRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class IngresoStockServiceImpl implements IngresoStockService {

    @Autowired
    private IngresoStockRepository ingresoStockRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Override
    public DatosRespuestaIngresoStock getIngresoStockById(Long id) {

        Optional<IngresoStock> ingresoStockOptional = ingresoStockRepository.findById(id);
        if (ingresoStockOptional.isPresent()) {
            IngresoStock ingresoStock = ingresoStockOptional.get();
            return new DatosRespuestaIngresoStock(ingresoStock);
        } else {
            throw new RuntimeException("Ingreso de stock no encontrado, verifique ID ingresado");
        }
    }

    @Override
    public Page<DatosListadoIngresoStock> getAllIngresoStock(Pageable pageable) {
        Page<IngresoStock> ingresoStockPage = ingresoStockRepository.findAll(pageable);
        return ingresoStockPage.map(DatosListadoIngresoStock::new);
    }

    @Override
    public DatosRespuestaIngresoStock createIngresoStock(DatosRegistroIngresoStock datosRegistroIngresoStock) {

        Producto producto = productoRepository.getReferenceById(datosRegistroIngresoStock.id_producto());
        Proveedor proveedor = proveedorRepository.getReferenceById(datosRegistroIngresoStock.id_proveedor());
        Usuario usuario = usuarioRepository.getReferenceById(datosRegistroIngresoStock.id_usuario());
        IngresoStock ingresoStock = ingresoStockRepository
                .save(new IngresoStock(datosRegistroIngresoStock, producto, proveedor, usuario));
        return new DatosRespuestaIngresoStock(ingresoStock);

    }

    @Override
    public DatosRespuestaIngresoStock updateIngresoStock(DatosActualizarIngresoStock datosActualizarIngresoStock) {
        Producto producto = productoRepository.getReferenceById(datosActualizarIngresoStock.id_producto());
        Proveedor proveedor = proveedorRepository.getReferenceById(datosActualizarIngresoStock.id_proveedor());
        Usuario usuario = usuarioRepository.getReferenceById(datosActualizarIngresoStock.id_usuario());
        IngresoStock ingresoStock = ingresoStockRepository.getReferenceById(datosActualizarIngresoStock.id());
        ingresoStock.actualizar(datosActualizarIngresoStock, producto, proveedor, usuario);
        return new DatosRespuestaIngresoStock(ingresoStock);
    }

    @Override
    public void activarIngresoStock(Long id) {
        IngresoStock ingresoStock = ingresoStockRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Ingreso de Stock no encontrado, verifique ID ingresado : " + id));

        if (!ingresoStock.getActivo()) {

            ingresoStock.setActivo(true);
            ingresoStockRepository.save(ingresoStock);
        }

    }

    @Override
    public void desactivarIngresoStock(Long id) {
        IngresoStock ingresoStock = ingresoStockRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Ingreso de Stock no encontrado, verifique ID ingresado : " + id));

        if (ingresoStock.getActivo()) {

            ingresoStock.setActivo(false);
            ingresoStockRepository.save(ingresoStock);
        }
    }

}
