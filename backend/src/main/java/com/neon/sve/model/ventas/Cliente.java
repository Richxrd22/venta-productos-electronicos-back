package com.neon.sve.model.ventas;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "clientes")
public class Cliente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String nombre;

    @Column(nullable = false, length = 50)
    private String apellido;

    @Column(nullable = false, unique = true, length = 8)
    private String dni;

    @Column(nullable = false, unique = true, length = 9)
    private String celular;

    @Column(nullable = false, unique = true, length = 100)
    private String correo;

    @Column(name = "fecha_creacion", updatable = false)
    @CreationTimestamp
    private Timestamp fechaCreacion;

    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private Boolean activo=true;

    @JsonIgnore
    @OneToMany(mappedBy = "id_cliente")
    private List<RegistroVenta> registroVentas;

    // Constructor para registro
    /*
    public Cliente(@Valid DatosRegistroCliente datosRegistroCliente) {
        this.nombre = datosRegistroCliente.nombre();
        this.apellido = datosRegistroCliente.apellido();
        this.dni = datosRegistroCliente.dni();
        this.celular = datosRegistroCliente.celular();
        this.correo = datosRegistroCliente.correo();
        this.activo = true; // Por defecto activo
    }*/

}
