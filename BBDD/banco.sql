-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: banco
-- ------------------------------------------------------
-- Server version	5.7.18-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cliente` (
  `DNI_Cliente` int(11) NOT NULL,
  `CUIL_Cliente` varchar(20) NOT NULL,
  `CodUsuario_Cliente` int(11) NOT NULL,
  `Provincia_Cliente` varchar(45) NOT NULL,
  `Localidad_Cliente` varchar(45) NOT NULL,
  `Pass_Cliente` varchar(100) NOT NULL,
  `Nombre_Cliente` varchar(50) DEFAULT NULL,
  `Apellido_Cliente` varchar(50) DEFAULT NULL,
  `Sexo_Cliente` varchar(10) DEFAULT NULL,
  `Nacionalidad_Cliente` varchar(30) DEFAULT NULL,
  `FechaNacimiento_Cliente` date DEFAULT NULL,
  `Direccion_Cliente` varchar(100) DEFAULT NULL,
  `Email_Cliente` varchar(50) DEFAULT NULL,
  `Telefono_Cliente` varchar(20) DEFAULT NULL,
  `Estado_Cliente` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`DNI_Cliente`,`CUIL_Cliente`),
  KEY `FK_Cliente_Localidad_idx` (`Localidad_Cliente`),
  KEY `FK_Cliente_Provincia_idx` (`Provincia_Cliente`),
  KEY `FK_Cliente_Usuario_idx` (`CodUsuario_Cliente`,`Pass_Cliente`),
  CONSTRAINT `FK_Cliente_Localidad` FOREIGN KEY (`Localidad_Cliente`) REFERENCES `localidad` (`Cod_Localidad`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_Cliente_Provincia` FOREIGN KEY (`Provincia_Cliente`) REFERENCES `localidad` (`CodProvincia_Localidad`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_Cliente_Usuario` FOREIGN KEY (`CodUsuario_Cliente`, `Pass_Cliente`) REFERENCES `usuario` (`Cod_Usuario`, `Nombre_Usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cuenta`
--

DROP TABLE IF EXISTS `cuenta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cuenta` (
  `NumeroCuenta_Cuenta` int(11) NOT NULL,
  `CBU_Cuenta` varchar(45) NOT NULL,
  `DNI_Cuenta` int(11) NOT NULL,
  `Tipo_Cuenta` int(10) NOT NULL,
  `FechaCreacion_Cuenta` date DEFAULT NULL,
  `Saldo_Cuenta` decimal(15,2) NOT NULL DEFAULT '10000.00',
  `Estado_Cuenta` bit(1) DEFAULT NULL,
  PRIMARY KEY (`NumeroCuenta_Cuenta`,`CBU_Cuenta`),
  KEY `FK_Cuenta_TipoCuenta_idx` (`Tipo_Cuenta`),
  KEY `FK_Cuenta_Cliente_idx` (`DNI_Cuenta`),
  CONSTRAINT `FK_Cuenta_Cliente` FOREIGN KEY (`DNI_Cuenta`) REFERENCES `cliente` (`CodUsuario_Cliente`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_Cuenta_TipoCuenta` FOREIGN KEY (`Tipo_Cuenta`) REFERENCES `tipo_cuenta` (`Cod_TipoCuenta`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cuenta`
--

LOCK TABLES `cuenta` WRITE;
/*!40000 ALTER TABLE `cuenta` DISABLE KEYS */;
/*!40000 ALTER TABLE `cuenta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cuota`
--

DROP TABLE IF EXISTS `cuota`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cuota` (
  `Cod_Cuota` int(11) NOT NULL,
  `NumeroCuotaPagar_Cuota` int(11) DEFAULT NULL,
  `Monto_Cuota` decimal(15,2) DEFAULT NULL,
  `FechaVencimiento_Cuota` date DEFAULT NULL,
  `FechaPago_Cuota` date DEFAULT NULL,
  `Estado_Cuota` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`Cod_Cuota`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cuota`
--

LOCK TABLES `cuota` WRITE;
/*!40000 ALTER TABLE `cuota` DISABLE KEYS */;
/*!40000 ALTER TABLE `cuota` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `localidad`
--

DROP TABLE IF EXISTS `localidad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `localidad` (
  `Cod_Localidad` varchar(45) NOT NULL,
  `CodProvincia_Localidad` varchar(45) NOT NULL,
  `Descripcion_Localidad` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Cod_Localidad`),
  KEY `FK_Localidad_Provincia_idx` (`CodProvincia_Localidad`),
  CONSTRAINT `FK_Localidad_Provincia` FOREIGN KEY (`CodProvincia_Localidad`) REFERENCES `provincia` (`Cod_Provincia`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `localidad`
--

LOCK TABLES `localidad` WRITE;
/*!40000 ALTER TABLE `localidad` DISABLE KEYS */;
/*!40000 ALTER TABLE `localidad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movimiento`
--

DROP TABLE IF EXISTS `movimiento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `movimiento` (
  `Cod_Movimiento` int(11) NOT NULL AUTO_INCREMENT,
  `CodTipo_Movimiento` int(11) NOT NULL,
  `NumeroCuenta_Movimiento` int(11) NOT NULL,
  `Fecha_Movimiento` date DEFAULT NULL,
  `Importe_Movimiento` decimal(15,2) DEFAULT NULL,
  `Descripcion_Movimiento` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Cod_Movimiento`),
  KEY `FK_Movimiento_TipoMovimiento_idx` (`CodTipo_Movimiento`),
  KEY `FK_Movimiento_Cuenta_idx` (`NumeroCuenta_Movimiento`),
  CONSTRAINT `FK_Movimiento_Cuenta` FOREIGN KEY (`NumeroCuenta_Movimiento`) REFERENCES `cuenta` (`NumeroCuenta_Cuenta`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_Movimiento_TipoMovimiento` FOREIGN KEY (`CodTipo_Movimiento`) REFERENCES `tipo_movimiento` (`Cod_TipoMovimiento`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movimiento`
--

LOCK TABLES `movimiento` WRITE;
/*!40000 ALTER TABLE `movimiento` DISABLE KEYS */;
/*!40000 ALTER TABLE `movimiento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prestamo`
--

DROP TABLE IF EXISTS `prestamo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `prestamo` (
  `Cod_Prestamo` int(11) NOT NULL,
  `DNI_Prestamo` int(11) NOT NULL,
  `NumeroCuenta_Prestamo` int(11) NOT NULL,
  `CodCuota_Prestamo` int(11) NOT NULL,
  `Fecha_Prestamo` date DEFAULT NULL,
  `ImportePedido_Prestamo` decimal(15,2) DEFAULT NULL,
  `ImportePagar_Prestamo` decimal(15,2) DEFAULT NULL,
  `PlazoMeses_Prestamo` int(11) DEFAULT NULL,
  `MontoMes_Prestamo` decimal(15,2) DEFAULT NULL,
  `Estado_Prestamo` varchar(20) NOT NULL DEFAULT 'Pendiente',
  PRIMARY KEY (`Cod_Prestamo`),
  KEY `FK_Prestamo_Cuota_idx` (`CodCuota_Prestamo`),
  KEY `FK_Prestamo_Cuenta_idx` (`NumeroCuenta_Prestamo`),
  KEY `FK_Prestamo_Cuenta_Cliente_idx` (`DNI_Prestamo`),
  CONSTRAINT `FK_Prestamo_Cuenta` FOREIGN KEY (`NumeroCuenta_Prestamo`) REFERENCES `cuenta` (`NumeroCuenta_Cuenta`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_Prestamo_Cuenta_Cliente` FOREIGN KEY (`DNI_Prestamo`) REFERENCES `cuenta` (`DNI_Cuenta`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_Prestamo_Cuota` FOREIGN KEY (`CodCuota_Prestamo`) REFERENCES `cuota` (`Cod_Cuota`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prestamo`
--

LOCK TABLES `prestamo` WRITE;
/*!40000 ALTER TABLE `prestamo` DISABLE KEYS */;
/*!40000 ALTER TABLE `prestamo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `provincia`
--

DROP TABLE IF EXISTS `provincia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `provincia` (
  `Cod_Provincia` varchar(45) NOT NULL,
  `Descripcion_Provincia` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Cod_Provincia`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `provincia`
--

LOCK TABLES `provincia` WRITE;
/*!40000 ALTER TABLE `provincia` DISABLE KEYS */;
/*!40000 ALTER TABLE `provincia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_cuenta`
--

DROP TABLE IF EXISTS `tipo_cuenta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipo_cuenta` (
  `Cod_TipoCuenta` int(10) NOT NULL AUTO_INCREMENT,
  `Descripcion_TipoCuenta` varchar(45) NOT NULL,
  PRIMARY KEY (`Cod_TipoCuenta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_cuenta`
--

LOCK TABLES `tipo_cuenta` WRITE;
/*!40000 ALTER TABLE `tipo_cuenta` DISABLE KEYS */;
/*!40000 ALTER TABLE `tipo_cuenta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_movimiento`
--

DROP TABLE IF EXISTS `tipo_movimiento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipo_movimiento` (
  `Cod_TipoMovimiento` int(10) NOT NULL AUTO_INCREMENT,
  `Descripcion_TipoMovimiento` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`Cod_TipoMovimiento`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_movimiento`
--

LOCK TABLES `tipo_movimiento` WRITE;
/*!40000 ALTER TABLE `tipo_movimiento` DISABLE KEYS */;
/*!40000 ALTER TABLE `tipo_movimiento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `Cod_Usuario` int(11) NOT NULL,
  `Nombre_Usuario` varchar(50) NOT NULL,
  `Pass_Usuario` varchar(100) NOT NULL,
  `Administrador_Usuario` bit(1) DEFAULT NULL,
  `Estado_Usuario` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`Cod_Usuario`,`Nombre_Usuario`),
  KEY `FK_PASS` (`Pass_Usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'banco'
--

--
-- Dumping routines for database 'banco'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-11-01 15:55:23

-- CARGA DE DATOS

INSERT INTO Provincia (Descripcion_Provincia, Cod_Provincia) 
VALUES 
('Buenos Aires', 'BA'),
('Córdoba', 'CBA'),
('Santa Fe', 'SF'),
('Mendoza', 'MZA'),
('Salta', 'SAL'),
('Neuquén', 'NEQ'),
('Misiones', 'MIS'),
('Chaco', 'CHA'),
('Tucumán', 'TUC'),
('La Pampa', 'LP');

INSERT INTO Localidad (Cod_Localidad,CodProvincia_Localidad, Descripcion_Localidad) 
VALUES 
('lp', 'BA','La Plata'),
('mdp', 'CBA','Mar del Plata'),
('cc', 'SF','Córdoba Capital'),
('vcp', 'MZA','Villa Carlos Paz'),
('rso','SAL' ,'Rosario'),
('sfc','NEQ' ,'Santa Fe Capital'),
('mc','MIS' ,'Mendoza Capital'),
('sc', 'MIS','Salta Capital'),
('nc','CHA' ,'Neuquén Capital'),
('ps','TUC' ,'Posadas');


INSERT INTO Usuario (Cod_Usuario,Nombre_Usuario, Pass_Usuario, Administrador_Usuario, Estado_Usuario) 
VALUES 
(1,'usuario1', 'pass123', 0, TRUE),
(2,'usuario2', 'pass123', 0, TRUE),
(3,'usuario3', 'pass123', 0, TRUE),
(4,'usuario4', 'pass123', 0, TRUE),
(5,'usuario5', 'pass123', 0, TRUE),
(6,'admin1', 'adminpass', 0, TRUE),
(7,'usuario6', 'pass123', 0, TRUE),
(8,'usuario7', 'pass123', 0, TRUE),
(9,'usuario8', 'pass123', 0, TRUE),
(10,'usuario9', 'pass123', 0, TRUE);



INSERT INTO Cliente (DNI_Cliente, CodUsuario_Cliente, CUIL_Cliente, Nombre_Cliente, Apellido_Cliente, Sexo_Cliente, Nacionalidad_Cliente, FechaNacimiento_Cliente, Direccion_Cliente, Localidad_Cliente, Provincia_Cliente, Email_Cliente, Telefono_Cliente, Pass_Cliente, Estado_Cliente) 
VALUES 
('11111111', 1, '20-11111111-1', 'Juan', 'Perez', 'M', 'Argentina', '1985-01-15', 'Calle Falsa 123', 'cc', 'SF', 'juan.perez@mail.com', '123456789', 'usuario1', TRUE),
('22222222', 2, '20-22222222-2', 'Ana', 'Lopez', 'F', 'Argentina', '1990-02-10', 'Av. Siempreviva 742', 'lp', 'BA', 'ana.lopez@mail.com', '987654321', 'usuario2', TRUE),
('33333333', 3, '20-33333333-3', 'Carlos', 'Diaz', 'M', 'Argentina', '1975-03-05', 'Calle Luna 456', 'mc', 'MIS', 'carlos.diaz@mail.com', '123123123', 'usuario3', TRUE),
('44444444', 4, '20-44444444-4', 'Lucia', 'Gomez', 'F', 'Argentina', '1988-04-20', 'Calle Sol 789', 'mdp', 'CBA', 'lucia.gomez@mail.com', '321321321', 'usuario4', TRUE),
('55555555', 5, '20-55555555-5', 'Roberto', 'Martinez', 'M', 'Argentina', '1995-05-30', 'Calle Estrella 159', 'nc', 'CHA', 'roberto.martinez@mail.com', '321321', 'usuario5', TRUE),
('66666666', 6, '20-66666666-6', 'Marta', 'Sanchez', 'F', 'Argentina', '1970-06-25', 'Calle Mar 753', 'ps', 'TUC', 'marta.sanchez@mail.com', '789789789', 'admin1', TRUE),
('77777777', 7, '20-77777777-7', 'Jorge', 'Ramos', 'M', 'Argentina', '1980-07-14', 'Calle Tierra 951', 'rso', 'SAL', 'jorge.ramos@mail.com', '654654654', 'usuario6', TRUE),
('88888888', 8, '20-88888888-8', 'Clara', 'Fernandez', 'F', 'Argentina', '1992-08-08', 'Calle Fuego 357', 'sc', 'MIS', 'clara.fernandez@mail.com', '852852852', 'usuario7', TRUE),
('99999999', 9, '20-99999999-9', 'Diego', 'Alvarez', 'M', 'Argentina', '1986-09-10', 'Calle Viento 258', 'sfc', 'NEQ', 'diego.alvarez@mail.com', '147147147', 'usuario8', TRUE),
('10101010', 10, '20-10101010-0', 'Paula', 'Morales', 'F', 'Argentina', '1978-10-10', 'Calle Agua 159', 'vcp', 'MZA', 'paula.morales@mail.com', '369369369', 'usuario9', TRUE);


INSERT INTO tipo_cuenta(Cod_TipoCuenta,Descripcion_TipoCuenta)
VALUES
(1,'Caja de ahorro'),
(2,'Cuenta corriente');


INSERT INTO Cuenta (DNI_Cuenta,NumeroCuenta_Cuenta, Tipo_Cuenta, CBU_Cuenta, Saldo_Cuenta, FechaCreacion_Cuenta, Estado_Cuenta) 
VALUES 
(1, 1011, 1, '00000001000000000001', 20000.00, '2024-01-01', TRUE),
(2, 2022, 1, '00000002000000000002', 15000.00, '2024-02-01', TRUE),
(3, 3033, 2, '00000003000000000003', 50000.00, '2024-03-01', TRUE),
(4, 4044, 1, '00000004000000000004', 25000.00, '2024-04-01', TRUE),
(5, 5055, 2, '00000005000000000005', 10000.00, '2024-05-01', TRUE),
(6, 6066, 2, '00000006000000000006', 30000.00, '2024-06-01', TRUE),
(7, 7077, 1, '00000007000000000007', 70000.00, '2024-07-01', TRUE),
(8, 8088, 2, '00000008000000000008', 5000.00, '2024-08-01', TRUE),
(9, 9099, 1, '00000009000000000009', 60000.00, '2024-09-01', TRUE),
(10, 10010,1, '00000010000000000010', 40000.00, '2024-10-01', TRUE);


INSERT INTO Cuota (Cod_Cuota, NumeroCuotaPagar_Cuota, FechaVencimiento_Cuota, Monto_Cuota, Estado_Cuota, FechaPago_Cuota)
VALUES 
(1, 1, '2025-02-01', 8750.00, TRUE, '2025-02-02'),
(2, 2, '2025-03-01', 8750.00, TRUE, '2025-03-02'),
(3, 1, '2025-03-10', 9160.67, TRUE, '2025-03-11'),
(4, 2, '2025-04-10', 9160.67, FALSE, null),
(5, 1, '2025-05-20', 9160.67, FALSE, NULL),
(6, 2, '2025-06-20', 9160.67, FALSE, NULL),
(7, 1, '2025-05-05', 8660.67, TRUE, null),
(8, 2, '2025-06-05', 8660.67, TRUE, '2025-06-06'),
(9, 1, '2025-06-15', 7770.78, FALSE, null),
(10, 2, '2025-07-15', 7770.78, FALSE, null);


INSERT INTO tipo_movimiento(Cod_TipoMovimiento,Descripcion_TipoMovimiento)
VALUES
(1,'cuenta origen'),
(2,'cuenta destino');


INSERT INTO Movimiento (Cod_Movimiento,NumeroCuenta_Movimiento, CodTipo_Movimiento, Fecha_Movimiento, Importe_Movimiento, Descripcion_Movimiento) 
VALUES 
(1, 2022,1, '2024-01-02', 5000.00, 'Depósito en efectivo'),
(2, 1011,2, '2024-02-05', 2000.00, 'Pago de servicios'),
(3, 3033,1, '2024-03-10', 10000.00, 'Préstamo otorgado'),
(4, 2022,2, '2024-04-15', 15000.00, 'Transferencia recibida'),
(5, 1011,1, '2024-05-20', 500.00, 'Compra con tarjeta de débito'),
(6, 4044,2, '2024-06-25', 3000.00, 'Pago de cuota de préstamo'),
(7, 2022,2, '2024-07-30', 7000.00, 'Depósito de cheque'),
(8, 10010,2, '2024-08-05', 1000.00, 'Extracción de cajero'),
(9, 3033,1, '2024-09-12', 25000.00, 'Préstamo aprobado'),
(10, 4044,1, '2024-10-18', 5000.00, 'Pago parcial de préstamo');


INSERT INTO Prestamo (Cod_Prestamo,DNI_Prestamo, NumeroCuenta_Prestamo,CodCuota_Prestamo, Fecha_Prestamo, ImportePedido_Prestamo, ImportePagar_Prestamo, PlazoMeses_Prestamo, MontoMes_Prestamo, Estado_Prestamo)
VALUES 
(1, 1, 1011,1, '2024-01-05', 10000.00, 10500.00, 12, 875.00, 'Autorizado'),
(2, 2, 2022,2, '2024-02-15', 15000.00, 16500.00, 18, 916.67, 'Autorizado'),
(3, 3, 3033,3, '2024-03-25', 20000.00, 22000.00, 24, 916.67, 'Autorizado'),
(4, 4,10010 ,4, '2024-04-10', 5000.00, 5200.00, 6, 866.67, 'Autorizado'),
(5, 5, 5055,5, '2024-05-20', 25000.00, 28000.00, 36, 777.78, 'Autorizado'),
(6, 6, 4044,6, '2024-06-05', 12000.00, 13000.00, 12, 1083.33, 'Pendiente'),
(7, 7, 6066,7, '2024-07-15', 30000.00, 35000.00, 48, 729.17, 'Pendiente'),
(8, 8, 7077,8, '2024-08-25', 4000.00, 4200.00, 6, 700.00, 'Autorizado'),
(9, 9, 8088,9, '2024-09-10', 18000.00, 19000.00, 18, 1055.56, 'Autorizado'),
(10, 10, 9099,10, '2024-10-20', 22000.00, 25000.00, 36, 694.44, 'Pendiente');


-- Agregar la columna NombreUsuario_Cliente en la tabla cliente
ALTER TABLE cliente
ADD COLUMN NombreUsuario_Cliente VARCHAR(50);INSERT INTO Cuota (Cod_Cuota, NumeroCuotaPagar_Cuota, FechaVencimiento_Cuota, Monto_Cuota, Estado_Cuota, FechaPago_Cuota) VALUES  (1, 1, '2025-02-01', 8750.00, TRUE, '2025-02-02'), (2, 2, '2025-03-01', 8750.00, TRUE, '2025-03-02'), (3, 1, '2025-03-10', 9160.67, TRUE, '2025-03-11'), (4, 2, '2025-04-10', 9160.67, FALSE, '2000-00-00'), (5, 1, '2025-05-20', 9160.67, FALSE, NULL), (6, 2, '2025-06-20', 9160.67, FALSE, NULL), (7, 1, '2025-05-05', 8660.67, TRUE, '2025-05-06'), (8, 2, '2025-06-05', 8660.67, TRUE, '2025-06-06'), (9, 1, '2025-06-15', 7770.78, FALSE, '2000-00-00'), (10, 2, '2025-07-15', 7770.78, FALSE, '2000-00-00')

-- Volver Admin un registro

UPDATE usuario SET Administrador_Usuario = 1 WHERE Cod_Usuario = 6;