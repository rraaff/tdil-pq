DROP TABLE WALL_WRITTING;
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