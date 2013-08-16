DROP TABLE IF EXISTS SYSTEMUSER;
DROP TABLE IF EXISTS SYSPROPERTIES;
DROP TABLE IF EXISTS BLOB_DATA;
DROP TABLE IF EXISTS NOTIFICATION_EMAIL;
DROP TABLE IF EXISTS RAW_INSERT;
DROP TABLE IF EXISTS GEO2;
DROP TABLE IF EXISTS GEO3;
DROP TABLE IF EXISTS GEO4;

DROP TABLE IF EXISTS PROMOTION_SELL;
DROP TABLE IF EXISTS PROMOTION_PHOTO;
DROP TABLE IF EXISTS PROMOTION_VIDEO;
DROP TABLE IF EXISTS PROMOTION;

DROP TABLE IF EXISTS SELL_VIDEO;
DROP TABLE IF EXISTS SELL_PHOTO;
DROP TABLE IF EXISTS SELL_MEDIA;
DROP TABLE IF EXISTS SELL;

DROP TABLE IF EXISTS WALL_WRITTING;
DROP TABLE IF EXISTS SERVICE_AREA;

DROP TABLE IF EXISTS PROF_SERVICE;
DROP TABLE IF EXISTS PROF_SERV_CATEGORY;
DROP TABLE IF EXISTS PROF_PRODUCT;
DROP TABLE IF EXISTS PROF_PROD_CATEGORY;

DROP TABLE IF EXISTS HIGHLIGHTED_PROF;
DROP TABLE IF EXISTS HIGHLIGHTED_CAT;
DROP TABLE IF EXISTS ADVERTISEMENT;

DROP TABLE IF EXISTS PROFILE_VIDEO;
DROP TABLE IF EXISTS PROFESIONAL;
DROP TABLE IF EXISTS PROFESIONAL_CHANGE;
DROP TABLE IF EXISTS PROFILE_PICTURE;

DROP TABLE IF EXISTS WALL;

DROP TABLE IF EXISTS CLIENT;

DROP TABLE IF EXISTS STATISTIC;

DROP TABLE IF EXISTS CACHE_REGION;

DROP TABLE IF EXISTS VERSION;

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

INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('contactform.email','tuafesta.test@gmail.com','Email del formulario de contacto',0);

INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('mail.smtp.user','tuafesta.test@gmail.com','mail.smtp.user',0);
INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('mail.smtp.host','smtp.gmail.com','mail.smtp.host',0);
INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('mail.smtp.port','465','mail.smtp.port',0);
INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('mail.smtp.starttls.enable','true','mail.smtp.starttls.enable',0);
INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('mail.smtp.auth','true','mail.smtp.auth',0);
INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('mail.smtp.socketFactory.port','465','mail.smtp.socketFactory.port',0);
INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('mail.smtp.socketFactory.class','javax.net.ssl.SSLSocketFactory','mail.smtp.socketFactory.class',0);
INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('mail.smtp.socketFactory.fallback','false','mail.smtp.socketFactory.fallback',0);
INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('mail.smtp.password','t3sttu4f3st4','mail.smtp.password',0);

INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('server.name','http://ec2-23-21-18-79.compute-1.amazonaws.com:8080/TUAFESTA_WEB/','Nombre del server',0);

INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('fb.api_key','3785427834','api_key de la app de facebook',0);
INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('fb.secret','63b6f58fec5dfba0cde041149e3a9255','secret de la app de facebook',0);


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
  `notificationType` VARCHAR(50) NOT NULL ,
  `description` VARCHAR(300) NOT NULL ,
  `subject` VARCHAR(50) NOT NULL ,
  `from_` VARCHAR(100) NOT NULL ,
  `replacements` TEXT NULL ,
  `content` TEXT NOT NULL ,
  `deleted` INT NOT NULL ,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

INSERT INTO NOTIFICATION_EMAIL(notificationType,description,content,subject,from_,deleted) VALUES('verif.email.prof', 'Verificacion de email de profesional','Para completar la validacion de tu email cliquea aca [LINK]','Verificacion de email', 'tuafesta.test@gmail.com', 0);
INSERT INTO NOTIFICATION_EMAIL(notificationType,description,content,subject,from_,deleted) VALUES('verif.email.client', 'Verificacion de email de cliente','Para completar la validacion de tu email cliquea aca [LINK]','Verificacion de email', 'tuafesta.test@gmail.com', 0);

INSERT INTO NOTIFICATION_EMAIL(notificationType,description,content,subject,from_,deleted) VALUES('passreset.email.prof', 'Reseteo de password de profesional','Nueva clave [PASSWORD]','Nueva clave', 'tuafesta.test@gmail.com', 0);
INSERT INTO NOTIFICATION_EMAIL(notificationType,description,content,subject,from_,deleted) VALUES('passreset.email.client', 'Reseteo de password de cliente','Nueva clave [PASSWORD]','Nueva clave', 'tuafesta.test@gmail.com', 0);

