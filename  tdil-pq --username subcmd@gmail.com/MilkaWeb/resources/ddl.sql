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
DROP TABLE IF EXISTS POST_IT;
DROP TABLE IF EXISTS WALL_WRITTING;
DROP TABLE IF EXISTS WALL;
DROP TABLE IF EXISTS FILTERED_WORD;
DROP TABLE IF EXISTS EMAIL_ENDINGS;
DROP TABLE IF EXISTS MAIL_TO_PARENT;
DROP TABLE IF EXISTS VIDEO;
DROP TABLE IF EXISTS GOOD_MORNING;

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

INSERT INTO SYSTEMUSER(username,password,name,email, deleted) VALUES('Admin',SHA1('3p0x7'), 'Admin', 'admin@admin.com', 0);

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
INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('contactform.email','milka@contact.com.ar','Email del formulario de contacto',0);
INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('server.name','www.milka.com.ar','Nombre del server',0);
INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('fotomilka.url','http://www.milka.com.ar/index.jsp','Url de tu foto milka',0);
INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('papapedia.url','http://www.milka.com.ar/papapedia.jsp','Url de la experiencia papapedia',0);
INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('postits.url','http://www.milka.com.ar/postits.jsp','Url de la experiencia post its',0);
INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('finalesDeEmail.url','http://www.milka.com.ar/finalesDeEmail.jsp','Url de la experiencia finales de email',0);
INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('cartasDeHijosAPadres.url','http://www.milka.com.ar/cartasDeHijosAPadres.jsp','Url de la experiencia cartas de hijos a padres',0);
INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('contentapproved.emailfrom','info@milka.com.ar','Email from de aprobacion de contenido',0);
INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('contentapproved.emailsubject','Contenido aprobado','Email subject de aprobacion de contenido',0);
INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('fb.app','3785427834','Id de la app de facebook usada para compartir',0);

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

INSERT INTO NOTIFICATION_EMAIL(notificationType,description,content,deleted) VALUES('tufotomilka', 'Aprobacion de foto milka','contenido del email',0);
INSERT INTO NOTIFICATION_EMAIL(notificationType,description,content,deleted) VALUES('post-it', 'Aprobacion de post it','contenido del email',0);
INSERT INTO NOTIFICATION_EMAIL(notificationType,description,content,deleted) VALUES('cartasdehijosapadres', 'Aprobacion de cartas de hijos a padres','contenido del email',0);
INSERT INTO NOTIFICATION_EMAIL(notificationType,description,content,deleted) VALUES('finalesdeemail', 'Aprobacion de finales de email','contenido del email',0);

