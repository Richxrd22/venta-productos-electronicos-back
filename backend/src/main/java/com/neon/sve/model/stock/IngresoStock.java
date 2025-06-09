package com.neon.sve.model.stock;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import com.neon.sve.dto.ingresoStock.DatosActualizarIngresoStock;
import com.neon.sve.dto.ingresoStock.DatosRegistroIngresoStock;
import com.neon.sve.model.producto.Producto;
import com.neon.sve.model.producto.Proveedor;
import com.neon.sve.model.usuario.Usuario;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private Boolean activo;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario id_usuario;

    @OneToOne(mappedBy = "id_ingresoStock")
    private DetalleIngreso detallesIngreso;

    public IngresoStock(@Valid DatosRegistroIngresoStock datosRegistroIngresoStock, 
            Proveedor proveedor, Usuario usuario) {

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
