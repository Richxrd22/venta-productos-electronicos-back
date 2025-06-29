package com.neon.sve.model.ventas;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.neon.sve.dto.detalleVenta.DatosRegistroDetalleVenta;
import com.neon.sve.model.producto.Producto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "detalle_ventas")

public class DetalleVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int cantidad;

    @Column(name = "precio_unitario", nullable = false, precision = 10, scale = 2)
    private BigDecimal precio_unitario;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal total;

    @Column(name = "fecha_creacion", updatable = false)
    @CreationTimestamp
    private Timestamp fecha_creacion;

    @ManyToOne
    @JoinColumn(name = "id_producto", nullable = false)
    private Producto id_producto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_registro_venta", nullable = false)
    private RegistroVenta id_registro_venta;

    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private Boolean activo = true;

    @JsonIgnore
    @OneToMany(mappedBy = "id_detalle_venta")
    private List<DetalleVentaSeries> detalleVentaSeries;

    @JsonIgnore
    @OneToOne(mappedBy = "id_detalle_venta")
    private Garantia garantia;

    @JsonIgnore
    @OneToMany(mappedBy = "detalleVenta", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<DevolucionVenta> devolucionesVenta;

    public DetalleVenta(@Valid DatosRegistroDetalleVenta datosRegistro,
            RegistroVenta registroVenta, Producto producto) {
        this.cantidad = datosRegistro.cantidad();
        this.precio_unitario = datosRegistro.precio_unitario();
        this.total = datosRegistro.total(); // Se calcularía en el servicio
        this.id_producto = producto;
        this.id_registro_venta = registroVenta;
    }

}
