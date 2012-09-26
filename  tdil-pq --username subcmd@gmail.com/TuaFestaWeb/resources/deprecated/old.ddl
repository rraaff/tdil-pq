CREATE TABLE PROFILE_PICTURE (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `id_blob_data` INT NOT NULL,
  `ext_blob_data` VARCHAR(10) NOT NULL ,
  `deleted` INT NOT NULL ,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE TABLE PROMOTION_VIDEO (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `id_promotion` INT NOT NULL,
  `orderNumber` INT NOT NULL ,
  `url` VARCHAR(200) NOT NULL,
  `deleted` INT NOT NULL ,
  PRIMARY KEY (`id`),
   CONSTRAINT `FK_PROMOTION_VIDEO_00`
    FOREIGN KEY (`id_promotion` )
    REFERENCES PROMOTION (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE PROFILE_VIDEO (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `id_profesional` INT NOT NULL,
  `orderNumber` INT NOT NULL ,
  `approved` INT NOT NULL,
  `url` VARCHAR(200) NOT NULL,
  `deleted` INT NOT NULL ,
  PRIMARY KEY (`id`),
   CONSTRAINT `FK_PROFILE_VIDEO_00`
    FOREIGN KEY (`id_profesional` )
    REFERENCES PROFESIONAL (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


CREATE TABLE SELL_PHOTO (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `id_sell` INT NOT NULL,
  `orderNumber` INT NOT NULL ,
  `approved` INT NOT NULL,
  `id_blob_data` INT NOT NULL,
  `ext_blob_data` VARCHAR(10) NOT NULL ,
  `deleted` INT NOT NULL ,
  PRIMARY KEY (`id`),
   CONSTRAINT `FK_SELL_PHOTO_00`
    FOREIGN KEY (`id_sell` )
    REFERENCES SELL (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE SELL_VIDEO (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `id_sell` INT NOT NULL,
  `orderNumber` INT NOT NULL ,
  `approved` INT NOT NULL,
  `url` VARCHAR(200) NOT NULL,
  `deleted` INT NOT NULL ,
  PRIMARY KEY (`id`),
   CONSTRAINT `FK_SELL_VIDEO_00`
    FOREIGN KEY (`id_sell` )
    REFERENCES SELL (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


CREATE TABLE PROF_PRODUCT (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(100) NOT NULL ,
  `description` VARCHAR(4000) NOT NULL ,
  `average_price` INT NULL,
  `approved` INT NOT NULL ,
  `id_category` INT NULL,
  `deleted` INT NOT NULL ,
  PRIMARY KEY (`id`),
  INDEX `IX_PROF_PROD_ID_CAT_00` (`id_category` ASC))
ENGINE = InnoDB;

CREATE TABLE PROF_PROD_CATEGORY (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(100) NOT NULL ,
  `description` VARCHAR(4000) NOT NULL ,
  `parent_id` INT NULL,
  `isother` INT NOT NULL DEFAULT 0,
  `deleted` INT NOT NULL ,
  PRIMARY KEY (`id`),
  INDEX `IX_PROF_PROD_CAT_00` (`parent_id` ASC))
ENGINE = InnoDB;

CREATE TABLE PROF_SERVICE (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(100) NOT NULL ,
  `description` VARCHAR(4000) NOT NULL ,
  `average_price` INT NULL,
  `approved` INT NOT NULL ,
  `id_category` INT NULL,
  `deleted` INT NOT NULL ,
  PRIMARY KEY (`id`),
  INDEX `IX_PROF_SERV_ID_CAT_00` (`id_category` ASC))
ENGINE = InnoDB;