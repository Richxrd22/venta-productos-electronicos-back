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

-- EMPLEADOS

INSERT INTO empleados (nombre, apellido, dni, celular, activo) VALUES
('Carlos', 'Ramírez', '12335678', '987634321', 1),
('Lucía', 'Gómez', '23456749', '976543222', 1),
('Pedro', 'Martínez', '34537890', '965434109', 1);

-- USUARIOS

INSERT INTO usuarios (clave, correo, id_empleado, id_rol, clave_cambiada, activo, intentos_fallidos, cuenta_bloqueada, fecha_bloqueo) VALUES
('vendedor124', 'vendedor233@empresa.com', 1, 3, 1, 1, 0, 0, NULL),
('almacen123', 'almacen@empresa.com', 2, 2, 1, 1, 0, 0, NULL),
('vendedor123', 'vendedor@empresa.com', 3, 3, 1, 1, 0, 0, NULL);


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
('HP-LAP-15E-PLA-001', 'Laptop HP Pavilion 15', '15-eg0501la', 'Plata', 'Laptop HP con procesador Intel Core i5 y 8GB RAM', 2499.90, 5, 30, 0, 12, 1, 1, 34, 1),
('LEN-LAP-81X-GRI-002', 'Laptop Lenovo Ideapad 3', '81X800ENLM', 'Gris', 'Laptop Lenovo con pantalla 15.6" y 256GB SSD', 1999.00, 5, 30, 0, 12, 1, 1, 34, 2),
('SAM-MON-C24-NEG-003', 'Monitor Samsung Curvo 24"', 'C24F390FHL', 'Negro', 'Monitor curvo Samsung Full HD 24 pulgadas', 799.00, 3, 15, 0, 12, 1, 1, 40, 18),
('KIN-SSD-SA4-MOD-004', 'SSD Kingston 480GB', 'SA400S37/480G', NULL, 'Disco sólido interno 480GB SATA 2.5"', 209.00, 10, 50, 0, 36, 1, 1, 48, 21),
('LOG-MOU-M18-GRI-005', 'Mouse Logitech M185', 'M185', 'Gris', 'Mouse inalámbrico óptico con receptor USB', 69.90, 20, 100, 0, 24, 1, 1, 45, 11),
('RED-AUR-MOD-ROJ-006', 'Auriculares Redragon Ares H120', NULL, 'Rojo', 'Headset gamer con micrófono', 129.00, 10, 40, 0, 12, 1, 1, 51, 15),
('TPL-ROU-AC1-NEG-007', 'Router TP-Link Archer C6', 'AC1200', 'Negro', 'Router inalámbrico dual band WiFi 5', 149.00, 5, 25, 0, 36, 1, 1, 36, 23),
('RAZ-TEC-MOD-NEG-008', 'Teclado Razer Cynosa V2', NULL, 'Negro', 'Teclado gaming retroiluminado RGB', 229.00, 5, 30, 0, 24, 1, 1, 42, 12),
('AMD-PRO-MOD-COL-009', 'Procesador AMD Ryzen 5 5600G', NULL, NULL, 'APU con gráficos Radeon Vega 7', 699.00, 5, 15, 0, 36, 1, 1, 39, 6),
('NVI-TAR-MOD-COL-010', 'Tarjeta Gráfica NVIDIA GTX 1650', NULL, NULL, '4GB GDDR6 PCIe 3.0', 1199.00, 2, 10, 0, 36, 1, 1, 38, 7),

-- Productos 11 a 20
('EPS-IMP-MOD-NEG-011', 'Impresora Epson EcoTank L3250', NULL, 'Negro', 'Multifuncional con sistema continuo de tinta', 699.00, 3, 20, 0, 12, 1, 1, 36, 24),
('SAM-TAB-SMX-GRI-012', 'Tablet Samsung Galaxy Tab A8', 'SM-X200', 'Gris', 'Pantalla 10.5" Octa-Core 64GB', 899.00, 4, 20, 0, 12, 1, 1, 34, 19),
('XIA-SMA-MOD-AZU-013', 'Smartphone Xiaomi Redmi Note 12', NULL, 'Azul', '6.67" AMOLED, Snapdragon 685, 128GB', 799.00, 5, 30, 0, 12, 1, 1,34, 20),
('HUA-SMA-MOD-ROS-014', 'Smartwatch Huawei Watch Fit 2', NULL, 'Rosa', 'Reloj inteligente con pantalla AMOLED', 599.00, 3, 15, 0, 12, 1, 1, 34, 20),
('MIC-WEB-MOD-NEG-015', 'Webcam Microsoft LifeCam HD-3000', NULL, 'Negro', 'Video HD 720p y micrófono integrado', 149.00, 5, 25, 0, 24, 1, 1, 46, 13),
('MSI-PLA-MOD-COL-016', 'Placa Madre MSI B550M PRO-VDH', NULL, NULL, 'Socket AM4, DDR4, HDMI', 429.00, 3, 15, 0, 36, 1, 1, 36, 10),
('DEL-PCD-540-BLA-017', 'All-in-One Dell Inspiron 24"', '5400', 'Blanco', 'Intel Core i5, 8GB RAM, 256GB SSD', 2999.00, 2, 10, 0, 12, 1, 1, 37, 3),
('BRO-IMP-MOD-NEG-018', 'Impresora Brother HL-1212W', NULL, 'Negro', 'Láser monocromática inalámbrica', 599.00, 3, 10, 0, 12, 1, 1, 36, 24),
('LOG-PAR-MOD-NEG-019', 'Parlantes Logitech Z313', NULL, 'Negro', 'Sistema 2.1 con subwoofer', 199.00, 5, 20, 0, 24, 1, 1, 52, 11),
('APP-TAB-MOD-GRI-020', 'Tablet Apple iPad 9ª Gen 64GB', NULL, 'Gris Espacial', 'Pantalla Retina 10.2", A13 Bionic', 1599.00, 2, 10, 0, 12, 1, 1, 34, 16),

-- Productos 21 a 30
('ASU-LAP-X51-NEG-021', 'Laptop Asus VivoBook X515EA', 'X515EA', 'Negro', 'Intel Core i5, 8GB RAM, 512GB SSD', 2299.00, 4, 15, 0, 12, 1, 1, 34, 4),
('ACE-LAP-A31-PLA-022', 'Laptop Acer Aspire 3', 'A315-24P', 'Plata', 'AMD Ryzen 3, 256GB SSD, 8GB RAM', 1799.00, 3, 20, 0, 12, 1, 1, 34, 5),
('GIG-MEM-MOD-COL-023', 'RAM Gigabyte 8GB DDR4', NULL, NULL, 'Memoria DDR4 2666MHz', 169.00, 10, 40, 0, 36, 1, 1, 40, 9),
('LOG-CAM-C92-NEG-024', 'Webcam Logitech C920s HD Pro', 'C920s', 'Negro', 'Full HD 1080p con tapa de privacidad', 279.00, 3, 10, 0, 24, 1, 1, 46, 11),
('AMD-TAR-MOD-COL-025', 'Tarjeta Radeon RX 6600 XT', NULL, NULL, '8GB GDDR6 para gaming 1080p', 1799.00, 2, 10, 0, 36, 1, 1, 39, 6),
('TPL-SWI-TL8-BLA-026', 'Switch TP-Link TL-SG108', 'TL-SG108', 'Blanco', 'Switch no gestionable 8 puertos Gigabit', 229.00, 5, 20, 0, 24, 1, 1, 36, 23),
('MIC-TEC-MOD-NEG-027', 'Teclado Microsoft Wired 600', NULL, 'Negro', 'Teclado ergonómico con cable USB', 89.00, 5, 25, 0, 24, 1, 1, 43, 13),
('DEL-MON-S22-NEG-028', 'Monitor Dell S2222H', 'S2222H', 'Negro', '21.5\", FHD, 75Hz, HDMI', 729.00, 3, 15, 0, 12, 1, 1, 40, 3),
('HUA-SMA-Y91-AZU-029', 'Smartphone Huawei Y91', 'Y91', 'Azul', '6.95\", 256GB, Kirin chip', 899.00, 4, 30, 0, 12, 1, 1, 34, 20),
('XIA-SMA-RE1-NEG-030', 'Smartphone Xiaomi Redmi 10A', 'Redmi10A', 'Negro', '3GB RAM, 64GB, Helio G25', 549.00, 5, 30, 0, 12, 1, 1, 34, 20),

-- Productos 31 a 40
('INT-PRO-I51-COL-031', 'Procesador Intel Core i5-11400F', 'i5-11400F', NULL, '6 núcleos, sin gráficos integrados', 799.00, 3, 15, 0, 36, 1, 1, 40, 8),
('KIN-ALM-UV5-MOD-032', 'Memoria USB Kingston 64GB', 'DTKN/64GB', NULL, 'USB 3.2 DataTraveler Kyson', 59.90, 20, 100, 0, 60, 1, 1, 48, 21),
('APP-CAM-MOD-BLA-033', 'Cámara Apple FaceTime HD', NULL, 'Blanco', 'Cámara integrada en MacBook', 0.00, 0, 0, 0, 0, 1, 1, 46, 16),
('GIG-PLA-B45-COL-034', 'Placa Madre Gigabyte B450M DS3H', 'B450M DS3H', NULL, 'Socket AM4, HDMI, DDR4', 399.00, 3, 12, 0, 36, 1, 1, 36, 9),
('MSI-PCD-MOD-NEG-035', 'PC de Escritorio MSI Codex 5', NULL, 'Negro', 'Intel Core i5, GTX 1660, 512GB SSD', 3899.00, 1, 5, 0, 12, 1, 1, 37, 10),
('XIA-TAB-PAD-GRI-036', 'Tablet Xiaomi Pad 5', 'Pad 5', 'Gris', '11\" WQHD+, 128GB, Snapdragon 860', 1399.00, 3, 15, 0, 12, 1, 1, 34, 20),
('HP-IMP-205-NEG-037', 'Impresora HP DeskJet 2050', '2050', 'Negro', 'Multifuncional, USB, tinta negra', 399.00, 5, 20, 0, 12, 1, 1, 36, 1),
('SAM-AUD-BUD-BLA-038', 'Audífonos Samsung Galaxy Buds2', 'Buds2', 'Blanco', 'Bluetooth, cancelación de ruido', 459.00, 5, 30, 0, 12, 1, 1, 50, 18),
('RAZ-MOU-DE2-NEG-039', 'Mouse Razer DeathAdder V2', 'DeathAdder V2', 'Negro', 'Ergonómico, 20000 DPI', 239.00, 3, 25, 0, 24, 1, 1, 44, 12),
('GEN-MOU-MOD-GRI-040', 'Mouse Genius DX-120', NULL, 'Gris', 'Mouse óptico con cable USB', 39.00, 10, 50, 0, 24, 1, 1, 44, 14);


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




