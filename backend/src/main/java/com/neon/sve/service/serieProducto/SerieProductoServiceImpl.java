package com.neon.sve.service.serieProducto;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.neon.sve.dto.serie.DatosActualizarSerie;
import com.neon.sve.dto.serie.DatosListadoSerie;
import com.neon.sve.dto.serie.DatosRegistroSerie;
import com.neon.sve.dto.serie.DatosRespuestaSerie;
import com.neon.sve.model.stock.DetalleIngreso;
import com.neon.sve.model.stock.EstadoSerie;
import com.neon.sve.model.stock.SerieProducto;
import com.neon.sve.repository.DetalleIngresoRepository;
import com.neon.sve.repository.SerieProductoRepository;

@Service
public class SerieProductoServiceImpl implements SerieProductoService {

    @Autowired
    private SerieProductoRepository serieProductoRepository;

    @Autowired
    private DetalleIngresoRepository detalleIngresoRepository;

    @Override
    public DatosRespuestaSerie getSerieProductoById(Long id) {
        Optional<SerieProducto> serie = serieProductoRepository.findById(id); // Simulate fetching from a repository
        if (serie.isPresent()) {
            SerieProducto serieProducto = serie.get();
            return new DatosRespuestaSerie(serieProducto);
        } else {
            throw new RuntimeException("Serie Producto no encontrado");
        }
    }

    @Override
    public Page<DatosListadoSerie> getAllSerieProducto(Pageable pageable) {
        Page<SerieProducto> serieProductoPage = serieProductoRepository.findAll(pageable);
        return serieProductoPage.map(DatosListadoSerie::new);
    }

    @Override
    public DatosRespuestaSerie createSerieProductos(DatosRegistroSerie datosRegistroSerie) {
        DetalleIngreso detalleIngreso = detalleIngresoRepository
                .getReferenceById(datosRegistroSerie.id_detalle_ingreso());

        // Validar si el detalle es sin serie (por convención, si no tiene ninguna serie
        // registrada todavía)
        long totalSeriesRegistradas = serieProductoRepository.contarPorDetalleIngreso(detalleIngreso);

        if (totalSeriesRegistradas == 0 && detalleIngreso.getCantidad() > 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Este detalle está registrado como un producto sin serie. No se pueden agregar series.");
        }

        // Validar que no se exceda la cantidad del detalle
        if (totalSeriesRegistradas >= detalleIngreso.getCantidad()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Ya se han registrado todas las series para este detalle.");
        }

        // Validar si la serie ya existe con estado ocupado
        List<EstadoSerie> estadosOcupados = List.of(
                EstadoSerie.ACTIVO,
                EstadoSerie.VENDIDO,
                EstadoSerie.DEVUELTO,
                EstadoSerie.REPARACION);

        // Validar que no exista ya una serie con el mismo número
        boolean yaExiste = serieProductoRepository.existsByNumeroSerieAndEstadoIn(
                datosRegistroSerie.numero_serie(), estadosOcupados);

        if (yaExiste) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Ya existe una serie con ese número en uso.");
        }

        // Guardar la nueva serie
        SerieProducto serieProducto = serieProductoRepository
                .save(new SerieProducto(datosRegistroSerie, detalleIngreso));

        return new DatosRespuestaSerie(serieProducto);
    }

    @Override
    public DatosRespuestaSerie updateSerieProductos(DatosActualizarSerie datosActualizarSerie) {

        SerieProducto serieProducto = serieProductoRepository.findById(datosActualizarSerie.id())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Serie Producto no encontrado"));

        DetalleIngreso detalleIngreso = detalleIngresoRepository.findById(datosActualizarSerie.id_detalle_ingreso())
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Detalle de ingreso no encontrado"));

        String nuevaSerie = datosActualizarSerie.numero_serie();
        String serieActual = serieProducto.getNumeroSerie();

        // Solo validar si la serie ha cambiado
        if (!nuevaSerie.equals(serieActual)) {
            List<EstadoSerie> estadosOcupados = List.of(
                    EstadoSerie.ACTIVO,
                    EstadoSerie.VENDIDO,
                    EstadoSerie.REPARACION,
                    EstadoSerie.DEVUELTO);

            boolean yaExiste = serieProductoRepository.existsByNumeroSerieAndEstadoIn(nuevaSerie, estadosOcupados);
            if (yaExiste) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "El número de serie '" + nuevaSerie + "' ya está siendo usado en otra serie activa.");
            }
        }

        serieProducto.actualizar(datosActualizarSerie, detalleIngreso);
        serieProductoRepository.save(serieProducto);
        return new DatosRespuestaSerie(serieProducto);
    }

}
