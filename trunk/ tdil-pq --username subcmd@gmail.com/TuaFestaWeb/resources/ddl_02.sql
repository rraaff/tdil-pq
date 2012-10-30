CREATE TABLE PROFESIONAL_AGENDA (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `id_profesional` INT NOT NULL,
  `date_` DATE NOT NULL ,
  `busy` INT NOT NULL,
  `deleted` INT NOT NULL ,
  PRIMARY KEY (`id`),
   CONSTRAINT `FK_PROFESIONAL_00`
    FOREIGN KEY (`id_profesional` )
    REFERENCES PROFESIONAL (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;