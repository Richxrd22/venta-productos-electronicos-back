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



-- Enero
INSERT INTO ingreso_stocks (id, id_proveedor, fecha_ingreso, tipo_documento, numero_documento, observaciones) VALUES
(24, 3, '2025-01-10', 'Factura', 'FAC-0806', 'Ingreso de equipos principales a inicios de año'),
(25, 4, '2025-01-15', 'Boleta', 'BOL-0807', 'Ingreso de productos de consumo rápido'),
(26, 6, '2025-01-25', 'Guía', 'GUI-0808', 'Guía para abastecimiento inicial');

-- Febrero
INSERT INTO ingreso_stocks (id, id_proveedor, fecha_ingreso, tipo_documento, numero_documento, observaciones) VALUES
(27, 1, '2025-02-12', 'Factura', 'FAC-0801', 'Ingreso temprano del año para laptops y SSDs'),
(28, 3, '2025-02-20', 'Boleta', 'BOL-0802', 'Ingreso de periféricos básicos'),
(29, 5, '2025-02-27', 'Guía', 'GUI-0803', 'Complemento de stock inicial del año');

-- Marzo
INSERT INTO ingreso_stocks (id, id_proveedor, fecha_ingreso, tipo_documento, numero_documento, observaciones) VALUES
(30, 4, '2025-03-08', 'Boleta', 'BOL-0804', 'Ingreso de componentes de alto rendimiento'),
(31, 2, '2025-03-15', 'Factura', 'FAC-0805', 'Reposición de tablets y auriculares'),
(32, 6, '2025-03-25', 'Guía', 'GUI-0806', 'Ingreso mixto para campaña escolar');

-- Abril
INSERT INTO ingreso_stocks (id, id_proveedor, fecha_ingreso, tipo_documento, numero_documento, observaciones) VALUES
(33, 2, '2025-04-15', 'Factura', 'FAC-0901', 'Ingreso inicial de temporada Q2'),
(34, 4, '2025-04-22', 'Boleta', 'BOL-0902', 'Carga básica de tablets y accesorios'),
(35, 6, '2025-04-28', 'Guía', 'GUI-0903', 'Reposición de componentes externos');

-- Mayo
INSERT INTO ingreso_stocks (id, id_proveedor, fecha_ingreso, tipo_documento, numero_documento, observaciones) VALUES
(36, 1, '2025-05-10', 'Factura', 'FAC-0904', 'Reabastecimiento de audio y SSDs'),
(37, 3, '2025-05-18', 'Boleta', 'BOL-0905', 'Ingreso de gadgets económicos'),
(38, 5, '2025-05-28', 'Guía', 'GUI-0906', 'Ingreso adicional de computadoras y periféricos');

-- Junio
INSERT INTO ingreso_stocks (id, id_proveedor, fecha_ingreso, tipo_documento, numero_documento, observaciones) VALUES
(39, 6, '2025-06-05', 'Boleta', 'BOL-0907', 'Ingreso de productos gaming'),
(40, 4, '2025-06-15', 'Guía', 'GUI-0908', 'Reabastecimiento de stock general'),
(41, 2, '2025-06-20', 'Factura', 'FAC-0909', 'Ingreso final previo a campaña julio');

-- ENERO
INSERT INTO ingreso_stocks (id, id_proveedor, fecha_ingreso, tipo_documento, numero_documento, observaciones) VALUES
(42, 7, '2025-01-07', 'Factura', 'FAC-0910', 'Ingreso inicial del año de periféricos'),
(43, 9, '2025-01-20', 'Boleta', 'BOL-0911', 'Reposición de tablets económicas'),
(44, 10, '2025-01-30', 'Guía', 'GUI-0912', 'Ingreso de accesorios básicos');

-- FEBRERO
INSERT INTO ingreso_stocks (id, id_proveedor, fecha_ingreso, tipo_documento, numero_documento, observaciones) VALUES
(45, 8, '2025-02-05', 'Factura', 'FAC-0913', 'Ingreso temprano de equipos all-in-one'),
(46, 12, '2025-02-18', 'Boleta', 'BOL-0914', 'Periféricos para zona sur'),
(47, 14, '2025-02-24', 'Guía', 'GUI-0915', 'Ingreso de gadgets para temporada');

-- MARZO
INSERT INTO ingreso_stocks (id, id_proveedor, fecha_ingreso, tipo_documento, numero_documento, observaciones) VALUES
(48, 13, '2025-03-05', 'Factura', 'FAC-0916', 'Ingreso de auriculares variados'),
(49, 11, '2025-03-14', 'Boleta', 'BOL-0917', 'Impresoras y monitores'),
(50, 16, '2025-03-29', 'Guía', 'GUI-0918', 'Carga mixta de marzo');

-- ABRIL
INSERT INTO ingreso_stocks (id, id_proveedor, fecha_ingreso, tipo_documento, numero_documento, observaciones) VALUES
(51, 15, '2025-04-02', 'Factura', 'FAC-0919', 'Reabastecimiento de laptops gama media'),
(52, 18, '2025-04-19', 'Boleta', 'BOL-0920', 'Stock básico de celulares'),
(53, 19, '2025-04-30', 'Guía', 'GUI-0921', 'Ingreso mensual de abril');

-- MAYO
INSERT INTO ingreso_stocks (id, id_proveedor, fecha_ingreso, tipo_documento, numero_documento, observaciones) VALUES
(54, 20, '2025-05-03', 'Factura', 'FAC-0922', 'Ingreso mixto de componentes'),
(55, 17, '2025-05-14', 'Boleta', 'BOL-0923', 'Accesorios para equipos de oficina'),
(56, 6, '2025-05-27', 'Guía', 'GUI-0924', 'Lote adicional por alta demanda');

-- JUNIO
INSERT INTO ingreso_stocks (id, id_proveedor, fecha_ingreso, tipo_documento, numero_documento, observaciones) VALUES
(57, 5, '2025-06-02', 'Factura', 'FAC-0925', 'Ingreso de routers y placas madre'),
(58, 1, '2025-06-17', 'Boleta', 'BOL-0926', 'Reposición de impresoras y teclados'),
(59, 2, '2025-06-28', 'Guía', 'GUI-0927', 'Ingreso de equipos móviles');

