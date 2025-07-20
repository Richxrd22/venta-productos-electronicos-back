-- ===== Ingreso Stock =====

INSERT INTO ingreso_stocks (
  id, id_proveedor, fecha_ingreso, tipo_documento, numero_documento,
  observaciones, activo, id_usuario, codigo_ingreso
) VALUES
(1, 3, '2025-07-01', 'Factura', 'FAC-1001', 'Ingreso inicial de productos básicos', 1, 1, 'ING-20250701-A1B2C3'),
(2, 5, '2025-07-01', 'Guía', 'GUI-1002', 'Ingreso intermedio para stock', 1, 1, 'ING-20250701-D4E5F6'),
(3, 7, '2025-07-01', 'Boleta', 'BOL-1003', 'Tercera carga de productos generales', 1, 1, 'ING-20250701-G7H8I9'),
(4, 2, '2025-07-01', 'Factura', 'FAC-1004', 'Carga adicional de periféricos', 1, 1, 'ING-20250701-J1K2L3'),
(5, 4, '2025-07-01', 'Guía', 'GUI-1005', 'Ingreso de componentes', 1, 1, 'ING-20250701-M4N5O6'),
(6, 1, '2025-07-01', 'Boleta', 'BOL-1006', 'Ingreso de dispositivos móviles', 1, 1, 'ING-20250701-P7Q8R9'),
(7, 6, '2025-07-01', 'Factura', 'FAC-1007', 'Ingreso de accesorios y almacenamiento', 1, 1, 'ING-20250701-S1T2U3'),
(8, 8, '2025-07-01', 'Factura', 'FAC-1008', 'Ingreso de equipos de escritorio y tablets', 1, 1, 'ING-20250701-V4W5X6');

INSERT INTO ingreso_stocks (
  id, id_proveedor, fecha_ingreso, tipo_documento, numero_documento,
  observaciones, activo, id_usuario, codigo_ingreso
) VALUES
(9, 2, '2025-07-03', 'Factura', 'FAC-1009', 'Reposición mensual de portátiles y periféricos', 1, 1, 'ING-20250703-Y7Z8A9'),
(10, 4, '2025-07-03', 'Guía', 'GUI-1010', 'Ingreso de componentes clave', 1, 1, 'ING-20250703-B2C3D4'),
(11, 1, '2025-07-03', 'Boleta', 'BOL-1011', 'Ingreso de accesorios diversos', 1, 1, 'ING-20250703-E5F6G7'),
(12, 7, '2025-07-03', 'Factura', 'FAC-1012', 'Equipamiento de tablets y móviles', 1, 1, 'ING-20250703-H8I9J1'),
(13, 5, '2025-07-03', 'Guía', 'GUI-1013', 'Carga complementaria de memorias y almacenamiento', 1, 1, 'ING-20250703-K2L3M4'),
(14, 3, '2025-07-03', 'Factura', 'FAC-1014', 'Stock general y productos variados', 1, 1, 'ING-20250703-N5O6P7');






-- Productos 1 a 10
UPDATE productos SET stock_actual = 26 WHERE id = 1;
UPDATE productos SET stock_actual = 22 WHERE id = 2;
UPDATE productos SET stock_actual = 12 WHERE id = 3;
UPDATE productos SET stock_actual = 40 WHERE id = 4;
UPDATE productos SET stock_actual = 78 WHERE id = 5;
UPDATE productos SET stock_actual = 34 WHERE id = 6;
UPDATE productos SET stock_actual = 19 WHERE id = 7;
UPDATE productos SET stock_actual = 23 WHERE id = 8;
UPDATE productos SET stock_actual = 11 WHERE id = 9;
UPDATE productos SET stock_actual = 9 WHERE id = 10;

-- Productos 11 a 20
UPDATE productos SET stock_actual = 17 WHERE id = 11;
UPDATE productos SET stock_actual = 17 WHERE id = 12;
UPDATE productos SET stock_actual = 26 WHERE id = 13;
UPDATE productos SET stock_actual = 13 WHERE id = 14;
UPDATE productos SET stock_actual = 22 WHERE id = 15;
UPDATE productos SET stock_actual = 12 WHERE id = 16;
UPDATE productos SET stock_actual = 8 WHERE id = 17;
UPDATE productos SET stock_actual = 7 WHERE id = 18;
UPDATE productos SET stock_actual = 17 WHERE id = 19;
UPDATE productos SET stock_actual = 9 WHERE id = 20;

-- Productos 21 a 30
UPDATE productos SET stock_actual = 13 WHERE id = 21;
UPDATE productos SET stock_actual = 18 WHERE id = 22;
UPDATE productos SET stock_actual = 34 WHERE id = 23;
UPDATE productos SET stock_actual = 8 WHERE id = 24;
UPDATE productos SET stock_actual = 6 WHERE id = 25;
UPDATE productos SET stock_actual = 17 WHERE id = 26;
UPDATE productos SET stock_actual = 10 WHERE id = 27;
UPDATE productos SET stock_actual = 13 WHERE id = 28;
UPDATE productos SET stock_actual = 26 WHERE id = 29;
UPDATE productos SET stock_actual = 24 WHERE id = 30;

-- Productos 31 a 40
UPDATE productos SET stock_actual = 14 WHERE id = 31;
UPDATE productos SET stock_actual = 72 WHERE id = 32;
UPDATE productos SET stock_actual = 0 WHERE id = 33;  -- stock cero
UPDATE productos SET stock_actual = 11 WHERE id = 34;
UPDATE productos SET stock_actual = 5 WHERE id = 35;  -- stock bajo
UPDATE productos SET stock_actual = 12 WHERE id = 36;
UPDATE productos SET stock_actual = 14 WHERE id = 37;
UPDATE productos SET stock_actual = 22 WHERE id = 38;
UPDATE productos SET stock_actual = 19 WHERE id = 39;
UPDATE productos SET stock_actual = 38 WHERE id = 40;


-- ENERO
INSERT INTO ingreso_stocks (id, id_proveedor, fecha_ingreso, tipo_documento, numero_documento, observaciones, activo, id_usuario, codigo_ingreso) VALUES
(15, 3, '2025-01-10', 'Factura', 'FAC-0806', 'Ingreso de equipos principales a inicios de año', 1, 1, 'ING-20250110-A1B2C3'),
(16, 4, '2025-01-15', 'Boleta', 'BOL-0807', 'Ingreso de productos de consumo rápido', 1, 1, 'ING-20250115-D4E5F6'),
(17, 6, '2025-01-25', 'Guía', 'GUI-0808', 'Guía para abastecimiento inicial', 1, 1, 'ING-20250125-G7H8I9');

-- FEBRERO
INSERT INTO ingreso_stocks (id, id_proveedor, fecha_ingreso, tipo_documento, numero_documento, observaciones, activo, id_usuario, codigo_ingreso) VALUES
(18, 1, '2025-02-12', 'Factura', 'FAC-0801', 'Ingreso temprano del año para laptops y SSDs', 1, 1, 'ING-20250212-J1K2L3'),
(19, 3, '2025-02-20', 'Boleta', 'BOL-0802', 'Ingreso de periféricos básicos', 1, 1, 'ING-20250220-M4N5O6'),
(20, 5, '2025-02-27', 'Guía', 'GUI-0803', 'Complemento de stock inicial del año', 1, 1, 'ING-20250227-P7Q8R9');

-- MARZO
INSERT INTO ingreso_stocks (id, id_proveedor, fecha_ingreso, tipo_documento, numero_documento, observaciones, activo, id_usuario, codigo_ingreso) VALUES
(21, 4, '2025-03-08', 'Boleta', 'BOL-0804', 'Ingreso de componentes de alto rendimiento', 1, 1, 'ING-20250308-S1T2U3'),
(22, 2, '2025-03-15', 'Factura', 'FAC-0805', 'Reposición de tablets y auriculares', 1, 1, 'ING-20250315-V4W5X6'),
(23, 6, '2025-03-25', 'Guía', 'GUI-0806', 'Ingreso mixto para campaña escolar', 1, 1, 'ING-20250325-Y7Z8A9');

