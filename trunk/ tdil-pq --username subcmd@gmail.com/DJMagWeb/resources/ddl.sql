DROP TABLE IF EXISTS SYSTEMUSER;
DROP TABLE IF EXISTS SYSPROPERTIES;
DROP TABLE IF EXISTS COUNTRY;
DROP TABLE IF EXISTS SECTION;
DROP TABLE IF EXISTS MENUITEM;
DROP TABLE IF EXISTS NEWSLETTER;

CREATE  TABLE SYSTEMUSER (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `username` VARCHAR(20) NULL ,
  `password` VARCHAR(4000) NULL ,
  `name` VARCHAR(255) NULL ,
  `email` VARCHAR(150) NULL ,
  `resetcode` VARCHAR(100) NULL ,
  `deleted` INT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `IX_SYSTEMUSER_00` (`username` ASC),
  INDEX `IX_SYSTEMUSER_01` (`email` ASC))
ENGINE = InnoDB;

INSERT INTO SYSTEMUSER(username,password,name,email) VALUES('Admin',SHA1('Admin'), 'Admin', 'admin@admin.com');

CREATE  TABLE SYSPROPERTIES (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `propKey` VARCHAR(100) NOT NULL ,
  `propValue` VARCHAR(255) NULL ,
  `deleted` INT NULL ,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `UQ_SYSPROPERTIES_00` (`propKey` ASC))
ENGINE = InnoDB;

CREATE TABLE COUNTRY (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(250) NOT NULL ,
  `deleted` INT NULL ,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `UQ_COUNTRY_00` (`name` ASC))
ENGINE = InnoDB;

CREATE TABLE SECTION (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(250) NOT NULL ,
  `deleted` INT NULL ,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `UQ_SECTION_00` (`name` ASC))
ENGINE = InnoDB;

CREATE TABLE MENUITEM (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `id_country` INT NOT NULL,
  `id_section` INT NOT NULL,
  `name` VARCHAR(250) NOT NULL,
  `position` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `UQ_MENUITEM_00` (`id_country` ASC, `id_section` ASC) ,
  CONSTRAINT `FK_MENUITEM_00`
    FOREIGN KEY (`id_country` )
    REFERENCES COUNTRY (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_MENUITEM_01`
    FOREIGN KEY (`id_section` )
    REFERENCES SECTION (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE NEWSLETTER (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `email` VARCHAR(100) NOT NULL ,
  `subscriptiondate` DATE NOT NULL,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;