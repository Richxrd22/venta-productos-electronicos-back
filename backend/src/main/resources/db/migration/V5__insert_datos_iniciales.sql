INSERT INTO categorias (nombre) VALUES 
('Audio'),
('Computadoras'),
('Consolas'),
('Accesorios');

INSERT INTO subcategorias (nombre, id_categoria) VALUES 
('Auriculares', 1),
('Parlantes', 1),
('Laptops', 2),
('Teclados', 2),
('PlayStation', 3),
('Xbox', 3),
('Cables USB', 4),
('Cargadores', 4);

INSERT INTO marcas (nombre) VALUES 
('Sony'),
('Dell'),
('HP'),
('Apple'),
('Samsung');

INSERT INTO proveedores (razon_social, ruc, direccion, correo, celular, telefono) VALUES 
('ElectroPeru S.A.', '12345678901', 'Av. Lima 123', 'contacto@electroperu.com', '987654321', '1234567'),
('Tecnología Global S.A.', '23456789012', 'Av. San Borja 456', 'ventas@tecnologiaglobal.com', '998877665', '2345678'),
('Distribuciones XYZ', '34567890123', 'Calle Juan 789', 'contacto@xyz.com', '912345678', '3456789');

