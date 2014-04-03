DROP TABLE IF EXISTS NATIVE_APP;

CREATE TABLE NATIVE_APP (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `code` VARCHAR(20) NULL ,
  `title` VARCHAR(100) NULL ,
  `version` VARCHAR(100) NULL ,
  `url` VARCHAR(400) NULL ,
  `deleted` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `IX_NATIVE_APP_00` (`code` ASC))
ENGINE = InnoDB;

insert into NATIVE_APP(code,title,version,url,deleted)
values('android','Peugeot Service Android','1.0.0','http://www.google.com',0);
commit;