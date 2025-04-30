package com.neon.sve.service.empleado;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.neon.sve.dto.empleado.DatosActualizarEmpleado;
import com.neon.sve.dto.empleado.DatosListadoEmpleado;
import com.neon.sve.dto.empleado.DatosRegistroEmpleado;
import com.neon.sve.dto.empleado.DatosRespuestaEmpleado;

public interface EmpleadoService {

    DatosRespuestaEmpleado getEmpleadoById(Long id_empleado);

    Page<DatosListadoEmpleado> getAllEmpleados(Pageable pageable);

    DatosRespuestaEmpleado createEmpleado(DatosRegistroEmpleado datosRegistroEmpleado);

    DatosRespuestaEmpleado updateEmpleado(DatosActualizarEmpleado datosActualizarEmpleado);

   // DatosRespuestaEmpleado getEmpleadoByCorreo(String correo_empleado);

    void activarEmpleado(Long id_empleado);

    void desactivarEmpleado(Long id_empleado);
}