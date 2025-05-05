package com.neon.sve.model.Producto;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.neon.sve.dto.Proveedores.DatosActualizarProveedores;
import com.neon.sve.dto.Proveedores.DatosRegistroProveedores;
import com.neon.sve.dto.empleado.DatosActualizarEmpleado;
import com.neon.sve.dto.empleado.DatosRegistroEmpleado;

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

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id_proveedor")
@Table(name = "proveedor")
public class Proveedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_proveedor;
    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    private String apellido;
    @Column(unique = true, nullable = false)
    private String correo;
    @Column(nullable = false, unique = true, length = 8)
    private String dni;
    @Column(unique = true, nullable = false, length = 9)
    private String telefono;
    @Column(unique = true, nullable = false, length = 9)
    private String celular;
    @Column
    private Boolean activo;

    @ManyToOne
    @JoinColumn(name = "id_empresa", nullable = false)
    private Empresa id_empresa;

    @JsonIgnore
    @OneToMany(mappedBy = "id_proveedor")
    private List<Producto> productos;
    

     public Proveedor(@Valid DatosRegistroProveedores datosRegistroProveedores, Empresa empresa) {
        this.nombre = datosRegistroProveedores.nombre();
        this.apellido = datosRegistroProveedores.apellido();
        this.correo = datosRegistroProveedores.correo();
        this.dni = datosRegistroProveedores.dni();
        this.celular = datosRegistroProveedores.celular();
        this.activo = datosRegistroProveedores.activo();
        this.id_empresa = empresa;
        this.telefono = datosRegistroProveedores.telefono();

    }
    
    public void actualizar(@Valid DatosActualizarProveedores datosActualizarProveedor, Empresa empresa) {
        
        this.nombre = datosActualizarProveedor.nombre();
        this.apellido = datosActualizarProveedor.apellido();
        this.correo = datosActualizarProveedor.correo();
        this.dni = datosActualizarProveedor.dni();
        this.celular = datosActualizarProveedor.celular();
        this.activo = datosActualizarProveedor.activo();
        this.id_empresa = empresa;
        this.telefono = datosActualizarProveedor.telefono();

    }

}
