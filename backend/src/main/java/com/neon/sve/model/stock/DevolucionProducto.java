package com.neon.sve.model.stock;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import com.neon.sve.model.usuario.Usuario; // Necesitamos la clase Usuario

import jakarta.persistence.Column;
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
@Table(name = "devolucion_productos")

public class DevolucionProducto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_serie_producto", nullable = true)
    private SerieProducto id_serie_producto;

    @ManyToOne
    @JoinColumn(name = "id_detalle_ingreso", nullable = true)
    private DetalleIngreso id_detalle_ingreso;

    @Column(nullable = false)
    private int cantidad;

    @Column(name = "fecha_devolucion", updatable = false)
    @CreationTimestamp
    private Timestamp fechaDevolucion;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String motivo;

    @Column(columnDefinition = "TEXT")
    private String observaciones;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario id_usuario;

    @Column(name = "reposicion_aplicada", nullable = false)
    private Boolean reposicionAplicada = false;

    // Constructor para el registro
    /*
    public DevolucionProducto(@Valid DatosRegistroDevolucionProducto datosRegistro, Usuario usuario, SerieProducto serieProducto, DetalleIngreso detalleIngreso) {
        if ((serieProducto != null && detalleIngreso != null) || (serieProducto == null && detalleIngreso == null)) {
            throw new IllegalArgumentException("Debe especificar una SerieProducto o un DetalleIngreso, pero no ambos.");
        }
        this.serieProducto = serieProducto;
        this.detalleIngreso = detalleIngreso;
        this.cantidad = datosRegistro.cantidad();
        this.motivo = datosRegistro.motivo();
        this.observaciones = datosRegistro.observaciones();
        this.usuario = usuario;
        this.reposicionAplicada = datosRegistro.reposicionAplicada();
    }
    */

}