-- NUEVOS DATOS

-- #####################################################
-- ## 1. CUPONES DE DESCUENTO (10 Cupones)
-- #####################################################
INSERT INTO cupones (codigo, descripcion, tipo_descuento, descuento_porcentaje, descuento_monto, fecha_inicio, fecha_fin, max_usos, usos_actuales, activo) VALUES
('BIENVENIDA20', '20% de descuento para nuevos clientes', 'PORCENTAJE', 20.00, NULL, '2023-01-01', '2025-12-31', 1000, 150, 1),
('CYBER2024', 'S/50 de descuento en compras mayores a S/300', 'MONTO', NULL, 50.00, '2024-11-20', '2024-11-25', 200, 198, 0),
('TODO10', '10% de descuento en toda la tienda', 'PORCENTAJE', 10.00, NULL, '2025-05-01', '2025-05-31', 500, 41, 1),
('INVIERNO30', 'S/30 de descuento en linea blanca', 'MONTO', NULL, 30.00, '2025-07-01', '2025-08-31', 300, 5, 1),
('FLASH15', 'Descuento rápido de 15%', 'PORCENTAJE', 15.00, NULL, '2025-02-10', '2025-02-11', 100, 100, 0),
('SOLOFANS', 'Descuento exclusivo para seguidores', 'PORCENTAJE', 12.00, NULL, '2024-01-01', '2025-12-31', 500, 78, 1),
('LIQUIDA50', 'S/50 de descuento en productos seleccionados', 'MONTO', NULL, 50.00, '2023-10-01', '2023-10-15', 100, 95, 0),
('TECNOMANIA', '10% en toda la categoría de tecnología', 'PORCENTAJE', 10.00, NULL, '2025-08-01', '2025-08-15', 400, 0, 1),
('ENVIOFREE', 'Descuento equivalente al envío', 'MONTO', NULL, 15.00, '2024-01-01', '2025-12-31', 2000, 450, 1),
('ANIVERSARIO', '25% de descuento por aniversario', 'PORCENTAJE', 25.00, NULL, '2025-10-01', '2025-10-07', 150, 0, 1);

-- #####################################################
-- ## 2. REGISTRO DE VENTAS (250 Ventas)
-- #####################################################
INSERT INTO registro_ventas (fecha, igv_porcentaje, subtotal, igv_total, descuento, total, cancelado, activo, id_usuario, id_cliente, id_metodo_pago, id_cupon) VALUES
('2024-01-15 10:30:00', 18.00, 1500.00, 270.00, 300.00, 1470.00, 1, 1, 2, 5, 1, 1),
('2024-02-20 11:00:00', 18.00, 350.00, 63.00, 0.00, 413.00, 1, 1, 1, 2, 2, NULL),
('2024-03-10 14:00:00', 18.00, 800.00, 144.00, 0.00, 944.00, 1, 1, 2, 8, 3, NULL),
('2024-04-16 18:20:00', 18.00, 420.00, 75.60, 15.00, 480.60, 1, 1, 1, 10, 1, 9),
('2024-05-05 09:15:00', 18.00, 120.00, 21.60, 0.00, 141.60, 1, 1, 3, 1, 2, NULL),
('2024-06-21 16:45:00', 18.00, 2500.00, 450.00, 0.00, 2950.00, 1, 1, 1, 7, 4, NULL),
('2024-06-08 12:00:00', 18.00, 650.00, 117.00, 0.00, 767.00, 1, 1, 3, 3, 1, NULL),
('2024-06-19 17:50:00', 18.00, 95.00, 17.10, 0.00, 112.10, 0, 1, 2, 9, 3, NULL),
('2024-05-30 11:30:00', 18.00, 1800.00, 324.00, 216.00, 1908.00, 1, 1, 1, 4, 4, 6),
('2024-05-14 20:00:00', 18.00, 220.00, 39.60, 0.00, 259.60, 1, 1, 2, 6, 2, NULL),
-- Ventas de 2024
('2024-01-15 10:00:00', 18.00, 300.50, 54.09, 0.00, 354.59, 1, 1, 1, 1, 1, NULL),
('2024-02-05 14:20:00', 18.00, 89.90, 16.18, 0.00, 106.08, 1, 1, 1, 8, 2, NULL),
('2024-03-10 18:00:00', 18.00, 550.00, 99.00, 55.00, 594.00, 1, 1, 1, 3, 3, 3),
('2024-04-15 11:45:00', 18.00, 1250.00, 225.00, 0.00, 1475.00, 1, 1, 1, 9, 4, NULL),
('2024-05-20 16:30:00', 18.00, 75.00, 13.50, 0.00, 88.50, 1, 1, 1, 2, 1, NULL),
('2024-06-02 09:10:00', 18.00, 420.00, 75.60, 0.00, 495.60, 0, 1, 1, 7, 2, NULL),
('2024-04-08 13:00:00', 18.00, 2100.00, 378.00, 420.00, 2058.00, 1, 1, 1, 4, 1, 1),
('2024-08-15 17:00:00', 18.00, 180.00, 32.40, 0.00, 212.40, 1, 1, 1, 10, 3, NULL),
('2024-09-24 19:00:00', 18.00, 980.00, 176.40, 0.00, 1156.40, 1, 1, 1, 5, 4, NULL),
('2024-10-03 10:25:00', 18.00, 600.00, 108.00, 0.00, 708.00, 1, 1, 1, 6, 2, NULL),
-- Ventas de 2025
('2025-01-10 15:00:00', 18.00, 450.00, 81.00, 45.00, 486.00, 1, 1, 1, 2, 1, 3),
('2025-01-18 12:00:00', 18.00, 88.00, 15.84, 0.00, 103.84, 1, 1, 1, 8, 3, NULL),
('2025-01-25 18:30:00', 18.00, 320.00, 57.60, 0.00, 377.60, 1, 1, 1, 1, 2, NULL),
('2025-02-02 11:10:00', 18.00, 150.00, 27.00, 0.00, 177.00, 1, 1, 1, 9, 1, NULL),
('2025-02-10 16:00:00', 18.00, 2400.00, 432.00, 360.00, 2472.00, 1, 1, 1, 3, 4, 5),
('2025-02-14 19:30:00', 18.00, 500.00, 90.00, 0.00, 590.00, 1, 1, 1, 7, 1, NULL),
('2025-02-20 09:00:00', 18.00, 130.00, 23.40, 0.00, 153.40, 1, 1, 1, 4, 3, NULL),
('2025-02-28 14:45:00', 18.00, 780.00, 140.40, 0.00, 920.40, 0, 1, 1, 10, 2, NULL),
('2025-03-05 17:20:00', 18.00, 99.90, 17.98, 0.00, 117.88, 1, 1, 1, 5, 1, NULL),
('2025-03-12 11:00:00', 18.00, 680.00, 122.40, 0.00, 802.40, 1, 1, 1, 6, 4, NULL),
('2025-03-20 10:50:00', 18.00, 145.00, 26.10, 0.00, 171.10, 1, 1, 1, 2, 1, NULL),
('2025-03-28 15:15:00', 18.00, 3000.00, 540.00, 600.00, 2940.00, 1, 1, 1, 8, 1, 1),
('2025-04-01 12:00:00', 18.00, 470.00, 84.60, 0.00, 554.60, 1, 1, 1, 1, 2, NULL),
('2025-04-08 16:40:00', 18.00, 250.00, 45.00, 0.00, 295.00, 1, 1, 1, 9, 3, NULL),
('2025-04-15 19:00:00', 18.00, 1100.00, 198.00, 0.00, 1298.00, 1, 1, 1, 3, 4, NULL),
('2025-04-22 09:30:00', 18.00, 85.00, 15.30, 0.00, 100.30, 1, 1, 1, 7, 1, NULL),
('2025-04-30 14:00:00', 18.00, 620.00, 111.60, 0.00, 731.60, 1, 1, 1, 4, 2, NULL),
('2025-05-05 18:10:00', 18.00, 190.00, 34.20, 19.00, 205.20, 1, 1, 1, 10, 1, 3),
('2025-05-12 11:50:00', 18.00, 720.00, 129.60, 0.00, 849.60, 1, 1, 1, 5, 3, NULL),
('2025-05-20 10:00:00', 18.00, 1300.00, 234.00, 0.00, 1534.00, 1, 1, 1, 6, 4, NULL),
('2025-05-28 15:30:00', 18.00, 280.00, 50.40, 0.00, 330.40, 0, 1, 1, 2, 2, NULL),
('2025-06-03 12:40:00', 18.00, 950.00, 171.00, 30.00, 1091.00, 1, 1, 1, 8, 1, 4),
('2025-06-10 17:00:00', 18.00, 560.00, 100.80, 0.00, 660.80, 1, 1, 1, 1, 3, NULL),
('2025-06-15 11:00:00', 18.00, 310.00, 55.80, 0.00, 365.80, 1, 1, 1, 9, 2, NULL),
('2025-06-20 16:20:00', 18.00, 1800.00, 324.00, 0.00, 2124.00, 1, 1, 1, 3, 4, NULL),
('2025-06-25 09:50:00', 18.00, 400.00, 72.00, 0.00, 472.00, 1, 1, 1, 7, 1, NULL),
('2025-06-01 10:10:00', 18.00, 75.50, 13.59, 0.00, 89.09, 1, 1, 1, 4, 2, NULL);
-- Nuevas Ventas - Enero 2025 (Desde ID 48)
INSERT INTO registro_ventas (fecha, igv_porcentaje, subtotal, igv_total, descuento, total, cancelado, activo, id_usuario, id_cliente, id_metodo_pago, id_cupon) VALUES
('2025-01-05 11:15:00', 18.00, 118.00, 21.24, 0.00, 139.24, 1, 1, 1, 4, 2, NULL),  -- Venta 48
('2025-01-12 16:40:00', 18.00, 360.00, 64.80, 0.00, 424.80, 1, 1, 1, 9, 1, NULL),  -- Venta 49
('2025-01-20 13:10:00', 18.00, 1300.00, 234.00, 100.00, 1434.00, 1, 1, 1, 2, 3, NULL),  -- Venta 50
('2025-01-28 18:20:00', 18.00, 1750.00, 315.00, 0.00, 2065.00, 1, 1, 1, 6, 4, NULL);  -- Venta 51

