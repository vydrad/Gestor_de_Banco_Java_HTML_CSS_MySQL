-- Deshabilitar las restricciones de claves foraneas temporalmente
SET FOREIGN_KEY_CHECKS = 0;

-- Eliminar registros de las tablas
TRUNCATE TABLE movimiento;
TRUNCATE TABLE prestamo;
TRUNCATE TABLE cuota;
TRUNCATE TABLE cuenta;
TRUNCATE TABLE cliente;
TRUNCATE TABLE localidad;
TRUNCATE TABLE provincia;
TRUNCATE TABLE tipo_cuenta;
TRUNCATE TABLE tipo_movimiento;
TRUNCATE TABLE usuario;

-- Habilitar las restricciones de claves foráneas nuevamente


SET FOREIGN_KEY_CHECKS = 1;


INSERT INTO tipo_cuenta (Cod_TipoCuenta, Descripcion_TipoCuenta) VALUES
(1, 'Caja de ahorro'),
(2, 'Cuenta corriente');

INSERT INTO tipo_movimiento (Cod_TipoMovimiento, Descripcion_TipoMovimiento) VALUES
(1, 'Deposito'),
(2, 'Extraccion');

INSERT INTO `provincia` VALUES ('BA','Buenos Aires'),('CBA','Cordoba'),('CHA','Chaco'),('LP','La Pampa'),('MIS','Misiones'),('MZA','Mendoza'),('NEQ','Neuquen'),('SAL','Salta'),('SF','Santa Fe'),('TUC','Tucuman');

INSERT INTO `localidad` VALUES ('cc','SF','Cordoba Capital'),('lp','BA','La Plata'),('mc','MIS','Mendoza Capital'),('mdp','CBA','Mar del Plata'),('nc','CHA','Neuquen Capital'),('ps','TUC','Posadas'),('rso','SAL','Rosario'),('sc','MIS','Salta Capital'),('sfc','NEQ','Santa Fe Capital'),('vcp','MZA','Villa Carlos Paz');

INSERT INTO usuario (Cod_Usuario, Nombre_Usuario, Pass_Usuario, Administrador_Usuario, Estado_Usuario) VALUES
(1, 'juanperez', 'pass123', 0, 1),
(2, 'analopez', 'pass456', 0, 1),
(3, 'carlosdiaz', 'pass789', 0, 1),
(4, 'luciamartinez', 'pass101', 0, 1),
(5, 'martasanchez', 'adminpass', 1, 1),
(6, 'jorgeramos', 'pass202', 0, 1),
(7, 'claragonzalez', 'pass303', 0, 1),
(8, 'diegoalvarez', 'pass404', 0, 1),
(9, 'paulamorales', 'pass505', 0, 1),
(10, 'robertogomez', 'pass606', 0, 1),
(11, 'fernandocruz', 'pass707', 0, 1),
(12, 'lauradiaz', 'pass808', 0, 1),
(13, 'manuelgarcia', 'pass909', 0, 1),
(14, 'gabrielatorres', 'pass1010', 0, 1),
(15, 'admingeneral', '2', 1, 1);