-- Enero (Complemento)
INSERT INTO ingreso_stocks (id, id_proveedor, fecha_ingreso, tipo_documento, numero_documento, observaciones) VALUES
(60, 7, '2025-01-05', 'Factura', 'FAC-0810', 'Ingreso de accesorios básicos de red'),
(61, 12, '2025-01-06', 'Boleta', 'BOL-0811', 'Reposición de unidades de almacenamiento SSD'),
(62, 18, '2025-01-08', 'Guía', 'GUI-0812', 'Ingreso parcial de productos portátiles'),
(63, 5, '2025-01-09', 'Factura', 'FAC-0813', 'Ingreso de tablets para stock inicial'),
(64, 9, '2025-01-11', 'Boleta', 'BOL-0814', 'Ingreso de periféricos económicos'),
(65, 14, '2025-01-12', 'Guía', 'GUI-0815', 'Ingreso mixto de gadgets'),
(66, 10, '2025-01-14', 'Factura', 'FAC-0816', 'Ingreso de routers y cables'),
(67, 8, '2025-01-17', 'Boleta', 'BOL-0817', 'Carga inicial de parlantes'),
(68, 6, '2025-01-18', 'Factura', 'FAC-0818', 'Ingreso general de tecnología'),
(69, 1, '2025-01-20', 'Guía', 'GUI-0819', 'Complemento de ingreso técnico'),
(70, 13, '2025-01-22', 'Factura', 'FAC-0820', 'Equipos de computación para reventa'),
(71, 11, '2025-01-23', 'Boleta', 'BOL-0821', 'Ingreso de webcams y micrófonos'),
(72, 15, '2025-01-27', 'Factura', 'FAC-0822', 'Ingreso de laptops económicas'),
(73, 17, '2025-01-29', 'Guía', 'GUI-0823', 'Ingreso general para primer trimestre');
-- Ingresos de stock adicionales para enero
INSERT INTO ingreso_stocks (id, id_proveedor, fecha_ingreso, tipo_documento, numero_documento, observaciones) VALUES
(74, 7, '2025-01-05', 'Factura', 'FAC-0810', 'Primer ingreso del año para periféricos'),
(75, 10, '2025-01-07', 'Boleta', 'BOL-0811', 'Ingreso básico para equipos de oficina'),
(76, 9, '2025-01-08', 'Guía', 'GUI-0812', 'Reposición rápida de monitores'),
(77, 13, '2025-01-12', 'Factura', 'FAC-0813', 'Ingreso adicional de componentes internos'),
(78, 14, '2025-01-14', 'Boleta', 'BOL-0814', 'Ingreso de tablets y accesorios móviles'),
(79, 11, '2025-01-16', 'Guía', 'GUI-0815', 'Ingreso mixto de productos electrónicos'),
(80, 12, '2025-01-18', 'Factura', 'FAC-0816', 'Abastecimiento de routers y cámaras'),
(81, 15, '2025-01-19', 'Boleta', 'BOL-0817', 'Ingreso para promoción de inicio de año'),
(82, 16, '2025-01-21', 'Guía', 'GUI-0818', 'Reposición de stock para celulares y audio'),
(83, 17, '2025-01-22', 'Factura', 'FAC-0819', 'Ingreso de accesorios gamer'),
(84, 18, '2025-01-23', 'Boleta', 'BOL-0820', 'Ingreso adicional de productos básicos'),
(85, 19, '2025-01-26', 'Guía', 'GUI-0821', 'Stock de respaldo para zona sur'),
(86, 20, '2025-01-27', 'Factura', 'FAC-0822', 'Ingreso para campaña escolar'),
(87, 8, '2025-01-28', 'Boleta', 'BOL-0823', 'Ingreso de productos de alta demanda'),
(88, 1, '2025-01-29', 'Guía', 'GUI-0824', 'Ingreso mixto para Lima y provincias'),
(89, 2, '2025-01-30', 'Factura', 'FAC-0825', 'Stock de inicio para febrero'),
(90, 5, '2025-01-31', 'Boleta', 'BOL-0826', 'Ingreso final de enero'),
(91, 6, '2025-01-31', 'Guía', 'GUI-0827', 'Refuerzo de stock para periféricos'),
(92, 3, '2025-01-31', 'Factura', 'FAC-0828', 'Ingreso de computadoras económicas'),
(93, 4, '2025-01-31', 'Boleta', 'BOL-0829', 'Ingreso de cámaras web y audífonos');

-- Ingresos adicionales para febrero
INSERT INTO ingreso_stocks (id, id_proveedor, fecha_ingreso, tipo_documento, numero_documento, observaciones) VALUES
(94, 7, '2025-02-02', 'Factura', 'FAC-0830', 'Ingreso de audífonos y webcams'),
(95, 8, '2025-02-03', 'Boleta', 'BOL-0831', 'Reabastecimiento de periféricos'),
(96, 9, '2025-02-04', 'Guía', 'GUI-0832', 'Ingreso mixto para zona sur'),
(97, 10, '2025-02-06', 'Factura', 'FAC-0833', 'Ingreso para campaña escolar'),
(98, 11, '2025-02-08', 'Boleta', 'BOL-0834', 'Ingreso económico para zonas rurales'),
(99, 12, '2025-02-10', 'Guía', 'GUI-0835', 'Stock de inicio escolar'),
(100, 13, '2025-02-15', 'Factura', 'FAC-0836', 'Reemplazo de tablets y smartphones'),
(101, 14, '2025-02-17', 'Boleta', 'BOL-0837', 'Ingreso rápido de accesorios gamer'),
(102, 15, '2025-02-22', 'Guía', 'GUI-0838', 'Ingreso de monitores y memorias RAM'),
(103, 16, '2025-02-24', 'Factura', 'FAC-0839', 'Ingreso de impresoras y SSDs'),
(104, 17, '2025-02-26', 'Boleta', 'BOL-0840', 'Ingreso variado para zona norte'),
(105, 18, '2025-02-28', 'Guía', 'GUI-0841', 'Ingreso final del mes de febrero');

