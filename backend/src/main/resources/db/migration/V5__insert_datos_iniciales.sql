INSERT INTO marcas (id, nombre, activo) VALUES
(1, 'HP', 1),
(2, 'Lenovo', 1),
(3, 'Dell', 1),
(4, 'Asus', 1),
(5, 'Acer', 1),
(6, 'Intel', 1),
(7, 'AMD', 1),
(8, 'NVIDIA', 1),
(9, 'Gigabyte', 1),
(10, 'MSI', 1),
(11, 'Logitech', 1),
(12, 'Razer', 1),
(13, 'Microsoft', 1),
(14, 'Genius', 1),
(15, 'Redragon', 1),
(16, 'Apple', 1),
(17, 'Samsung', 1),
(18, 'Xiaomi', 1),
(19, 'Huawei', 1),
(20, 'Kingston', 1),
(21, 'Western Digital', 1),
(22, 'TP-Link', 1),
(23, 'Epson', 1),
(24, 'Brother', 1);


-- ===== PROVEEDORES =====
INSERT INTO proveedores (razon_social, ruc, direccion, correo, celular, telefono, activo) VALUES
('TecnoGlobal S.A.C.', '20123456781', 'Av. Arequipa 1234, Lima', 'contacto@tecnoglobal.com', '987654321', '2345678', 1),
('ElectroMax Perú S.R.L.', '20456789012', 'Jr. Moquegua 567, Lima', 'ventas@electromax.com', '981234567', '4567890', 1),
('Grupo Digital Solutions', '20678901234', 'Av. Brasil 789, Callao', 'soporte@grupods.com', '912345678', '3456789', 1),
('Distribuidora ByteTech', '20111222333', 'Calle Lima 123, Trujillo', 'info@bytetech.com', '923456789', '5678901', 1),
('Perú Tecnología S.A.C.', '20444555666', 'Av. Javier Prado 456, Lima', 'contacto@perutec.com', '934567891', '6789012', 1),
('Tech Importaciones E.I.R.L.', '20333444555', 'Av. Angamos 890, Surco', 'ventas@techimport.pe', '945678912', '7890123', 1),
('Multiservicios Electrónicos', '20999888777', 'Jr. Ica 456, Piura', 'multiservicios@electro.com', '956789123', NULL, 1),
('Soluciones Digitales Andinas', '20888776655', 'Av. Grau 321, Arequipa', 'contacto@solandinas.pe', '967891234', NULL, 1),
('RedTech Distribuidores', '20777665544', 'Av. La Marina 123, Lima', 'ventas@redtech.pe', '978912345', '3456780', 1),
('Comercial Infotec', '20666554433', 'Calle Amazonas 456, Huancayo', 'infotec@comercial.com', '989123456', NULL, 1),
('Innova Digital Peru S.A.C.', '20555443322', 'Av. Pardo 789, Cusco', 'innova@digital.com', '990123456', '1234567', 1),
('Electronix Store E.I.R.L.', '20444332211', 'Jr. San Martín 456, Tacna', 'ventas@electronix.pe', '901234567', NULL, 1),
('DataNet Proveedores', '20333221100', 'Av. Colonial 999, Lima', 'contacto@datanet.com', '912345679', '7654321', 1),
('Proveedor Global Tech', '20222110099', 'Av. Primavera 654, Lima', 'globaltech@proveedor.com', '923456780', NULL, 1),
('MaxConnect EIRL', '20999988877', 'Calle Arequipa 567, Iquitos', 'ventas@maxconnect.pe', '934567892', '2345671', 1),
('ElectroMundo Perú', '20888877766', 'Av. Guardia Civil 321, Lima', 'soporte@electromundo.pe', '945678913', NULL, 1),
('Distribuciones Electronet', '20777766655', 'Av. Faucett 123, Lima', 'info@electronet.pe', '956789124', '6543210', 1),
('Tecnotronic S.A.C.', '20666655544', 'Jr. Huallaga 789, Lima', 'contacto@tecnotronic.pe', '967891235', NULL, 1),
('Global Distribuidora Digital', '20555544433', 'Calle Bolívar 987, Arequipa', 'global@distribuidora.pe', '978912346', '3210987', 1),
('Inversiones DigiMarket', '20444433322', 'Av. El Sol 456, Cusco', 'ventas@digimarket.com', '989123457', NULL, 1);


