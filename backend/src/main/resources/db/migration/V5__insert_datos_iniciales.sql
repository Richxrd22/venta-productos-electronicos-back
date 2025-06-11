-- ===== MARCAS =====
INSERT INTO marcas (nombre, activo) VALUES
('Samsung', 1), ('LG', 1), ('Sony', 1), ('HP', 1), ('Lenovo', 1),
('Dell', 1), ('Apple', 1), ('Xiaomi', 1), ('Asus', 1), ('Acer', 1),
('Huawei', 1), ('Philips', 1), ('Panasonic', 1), ('Motorola', 1), ('Toshiba', 1);

-- ===== PROVEEDORES =====
INSERT INTO proveedores (razon_social, ruc, direccion, correo, celular, telefono, activo) VALUES
('ElectroPeru S.A.', '12345678901', 'Av. Lima 123', 'contacto@electroperu.com', '987654321', '1234567', 1),
('Tecnología Global S.A.', '23456789012', 'Av. San Borja 456', 'ventas@tecnologiaglobal.com', '998877665', '2345678', 1),
('Distribuciones XYZ', '34567890123', 'Calle Juan 789', 'contacto@xyz.com', '912445678', '3456789', 1),
('ElectroTech SAC', '20601234567', 'Av. Javier Prado 1234, Lima', 'contacto@electrotech.pe', '987653321', '4211234', 1),
('Tecnología Global SRL', '20609876543', 'Calle Los Robles 456, Arequipa', 'ventas@tecnologiaglobal.pe', '976543210', '2334455', 1),
('Distribuidora Digital Perú', '20501122334', 'Av. Brasil 3456, Lima', 'info@digitalperu.com', '988112233', '4455667', 1),
('Importaciones MegaTech', '20770111223', 'Jr. Ayacucho 789, Trujillo', 'megatech@importaciones.pe', '965432198', NULL, 1),
('Electro Mayoristas Andinos', '20881234567', 'Av. Grau 132, Cusco', 'ventas@andinos.com', '912345678', '4011223', 1);

-- ===== CATEGORÍAS =====
INSERT INTO categorias (nombre, id_categoria_padre, nivel, activo) VALUES
('Electrónica', NULL, 0, 1),
('Computadoras', 1, 1, 1),
('Laptops', 2, 2, 1),
('PCs de Escritorio', 2, 2, 1),
('Componentes', 1, 1, 1),
('Tarjetas Gráficas', 5, 2, 1),
('Procesadores', 5, 2, 1),
('Almacenamiento', 5, 2, 1),
('Accesorios', 1, 1, 1),
('Teclados', 9, 2, 1),
('Mouses', 9, 2, 1),
('Monitores', 1, 1, 1),
('Impresoras', 1, 1, 1),
('Redes', 1, 1, 1),
('Routers', 14, 2, 1),
('Switches', 14, 2, 1);

-- ===== PRODUCTOS =====
INSERT INTO productos (
    sku, nombre, modelo, color, descripcion, precio_venta,
    min_stock, max_stock, stock_actual, garantia_meses,
    activo, id_usuario, id_categoria, id_marca
) VALUES
('SAM-SMA-A34-NEG-001', 'Galaxy A34', 'A34', 'Negro', 'Smartphone Samsung con pantalla AMOLED', 1299.99, 10, 50, 0, 12, 1, NULL, 2, 1),
('SAM-SMA-A34-BLA-002', 'Galaxy A34', 'A34', 'Blanco', 'Smartphone Samsung con pantalla AMOLED', 1299.99, 10, 50, 0, 12, 1, NULL, 2, 1),
('APP-TAB-IPA-GRY-003', 'iPad Pro', 'iPad', 'Gris', 'Tablet Apple de última generación', 3899.50, 5, 30, 0, 12, 1, NULL, 3, 7),
('APP-PHO-IPH-BLA-004', 'iPhone 13', 'iPhone', 'Blanco', 'Smartphone Apple con cámara avanzada', 3999.99, 5, 40, 0, 12, 1, NULL, 2, 7),
('LEN-LAP-THI-BLK-005', 'ThinkPad X1', 'ThinkPad', 'Negro', 'Laptop Lenovo para profesionales', 5299.00, 5, 20, 0, 24, 1, NULL, 3, 5),
('DELL-LAP-XPS-WHT-006', 'XPS 13', 'XPS', 'Blanco', 'Laptop Dell ultraligera', 4799.99, 5, 25, 0, 24, 1, NULL, 3, 6),
('SON-HED-MDR-BLK-007', 'MDR ZX110', 'MDR', 'Negro', 'Audífonos Sony con cancelación de ruido', 199.99, 10, 60, 0, 6, 1, NULL, 9, 3),
('SAM-TAB-GAL-BLU-008', 'Galaxy Tab S7', 'Galaxy', 'Azul', 'Tablet Samsung con S-Pen', 2299.00, 5, 30, 0, 12, 1, NULL, 3, 1),
('ASU-LAP-VIV-GRY-009', 'VivoBook 15', 'VivoBook', 'Gris', 'Laptop Asus para estudiantes', 2599.00, 5, 40, 0, 12, 1, NULL, 3, 9),
('HP-LAP-ENV-BLK-010', 'Envy 13', 'Envy', 'Negro', 'Laptop HP ultraligera y potente', 4399.00, 5, 20, 0, 24, 1, NULL, 3, 4);

