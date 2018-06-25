-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 18-05-2018 a las 01:01:50
-- Versión del servidor: 10.1.30-MariaDB
-- Versión de PHP: 5.6.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `facturacion`
--
CREATE DATABASE IF NOT EXISTS `facturacion` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `facturacion`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

CREATE TABLE IF NOT EXISTS `clientes` (
  `dni` varchar(10) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `direccion` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`dni`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `clientes`
--

INSERT INTO `clientes` (`dni`, `nombre`, `direccion`) VALUES
('1111', 'Francisco Gómez', '24.1.2'),
('1234', 'Norberto Díaz', '2.2.2');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `facturas`
--

CREATE TABLE IF NOT EXISTS `facturas` (
  `identificador` varchar(10) NOT NULL,
  `id_cliente` varchar(10) NOT NULL,
  `importe` double DEFAULT NULL,
  `fecha_pago` date DEFAULT NULL,
  PRIMARY KEY (`identificador`) USING BTREE,
  KEY `id_cliente` (`id_cliente`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `facturas`
--

INSERT INTO `facturas` (`identificador`, `id_cliente`, `importe`, `fecha_pago`) VALUES
('1', '1111', 22, '2018-01-02'),
('2', '1111', 33, NULL),
('3', '1234', 123, '2018-05-21'),
('4', '1234', 100, NULL);

-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `vfacturas`
-- (Véase abajo para la vista actual)
--
CREATE TABLE IF NOT EXISTS `vfacturas` (
`dni` varchar(10)
,`nombre` varchar(45)
,`direccion` varchar(45)
,`identificador` varchar(10)
,`id_cliente` varchar(10)
,`importe` double
,`fecha_pago` date
);

-- --------------------------------------------------------

--
-- Estructura para la vista `vfacturas`
--
DROP TABLE IF EXISTS `vfacturas`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vfacturas`  AS  select `clientes`.`dni` AS `dni`,`clientes`.`nombre` AS `nombre`,`clientes`.`direccion` AS `direccion`,`facturas`.`identificador` AS `identificador`,`facturas`.`id_cliente` AS `id_cliente`,`facturas`.`importe` AS `importe`,`facturas`.`fecha_pago` AS `fecha_pago` from (`clientes` join `facturas`) where (`clientes`.`dni` = `facturas`.`id_cliente`) ;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `facturas`
--
ALTER TABLE `facturas`
  ADD CONSTRAINT `id_cliente` FOREIGN KEY (`id_cliente`) REFERENCES `clientes` (`dni`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