CREATE TABLE RAW_INSERT (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `insertType` VARCHAR(100) NOT NULL ,
  `description` VARCHAR(300) NOT NULL ,
  `htmlContent` TEXT NOT NULL ,
  `deleted` INT NOT NULL ,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

INSERT INTO RAW_INSERT (insertType, description, htmlContent, deleted) VALUES('twitterFeed', 'Feed de Twitter', '', 1);
INSERT INTO RAW_INSERT (insertType, description, htmlContent, deleted) VALUES('facebookFeed', 'Feed de Facebook', '', 1);

INSERT INTO RAW_INSERT (insertType, description, htmlContent, deleted) VALUES('internal.finalesDeEmail', 'Flash config de finales de email', '', 1);
INSERT INTO RAW_INSERT (insertType, description, htmlContent, deleted) VALUES('internal.cartasDeHijosAPadres', 'Flash config de finales de cartas de hijos a padres', '', 1);

CREATE TABLE CLICK_COUNTER (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `clicks` INT NOT NULL,
  `deleted` INT NOT NULL ,
  PRIMARY KEY (`id`))
ENGINE = MYISAM;

INSERT INTO CLICK_COUNTER (id, clicks, deleted) VALUES(1,0, 0);
INSERT INTO CLICK_COUNTER (id, clicks, deleted) VALUES(2,0, 0);
INSERT INTO CLICK_COUNTER (id, clicks, deleted) VALUES(3,0, 0);
INSERT INTO CLICK_COUNTER (id, clicks, deleted) VALUES(4,0, 0);
INSERT INTO CLICK_COUNTER (id, clicks, deleted) VALUES(5,0, 0);
INSERT INTO CLICK_COUNTER (id, clicks, deleted) VALUES(6,0, 0);
INSERT INTO CLICK_COUNTER (id, clicks, deleted) VALUES(7,0, 0);
INSERT INTO CLICK_COUNTER (id, clicks, deleted) VALUES(8,0, 0);
INSERT INTO CLICK_COUNTER (id, clicks, deleted) VALUES(9,0, 0);
INSERT INTO CLICK_COUNTER (id, clicks, deleted) VALUES(10,0, 0);
INSERT INTO CLICK_COUNTER (id, clicks, deleted) VALUES(11,0, 0);
INSERT INTO CLICK_COUNTER (id, clicks, deleted) VALUES(12,0, 0);
INSERT INTO CLICK_COUNTER (id, clicks, deleted) VALUES(13,0, 0);
INSERT INTO CLICK_COUNTER (id, clicks, deleted) VALUES(14,0, 0);
INSERT INTO CLICK_COUNTER (id, clicks, deleted) VALUES(15,0, 0);
INSERT INTO CLICK_COUNTER (id, clicks, deleted) VALUES(16,0, 0);
INSERT INTO CLICK_COUNTER (id, clicks, deleted) VALUES(17,0, 0);
INSERT INTO CLICK_COUNTER (id, clicks, deleted) VALUES(18,0, 0);
INSERT INTO CLICK_COUNTER (id, clicks, deleted) VALUES(19,0, 0);
INSERT INTO CLICK_COUNTER (id, clicks, deleted) VALUES(20,0, 0);
INSERT INTO CLICK_COUNTER (id, clicks, deleted) VALUES(21,0, 0);
INSERT INTO CLICK_COUNTER (id, clicks, deleted) VALUES(22,0, 0);
INSERT INTO CLICK_COUNTER (id, clicks, deleted) VALUES(23,0, 0);
INSERT INTO CLICK_COUNTER (id, clicks, deleted) VALUES(24,0, 0);
INSERT INTO CLICK_COUNTER (id, clicks, deleted) VALUES(25,0, 0);
INSERT INTO CLICK_COUNTER (id, clicks, deleted) VALUES(26,0, 0);
INSERT INTO CLICK_COUNTER (id, clicks, deleted) VALUES(27,0, 0);
INSERT INTO CLICK_COUNTER (id, clicks, deleted) VALUES(28,0, 0);
INSERT INTO CLICK_COUNTER (id, clicks, deleted) VALUES(29,0, 0);
INSERT INTO CLICK_COUNTER (id, clicks, deleted) VALUES(30,0, 0);
INSERT INTO CLICK_COUNTER (id, clicks, deleted) VALUES(31,0, 0);
INSERT INTO CLICK_COUNTER (id, clicks, deleted) VALUES(32,0, 0);
INSERT INTO CLICK_COUNTER (id, clicks, deleted) VALUES(33,0, 0);
INSERT INTO CLICK_COUNTER (id, clicks, deleted) VALUES(34,0, 0);
INSERT INTO CLICK_COUNTER (id, clicks, deleted) VALUES(35,0, 0);
INSERT INTO CLICK_COUNTER (id, clicks, deleted) VALUES(36,0, 0);
INSERT INTO CLICK_COUNTER (id, clicks, deleted) VALUES(37,0, 0);
INSERT INTO CLICK_COUNTER (id, clicks, deleted) VALUES(38,0, 0);
INSERT INTO CLICK_COUNTER (id, clicks, deleted) VALUES(39,0, 0);
INSERT INTO CLICK_COUNTER (id, clicks, deleted) VALUES(40,0, 0);

CREATE TABLE TAG (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `description` VARCHAR(50) NOT NULL ,
  `deleted` INT NOT NULL ,
  PRIMARY KEY (`id`),
  INDEX `IX_TAG_00` (`description` ASC))
ENGINE = InnoDB;

CREATE TABLE AUTHOR (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(150) NOT NULL ,
  `email` VARCHAR(150) NOT NULL ,
  `deleted` INT NOT NULL ,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE TABLE MILKA_PHOTO (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `creationDate` DATETIME NOT NULL ,
  `publishDate` DATETIME NULL ,
  `id_author` INT NOT NULL ,
  `frontCover` INT NOT NULL,
  `showInHome` INT NOT NULL,
  `approved` INT NOT NULL,
  `id_blob_data` INT NULL,
  `ext_blob_data` VARCHAR(10) NULL ,
  `id_approved_data` INT NULL,
  `ext_approved_data` VARCHAR(10) NULL ,
  `id_click_counter` INT NOT NULL,
  `tags` VARCHAR(300) NULL ,
  `url_link` VARCHAR(400) NULL ,
  `url_target` VARCHAR(50) NULL ,
  `deleted` INT NOT NULL ,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_MILKA_PHOTO_00`
    FOREIGN KEY (`id_author` )
    REFERENCES AUTHOR (`id` )
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

CREATE TABLE POST_IT (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `creationDate` DATETIME NOT NULL ,
  `publishDate` DATETIME NULL ,
  `id_author` INT NOT NULL ,
  `originalText` VARCHAR(4000) NOT NULL ,
  `title` VARCHAR(100) NULL ,
  `description` VARCHAR(4000) NULL ,
  `approved` INT NOT NULL,
  `id_thumb` INT NULL,
  `ext_thum` VARCHAR(10) NULL ,
  `id_image` INT NULL,
  `ext_image` VARCHAR(10) NULL ,
  `url_link` VARCHAR(400) NULL ,
  `url_target` VARCHAR(50) NULL ,
  `position` INT NOT NULL ,
  `color` VARCHAR(50) NULL ,
  `id_click_counter` INT NOT NULL,
  `deleted` INT NOT NULL ,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_POST_IT_00`
    FOREIGN KEY (`id_author` )
    REFERENCES AUTHOR (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
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

INSERT INTO WALL (id, description, moderated, profanityFilter, deleted) VALUES(1,'papapedia', 0, 1, 0);
INSERT INTO WALL (id, description, moderated, profanityFilter, deleted) VALUES(2,'queodias', 0, 1, 0);
INSERT INTO WALL (id, description, moderated, profanityFilter, deleted) VALUES(3,'queamas', 0, 1, 0);

CREATE TABLE WALL_WRITTING (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `creationDate` DATETIME NOT NULL ,
  `publishDate` DATETIME NULL ,
  `id_author` INT  NULL ,
  `originalText` VARCHAR(4000) NOT NULL ,
  `approved` INT NOT NULL,
  `id_wall` INT NOT NULL,
  `id_click_counter` INT NULL,
  `deleted` INT NOT NULL ,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_WALL_WRITTING_00`
    FOREIGN KEY (`id_author` )
    REFERENCES AUTHOR (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_WALL_WRITTING_01`
    FOREIGN KEY (`id_wall` )
    REFERENCES WALL (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE FILTERED_WORD (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `word` VARCHAR(50) NOT NULL ,
  `deleted` INT NOT NULL ,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE TABLE EMAIL_ENDINGS (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `creationDate` DATETIME NOT NULL ,
  `publishDate` DATETIME NULL ,
  `id_author` INT NOT NULL ,
  `title` VARCHAR(100) NULL ,
  `description` VARCHAR(4000) NULL ,
  `frontCover` INT NOT NULL,
  `showInHome` INT NOT NULL,
  `approved` INT NOT NULL,
  `id_blob_data` INT NULL,
  `ext_blob_data` VARCHAR(10) NULL ,
  `id_approved_data` INT NULL,
  `ext_approved_data` VARCHAR(10) NULL ,
  `id_click_counter` INT NOT NULL,
  `url_link` VARCHAR(400) NULL ,
  `url_target` VARCHAR(50) NULL ,
  `deleted` INT NOT NULL ,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_EMAIL_ENDINGS_00`
    FOREIGN KEY (`id_author` )
    REFERENCES AUTHOR (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE MAIL_TO_PARENT (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `creationDate` DATETIME NOT NULL ,
  `publishDate` DATETIME NULL ,
  `id_author` INT NOT NULL ,
  `title` VARCHAR(100) NULL ,
  `description` VARCHAR(4000) NULL ,
  `frontCover` INT NOT NULL,
  `showInHome` INT NOT NULL,
  `approved` INT NOT NULL,
  `id_blob_data` INT NULL,
  `ext_blob_data` VARCHAR(10) NULL ,
  `id_approved_data` INT NULL,
  `ext_approved_data` VARCHAR(10) NULL ,
  `id_click_counter` INT NOT NULL,
  `url_link` VARCHAR(400) NULL ,
  `url_target` VARCHAR(50) NULL ,
  `deleted` INT NOT NULL ,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_MAIL_TO_PARENT_00`
    FOREIGN KEY (`id_author` )
    REFERENCES AUTHOR (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE VIDEO (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `title` VARCHAR(250) NOT NULL ,
  `url` VARCHAR(4000) NOT NULL ,
  `frontCoverExt` VARCHAR(10) NOT NULL ,
  `frontCover_id` INT NULL,
  `orderNumber` INT NOT NULL ,
  `deleted` INT NOT NULL ,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE TABLE GOOD_MORNING (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `creationDate` DATETIME NOT NULL ,
  `publishDate` DATETIME NULL ,
  `id_author` INT NOT NULL ,
  `title` VARCHAR(100) NULL ,
  `description` VARCHAR(4000) NULL ,
  `frontCover` INT NOT NULL,
  `showInHome` INT NOT NULL,
  `approved` INT NOT NULL,
  `id_blob_data` INT NULL,
  `ext_blob_data` VARCHAR(10) NULL ,
  `id_approved_data` INT NULL,
  `ext_approved_data` VARCHAR(10) NULL ,
  `id_click_counter` INT NOT NULL,
  `url_link` VARCHAR(400) NULL ,
  `url_target` VARCHAR(50) NULL ,
  `twitter` VARCHAR(400) NULL ,
  `facebook` VARCHAR(400) NULL ,
  `deleted` INT NOT NULL ,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_GOOD_MORNING_00`
    FOREIGN KEY (`id_author` )
    REFERENCES AUTHOR (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;