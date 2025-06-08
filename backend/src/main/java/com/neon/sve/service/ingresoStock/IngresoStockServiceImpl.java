package com.neon.sve.service.ingresoStock;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.neon.sve.dto.ingresoStock.DatosActualizarIngresoStock;
import com.neon.sve.dto.ingresoStock.DatosListadoIngresoStock;
import com.neon.sve.dto.ingresoStock.DatosRegistroIngresoStock;
import com.neon.sve.dto.ingresoStock.DatosRespuestaIngresoStock;
import com.neon.sve.model.producto.Producto;
import com.neon.sve.model.producto.Proveedor;
import com.neon.sve.model.stock.IngresoStock;
import com.neon.sve.model.usuario.Usuario;
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

        Producto producto = productoRepository.findById(datosRegistroIngresoStock.id_producto())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Producto no encontrado."));

        if (datosRegistroIngresoStock.cantidad() < producto.getMin_stock()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "La cantidad a ingresar no puede ser menor al stock mínimo permitido (" + producto.getMin_stock()
                            + ").");
        }

        if (datosRegistroIngresoStock.cantidad() > producto.getMax_stock()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "La cantidad a ingresar no puede ser mayor al stock máximo permitido (" + producto.getMax_stock()
                            + ").");
        }

        Proveedor proveedor = proveedorRepository.findById(datosRegistroIngresoStock.id_proveedor())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Proveedor no encontrado."));

        Usuario usuario = usuarioRepository.findById(datosRegistroIngresoStock.id_usuario())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuario no encontrado."));

        String lote = generarLotePorProducto(producto.getId());

        IngresoStock ingresoStock = new IngresoStock(
                datosRegistroIngresoStock, producto, proveedor, usuario, lote);

        ingresoStock = ingresoStockRepository.save(ingresoStock);
        return new DatosRespuestaIngresoStock(ingresoStock);

    }

    @Override
    public DatosRespuestaIngresoStock updateIngresoStock(DatosActualizarIngresoStock datosActualizarIngresoStock) {

        Proveedor proveedor = proveedorRepository.findById(datosActualizarIngresoStock.id_proveedor())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Proveedor no encontrado."));

        Usuario usuario = usuarioRepository.findById(datosActualizarIngresoStock.id_usuario())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuario no encontrado."));

        IngresoStock ingresoStock = ingresoStockRepository.findById(datosActualizarIngresoStock.id())
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ingreso de stock no encontrado."));

        Producto producto = ingresoStock.getId_producto();
        if (datosActualizarIngresoStock.cantidad() < producto.getMin_stock()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "La cantidad a ingresar no puede ser menor al stock mínimo permitido (" + producto.getMin_stock()
                            + ").");
        }

        if (datosActualizarIngresoStock.cantidad() > producto.getMax_stock()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "La cantidad a ingresar no puede ser mayor al stock máximo permitido (" + producto.getMax_stock()
                            + ").");
        }
        ingresoStock.actualizar(datosActualizarIngresoStock, proveedor, usuario, producto);
        ingresoStock = ingresoStockRepository.save(ingresoStock);
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

    private String generarLotePorProducto(Long idProducto) {
        long cantidadIngresos = ingresoStockRepository.contarPorProducto(idProducto);
        long siguienteNumero = cantidadIngresos + 1;
        return "L-" + String.format("%03d", siguienteNumero);
    }

}
