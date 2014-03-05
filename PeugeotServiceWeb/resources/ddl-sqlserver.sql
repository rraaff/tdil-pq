if exists (select * from INFORMATION_SCHEMA.TABLES where TABLE_NAME = 'ALERT')
    drop table ALERT;  

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
	
if exists (select * from INFORMATION_SCHEMA.TABLES where TABLE_NAME = 'SERVICE')
    drop table SERVICE;
    
if exists (select * from INFORMATION_SCHEMA.TABLES where TABLE_NAME = 'ADVICE')
    drop table ADVICE;
    
if exists (select * from INFORMATION_SCHEMA.TABLES where TABLE_NAME = 'VEHICLE')
    drop table VEHICLE;
    
if exists (select * from INFORMATION_SCHEMA.TABLES where TABLE_NAME = 'DEALER')
    drop table DEALER;

if exists (select * from INFORMATION_SCHEMA.TABLES where TABLE_NAME = 'CITY')
    drop table CITY;
    
if exists (select * from INFORMATION_SCHEMA.TABLES where TABLE_NAME = 'STATE')
    drop table STATE;

if exists (select * from INFORMATION_SCHEMA.TABLES where TABLE_NAME = 'MODEL')
    drop table MODEL;
    
if exists (select * from INFORMATION_SCHEMA.TABLES where TABLE_NAME = 'CONTACTDATA')
    drop table CONTACTDATA;

if exists (select * from INFORMATION_SCHEMA.TABLES where TABLE_NAME = 'DATA_IMPORT')
    drop table DATA_IMPORT;

if exists (select * from INFORMATION_SCHEMA.TABLES where TABLE_NAME = 'WEBSITEUSER')
    drop table WEBSITEUSER;    
    
if exists (select * from INFORMATION_SCHEMA.TABLES where TABLE_NAME = 'NOTIFICATION_EMAIL')
    drop table NOTIFICATION_EMAIL; 
    
if exists (select * from INFORMATION_SCHEMA.TABLES where TABLE_NAME = 'KM_DATA')
    drop table KM_DATA;    
    
CREATE TABLE WEBSITEUSER (
  id INT NOT NULL IDENTITY ,
  dni VARCHAR(20) NULL ,
  firstName VARCHAR(150) NULL ,
  lastName VARCHAR(150) NULL ,
  lojackUserId VARCHAR(50) NULL ,
  peugeotUserId VARCHAR(100) NULL ,
  homeUserId VARCHAR(100) NULL ,
  petUserId VARCHAR(100) NULL ,
  preventUserId VARCHAR(100) NULL ,
  email VARCHAR(150) NULL ,
  id_avatar INT NULL,
  ext_avatar VARCHAR(10) NULL ,
  PRIMARY KEY (id),
  UNIQUE (lojackUserId ASC));

CREATE INDEX IX_WEBSITEUSER_00 ON WEBSITEUSER (lojackUserId);
CREATE INDEX IX_WEBSITEUSER_01 ON WEBSITEUSER (peugeotUserId);
CREATE INDEX IX_WEBSITEUSER_02 ON WEBSITEUSER (homeUserId);
CREATE INDEX IX_WEBSITEUSER_03 ON WEBSITEUSER (petUserId);
CREATE INDEX IX_WEBSITEUSER_04 ON WEBSITEUSER (preventUserId);
CREATE INDEX IX_WEBSITEUSER_05 ON WEBSITEUSER (dni);
CREATE INDEX IX_WEBSITEUSER_06 ON WEBSITEUSER (email);

CREATE TABLE SYSTEMUSER (
  id INT NOT NULL IDENTITY ,
  username VARCHAR(20) NULL ,
  password VARCHAR(50) NULL ,
  type INT NOT NULL,
  loggingAccess INT NOT NULL,
  syspropAccess INT NOT NULL,
  modelImportAccess INT NOT NULL,
  dealerImportAccess INT NOT NULL,
  emailTemplateAccess INT NOT NULL,
  deleted INT NOT NULL ,
  PRIMARY KEY (id));
  
CREATE INDEX IX_SYSTEMUSER_00 ON SYSTEMUSER (username);

