package com.neon.sve.model.ventas;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import com.neon.sve.dto.reclamoGarantia.DatosActualizarReclamoGarantia;
import com.neon.sve.dto.reclamoGarantia.DatosRegistroReclamoGarantia;
import com.neon.sve.model.ventas.Tipos.EstadoReclamo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "reclamo_garantias")

public class ReclamoGarantia {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_garantia", nullable = false)
    private Garantia id_garantia;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String descripcion;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private EstadoReclamo estado = EstadoReclamo.PENDIENTE;

    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private Boolean activo = true;

    @Column(name = "fecha_reclamo", updatable = false)
    @CreationTimestamp
    private Timestamp fechaReclamo;

    public ReclamoGarantia(@Valid DatosRegistroReclamoGarantia datosRegistro, Garantia garantia) {
        this.id_garantia = garantia;
        this.descripcion = datosRegistro.descripcion();
    }

    public void actualizar(@Valid DatosActualizarReclamoGarantia datosActualizarReclamo, Garantia garantia) {
        this.id = datosActualizarReclamo.id_reclamo_garantia();
        this.id_garantia = garantia;
        this.descripcion = datosActualizarReclamo.descripcion();
        this.estado = datosActualizarReclamo.estado();
    }

}
