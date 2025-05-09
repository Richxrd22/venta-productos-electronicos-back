package com.neon.sve.model.Producto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.neon.sve.dto.empresa.DatosActualizarEmpresa;
import com.neon.sve.dto.empresa.DatosRegistroEmpresa;

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
@EqualsAndHashCode(of = "id_empresa")
@Table(name = "empresas")
public class Empresa {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id_empresa;
  @Column(nullable = false)
  private String nombre;
  @Column(unique = true, nullable = false)
  private String ruc;
  @Column(nullable = false)
  private String website;
  @Column(nullable = false, columnDefinition = "TINYINT(1)")
  private Boolean activo=true;


  @JsonIgnore
  @OneToMany(mappedBy = "id_empresa")
  private List<Proveedor> proveedores;

  public Empresa(@Valid DatosRegistroEmpresa datosRegistroEmpresa) {
    this.nombre = datosRegistroEmpresa.nombre();
    this.ruc = datosRegistroEmpresa.ruc();
    this.website = datosRegistroEmpresa.website();
  }

  public void actualizar(@Valid DatosActualizarEmpresa datosActualizarEmpresa) {
    this.nombre = datosActualizarEmpresa.nombre();
    this.ruc = datosActualizarEmpresa.ruc();
    this.website = datosActualizarEmpresa.website();
  }

}
