-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.0.45-community-nt


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema tuprofesional
--

CREATE DATABASE IF NOT EXISTS tuprofesional;
USE tuprofesional;

--
-- Definition of table `adicionales`
--

DROP TABLE IF EXISTS `adicionales`;
CREATE TABLE `adicionales` (
  `idadicionales` int(10) unsigned NOT NULL auto_increment,
  `dni` varchar(10) NOT NULL,
  `titulo` varchar(45) NOT NULL,
  `descripcion` varchar(150) NOT NULL,
  PRIMARY KEY  (`idadicionales`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `adicionales`
--

/*!40000 ALTER TABLE `adicionales` DISABLE KEYS */;
INSERT INTO `adicionales` (`idadicionales`,`dni`,`titulo`,`descripcion`) VALUES 
 (3,'485692B','Limpiezas baratas','Pruebe nuestros increibles servicios de limpieza en su domicilio o local comercial a un modico precio!!'),
 (4,'698532C','Limpieza a buen precio','Pruebe nuestros increibles servicios de limpieza en su domicilio o local comercial a un modico precio!!'),
 (5,'478596D','Fontaneria para todo el mundo','Pruebe nuestros increibles servicios de limpieza en su domicilio o local comercial a un modico precio!!'),
 (6,'584963E','Fontaneria para todos!','Pruebe nuestros increibles servicios de limpieza en su domicilio o local comercial a un modico precio!!'),
 (7,'111222A','Trabajos de Fontaneria','Pruebe nuestros increibles servicios de limpieza en su domicilio o local comercial a un modico precio!!'),
 (8,'673902A','Pinto casas color rosa','Otros colores a peticion.');
/*!40000 ALTER TABLE `adicionales` ENABLE KEYS */;


--
-- Definition of table `categorias`
--

DROP TABLE IF EXISTS `categorias`;
CREATE TABLE `categorias` (
  `id_categoria` int(10) unsigned NOT NULL auto_increment,
  `empleo` varchar(45) NOT NULL,
  PRIMARY KEY  (`id_categoria`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `categorias`
--

/*!40000 ALTER TABLE `categorias` DISABLE KEYS */;
INSERT INTO `categorias` (`id_categoria`,`empleo`) VALUES 
 (1,'Limpieza'),
 (2,'Carpintero'),
 (3,'Fontanero'),
 (4,'Electricista'),
 (5,'Pintor'),
 (6,'Albannil');
/*!40000 ALTER TABLE `categorias` ENABLE KEYS */;


--
-- Definition of table `datos`
--

DROP TABLE IF EXISTS `datos`;
CREATE TABLE `datos` (
  `id_datos` int(10) unsigned NOT NULL auto_increment,
  `dni` varchar(12) NOT NULL,
  `empresa` varchar(45) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `telefono` int(10) unsigned NOT NULL,
  `email` varchar(45) NOT NULL,
  `contrasena` varchar(45) NOT NULL,
  `urgente` tinyint(1) NOT NULL,
  PRIMARY KEY  (`id_datos`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1 PACK_KEYS=1;

--
-- Dumping data for table `datos`
--

/*!40000 ALTER TABLE `datos` DISABLE KEYS */;
INSERT INTO `datos` (`id_datos`,`dni`,`empresa`,`nombre`,`telefono`,`email`,`contrasena`,`urgente`) VALUES 
 (4,'485692B','Limpiezas Pepe','Pepe Rodriguez',258436952,'pepe@limpiezaspepe.com','pepe222',1),
 (5,'698532C','Jose Limpieza','Jose Perez',963258459,'jose@joselimpieza.com','jose222',0),
 (6,'478596D','Fontanerias Pepe','Pepe Rodriguez',478591445,'pepe@fontaneriaspepe.com','pepe222',1),
 (7,'584963E','Jose Fontaneria','Jose Perez',745822366,'jose@josefontaneria.com','jose222',0),
 (9,'673902A','Empresa de Pinturas','Pinturitas',666333666,'pintor@mail.com','12341234',1);
/*!40000 ALTER TABLE `datos` ENABLE KEYS */;


--
-- Definition of table `empleados`
--

DROP TABLE IF EXISTS `empleados`;
CREATE TABLE `empleados` (
  `index` int(10) unsigned NOT NULL auto_increment,
  `empleo` varchar(45) NOT NULL,
  `dni` varchar(12) NOT NULL,
  PRIMARY KEY  (`index`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `empleados`
--

/*!40000 ALTER TABLE `empleados` DISABLE KEYS */;
INSERT INTO `empleados` (`index`,`empleo`,`dni`) VALUES 
 (3,'Limpieza','485692B'),
 (4,'Limpieza','698532C'),
 (5,'Fontanero','478596D'),
 (6,'Fontanero','584963E'),
 (8,'Pintor','673902A');
/*!40000 ALTER TABLE `empleados` ENABLE KEYS */;


--
-- Definition of table `localizacion`
--

DROP TABLE IF EXISTS `localizacion`;
CREATE TABLE `localizacion` (
  `idlocalizacion` int(10) unsigned NOT NULL auto_increment,
  `dni` varchar(12) NOT NULL,
  `latitud` double NOT NULL,
  `longitud` double NOT NULL,
  PRIMARY KEY  (`idlocalizacion`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `localizacion`
--

/*!40000 ALTER TABLE `localizacion` DISABLE KEYS */;
INSERT INTO `localizacion` (`idlocalizacion`,`dni`,`latitud`,`longitud`) VALUES 
 (3,'485692B',40.398859,-3.744467),
 (4,'698532C',40.403645,-3.740321),
 (5,'478596D',40.392214,-3.736529),
 (6,'584963E',40.402281,-3.735135),
 (8,'673902A',40.3986791,-3.7364164);
/*!40000 ALTER TABLE `localizacion` ENABLE KEYS */;


--
-- Definition of table `puntuacion`
--

DROP TABLE IF EXISTS `puntuacion`;
CREATE TABLE `puntuacion` (
  `idpuntuacion` int(10) unsigned NOT NULL auto_increment,
  `dni` varchar(10) NOT NULL,
  `review` varchar(150) default NULL,
  `puntuacion` double NOT NULL,
  PRIMARY KEY  (`idpuntuacion`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `puntuacion`
--

/*!40000 ALTER TABLE `puntuacion` DISABLE KEYS */;
INSERT INTO `puntuacion` (`idpuntuacion`,`dni`,`review`,`puntuacion`) VALUES 
 (1,'25698321A','Fatal, vaya chapuza',1),
 (2,'25698321A','Genial!! Un trabajo excelente',4),
 (3,'123456A','Zoquete',4),
 (4,'485692B','Me dejo todo un poco sucio, sucio, pero es muy majo.',1),
 (5,'698532C','Un gran trabajo',5),
 (6,'698532C','Que tio mas limpio!',5),
 (7,'478596D','Vaya chapuza mas grande me ha hecho.',1),
 (8,'478596D','¿Pero donde le han ensennado a este hombre?',1),
 (9,'584963E','Vaya colgao',2),
 (10,'584963E','Este tio sabe lo que hace',4),
 (11,'673902A','Venga hasta luego',5);
/*!40000 ALTER TABLE `puntuacion` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