-- ===== CLIENTES =====
INSERT INTO clientes (nombre, apellido, dni, celular, correo, activo) VALUES
('Lucía', 'Pérez', '72104567', '987654310', 'lucia.perez@example.com', 1),
('Miguel', 'Sánchez', '70894561', '987654311', 'miguel.sanchez@example.com', 1),
('Andrea', 'García', '71984532', '987654312', 'andrea.garcia@example.com', 1),
('Pedro', 'Ruiz', '70023456', '987654313', 'pedro.ruiz@example.com', 1),
('Valeria', 'Torres', '71678945', '987654314', 'valeria.torres@example.com', 1),
('Juan', 'López', '70111234', '987654315', 'juan.lopez@example.com', 1),
('Camila', 'Fernández', '72345678', '987654316', 'camila.fernandez@example.com', 1),
('Diego', 'Castillo', '70543219', '987654317', 'diego.castillo@example.com', 1),
('María', 'Rojas', '70789123', '987654318', 'maria.rojas@example.com', 1),
('Sofía', 'Mendoza', '70987654', '987654319', 'sofia.mendoza@example.com', 1);

-- ===== MÉTODOS DE PAGO =====
INSERT INTO metodo_pagos (metodo, activo) VALUES
('Efectivo', 1),
('Yape', 1),
('Transferencia', 1),
('Visa', 1);

-- ===== VENTAS Y DETALLES (forma compatible con Flyway) =====

-- Venta VivoBook 15 a Lucía
INSERT INTO registro_ventas (id_cliente, id_metodo_pago, id_usuario, subtotal, igv_porcentaje, igv_total, descuento, total, fecha, cancelado, activo, id_cupon)
VALUES (1, 1, NULL, 2599.00, 18.0, 467.82, 0.00, 3066.82, '2025-06-09 10:00:00', 1, 1, NULL);

INSERT INTO detalle_ventas (id_registro_venta, id_producto, cantidad, precio_unitario, total)
SELECT rv.id, 9, 1, 2599.00, 2599.00
FROM registro_ventas rv WHERE rv.id_cliente = 1 AND rv.total = 3066.82 LIMIT 1;

-- Venta iPhone 13 a Miguel
INSERT INTO registro_ventas (id_cliente, id_metodo_pago, id_usuario, subtotal, igv_porcentaje, igv_total, descuento, total, fecha, cancelado, activo, id_cupon)
VALUES (2, 2, NULL, 3999.99, 18.0, 719.9982, 0.00, 4719.9882, '2025-06-09 11:30:00',1,1, NULL);

INSERT INTO detalle_ventas (id_registro_venta, id_producto, cantidad, precio_unitario, total)
SELECT rv.id, 4, 1, 3999.99, 3999.99
FROM registro_ventas rv WHERE rv.id_cliente = 2 AND rv.total = 4719.9882 LIMIT 1;

-- Venta Galaxy A34 a Andrea
INSERT INTO registro_ventas (id_cliente, id_metodo_pago, id_usuario, subtotal, igv_porcentaje, igv_total, descuento, total, fecha, cancelado, activo, id_cupon)
VALUES (3, 3, NULL, 2599.98, 18.0, 467.9964, 0.00, 3067.9764, '2025-06-09 13:00:00',1,1, NULL);

INSERT INTO detalle_ventas (id_registro_venta, id_producto, cantidad, precio_unitario, total)
SELECT rv.id, 1, 1, 1299.99, 1299.99 FROM registro_ventas rv WHERE rv.id_cliente = 3 AND rv.total = 3067.9764 LIMIT 1;

INSERT INTO detalle_ventas (id_registro_venta, id_producto, cantidad, precio_unitario, total)
SELECT rv.id, 2, 1, 1299.99, 1299.99 FROM registro_ventas rv WHERE rv.id_cliente = 3 AND rv.total = 3067.9764 LIMIT 1;
