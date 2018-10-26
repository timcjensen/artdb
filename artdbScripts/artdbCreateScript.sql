-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema artdb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `artdb` ;

-- -----------------------------------------------------
-- Schema artdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `artdb` DEFAULT CHARACTER SET utf8 ;
USE `artdb` ;

-- -----------------------------------------------------
-- Table `artdb`.`Portfolio`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `artdb`.`Portfolio` ;

CREATE TABLE IF NOT EXISTS `artdb`.`Portfolio` (
  `portfolio_id` INT NOT NULL,
  PRIMARY KEY (`portfolio_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `artdb`.`Artist`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `artdb`.`Artist` ;

CREATE TABLE IF NOT EXISTS `artdb`.`Artist` (
  `artist_id` INT NOT NULL,
  `artist_name` VARCHAR(200) NOT NULL,
  `life_date` VARCHAR(45) NULL,
  `nationality` VARCHAR(45) NULL,
  `portfolio_id` INT NOT NULL,
  PRIMARY KEY (`artist_id`, `artist_name`, `portfolio_id`),
  INDEX `fk_Artist_Portfolio1_idx` (`portfolio_id` ASC),
  CONSTRAINT `fk_Artist_Portfolio1`
    FOREIGN KEY (`portfolio_id`)
    REFERENCES `artdb`.`Portfolio` (`portfolio_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `artdb`.`Culture_info`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `artdb`.`Culture_info` ;

CREATE TABLE IF NOT EXISTS `artdb`.`Culture_info` (
  `culture_id` INT NOT NULL,
  `continent` VARCHAR(20) NULL,
  `country` VARCHAR(45) NULL,
  PRIMARY KEY (`culture_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `artdb`.`Room`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `artdb`.`Room` ;

CREATE TABLE IF NOT EXISTS `artdb`.`Room` (
  `room_id` INT NOT NULL,
  `room_name` VARCHAR(200) NOT NULL,
  PRIMARY KEY (`room_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `artdb`.`Department`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `artdb`.`Department` ;

CREATE TABLE IF NOT EXISTS `artdb`.`Department` (
  `department_id` INT NOT NULL,
  `department_name` VARCHAR(200) NULL,
  PRIMARY KEY (`department_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `artdb`.`Exhibitions`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `artdb`.`Exhibitions` ;

CREATE TABLE IF NOT EXISTS `artdb`.`Exhibitions` (
  `exhibition_id` INT NOT NULL,
  `exhibition_title` VARCHAR(200) NULL,
  `exhibition_description` VARCHAR(200) NULL,
  `begin` VARCHAR(200) NULL,
  `end` VARCHAR(200) NULL,
  `display_date` VARCHAR(200) NULL,
  `department_id` INT NOT NULL,
  PRIMARY KEY (`exhibition_id`, `department_id`),
  INDEX `fk_Exhibitions_Department1_idx` (`department_id` ASC),
  CONSTRAINT `fk_Exhibitions_Department1`
    FOREIGN KEY (`department_id`)
    REFERENCES `artdb`.`Department` (`department_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `artdb`.`specs`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `artdb`.`specs` ;

CREATE TABLE IF NOT EXISTS `artdb`.`specs` (
  `spec_id` INT NOT NULL,
  `dimensions` VARCHAR(200) NULL,
  PRIMARY KEY (`spec_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `artdb`.`Object`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `artdb`.`Object` ;

CREATE TABLE IF NOT EXISTS `artdb`.`Object` (
  `object_id` INT NOT NULL,
  `title` VARCHAR(200) NULL,
  `description` VARCHAR(200) NULL,
  `signature` VARCHAR(200) NULL,
  `dated` VARCHAR(200) NULL,
  `markings` VARCHAR(200) NULL,
  `style` VARCHAR(200) NULL,
  `classification` VARCHAR(200) NULL,
  `approval` VARCHAR(200) NULL,
  `credit_line` VARCHAR(45) NULL,
  `accession_number` INT NULL,
  `artist_id` INT NOT NULL,
  `artist_name` VARCHAR(200) NOT NULL,
  `culture_id` INT NOT NULL,
  `room_id` INT NOT NULL,
  `department_id` INT NOT NULL,
  `exhibition_id` INT NULL,
  `spec_id` INT NOT NULL,
  PRIMARY KEY (`object_id`, `artist_id`, `artist_name`, `culture_id`, `room_id`, `department_id`, `spec_id`),
  INDEX `fk_Object_Artist_idx` (`artist_id` ASC, `artist_name` ASC),
  INDEX `fk_Object_Culture_info1_idx` (`culture_id` ASC),
  INDEX `fk_Object_Room1_idx` (`room_id` ASC),
  INDEX `fk_Object_Department1_idx` (`department_id` ASC),
  INDEX `fk_Object_Exhibitions1_idx` (`exhibition_id` ASC),
  INDEX `fk_Object_specs1_idx` (`spec_id` ASC),
  CONSTRAINT `fk_Object_Artist`
    FOREIGN KEY (`artist_id` , `artist_name`)
    REFERENCES `artdb`.`Artist` (`artist_id` , `artist_name`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Object_Culture_info1`
    FOREIGN KEY (`culture_id`)
    REFERENCES `artdb`.`Culture_info` (`culture_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Object_Room1`
    FOREIGN KEY (`room_id`)
    REFERENCES `artdb`.`Room` (`room_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Object_Department1`
    FOREIGN KEY (`department_id`)
    REFERENCES `artdb`.`Department` (`department_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Object_Exhibitions1`
    FOREIGN KEY (`exhibition_id`)
    REFERENCES `artdb`.`Exhibitions` (`exhibition_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Object_specs1`
    FOREIGN KEY (`spec_id`)
    REFERENCES `artdb`.`specs` (`spec_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;