-- Ventas adicionales - Enero 2025
INSERT INTO registro_ventas (fecha, igv_porcentaje, subtotal, igv_total, descuento, total, cancelado, activo, id_usuario, id_cliente, id_metodo_pago, id_cupon) VALUES
('2025-01-09 10:50:00', 18.00, 640.00, 115.20, 0.00, 755.20, 1, 1, 1, 5, 2, NULL),  -- Venta 52
('2025-01-14 14:10:00', 18.00, 735.00, 132.30, 0.00, 867.30, 1, 1, 1, 7, 1, NULL),  -- Venta 53
('2025-01-19 16:30:00', 18.00, 594.00, 106.92, 0.00, 700.92, 1, 1, 1, 3, 3, NULL),  -- Venta 54
('2025-01-24 12:00:00', 18.00, 1479.00, 266.22, 0.00, 1745.22, 1, 1, 1, 1, 4, NULL), -- Venta 55
('2025-01-30 17:45:00', 18.00, 1435.00, 258.30, 0.00, 1693.30, 1, 1, 1, 10, 2, NULL); -- Venta 56

-- Ventas finales de enero 2025
INSERT INTO registro_ventas (fecha, igv_porcentaje, subtotal, igv_total, descuento, total, cancelado, activo, id_usuario, id_cliente, id_metodo_pago, id_cupon) VALUES
('2025-01-31 09:00:00', 18.00, 179.00, 32.22, 0.00, 211.22, 1, 1, 1, 9, 1, NULL),   -- Venta 57
('2025-01-31 11:30:00', 18.00, 229.00, 41.22, 0.00, 270.22, 1, 1, 1, 2, 3, NULL),   -- Venta 58
('2025-01-31 13:45:00', 18.00, 470.00, 84.60, 0.00, 554.60, 1, 1, 1, 5, 2, NULL),   -- Venta 59
('2025-01-31 18:15:00', 18.00, 975.00, 175.50, 0.00, 1150.50, 1, 1, 1, 4, 4, NULL); -- Venta 60

INSERT INTO registro_ventas (fecha, igv_porcentaje, subtotal, igv_total, descuento, total, cancelado, activo, id_usuario, id_cliente, id_metodo_pago, id_cupon) VALUES
('2025-02-05 10:45:00', 18.00, 109.00, 19.62, 0.00, 128.62, 1, 1, 1, 3, 2, NULL),
('2025-02-10 15:20:00', 18.00, 849.00, 152.82, 0.00, 1001.82, 1, 1, 2, 5, 3, NULL),
('2025-02-14 12:30:00', 18.00, 289.00, 52.02, 0.00, 341.02, 1, 1, 3, 2, 1, NULL),
('2025-02-20 17:00:00', 18.00, 958.00, 172.44, 0.00, 1130.44, 1, 1, 2, 4, 2, NULL),
('2025-02-27 11:15:00', 18.00, 1349.00, 242.82, 100.00, 1491.82, 1, 1, 1, 1, 4, NULL);

INSERT INTO registro_ventas (fecha, igv_porcentaje, subtotal, igv_total, descuento, total, cancelado, activo, id_usuario, id_cliente, id_metodo_pago, id_cupon) VALUES
('2025-02-16 16:40:00', 18.00, 318.00, 57.24, 0.00, 375.24, 1, 1, 1, 7, 2, NULL),
('2025-02-18 09:30:00', 18.00, 785.00, 141.30, 0.00, 926.30, 1, 1, 2, 5, 1, NULL),
('2025-02-22 14:00:00', 18.00, 1278.00, 230.04, 50.00, 1458.04, 1, 1, 3, 9, 3, NULL),
('2025-02-25 19:15:00', 18.00, 979.00, 176.22, 0.00, 1155.22, 1, 1, 2, 6, 4, NULL),
('2025-02-28 13:00:00', 18.00, 1798.00, 323.64, 100.00, 2021.64, 1, 1, 1, 2, 2, NULL);
INSERT INTO registro_ventas (fecha, igv_porcentaje, subtotal, igv_total, descuento, total, cancelado, activo, id_usuario, id_cliente, id_metodo_pago, id_cupon) VALUES
('2025-02-10 12:45:00', 18.00, 109.00, 19.62, 0.00, 128.62, 1, 1, 2, 3, 1, NULL),
('2025-02-12 10:00:00', 18.00, 359.00, 64.62, 0.00, 423.62, 1, 1, 1, 5, 2, NULL),
('2025-02-14 17:20:00', 18.00, 399.00, 71.82, 0.00, 470.82, 1, 1, 3, 9, 3, NULL),
('2025-02-20 08:10:00', 18.00, 1050.00, 189.00, 50.00, 1189.00, 1, 1, 1, 1, 4, NULL),
('2025-02-27 19:40:00', 18.00, 849.00, 152.82, 0.00, 1001.82, 1, 1, 2, 8, 1, NULL);


-- ## REGISTRO DE VENTAS - MARZO
INSERT INTO registro_ventas (fecha, igv_porcentaje, subtotal, igv_total, descuento, total, cancelado, activo, id_usuario, id_cliente, id_metodo_pago, id_cupon) VALUES
('2025-03-02 10:15:00', 18.00, 2500.00, 450.00, 50.00, 2900.00, 1, 1, 1, 3, 2, 5),
('2025-03-05 11:30:00', 18.00, 1200.00, 216.00, 0.00, 1416.00, 1, 1, 2, 5, 1, NULL),
('2025-03-08 14:00:00', 18.00, 899.00, 161.82, 0.00, 1060.82, 1, 1, 3, 1, 3, NULL),
('2025-03-11 16:45:00', 18.00, 3250.00, 585.00, 100.00, 3735.00, 1, 1, 1, 8, 4, 2),
('2025-03-15 09:00:00', 18.00, 450.00, 81.00, 0.00, 531.00, 1, 1, 2, 2, 1, NULL),
('2025-03-18 12:20:00', 18.00, 6800.00, 1224.00, 300.00, 7724.00, 1, 1, 3, 10, 2, 8),
('2025-03-21 17:00:00', 18.00, 180.00, 32.40, 0.00, 212.40, 1, 1, 1, 7, 3, NULL),
('2025-03-24 18:30:00', 18.00, 950.00, 171.00, 20.00, 1101.00, 1, 1, 2, 4, 4, 1),
('2025-03-27 10:00:00', 18.00, 2200.00, 396.00, 0.00, 2596.00, 1, 1, 3, 6, 1, NULL),
('2025-03-30 13:45:00', 18.00, 310.00, 55.80, 0.00, 365.80, 1, 1, 1, 9, 2, NULL);

INSERT INTO registro_ventas (fecha, igv_porcentaje, subtotal, igv_total, descuento, total, cancelado, activo, id_usuario, id_cliente, id_metodo_pago, id_cupon) VALUES
('2025-03-04 12:00:00', 18.00, 5000.00, 900.00, 150.00, 5750.00, 1, 1, 1, 1, 2, 8),
('2025-03-06 15:20:00', 18.00, 2000.00, 360.00, 0.00, 2360.00, 1, 1, 2, 7, 1, NULL),
('2025-03-09 18:00:00', 18.00, 1200.00, 216.00, 0.00, 1416.00, 1, 1, 3, 3, 3, NULL),
('2025-03-12 10:45:00', 18.00, 1600.00, 288.00, 50.00, 1838.00, 1, 1, 1, 9, 4, 1),
('2025-03-16 11:30:00', 18.00, 2500.00, 450.00, 0.00, 2950.00, 1, 1, 2, 2, 1, NULL),
('2025-03-19 14:10:00', 18.00, 1400.00, 252.00, 0.00, 1652.00, 1, 1, 3, 10, 2, NULL),
('2025-03-22 09:00:00', 18.00, 1000.00, 180.00, 20.00, 1160.00, 1, 1, 1, 5, 3, 4),
('2025-03-25 16:55:00', 18.00, 500.00, 90.00, 0.00, 590.00, 1, 1, 2, 8, 4, NULL),
('2025-03-28 19:20:00', 18.00, 7000.00, 1260.00, 300.00, 7960.00, 1, 1, 3, 4, 1, 9),
('2025-03-31 12:00:00', 18.00, 1000.00, 180.00, 0.00, 1180.00, 1, 1, 1, 6, 2, NULL);


