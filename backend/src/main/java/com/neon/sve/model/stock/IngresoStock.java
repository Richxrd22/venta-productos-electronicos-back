package com.neon.sve.model.stock;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.neon.sve.dto.ingresoStock.DatosActualizarIngresoStock;
import com.neon.sve.dto.ingresoStock.DatosRegistroIngresoStock;
import com.neon.sve.model.producto.Producto;
import com.neon.sve.model.producto.Proveedor;
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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "ingreso_stocks")
public class IngresoStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo_ingreso", nullable = false, unique = true, length = 30)
    private String codigo_ingreso;

    @ManyToOne
    @JoinColumn(name = "id_proveedor", nullable = false)
    private Proveedor id_proveedor;

    @Column(name = "fecha_ingreso", updatable = false)
    @CreationTimestamp
    private Timestamp fecha_ingreso;

    @Column(nullable = false, length = 20)
    private String tipo_documento;

    @Column(nullable = false, length = 50)
    private String numero_documento;

    @Column(nullable = true, columnDefinition = "TEXT")
    private String observaciones;

    @Column(name = "total", nullable = false, precision = 10, scale = 2)
    private BigDecimal total ;

    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private Boolean activo;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario id_usuario;

    @OneToMany(mappedBy = "ingresoStock", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleIngreso> detallesIngreso ;

    public IngresoStock(@Valid DatosRegistroIngresoStock datosRegistroIngresoStock,
            Proveedor proveedor, Usuario usuario, Producto producto) {

        this.id_proveedor = proveedor;
        this.tipo_documento = datosRegistroIngresoStock.tipo_documento();
        this.numero_documento = datosRegistroIngresoStock.numero_documento();
        this.observaciones = datosRegistroIngresoStock.observaciones();
        this.id_usuario = usuario;

    }

    public void actualizar(@Valid DatosActualizarIngresoStock datosActualizarIngresoStock,
            Proveedor proveedor, Usuario usuario) {
        this.id_proveedor = proveedor;
        this.tipo_documento = datosActualizarIngresoStock.tipo_documento();
        this.numero_documento = datosActualizarIngresoStock.numero_documento();
        this.observaciones = datosActualizarIngresoStock.observaciones();
        this.id_usuario = usuario;
    }

}
