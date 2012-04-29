DROP TABLE IF EXISTS SYSTEMUSER;
DROP TABLE IF EXISTS FBUSER;
DROP TABLE IF EXISTS PARTICIPATION;
DROP TABLE IF EXISTS DAILY_PRIZE;

CREATE  TABLE SYSTEMUSER (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `username` VARCHAR(20) NULL ,
  `password` VARCHAR(4000) NULL ,
  `name` VARCHAR(255) NULL ,
  `email` VARCHAR(150) NULL ,
  `resetcode` VARCHAR(100) NULL ,
  `deleted` INT NOT NULL,
  PRIMARY KEY (`id`) ,
  INDEX `IX_SYSTEMUSER_00` (`username` ASC),
  INDEX `IX_SYSTEMUSER_01` (`email` ASC))
ENGINE = InnoDB;

INSERT INTO SYSTEMUSER(username,password,name,email, deleted) VALUES('Admin',SHA1('Admin'), 'Admin', 'admin@admin.com', 0);

CREATE TABLE `FBUSER` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `fbid` VARCHAR(255) NULL ,
  `fbemail` VARCHAR(255) NULL ,
  `fbname` VARCHAR(255) NULL ,
  `fbusername` VARCHAR(250) NULL ,
  `fbgender` VARCHAR(100) NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fbidasc` (`fbid` ASC) );

CREATE  TABLE `PARTICIPATION` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `creationDate` DATE NOT NULL,
  `fbuserID` INT NOT NULL ,
  `xcoord` VARCHAR(10) NOT NULL,
  `ycoord` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `IX_PARTICIPATION_00` (`fbuserID` ASC) );
  
CREATE  TABLE `DAILY_PRIZE` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `prizeDate` DATE NOT NULL,
  `participationID` INT NULL ,
  `xcoord` VARCHAR(10) NOT NULL,
  `ycoord` VARCHAR(10) NOT NULL,
  `active` INT NOT NULL ,
  PRIMARY KEY (`id`) );