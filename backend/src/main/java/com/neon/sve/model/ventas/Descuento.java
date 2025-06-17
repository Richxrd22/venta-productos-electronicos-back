package com.neon.sve.model.ventas;

import com.neon.sve.dto.descuento.DatosActualizarDescuento;
import com.neon.sve.dto.descuento.DatosRegistroDescuento;
import com.neon.sve.model.producto.Categoria;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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

import java.time.LocalDate;


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
    private Categoria categoria;

    @Column(nullable = false)
    private Double porcentaje; // Porcentaje de descuento

    @Column(nullable = false)
    private LocalDate fecha_inicio; // Fecha de inicio del descuento

    @Column(nullable = false)
    private LocalDate fechaFin; // Fecha de finalización del descuento

    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private Boolean activo = true; // Estado del descuento

    public Descuento(@Valid DatosRegistroDescuento datosRegistroDescuento, Categoria categoria) {
        this.categoria = categoria;
        this.porcentaje = datosRegistroDescuento.porcentaje();
        this.fecha_inicio = datosRegistroDescuento.fecha_inicio();
        this.fechaFin = datosRegistroDescuento.fecha_fin();
    }

    public void actualizar(@Valid DatosActualizarDescuento datosActualizarDescuento, Categoria categoria) {
        this.categoria = categoria;
        this.porcentaje = datosActualizarDescuento.porcentaje();
        this.fecha_inicio = datosActualizarDescuento.fecha_inicio();
        this.fechaFin = datosActualizarDescuento.fecha_fin();
    }
}