INSERT INTO ingreso_stocks (id, id_proveedor, fecha_ingreso, tipo_documento, numero_documento, observaciones) VALUES
(106, 14, '2025-03-03', 'Factura', 'FAC-0810', 'Ingreso por campaña escolar regional'),
(107, 9, '2025-03-04', 'Boleta', 'BOL-0811', 'Reposición rápida de accesorios'),
(108, 2, '2025-03-05', 'Guía', 'GUI-0812', 'Guía de llegada para laptops escolares'),
(109, 19, '2025-03-06', 'Factura', 'FAC-0813', 'Ingreso de tablets y teclados'),
(110, 13, '2025-03-09', 'Guía', 'GUI-0814', 'Productos escolares enviados a almacén'),
(111, 6, '2025-03-10', 'Boleta', 'BOL-0815', 'Reposición de stock gamer'),
(112, 4, '2025-03-12', 'Factura', 'FAC-0816', 'Ingreso mixto para venta inmediata'),
(113, 10, '2025-03-16', 'Boleta', 'BOL-0817', 'Ingreso pequeño de periféricos'),
(114, 15, '2025-03-17', 'Guía', 'GUI-0818', 'Ingreso de tablets y monitores'),
(115, 17, '2025-03-19', 'Factura', 'FAC-0819', 'Compra programada de SSDs y auriculares'),
(116, 3, '2025-03-21', 'Boleta', 'BOL-0820', 'Carga económica para campaña escolar'),
(117, 8, '2025-03-23', 'Guía', 'GUI-0821', 'Ingreso desde proveedor andino'),
(118, 20, '2025-03-26', 'Factura', 'FAC-0822', 'Ingreso de alto valor en laptops'),
(119, 5, '2025-03-28', 'Boleta', 'BOL-0823', 'Ingreso final de marzo escolar');

-- Abril (Ingresos adicionales)
INSERT INTO ingreso_stocks (id, id_proveedor, fecha_ingreso, tipo_documento, numero_documento, observaciones) VALUES
(120, 7, '2025-04-01', 'Factura', 'FAC-0924', 'Ingreso de productos escolares'),
(121, 3, '2025-04-02', 'Boleta', 'BOL-0925', 'Carga de periféricos básicos'),
(122, 15, '2025-04-03', 'Guía', 'GUI-0926', 'Ingreso anticipado de stock'),
(123, 2, '2025-04-04', 'Factura', 'FAC-0927', 'Ingreso de impresoras y SSDs'),
(124, 11, '2025-04-05', 'Boleta', 'BOL-0928', 'Periféricos para promoción escolar'),
(125, 10, '2025-04-06', 'Guía', 'GUI-0929', 'Monitores y routers escolares'),
(126, 4, '2025-04-08', 'Factura', 'FAC-0930', 'Audio y computadoras económicas'),
(127, 12, '2025-04-09', 'Boleta', 'BOL-0931', 'Accesorios ligeros para colegios'),
(128, 9, '2025-04-10', 'Guía', 'GUI-0932', 'Tablets y gadgets escolares'),
(129, 16, '2025-04-11', 'Factura', 'FAC-0933', 'Ingreso parcial para abril'),
(130, 5, '2025-04-12', 'Boleta', 'BOL-0934', 'Complemento para stock general'),
(131, 13, '2025-04-13', 'Guía', 'GUI-0935', 'Ingreso por convenio regional'),
(132, 8, '2025-04-15', 'Factura', 'FAC-0936', 'Ingreso Q2 zona sur'),
(133, 14, '2025-04-16', 'Boleta', 'BOL-0937', 'Equipos multimedia para colegios'),
(134, 17, '2025-04-17', 'Guía', 'GUI-0938', 'Reposición por alta demanda'),
(135, 18, '2025-04-18', 'Factura', 'FAC-0939', 'Ingreso con financiamiento'),
(136, 6, '2025-04-19', 'Boleta', 'BOL-0940', 'Ingreso desde distribuidor Surco'),
(137, 20, '2025-04-20', 'Guía', 'GUI-0941', 'Ingreso de carga liviana'),
(138, 1, '2025-04-21', 'Factura', 'FAC-0942', 'Ingreso a almacén central'),
(139, 19, '2025-04-23', 'Boleta', 'BOL-0943', 'Reabastecimiento escolar'),
(140, 7, '2025-04-24', 'Guía', 'GUI-0944', 'Campaña abril cierre parcial'),
(141, 10, '2025-04-25', 'Factura', 'FAC-0945', 'Entrega final de abril'),
(142, 3, '2025-04-27', 'Boleta', 'BOL-0946', 'Ingreso menor gadgets'),
(143, 11, '2025-04-29', 'Guía', 'GUI-0947', 'Última carga abril');

-- Mayo (complemento hasta 20 ingresos)
INSERT INTO ingreso_stocks (id, id_proveedor, fecha_ingreso, tipo_documento, numero_documento, observaciones) VALUES
(144, 12, '2025-05-03', 'Boleta', 'BOL-0907', 'Ingreso de productos básicos para almacén'),
(145, 1,  '2025-05-04', 'Factura', 'FAC-0908', 'Ingreso de componentes tecnológicos'),
(146, 3,  '2025-05-06', 'Guía',    'GUI-0909', 'Reposición de tablets de media gama'),
(147, 5,  '2025-05-08', 'Factura', 'FAC-0910', 'Carga mixta por campaña escolar'),
(148, 14, '2025-05-09', 'Boleta', 'BOL-0911', 'Ingreso de periféricos y accesorios'),
(149, 6,  '2025-05-11', 'Guía',    'GUI-0912', 'Lote complementario de SSDs'),
(150, 19, '2025-05-12', 'Factura', 'FAC-0913', 'Ingreso de routers y dispositivos'),
(151, 17, '2025-05-14', 'Boleta', 'BOL-0914', 'Reposición de stock gamer'),
(152, 9,  '2025-05-17', 'Factura', 'FAC-0915', 'Ingreso general para ventas de mayo'),
(153, 18, '2025-05-19', 'Boleta', 'BOL-0916', 'Lote económico para ventas flash'),
(154, 7,  '2025-05-20', 'Guía',    'GUI-0917', 'Reabastecimiento de inventario'),
(155, 2,  '2025-05-22', 'Factura', 'FAC-0918', 'Ingreso por convenio institucional'),
(156, 16, '2025-05-25', 'Boleta', 'BOL-0919', 'Entrega de accesorios y laptops'),
(157, 8,  '2025-05-29', 'Guía',    'GUI-0920', 'Cierre de lote mensual de mayo');

