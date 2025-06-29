package com.neon.sve.model.ventas;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import com.neon.sve.dto.devolucionVenta.DatosActualizarDevolucionVenta;
import com.neon.sve.dto.devolucionVenta.DatosRegistroDevolucionVenta;
import com.neon.sve.model.usuario.Usuario;
import com.neon.sve.model.ventas.Tipos.EstadoReclamo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
    @JoinColumn(name = "id_detalle_venta", nullable = false)
    private DetalleVenta detalleVenta;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Timestamp fecha;

    @Column(nullable = false)
    private int cantidad;

    @Column(columnDefinition = "TEXT")
    private String motivo;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private EstadoReclamo estado = EstadoReclamo.PENDIENTE;

    // Constructor para registro

    public DevolucionVenta(@Valid DatosRegistroDevolucionVenta datosRegistro,
            DetalleVenta detalleVenta, Usuario usuario) {
        this.detalleVenta = detalleVenta;
        this.cantidad = datosRegistro.cantidad();
        this.motivo = datosRegistro.motivo();
        this.usuario = usuario;
    }

    public void actualizar(@Valid DatosActualizarDevolucionVenta datosActualizarDevolucionVenta,
            DetalleVenta detalleVenta) {
        this.id = datosActualizarDevolucionVenta.id();
        this.detalleVenta = detalleVenta;
        this.cantidad = datosActualizarDevolucionVenta.cantidad();
        this.motivo = datosActualizarDevolucionVenta.motivo();
        this.estado = datosActualizarDevolucionVenta.estado();
    }

}

