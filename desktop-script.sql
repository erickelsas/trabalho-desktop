SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

CREATE SCHEMA IF NOT EXISTS `desktop_erick` DEFAULT CHARACTER SET utf8 ;

USE `desktop_erick` ;

CREATE TABLE IF NOT EXISTS `desktop_erick`.`Patient` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `role` INT NOT NULL,
  `rg` VARCHAR(9) NOT NULL,
  `sus` INT(6) NOT NULL,
  `bloodType` VARCHAR(3) NOT NULL,
  PRIMARY KEY (`id`, `rg`),
  UNIQUE INDEX `rg_UNIQUE` (`rg` ASC),
  UNIQUE INDEX `sus_UNIQUE` (`sus` ASC),
  UNIQUE INDEX `id_UNIQUE` (`ID` ASC))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `desktop_erick`.`Doctor` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `role` INT NOT NULL,
  `rg` VARCHAR(9) NOT NULL,
  `espec` VARCHAR(45) NOT NULL,
  `crmNum` INT NOT NULL,
  `crmState` VARCHAR(2) NOT NULL,
  PRIMARY KEY (`id`, `rg`),
  UNIQUE INDEX `rg_UNIQUE` (`rg` ASC),
  UNIQUE INDEX `id_UNIQUE` (`ID` ASC))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `desktop_erick`.`Appointment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `Patient_id` INT NOT NULL,
  `Patient_rg` VARCHAR(9) NOT NULL,
  `Doctor_id` INT NOT NULL,
  `Doctor_rg` VARCHAR(9) NOT NULL,
  `role` INT NOT NULL,
  `appointmentDate` VARCHAR(45) NOT NULL,
  `deleted` TINYINT NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`ID` ASC),
  INDEX `fk_Appointment_Patient_idx` (`Patient_id` ASC, `Patient_rg` ASC),
  INDEX `fk_Appointment_Doctor1_idx` (`Doctor_id` ASC, `Doctor_rg` ASC),
  CONSTRAINT `fk_Appointment_Patient`
    FOREIGN KEY (`Patient_id` , `Patient_rg`)
    REFERENCES `desktop_erick`.`Patient` (`id` , `rg`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Appointment_Doctor1`
    FOREIGN KEY (`Doctor_id` , `Doctor_rg`)
    REFERENCES `desktop_erick`.`Doctor` (`id` , `rg`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

INSERT INTO patient (name, role, rg, sus, bloodType)
VALUES
  ('João da Silva', 1, '123456789', '987654', 'A+'),
  ('Maria Oliveira', 1, '987654321', '123456', 'B-'),
  ('Pedro Santos', 1, '654321987', '456789', 'O+'),
  ('Ana Souza', 1, '321654987', '789456', 'AB+'),
  ('José Pereira', 1, '987123654', '654123', 'A-'),
  ('Mariana Costa', 1, '789321456', '321789', 'B+'),
  ('Antônio Almeida', 1, '456789123', '987321', 'O-'),
  ('Sandra Ribeiro', 1, '654987321', '123789', 'AB-'),
  ('Carlos Gomes', 1, '321789456', '789321', 'A+'),
  ('Fernanda Santos', 1, '789456123', '456987', 'O+');
  
INSERT INTO doctor(name, role, rg, espec, crmNum, crmState)
VALUES
  ('José Silva', 2, '234567890', 'Cardiologia', '123456', 'SP'),
  ('Ana Oliveira', 2, '876543219', 'Dermatologia', '987654', 'RJ'),
  ('Pedro Almeida', 2, '543219876', 'Pediatria', '654321', 'MG'),
  ('Maria Pereira', 2, '321789654', 'Ortopedia', '321654', 'RS'),
  ('João Santos', 2, '789654123', 'Ginecologia', '987123', 'SC'),
  ('Mariana Costa', 2, '654789321', 'Oftalmologia', '789321', 'BA'),
  ('Carlos Gomes', 2, '456321789', 'Psiquiatria', '456789', 'PE'),
  ('Sandra Ribeiro', 2, '987456321', 'Endocrinologia', '654987', 'CE'),
  ('Fernanda Souza', 2, '321456789', 'Otorrinolaringologia', '321789', 'PR'),
  ('Antônio Lima', 2, '789123456', 'Urologia', '789456', 'PA');
  
INSERT INTO appointment (Patient_id, Patient_rg, Doctor_id, Doctor_rg, role, appointmentDate)
VALUES
  (1, '123456789', 1, '234567890', 3, '01/06/2023 09:00'),
  (2, '987654321', 2, '876543219', 3, '01/06/2023 10:30'),
  (3, '654321987', 3, '543219876', 3, '01/06/2023 11:45'),
  (4, '321654987', 4, '321789654', 3, '02/06/2023 14:15'),
  (5, '987123654', 5, '789654123', 3, '02/06/2023 15:30'),
  (6, '789321456', 6, '654789321', 3, '03/06/2023 10:00'),
  (7, '456789123', 7, '456321789', 3, '03/06/2023 11:30'),
  (8, '654987321', 8, '987456321', 3, '04/06/2023 13:45'),
  (9, '321789456', 9, '321456789', 3, '04/06/2023 15:00'),
  (10, '789456123', 10, '789123456', 3, '05/06/2023 16:30');

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;