-- ## REGISTRO DE VENTAS - ABRIL
INSERT INTO registro_ventas (fecha, igv_porcentaje, subtotal, igv_total, descuento, total, cancelado, activo, id_usuario, id_cliente, id_metodo_pago, id_cupon) VALUES
('2025-04-01 09:30:00', 18.00, 4500.00, 810.00, 200.00, 5110.00, 1, 1, 2, 1, 4, 3),
('2025-04-04 15:00:00', 18.00, 750.00, 135.00, 0.00, 885.00, 1, 1, 3, 8, 1, NULL),
('2025-04-07 12:10:00', 18.00, 1250.00, 225.00, 30.00, 1445.00, 1, 1, 1, 5, 2, 7),
('2025-04-10 11:00:00', 18.00, 300.00, 54.00, 0.00, 354.00, 1, 1, 2, 2, 3, NULL),
('2025-04-14 10:45:00', 18.00, 550.00, 99.00, 0.00, 649.00, 1, 1, 3, 9, 4, NULL),
('2025-04-18 16:00:00', 18.00, 7800.00, 1404.00, 500.00, 8704.00, 1, 1, 1, 10, 1, 10),
('2025-04-21 13:25:00', 18.00, 1999.00, 359.82, 0.00, 2358.82, 1, 1, 2, 7, 2, NULL),
('2025-04-24 19:00:00', 18.00, 620.00, 111.60, 0.00, 731.60, 1, 1, 3, 4, 3, NULL),
('2025-04-27 11:50:00', 18.00, 340.00, 61.20, 10.00, 391.20, 1, 1, 1, 6, 4, 4),
('2025-04-30 17:15:00', 18.00, 480.00, 86.40, 0.00, 566.40, 1, 1, 2, 3, 1, NULL);

INSERT INTO registro_ventas (fecha, igv_porcentaje, subtotal, igv_total, descuento, total, cancelado, activo, id_usuario, id_cliente, id_metodo_pago, id_cupon) VALUES
('2025-04-02 11:10:00', 18.00, 1800.00, 324.00, 0.00, 2124.00, 1, 1, 2, 9, 3, NULL),
('2025-04-05 13:00:00', 18.00, 3000.00, 540.00, 75.00, 3465.00, 1, 1, 3, 1, 4, 2),
('2025-04-08 17:40:00', 18.00, 700.00, 126.00, 0.00, 826.00, 1, 1, 1, 5, 1, NULL),
('2025-04-11 10:00:00', 18.00, 1900.00, 342.00, 0.00, 2242.00, 1, 1, 2, 10, 2, NULL),
('2025-04-15 16:25:00', 18.00, 1700.00, 306.00, 0.00, 2006.00, 1, 1, 3, 3, 3, NULL),
('2025-04-19 14:00:00', 18.00, 1100.00, 198.00, 30.00, 1268.00, 1, 1, 1, 8, 4, 5),
('2025-04-22 11:50:00', 18.00, 250.00, 45.00, 0.00, 295.00, 1, 1, 2, 2, 1, NULL),
('2025-04-25 09:30:00', 18.00, 800.00, 144.00, 0.00, 944.00, 1, 1, 3, 7, 2, NULL),
('2025-04-28 15:00:00', 18.00, 5000.00, 900.00, 200.00, 5700.00, 1, 1, 1, 4, 3, 10),
('2025-04-30 18:10:00', 18.00, 550.00, 99.00, 0.00, 649.00, 1, 1, 2, 6, 4, NULL);


-- ## REGISTRO DE VENTAS - MAYO
INSERT INTO registro_ventas (fecha, igv_porcentaje, subtotal, igv_total, descuento, total, cancelado, activo, id_usuario, id_cliente, id_metodo_pago, id_cupon) VALUES
('2025-05-02 14:20:00', 18.00, 1800.00, 324.00, 0.00, 2124.00, 1, 1, 3, 2, 2, NULL),
('2025-05-05 10:00:00', 18.00, 560.00, 100.80, 15.00, 645.80, 1, 1, 1, 8, 3, 6),
('2025-05-08 11:45:00', 18.00, 3200.00, 576.00, 0.00, 3776.00, 1, 1, 2, 1, 4, NULL),
('2025-05-12 09:15:00', 18.00, 275.00, 49.50, 0.00, 324.50, 1, 1, 3, 7, 1, NULL),
('2025-05-15 16:30:00', 18.00, 720.00, 129.60, 25.00, 824.60, 1, 1, 1, 4, 2, 9),
('2025-05-19 18:00:00', 18.00, 4999.00, 899.82, 150.00, 5748.82, 1, 1, 2, 10, 3, 2),
('2025-05-22 12:00:00', 18.00, 650.00, 117.00, 0.00, 767.00, 1, 1, 3, 5, 4, NULL),
('2025-05-25 15:10:00', 18.00, 110.00, 19.80, 0.00, 129.80, 1, 1, 1, 9, 1, NULL),
('2025-05-28 10:55:00', 18.00, 880.00, 158.40, 0.00, 1038.40, 1, 1, 2, 3, 2, NULL),
('2025-05-31 19:40:00', 18.00, 1500.00, 270.00, 40.00, 1730.00, 1, 1, 3, 6, 3, 1);

INSERT INTO registro_ventas (fecha, igv_porcentaje, subtotal, igv_total, descuento, total, cancelado, activo, id_usuario, id_cliente, id_metodo_pago, id_cupon) VALUES
('2025-05-03 10:15:00', 18.00, 4000.00, 720.00, 0.00, 4720.00, 1, 1, 3, 2, 1, NULL),
('2025-05-06 14:30:00', 18.00, 5000.00, 900.00, 150.00, 5750.00, 1, 1, 1, 8, 2, 3),
('2025-05-09 11:00:00', 18.00, 2000.00, 360.00, 0.00, 2360.00, 1, 1, 2, 4, 3, NULL),
('2025-05-13 13:20:00', 18.00, 1200.00, 216.00, 25.00, 1391.00, 1, 1, 3, 1, 4, 6),
('2025-05-17 15:00:00', 18.00, 1600.00, 288.00, 0.00, 1888.00, 1, 1, 1, 9, 1, NULL),
('2025-05-20 10:50:00', 18.00, 5000.00, 900.00, 0.00, 5900.00, 1, 1, 2, 5, 2, NULL),
('2025-05-23 09:45:00', 18.00, 1700.00, 306.00, 0.00, 2006.00, 1, 1, 3, 10, 3, NULL),
('2025-05-26 12:30:00', 18.00, 1400.00, 252.00, 30.00, 1622.00, 1, 1, 1, 3, 4, 7),
('2025-05-29 17:00:00', 18.00, 1500.00, 270.00, 0.00, 1770.00, 1, 1, 2, 7, 1, NULL),
('2025-05-31 20:00:00', 18.00, 500.00, 90.00, 0.00, 590.00, 1, 1, 3, 6, 2, NULL);

-- ## REGISTRO DE VENTAS - JUNIO
INSERT INTO registro_ventas (fecha, igv_porcentaje, subtotal, igv_total, descuento, total, cancelado, activo, id_usuario, id_cliente, id_metodo_pago, id_cupon) VALUES
('2025-06-03 12:30:00', 18.00, 999.00, 179.82, 0.00, 1178.82, 1, 1, 1, 4, 4, NULL),
('2025-06-06 14:00:00', 18.00, 210.00, 37.80, 0.00, 247.80, 1, 1, 2, 9, 1, NULL),
('2025-06-09 10:20:00', 18.00, 4200.00, 756.00, 120.00, 4836.00, 1, 1, 3, 2, 2, 5),
('2025-06-12 17:00:00', 18.00, 330.00, 59.40, 0.00, 389.40, 1, 1, 1, 7, 3, NULL),
('2025-06-16 11:35:00', 18.00, 680.00, 122.40, 0.00, 802.40, 1, 1, 2, 5, 4, NULL),
('2025-06-19 09:50:00', 18.00, 9500.00, 1710.00, 800.00, 10410.00, 1, 1, 3, 10, 1, 10),
('2025-06-21 16:15:00', 18.00, 150.00, 27.00, 5.00, 172.00, 1, 1, 1, 1, 2, 7),
('2025-06-24 13:00:00', 18.00, 540.00, 97.20, 0.00, 637.20, 1, 1, 2, 8, 3, NULL),
('2025-06-27 18:45:00', 18.00, 1300.00, 234.00, 0.00, 1534.00, 1, 1, 3, 6, 4, NULL),
('2025-06-30 20:00:00', 18.00, 250.00, 45.00, 10.00, 285.00, 1, 1, 1, 3, 1, 3);

INSERT INTO registro_ventas (fecha, igv_porcentaje, subtotal, igv_total, descuento, total, cancelado, activo, id_usuario, id_cliente, id_metodo_pago, id_cupon) VALUES
('2025-06-02 09:10:00', 18.00, 1800.00, 324.00, 0.00, 2124.00, 1, 1, 1, 1, 3, NULL),
('2025-06-05 16:00:00', 18.00, 3000.00, 540.00, 0.00, 3540.00, 1, 1, 2, 7, 4, NULL),
('2025-06-08 11:20:00', 18.00, 1300.00, 234.00, 60.00, 1474.00, 1, 1, 3, 3, 1, 1),
('2025-06-11 14:50:00', 18.00, 1900.00, 342.00, 0.00, 2242.00, 1, 1, 1, 10, 2, NULL),
('2025-06-15 10:00:00', 18.00, 400.00, 72.00, 0.00, 472.00, 1, 1, 2, 5, 3, NULL),
('2025-06-18 12:45:00', 18.00, 500.00, 90.00, 20.00, 570.00, 1, 1, 3, 8, 4, 8),
('2025-06-21 18:00:00', 18.00, 1600.00, 288.00, 0.00, 1888.00, 1, 1, 1, 2, 1, NULL),
('2025-06-24 15:30:00', 18.00, 5000.00, 900.00, 0.00, 5900.00, 1, 1, 2, 9, 2, NULL),
('2025-06-27 13:00:00', 18.00, 550.00, 99.00, 10.00, 639.00, 1, 1, 3, 4, 3, 2),
('2025-06-30 19:00:00', 18.00, 1000.00, 180.00, 0.00, 1180.00, 1, 1, 1, 6, 4, NULL);


