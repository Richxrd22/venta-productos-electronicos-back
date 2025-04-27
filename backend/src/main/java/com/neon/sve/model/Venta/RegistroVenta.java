package com.neon.sve.model.Venta;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.neon.sve.model.Usuario.Empleado;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data 
@Entity
@NoArgsConstructor 
@AllArgsConstructor 
@EqualsAndHashCode(of = "id_registro_venta")
@Table(name = "registro_venta")
public class RegistroVenta {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_registro_venta")
    private Long id_registro_venta;

    @Column(nullable = false)
    private LocalDate fecha;

    @Column(name = "igv_porcentaje", nullable = false)
    private double igv_porcentaje;

    @Column(nullable = false)
    private double subtotal;

    @Column(name = "igv_total", nullable = false)
    private double igv_total;

    @Column(nullable = false)
    private double total;

    @ManyToOne
    @JoinColumn(name = "id_empleado", nullable = false)
    private Empleado id_empleado; 

    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente id_cliente;

    @ManyToOne
    @JoinColumn(name = "id_metodo_pago", nullable = false)
    private MetodoPago id_metodo_pago; 

    @JsonIgnore
    @OneToMany(mappedBy = "id_registro_venta")
    private List<DetalleVenta> detalle_ventas;
}
