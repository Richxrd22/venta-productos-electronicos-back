CREATE TABLE categorias (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL,
    activo BIT(1) NOT NULL DEFAULT 1,
    PRIMARY KEY (id)
);

CREATE TABLE subcategorias(
    id BIGINT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL,
    activo BIT(1) NOT NULL DEFAULT 1,
    id_categoria BIGINT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (id_categoria) REFERENCES categorias(id)
);

CREATE TABLE proveedores (
    id BIGINT NOT NULL AUTO_INCREMENT,
    razon_social VARCHAR(50) NOT NULL,
    ruc VARCHAR(11) NOT NULL UNIQUE,
    direccion VARCHAR(100) NOT NULL,
    correo VARCHAR(50) NOT NULL UNIQUE,
    celular VARCHAR(9) NOT NULL,
    telefono VARCHAR(7) NULL,
    activo BIT(1) NOT NULL DEFAULT 1,
    PRIMARY KEY (id)
);

CREATE TABLE marcas (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL,
    activo BIT(1) NOT NULL DEFAULT 1,
    PRIMARY KEY (id)
);

CREATE TABLE productos (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL,
    descripcion VARCHAR(250) NOT NULL,
    precio_venta DOUBLE NOT NULL CHECK (precio_venta >= 0),
    precio_compra DOUBLE NOT NULL CHECK (precio_compra >= 0),
    min_stock INT NOT NULL CHECK (min_stock >= 0),
    max_stock INT NOT NULL CHECK (max_stock >= 0),
    garantia_meses INT NOT NULL CHECK (garantia_meses >= 0),
    activo BIT(1) NOT NULL DEFAULT 1,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    id_usuario BIGINT NOT NULL,
    id_subcategoria BIGINT NOT NULL,
    id_marca BIGINT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (id_subcategoria) REFERENCES subcategorias(id),
    FOREIGN KEY (id_marca) REFERENCES marcas(id),
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id)
);

