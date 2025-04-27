CREATE TABLE cliente (
    id_cliente BIGINT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    dni VARCHAR(8) NOT NULL UNIQUE,
    celular VARCHAR(9) NOT NULL UNIQUE,
    correo_cliente VARCHAR(100) NOT NULL UNIQUE,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    activo BIT(1) NOT NULL,
    PRIMARY KEY (id_cliente)
);


CREATE TABLE metodo_pago (
    id_metodo_pago BIGINT NOT NULL AUTO_INCREMENT,
    metodo VARCHAR(100) NOT NULL UNIQUE,
    PRIMARY KEY (id_metodo_pago)
);

CREATE TABLE registro_venta (
    id_registro_venta BIGINT NOT NULL AUTO_INCREMENT,
    fecha DATE NOT NULL,
    igv_porcentaje DOUBLE NOT NULL CHECK (igv_porcentaje >= 0),  
    subtotal DOUBLE NOT NULL CHECK (subtotal >= 0),  
    igv_total DOUBLE NOT NULL CHECK (igv_total >= 0),  
    total DOUBLE NOT NULL CHECK (total >= 0),  
    id_empleado BIGINT NOT NULL,  
    id_cliente BIGINT NOT NULL,  
    id_metodo_pago BIGINT NOT NULL,  
    PRIMARY KEY (id_registro_venta),
    FOREIGN KEY (id_empleado) REFERENCES empleado(id_empleado),  
    FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente),  
    FOREIGN KEY (id_metodo_pago) REFERENCES metodo_pago(id_metodo_pago)  
);

CREATE TABLE detalle_venta (
    id_detalle_venta BIGINT NOT NULL AUTO_INCREMENT,
    cantidad INT NOT NULL CHECK (cantidad > 0),
    precio_unitario DOUBLE NOT NULL CHECK (precio_unitario >= 0),
    total DOUBLE NOT NULL CHECK (total >= 0),
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    id_producto BIGINT NOT NULL,
    id_registro_venta BIGINT NOT NULL,
    PRIMARY KEY (id_detalle_venta),
    FOREIGN KEY (id_producto) REFERENCES producto(id_producto),
    FOREIGN KEY (id_registro_venta) REFERENCES registro_venta(id_registro_venta)
);

