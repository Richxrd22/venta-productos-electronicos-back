package com.neon.sve.model.ventas;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.neon.sve.model.usuario.Usuario;

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
@Table(name = "registro_ventas")

public class RegistroVenta {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Timestamp fecha;

    @Column(name = "igv_porcentaje", nullable = false)
    private double igvPorcentaje;

    @Column(nullable = false)
    private double subtotal;

    @Column(name = "igv_total", nullable = false)
    private double igvTotal;

    @Column(nullable = true) // Puede ser nulo si no hay descuento
    private Double descuento;

    @Column(nullable = false)
    private double total;

    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private Boolean cancelado;

    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private Boolean activo = true;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario id_usuario; // Relación con Usuario

    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente id_cliente; // Relación con Cliente

    @ManyToOne
    @JoinColumn(name = "id_metodo_pago", nullable = false)
    private MetodoPago id_metodo_pago; // Relación con MetodoPago

    @ManyToOne
    @JoinColumn(name = "id_cupon", nullable = true) // Puede ser nulo
    private Cupon id_cupon; // Relación con Cupon

    @JsonIgnore
    @OneToMany(mappedBy = "id_registro_venta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleVenta> detallesVenta;

    // Constructor para el registro
    /*
    public RegistroVenta(@Valid DatosRegistroVenta datosRegistroVenta, Usuario usuario, Cliente cliente, MetodoPago metodoPago, Cupon cupon) {
        this.igvPorcentaje = datosRegistroVenta.igvPorcentaje();
        this.subtotal = datosRegistroVenta.subtotal();
        this.igvTotal = datosRegistroVenta.igvTotal();
        this.descuento = datosRegistroVenta.descuento();
        this.total = datosRegistroVenta.total();
        this.cancelado = datosRegistroVenta.cancelado();
        this.usuario = usuario;
        this.cliente = cliente;
        this.metodoPago = metodoPago;
        this.cupon = cupon;
        this.activo = true; // Por defecto activo
    }*/

}