-- Junio (complemento desde id 158 hasta 172 para completar 25 ingresos)
INSERT INTO ingreso_stocks (id, id_proveedor, fecha_ingreso, tipo_documento, numero_documento, observaciones) VALUES
(158, 1, '2025-06-01', 'Factura', 'FAC-0921', 'Ingreso mensual para línea económica'),
(159, 2, '2025-06-02', 'Boleta', 'BOL-0922', 'Reabastecimiento inicial de junio'),
(160, 3, '2025-06-03', 'Guía', 'GUI-0923', 'Ingreso de periféricos populares'),
(161, 4, '2025-06-06', 'Factura', 'FAC-0924', 'Ingreso intermedio por campaña tech'),
(162, 5, '2025-06-07', 'Boleta', 'BOL-0925', 'Lote mixto gamer y hogar'),
(163, 6, '2025-06-08', 'Guía', 'GUI-0926', 'Guía complementaria para SSDs'),
(164, 7, '2025-06-10', 'Factura', 'FAC-0927', 'Ingreso focalizado en computadoras'),
(165, 8, '2025-06-12', 'Boleta', 'BOL-0928', 'Carga liviana para pruebas internas'),
(166, 9, '2025-06-16', 'Guía', 'GUI-0929', 'Ingreso regular de stock mensual'),
(167, 10, '2025-06-17', 'Factura', 'FAC-0930', 'Reposición de tablets y smartphones'),
(168, 11, '2025-06-19', 'Boleta', 'BOL-0931', 'Ingreso económico para ventas flash'),
(169, 12, '2025-06-22', 'Guía', 'GUI-0932', 'Guía técnica de actualización de productos'),
(170, 13, '2025-06-24', 'Factura', 'FAC-0933', 'Último lote técnico del mes'),
(171, 14, '2025-06-27', 'Boleta', 'BOL-0934', 'Reingreso de gadgets solicitados'),
(172, 15, '2025-06-29', 'Guía', 'GUI-0935', 'Guía de cierre mensual de junio');

-- Enero
INSERT INTO detalle_ingresos (id, id_ingreso, id_producto, codigo_lote, cantidad, precio_unitario, activo) VALUES
(61, 24, 2, 'L-20250110-LENOVO3', 6, 1950.00, 1),
(62, 24, 4, 'L-20250110-KING480', 12, 199.00, 1),
(63, 25, 5, 'L-20250115-LOGIM185', 30, 59.90, 1),
(64, 25, 20, 'L-20250115-IPAD9', 2, 1550.00, 1),
(65, 26, 8, 'L-20250125-RAZERV2', 4, 229.00, 1),
(66, 26, 19, 'L-20250125-Z313', 5, 179.00, 1);

-- Febrero
INSERT INTO detalle_ingresos (id, id_ingreso, id_producto, codigo_lote, cantidad, precio_unitario, activo) VALUES
(67, 27, 5, 'L-20250212-LOGIM185', 25, 55.00, 1),
(68, 27, 11, 'L-20250212-EPSL3250', 5, 639.00, 1),
(69, 28, 9, 'L-20250220-RYZEN5', 6, 629.00, 1),
(70, 28, 10, 'L-20250220-GTX1650', 3, 1090.00, 1),
(71, 29, 12, 'L-20250227-GALAXYA8', 7, 849.00, 1),
(72, 29, 6, 'L-20250227-AURH120', 12, 109.00, 1);

-- Marzo
INSERT INTO detalle_ingresos (id, id_ingreso, id_producto, codigo_lote, cantidad, precio_unitario, activo) VALUES
(73, 30, 7, 'L-20250308-ROUTERC6', 6, 149.00, 1),
(74, 30, 17, 'L-20250308-DELLAIO', 2, 2899.00, 1),
(75, 31, 14, 'L-20250315-HUAWEIFIT2', 5, 599.00, 1),
(76, 31, 15, 'L-20250315-WEBHD3000', 6, 139.00, 1),
(77, 32, 3, 'L-20250325-MON24CURVO', 7, 779.00, 1),
(78, 32, 23, 'L-20250325-GIGARAM', 10, 159.00, 1);

-- Abril
INSERT INTO detalle_ingresos (id, id_ingreso, id_producto, codigo_lote, cantidad, precio_unitario, activo) VALUES
(79, 33, 1, 'L-20250415-HP15', 5, 2399.00, 1),
(80, 33, 6, 'L-20250415-AURH120', 10, 119.00, 1),
(81, 34, 20, 'L-20250422-IPAD9', 3, 1549.00, 1),
(82, 34, 30, 'L-20250422-REDMI10', 10, 489.00, 1),
(83, 35, 13, 'L-20250428-TABLET-A8', 6, 899.00, 1),
(84, 35, 19, 'L-20250428-LOGI-Z313', 7, 189.00, 1);

-- Mayo
INSERT INTO detalle_ingresos (id, id_ingreso, id_producto, codigo_lote, cantidad, precio_unitario, activo) VALUES
(85, 36, 19, 'L-20250510-Z313', 8, 179.00, 1),
(86, 36, 4, 'L-20250510-KING480', 10, 199.00, 1),
(87, 37, 2, 'L-20250518-LENOVO3', 7, 1899.00, 1),
(88, 37, 5, 'L-20250518-LOGIM185', 20, 59.00, 1),
(89, 38, 9, 'L-20250528-RYZEN5', 8, 649.00, 1),
(90, 38, 8, 'L-20250528-RAZERV2', 5, 219.00, 1);

-- Junio
INSERT INTO detalle_ingresos (id, id_ingreso, id_producto, codigo_lote, cantidad, precio_unitario, activo) VALUES
(91, 39, 15, 'L-20250605-WEBHD3000', 6, 139.00, 1),
(92, 39, 13, 'L-20250605-XIAOMINOTE12', 8, 739.00, 1),
(93, 40, 16, 'L-20250615-MSI-B550', 5, 399.00, 1),
(94, 40, 6, 'L-20250615-AURH120', 10, 119.00, 1),
(95, 41, 1, 'L-20250620-HP15', 4, 2390.00, 1),
(96, 41, 4, 'L-20250620-KING480', 10, 189.00, 1);

-- DETALLE ENERO
INSERT INTO detalle_ingresos (id, id_ingreso, id_producto, codigo_lote, cantidad, precio_unitario, activo) VALUES
(97, 42, 5, 'L-20250107-LOGIM185', 20, 58.90, 1),
(98, 42, 6, 'L-20250107-AURH120', 15, 110.00, 1),
(99, 43, 13, 'L-20250120-XIAOMINOTE12', 5, 729.00, 1),
(100, 43, 20, 'L-20250120-IPAD9', 3, 1499.00, 1),
(101, 44, 4, 'L-20250130-KING480', 8, 189.00, 1),
(102, 44, 19, 'L-20250130-Z313', 6, 179.00, 1),

-- DETALLE FEBRERO
(103, 45, 17, 'L-20250205-DELLAIO', 3, 2850.00, 1),
(104, 45, 3, 'L-20250205-MON24CURVO', 6, 789.00, 1),
(105, 46, 15, 'L-20250218-WEBHD3000', 5, 135.00, 1),
(106, 46, 6, 'L-20250218-AURH120', 10, 109.00, 1),
(107, 47, 30, 'L-20250224-REDMI10', 6, 459.00, 1),
(108, 47, 8, 'L-20250224-RAZERV2', 3, 215.00, 1),