INSERT INTO SYSTEMUSER(username,password,type,loggingAccess,syspropAccess,modelImportAccess,dealerImportAccess,emailTemplateAccess,deleted) VALUES('tdil','f5314a8a0b0a34239a8bf78104f2ff4754a7a890', 0, 1, 1, 1, 1, 1, 0);
INSERT INTO SYSTEMUSER(username,password,type,loggingAccess,syspropAccess,modelImportAccess,dealerImportAccess,emailTemplateAccess,deleted) VALUES('cc','bdb480de655aa6ec75ca058c849c4faf3c0f75b1', 1, 0, 0, 0, 0, 0, 0);
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

CREATE TABLE DATA_IMPORT (
  id INT NOT NULL IDENTITY ,
  type VARCHAR(40) NOT NULL ,
  filename VARCHAR(400) NOT NULL ,
  status VARCHAR(20) NOT NULL ,
  processed INT NOT NULL,
  errors INT NOT NULL,
  startTime DATETIME NULL ,
  endTime DATETIME NULL ,
  PRIMARY KEY (id));

CREATE INDEX IX_DATA_IMPORT_00 ON DATA_IMPORT(type);

CREATE TABLE DEALER (
  id INT NOT NULL IDENTITY ,
  id_data_import INT NOT NULL CONSTRAINT FK_DEALER_01 FOREIGN KEY REFERENCES DATA_IMPORT(id),
  code VARCHAR(20) NOT NULL ,
  name VARCHAR(200) NOT NULL ,
  address VARCHAR(200) NOT NULL ,
  postalCode VARCHAR(20) NULL ,
  email VARCHAR(150) NOT NULL ,
  phone VARCHAR(200) NOT NULL ,
  fax VARCHAR(200) NULL ,
  category VARCHAR(200) NULL ,
  locationType VARCHAR(200) NULL ,
  id_city INT NOT NULL CONSTRAINT FK_DEALER_00 FOREIGN KEY REFERENCES CITY(id),
  lat DECIMAL (10,8) NULL ,
  lon DECIMAL (10,8) NULL ,
  deleted INT NOT NULL ,
  PRIMARY KEY (id));

CREATE INDEX IX_DEALER_00 ON DEALER (id_city);
CREATE INDEX IX_DEALER_01 ON DEALER (id_data_import);

CREATE TABLE MODEL (
  id INT NOT NULL IDENTITY ,
  name VARCHAR(20) NULL ,
  description VARCHAR(100) NULL ,
  monthWarranty INT NOT NULL,
  kmWarranty INT NOT NULL,
  deleted INT NOT NULL,
  PRIMARY KEY (id));

CREATE TABLE VEHICLE (
  id INT NOT NULL IDENTITY ,
  id_websiteuser INT NOT NULL CONSTRAINT FK_VEHICLE_00 FOREIGN KEY REFERENCES WEBSITEUSER(id),
  id_model INT NULL CONSTRAINT FK_VEHICLE_01 FOREIGN KEY REFERENCES MODEL(id),
  id_dealer INT NULL CONSTRAINT FK_VEHICLE_02 FOREIGN KEY REFERENCES DEALER(id),
  purchaseDate DATE NULL ,
  domain VARCHAR(20) NULL ,
  description VARCHAR(100) NULL ,
  km INT NULL,
  lastServiceKm INT NULL,
  lastServiceDate DATE NULL ,
  needsAdvice INT NOT NULL,
  needsAdvice1 INT NOT NULL,
  needsAdvice1Date DATE NULL,
  advice1sent INT NOT NULL,
  needsAdvice2 INT NOT NULL,
  needsAdvice2Date DATE NULL,
  advice2sent INT NOT NULL,
  needsAdvice3 INT NOT NULL,
  needsAdvice3Date DATE NULL,
  advice3sent INT NOT NULL,
  warrantyExpired INT NOT NULL,
  deleted INT NOT NULL,
  PRIMARY KEY (id));
  
CREATE INDEX IX_VEHICLE_00 ON VEHICLE(id_websiteuser);
CREATE INDEX IX_VEHICLE_01 ON VEHICLE(id_model);
CREATE INDEX IX_VEHICLE_02 ON VEHICLE(needsAdvice);
CREATE INDEX IX_VEHICLE_03 ON VEHICLE(id_dealer);