-- ===== CATEGORÍAS =====
-- Nivel 0
INSERT INTO categorias (nombre, id_categoria_padre, nivel, activo) VALUES
('Computadoras', NULL, 0, 1),
('Componentes', NULL, 0, 1),
('Accesorios', NULL, 0, 1),
('Monitores', NULL, 0, 1),
('Impresoras', NULL, 0, 1),
('Almacenamiento', NULL, 0, 1),
('Audio', NULL, 0, 1),
('Tablets', NULL, 0, 1),
('Smartphones', NULL, 0, 1),
('Cámaras', NULL, 0, 1),
('Smartwatches', NULL, 0, 1),
('Limpieza Tecnológica', NULL, 0, 1),
('Proyectores', NULL, 0, 1);
-- Nivel 1
INSERT INTO categorias (nombre, id_categoria_padre, nivel, activo) VALUES
('Laptops', 1, 1, 1),
('PCs de Escritorio', 1, 1, 1),
('Tarjetas Gráficas', 2, 1, 1),
('Procesadores', 2, 1, 1),
('Teclados', 3, 1, 1),
('Mouses', 3, 1, 1),
('Webcams', 3, 1, 1),
('Monitores Curvos', 4, 1, 1),
('Monitores Gaming', 4, 1, 1),
('Multifuncionales', 5, 1, 1),
('Tinta y Tóner', 5, 1, 1),
('HDD', 6, 1, 1),
('SSD', 6, 1, 1),
('Auriculares', 7, 1, 1),
('Parlantes Bluetooth', 7, 1, 1),
('Tablets Android', 8, 1, 1),
('iPads', 8, 1, 1),
('Android', 9, 1, 1),
('iPhone', 9, 1, 1),
('DSLR', 10, 1, 1);
-- Nivel 2 
INSERT INTO categorias (nombre, id_categoria_padre, nivel, activo) VALUES
('Ultrabooks', 14, 2, 1),
('Gaming Laptops', 14, 2, 1),
('Workstations', 15, 2, 1),
('All-in-One', 15, 2, 1),
('NVIDIA', 16, 2, 1),
('AMD Radeon', 16, 2, 1),
('Intel i5', 17, 2, 1),
('Intel i7', 17, 2, 1),
('Teclados Mecánicos', 18, 2, 1),
('Teclados Bluetooth', 18, 2, 1),
('Mouse Gamer', 19, 2, 1),
('Mouse Inalámbrico', 19, 2, 1),
('Webcam Full HD', 20, 2, 1),
('Webcam 4K', 20, 2, 1),
('SSD SATA', 26, 2, 1),
('SSD NVMe', 26, 2, 1),
('Auriculares Inalámbricos', 27, 2, 1),
('Auriculares con Cable', 27, 2, 1),
('Parlantes 2.1', 28, 2, 1),
('Parlantes Portátiles', 28, 2, 1);


