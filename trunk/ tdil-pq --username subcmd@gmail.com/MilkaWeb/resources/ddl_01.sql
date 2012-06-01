DROP TABLE IF EXISTS LOVE_NICKNAMES;

CREATE TABLE LOVE_NICKNAMES (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `creationDate` DATETIME NOT NULL ,
  `publishDate` DATETIME NULL ,
  `id_author` INT  NULL ,
  `sex` CHAR(1) NOT NULL ,
  `originalText` VARCHAR(150) NOT NULL ,
  `position` VARCHAR(25) NOT NULL ,
  `approved` INT NOT NULL,
  `id_click_counter` INT NULL,
  `deleted` INT NOT NULL ,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;