-- ABRIL
INSERT INTO ingreso_stocks (id, id_proveedor, fecha_ingreso, tipo_documento, numero_documento, observaciones, activo, id_usuario, codigo_ingreso) VALUES
(24, 2, '2025-04-15', 'Factura', 'FAC-0901', 'Ingreso inicial de temporada Q2', 1, 1, 'ING-20250415-B2C3D4'),
(25, 4, '2025-04-22', 'Boleta', 'BOL-0902', 'Carga básica de tablets y accesorios', 1, 1, 'ING-20250422-E5F6G7'),
(26, 6, '2025-04-28', 'Guía', 'GUI-0903', 'Reposición de componentes externos', 1, 1, 'ING-20250428-H8I9J1');

-- MAYO
INSERT INTO ingreso_stocks (id, id_proveedor, fecha_ingreso, tipo_documento, numero_documento, observaciones, activo, id_usuario, codigo_ingreso) VALUES
(27, 1, '2025-05-10', 'Factura', 'FAC-0904', 'Reabastecimiento de audio y SSDs', 1, 1, 'ING-20250510-K2L3M4'),
(28, 3, '2025-05-18', 'Boleta', 'BOL-0905', 'Ingreso de gadgets económicos', 1, 1, 'ING-20250518-N5O6P7'),
(29, 5, '2025-05-28', 'Guía', 'GUI-0906', 'Ingreso adicional de computadoras y periféricos', 1, 1, 'ING-20250528-Q8R9S1');

-- JUNIO
INSERT INTO ingreso_stocks (id, id_proveedor, fecha_ingreso, tipo_documento, numero_documento, observaciones, activo, id_usuario, codigo_ingreso) VALUES
(30, 6, '2025-06-05', 'Boleta', 'BOL-0907', 'Ingreso de productos gaming', 1, 1, 'ING-20250605-T2U3V4'),
(31, 4, '2025-06-15', 'Guía', 'GUI-0908', 'Reabastecimiento de stock general', 1, 1, 'ING-20250615-W5X6Y7'),
(32, 2, '2025-06-20', 'Factura', 'FAC-0909', 'Ingreso final previo a campaña julio', 1, 1, 'ING-20250620-Z8A9B1');

-- ENERO (segunda tanda)
INSERT INTO ingreso_stocks (id, id_proveedor, fecha_ingreso, tipo_documento, numero_documento, observaciones, activo, id_usuario, codigo_ingreso) VALUES
(33, 7, '2025-01-07', 'Factura', 'FAC-0910', 'Ingreso inicial del año de periféricos', 1, 1, 'ING-20250107-C2D3E4'),
(34, 9, '2025-01-20', 'Boleta', 'BOL-0911', 'Reposición de tablets económicas', 1, 1, 'ING-20250120-F5G6H7'),
(35, 10, '2025-01-30', 'Guía', 'GUI-0912', 'Ingreso de accesorios básicos', 1, 1, 'ING-20250130-I8J9K1');

-- FEBRERO (segunda tanda)
INSERT INTO ingreso_stocks (id, id_proveedor, fecha_ingreso, tipo_documento, numero_documento, observaciones, activo, id_usuario, codigo_ingreso) VALUES
(36, 8, '2025-02-05', 'Factura', 'FAC-0913', 'Ingreso temprano de equipos all-in-one', 1, 1, 'ING-20250205-L2M3N4'),
(37, 12, '2025-02-18', 'Boleta', 'BOL-0914', 'Periféricos para zona sur', 1, 1, 'ING-20250218-O5P6Q7'),
(38, 14, '2025-02-24', 'Guía', 'GUI-0915', 'Ingreso de gadgets para temporada', 1, 1, 'ING-20250224-R8S9T1');

-- MARZO (segunda tanda)
INSERT INTO ingreso_stocks (id, id_proveedor, fecha_ingreso, tipo_documento, numero_documento, observaciones, activo, id_usuario, codigo_ingreso) VALUES
(39, 13, '2025-03-05', 'Factura', 'FAC-0916', 'Ingreso de auriculares variados', 1, 1, 'ING-20250305-U2V3W4'),
(40, 11, '2025-03-14', 'Boleta', 'BOL-0917', 'Impresoras y monitores', 1, 1, 'ING-20250314-X5Y6Z7'),
(41, 16, '2025-03-29', 'Guía', 'GUI-0918', 'Carga mixta de marzo', 1, 1, 'ING-20250329-CX9ZQ1');

-- ABRIL (segunda tanda)
INSERT INTO ingreso_stocks (id, id_proveedor, fecha_ingreso, tipo_documento, numero_documento, observaciones, activo, id_usuario, codigo_ingreso) VALUES
(42, 15, '2025-04-02', 'Factura', 'FAC-0919', 'Reabastecimiento de laptops gama media', 1, 1, 'ING-20250402-LM3NE2'),
(43, 18, '2025-04-19', 'Boleta', 'BOL-0920', 'Stock básico de celulares', 1, 1, 'ING-20250419-G7H8I9'),
(44, 19, '2025-04-30', 'Guía', 'GUI-0921', 'Ingreso mensual de abril', 1, 1, 'ING-20250430-VY7RP3');

-- MAYO (segunda tanda)
INSERT INTO ingreso_stocks (id, id_proveedor, fecha_ingreso, tipo_documento, numero_documento, observaciones, activo, id_usuario, codigo_ingreso) VALUES
(45, 20, '2025-05-03', 'Factura', 'FAC-0922', 'Ingreso mixto de componentes', 1, 1, 'ING-20250503-HB6KJ9'),
(46, 17, '2025-05-14', 'Boleta', 'BOL-0923', 'Accesorios para equipos de oficina', 1, 1, 'ING-20250514-QW3EY8'),
(47, 6, '2025-05-27', 'Guía', 'GUI-0924', 'Lote adicional por alta demanda', 1, 1, 'ING-20250527-TN8KM2');

-- JUNIO (segunda tanda)
INSERT INTO ingreso_stocks (id, id_proveedor, fecha_ingreso, tipo_documento, numero_documento, observaciones, activo, id_usuario, codigo_ingreso) VALUES
(48, 5, '2025-06-02', 'Factura', 'FAC-0925', 'Ingreso de routers y placas madre', 1, 1, 'ING-20250602-MZ7LC1'),
(49, 1, '2025-06-17', 'Boleta', 'BOL-0926', 'Reposición de impresoras y teclados', 1, 1, 'ING-20250617-WX2LP9'),
(50, 2, '2025-06-28', 'Guía', 'GUI-0927', 'Ingreso de equipos móviles', 1, 1, 'ING-20250628-EX5NJ3');


