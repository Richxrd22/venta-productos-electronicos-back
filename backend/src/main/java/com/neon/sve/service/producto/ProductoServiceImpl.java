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
import com.neon.sve.model.producto.Categoria;
import com.neon.sve.model.producto.Marca;
import com.neon.sve.model.producto.Producto;
import com.neon.sve.model.usuario.Usuario;
import com.neon.sve.repository.CategoriaRepository;
import com.neon.sve.repository.MarcaRepository;
import com.neon.sve.repository.ProductoRepository;
import com.neon.sve.repository.UsuarioRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductoServiceImpl implements ProductoService {

        @Autowired
        private ProductoRepository productoRepository;

        @Autowired
        private UsuarioRepository usuarioRepository;

        @Autowired
        private CategoriaRepository categoriaRepository;


        @Autowired
        private MarcaRepository marcaRepository;

        @Override
        public DatosRespuestaProducto getProductoById(Long id) {
                Optional<Producto> productOptional = productoRepository.findById(id);
                if (productOptional.isPresent()) {

                        Producto producto = productOptional.get();
                        return new DatosRespuestaProducto(producto);

                } else {

                        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                                        "Producto no encontrado, verifique ID ingresado");
                }
        }

        @Override
        public Page<DatosListadoProducto> getAllProducto(Pageable pageable) {
                Page<Producto> productoPage = productoRepository.findAll(pageable);
                return productoPage.map(DatosListadoProducto::new);
        }

        @Override
        public DatosRespuestaProducto createProducto(DatosRegistroProducto datosRegistroProducto) {

                Usuario usuario = usuarioRepository.findById(datosRegistroProducto.id_usuario())
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                "Usuario no encontrado"));

                Categoria categoria = categoriaRepository.findById(datosRegistroProducto.id_categoria())
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                "Categoria no encontrada"));

                Marca marca = marcaRepository.findById(datosRegistroProducto.id_marca())
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                "Marca no encontrada"));

                String skuTemporal = "TEMP-" + System.currentTimeMillis();

                Producto producto = new Producto(datosRegistroProducto, usuario, categoria, marca, skuTemporal);
                producto = productoRepository.save(producto);

                String sku = generarSKU(
                                marca.getNombre(),
                                categoria.getNombre(),
                                datosRegistroProducto.modelo(),
                                datosRegistroProducto.color(),
                                producto.getId());

                producto.setSku(sku);
                producto = productoRepository.save(producto);

                return new DatosRespuestaProducto(producto);
        }

        @Override
        public DatosRespuestaProducto updateProducto(DatosActualizarProducto datosActualizarProducto) {

                Producto producto = productoRepository.findById(datosActualizarProducto.id())
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                "Producto no encontrado"));

                Usuario usuario = usuarioRepository.findById(datosActualizarProducto.id_usuario())
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                "Usuario no encontrado"));

                Marca marca = marcaRepository.findById(datosActualizarProducto.id_marca())
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                "Marca no encontrada"));

                Categoria categoria = categoriaRepository.findById(datosActualizarProducto.id_categoria())
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                "Categoria no encontrada"));

                producto.actualizar(datosActualizarProducto, usuario, categoria, marca);

                String nuevoSKU = generarSKU(
                                marca.getNombre(),
                                categoria.getNombre(),
                                datosActualizarProducto.modelo(),
                                datosActualizarProducto.color(),
                                producto.getId());
                producto.setSku(nuevoSKU);

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

                if (!Boolean.TRUE.equals(producto.getId_marca().getActivo())) {
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                                        "No se puede activar el producto porque la marca asociada está desactivada");
                }

                if (!Boolean.TRUE.equals(producto.getId_categoria().getActivo())) {
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                                        "No se puede activar el producto porque la subcategoría asociada está desactivada");
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
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                                        "El producto ya se encuenta desactivado");
                }
                producto.setActivo(false);
                productoRepository.save(producto);
        }

        private String generarSKU(String marca, String subcategoria, String modelo, String color, Long id) {
                String abrevMarca = abreviar(marca);
                String abrevSubcat = abreviar(subcategoria);
                String abrevModelo = (modelo != null && !modelo.isBlank()) ? abreviar(modelo) : "MOD";
                String abrevColor = (color != null && !color.isBlank()) ? abreviar(color) : "COL";

                return String.format("%s-%s-%s-%s-%03d", abrevMarca, abrevSubcat, abrevModelo, abrevColor, id);
        }

        private String abreviar(String texto) {
                texto = texto.replaceAll("[^a-zA-Z0-9]", "");
                return texto.length() >= 3
                                ? texto.substring(0, 3).toUpperCase()
                                : texto.toUpperCase();
        }
}