CREATE TABLE ADVICE (
  id INT NOT NULL IDENTITY ,
  id_vechicle INT NOT NULL CONSTRAINT FK_ADVICE_00 FOREIGN KEY REFERENCES VEHICLE(id),
  km INT NULL,
  serviceDate DATE NULL,
  adviseDate DATE NULL ,
  adviseNumber INT NOT NULL,
  isread INT NOT NULL,
  deleted INT NOT NULL,
  PRIMARY KEY (id));

CREATE INDEX IX_ADVICE_00 ON ADVICE(id_vechicle);

CREATE TABLE SERVICE (
  id INT NOT NULL IDENTITY ,
  id_vehicle INT NOT NULL CONSTRAINT FK_SERVICE_00 FOREIGN KEY REFERENCES VEHICLE(id),
  km INT NULL,
  serviceDate DATE NULL ,
  deleted INT NOT NULL,
  PRIMARY KEY (id));

CREATE INDEX IX_SERVICE_00 ON SERVICE(id_vehicle);

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

CREATE TABLE NOTIFICATION_EMAIL (
  id INT NOT NULL IDENTITY ,
  notificationType VARCHAR(50) NOT NULL ,
  description VARCHAR(300) NOT NULL ,
  subject VARCHAR(50) NOT NULL ,
  from_ VARCHAR(100) NOT NULL ,
  replacements VARCHAR(max) NULL ,
  content VARCHAR(max) NOT NULL ,
  deleted INT NOT NULL ,
  PRIMARY KEY (id));

CREATE TABLE KM_DATA (
  id INT NOT NULL IDENTITY ,
  dominio VARCHAR(20) NOT NULL ,
  km INT NOT NULL ,
  fechareporte DATE NOT NULL ,
  marca VARCHAR(100) NOT NULL ,
  modelo VARCHAR(100) NOT NULL ,
  fechaalta DATE NOT NULL ,
  identidad INT NOT NULL ,
  idequipo INT NOT NULL ,
  userlogin VARCHAR(100) NOT NULL ,
  cliente_nombre VARCHAR(200) NOT NULL ,
  cliente_cuit VARCHAR(100) NOT NULL ,
  deleted INT NOT NULL DEFAULT 0,
  PRIMARY KEY (id));

CREATE INDEX IX_KM_DATA_00 ON KM_DATA(dominio);

CREATE TABLE ALERT (
  id INT NOT NULL IDENTITY ,
  id_websiteuser INT NOT NULL CONSTRAINT FK_ALERT_00 FOREIGN KEY REFERENCES WEBSITEUSER(id),
  id_systemuser INT NULL CONSTRAINT FK_ALERT_01 FOREIGN KEY REFERENCES SYSTEMUSER(id),
  creationDate DATETIME NOT NULL ,
  modificationDate DATETIME NULL ,
  phoneNumber VARCHAR(20) NULL ,
  lat DECIMAL (10,8) NULL ,
  lon DECIMAL (10,8) NULL ,
  status INT NOT NULL,
  deleted INT NOT NULL,
  PRIMARY KEY (id));

CREATE INDEX IX_ALERT_00 ON ALERT(id_websiteuser);
CREATE INDEX IX_ALERT_01 ON ALERT(status,creationDate);
CREATE INDEX IX_ALERT_02 ON ALERT(id_systemuser);

INSERT INTO NOTIFICATION_EMAIL(notificationType,description,content,subject,from_,deleted,
replacements) 
VALUES('first.advice.km', 'Primer aviso de service por KM','Estimado [FIRST_NAME] [LAST_NAME], le recordamos que su vehiculo [DOMAIN] debe realizar el service','Primer aviso de service por KM', 'test.lojack.front@gmail.com', 0,
'SERVER_NAME,FIRST_NAME,LAST_NAME,DOMAIN,ACTUAL_KM,LAST_SERVICE_KM,NEXT_SERVICE_KM,NEXT_SERVICE_DATE,HAS_DEALER,DEALER_NAME,DEALER_ADDRESS,DEALER_PHONE,DEALER_EMAIL');

