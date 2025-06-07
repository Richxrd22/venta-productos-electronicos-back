CREATE TABLE empleados (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    dni VARCHAR(8) NOT NULL UNIQUE,
    celular VARCHAR(9) NOT NULL UNIQUE,
    activo BIT(1) NOT NULL DEFAULT 1,
    PRIMARY KEY (id)
);

CREATE TABLE roles (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL UNIQUE,
    activo BIT(1) NOT NULL DEFAULT 1,
    PRIMARY KEY (id)
);

CREATE TABLE usuarios (
    id BIGINT NOT NULL AUTO_INCREMENT,
    clave VARCHAR(50) NOT NULL,
    correo VARCHAR(50) NOT NULL UNIQUE,
    id_empleado BIGINT NOT NULL UNIQUE,
    id_rol BIGINT NOT NULL,
    clave_cambiada BIT(1) NOT NULL DEFAULT 0,
    activo BIT(1) NOT NULL DEFAULT 1,
    intentos_fallidos INT DEFAULT 0,
    cuenta_bloqueada BIT(1) DEFAULT 0,
    fecha_bloqueo DATETIME DEFAULT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (id_empleado) REFERENCES empleados(id),
    FOREIGN KEY (id_rol) REFERENCES roles(id)
);

INSERT INTO roles (nombre, activo)
VALUES 
    ('ADMINISTRADOR', 1),
    ('ALMACENISTA', 1),
    ('VENDEDOR', 1);