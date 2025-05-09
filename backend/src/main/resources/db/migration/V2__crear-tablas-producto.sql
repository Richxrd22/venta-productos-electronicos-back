CREATE TABLE empresas (
    id_empresa BIGINT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL,
    ruc VARCHAR(11) NOT NULL UNIQUE,
    website VARCHAR(255) NOT NULL,
    activo BIT(1) NOT NULL DEFAULT 1,
    PRIMARY KEY (id_empresa)
);

CREATE TABLE categorias (
    id_categoria BIGINT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL,
    activo BIT(1) NOT NULL DEFAULT 1,
    PRIMARY KEY (id_categoria)
);

CREATE TABLE subcategorias(
    id_subcategoria BIGINT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL,
    activo BIT(1) NOT NULL DEFAULT 1,
    id_categoria BIGINT NOT NULL,
    PRIMARY KEY (id_subcategoria),
    FOREIGN KEY (id_categoria) REFERENCES categorias(id_categoria)
);

CREATE TABLE proveedores (
    id_proveedor BIGINT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    correo VARCHAR(50) NOT NULL UNIQUE,
    dni VARCHAR(8) NOT NULL UNIQUE,
    celular VARCHAR(9) NOT NULL,
    activo BIT(1) NOT NULL DEFAULT 1,
    id_empresa BIGINT NOT NULL,
    PRIMARY KEY (id_proveedor),
    FOREIGN KEY (id_empresa) REFERENCES empresas(id_empresa)
);

CREATE TABLE marcas (
    id_marca BIGINT NOT NULL AUTO_INCREMENT,
    nombre_marca VARCHAR(50) NOT NULL,
    activo BIT(1) NOT NULL DEFAULT 1,
    PRIMARY KEY (id_marca)
);

CREATE TABLE productos (
    id_producto BIGINT NOT NULL AUTO_INCREMENT,
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
    PRIMARY KEY (id_producto),
    FOREIGN KEY (id_subcategoria) REFERENCES subcategorias(id_subcategoria),
    FOREIGN KEY (id_marca) REFERENCES marcas(id_marca),
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario)
);