-- DETALLE MARZO
(109, 48, 6, 'L-20250305-AURH120', 15, 105.00, 1),
(110, 48, 7, 'L-20250305-ROUTERC6', 5, 155.00, 1),
(111, 49, 11, 'L-20250314-EPSL3250', 4, 640.00, 1),
(112, 49, 3, 'L-20250314-MON24CURVO', 5, 799.00, 1),
(113, 50, 23, 'L-20250329-GIGARAM', 10, 165.00, 1),
(114, 50, 10, 'L-20250329-GTX1650', 2, 1080.00, 1),

-- DETALLE ABRIL
(115, 51, 1, 'L-20250402-HP15', 3, 2400.00, 1),
(116, 51, 2, 'L-20250402-LENOVO3', 4, 1890.00, 1),
(117, 52, 30, 'L-20250419-REDMI10', 8, 475.00, 1),
(118, 52, 12, 'L-20250419-GALAXYA8', 4, 850.00, 1),
(119, 53, 14, 'L-20250430-HUAWEIFIT2', 6, 579.00, 1),
(120, 53, 19, 'L-20250430-Z313', 7, 185.00, 1),

-- DETALLE MAYO
(121, 54, 9, 'L-20250503-RYZEN5', 4, 629.00, 1),
(122, 54, 4, 'L-20250503-KING480', 6, 199.00, 1),
(123, 55, 5, 'L-20250514-LOGIM185', 15, 60.00, 1),
(124, 55, 15, 'L-20250514-WEBHD3000', 6, 135.00, 1),
(125, 56, 7, 'L-20250527-ROUTERC6', 4, 150.00, 1),
(126, 56, 16, 'L-20250527-MSI-B550', 3, 389.00, 1),

-- DETALLE JUNIO
(127, 57, 7, 'L-20250602-ROUTERC6', 5, 145.00, 1),
(128, 57, 16, 'L-20250602-MSI-B550', 4, 395.00, 1),
(129, 58, 11, 'L-20250617-EPSL3250', 4, 649.00, 1),
(130, 58, 5, 'L-20250617-LOGIM185', 15, 59.00, 1),
(131, 59, 12, 'L-20250628-GALAXYA8', 5, 859.00, 1),
(132, 59, 13, 'L-20250628-XIAOMINOTE12', 6, 749.00, 1);

-- Enero - Detalles de los ingresos (Complemento)
INSERT INTO detalle_ingresos (id, id_ingreso, id_producto, codigo_lote, cantidad, precio_unitario, activo) VALUES
(133, 42, 6, 'L-20250105-AURH120', 10, 119.00, 1),
(134, 43, 4, 'L-20250106-KING480', 15, 195.00, 1),
(135, 44, 13, 'L-20250108-XIAOMINOTE12', 6, 735.00, 1),
(136, 45, 30, 'L-20250109-REDMI10', 8, 499.00, 1),
(137, 46, 5, 'L-20250111-LOGIM185', 25, 59.00, 1),
(138, 47, 14, 'L-20250112-HUAWEIFIT2', 4, 599.00, 1),
(139, 48, 7, 'L-20250114-ROUTERC6', 10, 145.00, 1),
(140, 49, 19, 'L-20250117-Z313', 12, 179.00, 1),
(141, 50, 2, 'L-20250118-LENOVO3', 5, 1949.00, 1),
(142, 51, 1, 'L-20250120-HP15', 3, 2399.00, 1),
(143, 52, 3, 'L-20250122-MON24CURVO', 4, 779.00, 1),
(144, 53, 15, 'L-20250123-WEBHD3000', 10, 139.00, 1),
(145, 54, 36, 'L-20250127-LENOVOIDEA', 4, 1649.00, 1),
(146, 55, 16, 'L-20250129-MSI-B550', 5, 399.00, 1);

-- Detalles de ingresos de enero (continuando desde id 145)
INSERT INTO detalle_ingresos (id, id_ingreso, id_producto, codigo_lote, cantidad, precio_unitario, activo) VALUES
(147, 74, 5, 'L-20250105-LOGIM185', 20, 58.00, 1),
(148, 74, 6, 'L-20250105-AURH120', 10, 109.00, 1),

(149, 75, 30, 'L-20250107-REDMI10', 8, 475.00, 1),
(150, 75, 31, 'L-20250107-REDMINOTE13', 5, 849.00, 1),

(151, 76, 3, 'L-20250108-MON24CURVO', 6, 789.00, 1),
(152, 76, 16, 'L-20250108-MSI-B550', 4, 399.00, 1),

(153, 77, 10, 'L-20250112-GTX1650', 2, 1085.00, 1),
(154, 77, 9, 'L-20250112-RYZEN5', 3, 640.00, 1),

(155, 78, 20, 'L-20250114-IPAD9', 2, 1520.00, 1),
(156, 78, 13, 'L-20250114-TABLET-A8', 3, 875.00, 1),

(157, 79, 8, 'L-20250116-RAZERV2', 4, 225.00, 1),
(158, 79, 7, 'L-20250116-ROUTERC6', 6, 149.00, 1),

(159, 80, 15, 'L-20250118-WEBHD3000', 10, 135.00, 1),
(160, 80, 6, 'L-20250118-AURH120', 12, 109.00, 1),

(161, 81, 2, 'L-20250119-LENOVO3', 4, 1900.00, 1),
(162, 81, 4, 'L-20250119-KING480', 8, 198.00, 1),

(163, 82, 19, 'L-20250121-Z313', 6, 179.00, 1),
(164, 82, 23, 'L-20250121-GIGARAM', 5, 159.00, 1),

(165, 83, 18, 'L-20250122-HUAWEIWATCHFIT2', 3, 599.00, 1),
(166, 83, 15, 'L-20250122-WEBHD3000', 7, 139.00, 1),

(167, 84, 27, 'L-20250123-AURICABLE', 10, 89.00, 1),
(168, 84, 28, 'L-20250123-AURINALAM', 8, 149.00, 1),

(169, 85, 21, 'L-20250126-GALAXYA8', 4, 849.00, 1),
(170, 85, 24, 'L-20250126-WEB4K', 2, 229.00, 1),

(171, 86, 1, 'L-20250127-HP15', 5, 2350.00, 1),
(172, 86, 14, 'L-20250127-HUAWEIFIT2', 6, 589.00, 1),

(173, 87, 12, 'L-20250128-GALAXYA8', 6, 849.00, 1),
(174, 87, 11, 'L-20250128-EPSL3250', 4, 639.00, 1),

