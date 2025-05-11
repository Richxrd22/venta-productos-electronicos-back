package com.neon.sve.model.Producto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.neon.sve.dto.proveedores.DatosActualizarProveedores;
import com.neon.sve.dto.proveedores.DatosRegistroProveedores;
import com.neon.sve.model.Ventas.IngresoStock;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "proveedores")
public class Proveedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,unique = true)
    private String razon_social;
    @Column(nullable = false)
    private String ruc;
    @Column(unique = true, nullable = false)
    private String correo;
    @Column(nullable = false)
    private String  direccion;
    @Column(unique = true, nullable = false, length = 9)
    private String celular;
    @Column(unique = true, nullable = false, length = 7)
    private String telefono;
    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private Boolean activo=true;
    
    @JsonIgnore
    @OneToMany(mappedBy = "id_proveedor")
    private List<IngresoStock> ingresoStocks;

    public Proveedor(@Valid DatosRegistroProveedores datosRegistroProveedores) {
        this.razon_social = datosRegistroProveedores.razon_social();
        this.ruc = datosRegistroProveedores.ruc();
        this.correo = datosRegistroProveedores.correo();
        this.direccion = datosRegistroProveedores.direccion();
        this.celular = datosRegistroProveedores.celular();
        this.telefono = datosRegistroProveedores.telefono();
    }
    
    public void actualizar(@Valid DatosActualizarProveedores datosActualizarProveedor) {
        this.razon_social = datosActualizarProveedor.razon_social();
        this.ruc = datosActualizarProveedor.ruc();
        this.correo = datosActualizarProveedor.correo();
        this.direccion = datosActualizarProveedor.direccion();
        this.celular = datosActualizarProveedor.celular();
        this.telefono = datosActualizarProveedor.telefono();
        this.activo = datosActualizarProveedor.activo();

    }

}
