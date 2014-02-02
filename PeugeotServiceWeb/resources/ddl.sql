DROP TABLE IF EXISTS SYSTEMUSER;
DROP TABLE IF EXISTS SYSPROPERTIES;
DROP TABLE IF EXISTS BLOB_DATA;
DROP TABLE IF EXISTS VERSION;
DROP TABLE IF EXISTS CACHE_REGION;
DROP TABLE IF EXISTS POI;

DROP TABLE IF EXISTS DEALER;
DROP TABLE IF EXISTS CITY;
DROP TABLE IF EXISTS STATE;

DROP TABLE IF EXISTS SERVICE;
DROP TABLE IF EXISTS ADVICE;
DROP TABLE IF EXISTS VEHICLE;
DROP TABLE IF EXISTS MODEL;

DROP TABLE IF EXISTS CONTACTDATA;

DROP TABLE IF EXISTS DATA_IMPORT;

DROP TABLE IF EXISTS WEBSITEUSER;


CREATE TABLE WEBSITEUSER (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `lojackUserId` VARCHAR(50) NULL ,
  `peugeotUserId` VARCHAR(100) NULL ,
  `homeUserId` VARCHAR(100) NULL ,
  `petUserId` VARCHAR(100) NULL ,
  `preventUserId` VARCHAR(100) NULL ,
  `email` VARCHAR(150) NULL ,
  `id_avatar` INT NULL,
  `ext_avatar` VARCHAR(10) NULL ,
  PRIMARY KEY (`id`),
  INDEX `IX_WEBSITEUSER_00` (`lojackUserId` ASC),
  INDEX `IX_WEBSITEUSER_01` (`peugeotUserId` ASC),
  INDEX `IX_WEBSITEUSER_02` (`homeUserId` ASC),
  INDEX `IX_WEBSITEUSER_03` (`petUserId` ASC),
  INDEX `IX_WEBSITEUSER_04` (`preventUserId` ASC))
ENGINE = InnoDB;

CREATE TABLE SYSTEMUSER (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `username` VARCHAR(20) NULL ,
  `password` VARCHAR(50) NULL ,
  `type` INT NOT NULL,
  `deleted` INT NOT NULL,
  PRIMARY KEY (`id`) ,
  INDEX `IX_SYSTEMUSER_00` (`username` ASC))
ENGINE = InnoDB;

INSERT INTO SYSTEMUSER(username,password,type,deleted) VALUES('tdil','f5314a8a0b0a34239a8bf78104f2ff4754a7a890', 0, 0);
INSERT INTO SYSTEMUSER(username,password,type,deleted) VALUES('admin',SHA1('admin'), 0, 0);
COMMIT;

CREATE TABLE SYSPROPERTIES (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `propKey` VARCHAR(100) NOT NULL ,
  `propValue` VARCHAR(255) NULL ,
  `description` VARCHAR(300) NOT NULL ,
  `deleted` INT NOT NULL ,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `UQ_SYSPROPERTIES_00` (`propKey` ASC))
ENGINE = InnoDB;

