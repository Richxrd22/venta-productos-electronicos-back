package com.neon.sve.model.ventas;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
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
@Table(name = "metodo_pagos")

public class MetodoPago {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String metodo;

    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private Boolean activo=true;

    @JsonIgnore
    @OneToMany(mappedBy = "id_metodo_pago")
    private List<RegistroVenta> registroVentas;

    // Constructor para registro
    /*
    public MetodoPago(@Valid DatosRegistroMetodoPago datosRegistroMetodoPago) {
        this.metodo = datosRegistroMetodoPago.metodo();
    }*/

}
