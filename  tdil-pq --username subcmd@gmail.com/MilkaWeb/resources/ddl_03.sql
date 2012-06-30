DELETE FROM VERSION;
INSERT INTO VERSION (versionNumber) VALUES(3);

COMMIT;

DROP TABLE IF EXISTS MAIL_TO_CHILD;

DROP TABLE IF EXISTS LOVE_HATE;

DELETE FROM SYSPROPERTIES WHERE propKey = 'cartasDePadresAHijos.url';
DELETE FROM NOTIFICATION_EMAIL WHERE notificationType = 'cartasdepadresahijos';
COMMIT;

CREATE TABLE MAIL_TO_CHILD (
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
  CONSTRAINT `FK_MAIL_TO_CHILD_00`
    FOREIGN KEY (`id_author` )
    REFERENCES AUTHOR (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('cartasDePadresAHijos.url','http://www.milka.com.ar/cartasDePadresAHijos.jsp','Url de la experiencia cartas de padres a hijos',0);

INSERT INTO NOTIFICATION_EMAIL(notificationType,description,content,deleted) VALUES('cartasdepadresahijos', 'Aprobacion de cartas de padres a hijos','contenido del email',0);

COMMIT;

CREATE TABLE LOVE_HATE (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `creationDate` DATETIME NOT NULL ,
  `publishDate` DATETIME NULL ,
  `content` VARCHAR(100) NULL ,
  `approved` INT NOT NULL,
  `love` INT NOT NULL,
  `votes` INT NOT NULL,
  `id_click_counter` INT NOT NULL,
  `url_link` VARCHAR(400) NULL ,
  `url_target` VARCHAR(50) NULL ,
  `deleted` INT NOT NULL ,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

DELETE FROM SYSPROPERTIES WHERE propKey = 'smpt.server';
DELETE FROM SYSPROPERTIES WHERE propKey = 'smpt.port';
DELETE FROM SYSPROPERTIES WHERE propKey LIKE 'mail.smtp%';
COMMIT;

INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('mail.smtp.user','contacto@milka.com.ar','mail.smtp.user',0);
INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('mail.smtp.host','smtp.gmail.com','mail.smtp.host',0);
INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('mail.smtp.port','465','mail.smtp.port',0);

INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('mail.smtp.starttls.enable','true','mail.smtp.starttls.enable',0);
INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('mail.smtp.auth','true','mail.smtp.auth',0);
INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('mail.smtp.socketFactory.port','465','mail.smtp.socketFactory.port',0);
INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('mail.smtp.socketFactory.class','javax.net.ssl.SSLSocketFactory','mail.smtp.socketFactory.class',0);
INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('mail.smtp.socketFactory.fallback','false','mail.smtp.socketFactory.fallback',0);
INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('mail.smtp.password','mil123ka','mail.smtp.password',0);

COMMIT;

DELETE FROM SYSPROPERTIES WHERE propKey = 'buendia.url';
DELETE FROM NOTIFICATION_EMAIL WHERE notificationType = 'buendia';
COMMIT;

INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('buendia.url','http://www.milka.com.ar/buendia.jsp','Url de la experiencia buen dia',0);

INSERT INTO NOTIFICATION_EMAIL(notificationType,description,content,deleted) VALUES('buendia', 'Aprobacion de buen dia','contenido del email',0);

COMMIT;

CREATE INDEX IX_EMAIL_ENDINGS_APP ON EMAIL_ENDINGS (approved); 
CREATE INDEX IX_GOOD_MORNING_APP ON GOOD_MORNING (approved);
CREATE INDEX IX_LOVE_HATE_APP ON LOVE_HATE (approved);
CREATE INDEX IX_LOVE_NICKNAMES_APP ON LOVE_NICKNAMES (approved);
CREATE INDEX IX_MAIL_TO_CHILD_APP ON MAIL_TO_CHILD (approved);
CREATE INDEX IX_MAIL_TO_PARENT_APP ON MAIL_TO_PARENT (approved);
CREATE INDEX IX_MILKA_PHOTO_APP ON MILKA_PHOTO (approved);
CREATE INDEX IX_POST_IT_APP ON POST_IT (approved);
CREATE INDEX IX_WALL_WRITTING_APP ON WALL_WRITTING (approved);