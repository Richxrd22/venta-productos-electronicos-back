package com.neon.sve.model.Venta;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data 
@Entity
@NoArgsConstructor 
@AllArgsConstructor 
@EqualsAndHashCode(of = "id_cliente")
@Table(name = "cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Long id_cliente;

    @Column(nullable = false, length = 50)
    private String nombre;

    @Column(nullable = false, length = 50)
    private String apellido;

    @Column(nullable = false, unique = true, length = 8)
    private String dni;

    @Column(nullable = false, unique = true, length = 9)
    private String celular;

    @Column(nullable = false, unique = true, length = 100)
    private String correo_cliente;  

    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private Boolean activo; 

    @Column(name = "fecha_creacion", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime fecha_creacion;  

    @JsonIgnore
    @OneToMany(mappedBy = "id_cliente")
    private List<RegistroVenta> ventas;

}
