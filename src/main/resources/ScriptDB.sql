-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 01, 2021 at 04:55 PM
-- Server version: 10.4.18-MariaDB
-- PHP Version: 8.0.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `dbproyectocursadataller1`
--
CREATE DATABASE IF NOT EXISTS `dbproyectocursadataller1` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `dbproyectocursadataller1`;

-- --------------------------------------------------------

--
-- Table structure for table `cita`
--

CREATE TABLE `cita` (
                        `id` bigint(20) NOT NULL,
                        `created_at` datetime(6) DEFAULT NULL,
                        `fecha` date DEFAULT NULL,
                        `hora` time DEFAULT NULL,
                        `updated_at` datetime(6) DEFAULT NULL,
                        `especialidad_id` bigint(20) DEFAULT NULL,
                        `medico_id` bigint(20) DEFAULT NULL,
                        `paciente_id` bigint(20) DEFAULT NULL,
                        `tipoCita_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `citahistoria`
--

CREATE TABLE `citahistoria` (
                                `id` bigint(20) NOT NULL,
                                `archivo` varchar(255) DEFAULT NULL,
                                `created_at` datetime(6) DEFAULT NULL,
                                `observacion` varchar(255) DEFAULT NULL,
                                `cita_id` bigint(20) DEFAULT NULL,
                                `estado_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `especialidad`
--

CREATE TABLE `especialidad` (
                                `id` bigint(20) NOT NULL,
                                `descripcion` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `especialidad_medico`
--

CREATE TABLE `especialidad_medico` (
                                       `especialidad_id` bigint(20) NOT NULL,
                                       `medico_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `estado`
--

CREATE TABLE `estado` (
                          `id` bigint(20) NOT NULL,
                          `descripcion` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `persona`
--

CREATE TABLE `persona` (
                           `id` bigint(20) NOT NULL,
                           `apellido` varchar(255) DEFAULT NULL,
                           `matricula` varchar(255) DEFAULT NULL,
                           `nombre` varchar(255) DEFAULT NULL,
                           `numeroAfiliado` varchar(255) DEFAULT NULL,
                           `numeroDocumento` varchar(255) DEFAULT NULL,
                           `sexo` varchar(255) DEFAULT NULL,
                           `tipoDocumento` varchar(255) DEFAULT NULL,
                           `email` varchar(100) NOT NULL,
                           `fechaNacimiento` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `persona`
--

    INSERT INTO `persona` (`id`, `apellido`, `matricula`, `nombre`, `numeroAfiliado`, `numeroDocumento`, `sexo`, `tipoDocumento`, `email`, `fechaNacimiento`) VALUES
    (1, 'Herrera', '123', 'Nicolas', NULL, '1321321', 'm', 'dni', '', ''),
    (82, 'Messi', '', 'Lionel', '1', '14819950', 'Masculino', 'DNI', 'lmessi@gmail.com', ''),
    (83, 'Maradon', '', 'Diego', '1010', '14819950', 'Masculino', 'DNI', 'dmaradona@gmail.com', ''),
    (84, 'Herrera', '', 'Nicolas', '3276', '32413382', 'Masculino', 'DNI', 'nherrera3276@gmail.com', ''),
    (86, 'MUNGIELLO', '', 'JOSE LUIS', '9999', '312312', 'Masculino', 'DNI', 'joselmungiello@hotmail.com', ''),
    (87, 'dasdsada', '', 'asdas', '3123123', '14819950', 'Masculino', 'DNI', 'asdasdas', ''),
    (88, 'a', '', 'a', '312321', '21312', 'Masculino', 'DNI', 'pulga@gmail.com', ''),
    (89, 'a', '', 'a', '1312312', '14819950', 'Masculino', 'DNI', 'tallerUnoPruebas@gmail.com', ''),
    (90, 'Herrera', '', 'Nicolas', '99993123', '14819950', 'Masculino', 'DNI', 'jherreradasdasdas3276@gmail.com', '29/02/2020');

-- --------------------------------------------------------

--
-- Table structure for table `tipocita`
--

CREATE TABLE `tipocita` (
                            `id` bigint(20) NOT NULL,
                            `descripcion` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `ubicacion`
--

CREATE TABLE `ubicacion` (
                             `id` bigint(20) NOT NULL,
                             `lat_actual` float NOT NULL,
                             `long_actual` float NOT NULL,
                             `usuario_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `usuario`
--

CREATE TABLE `usuario` (
                           `id` bigint(20) NOT NULL,
                           `email` varchar(255) DEFAULT NULL,
                           `password` varchar(255) DEFAULT NULL,
                           `rol` varchar(255) DEFAULT NULL,
                           `persona_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `usuario`
--

INSERT INTO `usuario` (`id`, `email`, `password`, `rol`, `persona_id`) VALUES
(2, 'jherrera3276@gmail.com', '$2a$10$RdAzdAJA2QFEynljDhDrQunVHM0PbJLznCaw9q9GjJ4NOqOf00H8.', 'Administrador', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cita`
--
ALTER TABLE `cita`
    ADD PRIMARY KEY (`id`),
  ADD KEY `FKh0xa3kjv8bt1c87f8c3wwkrv1` (`especialidad_id`),
  ADD KEY `FKh3hpw8ld8953mlx2hadyxsglg` (`medico_id`),
  ADD KEY `FKkcdsbj014ee29mjph2humj4d0` (`paciente_id`),
  ADD KEY `FKn3o2v6825xj424a4c0xv36u23` (`tipoCita_id`);

--
-- Indexes for table `citahistoria`
--
ALTER TABLE `citahistoria`
    ADD PRIMARY KEY (`id`),
  ADD KEY `FKj0hv3g8m3o86c7twacej45k3n` (`cita_id`),
  ADD KEY `FK2l1qtf6dlo9jf2dnlxrslqnuh` (`estado_id`);

--
-- Indexes for table `especialidad`
--
ALTER TABLE `especialidad`
    ADD PRIMARY KEY (`id`);

--
-- Indexes for table `especialidad_medico`
--
ALTER TABLE `especialidad_medico`
    ADD KEY `FKs2hptidhhor9l62s77sc56otl` (`medico_id`),
  ADD KEY `FKocg7yj9ixqa7qxabdqqnxw9hk` (`especialidad_id`);

--
-- Indexes for table `estado`
--
ALTER TABLE `estado`
    ADD PRIMARY KEY (`id`);

--
-- Indexes for table `persona`
--
ALTER TABLE `persona`
    ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tipocita`
--
ALTER TABLE `tipocita`
    ADD PRIMARY KEY (`id`);

--
-- Indexes for table `ubicacion`
--
ALTER TABLE `ubicacion`
    ADD PRIMARY KEY (`id`),
  ADD KEY `FK8yjql6sdrvc6530ffhbh4edk` (`usuario_id`);

--
-- Indexes for table `usuario`
--
ALTER TABLE `usuario`
    ADD PRIMARY KEY (`id`),
  ADD KEY `FKpwmov35tuwavb70fabk8iusg` (`persona_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `cita`
--
ALTER TABLE `cita`
    MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `citahistoria`
--
ALTER TABLE `citahistoria`
    MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `especialidad`
--
ALTER TABLE `especialidad`
    MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `estado`
--
ALTER TABLE `estado`
    MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `persona`
--
ALTER TABLE `persona`
    MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=91;

--
-- AUTO_INCREMENT for table `tipocita`
--
ALTER TABLE `tipocita`
    MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `ubicacion`
--
ALTER TABLE `ubicacion`
    MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `usuario`
--
ALTER TABLE `usuario`
    MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=62;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `cita`
--
ALTER TABLE `cita`
    ADD CONSTRAINT `FKh0xa3kjv8bt1c87f8c3wwkrv1` FOREIGN KEY (`especialidad_id`) REFERENCES `especialidad` (`id`),
  ADD CONSTRAINT `FKh3hpw8ld8953mlx2hadyxsglg` FOREIGN KEY (`medico_id`) REFERENCES `usuario` (`id`),
  ADD CONSTRAINT `FKkcdsbj014ee29mjph2humj4d0` FOREIGN KEY (`paciente_id`) REFERENCES `usuario` (`id`),
  ADD CONSTRAINT `FKn3o2v6825xj424a4c0xv36u23` FOREIGN KEY (`tipoCita_id`) REFERENCES `tipocita` (`id`);

--
-- Constraints for table `citahistoria`
--
ALTER TABLE `citahistoria`
    ADD CONSTRAINT `FK2l1qtf6dlo9jf2dnlxrslqnuh` FOREIGN KEY (`estado_id`) REFERENCES `estado` (`id`),
  ADD CONSTRAINT `FKj0hv3g8m3o86c7twacej45k3n` FOREIGN KEY (`cita_id`) REFERENCES `cita` (`id`);

--
-- Constraints for table `especialidad_medico`
--
ALTER TABLE `especialidad_medico`
    ADD CONSTRAINT `FKocg7yj9ixqa7qxabdqqnxw9hk` FOREIGN KEY (`especialidad_id`) REFERENCES `especialidad` (`id`),
  ADD CONSTRAINT `FKs2hptidhhor9l62s77sc56otl` FOREIGN KEY (`medico_id`) REFERENCES `usuario` (`id`);

--
-- Constraints for table `ubicacion`
--
ALTER TABLE `ubicacion`
    ADD CONSTRAINT `FK8yjql6sdrvc6530ffhbh4edk` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`);

--
-- Constraints for table `usuario`
--
ALTER TABLE `usuario`
    ADD CONSTRAINT `FKpwmov35tuwavb70fabk8iusg` FOREIGN KEY (`persona_id`) REFERENCES `persona` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;