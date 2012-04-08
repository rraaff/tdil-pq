DROP TABLE IF EXISTS SYSTEMUSER ;
DROP TABLE IF EXISTS SYSPROPERTIES ;
DROP TABLE IF EXISTS NEWSLETTER;

CREATE  TABLE `DJMAG`.`SYSTEMUSER` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `username` VARCHAR(20) NULL ,
  `password` VARCHAR(4000) NULL ,
  `name` VARCHAR(255) NULL ,
  `email` VARCHAR(100) NULL ,
  `resetcode` VARCHAR(100) NULL ,
  `deleted` INT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `UNAME` (`username` ASC) )
ENGINE = InnoDB;

CREATE  TABLE `DJMAG`.`SYSPROPERTIES` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `propKey` VARCHAR(100) NULL ,
  `propValue` VARCHAR(255) NULL ,
  `deleted` INT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;

CREATE TABLE `DJMAG`.`NEWSLETTER` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `email` VARCHAR(100) NOT NULL ,
  `subscriptiondate` DATE NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;