-- NUEVAS VENTAS DE PRODUCTOS FALTANTES
-- ENERO
INSERT INTO registro_ventas (fecha, igv_porcentaje, subtotal, igv_total, descuento, total, cancelado, activo, id_usuario, id_cliente, id_metodo_pago, id_cupon) VALUES
('2025-01-05 10:33:00', 18.00, 6000.00, 1080.00, 200.00, 6880.00, 1, 1, 1, 5, 1, 3),
('2025-01-09 14:11:00', 18.00, 3500.00, 630.00, 0.00, 4130.00, 1, 1, 2, 8, 2, NULL),
('2025-01-13 11:55:00', 18.00, 1800.00, 324.00, 50.00, 2074.00, 1, 1, 3, 2, 3, 7),
('2025-01-17 16:44:00', 18.00, 2500.00, 450.00, 0.00, 2950.00, 1, 1, 1, 9, 4, NULL),
('2025-01-21 09:22:00', 18.00, 1500.00, 270.00, 0.00, 1770.00, 1, 1, 2, 4, 1, NULL),
('2025-01-25 13:55:00', 18.00, 8000.00, 1440.00, 300.00, 9140.00, 1, 1, 3, 1, 2, 10),
('2025-01-29 17:22:00', 18.00, 900.00, 162.00, 0.00, 1062.00, 1, 1, 1, 6, 3, NULL);

-- FEBRERO
INSERT INTO registro_ventas (fecha, igv_porcentaje, subtotal, igv_total, descuento, total, cancelado, activo, id_usuario, id_cliente, id_metodo_pago, id_cupon) VALUES
('2025-02-03 11:32:00', 18.00, 2200.00, 396.00, 0.00, 2596.00, 1, 1, 2, 7, 3, NULL),
('2025-02-07 15:14:00', 18.00, 4800.00, 864.00, 100.00, 5564.00, 1, 1, 3, 3, 4, 1),
('2025-02-11 10:12:00', 18.00, 1100.00, 198.00, 0.00, 1298.00, 1, 1, 1, 10, 1, NULL),
('2025-02-15 14:12:00', 18.00, 7000.00, 1260.00, 250.00, 8010.00, 1, 1, 2, 1, 2, 5),
('2025-02-19 09:34:00', 18.00, 1300.00, 234.00, 0.00, 1534.00, 1, 1, 3, 5, 3, NULL),
('2025-02-23 12:15:00', 18.00, 3000.00, 540.00, 80.00, 3460.00, 1, 1, 1, 8, 4, 9),
('2025-02-27 16:59:00', 18.00, 600.00, 108.00, 0.00, 708.00, 1, 1, 2, 4, 1, NULL);

-- MARZO
INSERT INTO registro_ventas (fecha, igv_porcentaje, subtotal, igv_total, descuento, total, cancelado, activo, id_usuario, id_cliente, id_metodo_pago, id_cupon) VALUES
('2025-03-04 12:22:00', 18.00, 5500.00, 990.00, 180.00, 6310.00, 1, 1, 1, 1, 2, 8),
('2025-03-06 11:22:00', 18.00, 2800.00, 504.00, 0.00, 3304.00, 1, 1, 2, 7, 1, NULL),
('2025-03-09 11:22:00', 18.00, 1500.00, 270.00, 0.00, 1770.00, 1, 1, 3, 3, 3, NULL),
('2025-03-12 12:12:00', 18.00, 2000.00, 360.00, 60.00, 2300.00, 1, 1, 1, 9, 4, 1),
('2025-03-16 18:12:00', 18.00, 3000.00, 540.00, 0.00, 3540.00, 1, 1, 2, 2, 1, NULL),
('2025-03-19 12:12:00', 18.00, 1800.00, 324.00, 0.00, 2124.00, 1, 1, 3, 10, 2, NULL),
('2025-03-22 10:15:00', 18.00, 1200.00, 216.00, 25.00, 1391.00, 1, 1, 1, 5, 3, 4);

-- ABRIL
INSERT INTO registro_ventas (fecha, igv_porcentaje, subtotal, igv_total, descuento, total, cancelado, activo, id_usuario, id_cliente, id_metodo_pago, id_cupon) VALUES
('2025-04-01 11:10:00', 18.00, 2500.00, 450.00, 0.00, 2950.00, 1, 1, 2, 9, 3, NULL),
('2025-04-02 13:00:00', 18.00, 4000.00, 720.00, 100.00, 4620.00, 1, 1, 3, 1, 4, 2),
('2025-04-09 19:40:00', 18.00, 1000.00, 180.00, 0.00, 1180.00, 1, 1, 1, 5, 1, NULL),
('2025-04-14 10:00:00', 18.00, 2200.00, 396.00, 0.00, 2596.00, 1, 1, 2, 10, 2, NULL),
('2025-04-19 16:25:00', 18.00, 1900.00, 342.00, 0.00, 2242.00, 1, 1, 3, 3, 3, NULL),
('2025-04-21 14:00:00', 18.00, 1500.00, 270.00, 40.00, 1730.00, 1, 1, 1, 8, 4, 5),
('2025-04-29 11:50:00', 18.00, 400.00, 72.00, 0.00, 472.00, 1, 1, 2, 2, 1, NULL);

-- MAYO
INSERT INTO registro_ventas (fecha, igv_porcentaje, subtotal, igv_total, descuento, total, cancelado, activo, id_usuario, id_cliente, id_metodo_pago, id_cupon) VALUES
('2025-05-01 14:15:00', 18.00, 4500.00, 810.00, 0.00, 5310.00, 1, 1, 3, 2, 1, NULL),
('2025-05-03 14:30:00', 18.00, 6000.00, 1080.00, 200.00, 6880.00, 1, 1, 1, 8, 2, 3),
('2025-05-07 11:00:00', 18.00, 2500.00, 450.00, 0.00, 2950.00, 1, 1, 2, 4, 3, NULL),
('2025-05-14 13:20:00', 18.00, 1500.00, 270.00, 30.00, 1740.00, 1, 1, 3, 1, 4, 6),
('2025-05-19 15:00:00', 18.00, 2200.00, 396.00, 0.00, 2596.00, 1, 1, 1, 9, 1, NULL),
('2025-05-24 10:50:00', 18.00, 5500.00, 990.00, 0.00, 6490.00, 1, 1, 2, 5, 2, NULL),
('2025-05-27 09:45:00', 18.00, 2000.00, 360.00, 0.00, 2360.00, 1, 1, 3, 10, 3, NULL);

-- JUNIO
INSERT INTO registro_ventas (fecha, igv_porcentaje, subtotal, igv_total, descuento, total, cancelado, activo, id_usuario, id_cliente, id_metodo_pago, id_cupon) VALUES
('2025-06-01 09:30:00', 18.00, 2800.00, 504.00, 0.00, 3304.00, 1, 1, 1, 1, 3, NULL),
('2025-06-02 16:40:00', 18.00, 4500.00, 810.00, 0.00, 5310.00, 1, 1, 2, 7, 4, NULL),
('2025-06-06 11:20:00', 18.00, 1800.00, 324.00, 80.00, 2044.00, 1, 1, 3, 3, 1, 1),
('2025-06-18 14:50:00', 18.00, 2500.00, 450.00, 0.00, 2950.00, 1, 1, 1, 10, 2, NULL),
('2025-06-19 11:22:00', 18.00, 600.00, 108.00, 0.00, 708.00, 1, 1, 2, 5, 3, NULL),
('2025-06-20 12:25:00', 18.00, 800.00, 144.00, 30.00, 914.00, 1, 1, 3, 8, 4, 8),
('2025-06-28 12:22:00', 18.00, 2000.00, 360.00, 0.00, 2360.00, 1, 1, 1, 2, 1, NULL);


-- OTROS
INSERT INTO registro_ventas (fecha, igv_porcentaje, subtotal, igv_total, descuento, total, cancelado, activo, id_usuario, id_cliente, id_metodo_pago, id_cupon) VALUES
('2025-01-03 09:30:01', 18.00, 3800.00, 684.00, 100.00, 4384.00, 1, 1, 1, 7, 2, 5),
('2025-01-08 14:15:03', 18.00, 2500.00, 450.00, 0.00, 2950.00, 1, 1, 2, 3, 1, NULL),
('2025-01-15 11:00:04', 18.00, 1700.00, 306.00, 50.00, 1956.00, 1, 1, 3, 9, 3, 2),
('2025-01-20 16:45:22', 18.00, 4200.00, 756.00, 0.00, 4956.00, 1, 1, 1, 1, 4, NULL),
('2025-01-27 10:00:44', 18.00, 1900.00, 342.00, 0.00, 2242.00, 1, 1, 2, 5, 1, NULL);

INSERT INTO registro_ventas (fecha, igv_porcentaje, subtotal, igv_total, descuento, total, cancelado, activo, id_usuario, id_cliente, id_metodo_pago, id_cupon) VALUES
('2025-02-04 12:00:32', 18.00, 2100.00, 378.00, 0.00, 2478.00, 1, 1, 3, 2, 2, NULL),
('2025-02-09 15:30:33', 18.00, 3600.00, 648.00, 120.00, 4128.00, 1, 1, 1, 8, 3, 8),
('2025-02-14 09:45:02', 18.00, 1400.00, 252.00, 0.00, 1652.00, 1, 1, 2, 4, 4, NULL),
('2025-02-21 13:10:01', 18.00, 2900.00, 522.00, 0.00, 3422.00, 1, 1, 3, 10, 1, NULL),
('2025-02-26 17:50:05', 18.00, 1600.00, 288.00, 70.00, 1818.00, 1, 1, 1, 6, 2, 1);

