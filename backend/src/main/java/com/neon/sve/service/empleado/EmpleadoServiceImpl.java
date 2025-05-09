package com.neon.sve.service.empleado;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.neon.sve.dto.empleado.DatosActualizarEmpleado;
import com.neon.sve.dto.empleado.DatosListadoEmpleado;
import com.neon.sve.dto.empleado.DatosRegistroEmpleado;
import com.neon.sve.dto.empleado.DatosRespuestaEmpleado;
import com.neon.sve.model.Usuario.Empleado;
import com.neon.sve.repository.EmpleadoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Override
    public DatosRespuestaEmpleado getEmpleadoById(Long id_empleado) {
        Optional<Empleado> empleadoOptional = empleadoRepository.findById(id_empleado);
        if (empleadoOptional.isPresent()) {
            Empleado empleado = empleadoOptional.get();
            return new DatosRespuestaEmpleado(empleado);
        } else {
            throw new RuntimeException("Empleado no encontrado");
        }
    }

    @Override
    public Page<DatosListadoEmpleado> getAllEmpleados(Pageable pageable) {
        Page<Empleado> empleadoPage = empleadoRepository.findAll(pageable);
        return empleadoPage.map(DatosListadoEmpleado::new);
    }

    @Override
    public DatosRespuestaEmpleado createEmpleado(DatosRegistroEmpleado datosRegistroEmpleado) {
        Empleado empleado = empleadoRepository.save(new Empleado(datosRegistroEmpleado));
        return new DatosRespuestaEmpleado(empleado);
    }

    @Override
    public DatosRespuestaEmpleado updateEmpleado(DatosActualizarEmpleado datosActualizarEmpleado) {
        Empleado empleado = empleadoRepository.getReferenceById(datosActualizarEmpleado.id());
        empleado.actualizar(datosActualizarEmpleado);
        empleado = empleadoRepository.save(empleado);
        return new DatosRespuestaEmpleado(empleado);
    }

    @Override
    public void activarEmpleado(Long id_empleado) {
        Empleado empleado = empleadoRepository.findById(id_empleado)
                .orElseThrow(() -> new EntityNotFoundException("Empleado no encontrado con ID: " + id_empleado));

        if (!empleado.getActivo()) {
            empleado.setActivo(true);
            empleadoRepository.save(empleado);
        }
    }

    @Override
    public void desactivarEmpleado(Long id_empleado) {
        Empleado empleado = empleadoRepository.findById(id_empleado)
                .orElseThrow(() -> new EntityNotFoundException("Empleado no encontrado con ID: " + id_empleado));

        if (empleado.getActivo()) {
            empleado.setActivo(false);
            empleadoRepository.save(empleado);
        }
    }


}
