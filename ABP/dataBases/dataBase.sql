-- MySQL Workbench Synchronization
-- Generated: 2022-04-01 22:38
-- Model: New Model
-- Version: 1.0
-- Project: Name of the project
-- Author: Gasler

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

CREATE SCHEMA IF NOT EXISTS `registro_examen` DEFAULT CHARACTER SET utf8 ;

CREATE TABLE IF NOT EXISTS `registro_examen`.`estudiantes` (
  `id` INT(11) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `apellidos` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_estudiante_UNIQUE` (`id` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `registro_examen`.`registro` (
  `id_registro` INT(11) NOT NULL,
  `nota` INT(11) NOT NULL DEFAULT 0,
  `pruebas_id` INT(11) NOT NULL,
  `estudiantes_id` INT(11) NOT NULL,
  `creaciones_id` INT(11) NOT NULL,
  `docentes_id` INT(11) NOT NULL,
  PRIMARY KEY (`id_registro`, `pruebas_id`, `estudiantes_id`, `creaciones_id`, `docentes_id`),
  INDEX `fk_registros_practicas1_idx` (`pruebas_id` ASC) ,
  INDEX `fk_registro_estudiantes1_idx` (`estudiantes_id` ASC) ,
  INDEX `fk_registro_creaciones1_idx` (`creaciones_id` ASC, `docentes_id` ASC) ,
  CONSTRAINT `fk_registros_practicas1`
    FOREIGN KEY (`pruebas_id`)
    REFERENCES `registro_examen`.`pruebas` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_registro_estudiantes1`
    FOREIGN KEY (`estudiantes_id`)
    REFERENCES `registro_examen`.`estudiantes` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_registro_creaciones1`
    FOREIGN KEY (`creaciones_id` , `docentes_id`)
    REFERENCES `registro_examen`.`creaciones` (`id` , `docentes_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `registro_examen`.`docentes` (
  `id` INT(11) NOT NULL,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `apellidos` VARCHAR(100) NULL DEFAULT NULL,
  `password` VARCHAR(12) NULL DEFAULT 'abc123',
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `registro_examen`.`pruebas` (
  `id` INT(11) NOT NULL,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `pregunta` VARCHAR(45) NULL DEFAULT NULL,
  `op_a` VARCHAR(45) NULL DEFAULT NULL,
  `op_b` VARCHAR(45) NULL DEFAULT NULL,
  `op_c` VARCHAR(45) NULL DEFAULT NULL,
  `res` VARCHAR(45) NULL DEFAULT NULL,
  `tipo` VARCHAR(45) NULL DEFAULT 'Practica',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `registro_examen`.`datos` (
  `id` INT(11) NOT NULL,
  `pregunta` VARCHAR(255) NULL DEFAULT NULL,
  `op_a` VARCHAR(255) NULL DEFAULT NULL,
  `op_b` VARCHAR(255) NULL DEFAULT NULL,
  `op_c` VARCHAR(255) NULL DEFAULT NULL,
  `respuesta` VARCHAR(255) NULL DEFAULT NULL,
  `creaciones_id` INT(11) NOT NULL,
  `creaciones_docentes_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`, `creaciones_id`, `creaciones_docentes_id`),
  UNIQUE INDEX `op_a_UNIQUE` (`op_a` ASC) ,
  UNIQUE INDEX `pregunta_UNIQUE` (`pregunta` ASC) ,
  UNIQUE INDEX `op_b_UNIQUE` (`op_b` ASC) ,
  UNIQUE INDEX `op_c_UNIQUE` (`op_c` ASC) ,
  UNIQUE INDEX `respuesta_UNIQUE` (`respuesta` ASC) ,
  INDEX `fk_datos_creaciones1_idx` (`creaciones_id` ASC, `creaciones_docentes_id` ASC) ,
  CONSTRAINT `fk_datos_creaciones1`
    FOREIGN KEY (`creaciones_id` , `creaciones_docentes_id`)
    REFERENCES `registro_examen`.`creaciones` (`id` , `docentes_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `registro_examen`.`creaciones` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NULL DEFAULT NULL,
  `docentes_id` INT(11) NOT NULL,
  `tipo` VARCHAR(45) NOT NULL DEFAULT 'Practica',
  PRIMARY KEY (`id`, `docentes_id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) ,
  INDEX `fk_creaciones_docentes1_idx` (`docentes_id` ASC) ,
  CONSTRAINT `fk_creaciones_docentes1`
    FOREIGN KEY (`docentes_id`)
    REFERENCES `registro_examen`.`docentes` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
