package com.neon.sve.model.producto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.neon.sve.model.ventas.Descuento;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
@EqualsAndHashCode(of = "id")
@Table(name = "categorias")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String nombre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_categoria_padre")
    private Categoria categoriaPadre;

    @OneToMany(mappedBy = "categoriaPadre", fetch = FetchType.EAGER)
    private List<Categoria> subcategorias = new ArrayList<>();

    @Column(nullable = false)
    private int nivel;

    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private Boolean activo = true;

    @JsonIgnore
    @OneToMany(mappedBy = "id_categoria")
    private List<Producto> productos;

    @JsonIgnore
    @OneToMany(mappedBy = "categoria")
    private List<Descuento> descuentos;

}
