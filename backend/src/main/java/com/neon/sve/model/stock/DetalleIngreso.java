package com.neon.sve.model.stock;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.neon.sve.dto.detalleIngreso.DatosRegistroDetalleIngreso;
import com.neon.sve.model.producto.Producto;
import com.neon.sve.model.usuario.Empleado;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "detalle_ingresos")

public class DetalleIngreso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "id_ingreso", nullable = false)
    private IngresoStock id_ingresoStock; // Relación con la entidad IngresoStock

    @ManyToOne
    @JoinColumn(name = "id_producto", nullable = false)
    private Producto id_producto; // Relación con la entidad Producto

    @Column(name = "codigo_lote", nullable = false, length = 100)
    private String codigoLote;

    @Column(nullable = false)
    private int cantidad;

    @Column(name = "precio_unitario", nullable = false, precision = 10, scale = 2)
    private BigDecimal precio_unitario;

    @Column(name = "subtotal", insertable = false, updatable = false, nullable = false, precision = 12, scale = 2)
    private BigDecimal subtotal;

    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private Boolean activo;

    @JsonIgnore
    @OneToMany(mappedBy = "id_detalle_ingreso", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SerieProducto> seriesProductos;

    public DetalleIngreso(@Valid DatosRegistroDetalleIngreso datosRegistroDetalleIngreso, IngresoStock ingresoStock,
            Producto producto) {
        this.id_ingresoStock = ingresoStock;
        this.id_producto = producto;
        this.cantidad = datosRegistroDetalleIngreso.cantidad();
        this.precio_unitario = datosRegistroDetalleIngreso.precio_unitario();
    }

}
