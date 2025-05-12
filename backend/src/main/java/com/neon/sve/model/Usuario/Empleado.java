package com.neon.sve.model.usuario;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.neon.sve.dto.empleado.DatosActualizarEmpleado;
import com.neon.sve.dto.empleado.DatosRegistroEmpleado;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data 
@Builder
@Entity
@NoArgsConstructor 
@AllArgsConstructor 
@EqualsAndHashCode(of = "id")
@Table(name = "empleados")
public class Empleado {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @Column(unique = true, nullable = false, length = 9)
    private String celular;

    @Column(unique = true, nullable = false)
    private String correo;

    @Column(unique = true, nullable = false, length = 8)
    private String dni;

    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private Boolean activo;

    @JsonIgnore
    @OneToOne(mappedBy = "id_empleado")
    private Usuario usuario;

     public Empleado(@Valid DatosRegistroEmpleado datosRegistroEmpleado) {
        this.nombre = datosRegistroEmpleado.nombre();
        this.apellido = datosRegistroEmpleado.apellido();
        this.dni = datosRegistroEmpleado.dni();
        this.correo = datosRegistroEmpleado.correo();
        this.celular = datosRegistroEmpleado.celular();
        this.activo = datosRegistroEmpleado.activo();
    }

    public void actualizar(@Valid DatosActualizarEmpleado datosActualizarEmpleado) {
        this.nombre = datosActualizarEmpleado.nombre();
        this.apellido = datosActualizarEmpleado.apellido();
        this.dni = datosActualizarEmpleado.dni();
        this.correo = datosActualizarEmpleado.correo();
        this.celular = datosActualizarEmpleado.celular();
        this.activo = datosActualizarEmpleado.activo();
    }

}