-- ENERO (Complemento con IDs secuenciales)
INSERT INTO ingreso_stocks (id, id_proveedor, fecha_ingreso, tipo_documento, numero_documento, observaciones, activo, id_usuario, codigo_ingreso) VALUES
(51, 7,  '2025-01-05', 'Factura', 'FAC-0810', 'Ingreso de accesorios básicos de red', 1, 1, 'ING-20250105-XD1JY9'),
(52, 12, '2025-01-06', 'Boleta',  'BOL-0811', 'Reposición de unidades de almacenamiento SSD', 1, 1, 'ING-20250106-RM8NE2'),
(53, 18, '2025-01-08', 'Guía',    'GUI-0812', 'Ingreso parcial de productos portátiles', 1, 1, 'ING-20250108-JQ4LX1'),
(54, 5,  '2025-01-09', 'Factura', 'FAC-0813', 'Ingreso de tablets para stock inicial', 1, 1, 'ING-20250109-KT5ME9'),
(55, 9,  '2025-01-11', 'Boleta',  'BOL-0814', 'Ingreso de periféricos económicos', 1, 1, 'ING-20250111-LD7OP4'),
(56, 14, '2025-01-12', 'Guía',    'GUI-0815', 'Ingreso mixto de gadgets', 1, 1, 'ING-20250112-YV3KZ6'),
(57, 10, '2025-01-14', 'Factura', 'FAC-0816', 'Ingreso de routers y cables', 1, 1, 'ING-20250114-HW1EX2'),
(58, 8,  '2025-01-17', 'Boleta',  'BOL-0817', 'Carga inicial de parlantes', 1, 1, 'ING-20250117-NS4TQ7'),
(59, 6,  '2025-01-18', 'Factura', 'FAC-0818', 'Ingreso general de tecnología', 1, 1, 'ING-20250118-KJ6RU5'),
(60, 1,  '2025-01-20', 'Guía',    'GUI-0819', 'Complemento de ingreso técnico', 1, 1, 'ING-20250120-PM8VR9'),
(61, 13, '2025-01-22', 'Factura', 'FAC-0820', 'Equipos de computación para reventa', 1, 1, 'ING-20250122-ZK1XT8'),
(62, 11, '2025-01-23', 'Boleta',  'BOL-0821', 'Ingreso de webcams y micrófonos', 1, 1, 'ING-20250123-GY7WR3'),
(63, 15, '2025-01-27', 'Factura', 'FAC-0822', 'Ingreso de laptops económicas', 1, 1, 'ING-20250127-AF5NU3'),
(64, 17, '2025-01-29', 'Guía',    'GUI-0823', 'Ingreso general para primer trimestre', 1, 1, 'ING-20250129-CX9EP1'),
(65, 20, '2025-01-27', 'Factura', 'FAC-0822', 'Ingreso para campaña escolar', 1, 1, 'ING-20250127-VD3NK8'),
(66, 8,  '2025-01-28', 'Boleta',  'BOL-0823', 'Ingreso de productos de alta demanda', 1, 1, 'ING-20250128-FP7YM2'),
(67, 1,  '2025-01-29', 'Guía',    'GUI-0824', 'Ingreso mixto para Lima y provincias', 1, 1, 'ING-20250129-HR6LT4'),
(68, 2,  '2025-01-30', 'Factura', 'FAC-0825', 'Stock de inicio para febrero', 1, 1, 'ING-20250130-UB9MJ1'),
(69, 5,  '2025-01-31', 'Boleta',  'BOL-0826', 'Ingreso final de enero', 1, 1, 'ING-20250131-QE3XU2'),
(70, 6,  '2025-01-31', 'Guía',    'GUI-0827', 'Refuerzo de stock para periféricos', 1, 1, 'ING-20250131-JW2RN6'),
(71, 3,  '2025-01-31', 'Factura', 'FAC-0828', 'Ingreso de computadoras económicas', 1, 1, 'ING-20250131-MT8YK3'),
(72, 4,  '2025-01-31', 'Boleta',  'BOL-0829', 'Ingreso de cámaras web y audífonos', 1, 1, 'ING-20250131-ZQ7BR4');


-- FEBRERO
INSERT INTO ingreso_stocks (id, id_proveedor, fecha_ingreso, tipo_documento, numero_documento, observaciones, activo, id_usuario, codigo_ingreso) VALUES
(73,  7,  '2025-02-02', 'Factura', 'FAC-0830', 'Ingreso de audífonos y webcams', 1, 1, 'ING-20250202-WJ5KX2'),
(74,  8,  '2025-02-03', 'Boleta',  'BOL-0831', 'Reabastecimiento de periféricos', 1, 1, 'ING-20250203-VB4LP9'),
(75,  9,  '2025-02-04', 'Guía',    'GUI-0832', 'Ingreso mixto para zona sur', 1, 1, 'ING-20250204-MZ2TY7'),
(76, 10,  '2025-02-06', 'Factura', 'FAC-0833', 'Ingreso para campaña escolar', 1, 1, 'ING-20250206-JC9RM4'),
(77, 11,  '2025-02-08', 'Boleta',  'BOL-0834', 'Ingreso económico para zonas rurales', 1, 1, 'ING-20250208-LW8XE3'),
(78, 12,  '2025-02-10', 'Guía',    'GUI-0835', 'Stock de inicio escolar', 1, 1, 'ING-20250210-KD7NQ1'),
(79, 13,  '2025-02-15', 'Factura', 'FAC-0836', 'Reemplazo de tablets y smartphones', 1, 1, 'ING-20250215-ZR6UC8'),
(80, 14,  '2025-02-17', 'Boleta',  'BOL-0837', 'Ingreso rápido de accesorios gamer', 1, 1, 'ING-20250217-XP3DJ9'),
(81, 15,  '2025-02-22', 'Guía',    'GUI-0838', 'Ingreso de monitores y memorias RAM', 1, 1, 'ING-20250222-NF8VS5'),
(82, 16,  '2025-02-24', 'Factura', 'FAC-0839', 'Ingreso de impresoras y SSDs', 1, 1, 'ING-20250224-TG1AY6'),
(83, 17,  '2025-02-26', 'Boleta',  'BOL-0840', 'Ingreso variado para zona norte', 1, 1, 'ING-20250226-QE7HJ3'),
(84, 18,  '2025-02-28', 'Guía',    'GUI-0841', 'Ingreso final del mes de febrero', 1, 1, 'ING-20250228-DY9LW4');

-- MARZO
INSERT INTO ingreso_stocks (id, id_proveedor, fecha_ingreso, tipo_documento, numero_documento, observaciones, activo, id_usuario, codigo_ingreso) VALUES
(85, 14, '2025-03-03', 'Factura', 'FAC-0810', 'Ingreso por campaña escolar regional', 1, 1, 'ING-20250303-I8J9K1'),
(86,  9, '2025-03-04', 'Boleta',  'BOL-0811', 'Reposición rápida de accesorios', 1, 1, 'ING-20250304-L2M3N4'),
(87,  2, '2025-03-05', 'Guía',    'GUI-0812', 'Guía de llegada para laptops escolares', 1, 1, 'ING-20250305-O5P6Q7'),
(88, 19, '2025-03-06', 'Factura', 'FAC-0813', 'Ingreso de tablets y teclados', 1, 1, 'ING-20250306-R8S9T1'),
(89, 13, '2025-03-09', 'Guía',    'GUI-0814', 'Productos escolares enviados a almacén', 1, 1, 'ING-20250309-U2V3W4'),
(90,  6, '2025-03-10', 'Boleta',  'BOL-0815', 'Reposición de stock gamer', 1, 1, 'ING-20250310-X5Y6Z7'),
(91,  4, '2025-03-12', 'Factura', 'FAC-0816', 'Ingreso mixto para venta inmediata', 1, 1, 'ING-20250312-WQ3TR2'),
(92, 10, '2025-03-16', 'Boleta',  'BOL-0817', 'Ingreso pequeño de periféricos', 1, 1, 'ING-20250316-XK6MF8'),
(93, 15, '2025-03-17', 'Guía',    'GUI-0818', 'Ingreso de tablets y monitores', 1, 1, 'ING-20250317-BV2CA6'),
(94, 17, '2025-03-19', 'Factura', 'FAC-0819', 'Compra programada de SSDs y auriculares', 1, 1, 'ING-20250319-ZE4YU1'),
(95,  3, '2025-03-21', 'Boleta',  'BOL-0820', 'Carga económica para campaña escolar', 1, 1, 'ING-20250321-DC9LA7'),
(96,  8, '2025-03-23', 'Guía',    'GUI-0821', 'Ingreso desde proveedor andino', 1, 1, 'ING-20250323-PL1ZW3'),
(97, 20, '2025-03-26', 'Factura', 'FAC-0822', 'Ingreso de alto valor en laptops', 1, 1, 'ING-20250326-FJ8RM2'),
(98,  5, '2025-03-28', 'Boleta',  'BOL-0823', 'Ingreso final de marzo escolar', 1, 1, 'ING-20250328-YU2ME7');

