package com.neon.sve.service.marca;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.neon.sve.dto.marca.DatosActualizarMarca;
import com.neon.sve.dto.marca.DatosListadoMarca;
import com.neon.sve.dto.marca.DatosRegistroMarca;
import com.neon.sve.dto.marca.DatosRespuestaMarca;
import com.neon.sve.model.producto.Marca;
import com.neon.sve.repository.MarcaRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class MarcaServiceImpl implements MarcaService {

    @Autowired
    private MarcaRepository marcaRepository;

    @Override
    public DatosRespuestaMarca getMarcaById(Long id) {
        Optional<Marca> marcaOptional = marcaRepository.findById(id);
        if (marcaOptional.isPresent()) {
            Marca marca = marcaOptional.get();
            return new DatosRespuestaMarca(marca);

        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Marca no encontrada");
        }
    }

    @Override
    public Page<DatosListadoMarca> getAllMarca(Pageable pageable) {
        Page<Marca> marcaPage = marcaRepository.findAll(pageable);
        return marcaPage.map(DatosListadoMarca::new);
    }

    @Override
    public DatosRespuestaMarca createMarca(DatosRegistroMarca datosRegistroMarca) {
        Optional<Marca> marcaOptional = marcaRepository.findByNombre(datosRegistroMarca.nombre());
        if (marcaOptional.isPresent()) {

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Ya existe una marca con el nombre ingresado, verifique o intentato nuevamente : "
                            + datosRegistroMarca.nombre());
        }
        Marca marca = marcaRepository.save(new Marca(datosRegistroMarca));
        return new DatosRespuestaMarca(marca);

    }

    @Override
    public DatosRespuestaMarca updateMarca(DatosActualizarMarca datosActualizarmarca) {
        Optional<Marca> marcaOptional = marcaRepository.findByNombre(datosActualizarmarca.nombre());
        if (marcaOptional.isPresent()) {

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Ya existe una marca con el nombre ingresado, verifique o intentato nuevamente : "
                            + datosActualizarmarca.nombre());
        }

        Marca marca = marcaRepository.getReferenceById(datosActualizarmarca.id());
        marca.actualizar(datosActualizarmarca);
        marca = marcaRepository.save(marca);
        return new DatosRespuestaMarca(marca);

    }

    @Override
    public void activarMarca(Long id) {
        Marca marca = marcaRepository.findById(id)
                .orElseThrow(
                        () -> new EntityNotFoundException("Marca no encontrada, verifique el ID ingresado : " + id));

        if (Boolean.TRUE.equals(marca.getActivo())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La Marca ya esta activa");

        }

        marca.setActivo(true);
        marcaRepository.save(marca);
    }

    @Override
    public void desactivarMarca(Long id) {
        Marca marca = marcaRepository.findById(id)
                .orElseThrow(
                        () -> new EntityNotFoundException("Marca no encontrada, verifique el ID ingresado : " + id));

        if (!marca.getActivo()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La Marca ya esta desactivada");
        }

        marca.setActivo(false);
        marcaRepository.save(marca);
    }

}
