DELETE FROM VERSION;
INSERT INTO VERSION (versionNumber) VALUES(3);

COMMIT;

DROP TABLE IF EXISTS MAIL_TO_CHILD;

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