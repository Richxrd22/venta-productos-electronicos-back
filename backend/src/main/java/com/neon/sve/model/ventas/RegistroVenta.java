package com.neon.sve.model.ventas;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.neon.sve.dto.registroVenta.DatosActualizarRegistroVenta;
import com.neon.sve.dto.registroVenta.DatosRegistroVenta;
import com.neon.sve.model.usuario.Usuario;

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
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
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

    @Column(name = "igv_porcentaje", precision = 5, scale = 2)
    private BigDecimal igv_porcentaje;

    @Column(precision = 10, scale = 2)
    private BigDecimal subtotal;

    @Column(name = "igv_total", precision = 10, scale = 2)
    private BigDecimal igv_total;

    @Column(nullable = true, precision = 10, scale = 2) // Puede ser nulo si no hay descuento
    private BigDecimal descuento;

    @Column(precision = 10, scale = 2)
    private BigDecimal total;

    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private Boolean cancelado;

    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private Boolean activo = true;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = true)
    private Usuario id_usuario; // Relación con Usuario

    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente id_cliente; // Relación con Cliente

    @ManyToOne
    @JoinColumn(name = "id_metodo_pago", nullable = false)
    private MetodoPago id_metodo_pago; // Relación con MetodoPago

    @ManyToOne
    @JoinColumn(name = "id_cupon") // Puede ser nulo
    private Cupon id_cupon; // Relación con Cupon

    @OneToMany(mappedBy = "id_registro_venta", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<DetalleVenta> detallesVenta;

    public RegistroVenta(@Valid DatosRegistroVenta datosRegistroVenta, Usuario usuario, Cliente cliente,
            MetodoPago metodoPago, Cupon cupon) {
        this.igv_porcentaje = datosRegistroVenta.igv_porcentaje();
        this.subtotal = datosRegistroVenta.subtotal();
        this.igv_total = datosRegistroVenta.igv_total();
        this.descuento = datosRegistroVenta.descuento();
        this.total = datosRegistroVenta.total();
        this.cancelado = datosRegistroVenta.cancelado();
        this.id_usuario = usuario;
        this.id_cliente = cliente;
        this.id_metodo_pago = metodoPago;
        this.id_cupon = cupon;
    }

    public void actualizar(@Valid DatosActualizarRegistroVenta datosActualizarRegistroVenta, Usuario usuario,
            Cliente cliente,
            MetodoPago metodoPago, Cupon cupon) {
        this.igv_porcentaje = datosActualizarRegistroVenta.igv_porcentaje();
        this.subtotal = datosActualizarRegistroVenta.subtotal();
        this.igv_total = datosActualizarRegistroVenta.igv_total();
        this.descuento = datosActualizarRegistroVenta.descuento();
        this.total = datosActualizarRegistroVenta.total();
        this.cancelado = datosActualizarRegistroVenta.cancelado();
        this.id_usuario = usuario;
        this.id_cliente = cliente;
        this.id_metodo_pago = metodoPago;
        this.id_cupon = cupon;
    }

    public RegistroVenta(BigDecimal subtotal2, double doubleValue, double doubleValue2, BigDecimal total2,
            Boolean cancelado2, Usuario usuario, Cliente cliente, MetodoPago metodoPago, Cupon cupon) {
        //TODO Auto-generated constructor stub
    }

}
