if exists (select * from INFORMATION_SCHEMA.TABLES where TABLE_NAME = 'SYSTEMUSER')
    drop table SYSTEMUSER;

if exists (select * from INFORMATION_SCHEMA.TABLES where TABLE_NAME = 'SYSPROPERTIES')
    drop table SYSPROPERTIES;

if exists (select * from INFORMATION_SCHEMA.TABLES where TABLE_NAME = 'BLOB_DATA')
    drop table BLOB_DATA;

if exists (select * from INFORMATION_SCHEMA.TABLES where TABLE_NAME = 'VERSION')
    drop table VERSION;	

if exists (select * from INFORMATION_SCHEMA.TABLES where TABLE_NAME = 'CACHE_REGION')
    drop table CACHE_REGION;
	
if exists (select * from INFORMATION_SCHEMA.TABLES where TABLE_NAME = 'POI')
    drop table POI;
	
if exists (select * from INFORMATION_SCHEMA.TABLES where TABLE_NAME = 'DEALER')
    drop table DEALER;

if exists (select * from INFORMATION_SCHEMA.TABLES where TABLE_NAME = 'CITY')
    drop table CITY;
    
if exists (select * from INFORMATION_SCHEMA.TABLES where TABLE_NAME = 'STATE')
    drop table STATE;

if exists (select * from INFORMATION_SCHEMA.TABLES where TABLE_NAME = 'SERVICE')
    drop table SERVICE;
    
if exists (select * from INFORMATION_SCHEMA.TABLES where TABLE_NAME = 'ADVICE')
    drop table ADVICE;
    
if exists (select * from INFORMATION_SCHEMA.TABLES where TABLE_NAME = 'VEHICLE')
    drop table VEHICLE;

if exists (select * from INFORMATION_SCHEMA.TABLES where TABLE_NAME = 'MODEL')
    drop table MODEL;
    
if exists (select * from INFORMATION_SCHEMA.TABLES where TABLE_NAME = 'CONTACTDATA')
    drop table CONTACTDATA;

if exists (select * from INFORMATION_SCHEMA.TABLES where TABLE_NAME = 'WEBSITEUSER')
    drop table WEBSITEUSER;    
    
CREATE TABLE WEBSITEUSER (
  id INT NOT NULL IDENTITY ,
  lojackUserId VARCHAR(50) NULL ,
  peugeotUserId VARCHAR(100) NULL ,
  homeUserId VARCHAR(100) NULL ,
  petUserId VARCHAR(100) NULL ,
  preventUserId VARCHAR(100) NULL ,
  id_avatar INT NULL,
  ext_avatar VARCHAR(10) NULL ,
  PRIMARY KEY (id),
  UNIQUE (lojackUserId ASC));

CREATE INDEX IX_WEBSITEUSER_00 ON WEBSITEUSER (lojackUserId);
CREATE INDEX IX_WEBSITEUSER_01 ON WEBSITEUSER (peugeotUserId);
CREATE INDEX IX_WEBSITEUSER_02 ON WEBSITEUSER (homeUserId);
CREATE INDEX IX_WEBSITEUSER_03 ON WEBSITEUSER (petUserId);
CREATE INDEX IX_WEBSITEUSER_04 ON WEBSITEUSER (preventUserId);

CREATE TABLE SYSTEMUSER (
  id INT NOT NULL IDENTITY ,
  username VARCHAR(20) NULL ,
  password VARCHAR(50) NULL ,
  type INT NOT NULL,
  deleted INT NOT NULL ,
  PRIMARY KEY (id));
  
CREATE INDEX IX_SYSTEMUSER_00 ON SYSTEMUSER (username);

INSERT INTO SYSTEMUSER(username,password,type,deleted) VALUES('tdil','f5314a8a0b0a34239a8bf78104f2ff4754a7a890', 0, 0);
COMMIT;

CREATE TABLE SYSPROPERTIES (
  id INT NOT NULL IDENTITY ,
  propKey VARCHAR(100) NOT NULL ,
  propValue VARCHAR(255) NULL ,
  description VARCHAR(300) NOT NULL ,
  deleted INT NOT NULL ,
  PRIMARY KEY (id));
  
CREATE UNIQUE INDEX UQ_SYSPROPERTIES_00 ON SYSPROPERTIES (propKey);

CREATE TABLE BLOB_DATA (
  id INT NOT NULL IDENTITY ,
  dataType VARCHAR(20) NOT NULL ,
  filename VARCHAR(100) NOT NULL ,
  content varBinary(MAX) NOT NULL ,
  deleted INT NOT NULL ,
  PRIMARY KEY (id));
  
CREATE TABLE VERSION (
	versionNumber INT NOT NULL);

INSERT INTO VERSION (versionNumber) VALUES(0);

CREATE TABLE CACHE_REGION (
  id INT NOT NULL IDENTITY ,
  version INT NOT NULL,
  name VARCHAR(200) NOT NULL ,
  deleted INT NOT NULL ,
  PRIMARY KEY (id));

CREATE UNIQUE INDEX IX_CACHE_REGION_00 ON CACHE_REGION (name);