-- ===== PRODUCTOS =====
INSERT INTO productos (
    sku, nombre, modelo, color, descripcion,
    precio_venta, min_stock, max_stock, stock_actual,
    garantia_meses, activo, id_usuario, id_categoria, id_marca
) VALUES
-- Productos 1 a 10
('HP-LAP-15E-PLA-001', 'Laptop HP Pavilion 15', '15-eg0501la', 'Plata', 'Laptop HP con procesador Intel Core i5 y 8GB RAM', 2499.90, 5, 20, 0, 12, 1, NULL, 34, 1),
('LEN-LAP-81X-GRI-002', 'Laptop Lenovo Ideapad 3', '81X800ENLM', 'Gris', 'Laptop Lenovo con pantalla 15.6" y 256GB SSD', 1999.00, 5, 20, 0, 12, 1, NULL, 34, 2),
('SAM-MON-C24-NEG-003', 'Monitor Samsung Curvo 24"', 'C24F390FHL', 'Negro', 'Monitor curvo Samsung Full HD 24 pulgadas', 799.00, 3, 15, 0, 12, 1, NULL, 41, 18),
('KIN-SSD-SA4-MOD-004', 'SSD Kingston 480GB', 'SA400S37/480G', NULL, 'Disco sólido interno 480GB SATA 2.5"', 209.00, 10, 50, 0, 36, 1, NULL, 46, 21),
('LOG-MOU-M18-GRI-005', 'Mouse Logitech M185', 'M185', 'Gris', 'Mouse inalámbrico óptico con receptor USB', 69.90, 20, 100, 0, 24, 1, NULL, 39, 11),
('RED-AUR-MOD-ROJ-006', 'Auriculares Redragon Ares H120', NULL, 'Rojo', 'Headset gamer con micrófono', 129.00, 10, 40, 0, 12, 1, NULL, 47, 15),
('TPL-ROU-AC1-NEG-007', 'Router TP-Link Archer C6', 'AC1200', 'Negro', 'Router inalámbrico dual band WiFi 5', 149.00, 5, 25, 0, 36, 1, NULL, 50, 23),
('RAZ-TEC-MOD-NEG-008', 'Teclado Razer Cynosa V2', NULL, 'Negro', 'Teclado gaming retroiluminado RGB', 229.00, 5, 30, 0, 24, 1, NULL, 38, 12),
('AMD-PRO-MOD-COL-009', 'Procesador AMD Ryzen 5 5600G', NULL, NULL, 'APU con gráficos Radeon Vega 7', 699.00, 5, 15, 0, 36, 1, NULL, 36, 6),
('NVI-TAR-MOD-COL-010', 'Tarjeta Gráfica NVIDIA GTX 1650', NULL, NULL, '4GB GDDR6 PCIe 3.0', 1199.00, 2, 10, 0, 36, 1, NULL, 35, 7),

-- Productos 11 a 20
('EPS-IMP-MOD-NEG-011', 'Impresora Epson EcoTank L3250', NULL, 'Negro', 'Multifuncional con sistema continuo de tinta', 699.00, 3, 20, 0, 12, 1, NULL, 43, 24),
('SAM-TAB-SMX-GRI-012', 'Tablet Samsung Galaxy Tab A8', 'SM-X200', 'Gris', 'Pantalla 10.5" Octa-Core 64GB', 899.00, 4, 20, 0, 12, 1, NULL, 48, 19),
('XIA-SMA-MOD-AZU-013', 'Smartphone Xiaomi Redmi Note 12', NULL, 'Azul', '6.67" AMOLED, Snapdragon 685, 128GB', 799.00, 5, 30, 0, 12, 1, NULL, 49, 20),
('HUA-SMA-MOD-ROS-014', 'Smartwatch Huawei Watch Fit 2', NULL, 'Rosa', 'Reloj inteligente con pantalla AMOLED', 599.00, 3, 15, 0, 12, 1, NULL, 51, 20),
('MIC-WEB-MOD-NEG-015', 'Webcam Microsoft LifeCam HD-3000', NULL, 'Negro', 'Video HD 720p y micrófono integrado', 149.00, 5, 25, 0, 24, 1, NULL, 40, 13),
('MSI-PLA-MOD-COL-016', 'Placa Madre MSI B550M PRO-VDH', NULL, NULL, 'Socket AM4, DDR4, HDMI', 429.00, 3, 15, 0, 36, 1, NULL, 36, 10),
('DEL-PCD-540-BLA-017', 'All-in-One Dell Inspiron 24"', '5400', 'Blanco', 'Intel Core i5, 8GB RAM, 256GB SSD', 2999.00, 2, 10, 0, 12, 1, NULL, 35, 3),
('BRO-IMP-MOD-NEG-018', 'Impresora Brother HL-1212W', NULL, 'Negro', 'Láser monocromática inalámbrica', 599.00, 3, 10, 0, 12, 1, NULL, 43, 24),
('LOG-PAR-MOD-NEG-019', 'Parlantes Logitech Z313', NULL, 'Negro', 'Sistema 2.1 con subwoofer', 199.00, 5, 20, 0, 24, 1, NULL, 47, 11),
('APP-TAB-MOD-GRI-020', 'Tablet Apple iPad 9ª Gen 64GB', NULL, 'Gris Espacial', 'Pantalla Retina 10.2", A13 Bionic', 1599.00, 2, 10, 0, 12, 1, NULL, 48, 16),

