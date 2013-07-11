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

INSERT INTO SYSTEMUSER(username,password,loggingAccess,syspropAccess, deleted) VALUES('tdil','f5314a8a0b0a34239a8bf78104f2ff4754a7a890', 1, 1, 0);
COMMIT;