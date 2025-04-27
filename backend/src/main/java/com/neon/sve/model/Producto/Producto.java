package com.neon.sve.model.Producto;
import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import com.neon.sve.model.Usuario.Empleado;

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
@EqualsAndHashCode(of = "id_producto")
@Table(name = "producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Long id_producto;

    @Column(nullable = false, unique = true, length = 50)
    private String nombre;

    @Column(nullable = false, length = 250)
    private String descripcion;

    @Column(nullable = false, unique = true, length = 50)
    private String sku;

    @Column(nullable = false)
    private double precio;

    @Column(nullable = false)
    private int min_stock;

    @Column(nullable = false)
    private int max_stock;

    @Column(nullable = false)
    private int stock;

    @Column(nullable = false)
    private int garantia_meses;

    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private Boolean activo;

    @Column(name = "fecha_creacion", updatable = false)
    @CreationTimestamp
    private Timestamp fecha_creacion;

    @ManyToOne
    @JoinColumn(name = "id_empleado", nullable = false)
    private Empleado id_empleado;

    @ManyToOne
    @JoinColumn(name = "id_categoria", nullable = false)
    private Categoria id_categoria;

    @ManyToOne
    @JoinColumn(name = "id_marca", nullable = false)
    private Marca id_marca;

    @ManyToOne
    @JoinColumn(name = "id_proveedor", nullable = false)
    private Proveedor id_proveedor;


}
