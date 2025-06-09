-- Inserción de marcas
INSERT INTO marcas (nombre, activo) VALUES
('Samsung', 1),
('LG', 1),
('Sony', 1),
('HP', 1),
('Lenovo', 1),
('Dell', 1),
('Apple', 1),
('Xiaomi', 1),
('Asus', 1),
('Acer', 1),
('Huawei', 1),
('Philips', 1),
('Panasonic', 1),
('Motorola', 1),
('Toshiba', 1);

-- Inserción de proveedores
INSERT INTO proveedores (razon_social, ruc, direccion, correo, celular, telefono, activo) VALUES
('ElectroPeru S.A.', '12345678901', 'Av. Lima 123', 'contacto@electroperu.com', '987654321', '1234567', 1),
('Tecnología Global S.A.', '23456789012', 'Av. San Borja 456', 'ventas@tecnologiaglobal.com', '998877665', '2345678', 1),
('Distribuciones XYZ', '34567890123', 'Calle Juan 789', 'contacto@xyz.com', '912445678', '3456789', 1),
('ElectroTech SAC', '20601234567', 'Av. Javier Prado 1234, Lima', 'contacto@electrotech.pe', '987653321', '4211234', 1),
('Tecnología Global SRL', '20609876543', 'Calle Los Robles 456, Arequipa', 'ventas@tecnologiaglobal.pe', '976543210', '2334455', 1),
('Distribuidora Digital Perú', '20501122334', 'Av. Brasil 3456, Lima', 'info@digitalperu.com', '988112233', '4455667', 1),
('Importaciones MegaTech', '20770111223', 'Jr. Ayacucho 789, Trujillo', 'megatech@importaciones.pe', '965432198', NULL, 1),
('Electro Mayoristas Andinos', '20881234567', 'Av. Grau 132, Cusco', 'ventas@andinos.com', '912345678', '4011223', 1);

-- Inserción de categorías
INSERT INTO categorias (nombre, id_categoria_padre, nivel, activo) VALUES
('Electrónica', NULL, 0, 1),          -- id_categoria = 1
('Computadoras', 1, 1, 1),            -- id_categoria = 2
('Laptops', 2, 2, 1),                 -- id_categoria = 3
('PCs de Escritorio', 2, 2, 1),       -- id_categoria = 4
('Componentes', 1, 1, 1),             -- id_categoria = 5
('Tarjetas Gráficas', 5, 2, 1),       -- id_categoria = 6
('Procesadores', 5, 2, 1),            -- id_categoria = 7
('Almacenamiento', 5, 2, 1),          -- id_categoria = 8
('Accesorios', 1, 1, 1),              -- id_categoria = 9
('Teclados', 9, 2, 1),                -- id_categoria = 10
('Mouses', 9, 2, 1),                  -- id_categoria = 11
('Monitores', 1, 1, 1),               -- id_categoria = 12
('Impresoras', 1, 1, 1),              -- id_categoria = 13
('Redes', 1, 1, 1),                   -- id_categoria = 14
('Routers', 14, 2, 1),                -- id_categoria = 15
('Switches', 14, 2, 1);               -- id_categoria = 16

-- Inserción de productos (nota: id_usuario = 1, asigna según tu sistema)
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
<<<<<<< HEAD
('HP-LAP-ENV-BLK-010', 'Envy 13', 'Envy', 'Negro', 'Laptop HP ultraligera y potente', 4399.00, 5, 20, 0, 24, 1, NULL, 3, 4);
=======
('HP-LAP-ENV-BLK-010', 'Envy 13', 'Envy', 'Negro', 'Laptop HP ultraligera y potente', 4399.00, 5, 20, 0, 24, 1, NULL, 3, 4);
>>>>>>> main
