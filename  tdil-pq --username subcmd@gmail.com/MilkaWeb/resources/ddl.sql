DROP TABLE IF EXISTS SYSTEMUSER;
DROP TABLE IF EXISTS SYSPROPERTIES;
DROP TABLE IF EXISTS BLOB_DATA;
DROP TABLE IF EXISTS NOTIFICATION_EMAIL;
DROP TABLE IF EXISTS RAW_INSERT;
DROP TABLE IF EXISTS CLICK_COUNTER;
DROP TABLE IF EXISTS TAG;
DROP TABLE IF EXISTS AUTHOR;
DROP TABLE IF EXISTS MILKA_PHOTO;
DROP TABLE IF EXISTS MILKA_PHOTO_TAG;

CREATE TABLE SYSTEMUSER (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `username` VARCHAR(20) NULL ,
  `password` VARCHAR(4000) NULL ,
  `name` VARCHAR(255) NULL ,
  `email` VARCHAR(150) NULL ,
  `resetcode` VARCHAR(100) NULL ,
  `deleted` INT NOT NULL,
  PRIMARY KEY (`id`) ,
  INDEX `IX_SYSTEMUSER_00` (`username` ASC),
  INDEX `IX_SYSTEMUSER_01` (`email` ASC))
ENGINE = InnoDB;

INSERT INTO SYSTEMUSER(username,password,name,email, deleted) VALUES('Admin',SHA1('Admin'), 'Admin', 'admin@admin.com', 0);

CREATE TABLE SYSPROPERTIES (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `propKey` VARCHAR(100) NOT NULL ,
  `propValue` VARCHAR(255) NULL ,
  `description` VARCHAR(300) NOT NULL ,
  `deleted` INT NOT NULL ,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `UQ_SYSPROPERTIES_00` (`propKey` ASC))
ENGINE = InnoDB;

INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('smpt.server','localhost','Servidor de email',0);
INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('smpt.port','2525','Puerto del servidor de email',0);

CREATE TABLE BLOB_DATA (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `dataType` VARCHAR(20) NOT NULL ,
  `filename` VARCHAR(100) NOT NULL ,
  `content` MEDIUMBLOB NOT NULL ,
  `deleted` INT NOT NULL ,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE TABLE NOTIFICATION_EMAIL (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `notificationType` VARCHAR(20) NOT NULL ,
  `description` VARCHAR(300) NOT NULL ,
  `replacements` TEXT NULL ,
  `content` TEXT NOT NULL ,
  `deleted` INT NOT NULL ,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

INSERT INTO NOTIFICATION_EMAIL(notificationType,description,content,deleted) VALUES('yourmilkaphoto', 'Aprobacion de foto milka','contenido del email',1);

CREATE TABLE RAW_INSERT (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `insertType` VARCHAR(20) NOT NULL ,
  `description` VARCHAR(300) NOT NULL ,
  `htmlContent` TEXT NOT NULL ,
  `deleted` INT NOT NULL ,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

INSERT INTO RAW_INSERT (insertType, description, htmlContent, deleted) VALUES('twitterFeed', 'Feed de Twitter', '', 1);
INSERT INTO RAW_INSERT (insertType, description, htmlContent, deleted) VALUES('facebookFeed', 'Feed de Facebook', '', 1);

CREATE TABLE CLICK_COUNTER (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `clicks` INT NOT NULL,
  `deleted` INT NOT NULL ,
  PRIMARY KEY (`id`))
ENGINE = MYISAM;

INSERT INTO CLICK_COUNTER (id, clicks, deleted) VALUES(1,150, 1);
INSERT INTO CLICK_COUNTER (id, clicks, deleted) VALUES(2,75, 1);

CREATE TABLE TAG (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `description` VARCHAR(50) NOT NULL ,
  `deleted` INT NOT NULL ,
  PRIMARY KEY (`id`),
  INDEX `IX_TAG_00` (`description` ASC))
ENGINE = InnoDB;

CREATE TABLE AUTHOR (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `firstname` VARCHAR(100) NOT NULL ,
  `lastname` VARCHAR(100) NOT NULL ,
  `email` VARCHAR(150) NOT NULL ,
  `deleted` INT NOT NULL ,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE TABLE MILKA_PHOTO (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `creationDate` DATETIME NOT NULL ,
  `id_author` INT NOT NULL ,
  `frontCover` INT NOT NULL,
  `showInHome` INT NOT NULL,
  `approved` INT NOT NULL,
  `id_blob_data` INT NULL,
  `ext_blob_data` VARCHAR(10) NULL ,
  `id_click_counter` INT NOT NULL,
  `tags` VARCHAR(300) NULL ,
  `deleted` INT NOT NULL ,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_MILKA_PHOTO_00`
    FOREIGN KEY (`id_author` )
    REFERENCES AUTHOR (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_MILKA_PHOTO_01`
    FOREIGN KEY (`id_blob_data` )
    REFERENCES BLOB_DATA (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE MILKA_PHOTO_TAG (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `id_milka_photo` INT NOT NULL,
  `id_tag` INT NOT NULL,
  `deleted` INT NOT NULL ,
  PRIMARY KEY (`id`),
  INDEX `IX_MILKA_PHOTO_TAG_00` (`id_milka_photo` ASC),
  INDEX `IX_MILKA_PHOTO_TAG_01` (`id_tag` ASC),
  CONSTRAINT `FK_MILKA_PHOTO_TAG_00`
    FOREIGN KEY (`id_milka_photo` )
    REFERENCES MILKA_PHOTO (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_MILKA_PHOTO_TAG_10`
    FOREIGN KEY (`id_tag` )
    REFERENCES TAG (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