-- cliente
INSERT INTO cliente (
    DNI_Cliente, CUIL_Cliente, CodUsuario_Cliente, Provincia_Cliente, Localidad_Cliente, 
    NombreUsuario_Cliente, Pass_Cliente, Nombre_Cliente, Apellido_Cliente, Sexo_Cliente, 
    Nacionalidad_Cliente, FechaNacimiento_Cliente, Direccion_Cliente, Email_Cliente, 
    Telefono_Cliente, Estado_Cliente
) VALUES
(10000001, '20-10000001-0', 1, 'SF', 'cc', 'juanperez', 'pass123', 'Juan', 'Perez', 'M', 'Argentina', '1990-01-15', 'Calle Falsa 123', 'juanperez@mail.com', '3411234567', 1),
(10000002, '20-10000002-1', 2, 'BA', 'lp', 'analopez', 'pass456', 'Ana', 'Lopez', 'F', 'Argentina', '1985-03-20', 'Av. Siempreviva 742', 'analopez@mail.com', '2219876543', 1),
(10000003, '20-10000003-2', 3, 'MIS', 'mc', 'carlosdiaz', 'pass789', 'Carlos', 'Diaz', 'M', 'Argentina', '1978-07-05', 'Calle Luna 456', 'carlosdiaz@mail.com', '3752123456', 1),
(10000004, '20-10000004-3', 4, 'CBA', 'mdp', 'luciamartinez', 'pass101', 'Lucia', 'Martinez', 'F', 'Argentina', '1992-06-15', 'Calle Sol 789', 'luciamartinez@mail.com', '3516549873', 1),
(10000005, '20-10000005-4', 5, 'TUC', 'ps', 'martasanchez', 'adminpass', 'Marta', 'Sanchez', 'F', 'Argentina', '1965-10-20', 'Calle Mar 123', 'martasanchez@mail.com', '3811234567', 1),
(10000006, '20-10000006-5', 6, 'SAL', 'rso', 'jorgeramos', 'pass202', 'Jorge', 'Ramos', 'M', 'Argentina', '1990-11-01', 'Calle Tierra 951', 'jorgeramos@mail.com', '3874567890', 1),
(10000007, '20-10000007-6', 7, 'CHA', 'nc', 'claragonzalez', 'pass303', 'Clara', 'Gonzalez', 'F', 'Argentina', '1988-02-28', 'Calle Fuego 357', 'claragonzalez@mail.com', '3621237890', 1),
(10000008, '20-10000008-7', 8, 'NEQ', 'sfc', 'diegoalvarez', 'pass404', 'Diego', 'Alvarez', 'M', 'Argentina', '1983-03-15', 'Calle Viento 258', 'diegoalvarez@mail.com', '2996547890', 1),
(10000009, '20-10000009-8', 9, 'MZA', 'vcp', 'paulamorales', 'pass505', 'Paula', 'Morales', 'F', 'Argentina', '1995-05-05', 'Calle Agua 159', 'paulamorales@mail.com', '2611236547', 1),
(10000010, '20-10000010-9', 10, 'BA', 'lp', 'robertogomez', 'pass606', 'Roberto', 'Gomez', 'M', 'Argentina', '1975-12-10', 'Calle Estrella 321', 'robertogomez@mail.com', '2219873210', 1),
(10000011, '20-10000011-0', 11, 'SF', 'cc', 'fernandocruz', 'pass707', 'Fernando', 'Cruz', 'M', 'Argentina', '1988-04-25', 'Calle Luna 654', 'fernandocruz@mail.com', '3426549873', 1),
(10000012, '20-10000012-1', 12, 'MIS', 'mc', 'lauradiaz', 'pass808', 'Laura', 'Diaz', 'F', 'Argentina', '1991-09-15', 'Calle Siempreviva 456', 'lauradiaz@mail.com', '3756987456', 1),
(10000013, '20-10000013-2', 13, 'CBA', 'mdp', 'manuelgarcia', 'pass909', 'Manuel', 'Garcia', 'M', 'Argentina', '1994-08-30', 'Calle Mar del Plata 789', 'manuelgarcia@mail.com', '3511236987', 1),
(10000014, '20-10000014-3', 14, 'SAL', 'rso', 'gabrielatorres', 'pass1010', 'Gabriela', 'Torres', 'F', 'Argentina', '1981-06-01', 'Calle Rosario 321', 'gabrielatorres@mail.com', '3873219876', 1),
(10000015, '20-10000015-4', 15, 'TUC', 'ps', 'admingeneral', 'adminmaster', 'Admin', 'General', 'M', 'Argentina', '1980-01-01', 'Calle Admin 123', 'admingeneral@mail.com', '3814561230', 1);


-- cuenta
INSERT INTO cuenta (
    NumeroCuenta_Cuenta, CBU_Cuenta, DNI_Cuenta, Tipo_Cuenta, FechaCreacion_Cuenta, 
    Saldo_Cuenta, Estado_Cuenta
) VALUES
(10101, '00000001000000000101', 10000001, 1, '2023-01-15', 15000.50, 1),
(10102, '00000001000000000102', 10000001, 2, '2023-02-15', 500000.00, 1),
(10201, '00000001000000000201', 10000002, 1, '2023-03-20', 120000.00, 1),
(10202, '00000001000000000202', 10000002, 1, '2023-04-10', 80000.75, 1),
(10301, '00000001000000000301', 10000003, 2, '2023-04-15', 15000.25, 1),
(10401, '00000001000000000401', 10000004, 1, '2023-05-01', 30000.00, 1),
(10601, '00000001000000000601', 10000006, 2, '2023-05-05', 20000.00, 1),
(10701, '00000001000000000701', 10000007, 1, '2023-06-20', 10000.00, 1),
(10801, '00000001000000000801', 10000008, 1, '2023-07-15', 25000.00, 1),
(10901, '00000001000000000901', 10000009, 1, '2023-08-10', 15000.00, 1),
(11001, '00000001000000001001', 10000010, 1, '2023-09-05', 2000.00, 1),
(12001, '00000001000000002001', 10000011, 1, '2023-10-01', 22000.00, 1),
(13001, '00000001000000003001', 10000012, 1, '2023-11-10', 18000.50, 1),
(14001, '00000001000000004001', 10000013, 1, '2023-12-20', 17000.75, 1),
(15001, '00000001000000005001', 10000014, 1, '2024-01-15', 12000.25, 1),
(16001, '00000001000000006001', 10000014, 1, '2024-01-15', 120000.25, 1),
(17001, '00000001000000007001', 10000014, 1, '2024-01-15', 120000.25, 1);