(175, 88, 9, 'L-20250129-RYZEN5', 5, 635.00, 1),
(176, 88, 10, 'L-20250129-GTX1650', 3, 1080.00, 1),

(177, 89, 26, 'L-20250130-SSD-SATA', 10, 155.00, 1),
(178, 89, 25, 'L-20250130-SSD-NVME', 7, 220.00, 1),

(179, 90, 31, 'L-20250131-REDMINOTE13', 5, 849.00, 1),
(180, 90, 30, 'L-20250131-REDMI10', 6, 485.00, 1),

(181, 91, 19, 'L-20250131-Z313', 5, 179.00, 1),
(182, 91, 4, 'L-20250131-KING480', 8, 195.00, 1),

(183, 92, 3, 'L-20250131-MON24CURVO', 5, 775.00, 1),
(184, 92, 8, 'L-20250131-RAZERV2', 3, 225.00, 1),

(185, 93, 6, 'L-20250131-AURH120', 9, 109.00, 1),
(186, 93, 7, 'L-20250131-ROUTERC6', 4, 149.00, 1);

-- Detalles de ingreso para febrero (continuando desde id 185)
INSERT INTO detalle_ingresos (id, id_ingreso, id_producto, codigo_lote, cantidad, precio_unitario, activo) VALUES
(187, 94, 6, 'L-20250202-AURH120', 10, 109.00, 1),
(188, 94, 15, 'L-20250202-WEBHD3000', 8, 139.00, 1),

(189, 95, 5, 'L-20250203-LOGIM185', 25, 59.00, 1),
(190, 95, 19, 'L-20250203-Z313', 6, 179.00, 1),

(191, 96, 3, 'L-20250204-MON24CURVO', 4, 785.00, 1),
(192, 96, 23, 'L-20250204-GIGARAM', 6, 159.00, 1),

(193, 97, 13, 'L-20250206-TABLET-A8', 5, 879.00, 1),
(194, 97, 12, 'L-20250206-GALAXYA8', 4, 849.00, 1),

(195, 98, 30, 'L-20250208-REDMI10', 6, 475.00, 1),
(196, 98, 4, 'L-20250208-KING480', 8, 199.00, 1),

(197, 99, 1, 'L-20250210-HP15', 3, 2399.00, 1),
(198, 99, 2, 'L-20250210-LENOVO3', 4, 1950.00, 1),

(199, 100, 14, 'L-20250215-HUAWEIFIT2', 6, 589.00, 1),
(200, 100, 11, 'L-20250215-EPSL3250', 3, 639.00, 1),

(201, 101, 8, 'L-20250217-RAZERV2', 4, 225.00, 1),
(202, 101, 7, 'L-20250217-ROUTERC6', 5, 149.00, 1),

(203, 102, 16, 'L-20250222-MSI-B550', 3, 399.00, 1),
(204, 102, 3, 'L-20250222-MON24CURVO', 6, 785.00, 1),

(205, 103, 25, 'L-20250224-SSD-NVME', 5, 220.00, 1),
(206, 103, 26, 'L-20250224-SSD-SATA', 7, 149.00, 1),

(207, 104, 27, 'L-20250226-AURICABLE', 12, 79.00, 1),
(208, 104, 28, 'L-20250226-AURINALAM', 9, 139.00, 1),

(209, 105, 20, 'L-20250228-IPAD9', 2, 1549.00, 1),
(210, 105, 24, 'L-20250228-WEB4K', 2, 229.00, 1);

-- Detalle Ingresos Marzo (IDs desde 211)
INSERT INTO detalle_ingresos (id, id_ingreso, id_producto, codigo_lote, cantidad, precio_unitario, activo) VALUES
(211, 106, 2, 'L-20250303-LENOVO3', 4, 1920.00, 1),
(212, 106, 4, 'L-20250303-KING480', 10, 199.00, 1),

(213, 107, 5, 'L-20250304-LOGIM185', 15, 59.90, 1),
(214, 107, 6, 'L-20250304-AURH120', 10, 109.00, 1),

(215, 108, 1, 'L-20250305-HP15', 3, 2350.00, 1),
(216, 108, 3, 'L-20250305-MON24CURVO', 2, 770.00, 1),

(217, 109, 12, 'L-20250306-GALAXYA8', 5, 840.00, 1),
(218, 109, 40, 'L-20250306-TCLMECH', 8, 139.00, 1),

(219, 110, 20, 'L-20250309-IPAD9', 2, 1530.00, 1),
(220, 110, 30, 'L-20250309-REDMI10', 6, 470.00, 1),

(221, 111, 9, 'L-20250310-RYZEN5', 4, 640.00, 1),
(222, 111, 10, 'L-20250310-GTX1650', 2, 1090.00, 1),

(223, 112, 7, 'L-20250312-ROUTERC6', 5, 149.00, 1),
(224, 112, 8, 'L-20250312-RAZERV2', 4, 229.00, 1),

(225, 113, 19, 'L-20250316-Z313', 6, 179.00, 1),
(226, 113, 6, 'L-20250316-AURH120', 8, 109.00, 1),

(227, 114, 13, 'L-20250317-XIAOMINOTE12', 3, 735.00, 1),
(228, 114, 3, 'L-20250317-MON24CURVO', 2, 775.00, 1),

(229, 115, 4, 'L-20250319-KING480', 12, 199.00, 1),
(230, 115, 5, 'L-20250319-LOGIM185', 18, 59.90, 1),

(231, 116, 30, 'L-20250321-REDMI10', 10, 480.00, 1),
(232, 116, 6, 'L-20250321-AURH120', 6, 109.00, 1),

(233, 117, 17, 'L-20250323-DELLAIO', 2, 2850.00, 1),
(234, 117, 15, 'L-20250323-WEBHD3000', 5, 139.00, 1),

(235, 118, 2, 'L-20250326-LENOVO3', 5, 1940.00, 1),
(236, 118, 23, 'L-20250326-GIGARAM', 10, 159.00, 1),

(237, 119, 14, 'L-20250328-HUAWEIFIT2', 4, 599.00, 1),
(238, 119, 16, 'L-20250328-MSI-B550', 3, 399.00, 1);


-- Abril: Detalles (productos variados)
INSERT INTO detalle_ingresos (id, id_ingreso, id_producto, codigo_lote, cantidad, precio_unitario, activo) VALUES
(239, 120, 2, 'L-20250401-LENOVO3', 3, 1890.00, 1),
(240, 120, 4, 'L-20250401-KING480', 6, 199.00, 1),

(241, 121, 6, 'L-20250402-AURH120', 10, 109.00, 1),
(242, 121, 5, 'L-20250402-LOGIM185', 12, 59.00, 1),

