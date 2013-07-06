CREATE TABLE SYSTEMUSER (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `username` VARCHAR(20) NULL ,
  `password` VARCHAR(50) NULL ,
  `loggingAccess` INT NOT NULL,
  `syspropAccess` INT NOT NULL,
  `deleted` INT NOT NULL,
  PRIMARY KEY (`id`) ,
  INDEX `IX_SYSTEMUSER_00` (`username` ASC))
ENGINE = InnoDB;

INSERT INTO SYSTEMUSER(username,password,loggingAccess,syspropAccess, deleted) VALUES('Admin',SHA1('Admin'), 1, 1, 0);
COMMIT;