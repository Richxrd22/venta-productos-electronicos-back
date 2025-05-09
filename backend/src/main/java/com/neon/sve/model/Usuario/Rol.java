package com.neon.sve.model.Usuario;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.neon.sve.dto.rol.DatosActualizarRol;
import com.neon.sve.dto.rol.DatosRegistroRol;

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
@EqualsAndHashCode(of = "id_rol")
@Table(name = "roles")
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_rol;
    @Column(unique = true, nullable = false, name = "nombre")
    private String nombre;

    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private Boolean activo=true;


    @JsonIgnore
    @OneToMany(mappedBy = "id_rol")
    private List<Usuario> usuarios;


    public Rol(@Valid DatosRegistroRol datosRegistroRol) {
        this.nombre = datosRegistroRol.nombre();
    }

    public void actualizar(@Valid DatosActualizarRol datosActualizarRol) {
        this.nombre = datosActualizarRol.nombre();
    }
}