-- Productos 21 a 30
('ASU-LAP-X51-NEG-021', 'Laptop Asus VivoBook X515EA', 'X515EA', 'Negro', 'Intel Core i5, 8GB RAM, 512GB SSD', 2299.00, 4, 15, 0, 12, 1, NULL, 34, 4),
('ACE-LAP-A31-PLA-022', 'Laptop Acer Aspire 3', 'A315-24P', 'Plata', 'AMD Ryzen 3, 256GB SSD, 8GB RAM', 1799.00, 3, 20, 0, 12, 1, NULL, 34, 5),
('GIG-MEM-MOD-COL-023', 'RAM Gigabyte 8GB DDR4', NULL, NULL, 'Memoria DDR4 2666MHz', 169.00, 10, 40, 0, 36, 1, NULL, 46, 9),
('LOG-CAM-C92-NEG-024', 'Webcam Logitech C920s HD Pro', 'C920s', 'Negro', 'Full HD 1080p con tapa de privacidad', 279.00, 3, 10, 0, 24, 1, NULL, 40, 11),
('AMD-TAR-MOD-COL-025', 'Tarjeta Radeon RX 6600 XT', NULL, NULL, '8GB GDDR6 para gaming 1080p', 1799.00, 2, 10, 0, 36, 1, NULL, 35, 6),
('TPL-SWI-TL8-BLA-026', 'Switch TP-Link TL-SG108', 'TL-SG108', 'Blanco', 'Switch no gestionable 8 puertos Gigabit', 229.00, 5, 20, 0, 24, 1, NULL, 52, 23),
('MIC-TEC-MOD-NEG-027', 'Teclado Microsoft Wired 600', NULL, 'Negro', 'Teclado ergonómico con cable USB', 89.00, 5, 25, 0, 24, 1, NULL, 38, 13),
('DEL-MON-S22-NEG-028', 'Monitor Dell S2222H', 'S2222H', 'Negro', '21.5", FHD, 75Hz, HDMI', 729.00, 3, 15, 0, 12, 1, NULL, 41, 3),
('HUA-SMA-Y91-AZU-029', 'Smartphone Huawei Y91', 'Y91', 'Azul', '6.95", 256GB, Kirin chip', 899.00, 4, 30, 0, 12, 1, NULL, 49, 20),
('XIA-SMA-RE1-NEG-030', 'Smartphone Xiaomi Redmi 10A', 'Redmi10A', 'Negro', '3GB RAM, 64GB, Helio G25', 549.00, 5, 30, 0, 12, 1, NULL, 49, 20),

