package com.neon.sve.model.ventas;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import com.neon.sve.model.usuario.Usuario;

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
@Table(name = "devoluciones_venta")

public class DevolucionVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_detalle", nullable = false)
    private DetalleVenta id_detalle_venta;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Timestamp fecha;

    @Column(nullable = false)
    private int cantidad;

    @Column(columnDefinition = "TEXT")
    private String motivo;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario id_usuario;

    // Constructor para registro
    /*
     * public DevolucionVenta(@Valid DatosRegistroDevolucionVenta datosRegistro,
     * DetalleVenta detalleVenta, Usuario usuario) {
     * this.detalleVenta = detalleVenta;
     * this.cantidad = datosRegistro.cantidad();
     * this.motivo = datosRegistro.motivo();
     * this.usuario = usuario;
     * }
     */

}
