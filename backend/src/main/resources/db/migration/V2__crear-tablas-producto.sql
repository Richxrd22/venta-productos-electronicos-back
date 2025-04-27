CREATE TABLE empresa (
    id_empresa BIGINT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL,
    ruc VARCHAR(11) NOT NULL UNIQUE,
    website VARCHAR(255) NOT NULL,
    PRIMARY KEY (id_empresa)
);

CREATE TABLE categoria (
    id_categoria BIGINT NOT NULL AUTO_INCREMENT,
    nombre_categoria VARCHAR(50) NOT NULL,
    activo BIT(1) NOT NULL,
    PRIMARY KEY (id_categoria)
);

CREATE TABLE proveedor (
    id_proveedor BIGINT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    correo VARCHAR(50) NOT NULL UNIQUE,
    dni VARCHAR(8) NOT NULL UNIQUE,
    celular VARCHAR(9) NOT NULL,
    activo BIT(1) NOT NULL,
    id_empresa BIGINT NOT NULL,
    PRIMARY KEY (id_proveedor),
    FOREIGN KEY (id_empresa) REFERENCES empresa(id_empresa)
);

CREATE TABLE marca (
    id_marca BIGINT NOT NULL AUTO_INCREMENT,
    nombre_marca VARCHAR(50) NOT NULL,
    PRIMARY KEY (id_marca)
);

CREATE TABLE producto (
    id_producto BIGINT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL,
    descripcion VARCHAR(250) NOT NULL,
    sku VARCHAR(50) NOT NULL UNIQUE,
    precio DOUBLE NOT NULL CHECK (precio >= 0),
    min_stock INT NOT NULL CHECK (min_stock >= 0),
    max_stock INT NOT NULL CHECK (max_stock >= 0),
    stock INT NOT NULL CHECK (stock >= 0),
    garantia_meses INT NOT NULL CHECK (garantia_meses >= 0),
    activo BIT(1) NOT NULL,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    id_empleado BIGINT NOT NULL,
    id_categoria BIGINT NOT NULL,
    id_proveedor BIGINT NOT NULL,
    id_marca BIGINT NOT NULL,
    PRIMARY KEY (id_producto),
    FOREIGN KEY (id_categoria) REFERENCES categoria(id_categoria),
    FOREIGN KEY (id_proveedor) REFERENCES proveedor(id_proveedor),
    FOREIGN KEY (id_marca) REFERENCES marca(id_marca),
    FOREIGN KEY (id_empleado) REFERENCES empleado(id_empleado)
);