CREATE TABLE empleados (
    id_empleado BIGINT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    dni VARCHAR(8) NOT NULL UNIQUE,
    correo VARCHAR(50) NOT NULL UNIQUE,
    celular VARCHAR(9) NOT NULL,
    estado BIT(1) NOT NULL,
    PRIMARY KEY (id_empleado)
);

CREATE TABLE roles (
    id_rol BIGINT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL UNIQUE,
    PRIMARY KEY (id_rol)
);

CREATE TABLE usuarios (
    id_usuario BIGINT NOT NULL AUTO_INCREMENT,
    clave VARCHAR(50) NOT NULL,
    correo VARCHAR(50) NOT NULL UNIQUE,
    id_empleado BIGINT NOT NULL UNIQUE,
    id_rol BIGINT NOT NULL,
    clave_cambiada BIT(1) NOT NULL DEFAULT 0,
    PRIMARY KEY (id_usuario),
    FOREIGN KEY (id_empleado) REFERENCES empleados(id_empleado),
    FOREIGN KEY (id_rol) REFERENCES roles(id_rol)
);

INSERT INTO roles (nombre)
VALUES 
    ('ADMINISTRADOR'),
    ('ALMACENISTA'),
    ('VENDEDOR');