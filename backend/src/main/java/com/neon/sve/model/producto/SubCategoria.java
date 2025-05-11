package com.neon.sve.model.producto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.neon.sve.dto.subCategoria.DatosActualizarSubCategoria;
import com.neon.sve.dto.subCategoria.DatosRegistroSubCategoria;

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
@EqualsAndHashCode(of = "id")
@Table(name = "subcategorias")
public class SubCategoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String nombre;

    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private Boolean activo = true;

    @ManyToOne
    @JoinColumn(name = "id_categoria", nullable = false)
    private Categoria id_categoria;

    @JsonIgnore
    @OneToMany(mappedBy = "id_subcategoria")
    private List<Producto> productos;

    public SubCategoria(@Valid DatosRegistroSubCategoria datosRegistroSubCategoria, Categoria categoria) {
        this.nombre = datosRegistroSubCategoria.nombre();
        this.id_categoria = categoria;
    }

    public void actualizar(@Valid DatosActualizarSubCategoria datosActualizarSubCategoria, Categoria categoria) {
        this.nombre = datosActualizarSubCategoria.nombre();
        this.activo = datosActualizarSubCategoria.activo();
        this.id_categoria = categoria;
    }

}