-- cuota
INSERT INTO cuota (
    Cod_Cuota, NumeroCuotaPagar_Cuota, Monto_Cuota, FechaVencimiento_Cuota, 
    FechaPago_Cuota, Estado_Cuota
) VALUES
(1, 1, 2000.00, '2024-01-15', NULL, 'Pendiente'),
(2, 2, 1200.00, '2024-02-15', NULL, 'Pendiente'),
(3, 11,1500.00, '2024-03-10', NULL, 'Pendiente'),
(4, 2, 2400.00, '2024-04-10', NULL, 'Pendiente'),
(5, 1, 1000.00, '2024-05-15', NULL, 'Pendiente'),
(6, 1, 600.00, '2024-06-15', NULL, 'Pendiente'),
(7, 2, 2500.00, '2024-07-15', NULL, 'Pendiente'),
(8, 1, 1800.00, '2024-08-10', NULL, 'Pendiente'),
(9, 2, 1200.00, '2024-09-10', NULL, 'Pendiente'),
(10, 1, 1100.00, '2024-10-05', NULL, 'Pendiente'),
(11, 2, 1100.00, '2024-11-05', NULL, 'Pendiente'),
(12, 1, 1200.00, '2024-12-20', NULL, 'Pendiente'),
(13, 2, 1500.00, '2025-01-20', NULL, 'Pendiente'),
(14, 1, 1200.00, '2025-02-15', NULL, 'Pendiente'),
(15, 2, 1100.00, '2025-03-15', NULL, 'Pendiente');

-- prestamo
INSERT INTO prestamo (
    Cod_Prestamo, DNI_Prestamo, NumeroCuenta_Prestamo, CodCuota_Prestamo, 
    Fecha_Prestamo, ImportePedido_Prestamo, ImportePagar_Prestamo, 
    PlazoMeses_Prestamo, MontoMes_Prestamo, Estado_Prestamo
) VALUES
(1, 10000001, 10101, 1, '2023-12-01', 10000.00, 12000.00, 6, 2000.00, 'Activo'),
(2, 10000001, 10102, 2, '2024-01-10', 8000.00, 9600.00, 8, 1200.00, 'Activo'),
(3, 10000002, 10201, 3, '2024-02-05', 15000.00, 18000.00, 12, 1500.00, 'Activo'),
(4, 10000002, 10202, 4, '2024-03-01', 20000.00, 24000.00, 10, 2400.00, 'Pendiente'),
(5, 10000003, 10301, 5, '2024-04-12', 5000.00, 6000.00, 6, 1000.00, 'Activo'),
(6, 10000004, 10401, 6, '2024-05-10', 3000.00, 3600.00, 6, 600.00, 'Activo'),
(7, 10000006, 10601, 7, '2024-06-05', 25000.00, 30000.00, 12, 2500.00, 'Pendiente'),
(8, 10000007, 10701, 8, '2024-07-10', 12000.00, 14400.00, 8, 1800.00, 'Activo'),
(9, 10000008, 10801, 9, '2024-08-20', 10000.00, 12000.00, 10, 1200.00, 'Pendiente'),
(10, 10000009, 10901, 10, '2024-09-15', 5000.00, 5500.00, 5, 1100.00, 'Activo'),
(11, 10000010, 11001, 11, '2024-10-10', 8000.00, 8800.00, 8, 1100.00, 'Pendiente'),
(12, 10000011, 12001, 12, '2024-11-01', 6000.00, 7200.00, 6, 1200.00, 'Activo'),
(13, 10000012, 13001, 13, '2024-12-05', 15000.00, 18000.00, 12, 1500.00, 'Pendiente'),
(14, 10000013, 14001, 14, '2025-01-10', 9000.00, 10800.00, 9, 1200.00, 'Pendiente'),
(15, 10000014, 15001, 15, '2025-02-15', 5000.00, 5500.00, 5, 1100.00, 'Activo');

-- movimiento
INSERT INTO movimiento (
    Cod_Movimiento, CodTipo_Movimiento, NumeroCuenta_Movimiento, 
    Fecha_Movimiento, Importe_Movimiento, Descripcion_Movimiento
) VALUES
(1, 1, 10101, '2024-01-01', 5000.00, 'Deposito inicial'),
(2, 2, 10102, '2024-01-05', 1000.00, 'Extraccion por cajero automatico'),
(3, 1, 10201, '2024-02-01', 7000.00, 'Deposito en efectivo'),
(4, 2, 10202, '2024-02-10', 3000.00, 'Extraccion para pago de servicios'),
(5, 1, 10301, '2024-03-01', 12000.00, 'Deposito por transferencia'),
(6, 2, 10401, '2024-03-20', 1500.00, 'Extraccion para compra de materiales'),
(7, 1, 10601, '2024-04-01', 10000.00, 'Deposito por salario'),
(8, 2, 10701, '2024-04-15', 5000.00, 'Extraccion para gastos personales'),
(9, 1, 10801, '2024-05-10', 2000.00, 'Deposito en cheque'),
(10, 2, 10901, '2024-05-25', 3000.00, 'Extraccion por transferencia'),
(11, 1, 11001, '2024-06-05', 1000.00, 'Deposito en efectivo'),
(12, 2, 12001, '2024-06-20', 1500.00, 'Extraccion para pago de alquiler'),
(13, 1, 13001, '2024-07-01', 7000.00, 'Deposito por devolucion de prestamo'),
(14, 2, 14001, '2024-07-15', 2500.00, 'Extraccion por gastos escolares'),
(15, 1, 15001, '2024-08-01', 5000.00, 'Deposito por venta de productos');