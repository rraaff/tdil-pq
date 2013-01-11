DROP TABLE IF EXISTS BOUSER;
DROP TABLE IF EXISTS SYSTEMUSER;
DROP TABLE IF EXISTS TICKETS;
DROP TABLE IF EXISTS INSTANT_WIN;
DROP TABLE IF EXISTS LOGINS;

CREATE  TABLE `BOUSER` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `nombre` VARCHAR(255) NULL ,
  `apellido` VARCHAR(255) NULL ,
  `email` VARCHAR(150) NULL ,
  `usuario` VARCHAR(100) NULL ,
  `password` VARCHAR(4000) NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `BOUNAME` (`usuario` ASC) );

INSERT INTO BOUSER(nombre, apellido, email, usuario, password)
VALUES ('lasmoras', 'lasmoras', 'aas@ssdd.com', 'lasmoras', 'lasmoras'); 

INSERT INTO BOUSER(nombre, apellido, email, usuario, password)
VALUES ('pablo', 'mendoza', 'aas@ssdd.com', 'mendoza', 'mendoza'); 

CREATE  TABLE `SYSTEMUSER` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `nombre` VARCHAR(255) NULL ,
  `apellido` VARCHAR(255) NULL ,
  `documento` VARCHAR(100) NULL ,
  `edad` INT NOT NULL ,
  `email` VARCHAR(150) NULL ,
  `usuario` VARCHAR(100) NULL ,
  `password` VARCHAR(4000) NULL ,
  `fechaCreacion` DATETIME NOT NULL,
  PRIMARY KEY (`id`) ,
  INDEX `UNAME` (`usuario` ASC) );
  
CREATE  TABLE `TICKETS` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `systemuserID` INT NOT NULL ,
  `ticket` VARCHAR(255) NULL ,
  `supermercado` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `botellas` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `fechaCarga` DATETIME NOT NULL,
  `ganador` INT NULL ,
  PRIMARY KEY (`id`),
  INDEX `TicketCode` (`ticket` ASC) );
  
CREATE  TABLE `INSTANT_WIN` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `ticketID` INT NULL ,
  `descripcion` VARCHAR(400) NOT NULL ,
  `mensaje` MEDIUMTEXT NULL ,
  `inicio` DATETIME NOT NULL,
  `fin` DATETIME NOT NULL,
  `name` VARCHAR(100) NOT NULL,
  `type` VARCHAR(30) NOT NULL,
  `size` INT NOT NULL,
  `content` MEDIUMBLOB NOT NULL,
  PRIMARY KEY (`id`) );
  
CREATE TABLE `LOGINS` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `systemUserID` INT NOT NULL ,
  `fechaLogin` DATETIME NOT NULL,
  PRIMARY KEY (`id`) ,
  INDEX `UNAME` (`systemUserID` ASC) );