INSERT INTO NOTIFICATION_EMAIL(notificationType,description,content,subject,from_,deleted) 
VALUES('disapprove.personal', 'Desaprobacion de datos personales','La modificacion de tus datos personales no ha sido aprobada.[MOTIVE]', 'Modificacion no aprobada', 'tuafesta.test@gmail.com', 0);

INSERT INTO NOTIFICATION_EMAIL(notificationType,description,content,subject,from_,deleted) 
VALUES('disapprove.business', 'Desaprobacion de datos profesionales','La modificacion de tus datos profesionales no ha sido aprobada.[MOTIVE]', 'Modificacion no aprobada', 'tuafesta.test@gmail.com', 0);

INSERT INTO NOTIFICATION_EMAIL(notificationType,description,content,subject,from_,deleted) 
VALUES('disapprove.sell', 'Desaprobacion de datos venta','La venta [SELL_NAME] no ha sido aprobada.[MOTIVE]','Venta no aprobada', 'tuafesta.test@gmail.com', 0);


CREATE TABLE RAW_INSERT (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `insertType` VARCHAR(100) NOT NULL ,
  `description` VARCHAR(300) NOT NULL ,
  `htmlContent` TEXT NOT NULL ,
  `deleted` INT NOT NULL ,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE TABLE GEO2(
  `id` INT NOT NULL AUTO_INCREMENT ,
  `nombre` VARCHAR(100) NOT NULL ,
  `availableForService` INT NOT NULL,
  `showInHome` INT NOT NULL,
  `homeIndex` INT NULL,
  `deleted` INT NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE TABLE GEO3 (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `geo2_id` INT NOT NULL,
  `nombre` VARCHAR(100) NOT NULL ,
  `availableForService` INT NOT NULL,
  `showInHome` INT NOT NULL,
  `homeIndex` INT NULL,
  `deleted` INT NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE TABLE GEO4 (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `geo3_id` INT NOT NULL,
  `nombre` VARCHAR(100) NOT NULL ,
  `availableForService` INT NOT NULL,
  `showInHome` INT NOT NULL,
  `homeIndex` INT NULL,
  `deleted` INT NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE TABLE CATEGORY (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(100) NOT NULL ,
  `description` VARCHAR(4000) NOT NULL ,
  `parent_id` INT NULL,
  `type` INT NOT NULL,
  `showInHome` INT NOT NULL,
  `homeIndex` INT NULL,
  `isother` INT NOT NULL DEFAULT 0,
  `deleted` INT NOT NULL ,
  PRIMARY KEY (`id`),
  INDEX `IX_CATEGORY_00` (`parent_id` ASC))
ENGINE = InnoDB;

CREATE TABLE WALL (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `description` VARCHAR(50) NOT NULL ,
  `moderated` INT NOT NULL,
  `profanityFilter` INT NOT NULL,
  `deleted` INT NOT NULL ,
  PRIMARY KEY (`id`),
  INDEX `IX_WALL_00` (`description` ASC))
ENGINE = InnoDB;

CREATE TABLE WALL_WRITTING (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `creationDate` DATETIME NOT NULL ,
  `publishDate` DATETIME NULL ,
  `id_author` INT  NULL ,
  `originalText` VARCHAR(4000) NOT NULL ,
  `approved` INT NOT NULL,
  `id_wall` INT NOT NULL,
  `response_pending` INT NOT NULL ,
  `id_response_to` INT  NULL ,
  `deleted` INT NOT NULL ,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_WALL_WRITTING_01`
    FOREIGN KEY (`id_wall` )
    REFERENCES WALL (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE PROFESIONAL_CHANGE (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `id_profile_picture` INT NULL,
  `ext_profile_picture` VARCHAR(10) NULL ,
  `modificationDate` DATETIME NULL ,
  `reviewDate` DATETIME NULL ,
  `disapproveReason` VARCHAR(4000) NULL ,
  `firstname` VARCHAR(100) NULL ,
  `lastname` VARCHAR(100) NULL ,
  `sex` CHAR(1) NULL ,
  `birthdate` DATE NULL ,
  `phoneAreaCode` VARCHAR(15) NULL ,
  `phoneNumber` VARCHAR(15) NULL ,
  `phoneExtension` VARCHAR(10) NULL ,
  `phoneType` VARCHAR(25) NULL ,
  `id_geolevel` INT NULL,
  `businessName` VARCHAR(400) NULL ,
  `cuit` VARCHAR(400) NULL ,
  `iibb` VARCHAR(400) NULL ,
  `website` VARCHAR(200) NULL ,
  `facebook` VARCHAR(200) NULL ,
  `businesshours` VARCHAR(4000) NULL ,
  `description` VARCHAR(4000) NULL ,
  `video1` VARCHAR(200) NULL,
  `video2` VARCHAR(200) NULL,
  `video3` VARCHAR(200) NULL,
  `video4` VARCHAR(200) NULL,
  `video5` VARCHAR(200) NULL,
  `deleted` INT NOT NULL ,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE TABLE PROFESIONAL (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `id_profesional_change` INT NOT NULL,
  `reviewDate` DATETIME NULL ,
  `disapproveReason` VARCHAR(4000) NULL ,
  `id_profile_picture` INT NULL,
  `ext_profile_picture` VARCHAR(10) NULL ,
  `firstname` VARCHAR(100) NOT NULL ,
  `lastname` VARCHAR(100) NOT NULL ,
  `sex` CHAR(1) NOT NULL ,
  `birthdate` DATE NOT NULL ,
  `phoneAreaCode` VARCHAR(15) NULL ,
  `phoneNumber` VARCHAR(15) NULL ,
  `phoneExtension` VARCHAR(10) NULL ,
  `phoneType` VARCHAR(25) NULL ,
  `id_geolevel` INT NULL,
  `businessName` VARCHAR(400) NOT NULL ,
  `cuit` VARCHAR(400) NULL ,
  `iibb` VARCHAR(400) NULL ,
  `email` VARCHAR(150) NOT NULL ,
  `password` VARCHAR(255) NULL ,
  `website` VARCHAR(200) NULL ,
  `facebookid` VARCHAR(200) NULL ,
  `facebook` VARCHAR(200) NULL ,
  `businesshours` VARCHAR(4000) NULL ,
  `description` VARCHAR(4000) NULL ,
  `video1` VARCHAR(200) NULL,
  `video2` VARCHAR(200) NULL,
  `video3` VARCHAR(200) NULL,
  `video4` VARCHAR(200) NULL,
  `video5` VARCHAR(200) NULL,
  `emailvalid` INT NOT NULL,
  `status` INT NOT NULL,
  `datachanged` INT NOT NULL,
  `id_wall` INT NOT NULL,
  `verifemail` VARCHAR(20) NULL ,
  `deleted` INT NOT NULL ,
  PRIMARY KEY (`id`),
  INDEX `IX_PROFESIONAL_00` (`id_geolevel` ASC),
  INDEX `IX_PROFESIONAL_01` (`email` ASC),
   CONSTRAINT `FK_WALL_WRITTING_02`
    FOREIGN KEY (`id_wall` )
    REFERENCES WALL (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
   CONSTRAINT `FK_PROFESIONAL_03`
    FOREIGN KEY (`id_profesional_change` )
    REFERENCES PROFESIONAL_CHANGE (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE VERSION (
	versionNumber INT NOT NULL)
ENGINE = MYISAM;

INSERT INTO VERSION (versionNumber) VALUES(0);

COMMIT;

CREATE TABLE SELL (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `id_profesional` INT NOT NULL,
  `type` INT NOT NULL,
  `id_category` INT NULL,
  `name` VARCHAR(400) NULL,
  `description` VARCHAR(4000) NULL,
  `referenceprice` DECIMAL(10,2),
  `approved` INT NOT NULL,
  `deleted` INT NOT NULL ,
  PRIMARY KEY (`id`),
   CONSTRAINT `FK_CATEGORY_00`
    FOREIGN KEY (`id_category` )
    REFERENCES CATEGORY (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE SELL_MEDIA (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `id_sell` INT NOT NULL,
  `video1` VARCHAR(200) NULL,
  `video2` VARCHAR(200) NULL,
  `video3` VARCHAR(200) NULL,
  `video4` VARCHAR(200) NULL,
  `video5` VARCHAR(200) NULL,
  `id_blob_data1` INT NULL,
  `ext_blob_data1` VARCHAR(10) NULL ,
  `id_blob_data2` INT NULL,
  `ext_blob_data2` VARCHAR(10) NULL ,
  `id_blob_data3` INT NULL,
  `ext_blob_data3` VARCHAR(10) NULL ,
  `id_blob_data4` INT NULL,
  `ext_blob_data4` VARCHAR(10) NULL ,
  `id_blob_data5` INT NULL,
  `ext_blob_data5` VARCHAR(10) NULL ,
  `approved` INT NOT NULL,
  `deleted` INT NOT NULL ,
  PRIMARY KEY (`id`),
   CONSTRAINT `FK_SELL_MEDIA_00`
    FOREIGN KEY (`id_sell` )
    REFERENCES SELL (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE SERVICE_AREA (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `id_profesional` INT NOT NULL,
  `approved` INT NOT NULL,
  `level` INT NOT NULL,
  `id_geolevel` INT NOT NULL,
  `deleted` INT NOT NULL ,
  PRIMARY KEY (`id`),
  INDEX `IX_SERVICE_AREA_00` (`id_geolevel` ASC),
   CONSTRAINT `FK_SERVICE_AREA_00`
    FOREIGN KEY (`id_profesional` )
    REFERENCES PROFESIONAL (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE PROMOTION (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `startDate` DATETIME NOT NULL ,
  `endDate` DATETIME NOT NULL ,
  `name` VARCHAR(100) NOT NULL ,
  `description` VARCHAR(4000) NOT NULL ,
  `price` DECIMAL(10,2),
  `deleted` INT NOT NULL ,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE TABLE PROMOTION_SELL (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `id_promotion` INT NOT NULL,
  `referenceprice` DECIMAL(10,2),
  `id_sell` INT NOT NULL,
  `deleted` INT NOT NULL ,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_PROMOTION_SELL_00`
    FOREIGN KEY (`id_promotion` )
    REFERENCES PROMOTION (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_PROMOTION_SELL_01`
    FOREIGN KEY (`id_sell` )
    REFERENCES SELL (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE PROMOTION_PHOTO (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `id_promotion` INT NOT NULL,
  `orderNumber` INT NOT NULL ,
  `id_blob_data` INT NOT NULL,
  `ext_blob_data` VARCHAR(10) NOT NULL ,
  `deleted` INT NOT NULL ,
  PRIMARY KEY (`id`),
   CONSTRAINT `FK_PROMOTION_PHOTO_00`
    FOREIGN KEY (`id_promotion` )
    REFERENCES PROMOTION (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE CLIENT (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `firstname` VARCHAR(100) NOT NULL ,
  `lastname` VARCHAR(100) NOT NULL ,
  `sex` CHAR(1) NOT NULL ,
  `birthdate` DATE NOT NULL ,
  `id_geolevel` INT NULL,
  `email` VARCHAR(150) NOT NULL ,
  `password` VARCHAR(255) NULL ,
  `facebookid` VARCHAR(200) NULL ,
  `emailvalid` INT NOT NULL,
  `status` INT NOT NULL,
  `verifemail` VARCHAR(20) NULL ,
  `deleted` INT NOT NULL ,
  PRIMARY KEY (`id`),
  INDEX `IX_CLIENT_00` (`email` ASC),
  INDEX `IX_CLIENT_01` (`facebookid` ASC))
ENGINE = InnoDB;

CREATE TABLE STATISTIC (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `textData` VARCHAR(100) NULL ,
  `statType` INT NOT NULL,
  `objectId` INT NULL,
  `extraId` INT NULL,
  `objectTime` DATETIME NULL ,
  `deleted` INT NOT NULL ,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE TABLE CACHE_REGION (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `version` INT NOT NULL,
  `name` VARCHAR(200) NOT NULL ,
  `deleted` INT NOT NULL ,
  PRIMARY KEY (`id`),
  INDEX `IX_CACHE_REGION_00` (`name` ASC))
ENGINE = InnoDB;

INSERT INTO CACHE_REGION(version, name, deleted) VALUES(1, 'com.tdil.tuafesta.model.Category',0);
COMMIT;

CREATE TABLE ADVERTISEMENT (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `id_profesional` INT NOT NULL,
  `id_sell` INT NOT NULL,
  `type` INT NOT NULL,
  `fromDate` DATETIME NOT NULL ,
  `toDate` DATETIME NOT NULL ,
  `paidUp` INT NOT NULL,
  `price` DECIMAL(10,2),
  `id_blob_data` INT NULL,
  `ext_blob_data` VARCHAR(10) NULL ,
  `deleted` INT NOT NULL ,
  PRIMARY KEY (`id`),
  INDEX `IX_ADVERTISEMENT_00` (`id_sell` ASC),
  INDEX `IX_ADVERTISEMENT_01` (`fromDate` ASC, `toDate` ASC),
   CONSTRAINT `FK_ADVERTISEMENT_00`
    FOREIGN KEY (`id_profesional` )
    REFERENCES PROFESIONAL (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE HIGHLIGHTED_CAT (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `id_category` INT NULL,
  `fromDate` DATETIME NOT NULL ,
  `toDate` DATETIME NOT NULL ,
  `deleted` INT NOT NULL ,
  PRIMARY KEY (`id`),
 CONSTRAINT `FK_HIGHLIGHTED_CAT_00`
    FOREIGN KEY (`id_category` )
    REFERENCES CATEGORY (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  INDEX `IX_HIGHLIGHTED_CAT_01` (`fromDate` ASC, `toDate` ASC))
ENGINE = InnoDB;