-- Productos 31 a 40
('INT-PRO-I51-COL-031', 'Procesador Intel Core i5-11400F', 'i5-11400F', NULL, '6 núcleos, sin gráficos integrados', 799.00, 3, 15, 0, 36, 1, NULL, 36, 8),
('KIN-ALM-UV5-MOD-032', 'Memoria USB Kingston 64GB', 'DTKN/64GB', NULL, 'USB 3.2 DataTraveler Kyson', 59.90, 20, 100, 0, 60, 1, NULL, 46, 21),
('APP-CAM-MOD-BLA-033', 'Cámara Apple FaceTime HD', NULL, 'Blanco', 'Cámara integrada en MacBook', 0.00, 0, 0, 0, 0, 1, NULL, 40, 16),
('GIG-PLA-B45-COL-034', 'Placa Madre Gigabyte B450M DS3H', 'B450M DS3H', NULL, 'Socket AM4, HDMI, DDR4', 399.00, 3, 12, 0, 36, 1, NULL, 36, 9),
('MSI-PCD-MOD-NEG-035', 'PC de Escritorio MSI Codex 5', NULL, 'Negro', 'Intel Core i5, GTX 1660, 512GB SSD', 3899.00, 1, 5, 0, 12, 1, NULL, 35, 10),
('XIA-TAB-PAD-GRI-036', 'Tablet Xiaomi Pad 5', 'Pad 5', 'Gris', '11" WQHD+, 128GB, Snapdragon 860', 1399.00, 3, 15, 0, 12, 1, NULL, 48, 20),
('HP-IMP-205-NEG-037', 'Impresora HP DeskJet 2050', '2050', 'Negro', 'Multifuncional, USB, tinta negra', 399.00, 5, 20, 0, 12, 1, NULL, 43, 1),
('SAM-AUD-BUD-BLA-038', 'Audífonos Samsung Galaxy Buds2', 'Buds2', 'Blanco', 'Bluetooth, cancelación de ruido', 459.00, 5, 30, 0, 12, 1, NULL, 47, 18),
('RAZ-MOU-DE2-NEG-039', 'Mouse Razer DeathAdder V2', 'DeathAdder V2', 'Negro', 'Ergonómico, 20000 DPI', 239.00, 3, 25, 0, 24, 1, NULL, 39, 12),
('GEN-MOU-MOD-GRI-040', 'Mouse Genius DX-120', NULL, 'Gris', 'Mouse óptico con cable USB', 39.00, 10, 50, 0, 24, 1, NULL, 39, 14);

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



-- ===== Ingreso Stock =====

INSERT INTO ingreso_stocks (id, id_proveedor, fecha_ingreso, tipo_documento, numero_documento, observaciones) VALUES
(1, 3, '2025-07-01', 'Factura', 'FAC-1001', 'Ingreso inicial de productos básicos'),
(2, 5, '2025-07-01', 'Guía', 'GUI-1002', 'Ingreso intermedio para stock'),
(3, 7, '2025-07-01', 'Boleta', 'BOL-1003', 'Tercera carga de productos generales'),
(4, 2, '2025-07-01', 'Factura', 'FAC-1004', 'Carga adicional de periféricos'),
(5, 4, '2025-07-01', 'Guía', 'GUI-1005', 'Ingreso de componentes'),
(6, 1, '2025-07-01', 'Boleta', 'BOL-1006', 'Ingreso de dispositivos móviles'),
(7, 6, '2025-07-01', 'Factura', 'FAC-1007', 'Ingreso de accesorios y almacenamiento'),
(8, 8, '2025-07-01', 'Factura', 'FAC-1008', 'Ingreso de equipos de escritorio y tablets');

