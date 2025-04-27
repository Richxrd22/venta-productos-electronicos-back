package com.neon.sve.model.Producto;


import java.security.Timestamp;


import com.neon.sve.dto.TipoMovimiento;
import com.neon.sve.model.Usuario.Empleado;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@EqualsAndHashCode(of = "id_movimiento")
@Table(name = "movimiento_stock")
public class MovimientoStock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_movimiento")
    private Long id_movimiento;

    @Column(name = "fecha_movimiento", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp fecha_movimiento;

    @Column(nullable = false)
    private int cantidad;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_movimiento", nullable = false)
    private TipoMovimiento tipo_movimiento;

    @ManyToOne
    @JoinColumn(name = "id_producto", nullable = false)
    private Producto id_producto;

    @ManyToOne
    @JoinColumn(name = "id_empleado", nullable = false)
    private Empleado id_empleado;
}
