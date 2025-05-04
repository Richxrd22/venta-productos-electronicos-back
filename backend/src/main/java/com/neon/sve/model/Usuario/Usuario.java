package com.neon.sve.model.Usuario;

import com.neon.sve.dto.usuario.DatosActualizarUsuario;
import com.neon.sve.dto.usuario.DatosRegistroUsuario;

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
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Builder
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id_usuario")
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_usuario;

    @Column(unique = true, nullable = false)
    private String correo_usuario;

    @Column(nullable = false)
    private String clave;

    @Column(nullable = false, columnDefinition = "TINYINT(1) DEFAULT 0")
    private Boolean clave_cambiada;

    @ManyToOne
    @JoinColumn(name = "id_rol")
    private Rol id_rol;

    @OneToOne
    @JoinColumn(name = "id_empleado", unique = true, nullable = false)
    private Empleado id_empleado;

    public Usuario(@Valid DatosRegistroUsuario datosRegistroUsuario,Empleado empleado, Rol rol ) {
        this.correo_usuario = datosRegistroUsuario.correo_usuario();
        this.clave = datosRegistroUsuario.clave();
        this.id_rol = rol;
        this.id_empleado = empleado;
        this.clave_cambiada = datosRegistroUsuario.clave_cambiada();
    }

    public void actualizar(@Valid DatosActualizarUsuario datosActualizarUsuario,Empleado empleado,Rol rol) {
        this.clave = datosActualizarUsuario.clave();
        this.correo_usuario = datosActualizarUsuario.correo_usuario();
        this.id_rol = rol;
        this.id_empleado = empleado;
        this.clave_cambiada = datosActualizarUsuario.clave_cambiada();
    }
}
