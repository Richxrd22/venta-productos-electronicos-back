package com.neon.sve.model.usuario;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.neon.sve.dto.usuario.DatosActualizarUsuario;
import com.neon.sve.dto.usuario.DatosRegistroUsuario;
import com.neon.sve.dto.usuarioEmpleado.DatosActualizarUsuarioEmpleado;
import com.neon.sve.model.producto.Producto;
import com.neon.sve.model.stock.DevolucionProducto;
import com.neon.sve.model.stock.IngresoStock;
import com.neon.sve.model.ventas.DevolucionVenta;
import com.neon.sve.model.ventas.RegistroVenta;

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
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Builder
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "usuarios")
public class Usuario implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String correo;

    @Column(nullable = false)
    private String clave;

    @Column(nullable = false, columnDefinition = "TINYINT(1) DEFAULT 0")
    private Boolean clave_cambiada;

    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private Boolean activo;

    @Column(nullable = false)
    private int intentosFallidos;

    @Column(nullable = false, columnDefinition = "TINYINT(1) DEFAULT 0")
    private boolean cuentaBloqueada;

    private LocalDateTime fechaBloqueo;

    @ManyToOne
    @JoinColumn(name = "id_rol")
    private Rol id_rol;

    @OneToOne
    @JoinColumn(name = "id_empleado", unique = true, nullable = false)
    private Empleado id_empleado;

    @JsonIgnore
    @OneToMany(mappedBy = "id_usuario")
    private List<IngresoStock> ingresoStocks;

    @JsonIgnore
    @OneToMany(mappedBy = "usuario")
    private List<DevolucionVenta> devolucionVentas;

    @JsonIgnore
    @OneToMany(mappedBy = "id_usuario")
    private List<RegistroVenta> registroVentas;

    @OneToMany(mappedBy = "id_usuario")
    private List<Producto> productos;

    @OneToMany(mappedBy = "id_usuario")
    private List<DevolucionProducto> devolucionProductos;

    public Usuario(@Valid DatosRegistroUsuario datosRegistroUsuario,Empleado empleado, Rol rol ) {
        this.correo = datosRegistroUsuario.correo();
        this.clave = datosRegistroUsuario.clave();
        this.id_rol = rol;
        this.id_empleado = empleado;
        this.clave_cambiada = datosRegistroUsuario.clave_cambiada();
    }

    public void actualizar(@Valid DatosActualizarUsuario datosActualizarUsuario,Empleado empleado,Rol rol) {
        this.clave = datosActualizarUsuario.clave();
        this.correo = datosActualizarUsuario.correo();
        this.id_rol = rol;
        this.id_empleado = empleado;
        this.clave_cambiada = datosActualizarUsuario.clave_cambiada();
    }

    public void actualizar(@Valid DatosActualizarUsuarioEmpleado datosActualizarUsuarioEmpleado,Empleado empleado,Rol rol) {
        this.correo = datosActualizarUsuarioEmpleado.correo();
        this.id_rol = rol;
        this.id_empleado = empleado;
    }

    @Override
    public String getPassword() {
        return clave;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Asigna los roles de la entidad Usuario
        return List.of(new SimpleGrantedAuthority(id_rol.getNombre()));
    }
    
    @Override
    public String getUsername() {
        // En este caso, se utilizaría el correo como el nombre de usuario
        return correo;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Puedes ajustar la lógica según las necesidades de tu aplicación
    }
    

    @Override
    public boolean isAccountNonLocked() {
        return true; // Ajustar según las necesidades
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Ajustar según las necesidades
    }

    @Override
    public boolean isEnabled() {
        return true; // Ajustar según las necesidades, por ejemplo, si el usuario está activo o no
    }
}
