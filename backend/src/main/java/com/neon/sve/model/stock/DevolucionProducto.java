package com.neon.sve.model.stock;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import com.neon.sve.dto.devolucionProducto.DatosActualizarDevolucionProducto;
import com.neon.sve.dto.devolucionProducto.DatosRegistroDevolucionProducto;
import com.neon.sve.model.usuario.Usuario; // Necesitamos la clase Usuario

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

    
    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private Boolean activo=true;

    public DevolucionProducto(@Valid DatosRegistroDevolucionProducto datos, Usuario usuario,
            DetalleIngreso detalleIngreso) {
        this(null, datos, usuario, detalleIngreso);
    }

    public DevolucionProducto(SerieProducto serie, @Valid DatosRegistroDevolucionProducto datos, Usuario usuario,
            DetalleIngreso detalleIngreso) {
        this.id_serie_producto = serie;
        this.id_detalle_ingreso = detalleIngreso;
        this.cantidad = (serie != null) ? 1 : (datos.cantidad() != null ? datos.cantidad() : 0);
        this.motivo = datos.motivo().trim();
        this.observaciones = datos.observaciones();
        this.id_usuario = usuario;
        this.reposicionAplicada = Boolean.TRUE.equals(datos.reposicion_aplicada());
    }

    public void actualizar(@Valid DatosActualizarDevolucionProducto datos, Usuario usuario) {
        this.cantidad = datos.cantidad();
        this.motivo = datos.motivo();
        this.observaciones = datos.observaciones();
        this.id_usuario = usuario;
        this.reposicionAplicada = datos.reposicion_aplicada();
    }
}
