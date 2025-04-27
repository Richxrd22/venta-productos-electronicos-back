CREATE TABLE movimiento_stock (
    id_movimiento BIGINT NOT NULL AUTO_INCREMENT,
    id_producto BIGINT NOT NULL,
    id_empleado BIGINT NOT NULL,
    fecha_movimiento TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    cantidad INT NOT NULL CHECK (cantidad > 0),
    tipo_movimiento ENUM('ENTRADA', 'SALIDA') NOT NULL,
    PRIMARY KEY (id_movimiento),
    FOREIGN KEY (id_producto) REFERENCES producto(id_producto),
    FOREIGN KEY (id_empleado) REFERENCES empleado(id_empleado)
);