(243, 122, 3, 'L-20250403-MON24CURVO', 2, 770.00, 1),
(244, 122, 8, 'L-20250403-RAZERV2', 4, 229.00, 1),

(245, 123, 11, 'L-20250404-EPSL3250', 4, 639.00, 1),
(246, 123, 4, 'L-20250404-KING480', 8, 199.00, 1),

(247, 124, 5, 'L-20250405-LOGIM185', 20, 59.00, 1),

(248, 125, 3, 'L-20250406-MON24CURVO', 3, 769.00, 1),
(249, 125, 7, 'L-20250406-ROUTERC6', 6, 149.00, 1),

(250, 126, 1, 'L-20250408-HP15', 2, 2399.00, 1),
(251, 126, 6, 'L-20250408-AURH120', 6, 109.00, 1),

(252, 127, 14, 'L-20250409-HUAWEIFIT2', 5, 599.00, 1),

(253, 128, 13, 'L-20250410-XIAOMINOTE12', 3, 739.00, 1),
(254, 128, 20, 'L-20250410-IPAD9', 1, 1549.00, 1),

(255, 129, 19, 'L-20250411-Z313', 4, 179.00, 1),

(256, 130, 4, 'L-20250412-KING480', 10, 199.00, 1),
(257, 130, 23, 'L-20250412-GIGARAM', 5, 159.00, 1),

(258, 131, 17, 'L-20250413-DELLAIO', 1, 2890.00, 1),
(259, 131, 15, 'L-20250413-WEBHD3000', 5, 139.00, 1),

(260, 132, 2, 'L-20250415-LENOVO3', 5, 1949.00, 1),

(261, 133, 8, 'L-20250416-RAZERV2', 4, 229.00, 1),

(262, 134, 30, 'L-20250417-REDMI10', 8, 489.00, 1),

(263, 135, 10, 'L-20250418-GTX1650', 2, 1090.00, 1),

(264, 136, 6, 'L-20250419-AURH120', 10, 109.00, 1),

(265, 137, 5, 'L-20250420-LOGIM185', 20, 59.90, 1),

(266, 138, 1, 'L-20250421-HP15', 2, 2399.00, 1),

(267, 139, 19, 'L-20250423-Z313', 5, 179.00, 1),

(268, 140, 16, 'L-20250424-MSI-B550', 3, 399.00, 1),

(269, 141, 12, 'L-20250425-GALAXYA8', 4, 849.00, 1),

(270, 142, 6, 'L-20250427-AURH120', 10, 119.00, 1),

(271, 143, 3, 'L-20250429-MON24CURVO', 3, 779.00, 1);

-- Mayo - detalle_ingresos para ingresos 144 al 157 (inicio en id = 272)
INSERT INTO detalle_ingresos (id, id_ingreso, id_producto, codigo_lote, cantidad, precio_unitario, activo) VALUES
(272, 144, 5, 'L-20250503-LOGIM185', 15, 59.00, 1),
(273, 144, 20, 'L-20250503-IPAD9', 3, 1550.00, 1),

(274, 145, 4, 'L-20250504-KING480', 10, 199.00, 1),
(275, 145, 6, 'L-20250504-AURH120', 12, 119.00, 1),

(276, 146, 13, 'L-20250506-TABLET-A8', 6, 879.00, 1),
(277, 146, 14, 'L-20250506-HUAWEIFIT2', 4, 599.00, 1),

(278, 147, 1, 'L-20250508-HP15', 3, 2399.00, 1),
(279, 147, 9, 'L-20250508-RYZEN5', 5, 639.00, 1),
(280, 147, 19, 'L-20250508-Z313', 6, 189.00, 1),

(281, 148, 7, 'L-20250509-ROUTERC6', 8, 159.00, 1),
(282, 148, 11, 'L-20250509-EPSL3250', 2, 639.00, 1),

(283, 149, 4, 'L-20250511-KING480', 15, 199.00, 1),
(284, 149, 8, 'L-20250511-RAZERV2', 3, 219.00, 1),

(285, 150, 7, 'L-20250512-ROUTERC6', 6, 149.00, 1),
(286, 150, 16, 'L-20250512-MSI-B550', 4, 399.00, 1),

(287, 151, 10, 'L-20250514-GTX1650', 2, 1090.00, 1),
(288, 151, 17, 'L-20250514-DELLAIO', 1, 2899.00, 1),
(289, 151, 6, 'L-20250514-AURH120', 10, 119.00, 1),

(290, 152, 2, 'L-20250517-LENOVO3', 4, 1890.00, 1),
(291, 152, 3, 'L-20250517-MON24CURVO', 3, 779.00, 1),

(292, 153, 30, 'L-20250519-REDMI10', 6, 489.00, 1),
(293, 153, 12, 'L-20250519-GALAXYA8', 3, 849.00, 1),

(294, 154, 23, 'L-20250520-GIGARAM', 10, 159.00, 1),
(295, 154, 4, 'L-20250520-KING480', 12, 199.00, 1),

(296, 155, 15, 'L-20250522-WEBHD3000', 6, 139.00, 1),
(297, 155, 24, 'L-20250522-LOGI-K380', 10, 169.00, 1),

(298, 156, 2, 'L-20250525-LENOVO3', 5, 1899.00, 1),
(299, 156, 5, 'L-20250525-LOGIM185', 15, 59.00, 1),

(300, 157, 13, 'L-20250529-TABLET-A8', 4, 899.00, 1),
(301, 157, 19, 'L-20250529-Z313', 6, 189.00, 1),
(302, 157, 14, 'L-20250529-HUAWEIFIT2', 3, 599.00, 1);

-- Junio - detalle_ingresos para ingresos 158 al 172 (inicio en id = 303)
INSERT INTO detalle_ingresos (id, id_ingreso, id_producto, codigo_lote, cantidad, precio_unitario, activo) VALUES
(303, 158, 5, 'L-20250601-LOGIM185', 20, 59.00, 1),
(304, 158, 6, 'L-20250601-AURH120', 10, 119.00, 1),

(305, 159, 4, 'L-20250602-KING480', 12, 199.00, 1),
(306, 159, 1, 'L-20250602-HP15', 3, 2399.00, 1),

(307, 160, 19, 'L-20250603-Z313', 7, 189.00, 1),
(308, 160, 8, 'L-20250603-RAZERV2', 4, 219.00, 1),

(309, 161, 9, 'L-20250606-RYZEN5', 5, 649.00, 1),
(310, 161, 16, 'L-20250606-MSI-B550', 3, 399.00, 1),

