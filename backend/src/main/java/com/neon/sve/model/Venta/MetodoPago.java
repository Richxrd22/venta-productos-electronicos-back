package com.neon.sve.model.Venta;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data 
@Entity
@NoArgsConstructor 
@AllArgsConstructor 
@EqualsAndHashCode(of = "id_metodo_pago")
@Table(name = "metodo_pago")
public class MetodoPago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_metodo_pago")
    private Long id_metodo_pago;

    @Column(nullable = false, unique = true)
    private String metodo;

    @JsonIgnore
    @OneToMany(mappedBy = "id_metodo_pago")
    private List<RegistroVenta> ventas; 
    
}
