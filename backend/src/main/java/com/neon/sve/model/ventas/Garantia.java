package com.neon.sve.model.ventas;

import java.time.LocalDate; // Para las fechas de inicio y fin

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.neon.sve.dto.garantia.DatosActualizarGarantia;
import com.neon.sve.dto.garantia.DatosRegistroGarantia;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
@Table(name = "garantias")

public class Garantia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "id_detalle_venta", nullable = false)
    private DetalleVenta id_detalle_venta;

    @Column(name = "inicio_garantia", nullable = false)
    private LocalDate inicioGarantia;

    @Column(name = "fin_garantia", nullable = false)
    private LocalDate finGarantia;

    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private Boolean activo = true;

    @JsonIgnore
    @OneToMany(mappedBy = "id_garantia")
    private List<ReclamoGarantia> reclamosGarantia;

    public Garantia(@Valid DatosRegistroGarantia datosRegistro, DetalleVenta detalleVenta) {
        this.id_detalle_venta = detalleVenta;
        this.inicioGarantia = datosRegistro.inicio_garantia();
        this.finGarantia = datosRegistro.fin_garantia();
    }

    public void actualizar(@Valid DatosActualizarGarantia datosActualizarGarantia, DetalleVenta detalleVenta) {
        this.id = datosActualizarGarantia.id_garantia();
        this.id_detalle_venta = detalleVenta;
        this.inicioGarantia = datosActualizarGarantia.inicio_garantia();
        this.finGarantia = datosActualizarGarantia.fin_garantia();
    }
}
