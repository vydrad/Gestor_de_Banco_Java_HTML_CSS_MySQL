CREATE DATABASE  IF NOT EXISTS `banco` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `banco`;
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
  `NombreUsuario_Cliente` varchar(50) NOT NULL,
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
  KEY `FK_Cliente_Usuario_idx1` (`CodUsuario_Cliente`,`NombreUsuario_Cliente`),
  CONSTRAINT `FK_Cliente_Localidad` FOREIGN KEY (`Localidad_Cliente`) REFERENCES `localidad` (`Cod_Localidad`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_Cliente_Provincia` FOREIGN KEY (`Provincia_Cliente`) REFERENCES `localidad` (`CodProvincia_Localidad`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_Cliente_Usuario` FOREIGN KEY (`CodUsuario_Cliente`, `NombreUsuario_Cliente`) REFERENCES `usuario` (`Cod_Usuario`, `Nombre_Usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--


--
-- Table structure for table `cuenta`
--

DROP TABLE IF EXISTS `cuenta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cuenta` (
  `NumeroCuenta_Cuenta` int(11) NOT NULL,
  `CBU_Cuenta` varchar(45) NOT NULL,
  `DNI_Cuenta` int(11) DEFAULT NULL,
  `Tipo_Cuenta` int(10) NOT NULL,
  `FechaCreacion_Cuenta` date DEFAULT NULL,
  `Saldo_Cuenta` decimal(15,2) NOT NULL DEFAULT '10000.00',
  `Estado_Cuenta` bit(1) DEFAULT NULL,
  PRIMARY KEY (`NumeroCuenta_Cuenta`,`CBU_Cuenta`),
  KEY `FK_Cuenta_TipoCuenta_idx` (`Tipo_Cuenta`),
  KEY `FK_Cuenta_Cliente_idx` (`DNI_Cuenta`),
  CONSTRAINT `FK_Cuenta_Cliente` FOREIGN KEY (`DNI_Cuenta`) REFERENCES `cliente` (`DNI_Cliente`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `FK_Cuenta_TipoCuenta` FOREIGN KEY (`Tipo_Cuenta`) REFERENCES `tipo_cuenta` (`Cod_TipoCuenta`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cuenta`
--


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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movimiento`
--



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
  CONSTRAINT `FK_Prestamo_Cuenta_Cliente` FOREIGN KEY (`DNI_Prestamo`) REFERENCES `cuenta` (`DNI_Cuenta`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `FK_Prestamo_Cuota` FOREIGN KEY (`CodCuota_Prestamo`) REFERENCES `cuota` (`Cod_Cuota`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prestamo`
--



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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_cuenta`
--



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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_movimiento`
--


--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `Cod_Usuario` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre_Usuario` varchar(50) NOT NULL,
  `Pass_Usuario` varchar(100) NOT NULL,
  `Administrador_Usuario` bit(1) DEFAULT NULL,
  `Estado_Usuario` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`Cod_Usuario`,`Nombre_Usuario`),
  KEY `FK_PASS` (`Pass_Usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

