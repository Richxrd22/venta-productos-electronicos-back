

CREATE TABLE salida_stocks (
    id_salida BIGINT NOT NULL AUTO_INCREMENT,
    id_producto BIGINT NOT NULL,
    cantidad INT NOT NULL CHECK (cantidad > 0),
    motivo VARCHAR(100) NOT NULL, -- ejemplo: 'venta', 'dañado', 'donación', etc.
    observaciones TEXT,
    fecha_salida TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    id_usuario BIGINT NOT NULL,
    activo BIT(1) NOT NULL DEFAULT 1,
    PRIMARY KEY (id_salida),
    FOREIGN KEY (id_producto) REFERENCES productos(id_producto),
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario)
);


/*
CREATE TABLE productos_danados (
    id_danado BIGINT NOT NULL AUTO_INCREMENT,
    id_producto BIGINT NOT NULL,
    id_ingreso BIGINT NOT NULL, -- referencia al lote ingresado
    cantidad INT NOT NULL CHECK (cantidad > 0),
    descripcion TEXT NOT NULL,
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    id_empleado BIGINT NOT NULL,
    PRIMARY KEY (id_danado),
    FOREIGN KEY (id_producto) REFERENCES productos(id_producto),
    FOREIGN KEY (id_ingreso) REFERENCES ingreso_stocks(id_ingreso),
    FOREIGN KEY (id_empleado) REFERENCES empleados(id_empleado)
);

*/