CREATE TABLE BLOB_DATA (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `dataType` VARCHAR(20) NOT NULL ,
  `filename` VARCHAR(100) NOT NULL ,
  `content` MEDIUMBLOB NOT NULL ,
  `deleted` INT NOT NULL ,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE TABLE VERSION (
	versionNumber INT NOT NULL)
ENGINE = MYISAM;

INSERT INTO VERSION (versionNumber) VALUES(0);

COMMIT;

CREATE TABLE CACHE_REGION (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `version` INT NOT NULL,
  `name` VARCHAR(200) NOT NULL ,
  `deleted` INT NOT NULL ,
  PRIMARY KEY (`id`),
  INDEX `IX_CACHE_REGION_00` (`name` ASC))
ENGINE = InnoDB;
INSERT INTO CACHE_REGION(version, name, deleted) VALUES(1, 'com.tdil.ljpeugeot.model.Category',0);
COMMIT;

CREATE TABLE POI (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `type` INT NOT NULL,
  `subtype` INT NOT NULL,
  `name` VARCHAR(200) NOT NULL ,
  `description` VARCHAR(4000) NOT NULL ,
  `lat` DECIMAL (10,8) NOT NULL ,
  `lon` DECIMAL (10,8) NOT  NULL ,
  PRIMARY KEY (`id`),
  INDEX `IX_POI_00` (`type` ASC))
ENGINE = InnoDB;

CREATE TABLE STATE (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(200) NOT NULL ,
  `deleted` INT NOT NULL ,
  PRIMARY KEY (`id`),
  INDEX `IX_STATE_00` (`name` ASC))
ENGINE = InnoDB;

CREATE TABLE CITY (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(200) NOT NULL ,
  `id_state` INT NOT NULL ,
  `deleted` INT NOT NULL ,
  PRIMARY KEY (`id`),
  INDEX `IX_CITY_00` (`name` ASC),
  INDEX `IX_CITY_01` (`id_state` ASC),
  CONSTRAINT `FK_CITY_00`
    FOREIGN KEY (`id_state` )
    REFERENCES STATE (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE DATA_IMPORT (
  id INT NOT NULL AUTO_INCREMENT ,
  type VARCHAR(40) NOT NULL ,
  filename VARCHAR(400) NOT NULL ,
  status VARCHAR(20) NOT NULL ,
  processed INT NOT NULL,
  errors INT NOT NULL,
  startTime DATETIME NULL ,
  endTime DATETIME NULL ,
  PRIMARY KEY (id),
  INDEX `IX_DATA_IMPORT_00` (`type` ASC))
  ENGINE = InnoDB;

CREATE TABLE DEALER (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `id_data_import` INT NOT NULL,
  `name` VARCHAR(200) NOT NULL ,
  `address` VARCHAR(200) NOT NULL ,
  `email` VARCHAR(150) NOT NULL ,
  `phone` VARCHAR(200) NOT NULL ,
  `id_city` INT NOT NULL ,
  `lat` DECIMAL (10,8) NULL ,
  `lon` DECIMAL (10,8) NULL ,
  `deleted` INT NOT NULL ,
  PRIMARY KEY (`id`),
  INDEX `IX_DEALER_00` (`id_city` ASC),
  INDEX `IX_DEALER_01` (`id_data_import` ASC),
  CONSTRAINT `FK_DEALER_00`
    FOREIGN KEY (`id_city` )
    REFERENCES CITY (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_DEALER_01`
    FOREIGN KEY (`id_data_import` )
    REFERENCES DATA_IMPORT (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE MODEL (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(20) NULL ,
  `description` VARCHAR(100) NULL ,
  `monthWarranty` INT NOT NULL,
  `deleted` INT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE TABLE VEHICLE (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `id_websiteuser` INT NOT NULL,
  `id_model` INT NOT NULL,
  `purchaseDate` DATE NULL ,
  `domain` VARCHAR(20) NULL ,
  `description` VARCHAR(100) NULL ,
  `km` INT NULL,
  `lastServiceKm` INT NULL,
  `lastServiceDate` DATE NULL ,
  `needsAdvice` INT NOT NULL,
  `needsAdvice1` INT NOT NULL,
  `advice1sent` INT NOT NULL,
  `needsAdvice2` INT NOT NULL,
  `advice2sent` INT NOT NULL,
  `needsAdvice3` INT NOT NULL,
  `advice3sent` INT NOT NULL,
  `warrantyExpired` INT NOT NULL,
  `deleted` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `IX_VEHICLE_00` (`id_websiteuser` ASC),
  INDEX `IX_VEHICLE_01` (`id_model` ASC),
  INDEX `IX_VEHICLE_02` (`needsAdvice` ASC),
  CONSTRAINT `FK_VEHICLE_00`
    FOREIGN KEY (`id_websiteuser` )
    REFERENCES WEBSITEUSER (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_VEHICLE_01`
    FOREIGN KEY (`id_model` )
    REFERENCES MODEL (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


CREATE TABLE ADVICE (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `id_vechicle` INT NOT NULL,
  `km` INT NULL,
  `adviseDate` DATE NULL ,
  `adviseNumber` INT NOT NULL,
  `isread` INT NOT NULL,
  `deleted` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `IX_ADVICE_00` (`id_vechicle` ASC),
  CONSTRAINT `FK_ADVICE_00`
    FOREIGN KEY (`id_vechicle` )
    REFERENCES VEHICLE (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE SERVICE (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `id_vechicle` INT NOT NULL,
  `km` INT NULL,
  `serviceDate` DATE NULL ,
  `deleted` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `IX_SERVICE_00` (`id_vechicle` ASC),
  CONSTRAINT `FK_SERVICE_00`
    FOREIGN KEY (`id_vechicle` )
    REFERENCES VEHICLE (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE CONTACTDATA (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `id_websiteuser` INT NOT NULL,
  `contact1Name` VARCHAR(150) NULL ,
  `contact1Relation` VARCHAR(150) NULL ,
  `contact1Phone` VARCHAR(20) NULL ,
  `contact1SecWord` VARCHAR(20) NULL ,
  `contact1HealthI` VARCHAR(100) NULL ,
  `contact2Name` VARCHAR(150) NULL ,
  `contact2Relation` VARCHAR(150) NULL ,
  `contact2Phone` VARCHAR(20) NULL ,
  `contact3Name` VARCHAR(150) NULL ,
  `contact3Relation` VARCHAR(150) NULL ,
  `contact3Phone` VARCHAR(20) NULL ,
  `deleted` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `IX_CONTACTDATA_00` (`id_websiteuser` ASC),
  CONSTRAINT `FK_CONTACTDATA_00`
    FOREIGN KEY (`id_websiteuser` )
    REFERENCES WEBSITEUSER (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;