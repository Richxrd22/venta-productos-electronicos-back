package com.neon.sve.model.stock;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore; // Importa JsonIgnore
import com.neon.sve.model.ventas.DetalleVentaSeries;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "serie_productos")
public class SerieProducto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_detalle_ingreso", nullable = false)
    private DetalleIngreso id_detalle_ingreso; // Relación con DetalleIngreso

    @Column(name = "numero_serie", nullable = false, unique = true, length = 50)
    private String numeroSerie;

    @Enumerated(EnumType.STRING) // Mapea el Enum a un String en la BD
    @Column(nullable = false, length = 20)
    private EstadoSerie estado = EstadoSerie.ACTIVO; // Valor por defecto

    @Column(name = "fecha_registro", updatable = false)
    @CreationTimestamp // Genera la fecha al crear el registro
    private Timestamp fechaRegistro;

    // Relación con DetalleVentaSerie: Una SerieProducto puede estar en varios
    // DetalleVentaSerie
    @JsonIgnore
    @OneToMany(mappedBy = "id_serie_producto")
    private List<DetalleVentaSeries> detalleVentaSeries;

    // Relación con DevolucionProducto: Una SerieProducto puede ser devuelta
    @JsonIgnore
    @OneToMany(mappedBy = "id_serie_producto")
    private List<DevolucionProducto> devolucionesProducto;

    // Constructor para el registro
    /*
     * public SerieProducto(@Valid DatosRegistroSerieProducto
     * datosRegistroSerieProducto, DetalleIngreso detalleIngreso) {
     * this.detalleIngreso = detalleIngreso;
     * this.numeroSerie = datosRegistroSerieProducto.numeroSerie();
     * // El estado por defecto ya se establece arriba
     * }
     */

}