-- ABRIL
INSERT INTO ingreso_stocks (id, id_proveedor, fecha_ingreso, tipo_documento, numero_documento, observaciones, activo, id_usuario, codigo_ingreso) VALUES
(99,  7,  '2025-04-01', 'Factura', 'FAC-0924', 'Ingreso de productos escolares', 1, 1, 'ING-20250401-MK5XT1'),
(100, 3,  '2025-04-02', 'Boleta',  'BOL-0925', 'Carga de periféricos básicos', 1, 1, 'ING-20250402-D4E5X6'),
(101,15,  '2025-04-03', 'Guía',    'GUI-0926', 'Ingreso anticipado de stock', 1, 1, 'ING-20250403-G7H8I9'),
(102, 2,  '2025-04-04', 'Factura', 'FAC-0927', 'Ingreso de impresoras y SSDs', 1, 1, 'ING-20250404-LR3CZ9'),
(103,11,  '2025-04-05', 'Boleta',  'BOL-0928', 'Periféricos para promoción escolar', 1, 1, 'ING-20250405-XE7VP3'),
(104,10,  '2025-04-06', 'Guía',    'GUI-0929', 'Monitores y routers escolares', 1, 1, 'ING-20250406-ZT6DF2'),
(105, 4,  '2025-04-08', 'Factura', 'FAC-0930', 'Audio y computadoras económicas', 1, 1, 'ING-20250408-BA3HM5'),
(106,12,  '2025-04-09', 'Boleta',  'BOL-0931', 'Accesorios ligeros para colegios', 1, 1, 'ING-20250409-KC9TW4'),
(107, 9,  '2025-04-10', 'Guía',    'GUI-0932', 'Tablets y gadgets escolares', 1, 1, 'ING-20250410-JV6WR9'),
(108,16,  '2025-04-11', 'Factura', 'FAC-0933', 'Ingreso parcial para abril', 1, 1, 'ING-20250411-RL2PY3'),
(109, 5,  '2025-04-12', 'Boleta',  'BOL-0934', 'Complemento para stock general', 1, 1, 'ING-20250412-TN8XA6'),
(110,13,  '2025-04-13', 'Guía',    'GUI-0935', 'Ingreso por convenio regional', 1, 1, 'ING-20250413-FB7LS2'),
(111, 8,  '2025-04-15', 'Factura', 'FAC-0936', 'Ingreso Q2 zona sur', 1, 1, 'ING-20250415-K2L3M4'),
(112,14,  '2025-04-16', 'Boleta',  'BOL-0937', 'Equipos multimedia para colegios', 1, 1, 'ING-20250416-DZ9WY8'),
(113,17,  '2025-04-17', 'Guía',    'GUI-0938', 'Reposición por alta demanda', 1, 1, 'ING-20250417-VP3BN5'),
(114,18,  '2025-04-18', 'Factura', 'FAC-0939', 'Ingreso con financiamiento', 1, 1, 'ING-20250418-MF6TY1'),
(115, 6,  '2025-04-19', 'Boleta',  'BOL-0940', 'Ingreso desde distribuidor Surco', 1, 1, 'ING-20250419-YX8HZ4'),
(116,20,  '2025-04-20', 'Guía',    'GUI-0941', 'Ingreso de carga liviana', 1, 1, 'ING-20250420-CT2MG9'),
(117, 1,  '2025-04-21', 'Factura', 'FAC-0942', 'Ingreso a almacén central', 1, 1, 'ING-20250421-C2D3E4'),
(118,19,  '2025-04-23', 'Boleta',  'BOL-0943', 'Reabastecimiento escolar', 1, 1, 'ING-20250423-KL9DQ6'),
(119, 7,  '2025-04-24', 'Guía',    'GUI-0944', 'Campaña abril cierre parcial', 1, 1, 'ING-20250424-ZW1JK8'),
(120,10,  '2025-04-25', 'Factura', 'FAC-0945', 'Entrega final de abril', 1, 1, 'ING-20250425-PQ4NR3'),
(121, 3,  '2025-04-27', 'Boleta',  'BOL-0946', 'Ingreso menor gadgets', 1, 1, 'ING-20250427-O5P6Q7'),
(122,11,  '2025-04-29', 'Guía',    'GUI-0947', 'Última carga abril', 1, 1, 'ING-20250429-R8S9T1');

-- MAYO
INSERT INTO ingreso_stocks (id, id_proveedor, fecha_ingreso, tipo_documento, numero_documento, observaciones, activo, id_usuario, codigo_ingreso) VALUES
(123,12, '2025-05-03', 'Boleta',  'BOL-0907', 'Ingreso de productos básicos para almacén', 1, 1, 'ING-20250503-GZ5PK2'),
(124, 1, '2025-05-04', 'Factura', 'FAC-0908', 'Ingreso de componentes tecnológicos', 1, 1, 'ING-20250504-LW8DN3'),
(125, 3, '2025-05-06', 'Guía',    'GUI-0909', 'Reposición de tablets de media gama', 1, 1, 'ING-20250506-RK2MU7'),
(126, 5, '2025-05-08', 'Factura', 'FAC-0910', 'Carga mixta por campaña escolar', 1, 1, 'ING-20250508-YE3XW9'),
(127,14, '2025-05-09', 'Boleta',  'BOL-0911', 'Ingreso de periféricos y accesorios', 1, 1, 'ING-20250509-KD7QZ1'),
(128, 6, '2025-05-11', 'Guía',    'GUI-0912', 'Lote complementario de SSDs', 1, 1, 'ING-20250511-NP5VH4'),
(129,19, '2025-05-12', 'Factura', 'FAC-0913', 'Ingreso de routers y dispositivos', 1, 1, 'ING-20250512-XT9AY6'),
(130,17, '2025-05-14', 'Boleta',  'BOL-0914', 'Reposición de stock gamer', 1, 1, 'ING-20250514-JF6LU3'),
(131, 9, '2025-05-17', 'Factura', 'FAC-0915', 'Ingreso general para ventas de mayo', 1, 1, 'ING-20250517-WZ3HC2'),
(132,18, '2025-05-19', 'Boleta',  'BOL-0916', 'Lote económico para ventas flash', 1, 1, 'ING-20250519-VY1MP5'),
(133, 7, '2025-05-20', 'Guía',    'GUI-0917', 'Reabastecimiento de inventario', 1, 1, 'ING-20250520-ZR6TC8'),
(134, 2, '2025-05-22', 'Factura', 'FAC-0918', 'Ingreso por convenio institucional', 1, 1, 'ING-20250522-PD4EX7'),
(135,16, '2025-05-25', 'Boleta',  'BOL-0919', 'Entrega de accesorios y laptops', 1, 1, 'ING-20250525-QA7BW3'),
(136, 8, '2025-05-29', 'Guía',    'GUI-0920', 'Cierre de lote mensual de mayo', 1, 1, 'ING-20250529-MC9UY4');

-- JUNIO
INSERT INTO ingreso_stocks (id, id_proveedor, fecha_ingreso, tipo_documento, numero_documento, observaciones, activo, id_usuario, codigo_ingreso) VALUES
(137,  4, '2025-06-01', 'Factura', 'FAC-0921', 'Ingreso de dispositivos de red', 1, 1, 'ING-20250601-BF7XZ2'),
(138, 10, '2025-06-02', 'Boleta',  'BOL-0922', 'Periféricos para campañas intermedias', 1, 1, 'ING-20250602-KG4MV8'),
(139,  1, '2025-06-04', 'Guía',    'GUI-0923', 'Ingreso técnico de productos móviles', 1, 1, 'ING-20250604-RL8YW1'),
(140, 13, '2025-06-05', 'Factura', 'FAC-0924', 'Ingreso general para stock intermedio', 1, 1, 'ING-20250605-ZT3LJ5'),
(141,  9, '2025-06-07', 'Boleta',  'BOL-0925', 'Ingreso económico para zona este', 1, 1, 'ING-20250607-VF6KA4'),
(142, 19, '2025-06-09', 'Guía',    'GUI-0926', 'Ingreso mixto de SSDs y accesorios', 1, 1, 'ING-20250609-CT5RN6'),
(143,  5, '2025-06-11', 'Factura', 'FAC-0927', 'Ingreso parcial de tecnología', 1, 1, 'ING-20250611-XQ2AP9'),
(144, 14, '2025-06-12', 'Boleta',  'BOL-0928', 'Ingreso para promociones especiales', 1, 1, 'ING-20250612-LW3HS7'),
(145,  6, '2025-06-14', 'Guía',    'GUI-0929', 'Ingreso de impresoras y routers', 1, 1, 'ING-20250614-WM9QK3'),
(146,  2, '2025-06-17', 'Factura', 'FAC-0930', 'Reposición para campañas de invierno', 1, 1, 'ING-20250617-ZN1UB5'),
(147,  8, '2025-06-19', 'Boleta',  'BOL-0931', 'Stock económico gamer', 1, 1, 'ING-20250619-AG5LM2'),
(148, 11, '2025-06-21', 'Guía',    'GUI-0932', 'Ingreso desde proveedor nacional', 1, 1, 'ING-20250621-YR7ND4'),
(149,  7, '2025-06-23', 'Factura', 'FAC-0933', 'Ingreso de monitores y accesorios', 1, 1, 'ING-20250623-PJ4XE8'),
(150, 20, '2025-06-25', 'Boleta',  'BOL-0934', 'Ingreso de cierre de ciclo escolar', 1, 1, 'ING-20250625-QW2HK9'),
(151,  3, '2025-06-27', 'Guía',    'GUI-0935', 'Ingreso logístico final del mes', 1, 1, 'ING-20250627-UX9EM1');



