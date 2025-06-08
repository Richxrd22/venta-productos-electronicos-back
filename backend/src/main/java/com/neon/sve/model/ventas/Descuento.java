package com.neon.sve.model.ventas;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.neon.sve.dto.descuento.DatosActualizarDescuento;
import com.neon.sve.dto.descuento.DatosRegistroDescuento;
import com.neon.sve.model.producto.Categoria;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "descuentos")
public class Descuento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_categoria", nullable = false)
    private Categoria id_categoria;

    @Column(nullable = false)
    private Double porcentaje; // Porcentaje de descuento

    @Column(nullable = false)
    private LocalDate fecha_inicio; // Fecha de inicio del descuento

    @Column(nullable = false)
    private LocalDate fecha_fin; // Fecha de finalización del descuento

    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private Boolean activo = true; // Estado del descuento

    public Descuento(@Valid DatosRegistroDescuento datosRegistroDescuento, Categoria categoria) {
        this.id_categoria = categoria;
        this.porcentaje = datosRegistroDescuento.procentaje();
        this.fecha_inicio = datosRegistroDescuento.fecha_inicio();
        this.fecha_fin = datosRegistroDescuento.fecha_fin();
    }

    public void actualizar(@Valid DatosActualizarDescuento datosActualizarDescuento, Categoria categoria) {
        this.id_categoria = categoria;
        this.porcentaje = datosActualizarDescuento.procentaje();
        this.fecha_inicio = datosActualizarDescuento.fecha_inicio();
        this.fecha_fin = datosActualizarDescuento.fecha_fin();
    }
}