INSERT INTO detalle_ingresos (id, id_ingreso, id_producto, codigo_lote, cantidad, precio_unitario, activo) VALUES
(1, 1, 5, 'L-20250701-ABC123', 10, 589.99, 1),
(2, 1, 12, 'L-20250701-DEF456', 8, 349.50, 1),
(3, 2, 18, 'L-20250701-GHI789', 5, 429.90, 1),
(4, 2, 27, 'L-20250701-JKL012', 14, 1199.00, 1),
(5, 3, 3, 'L-20250701-MNO345', 15, 239.99, 1), -- Ajustado a max_stock 15
(6, 3, 30, 'L-20250701-PQR678', 18, 149.95, 1), -- Ajustado a max_stock 30 - stock previo 12
(7, 4, 4, 'L-20250701-KIN-480', 25, 199.00, 1),
(8, 4, 5, 'L-20250701-LOG-M185', 50, 59.90, 1),
(9, 4, 6, 'L-20250701-RED-H120', 20, 119.00, 1),
(10, 5, 7, 'L-20250701-TPL-C6', 15, 139.00, 1),
(11, 5, 8, 'L-20250701-RAZ-V2', 10, 219.00, 1),
(12, 5, 9, 'L-20250701-AMD-5600G', 10, 649.00, 1),
(13, 6, 10, 'L-20250701-NVI-1650', 5, 1099.00, 1),
(14, 6, 11, 'L-20250701-EPS-L3250', 10, 649.00, 1),
(15, 6, 13, 'L-20250701-XIA-NOTE12', 20, 749.00, 1),
(16, 6, 14, 'L-20250701-HUA-FIT2', 10, 559.00, 1),
(17, 7, 15, 'L-20250701-MIC-HD3000', 15, 139.00, 1),
(18, 7, 16, 'L-20250701-MSI-B550', 5, 399.00, 1),
(19, 7, 17, 'L-20250701-DEL-5400', 3, 2899.00, 1),
(20, 7, 19, 'L-20250701-LOG-Z313', 10, 179.00, 1),
(21, 7, 20, 'L-20250701-IPAD-64', 5, 1499.00, 1),
(22, 8, 21, 'L-20250701-ASU-X515', 8, 2199.00, 1),
(23, 8, 22, 'L-20250701-ACE-ASP3', 7, 1699.00, 1),
(24, 8, 23, 'L-20250701-GIG-8GB', 30, 159.00, 1),
(25, 8, 26, 'L-20250701-TPL-SW8', 10, 209.00, 1),
(26, 8, 30, 'L-20250701-REDMI10A', 18, 499.00, 1), -- Ajustado a no pasar el max_stock
(27, 8, 32, 'L-20250701-KIN-USB64', 40, 49.90, 1),
(28, 8, 34, 'L-20250701-GIG-B450M', 5, 369.00, 1),
(29, 8, 36, 'L-20250701-PAD5', 7, 1299.00, 1),
(30, 8, 40, 'L-20250701-GENIUS', 25, 29.00, 1);


UPDATE productos SET stock_actual = stock_actual + 10 WHERE id = 5;
UPDATE productos SET stock_actual = stock_actual + 8 WHERE id = 12;
UPDATE productos SET stock_actual = stock_actual + 5 WHERE id = 18;
UPDATE productos SET stock_actual = stock_actual + 14 WHERE id = 27;
UPDATE productos SET stock_actual = stock_actual + 15 WHERE id = 3;  -- Ajustado
UPDATE productos SET stock_actual = stock_actual + 18 WHERE id = 30; -- Ajustado

UPDATE productos SET stock_actual = stock_actual + 25 WHERE id = 4;
UPDATE productos SET stock_actual = stock_actual + 50 WHERE id = 5;
UPDATE productos SET stock_actual = stock_actual + 20 WHERE id = 6;
UPDATE productos SET stock_actual = stock_actual + 15 WHERE id = 7;
UPDATE productos SET stock_actual = stock_actual + 10 WHERE id = 8;
UPDATE productos SET stock_actual = stock_actual + 10 WHERE id = 9;
UPDATE productos SET stock_actual = stock_actual + 5 WHERE id = 10;
UPDATE productos SET stock_actual = stock_actual + 10 WHERE id = 11;
UPDATE productos SET stock_actual = stock_actual + 20 WHERE id = 13;
UPDATE productos SET stock_actual = stock_actual + 10 WHERE id = 14;
UPDATE productos SET stock_actual = stock_actual + 15 WHERE id = 15;
UPDATE productos SET stock_actual = stock_actual + 5 WHERE id = 16;
UPDATE productos SET stock_actual = stock_actual + 3 WHERE id = 17;
UPDATE productos SET stock_actual = stock_actual + 10 WHERE id = 19;
UPDATE productos SET stock_actual = stock_actual + 5 WHERE id = 20;
UPDATE productos SET stock_actual = stock_actual + 8 WHERE id = 21;
UPDATE productos SET stock_actual = stock_actual + 7 WHERE id = 22;
UPDATE productos SET stock_actual = stock_actual + 30 WHERE id = 23;
UPDATE productos SET stock_actual = stock_actual + 10 WHERE id = 26;
UPDATE productos SET stock_actual = stock_actual + 40 WHERE id = 32;
UPDATE productos SET stock_actual = stock_actual + 5 WHERE id = 34;
UPDATE productos SET stock_actual = stock_actual + 7 WHERE id = 36;
UPDATE productos SET stock_actual = stock_actual + 25 WHERE id = 40;