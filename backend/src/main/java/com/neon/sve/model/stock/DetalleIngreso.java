package com.neon.sve.model.stock;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.neon.sve.model.producto.Producto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "detalle_ingresos")

public class DetalleIngreso {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
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
    private double precioUnitario;

    @Column(precision = 12, scale = 2)
    private double subtotal;

    @JsonIgnore
    @OneToMany(mappedBy = "id_detalle_ingreso", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SerieProducto> seriesProductos;

     // Constructor para el registro
    /*
    public DetalleIngreso(@Valid DatosRegistroDetalleIngreso datosRegistroDetalleIngreso, IngresoStock ingresoStock, Producto producto) {
        this.ingresoStock = ingresoStock;
        this.producto = producto;
        this.codigoLote = datosRegistroDetalleIngreso.codigoLote();
        this.cantidad = datosRegistroDetalleIngreso.cantidad();
        this.precioUnitario = datosRegistroDetalleIngreso.precioUnitario();
        // El subtotal se calcularía en el servicio o se dejaría que la BD lo genere
        this.subtotal = this.cantidad * this.precioUnitario; // Cálculo simple para el lado de la aplicación
    }*/

}
