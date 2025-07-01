package com.neon.sve.model.ventas;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.neon.sve.model.usuario.Usuario;
import com.neon.sve.model.ventas.Tipos.EstadoReclamo;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
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

    @Deprecated // Marcamos como obsoleto para no usarlo en código nuevo
    @ManyToOne
    @JoinColumn(name = "id_detalle_venta", nullable = true) // Hacemos que la columna sea opcional
    private DetalleVenta detalleVenta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_registro_venta", nullable = false)
    private RegistroVenta venta;

    @OneToMany(mappedBy = "devolucionVenta", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<DetalleDevolucion> detallesDevolucion = new ArrayList<>();

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Timestamp fecha;

    @Column(nullable = true)
    private int cantidad;

    @Column(columnDefinition = "TEXT")
    private String motivo;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private EstadoReclamo estado = EstadoReclamo.PENDIENTE;

}