INSERT INTO detalle_ingresos (id, id_ingreso, id_producto, codigo_detalle, cantidad, precio_unitario, activo) VALUES
(1, 1, 5, 'DI-20250701-82TM-001', 20, 589.99, 1),
(2, 1, 12, 'DI-20250701-82TM-002', 15, 349.50, 1),
(3, 2, 18, 'DI-20250701-Z4F8-001', 22, 429.90, 1),
(4, 2, 27, 'DI-20250701-Z4F8-002', 24, 1199.00, 1),
(5, 3, 3, 'DI-20250701-4S2J-001', 20, 239.99, 1),
(6, 3, 30, 'DI-20250701-4S2J-002', 18, 149.95, 1),
(7, 4, 4, 'DI-20250701-ESWX-001', 25, 199.00, 1),
(8, 4, 5, 'DI-20250701-ESWX-002', 50, 59.90, 1),
(9, 4, 6, 'DI-20250701-ESWX-003', 20, 119.00, 1),
(10, 5, 7, 'DI-20250701-HAU9-001', 15, 139.00, 1),
(11, 5, 8, 'DI-20250701-HAU9-002', 10, 219.00, 1),
(12, 5, 9, 'DI-20250701-HAU9-003', 10, 649.00, 1),
(13, 6, 10, 'DI-20250701-TL8A-001', 5, 1099.00, 1),
(14, 6, 11, 'DI-20250701-TL8A-002', 10, 649.00, 1),
(15, 6, 13, 'DI-20250701-TL8A-003', 20, 749.00, 1),
(16, 6, 14, 'DI-20250701-TL8A-004', 10, 559.00, 1),
(17, 7, 15, 'DI-20250701-QHTB-001', 15, 139.00, 1),
(18, 7, 16, 'DI-20250701-QHTB-002', 5, 399.00, 1),
(19, 7, 17, 'DI-20250701-QHTB-003', 3, 2899.00, 1),
(20, 7, 19, 'DI-20250701-QHTB-004', 10, 179.00, 1),
(21, 7, 20, 'DI-20250701-QHTB-005', 5, 1499.00, 1),
(22, 8, 21, 'DI-20250701-AOE0-001', 8, 2199.00, 1),
(23, 8, 22, 'DI-20250701-AOE0-002', 7, 1699.00, 1),
(24, 8, 23, 'DI-20250701-AOE0-003', 30, 159.00, 1),
(25, 8, 26, 'DI-20250701-AOE0-004', 10, 209.00, 1),
(26, 8, 30, 'DI-20250701-AOE0-005', 18, 499.00, 1),
(27, 8, 32, 'DI-20250701-AOE0-006', 40, 49.90, 1),
(28, 8, 34, 'DI-20250701-AOE0-007', 5, 369.00, 1),
(29, 8, 36, 'DI-20250701-AOE0-008', 7, 1299.00, 1),
(30, 8, 40, 'DI-20250701-AOE0-009', 25, 29.00, 1);

INSERT INTO detalle_ingresos (id, id_ingreso, id_producto, codigo_detalle, cantidad, precio_unitario, activo) VALUES
(31, 9, 1, 'DI-20250703-NQX7-001', 26, 2399.00, 1),
(32, 9, 2, 'DI-20250703-NQX7-002', 22, 1899.00, 1),
(33, 9, 5, 'DI-20250703-NQX7-003', 28, 59.90, 1),
(34, 9, 8, 'DI-20250703-NQX7-004', 23, 219.00, 1),

(35, 10, 4, 'DI-20250703-U6ZJ-001', 40, 199.00, 1),
(36, 10, 9, 'DI-20250703-U6ZJ-002', 11, 679.00, 1),
(37, 10, 12, 'DI-20250703-U6ZJ-003', 17, 879.00, 1),
(38, 10, 28, 'DI-20250703-U6ZJ-004', 13, 699.00, 1),

(39, 11, 6, 'DI-20250703-WF8K-001', 34, 119.00, 1),
(40, 11, 15, 'DI-20250703-WF8K-002', 22, 139.00, 1),
(41, 11, 19, 'DI-20250703-WF8K-003', 17, 179.00, 1),
(42, 11, 40, 'DI-20250703-WF8K-004', 38, 29.00, 1),

(43, 12, 13, 'DI-20250703-YX2B-001', 26, 739.00, 1),
(44, 12, 14, 'DI-20250703-YX2B-002', 13, 559.00, 1),
(45, 12, 20, 'DI-20250703-YX2B-003', 9, 1499.00, 1),
(46, 12, 36, 'DI-20250703-YX2B-004', 12, 1299.00, 1),

(47, 13, 23, 'DI-20250703-GVQ1-001', 34, 159.00, 1),
(48, 13, 32, 'DI-20250703-GVQ1-002', 72, 49.00, 1),
(49, 13, 26, 'DI-20250703-GVQ1-003', 17, 209.00, 1),
(50, 13, 34, 'DI-20250703-GVQ1-004', 5, 369.00, 1),

(51, 14, 3, 'DI-20250703-BK3E-001', 12, 799.00, 1),
(52, 14, 11, 'DI-20250703-BK3E-002', 17, 649.00, 1),
(53, 14, 16, 'DI-20250703-BK3E-003', 12, 399.00, 1),
(54, 14, 30, 'DI-20250703-BK3E-004', 24, 499.00, 1),
(55, 14, 39, 'DI-20250703-BK3E-005', 19, 239.00, 1);

