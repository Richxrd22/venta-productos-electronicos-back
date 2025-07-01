


CREATE TABLE cupones (
    id BIGINT AUTO_INCREMENT,
    codigo VARCHAR(50) NOT NULL UNIQUE,
    descripcion VARCHAR(255),
    tipo_descuento ENUM('PORCENTAJE', 'MONTO') NOT NULL,
    descuento_porcentaje DOUBLE CHECK (descuento_porcentaje >= 0),
    descuento_monto DOUBLE CHECK (descuento_monto >= 0),
    fecha_inicio DATE NOT NULL,
    fecha_fin DATE NOT NULL,
    max_usos INT NOT NULL CHECK (max_usos >= 1),
    usos_actuales INT NOT NULL DEFAULT 0 CHECK (usos_actuales >= 0),
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    activo BIT(1) DEFAULT 1,
    PRIMARY KEY (id)
);

CREATE TABLE clientes (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    dni VARCHAR(8) NOT NULL UNIQUE,
    celular VARCHAR(9) NOT NULL UNIQUE,
    correo VARCHAR(100) NOT NULL UNIQUE,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    activo BIT(1) NOT NULL DEFAULT 1,
    PRIMARY KEY (id)
);

CREATE TABLE metodo_pagos (
    id BIGINT NOT NULL AUTO_INCREMENT,
    metodo VARCHAR(100) NOT NULL UNIQUE,
    activo BIT(1) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE registro_ventas (
    id BIGINT NOT NULL AUTO_INCREMENT,
    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    igv_porcentaje DOUBLE NOT NULL CHECK (igv_porcentaje >= 0),
    subtotal DOUBLE NOT NULL CHECK (subtotal >= 0),
    igv_total DOUBLE NOT NULL CHECK (igv_total >= 0),
    descuento DOUBLE NULL CHECK (descuento >= 0),
    total DOUBLE NOT NULL CHECK (total >= 0),
    cancelado BIT(1) NOT NULL,
    activo BIT(1) NOT NULL DEFAULT 1,
    id_usuario BIGINT NULL,
    id_cliente BIGINT NOT NULL,
    id_metodo_pago BIGINT NOT NULL,
    id_cupon BIGINT DEFAULT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id),
    FOREIGN KEY (id_cliente) REFERENCES clientes(id),
    FOREIGN KEY (id_metodo_pago) REFERENCES metodo_pagos(id),
    FOREIGN KEY (id_cupon) REFERENCES cupones(id)
);


CREATE TABLE descuentos (
    id BIGINT AUTO_INCREMENT,                -- Identificador único del descuento
    id_categoria BIGINT NOT NULL,                         -- Referencia al producto al que se aplica el descuento
    porcentaje DECIMAL(5,2) NOT NULL,                -- Porcentaje de descuento (por ejemplo, 20.00 para un 20%)
    fecha_inicio DATE NOT NULL,                       -- Fecha de inicio del descuento
    fecha_fin DATE NOT NULL,                          -- Fecha de finalización del descuento
    activo BIT(1) NOT NULL DEFAULT 1,  
    PRIMARY KEY (id),                    -- Estado del descuento
    FOREIGN KEY (id_categoria) REFERENCES categorias(id) -- Relación con la tabla de productos
);

CREATE TABLE detalle_ventas (
    id BIGINT NOT NULL AUTO_INCREMENT,
    cantidad INT NOT NULL CHECK (cantidad > 0),
    precio_unitario DOUBLE NOT NULL CHECK (precio_unitario >= 0),
    total DOUBLE NOT NULL CHECK (total >= 0),
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    id_producto BIGINT NOT NULL,
    id_registro_venta BIGINT NOT NULL,
    activo BIT(1) NOT NULL DEFAULT 1,
    PRIMARY KEY (id),
    FOREIGN KEY (id_producto) REFERENCES productos(id),
    FOREIGN KEY (id_registro_venta) REFERENCES registro_ventas(id)
);

CREATE TABLE detalle_venta_series (
    id BIGINT NOT NULL AUTO_INCREMENT,
    id_detalle_venta BIGINT NOT NULL,
    id_serie_producto BIGINT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (id_detalle_venta) REFERENCES detalle_ventas(id),
    FOREIGN KEY (id_serie_producto) REFERENCES serie_productos(id)
);

CREATE TABLE garantias (
    id BIGINT NOT NULL AUTO_INCREMENT,
    id_detalle_venta BIGINT NOT NULL, -- Relación directa con producto vendido
    inicio_garantia DATE NOT NULL, -- Se define por la fecha de la venta
    fin_garantia DATE NOT NULL,    -- Se calcula según la duración en meses
    activo BIT(1) NOT NULL DEFAULT 1, -- Permite desactivar garantía si se revoca
    PRIMARY KEY (id),
    FOREIGN KEY (id_detalle_venta) REFERENCES detalle_ventas(id)
);

CREATE TABLE reclamo_garantias (
    id BIGINT NOT NULL AUTO_INCREMENT,
    id_garantia BIGINT NOT NULL,
    descripcion TEXT NOT NULL,
    estado ENUM('PENDIENTE', 'RESUELTO', 'RECHAZADO') NOT NULL DEFAULT 'PENDIENTE',
    activo BIT(1) NOT NULL DEFAULT 1,
    fecha_reclamo TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    FOREIGN KEY (id_garantia) REFERENCES garantias(id)
);

CREATE TABLE devoluciones_venta (
    id BIGINT AUTO_INCREMENT,
    id_detalle_venta BIGINT NULL,
    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    cantidad INT NULL CHECK (cantidad > 0),
    motivo TEXT,
    id_usuario BIGINT NOT NULL,
    estado ENUM('PENDIENTE', 'RESUELTO', 'RECHAZADO') NOT NULL DEFAULT 'PENDIENTE',
    PRIMARY KEY (id),
    FOREIGN KEY (id_detalle_venta) REFERENCES detalle_ventas(id),
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id)
);