INSERT INTO NOTIFICATION_EMAIL(notificationType,description,content,subject,from_,deleted,
replacements) 
VALUES('second.advice.km', 'Segundo aviso de service por KM','Estimado [FIRST_NAME] [LAST_NAME], le recordamos que su vehiculo [DOMAIN] debe realizar el service','Segundo aviso de service por KM', 'test.lojack.front@gmail.com', 0,
'SERVER_NAME,FIRST_NAME,LAST_NAME,DOMAIN,ACTUAL_KM,LAST_SERVICE_KM,NEXT_SERVICE_KM,NEXT_SERVICE_DATE,HAS_DEALER,DEALER_NAME,DEALER_ADDRESS,DEALER_PHONE,DEALER_EMAIL');

INSERT INTO NOTIFICATION_EMAIL(notificationType,description,content,subject,from_,deleted,
replacements)
VALUES('third.advice.km', 'Tercer aviso de service por KM','Estimado [FIRST_NAME] [LAST_NAME], le recordamos que su vehiculo [DOMAIN] debe realizar el service','Tercer aviso aviso de service por KM', 'test.lojack.front@gmail.com', 0,
'SERVER_NAME,FIRST_NAME,LAST_NAME,DOMAIN,ACTUAL_KM,LAST_SERVICE_KM,NEXT_SERVICE_KM,NEXT_SERVICE_DATE,HAS_DEALER,DEALER_NAME,DEALER_ADDRESS,DEALER_PHONE,DEALER_EMAIL');

INSERT INTO NOTIFICATION_EMAIL(notificationType,description,content,subject,from_,deleted,
replacements) 
VALUES('first.advice.date', 'Primer aviso de service por fecha','Estimado [FIRST_NAME] [LAST_NAME], le recordamos que su vehiculo [DOMAIN] debe realizar el service','Primer aviso de service por fecha', 'test.lojack.front@gmail.com', 0,
'SERVER_NAME,FIRST_NAME,LAST_NAME,DOMAIN,ACTUAL_KM,LAST_SERVICE_KM,NEXT_SERVICE_KM,NEXT_SERVICE_DATE,HAS_DEALER,DEALER_NAME,DEALER_ADDRESS,DEALER_PHONE,DEALER_EMAIL');

INSERT INTO NOTIFICATION_EMAIL(notificationType,description,content,subject,from_,deleted,
replacements) 
VALUES('second.advice.date', 'Segundo aviso de service por fecha','Estimado [FIRST_NAME] [LAST_NAME], le recordamos que su vehiculo [DOMAIN] debe realizar el service','Segundo aviso de service por fecha', 'test.lojack.front@gmail.com', 0,
'SERVER_NAME,FIRST_NAME,LAST_NAME,DOMAIN,ACTUAL_KM,LAST_SERVICE_KM,NEXT_SERVICE_KM,NEXT_SERVICE_DATE,HAS_DEALER,DEALER_NAME,DEALER_ADDRESS,DEALER_PHONE,DEALER_EMAIL');

INSERT INTO NOTIFICATION_EMAIL(notificationType,description,content,subject,from_,deleted,
replacements) 
VALUES('third.advice.date', 'Tercer aviso de service por fecha','Estimado [FIRST_NAME] [LAST_NAME], le recordamos que su vehiculo [DOMAIN] debe realizar el service','Tercer aviso aviso de service por fecha', 'test.lojack.front@gmail.com', 0,
'SERVER_NAME,FIRST_NAME,LAST_NAME,DOMAIN,ACTUAL_KM,LAST_SERVICE_KM,NEXT_SERVICE_KM,NEXT_SERVICE_DATE,HAS_DEALER,DEALER_NAME,DEALER_ADDRESS,DEALER_PHONE,DEALER_EMAIL');

INSERT INTO NOTIFICATION_EMAIL(notificationType,description,content,subject,from_,deleted,
replacements) 
VALUES('dealer.advice', 'Aviso a la concesionaria','Estimado [DEALER_NAME], el vehiculo [DOMAIN] perteneciente a [FIRST_NAME] [LAST_NAME] debe realizar el service','Aviso para la concesionaria', 'test.lojack.front@gmail.com', 0,
'SERVER_NAME,FIRST_NAME,LAST_NAME,DOMAIN,ACTUAL_KM,LAST_SERVICE_KM,NEXT_SERVICE_KM,NEXT_SERVICE_DATE,HAS_DEALER,DEALER_NAME,DEALER_ADDRESS,DEALER_PHONE,DEALER_EMAIL');

COMMIT;