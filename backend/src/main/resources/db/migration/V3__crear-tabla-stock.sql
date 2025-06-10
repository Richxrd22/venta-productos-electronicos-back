CREATE TABLE ingreso_stocks (
    id BIGINT NOT NULL AUTO_INCREMENT,
    id_proveedor BIGINT NOT NULL,
    fecha_ingreso DATE NOT NULL,
    tipo_documento VARCHAR(20),
    numero_documento VARCHAR(50),
    observaciones TEXT,
    PRIMARY KEY (id),
    FOREIGN KEY (id_proveedor) REFERENCES proveedores(id)
);

CREATE TABLE detalle_ingresos (
    id BIGINT NOT NULL AUTO_INCREMENT,
    id_ingreso BIGINT NOT NULL,
    id_producto BIGINT NOT NULL,
    codigo_lote VARCHAR(100) NOT NULL,
    cantidad INT NOT NULL CHECK (cantidad >= 0),
    precio_unitario DECIMAL(10, 2) NOT NULL,
    subtotal DECIMAL(12, 2) GENERATED ALWAYS AS (cantidad * precio_unitario) STORED,
    activo BIT(1) NOT NULL DEFAULT 1,
    PRIMARY KEY (id),
    FOREIGN KEY (id_ingreso) REFERENCES ingreso_stocks(id),
    FOREIGN KEY (id_producto) REFERENCES productos(id)
);

CREATE TABLE serie_productos (
    id BIGINT NOT NULL AUTO_INCREMENT,
    id_detalle_ingreso BIGINT NOT NULL,
    numero_serie VARCHAR(50) NOT NULL UNIQUE,
    estado ENUM('ACTIVO', 'VENDIDO', 'INACTIVO', 'DEVUELTO', 'REPARACION') DEFAULT 'ACTIVO',
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    FOREIGN KEY (id_detalle_ingreso) REFERENCES detalle_ingresos(id)
);


CREATE TABLE devolucion_productos (
    id BIGINT NOT NULL AUTO_INCREMENT,
    -- Una de estas dos columnas debe tener valor, pero no ambas:
    id_serie_producto BIGINT DEFAULT NULL,  -- Devolución por serie
    id_detalle_ingreso BIGINT NOT NULL, -- Devolución por lote (sin serie)
    
    cantidad INT NOT NULL CHECK (cantidad > 0),
    fecha_devolucion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    motivo TEXT NOT NULL,
    observaciones TEXT,
    id_usuario BIGINT NOT NULL,
    reposicion_aplicada BOOLEAN DEFAULT FALSE,
    activo BIT(1) NOT NULL DEFAULT 1,
    PRIMARY KEY (id),
    FOREIGN KEY (id_serie_producto) REFERENCES serie_productos(id),
    FOREIGN KEY (id_detalle_ingreso) REFERENCES detalle_ingresos(id),
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id)
);