INSERT INTO detalle_ingresos (id, id_ingreso, id_producto, codigo_detalle, cantidad, precio_unitario, activo) VALUES
(61, 15, 2, 'DI-20250110-XA72-001', 6, 1950.00, 1),
(62, 15, 4, 'DI-20250110-XA72-002', 12, 199.00, 1),
(63, 16, 5, 'DI-20250115-LQKM-001', 30, 59.90, 1),
(64, 16, 20, 'DI-20250115-LQKM-002', 2, 1550.00, 1),
(65, 17, 8, 'DI-20250125-WR3F-001', 4, 229.00, 1),
(66, 17, 19, 'DI-20250125-WR3F-002', 5, 179.00, 1),
(67, 18, 5, 'DI-20250212-ZJ81-001', 25, 55.00, 1),
(68, 18, 11, 'DI-20250212-ZJ81-002', 5, 639.00, 1),
(69, 19, 9, 'DI-20250220-DQ34-001', 6, 629.00, 1),
(70, 19, 10, 'DI-20250220-DQ34-002', 3, 1090.00, 1),
(71, 20, 12, 'DI-20250227-UC9M-001', 7, 849.00, 1),
(72, 20, 6, 'DI-20250227-UC9M-002', 12, 109.00, 1),
(73, 21, 7, 'DI-20250308-VTB4-001', 6, 149.00, 1),
(74, 21, 17, 'DI-20250308-VTB4-002', 2, 2899.00, 1),
(75, 22, 14, 'DI-20250315-YKPX-001', 5, 599.00, 1),
(76, 22, 15, 'DI-20250315-YKPX-002', 6, 139.00, 1),
(77, 23, 3, 'DI-20250325-RM7Q-001', 7, 779.00, 1),
(78, 23, 23, 'DI-20250325-RM7Q-002', 10, 159.00, 1),
(79, 24, 1, 'DI-20250415-HF92-001', 5, 2399.00, 1),
(80, 24, 6, 'DI-20250415-HF92-002', 10, 119.00, 1),
(81, 25, 20, 'DI-20250422-TLKE-001', 3, 1549.00, 1),
(82, 25, 30, 'DI-20250422-TLKE-002', 10, 489.00, 1),
(83, 26, 13, 'DI-20250428-KXJ7-001', 6, 899.00, 1),
(84, 26, 19, 'DI-20250428-KXJ7-002', 7, 189.00, 1),
(85, 27, 19, 'DI-20250510-BYQ8-001', 8, 179.00, 1),
(86, 27, 4,  'DI-20250510-BYQ8-002', 10, 199.00, 1),
(87, 28, 2,  'DI-20250518-KGUX-001', 7, 1899.00, 1),
(88, 28, 5,  'DI-20250518-KGUX-002', 20, 59.00, 1),
(89, 29, 9,  'DI-20250528-JM9F-001', 8, 649.00, 1),
(90, 29, 8,  'DI-20250528-JM9F-002', 5, 219.00, 1),
(91, 30, 15, 'DI-20250605-YR45-001', 6, 139.00, 1),
(92, 30, 13, 'DI-20250605-YR45-002', 8, 739.00, 1),
(93, 31, 16, 'DI-20250615-VT8M-001', 5, 399.00, 1),
(94, 31, 6,  'DI-20250615-VT8M-002', 10, 119.00, 1),
(95, 32, 1,  'DI-20250620-H2BQ-001', 4, 2390.00, 1),
(96, 32, 4,  'DI-20250620-H2BQ-002', 10, 189.00, 1),
(97, 33, 5,  'DI-20250107-K9FM-001', 20, 58.90, 1),
(98, 33, 6,  'DI-20250107-K9FM-002', 15, 110.00, 1),
(99, 34, 13, 'DI-20250120-UX21-001', 5, 729.00, 1),
(100, 34, 20, 'DI-20250120-UX21-002', 3, 1499.00, 1),
(101, 35, 4,  'DI-20250130-QWB3-001', 8, 189.00, 1),
(102, 35, 19, 'DI-20250130-QWB3-002', 6, 179.00, 1),
(103, 36, 17, 'DI-20250205-RQZ7-001', 3, 2850.00, 1),
(104, 36, 3,  'DI-20250205-RQZ7-002', 6, 789.00, 1),
(105, 37, 15, 'DI-20250218-ZJ39-001', 5, 135.00, 1),
(106, 37, 6,  'DI-20250218-ZJ39-002', 10, 109.00, 1),
(107, 38, 30, 'DI-20250224-ENX5-001', 6, 459.00, 1),
(108, 38, 8,  'DI-20250224-ENX5-002', 3, 215.00, 1),
(109, 39, 6,  'DI-20250305-YL7M-001', 15, 105.00, 1),
(110, 39, 7,  'DI-20250305-YL7M-002', 5, 155.00, 1),
(111, 40, 11, 'DI-20250314-MTX3-001', 4, 640.00, 1),
(112, 40, 3,  'DI-20250314-MTX3-002', 5, 799.00, 1),
(113, 41, 23, 'DI-20250329-PRC4-001', 10, 165.00, 1),
(114, 41, 10, 'DI-20250329-PRC4-002', 2, 1080.00, 1),
(115, 42, 1,  'DI-20250402-DLJ2-001', 3, 2400.00, 1),
(116, 42, 2,  'DI-20250402-DLJ2-002', 4, 1890.00, 1),
(117, 43, 30, 'DI-20250419-KLP8-001', 8, 475.00, 1),
(118, 43, 12, 'DI-20250419-KLP8-002', 4, 850.00, 1),
(119, 44, 14, 'DI-20250430-MHF6-001', 6, 579.00, 1),
(120, 44, 19, 'DI-20250430-MHF6-002', 7, 185.00, 1),
(121, 45, 9, 'DI-20250503-BTY3-001', 4, 629.00, 1),
(122, 45, 4, 'DI-20250503-BTY3-002', 6, 199.00, 1),
(123, 46, 5, 'DI-20250514-KNF8-001', 15, 60.00, 1),
(124, 47, 15, 'DI-20250514-KNF8-002', 6, 135.00, 1),
(125, 48, 7, 'DI-20250527-MXU4-001', 4, 150.00, 1),
(126, 48, 16, 'DI-20250527-MXU4-002', 3, 389.00, 1),
(127, 49, 7, 'DI-20250602-QP4Z-001', 5, 145.00, 1),
(128, 49, 16, 'DI-20250602-QP4Z-002', 4, 395.00, 1),
(129, 50, 11, 'DI-20250617-RKLM-001', 4, 649.00, 1),
(130, 50, 5, 'DI-20250617-RKLM-002', 15, 59.00, 1),
(131, 51, 12, 'DI-20250628-XMA2-001', 5, 859.00, 1),
(132, 51, 13, 'DI-20250628-XMA2-002', 6, 749.00, 1),
(133, 52, 6,  'DI-20250105-FR43-001', 10, 119.00, 1),
(134, 53, 4,  'DI-20250106-L8BZ-001', 15, 195.00, 1),
(135, 54, 13, 'DI-20250108-NXM5-001', 6, 735.00, 1),
(136, 55, 30, 'DI-20250109-MK2P-001', 8, 499.00, 1),
(137, 56, 5,  'DI-20250111-DV7W-001', 25, 59.00, 1),
(138, 57, 14, 'DI-20250112-GYP9-001', 4, 599.00, 1),
(139, 58, 7,  'DI-20250114-ZL3X-001', 10, 145.00, 1),
(140, 59, 19, 'DI-20250117-MTQ8-001', 12, 179.00, 1),
(141, 60, 2,  'DI-20250118-HC97-001', 5, 1949.00, 1),
(142, 61, 1,  'DI-20250120-PJ3K-001', 3, 2399.00, 1),
(143, 62, 3,  'DI-20250122-BVY1-001', 4, 779.00, 1),
(144, 63, 15, 'DI-20250123-JK2F-001', 10, 139.00, 1),
(145, 64, 36, 'DI-20250127-KRP4-001', 4, 1649.00, 1),
(146, 65, 16, 'DI-20250129-LUX3-001', 5, 399.00, 1),
(147, 66, 5,  'DI-20250105-YRD4-001', 20, 58.00, 1),
(148, 66, 6,  'DI-20250105-YRD4-002', 10, 109.00, 1),
(149, 67, 30, 'DI-20250107-GZ8K-001', 8, 475.00, 1),
(150, 67, 31, 'DI-20250107-GZ8K-002', 5, 849.00, 1),
(151, 68, 3,  'DI-20250108-VBN3-001', 6, 789.00, 1),
(152, 68, 16, 'DI-20250108-VBN3-002', 4, 399.00, 1),
(153, 69, 10, 'DI-20250112-XKJ4-001', 2, 1085.00, 1),
(154, 69, 9,  'DI-20250112-XKJ4-002', 3, 640.00, 1),
(155, 70, 20, 'DI-20250114-BTWP-001', 2, 1520.00, 1),
(156, 70, 13, 'DI-20250114-BTWP-002', 3, 875.00, 1),
(157, 71, 8,  'DI-20250116-NVXF-001', 4, 225.00, 1),
(158, 71, 7,  'DI-20250116-NVXF-002', 6, 149.00, 1),
(159, 72, 15, 'DI-20250118-MPZQ-001', 10, 135.00, 1),
(160, 72, 6,  'DI-20250118-MPZQ-002', 12, 109.00, 1),
(161, 73, 2,  'DI-20250119-ZDFJ-001', 4, 1900.00, 1),
(162, 73, 4,  'DI-20250119-ZDFJ-002', 8, 198.00, 1),
(163, 74, 19, 'DI-20250121-RQL3-001', 6, 179.00, 1),
(164, 74, 23, 'DI-20250121-RQL3-002', 5, 159.00, 1),
(165, 75, 18, 'DI-20250122-LXYK-001', 3, 599.00, 1),
(166, 75, 15, 'DI-20250122-LXYK-002', 7, 139.00, 1),
(167, 76, 27, 'DI-20250123-WYH3-001', 10, 89.00, 1),
(168, 76, 28, 'DI-20250123-WYH3-002', 8, 149.00, 1),
(169, 77, 21, 'DI-20250126-KNQT-001', 4, 849.00, 1),
(170, 77, 24, 'DI-20250126-KNQT-002', 2, 229.00, 1),
(171, 78, 1,  'DI-20250127-MDC2-001', 5, 2350.00, 1),
(172, 78, 14, 'DI-20250127-MDC2-002', 6, 589.00, 1),
(173, 79, 12, 'DI-20250128-NP74-001', 6, 849.00, 1),
(174, 79, 11, 'DI-20250128-NP74-002', 4, 639.00, 1),
(175, 80, 9,  'DI-20250129-HM8V-001', 5, 635.00, 1),
(176, 80, 10, 'DI-20250129-HM8V-002', 3, 1080.00, 1),
(177, 81, 26, 'DI-20250130-RVAK-001', 10, 155.00, 1),
(178, 82, 25, 'DI-20250130-RVAK-002', 7, 220.00, 1),
(179, 83, 31, 'DI-20250131-ZFGL-001', 5, 849.00, 1),
(180, 83, 30, 'DI-20250131-ZFGL-002', 6, 485.00, 1),
(181, 84, 19, 'DI-20250131-GMRD-001', 5, 179.00, 1),
(182, 84, 4,  'DI-20250131-GMRD-002', 8, 195.00, 1),
(183, 85, 3,  'DI-20250131-WZX3-001', 5, 775.00, 1),
(184, 85, 8,  'DI-20250131-WZX3-002', 3, 225.00, 1),
(185, 86, 6,  'DI-20250131-YVNR-001', 9, 109.00, 1),
(186, 86, 7,  'DI-20250131-YVNR-002', 4, 149.00, 1),
(187, 87, 6,  'DI-20250202-BKTM-001', 10, 109.00, 1),
(188, 87, 15, 'DI-20250202-BKTM-002', 8, 139.00, 1),
(189, 88, 5,  'DI-20250203-LDZC-001', 25, 59.00, 1),
(190, 88, 19, 'DI-20250203-LDZC-002', 6, 179.00, 1),
(191, 89, 3,  'DI-20250204-GH42-001', 4, 785.00, 1),
(192, 89, 23, 'DI-20250204-GH42-002', 6, 159.00, 1),
(193, 90, 13, 'DI-20250206-QPMX-001', 5, 879.00, 1),
(194, 90, 12, 'DI-20250206-QPMX-002', 4, 849.00, 1),
(195, 91, 30, 'DI-20250208-VCDL-001', 6, 475.00, 1),
(196, 91, 4,  'DI-20250208-VCDL-002', 8, 199.00, 1),
(197, 92, 1,  'DI-20250210-KVZY-001', 3, 2399.00, 1),
(198, 92, 2,  'DI-20250210-KVZY-002', 4, 1950.00, 1),
(199, 93,14, 'DI-20250215-MXU9-001', 6, 589.00, 1),
(200, 93,11, 'DI-20250215-MXU9-002', 3, 639.00, 1),
(201, 94,8,  'DI-20250217-NFKW-001', 4, 225.00, 1),
(202, 94,7,  'DI-20250217-NFKW-002', 5, 149.00, 1),
(203, 95,16, 'DI-20250222-WEJ8-001', 3, 399.00, 1),
(204, 95,3,  'DI-20250222-WEJ8-002', 6, 785.00, 1),
(205, 96, 25, 'DI-20250224-YXZP-001', 5, 220.00, 1),
(206, 96, 26, 'DI-20250224-YXZP-002', 7, 149.00, 1),
(207, 97, 27, 'DI-20250226-JKQ5-001', 12, 79.00, 1),
(208, 97, 28, 'DI-20250226-JKQ5-002', 9, 139.00, 1),
(209, 98, 20, 'DI-20250228-ZWXP-001', 2, 1549.00, 1),
(210, 98, 24, 'DI-20250228-ZWXP-002', 2, 229.00, 1),
(211, 99, 2,  'DI-20250303-MR5D-001', 4, 1920.00, 1),
(212, 99, 4,  'DI-20250303-MR5D-002', 10, 199.00, 1),
(213, 100,5,  'DI-20250304-XNBZ-001', 15, 59.90, 1),
(214, 100,6,  'DI-20250304-XNBZ-002', 10, 109.00, 1),
(215, 101,1,  'DI-20250305-ACM9-001', 3, 2350.00, 1),
(216, 101,3,  'DI-20250305-ACM9-002', 2, 770.00, 1),
(217, 102,12, 'DI-20250306-LSJF-001', 5, 840.00, 1),
(218, 102,40, 'DI-20250306-LSJF-002', 8, 139.00, 1),
(219, 103,20, 'DI-20250309-XPWH-001', 2, 1530.00, 1),
(220, 103,30, 'DI-20250309-XPWH-002', 6, 470.00, 1),
(221, 104,9,  'DI-20250310-KVMS-001', 4, 640.00, 1),
(222, 104,10, 'DI-20250310-KVMS-002', 2, 1090.00, 1),
(223, 105,7,  'DI-20250312-GVCT-001', 5, 149.00, 1),
(224, 105,8,  'DI-20250312-GVCT-002', 4, 229.00, 1),
(225, 106,19, 'DI-20250316-ZTWR-001', 6, 179.00, 1),
(226, 106,6,  'DI-20250316-ZTWR-002', 8, 109.00, 1),
(227, 107,13, 'DI-20250317-BXKC-001', 3, 735.00, 1),
(228, 107,3,  'DI-20250317-BXKC-002', 2, 775.00, 1),
(229, 108,4,  'DI-20250319-VPMD-001', 12, 199.00, 1),
(230, 108,5,  'DI-20250319-VPMD-002', 18, 59.90, 1),
(231, 109,30, 'DI-20250321-JZQY-001', 10, 480.00, 1),
(232, 109,6,  'DI-20250321-JZQY-002', 6, 109.00, 1),
(233, 110,17, 'DI-20250323-NWFS-001', 2, 2850.00, 1),
(234, 110,15, 'DI-20250323-NWFS-002', 5, 139.00, 1),
(235, 111,2,  'DI-20250326-LKBY-001', 5, 1940.00, 1),
(236, 111,23, 'DI-20250326-LKBY-002', 10, 159.00, 1),
(237, 112,14, 'DI-20250328-MZXR-001', 4, 599.00, 1),
(238, 112,16, 'DI-20250328-MZXR-002', 3, 399.00, 1),
(239, 113,2,  'DI-20250401-ZNYC-001', 3, 1890.00, 1),
(240, 113,4,  'DI-20250401-ZNYC-002', 6, 199.00, 1),
(241, 114,6,  'DI-20250402-JGZT-001', 10, 109.00, 1),
(242, 114,5,  'DI-20250402-JGZT-002', 12, 59.00, 1),
(243, 115,3,  'DI-20250403-KPVH-001', 2, 770.00, 1),
(244, 115,8,  'DI-20250403-KPVH-002', 4, 229.00, 1),
(245, 116,11, 'DI-20250404-NLMS-001', 4, 639.00, 1),
(246, 116,4,  'DI-20250404-NLMS-002', 8, 199.00, 1),
(247, 117,5,  'DI-20250405-WUDF-001', 20, 59.00, 1),
(248, 118,3,  'DI-20250406-PMZX-001', 3, 769.00, 1),
(249, 118,7,  'DI-20250406-PMZX-002', 6, 149.00, 1),
(250, 119,1,  'DI-20250408-XCQV-001', 2, 2399.00, 1),
(251, 119,6,  'DI-20250408-XCQV-002', 6, 109.00, 1),
(252, 120,14, 'DI-20250409-VBFN-001', 5, 599.00, 1),
(253, 121,13, 'DI-20250410-MBYK-001', 3, 739.00, 1),
(254, 121,20, 'DI-20250410-MBYK-002', 1, 1549.00, 1),
(255, 122,19, 'DI-20250411-JQXR-001', 4, 179.00, 1),
(256, 123,4,  'DI-20250412-WTSL-001', 10, 199.00, 1),
(257, 123,23, 'DI-20250412-WTSL-002', 5, 159.00, 1),
(258, 124,17, 'DI-20250413-KDMS-001', 1, 2890.00, 1),
(259, 124,15, 'DI-20250413-KDMS-002', 5, 139.00, 1),
(260, 125, 2, 'DI-20250415-RUHP-001', 5, 1949.00, 1),
(261, 126, 8, 'DI-20250416-BLVP-001', 4, 229.00, 1),
(262, 127,30, 'DI-20250417-LZTR-001', 8, 489.00, 1),
(263, 128,10, 'DI-20250418-YTKX-001', 2, 1090.00, 1),
(264, 129, 6, 'DI-20250419-QXCL-001', 10, 109.00, 1),
(265, 130, 5, 'DI-20250420-SRMF-001', 20, 59.90, 1),
(266, 131, 1, 'DI-20250421-UDNB-001', 2, 2399.00, 1),
(267, 132,19, 'DI-20250423-WOJK-001', 5, 179.00, 1),
(268, 133,16, 'DI-20250424-VBNL-001', 3, 399.00, 1),
(269, 134,12, 'DI-20250425-LJMY-001', 4, 849.00, 1),
(270, 135, 6, 'DI-20250427-XTSJ-001', 10, 119.00, 1),
(271, 136, 3, 'DI-20250429-BYQK-001', 3, 779.00, 1),
(272, 137, 5, 'DI-20250503-ARLN-001', 15, 59.00, 1),
(273, 137,20, 'DI-20250503-ARLN-002', 3, 1550.00, 1),
(274, 138, 4, 'DI-20250504-QZPK-001', 10, 199.00, 1),
(275, 138, 6, 'DI-20250504-QZPK-002', 12, 119.00, 1),
(276, 139,13, 'DI-20250506-BRMD-001', 6, 879.00, 1),
(277, 139,14, 'DI-20250506-BRMD-002', 4, 599.00, 1),
(278, 140, 1, 'DI-20250508-VFJQ-001', 3, 2399.00, 1),
(279, 140, 9, 'DI-20250508-VFJQ-002', 5, 639.00, 1),
(280, 140,19, 'DI-20250508-VFJQ-003', 6, 189.00, 1),
(281, 141, 7, 'DI-20250509-LCKT-001', 8, 159.00, 1),
(282, 141,11, 'DI-20250509-LCKT-002', 2, 639.00, 1),
(283, 142, 4, 'DI-20250511-XWRF-001', 15, 199.00, 1),
(284, 142, 8, 'DI-20250511-XWRF-002', 3, 219.00, 1),
(285, 143, 7, 'DI-20250512-GMJL-001', 6, 149.00, 1),
(286, 143,16, 'DI-20250512-GMJL-002', 4, 399.00, 1),
(287, 144,10, 'DI-20250514-DVYH-001', 2, 1090.00, 1),
(288, 144,17, 'DI-20250514-DVYH-002', 1, 2899.00, 1),
(289, 144, 6, 'DI-20250514-DVYH-003', 10, 119.00, 1),
(290, 145, 2, 'DI-20250517-HCLP-001', 4, 1890.00, 1),
(291, 145, 3, 'DI-20250517-HCLP-002', 3, 779.00, 1),
(292, 146,30, 'DI-20250519-TMRV-001', 6, 489.00, 1),
(293, 146,12, 'DI-20250519-TMRV-002', 3, 849.00, 1),
(294, 147,23, 'DI-20250520-LVZK-001', 10, 159.00, 1),
(295, 147, 4, 'DI-20250520-LVZK-002', 12, 199.00, 1),
(296, 148,15, 'DI-20250522-NKSY-001', 6, 139.00, 1),
(297, 148,24, 'DI-20250522-NKSY-002', 10, 169.00, 1),
(298, 149, 2, 'DI-20250525-BXMW-001', 5, 1899.00, 1),
(299, 149, 5, 'DI-20250525-BXMW-002', 15, 59.00, 1),
(300, 150,13, 'DI-20250529-ZPMT-001', 4, 899.00, 1),
(301, 150,19, 'DI-20250529-ZPMT-002', 6, 189.00, 1),
(302, 150,14, 'DI-20250529-ZPMT-003', 3, 599.00, 1),
(303, 151, 5, 'DI-20250601-LURK-001', 20, 59.00, 1),
(304, 151, 6, 'DI-20250601-LURK-002', 10, 119.00, 1);



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

