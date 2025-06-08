package com.neon.sve.model.ventas;

import com.neon.sve.model.stock.SerieProducto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
@Table(name = "detalle_venta_series")

public class DetalleVentaSeries {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_detalle_venta", nullable = false)
    private DetalleVenta id_detalle_venta;

    @ManyToOne
    @JoinColumn(name = "id_serie_producto", nullable = false)
    private SerieProducto id_serie_producto;

    /*
     * public DetalleVentaSerie(@Valid DatosRegistroDetalleVentaSerie datosRegistro,
     * DetalleVenta detalleVenta, SerieProducto serieProducto) {
     * this.detalleVenta = detalleVenta;
     * this.serieProducto = serieProducto;
     * }
     */

}