(311, 162, 10, 'L-20250607-GTX1650', 4, 1090.00, 1),
(312, 162, 2, 'L-20250607-LENOVO3', 2, 1899.00, 1),

(313, 163, 4, 'L-20250608-KING480', 15, 189.00, 1),
(314, 163, 24, 'L-20250608-LOGI-K380', 5, 169.00, 1),

(315, 164, 3, 'L-20250610-MON24CURVO', 3, 779.00, 1),
(316, 164, 23, 'L-20250610-GIGARAM', 8, 159.00, 1),

(317, 165, 13, 'L-20250612-TABLET-A8', 4, 899.00, 1),
(318, 165, 14, 'L-20250612-HUAWEIFIT2', 4, 599.00, 1),

(319, 166, 11, 'L-20250616-EPSL3250', 3, 639.00, 1),
(320, 166, 6, 'L-20250616-AURH120', 6, 119.00, 1),

(321, 167, 20, 'L-20250617-IPAD9', 2, 1550.00, 1),
(322, 167, 12, 'L-20250617-GALAXYA8', 5, 849.00, 1),

(323, 168, 30, 'L-20250619-REDMI10', 6, 489.00, 1),
(324, 168, 25, 'L-20250619-HONOR90', 4, 869.00, 1),

(325, 169, 15, 'L-20250622-WEBHD3000', 6, 139.00, 1),
(326, 169, 26, 'L-20250622-LOGI-Z607', 2, 419.00, 1),

(327, 170, 7, 'L-20250624-ROUTERC6', 7, 149.00, 1),
(328, 170, 16, 'L-20250624-MSI-B550', 2, 399.00, 1),

(329, 171, 2, 'L-20250627-LENOVO3', 3, 1899.00, 1),
(330, 171, 5, 'L-20250627-LOGIM185', 10, 59.00, 1),

(331, 172, 19, 'L-20250629-Z313', 6, 179.00, 1),
(332, 172, 8, 'L-20250629-RAZERV2', 3, 229.00, 1),
(333, 172, 9, 'L-20250629-RYZEN5', 2, 649.00, 1),
(334, 172, 24, 'L-20250629-LOGI-K380', 4, 169.00, 1);


INSERT INTO serie_productos (id_detalle_ingreso, numero_serie, estado) VALUES
(1, 'SN-LAP-00101', 'VENDIDO'), (2, 'SN-CEL-00201', 'VENDIDO'), (3, 'SN-TV-00301', 'VENDIDO'),
(3, 'SN-TV-00302', 'VENDIDO'), (4, 'SN-CAM-00401', 'VENDIDO'), (5, 'SN-HDD-00501', 'VENDIDO'),
(6, 'SN-CPU-00601', 'VENDIDO'), (7, 'SN-MOU-00701', 'VENDIDO'), (8, 'SN-KEY-00801', 'VENDIDO'),
(8, 'SN-KEY-00802', 'VENDIDO'), (9, 'SN-GPU-00901', 'VENDIDO'), (10, 'SN-MON-01001', 'VENDIDO'),
(11, 'SN-AUD-01101', 'VENDIDO'), (11, 'SN-AUD-01102', 'VENDIDO'), (12, 'SN-LAP-00102', 'VENDIDO'),
(13, 'SN-CEL-00202', 'DEVUELTO'), (14, 'SN-TV-00303', 'VENDIDO'), (15, 'SN-CAM-00402', 'VENDIDO'),
(16, 'SN-HDD-00502', 'VENDIDO'), (17, 'SN-CPU-00602', 'VENDIDO'), (17, 'SN-CPU-00603', 'VENDIDO'),
(17, 'SN-CPU-00604', 'VENDIDO'), (18, 'SN-MOU-00702', 'VENDIDO'), (19, 'SN-KEY-00803', 'VENDIDO'),
(19, 'SN-KEY-00804', 'VENDIDO'), (20, 'SN-GPU-00902', 'VENDIDO'), (21, 'SN-MON-01002', 'VENDIDO'),
(22, 'SN-AUD-01103', 'VENDIDO'), (23, 'SN-LAP-00103', 'VENDIDO'), (23, 'SN-LAP-00104', 'VENDIDO'),
(24, 'SN-CEL-00203', 'VENDIDO'), (25, 'SN-TV-00304', 'REPARACION'),(26, 'SN-CAM-00403', 'VENDIDO'),
-- Stock disponible (ACTIVO)
(27, 'SN-HDD-00503', 'ACTIVO'), (28, 'SN-CPU-00605', 'ACTIVO'), (29, 'SN-MOU-00703', 'ACTIVO'),
(30, 'SN-KEY-00805', 'ACTIVO'), (1, 'SN-GPU-00903', 'ACTIVO'), (2, 'SN-MON-01003', 'ACTIVO'),
(3, 'SN-AUD-01104', 'ACTIVO'), (4, 'SN-LAP-00105', 'ACTIVO'), (5, 'SN-CEL-00204', 'ACTIVO'),
(6, 'SN-TV-00305', 'ACTIVO'), (7, 'SN-CAM-00404', 'ACTIVO'), (8, 'SN-HDD-00504', 'ACTIVO'),
(9, 'SN-CPU-00606', 'ACTIVO'), (10, 'SN-MOU-00704', 'ACTIVO'), (11, 'SN-KEY-00806', 'ACTIVO');



-- #####################################################
-- ## 7. DEVOLUCION DE PRODUCTOS (10 Devoluciones)
-- #####################################################
INSERT INTO devolucion_productos (id_serie_producto, id_detalle_ingreso, cantidad, fecha_devolucion, motivo, observaciones, id_usuario, reposicion_aplicada, activo) VALUES
(16, 13, 1, '2023-03-01 10:00:00', 'Producto no compatible con mi equipo', 'Cliente solicitó cambio por otro modelo.', 1, 1, 1),
(33, 25, 1, '2025-02-15 15:20:00', 'Defecto de fábrica, no enciende', 'Se entregó nota de crédito al cliente.', 1, 0, 1),
(17, 5, 1, '2024-08-20 11:00:00', 'No era el color esperado por el cliente.', 'Se aplicó reposición por el mismo producto en otro color.', 1, 1, 1),
(32, 12, 1, '2025-01-05 16:45:00', 'El cliente se arrepintió de la compra.', 'Producto en perfecto estado. Se devuelve a stock.', 1, 0, 1),
(10, 28, 1, '2025-06-10 14:00:00', 'Caja dañada durante el envío.', 'Se coordinó con el courier y se envió nuevo producto.', 1, 1, 1);
