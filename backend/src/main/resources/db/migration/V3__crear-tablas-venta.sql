CREATE TABLE ingreso_stocks (
    id BIGINT NOT NULL AUTO_INCREMENT,
    id_producto BIGINT NOT NULL,
    id_proveedor BIGINT NOT NULL,
    id_usuario BIGINT NOT NULL,
    lote VARCHAR(50) NOT NULL, 
    cantidad INT NOT NULL CHECK (cantidad > 0),
    fecha_ingreso TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    activo BIT(1) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (id_producto) REFERENCES productos(id),
    FOREIGN KEY (id_proveedor) REFERENCES proveedores(id),
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id)
);

CREATE TABLE clientes (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    dni VARCHAR(8) NOT NULL UNIQUE,
    celular VARCHAR(9) NOT NULL UNIQUE,
    correo VARCHAR(100) NOT NULL UNIQUE,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    activo BIT(1) NOT NULL,
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
    fecha DATE NOT NULL,
    igv_porcentaje DOUBLE NOT NULL CHECK (igv_porcentaje >= 0),  
    subtotal DOUBLE NOT NULL CHECK (subtotal >= 0),  
    igv_total DOUBLE NOT NULL CHECK (igv_total >= 0),  
    descuento DOUBLE NULL CHECK (descuento >= 0),
    total DOUBLE NOT NULL CHECK (total >= 0),  
    id_usuario BIGINT NOT NULL,  
    id_cliente BIGINT NOT NULL,  
    id_metodo_pago BIGINT NOT NULL,  
    cancelado BIT(1) NOT NULL,
    activo BIT(1) NOT NULL DEFAULT 1,
    PRIMARY KEY (id),
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id),  
    FOREIGN KEY (id_cliente) REFERENCES clientes(id),  
    FOREIGN KEY (id_metodo_pago) REFERENCES metodo_pagos(id)  
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

CREATE TABLE garantias (
    id BIGINT NOT NULL AUTO_INCREMENT,
    id_detalle BIGINT NOT NULL, -- se relaciona con el producto vendido
    inicio_garantia DATE NOT NULL, -- fecha de la venta
    fin_garantia DATE NOT NULL,    -- fecha_venta + producto.garantia_meses
    activo BIT(1) NOT NULL DEFAULT 1,
    PRIMARY KEY (id),
    FOREIGN KEY (id_detalle) REFERENCES detalle_ventas(id)
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

CREATE TABLE reclamo_proveedor (
    id BIGINT NOT NULL AUTO_INCREMENT,
    id_garantia BIGINT NOT NULL,  -- Reclamo proviene de una garantía
    id_proveedor BIGINT NOT NULL,
    id_ingreso BIGINT NOT NULL,   -- Lote al que corresponde el producto
    cantidad INT NOT NULL CHECK (cantidad > 0),
    descripcion TEXT NOT NULL,    -- Detalles del defecto de fábrica
    fecha_reclamo TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    estado ENUM('PENDIENTE', 'RESUELTO', 'RECHAZADO') DEFAULT 'PENDIENTE',
    activo BIT(1) NOT NULL DEFAULT 1,
    id_usuario BIGINT NOT NULL,  -- Quién gestionó el reclamo
    PRIMARY KEY (id),
    FOREIGN KEY (id_garantia) REFERENCES garantias(id),
    FOREIGN KEY (id_proveedor) REFERENCES proveedores(id),
    FOREIGN KEY (id_ingreso) REFERENCES ingreso_stocks(id),
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id)
);