CREATE TABLE POI (
  id INT NOT NULL IDENTITY ,
  type INT NOT NULL,
  subtype INT NOT NULL,
  name VARCHAR(200) NOT NULL ,
  description VARCHAR(4000) NOT NULL ,
  lat DECIMAL (10,8) NOT NULL ,
  lon DECIMAL (10,8) NOT  NULL ,
  PRIMARY KEY (id));

CREATE INDEX IX_POI_00 ON POI (type);

CREATE TABLE STATE (
  id INT NOT NULL IDENTITY ,
  name VARCHAR(200) NOT NULL ,
  deleted INT NOT NULL ,
  PRIMARY KEY (id));
  
CREATE INDEX IX_STATE_00 ON STATE (name);

CREATE TABLE CITY (
  id INT NOT NULL IDENTITY ,
  name VARCHAR(200) NOT NULL ,
  id_state INT NOT NULL CONSTRAINT FK_CITY_00 FOREIGN KEY REFERENCES STATE(id),
  deleted INT NOT NULL ,
  PRIMARY KEY (id));
  
CREATE INDEX IX_CITY_00 ON CITY (name);
CREATE INDEX IX_CITY_01 ON CITY (id_state);

CREATE TABLE DEALER (
  id INT NOT NULL IDENTITY ,
  name VARCHAR(200) NOT NULL ,
  address VARCHAR(200) NOT NULL ,
  email VARCHAR(100) NOT NULL ,
  phone VARCHAR(200) NOT NULL ,
  id_city INT NOT NULL CONSTRAINT FK_DEALER_00 FOREIGN KEY REFERENCES CITY(id),
  lat DECIMAL (10,8) NULL ,
  lon DECIMAL (10,8) NULL ,
  deleted INT NOT NULL ,
  PRIMARY KEY (id));

CREATE INDEX IX_DEALER_00 ON DEALER (id_city);

CREATE TABLE MODEL (
  id INT NOT NULL IDENTITY ,
  name VARCHAR(20) NULL ,
  description VARCHAR(100) NULL ,
  monthWarranty INT NOT NULL,
  deleted INT NOT NULL,
  PRIMARY KEY (id));

CREATE TABLE VEHICLE (
  id INT NOT NULL IDENTITY ,
  id_websiteuser INT NOT NULL CONSTRAINT FK_VEHICLE_00 FOREIGN KEY REFERENCES WEBSITEUSER(id),
  id_model INT NOT NULL CONSTRAINT FK_VEHICLE_01 FOREIGN KEY REFERENCES MODEL(id),
  description VARCHAR(100) NULL ,
  km INT NULL,
  lastServiceKm INT NULL,
  lastServiceDate DATE NULL ,
  needsAdvice1 INT NOT NULL,
  advice1sent INT NOT NULL,
  needsAdvice2 INT NOT NULL,
  advice2sent INT NOT NULL,
  needsAdvice3 INT NOT NULL,
  advice3sent INT NOT NULL,
  warrantyExpired INT NOT NULL,
  deleted INT NOT NULL,
  PRIMARY KEY (id));
  
CREATE INDEX IX_VEHICLE_00 ON VEHICLE(id_websiteuser);
CREATE INDEX IX_VEHICLE_01 ON VEHICLE(id_model);

CREATE TABLE ADVICE (
  id INT NOT NULL IDENTITY ,
  id_vechicle INT NOT NULL CONSTRAINT FK_ADVICE_00 FOREIGN KEY REFERENCES VEHICLE(id),
  km INT NULL,
  adviseDate DATE NULL ,
  adviseNumber INT NOT NULL,
  isread INT NOT NULL,
  deleted INT NOT NULL,
  PRIMARY KEY (id));

CREATE INDEX IX_ADVICE_00 ON ADVICE(id_vechicle);

CREATE TABLE SERVICE (
  id INT NOT NULL IDENTITY ,
  id_vechicle INT NOT NULL CONSTRAINT FK_SERVICE_00 FOREIGN KEY REFERENCES VEHICLE(id),
  km INT NULL,
  serviceDate DATE NULL ,
  deleted INT NOT NULL,
  PRIMARY KEY (id));

CREATE INDEX IX_SERVICE_00 ON SERVICE(id_vechicle);

CREATE TABLE CONTACTDATA (
  id INT NOT NULL IDENTITY ,
  id_websiteuser INT NOT NULL CONSTRAINT FK_CONTACTDATA_00 FOREIGN KEY REFERENCES WEBSITEUSER(id),
  contact1Name VARCHAR(150) NULL ,
  contact1Relation VARCHAR(150) NULL ,
  contact1Phone VARCHAR(20) NULL ,
  contact1SecWord VARCHAR(20) NULL ,
  contact1HealthI VARCHAR(100) NULL ,
  contact2Name VARCHAR(150) NULL ,
  contact2Relation VARCHAR(150) NULL ,
  contact2Phone VARCHAR(20) NULL ,
  contact3Name VARCHAR(150) NULL ,
  contact3Relation VARCHAR(150) NULL ,
  contact3Phone VARCHAR(20) NULL ,
  deleted INT NOT NULL,
  PRIMARY KEY (id));
  
CREATE INDEX IX_CONTACTDATA_00 ON CONTACTDATA(id_websiteuser);