-- #####################################################
-- ## 3. DETALLE DE VENTAS
-- #####################################################
INSERT INTO detalle_ventas (cantidad, precio_unitario, total, fecha_creacion, id_producto, id_registro_venta, activo) VALUES
(1, 1500.00, 1500.00, '2024-01-15 10:30:00', 25, 1, 1),   -- Venta 1
(1, 350.00, 350.00, '2024-02-20 11:00:00', 10, 2, 1),    -- Venta 2
(2, 69.90, 139.80, '2024-03-10 14:00:00', 5, 3, 1),    -- Venta 3 (2 unidades)
(1, 420.00, 420.00, '2024-04-16 18:20:00', 33, 4, 1),   -- Venta 4
(1, 120.00, 120.00, '2024-05-05 09:15:00', 1, 5, 1),    -- Venta 5
(1, 2500.00, 2500.00, '2024-06-21 16:45:00', 40, 6, 1),  -- Venta 6
(1, 650.00, 650.00, '2024-06-08 12:00:00', 18, 7, 1),   -- Venta 7
(1, 95.00, 95.00, '2024-06-19 17:50:00', 2, 8, 1),     -- Venta 8
(2, 900.00, 1800.00, '2024-06-30 11:30:00', 38, 9, 1),   -- Venta 9 (2 unidades)
(1, 220.00, 220.00, '2024-06-14 20:00:00', 12, 10, 1),   -- Venta 10
-- Ventas de 2024
(1, 150.00, 150.00, '2024-01-15 10:00:00', 7, 11, 1),   -- Venta 11 (2 productos)
(1, 150.50, 150.50, '2024-01-15 10:00:00', 8, 11, 1),
(1, 89.90, 89.90, '2024-02-05 14:20:00', 22, 12, 1),     -- Venta 12
(1, 550.00, 550.00, '2024-03-10 18:00:00', 30, 13, 1),   -- Venta 13
(2, 625.00, 1250.00, '2024-04-15 11:45:00', 35, 14, 1),  -- Venta 14
(1, 75.00, 75.00, '2024-05-20 16:30:00', 3, 15, 1),      -- Venta 15
(1, 420.00, 420.00, '2024-06-02 09:10:00', 28, 16, 1),  -- Venta 16
(3, 700.00, 2100.00, '2024-05-08 13:00:00', 39, 17, 1),  -- Venta 17 (3 unidades)
(1, 180.00, 180.00, '2024-05-15 17:00:00', 14, 18, 1),  -- Venta 18
(2, 490.00, 980.00, '2024-03-24 19:00:00', 37, 19, 1),  -- Venta 19
(1, 600.00, 600.00, '2024-03-03 10:25:00', 26, 20, 1),   -- Venta 20
-- Ventas de 2025
(1, 450.00, 450.00, '2025-01-10 15:00:00', 11, 21, 1),  -- Venta 21
(1, 88.00, 88.00, '2025-01-18 12:00:00', 4, 22, 1),      -- Venta 22
(1, 160.00, 160.00, '2025-01-25 18:30:00', 15, 23, 1),  -- Venta 23 (2 productos, total 320, cada uno es 160)
(1, 160.00, 160.00, '2025-01-25 18:30:00', 16, 23, 1),
(1, 150.00, 150.00, '2025-02-02 11:10:00', 6, 24, 1),    -- Venta 24
(1, 2400.00, 2400.00, '2025-02-10 16:00:00', 40, 25, 1), -- Venta 25
(1, 500.00, 500.00, '2025-02-14 19:30:00', 20, 26, 1),  -- Venta 26
(1, 130.00, 130.00, '2025-02-20 09:00:00', 9, 27, 1),   -- Venta 27
(1, 390.00, 390.00, '2025-02-28 14:45:00', 31, 28, 1),  -- Venta 28 (2 productos, total 780, cada uno es 390)
(1, 390.00, 390.00, '2025-02-28 14:45:00', 32, 28, 1),
(1, 99.90, 99.90, '2025-03-05 17:20:00', 13, 29, 1),    -- Venta 29
(1, 680.00, 680.00, '2025-03-12 11:00:00', 23, 30, 1),  -- Venta 30
(1, 145.00, 145.00, '2025-03-20 10:50:00', 17, 31, 1),  -- Venta 31
(1, 3000.00, 3000.00, '2025-03-28 15:15:00', 25, 32, 1), -- Venta 32
(1, 470.00, 470.00, '2025-04-01 12:00:00', 19, 33, 1),  -- Venta 33
(1, 250.00, 250.00, '2025-04-08 16:40:00', 10, 34, 1),  -- Venta 34
(1, 1100.00, 1100.00, '2025-04-15 19:00:00', 36, 35, 1), -- Venta 35
(1, 69.90, 69.90, '2025-04-22 09:30:00', 5, 36, 1),     -- Venta 36
(1, 620.00, 620.00, '2025-04-30 14:00:00', 27, 37, 1),  -- Venta 37
(1, 190.00, 190.00, '2025-05-05 18:10:00', 18, 38, 1),  -- Venta 38
(1, 720.00, 720.00, '2025-05-12 11:50:00', 29, 39, 1),  -- Venta 39
(1, 1300.00, 1300.00, '2025-05-20 10:00:00', 34, 40, 1), -- Venta 40
(1, 280.00, 280.00, '2025-05-28 15:30:00', 21, 41, 1),  -- Venta 41
(1, 950.00, 950.00, '2025-06-03 12:40:00', 39, 42, 1),  -- Venta 42
(1, 560.00, 560.00, '2025-06-10 17:00:00', 24, 43, 1),  -- Venta 43
(1, 310.00, 310.00, '2025-06-15 11:00:00', 12, 44, 1),  -- Venta 44
(1, 1800.00, 1800.00, '2025-06-20 16:20:00', 38, 45, 1), -- Venta 45
(1, 400.00, 400.00, '2025-06-25 09:50:00', 30, 46, 1),  -- Venta 46
(1, 75.50, 75.50, '2025-06-01 10:10:00', 1, 47, 1);    -- Venta 47
-- Nuevos Detalles de Venta (Desde ID 51)
INSERT INTO detalle_ventas (cantidad, precio_unitario, total, fecha_creacion, id_producto, id_registro_venta, activo) VALUES
(2, 59.00, 118.00, '2025-01-05 11:15:00', 5, 48, 1),   -- Mouse Logitech
(1, 180.00, 180.00, '2025-01-12 16:40:00', 6, 49, 1),  -- Auriculares
(1, 180.00, 180.00, '2025-01-12 16:40:00', 19, 49, 1), -- Parlantes Z313
(1, 1300.00, 1300.00, '2025-01-20 13:10:00', 20, 50, 1), -- iPad 9
(2, 225.00, 450.00, '2025-01-28 18:20:00', 8, 51, 1),  -- Razer Viper
(1, 1300.00, 1300.00, '2025-01-28 18:20:00', 13, 51, 1); -- Xiaomi Note 12

-- Detalles adicionales desde ID 57
INSERT INTO detalle_ventas (cantidad, precio_unitario, total, fecha_creacion, id_producto, id_registro_venta, activo) VALUES
(1, 640.00, 640.00, '2025-01-09 10:50:00', 9, 52, 1),     -- Ryzen 5
(1, 735.00, 735.00, '2025-01-14 14:10:00', 13, 53, 1),    -- Xiaomi Note 12
(1, 225.00, 225.00, '2025-01-19 16:30:00', 8, 54, 1),     -- Razer V2
(1, 369.00, 369.00, '2025-01-19 16:30:00', 6, 54, 1),     -- Auriculares H120
(1, 739.00, 739.00, '2025-01-24 12:00:00', 2, 55, 1),     -- Laptop Lenovo 3
(2, 370.00, 740.00, '2025-01-24 12:00:00', 4, 55, 1),     -- SSD Kingston 480 GB
(1, 849.00, 849.00, '2025-01-30 17:45:00', 30, 56, 1),    -- Redmi 10
(1, 586.00, 586.00, '2025-01-30 17:45:00', 6, 56, 1);     -- Auriculares H120

-- Detalles desde ID 65
INSERT INTO detalle_ventas (cantidad, precio_unitario, total, fecha_creacion, id_producto, id_registro_venta, activo) VALUES
(1, 179.00, 179.00, '2025-01-31 09:00:00', 19, 57, 1),  -- Parlantes Z313
(1, 229.00, 229.00, '2025-01-31 11:30:00', 8, 58, 1),   -- Razer V2
(1, 470.00, 470.00, '2025-01-31 13:45:00', 19, 59, 1),  -- Parlantes Z313
(1, 485.00, 485.00, '2025-01-31 18:15:00', 30, 60, 1),  -- Redmi 10
(1, 490.00, 490.00, '2025-01-31 18:15:00', 31, 60, 1);  -- Redmi Note 13

INSERT INTO detalle_ventas (cantidad, precio_unitario, total, fecha_creacion, id_producto, id_registro_venta, activo) VALUES
(1, 109.00, 109.00, '2025-02-05 10:45:00', 6, 61, 1),      -- AURH120
(1, 849.00, 849.00, '2025-02-10 15:20:00', 12, 62, 1),     -- GALAXYA8
(1, 289.00, 289.00, '2025-02-14 12:30:00', 19, 63, 1),     -- Z313
(1, 729.00, 729.00, '2025-02-20 17:00:00', 13, 64, 1),     -- TABLET A8
(1, 229.00, 229.00, '2025-02-20 17:00:00', 8, 64, 1),      -- RAZERV2
(1, 849.00, 849.00, '2025-02-27 11:15:00', 10, 65, 1),     -- GTX1650
(1, 500.00, 500.00, '2025-02-27 11:15:00', 9, 65, 1);      -- RYZEN5

