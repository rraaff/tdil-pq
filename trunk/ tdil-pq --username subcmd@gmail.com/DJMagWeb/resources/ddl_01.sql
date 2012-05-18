DELETE FROM SECTION WHERE sectionType = 'IMAGE_GALLERY';
COMMIT;
INSERT INTO SECTION (name, sectionType, deleted) VALUES('IMAGE_GALLERIES','IMAGE_GALLERY', 0);

DROP TABLE IF EXISTS IMAGE_IN_GAL;
DROP TABLE IF EXISTS IMAGE_GALLERY;
DROP TABLE IF EXISTS RANKING_POS_IMG;
DROP TABLE IF EXISTS RANKING_POSITION;

ALTER TABLE COUNTRY ENGINE=InnoDB;

CREATE TABLE IMAGE_GALLERY (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `title` VARCHAR(250) NOT NULL ,
  `description` VARCHAR(4000) NOT NULL ,
  `id_country` INT NOT NULL,
  `deleted` INT NOT NULL ,
  PRIMARY KEY (`id`),
  INDEX `UQ_IMAGE_GALLERY_00` (`id_country` ASC),
  CONSTRAINT `FK_IMAGE_GALLERY_00`
    FOREIGN KEY (`id_country` )
    REFERENCES COUNTRY (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IMAGE_IN_GAL (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `id_gallery` INT NOT NULL,
  `imageExt` VARCHAR(10) NOT NULL ,
  `image_id` INT NULL,
  `orderNumber` INT NOT NULL ,
  `deleted` INT NOT NULL ,
  PRIMARY KEY (`id`),
  INDEX `IX_IMAGE_IN_GAL_00` (`id_gallery` ASC),
  CONSTRAINT `FK_IMAGE_IN_GAL_01`
    FOREIGN KEY (`id_gallery` )
    REFERENCES IMAGE_GALLERY (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE RANKING_POSITION (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `id_ranking_note` INT NOT NULL,
  `title` VARCHAR(250) NULL ,
  `summary` VARCHAR(4000) NULL ,
  `content` MEDIUMTEXT NULL ,
  `orderNumber` INT NOT NULL ,
  `imageExt` VARCHAR(10) NULL ,
  `image_id` INT NULL,
  `deleted` INT NOT NULL ,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE TABLE RANKING_POS_IMG (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `id_ranking_pos` INT NOT NULL,
  `imageExt` VARCHAR(10) NOT NULL ,
  `image_id` INT NULL,
  `orderNumber` INT NOT NULL ,
  `deleted` INT NOT NULL ,
  PRIMARY KEY (`id`),
  INDEX `IX_RANKING_POS_IMG_00` (`id_ranking_pos` ASC),
  CONSTRAINT `FK_RANKING_POS_IMG_00`
    FOREIGN KEY (`id_ranking_pos` )
    REFERENCES RANKING_POSITION (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

ALTER TABLE RANKING_NOTE MODIFY positions TEXT NULL;