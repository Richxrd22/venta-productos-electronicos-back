package com.neon.sve.service.producto;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.neon.sve.dto.producto.DatosActualizarProducto;
import com.neon.sve.dto.producto.DatosListadoProducto;
import com.neon.sve.dto.producto.DatosRegistroProducto;
import com.neon.sve.dto.producto.DatosRespuestaProducto;
import com.neon.sve.model.producto.Marca;
import com.neon.sve.model.producto.Producto;
import com.neon.sve.model.producto.SubCategoria;
import com.neon.sve.model.usuario.Usuario;
import com.neon.sve.repository.MarcaRepository;
import com.neon.sve.repository.ProductoRepository;
import com.neon.sve.repository.SubCategoriaRepository;
import com.neon.sve.repository.UsuarioRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private SubCategoriaRepository subCategoriaRepository;

    @Autowired
    private MarcaRepository marcaRepository;

    @Override
    public DatosRespuestaProducto getProductoById(Long id) {
        Optional<Producto> productOptional = productoRepository.findById(id);
        if (productOptional.isPresent()) {

            Producto producto = productOptional.get();
            return new DatosRespuestaProducto(producto);

        } else {

            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado, verifique ID ingresado");
        }
    }

    @Override
    public Page<DatosListadoProducto> getAllProducto(Pageable pageable) {
        Page<Producto> productoPage = productoRepository.findAll(pageable);
        return productoPage.map(DatosListadoProducto::new);
    }

    @Override
    public DatosRespuestaProducto createProducto(DatosRegistroProducto datosRegistroProducto) {
        Optional<Producto> productOptional = productoRepository.findByNombre(datosRegistroProducto.nombre());
        if (productOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Ya existe un producto con el nombre ingresado : " + datosRegistroProducto.nombre());

        }
        Usuario usuario = usuarioRepository.getReferenceById(datosRegistroProducto.id_usuario());
        SubCategoria subCategoria = subCategoriaRepository.getReferenceById(datosRegistroProducto.id_subcategoria());
        Marca marca = marcaRepository.getReferenceById(datosRegistroProducto.id_marca());
        Producto producto = productoRepository
                .save(new Producto(datosRegistroProducto, usuario, subCategoria, marca));
        return new DatosRespuestaProducto(producto);

    }

    @Override
    public DatosRespuestaProducto updateProducto(DatosActualizarProducto datosActualizarProducto) {
        Optional<Producto> productOptional = productoRepository.findByNombre(datosActualizarProducto.nombre());
        if (productOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Ya existe un producto con el nombre ingresado : " + datosActualizarProducto.nombre());

        }
        Usuario usuario = usuarioRepository.getReferenceById(datosActualizarProducto.id_usuario());
        SubCategoria subCategoria = subCategoriaRepository.getReferenceById(datosActualizarProducto.id_subcategoria());
        Marca marca = marcaRepository.getReferenceById(datosActualizarProducto.id_marca());
        Producto producto = productoRepository.getReferenceById(datosActualizarProducto.id());
        producto.actualizar(datosActualizarProducto, usuario, subCategoria, marca);
        producto = productoRepository.save(producto);
        return new DatosRespuestaProducto(producto);

    }

    @Override
    public void activarProducto(Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Producto no encontrado con el ID ingresado, verifique ID : " + id));

        if (Boolean.TRUE.equals(producto.getActivo())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El producto ya esta activado");
        }
        producto.setActivo(true);
        productoRepository.save(producto);
    }

    @Override
    public void desactivarProducto(Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Producto no encontrado con el ID ingresado, verifique ID : " + id));

        if (!producto.getActivo()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El producto ya se encuenta desactivado");
        }
        producto.setActivo(false);
        productoRepository.save(producto);
    }

}
