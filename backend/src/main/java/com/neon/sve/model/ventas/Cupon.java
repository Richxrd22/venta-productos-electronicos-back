package com.neon.sve.model.ventas;

import java.sql.Timestamp;
import java.time.LocalDate; // Para fechas de inicio y fin

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.neon.sve.model.ventas.Tipos.TipoDescuentoCupon;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "cupones")

public class Cupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String codigo;

    @Column(length = 255)
    private String descripcion;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_descuento", nullable = false)
    private TipoDescuentoCupon tipoDescuentoCupon;

    @Column(name = "descuento_porcentaje")
    private Double descuentoPorcentaje;

    @Column(name = "descuento_monto")
    private Double descuentoMonto;

    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin", nullable = false)
    private LocalDate fechaFin;

    @Column(name = "max_usos", nullable = false)
    private int maxUsos;

    @Column(name = "usos_actuales", nullable = false)
    private int usosActuales = 0;

    @Column(name = "fecha_creacion", updatable = false)
    @CreationTimestamp
    private Timestamp fechaCreacion;

    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private Boolean activo = true;

    @JsonIgnore
    @OneToMany(mappedBy = "id_cupon")
    private List<RegistroVenta> registroVentas;

    // Constructor para el registro
    /*
     * public Cupon(@Valid DatosRegistroCupon datosRegistroCupon) {
     * this.codigo = datosRegistroCupon.codigo();
     * this.descripcion = datosRegistroCupon.descripcion();
     * this.tipoDescuento = datosRegistroCupon.tipoDescuento();
     * this.descuentoPorcentaje = datosRegistroCupon.descuentoPorcentaje();
     * this.descuentoMonto = datosRegistroCupon.descuentoMonto();
     * this.fechaInicio = datosRegistroCupon.fechaInicio();
     * this.fechaFin = datosRegistroCupon.fechaFin();
     * this.maxUsos = datosRegistroCupon.maxUsos();
     * }
     */

}
