DROP TABLE IF EXISTS WEBSITEUSER;
DROP TABLE IF EXISTS SYSPROPERTIES;
DROP TABLE IF EXISTS BLOB_DATA;
DROP TABLE IF EXISTS CACHE_REGION;

DROP TABLE IF EXISTS VERSION;

CREATE TABLE WEBSITEUSER (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `userId` VARCHAR(50) NULL ,
  `id_avatar` INT NULL,
  `ext_avatar` VARCHAR(10) NULL ,
  PRIMARY KEY (`id`),
  INDEX `IX_WEBSITEUSER_00` (`userId` ASC))
ENGINE = InnoDB;

CREATE TABLE SYSPROPERTIES (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `propKey` VARCHAR(100) NOT NULL ,
  `propValue` VARCHAR(255) NULL ,
  `description` VARCHAR(300) NOT NULL ,
  `deleted` INT NOT NULL ,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `UQ_SYSPROPERTIES_00` (`propKey` ASC))
ENGINE = InnoDB;

INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('thalamus.server','http://localhost:8280/ThalamusWeb/','thalamus.server',0);
INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('thalamus.touchpoint.code','test','thalamus.touchpoint.code',0);
INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('thalamus.touchpoint.token','testtesttesttesttesttesttesttesttesttesttesttesttesttesttesttest','thalamus.touchpoint.token',0);
INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('gis.server','http://localhost:8180/GISWeb/gis/','gis.server',0);
INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('services.server','http://localhost:8180/GISWeb/services/','services.server',0);
INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('prop.tmp.path','/home/mgodoy/icarus/apache-tomcat-6.0.32/temp','prop.tmp.path',0);

CREATE TABLE BLOB_DATA (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `dataType` VARCHAR(20) NOT NULL ,
  `filename` VARCHAR(100) NOT NULL ,
  `content` MEDIUMBLOB NOT NULL ,
  `deleted` INT NOT NULL ,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE TABLE VERSION (
	versionNumber INT NOT NULL)
ENGINE = MYISAM;

INSERT INTO VERSION (versionNumber) VALUES(0);

COMMIT;

CREATE TABLE CACHE_REGION (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `version` INT NOT NULL,
  `name` VARCHAR(200) NOT NULL ,
  `deleted` INT NOT NULL ,
  PRIMARY KEY (`id`),
  INDEX `IX_CACHE_REGION_00` (`name` ASC))
ENGINE = InnoDB;

INSERT INTO CACHE_REGION(version, name, deleted) VALUES(1, 'com.tdil.lojack.model.Category',0);
COMMIT;
