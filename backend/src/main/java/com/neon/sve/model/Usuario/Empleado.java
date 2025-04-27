package com.neon.sve.model.Usuario;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.neon.sve.model.Producto.MovimientoStock;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data 
@Entity
@NoArgsConstructor 
@AllArgsConstructor 
@EqualsAndHashCode(of = "id_empleado")
@Table(name = "empleado")
public class Empleado {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_empleado;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @Column(unique = true, nullable = false, length = 9)
    private String celular;

    @Column(unique = true, nullable = false)
    private String correo_empleado;

    @Column(unique = true, nullable = false, length = 8)
    private String dni;

    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private Boolean activo;

    @JsonIgnore
    @OneToOne(mappedBy = "id_empleado")
    private Usuario usuario;

    @OneToMany(mappedBy = "id_empleado")
    private List<MovimientoStock>  movimientos_stock;
}
