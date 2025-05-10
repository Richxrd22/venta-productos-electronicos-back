package com.neon.sve.service.ingresoStock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.neon.sve.dto.ingresoStock.DatosActualizarIngresoStock;
import com.neon.sve.dto.ingresoStock.DatosListadoIngresoStock;
import com.neon.sve.dto.ingresoStock.DatosRegistroIngresoStock;
import com.neon.sve.dto.ingresoStock.DatosRespuestaIngresoStock;
import com.neon.sve.model.Ventas.IngresoStock;
import com.neon.sve.repository.IngresoStockRepository;
import com.neon.sve.repository.ProductoRepository;
import com.neon.sve.repository.ProveedorRepository;
import com.neon.sve.repository.UsuarioRepository;

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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getIngresoStockById'");
    }

    @Override
    public Page<DatosListadoIngresoStock> getAllIngresoStock(Pageable pageable) {
        Page<IngresoStock> ingresoStockPage = ingresoStockRepository.findAll(pageable);
        return ingresoStockPage.map(DatosListadoIngresoStock::new);
    }

    @Override
    public DatosRespuestaIngresoStock createIngresoStock(DatosRegistroIngresoStock datosRegistroIngresoStock) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createIngresoStock'");
    }

    @Override
    public DatosRespuestaIngresoStock updateIngresoStock(DatosActualizarIngresoStock datosActualizarIngresoStock) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateIngresoStock'");
    }

    @Override
    public void activarIngresoStock(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'activarIngresoStock'");
    }

    @Override
    public void desactivarIngresoStock(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'desactivarIngresoStock'");
    }

}