-- NUEVOS REGISTROS

INSERT INTO devolucion_productos (id_serie_producto, id_detalle_ingreso, cantidad, fecha_devolucion, motivo, observaciones, id_usuario, reposicion_aplicada, activo) VALUES
(10, 27, 3, '2025-06-10 14:00:00', 'Defecto de fábrica, no enciende', 'Se notifico al proveedor el hecho', 2, 3, 1),
(22, 25, 1, '2025-01-15 11:30:00', 'Producto defectuoso', 'No enciende.', 2, 1, 1),
(5, 112, 1, '2025-01-20 09:15:00', 'No cumple con las especificaciones', 'El color no corresponde al solicitado.', 3, 1, 1),
(31, 24, 2, '2025-02-05 14:00:00', 'Daño estético en el empaque', 'Cliente rechazó el producto por caja dañada.', 1, 0, 1),
(8, 77, 3, '2025-02-10 16:45:00', 'Producto no compatible', 'Incompatibilidad con el sistema operativo del cliente.', 3, 1, 1),
(45, 18, 1, '2025-02-18 12:00:00', 'Cliente se arrepintió de la compra', 'Ya no necesita el producto.', 2, 0, 1),
(19, 94, 2, '2025-03-02 10:10:00', 'Faltan accesorios', 'No incluía el cargador.', 3, 1, 1),
(22, 15, 1, '2025-03-08 17:30:00', 'Producto defectuoso', 'La batería no retiene la carga.', 1, 1, 1),
(3, 201, 5, '2025-03-15 11:00:00', 'Daño físico en el producto', 'Pantalla con rayaduras.', 2, 1, 1),
(40, 28, 2, '2025-03-22 13:20:00', 'No cumple con las expectativas', 'El rendimiento es inferior al esperado.', 2, 0, 1),
(11, 42, 4, '2025-04-01 15:00:00', 'Producto incorrecto', 'Se envió un modelo diferente.', 3, 1, 1),
(8, 31, 6, '2025-04-07 09:45:00', 'Producto no compatible', 'Los puertos no son los correctos para sus dispositivos.', 1, 1, 1),
(25, 19, 2, '2025-04-14 18:00:00', 'Cliente se arrepintió de la compra', NULL, 2, 0, 1),
(36, 19, 1, '2025-04-21 10:35:00', 'Producto defectuoso', 'Presenta sobrecalentamiento excesivo.', 2, 1, 1),
(22, 88, 1, '2025-04-28 12:15:00', 'No cumple con las especificaciones', 'La capacidad de almacenamiento es menor.', 3, 1, 1),
(5, 21, 2, '2025-05-03 16:00:00', 'Daño estético en el producto', 'Carcasa con golpes.', 1, 1, 1),
(15, 6, 1, '2025-05-10 11:50:00', 'Faltan accesorios', 'No venía con el manual de usuario.', 3, 1, 1),
(31, 15, 2, '2025-05-18 14:30:00', 'Producto no compatible', 'Conflicto de software con otros programas.', 2, 0, 1),
(42, 219, 1, '2025-05-25 09:00:00', 'Cliente se arrepintió de la compra', 'Encontró una mejor oferta.', 3, 0, 1),
(8, 23, 3, '2025-06-02 13:00:00', 'Producto defectuoso', 'El dispositivo se reinicia constantemente.', 1, 1, 1),
(29, 17, 1, '2025-06-08 10:25:00', 'Producto incorrecto', 'El color no es el que ordenó.', 1, 1, 1),
(19, 33, 3, '2025-06-12 15:40:00', 'No cumple con las expectativas', 'La calidad de la cámara es deficiente.', 2, 0, 1),
(45, 33, 3, '2025-06-19 17:55:00', 'Daño físico en el producto', 'Botón de encendido atascado.', 3, 1, 1),
(22, 19, 2, '2025-06-23 11:10:00', 'Producto defectuoso', 'Problemas con la conectividad Wi-Fi.', 1, 1, 1),
(36, 29, 2, '2025-06-28 09:30:00', 'Faltan accesorios', 'Falta el cable de alimentación.', 2, 1, 1),
(8, 19, 1, '2025-06-30 16:20:00', 'Producto no compatible', 'No es compatible con su red eléctrica.', 2, 1, 1);

-- #####################################################
-- INSERT DE DESCUENTOS
-- #####################################################

INSERT INTO descuentos (id_categoria, porcentaje, fecha_inicio, fecha_fin, activo) VALUES
(20, 15, '2025-06-01', '2025-06-30', 0),
(50, 07, '2025-08-01', '2025-08-15', 1),
(25, 09, '2025-10-01', '2025-10-31', 1);