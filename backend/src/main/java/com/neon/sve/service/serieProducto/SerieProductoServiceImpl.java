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
import com.neon.sve.dto.serie.DatosListadoSerieDetalle;
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
        DetalleIngreso detalleIngreso = detalleIngresoRepository.findById(datosRegistroSerie.id_detalle_ingreso())
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Detalle de ingreso no encontrado"));

        validarProductoConSeries(detalleIngreso);
        validarCantidadSeries(detalleIngreso);
        validarSerieLocal(datosRegistroSerie.numero_serie(), detalleIngreso);
        validarSerieGlobal(datosRegistroSerie.numero_serie());

        SerieProducto nuevaSerie = new SerieProducto(datosRegistroSerie, detalleIngreso);
        serieProductoRepository.save(nuevaSerie);

        return new DatosRespuestaSerie(nuevaSerie);
    }

    @Override
    public DatosRespuestaSerie updateSerieProductos(DatosActualizarSerie datosActualizarSerie) {
        SerieProducto serieProducto = serieProductoRepository.findById(datosActualizarSerie.id())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Serie Producto no encontrada"));

        Long idIngresoOriginal = serieProducto.getId_detalle_ingreso().getId();
        Long idIngresoActualizado = datosActualizarSerie.id_detalle_ingreso();

        if (!idIngresoOriginal.equals(idIngresoActualizado)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "No se puede cambiar el detalle de ingreso original de la serie.");
        }

        String nuevaSerie = datosActualizarSerie.numero_serie();
        String serieActual = serieProducto.getNumeroSerie();

        if (!nuevaSerie.equals(serieActual)) {
            validarSerieLocal(nuevaSerie, serieProducto.getId_detalle_ingreso());
            validarSerieGlobal(nuevaSerie);
        }

        serieProducto.actualizar(datosActualizarSerie, serieProducto.getId_detalle_ingreso());
        serieProductoRepository.save(serieProducto);

        return new DatosRespuestaSerie(serieProducto);
    }

    private void validarProductoConSeries(DetalleIngreso detalleIngreso) {
        long totalSeries = serieProductoRepository.contarPorDetalleIngreso(detalleIngreso);
        if (totalSeries == 0 && detalleIngreso.getCantidad() > 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Este detalle está registrado como producto sin serie. No se pueden agregar series.");
        }
    }

    private void validarCantidadSeries(DetalleIngreso detalleIngreso) {
        List<EstadoSerie> estadosValidos = List.of(
                EstadoSerie.ACTIVO,
                EstadoSerie.VENDIDO,
                EstadoSerie.REPARACION,
                EstadoSerie.DEVUELTO);

        long cantidadSeries = serieProductoRepository.contarPorDetalleIngresoYEstados(detalleIngreso, estadosValidos);

        if (cantidadSeries >= detalleIngreso.getCantidad()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Ya se han registrado todas las series activas para este detalle.");
        }
    }

    private void validarSerieLocal(String numeroSerie, DetalleIngreso detalleIngreso) {
        Optional<SerieProducto> serieExistente = serieProductoRepository
                .findByNumeroSerieAndDetalleIngreso(numeroSerie, detalleIngreso);

        if (serieExistente.isPresent()) {
            EstadoSerie estado = serieExistente.get().getEstado();
            String mensaje = switch (estado) {
                case ACTIVO -> "Ya existe una serie con ese número en estado ACTIVO para este detalle.";
                case INACTIVO -> "Esa serie ya existe en estado INACTIVO. Puede reactivarla si lo desea.";
                default -> "Ya existe una serie con ese número asociada a este detalle.";
            };
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, mensaje);
        }
    }

    private void validarSerieGlobal(String numeroSerie) {
        List<EstadoSerie> estadosOcupados = List.of(
                EstadoSerie.ACTIVO,
                EstadoSerie.VENDIDO,
                EstadoSerie.REPARACION,
                EstadoSerie.DEVUELTO);

        boolean yaExiste = serieProductoRepository.existsByNumeroSerieAndEstadoIn(numeroSerie, estadosOcupados);
        if (yaExiste) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "El número de serie '" + numeroSerie + "' ya está en uso activo en otro ingreso.");
        }
    }

    @Override
    public List<DatosListadoSerieDetalle> getAllSerieProductoByDetalleIngresoId(Long id_detalle_ingreso) {
           return serieProductoRepository.buscarPorIdDetalleIngreso(id_detalle_ingreso)
            .stream()
            .map(DatosListadoSerieDetalle::new)
            .toList();
    }

}