INSERT INTO detalle_ventas (cantidad, precio_unitario, total, fecha_creacion, id_producto, id_registro_venta, activo) VALUES
(1, 109.00, 109.00, '2025-02-16 16:40:00', 6, 66, 1),      -- AURH120
(1, 209.00, 209.00, '2025-02-16 16:40:00', 4, 66, 1),      -- KING480
(1, 785.00, 785.00, '2025-02-18 09:30:00', 3, 67, 1),      -- MON24CURVO
(1, 639.00, 639.00, '2025-02-22 14:00:00', 11, 68, 1),     -- EPSON L3250
(1, 639.00, 639.00, '2025-02-22 14:00:00', 11, 68, 1),     -- EPSON L3250 (repetido para usar stock acumulado)
(1, 849.00, 849.00, '2025-02-25 19:15:00', 30, 69, 1),     -- REDMI 10
(1, 130.00, 130.00, '2025-02-25 19:15:00', 15, 69, 1),     -- WEBCAM HD
(1, 849.00, 849.00, '2025-02-28 13:00:00', 10, 70, 1),     -- GTX1650
(1, 949.00, 949.00, '2025-02-28 13:00:00', 12, 70, 1);     -- GALAXY A8

INSERT INTO detalle_ventas (cantidad, precio_unitario, total, fecha_creacion, id_producto, id_registro_venta, activo) VALUES
(1, 109.00, 109.00, '2025-02-10 12:45:00', 6, 71, 1),       -- AURH120
(1, 179.00, 179.00, '2025-02-12 10:00:00', 19, 72, 1),      -- Z313
(1, 180.00, 180.00, '2025-02-12 10:00:00', 5, 72, 1),       -- LOGIM185
(1, 399.00, 399.00, '2025-02-14 17:20:00', 16, 73, 1),      -- MSI-B550
(1, 849.00, 849.00, '2025-02-20 08:10:00', 10, 74, 1),      -- GTX1650
(1, 201.00, 201.00, '2025-02-20 08:10:00', 4, 74, 1),       -- KING480
(1, 849.00, 849.00, '2025-02-27 19:40:00', 30, 75, 1);      -- REDMI 10

-- ## DETALLE DE VENTAS - MARZO
INSERT INTO detalle_ventas (cantidad, precio_unitario, total, fecha_creacion, id_producto, id_registro_venta, activo) VALUES
(1, 2500.00, 2500.00, '2025-03-02 10:15:00', 15, 76, 1),
(2, 600.00, 1200.00, '2025-03-05 11:30:00', 8, 77, 1),
(1, 899.00, 899.00, '2025-03-08 14:00:00', 33, 78, 1),
(1, 3250.00, 3250.00, '2025-03-11 16:45:00', 2, 79, 1),
(3, 150.00, 450.00, '2025-03-15 09:00:00', 28, 80, 1),
(2, 3400.00, 6800.00, '2025-03-18 12:20:00', 1, 81, 1),
(1, 180.00, 180.00, '2025-03-21 17:00:00', 39, 82, 1),
(1, 950.00, 950.00, '2025-03-24 18:30:00', 11, 83, 1),
(2, 1100.00, 2200.00, '2025-03-27 10:00:00', 22, 84, 1),
(1, 310.00, 310.00, '2025-03-30 13:45:00', 18, 85, 1);

INSERT INTO detalle_ventas (cantidad, precio_unitario, total, fecha_creacion, id_producto, id_registro_venta, activo) VALUES
(1, 5000.00, 5000.00, '2025-03-04 12:00:00', 34, 86, 1),
(2, 1000.00, 2000.00, '2025-03-06 15:20:00', 26, 87, 1),
(1, 1200.00, 1200.00, '2025-03-09 18:00:00', 12, 88, 1),
(2, 800.00, 1600.00, '2025-03-12 10:45:00', 14, 89, 1),
(1, 2500.00, 2500.00, '2025-03-16 11:30:00', 21, 90, 1),
(1, 1400.00, 1400.00, '2025-03-19 14:10:00', 11, 91, 1),
(2, 500.00, 1000.00, '2025-03-22 09:00:00', 16, 92, 1),
(1, 500.00, 500.00, '2025-03-25 16:55:00', 18, 93, 1),
(2, 3500.00, 7000.00, '2025-03-28 19:20:00', 2, 94, 1),
(1, 1000.00, 1000.00, '2025-03-31 12:00:00', 13, 95, 1);

-- ## DETALLE DE VENTAS - ABRIL
INSERT INTO detalle_ventas (cantidad, precio_unitario, total, fecha_creacion, id_producto, id_registro_venta, activo) VALUES
(2, 2250.00, 4500.00, '2025-04-01 09:30:00', 5, 96, 1),
(1, 750.00, 750.00, '2025-04-04 15:00:00', 12, 97, 1),
(1, 1250.00, 1250.00, '2025-04-07 12:10:00', 20, 98, 1),
(3, 100.00, 300.00, '2025-04-10 11:00:00', 35, 99, 1),
(1, 550.00, 550.00, '2025-04-14 10:45:00', 14, 100, 1),
(1, 7800.00, 7800.00, '2025-04-18 16:00:00', 40, 101, 1),
(1, 1999.00, 1999.00, '2025-04-21 13:25:00', 7, 102, 1),
(2, 310.00, 620.00, '2025-04-24 19:00:00', 24, 103, 1),
(1, 340.00, 340.00, '2025-04-27 11:50:00', 30, 104, 1),
(3, 160.00, 480.00, '2025-04-30 17:15:00', 27, 105, 1);

INSERT INTO detalle_ventas (cantidad, precio_unitario, total, fecha_creacion, id_producto, id_registro_venta, activo) VALUES
(2, 900.00, 1800.00, '2025-04-02 11:10:00', 8, 106, 1),
(1, 3000.00, 3000.00, '2025-04-05 13:00:00', 7, 107, 1),
(1, 700.00, 700.00, '2025-04-08 17:40:00', 9, 108, 1),
(1, 1900.00, 1900.00, '2025-04-11 10:00:00', 20, 109, 1),
(1, 1700.00, 1700.00, '2025-04-15 16:25:00', 22, 110, 1),
(3, 366.67, 1100.00, '2025-04-19 14:00:00', 29, 111, 1),
(1, 250.00, 250.00, '2025-04-22 11:50:00', 36, 112, 1),
(1, 800.00, 800.00, '2025-04-25 09:30:00', 10, 113, 1),
(1, 5000.00, 5000.00, '2025-04-28 15:00:00', 1, 114, 1),
(1, 550.00, 550.00, '2025-04-30 18:10:00', 30, 115, 1);

-- ## DETALLE DE VENTAS - MAYO
INSERT INTO detalle_ventas (cantidad, precio_unitario, total, fecha_creacion, id_producto, id_registro_venta, activo) VALUES
(1, 1800.00, 1800.00, '2025-05-02 14:20:00', 21, 116, 1),
(2, 280.00, 560.00, '2025-05-05 10:00:00', 31, 117, 1),
(1, 3200.00, 3200.00, '2025-05-08 11:45:00', 3, 118, 1),
(1, 275.00, 275.00, '2025-05-12 09:15:00', 17, 119, 1),
(3, 240.00, 720.00, '2025-05-15 16:30:00', 29, 120, 1),
(1, 4999.00, 4999.00, '2025-05-19 18:00:00', 4, 121, 1),
(1, 650.00, 650.00, '2025-05-22 12:00:00', 13, 122, 1),
(1, 110.00, 110.00, '2025-05-25 15:10:00', 38, 123, 1),
(2, 440.00, 880.00, '2025-05-28 10:55:00', 9, 124, 1),
(1, 1500.00, 1500.00, '2025-05-31 19:40:00', 25, 125, 1);

INSERT INTO detalle_ventas (cantidad, precio_unitario, total, fecha_creacion, id_producto, id_registro_venta, activo) VALUES
(1, 4000.00, 4000.00, '2025-05-03 10:15:00', 2, 126, 1),
(1, 5000.00, 5000.00, '2025-05-06 14:30:00', 34, 127, 1),
(2, 1000.00, 2000.00, '2025-05-09 11:00:00', 26, 128, 1),
(1, 1200.00, 1200.00, '2025-05-13 13:20:00', 12, 129, 1),
(2, 800.00, 1600.00, '2025-05-17 15:00:00', 14, 130, 1),
(2, 2500.00, 5000.00, '2025-05-20 10:50:00', 21, 131, 1),
(1, 1700.00, 1700.00, '2025-05-23 09:45:00', 22, 132, 1),
(1, 1400.00, 1400.00, '2025-05-26 12:30:00', 11, 133, 1),
(3, 500.00, 1500.00, '2025-05-29 17:00:00', 16, 134, 1),
(1, 500.00, 500.00, '2025-05-31 20:00:00', 18, 135, 1);

-- ## DETALLE DE VENTAS - JUNIO
INSERT INTO detalle_ventas (cantidad, precio_unitario, total, fecha_creacion, id_producto, id_registro_venta, activo) VALUES
(1, 999.00, 999.00, '2025-06-03 12:30:00', 19, 136, 1),
(3, 70.00, 210.00, '2025-06-06 14:00:00', 37, 137, 1),
(1, 4200.00, 4200.00, '2025-06-09 10:20:00', 6, 138, 1),
(1, 330.00, 330.00, '2025-06-12 17:00:00', 16, 139, 1),
(2, 340.00, 680.00, '2025-06-16 11:35:00', 23, 140, 1),
(2, 4750.00, 9500.00, '2025-06-19 09:50:00', 34, 141, 1),
(1, 150.00, 150.00, '2025-06-21 16:15:00', 36, 142, 1),
(1, 540.00, 540.00, '2025-06-24 13:00:00', 10, 143, 1),
(1, 1300.00, 1300.00, '2025-06-27 18:45:00', 26, 144, 1),
(1, 250.00, 250.00, '2025-06-30 20:00:00', 32, 145, 1);

INSERT INTO detalle_ventas (cantidad, precio_unitario, total, fecha_creacion, id_producto, id_registro_venta, activo) VALUES
(2, 900.00, 1800.00, '2025-06-02 09:10:00', 8, 146, 1),
(1, 3000.00, 3000.00, '2025-06-05 16:00:00', 7, 147, 1),
(2, 650.00, 1300.00, '2025-06-08 11:20:00', 9, 148, 1),
(1, 1900.00, 1900.00, '2025-06-11 14:50:00', 20, 149, 1),
(1, 400.00, 400.00, '2025-06-15 10:00:00', 29, 150, 1),
(2, 250.00, 500.00, '2025-06-18 12:45:00', 36, 151, 1),
(2, 800.00, 1600.00, '2025-06-21 18:00:00', 10, 152, 1),
(1, 5000.00, 5000.00, '2025-06-24 15:30:00', 1, 153, 1),
(1, 550.00, 550.00, '2025-06-27 13:00:00', 30, 154, 1),
(1, 1000.00, 1000.00, '2025-06-30 19:00:00', 13, 155, 1);

-- DETALLE DE VENTAS DE PRODUCTOS FALTANTES

-- ENERO
INSERT INTO detalle_ventas (cantidad, precio_unitario, total, fecha_creacion, id_producto, id_registro_venta, activo) VALUES
(1, 6000.00, 6000.00, '2025-01-05 10:11:00', 27, 156, 1),
(2, 1750.00, 3500.00, '2025-01-09 14:22:00', 10, 157, 1),
(1, 1800.00, 1800.00, '2025-01-13 11:33:00', 22, 158, 1),
(1, 2500.00, 2500.00, '2025-01-17 16:44:00', 21, 159, 1),
(3, 500.00, 1500.00, '2025-01-21 09:33:00', 20, 160, 1),
(1, 8000.00, 8000.00, '2025-01-25 13:42:00', 2, 161, 1),
(2, 450.00, 900.00, '2025-01-29 17:25:00', 29, 162, 1);

-- FEBRERO
INSERT INTO detalle_ventas (cantidad, precio_unitario, total, fecha_creacion, id_producto, id_registro_venta, activo) VALUES
(2, 1100.00, 2200.00, '2025-02-03 11:22:00', 13, 163, 1),
(1, 4800.00, 4800.00, '2025-02-07 15:54:00', 21, 164, 1),
(1, 1100.00, 1100.00, '2025-02-11 10:32:00', 12, 165, 1),
(1, 7000.00, 7000.00, '2025-02-15 14:23:00', 2, 166, 1),
(3, 433.33, 1300.00, '2025-02-19 09:21:00', 14, 167, 1),
(1, 3000.00, 3000.00, '2025-02-23 12:11:00', 10, 168, 1),
(2, 300.00, 600.00, '2025-02-27 16:59:00', 29, 169, 1);

-- MARZO
INSERT INTO detalle_ventas (cantidad, precio_unitario, total, fecha_creacion, id_producto, id_registro_venta, activo) VALUES
(1, 5500.00, 5500.00, '2025-03-04 12:11:00', 27, 170, 1),
(2, 1400.00, 2800.00, '2025-03-06 15:13:00', 10, 171, 1),
(1, 1500.00, 1500.00, '2025-03-09 18:15:00', 22, 172, 1),
(2, 1000.00, 2000.00, '2025-03-12 10:17:00', 21, 173, 1),
(1, 3000.00, 3000.00, '2025-03-16 11:44:00', 12, 174, 1),
(1, 1800.00, 1800.00, '2025-03-19 14:26:00', 20, 175, 1),
(3, 400.00, 1200.00, '2025-03-22 09:43:00', 14, 176, 1);

-- ABRIL
INSERT INTO detalle_ventas (cantidad, precio_unitario, total, fecha_creacion, id_producto, id_registro_venta, activo) VALUES
(2, 1250.00, 2500.00, '2025-04-02 11:43:00', 20, 177, 1),
(1, 4000.00, 4000.00, '2025-04-05 13:22:00', 1, 178, 1),
(1, 1000.00, 1000.00, '2025-04-11 17:11:00', 13, 179, 1),
(1, 2200.00, 2200.00, '2025-04-14 10:24:00', 27, 180, 1),
(1, 1900.00, 1900.00, '2025-04-16 16:22:00', 22, 181, 1),
(3, 500.00, 1500.00, '2025-04-18 14:13:00', 30, 182, 1),
(1, 400.00, 400.00, '2025-04-25 11:59:00', 29, 183, 1);

-- MAYO
INSERT INTO detalle_ventas (cantidad, precio_unitario, total, fecha_creacion, id_producto, id_registro_venta, activo) VALUES
(1, 4500.00, 4500.00, '2025-05-01 10:15:00', 1, 184, 1),
(1, 6000.00, 6000.00, '2025-05-04 14:30:00', 2, 185, 1),
(2, 1250.00, 2500.00, '2025-05-07 11:00:00', 12, 186, 1),
(1, 1500.00, 1500.00, '2025-05-11 13:20:00', 13, 187, 1),
(2, 1100.00, 2200.00, '2025-05-16 15:00:00', 14, 188, 1),
(2, 2750.00, 5500.00, '2025-05-24 10:50:00', 20, 189, 1),
(1, 2000.00, 2000.00, '2025-05-26 09:45:00', 21, 190, 1);

-- JUNIO
INSERT INTO detalle_ventas (cantidad, precio_unitario, total, fecha_creacion, id_producto, id_registro_venta, activo) VALUES
(2, 1400.00, 2800.00, '2025-06-04 09:10:00', 22, 191, 1),
(1, 4500.00, 4500.00, '2025-06-06 16:00:00', 21, 192, 1),
(2, 900.00, 1800.00, '2025-06-09 11:20:00', 20, 193, 1),
(1, 2500.00, 2500.00, '2025-06-15 14:50:00', 14, 194, 1),
(1, 600.00, 600.00, '2025-06-19 10:00:00', 13, 195, 1),
(2, 400.00, 800.00, '2025-06-22 12:45:00', 12, 196, 1),
(2, 1000.00, 2000.00, '2025-06-30 18:00:00', 29, 197, 1);

-- OTROS
INSERT INTO detalle_ventas (cantidad, precio_unitario, total, fecha_creacion, id_producto, id_registro_venta, activo) VALUES
(1, 3800.00, 3800.00, '2025-01-03 09:30:01', 21, 197, 1),
(2, 1250.00, 2500.00, '2025-01-08 14:15:04', 12, 198, 1),
(1, 1700.00, 1700.00, '2025-01-15 11:00:02', 1, 199, 1),
(1, 4200.00, 4200.00, '2025-01-20 16:45:43', 2, 200, 1),
(3, 633.33, 1900.00, '2025-01-27 10:00:11', 13, 201, 1);

INSERT INTO detalle_ventas (cantidad, precio_unitario, total, fecha_creacion, id_producto, id_registro_venta, activo) VALUES
(2, 1050.00, 2100.00, '2025-02-04 12:00:21', 14, 202, 1),
(1, 3600.00, 3600.00, '2025-02-09 15:30:22', 20, 203, 1),
(1, 1400.00, 1400.00, '2025-02-14 09:45:12', 21, 204, 1),
(1, 2900.00, 2900.00, '2025-02-21 13:10:45', 1, 205, 1),
(3, 533.33, 1600.00, '2025-02-26 17:50:22', 12, 206, 1);

-- #####################################################
-- ## 5. GARANTIAS
-- #####################################################
INSERT INTO garantias (id_detalle_venta, inicio_garantia, fin_garantia, activo) VALUES
(1, '2023-01-15', '2024-01-14', 0), (2, '2023-02-20', '2024-02-19', 0),
(3, '2023-03-10', '2024-03-09', 0), (4, '2023-04-16', '2024-04-15', 0),
(5, '2023-05-05', '2024-05-04', 0), (6, '2023-06-21', '2025-06-20', 1), -- 2 años
(7, '2023-07-08', '2024-05-07', 0), (8, '2023-08-19', '2024-08-18', 0),
(9, '2023-09-30', '2024-06-29', 0), (10, '2024-10-14', '2025-10-13', 1),
(11, '2024-01-15', '2025-01-14', 1), (12, '2024-02-05', '2025-02-04', 1),
(13, '2024-03-10', '2025-03-09', 1), (14, '2024-04-15', '2026-04-14', 1), -- 2 años
(15, '2024-05-20', '2025-05-19', 1), (16, '2024-06-02', '2025-06-01', 1),
(17, '2024-07-08', '2025-06-07', 1), (18, '2024-08-15', '2025-08-14', 1),
(19, '2025-09-24', '2026-06-23', 1), (20, '2025-10-03', '2026-10-02', 1),
(21, '2025-01-10', '2026-01-09', 1), (22, '2025-01-18', '2026-01-17', 1),
(23, '2025-01-25', '2026-01-24', 1), (24, '2025-02-02', '2026-02-01', 1);

-- #####################################################
-- ## 6. RECLAMO DE GARANTIAS (20 Reclamos)
-- #####################################################
INSERT INTO reclamo_garantias (id_garantia, descripcion, estado, activo, fecha_reclamo) VALUES
(6, 'La laptop se sobrecalienta excesivamente.', 'PENDIENTE', 1, '2024-01-15 14:30:00'),
(14, 'La tarjeta gráfica presenta artifacts visuales.', 'RESUELTO', 1, '2025-03-20 16:00:00'),
(21, 'El producto no retiene la carga.', 'RESUELTO', 1, '2025-05-10 09:00:00'),
(15, 'El teclado tiene una tecla que no funciona (letra A)', 'PENDIENTE', 1, '2025-06-01 11:20:00'),
(2, 'El celular no encendió (fuera de garantía).', 'RECHAZADO', 1, '2024-05-10 09:00:00'),
(10, 'Falla intermitente en el audio.', 'RESUELTO', 1, '2025-02-01 10:00:00'),
(15, 'La pantalla muestra una línea de pixeles muertos.', 'RESUELTO', 1, '2025-04-11 12:00:00'),
(20, 'El empaque llegó abierto, pero el producto funciona.', 'RECHAZADO', 1, '2025-05-01 18:00:00'),
(22, 'La batería dura menos de lo especificado.', 'PENDIENTE', 1, '2025-06-20 15:00:00'),
(19, 'El software se reinicia solo.', 'RESUELTO', 1, '2025-07-01 09:30:00');

