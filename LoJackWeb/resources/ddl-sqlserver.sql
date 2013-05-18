if exists (select * from INFORMATION_SCHEMA.TABLES where TABLE_NAME = 'ALARM_CONF')
    drop table ALARM_CONF;

if exists (select * from INFORMATION_SCHEMA.TABLES where TABLE_NAME = 'LIGHT_CONF')
    drop table LIGHT_CONF;

if exists (select * from INFORMATION_SCHEMA.TABLES where TABLE_NAME = 'ASYNC_JOB')
    drop table ASYNC_JOB;

if exists (select * from INFORMATION_SCHEMA.TABLES where TABLE_NAME = 'WEBSITEUSER')
    drop table WEBSITEUSER;

if exists (select * from INFORMATION_SCHEMA.TABLES where TABLE_NAME = 'SYSPROPERTIES')
    drop table SYSPROPERTIES;	

if exists (select * from INFORMATION_SCHEMA.TABLES where TABLE_NAME = 'BLOB_DATA')
    drop table BLOB_DATA;
	
if exists (select * from INFORMATION_SCHEMA.TABLES where TABLE_NAME = 'CACHE_REGION')
    drop table CACHE_REGION;
	
if exists (select * from INFORMATION_SCHEMA.TABLES where TABLE_NAME = 'POI')
    drop table POI;

if exists (select * from INFORMATION_SCHEMA.TABLES where TABLE_NAME = 'VERSION')
    drop table VERSION;

CREATE TABLE WEBSITEUSER (
  id INT NOT NULL IDENTITY ,
  lojackUserId VARCHAR(50) NULL ,
  id_avatar INT NULL,
  ext_avatar VARCHAR(10) NULL ,
  PRIMARY KEY (id),
  UNIQUE (lojackUserId ASC));
  
CREATE TABLE ALARM_CONF (
  id INT NOT NULL IDENTITY ,
  idEntidad INT NOT NULL ,
  idWebsiteUser INT NOT NULL 
    CONSTRAINT FK_ALARM_CONF_00 FOREIGN KEY REFERENCES WEBSITEUSER(id),
  description VARCHAR(100) NULL ,
  emailNotification INT NOT NULL ,
  PRIMARY KEY (id));
    
CREATE INDEX IX_ALARM_CONF_00 ON ALARM_CONF (idWebsiteUser);

CREATE TABLE LIGHT_CONF (
  id INT NOT NULL IDENTITY ,
  idEntidad INT NOT NULL ,
  idLuz INT NOT NULL ,
  idWebsiteUser INT NOT NULL 
    CONSTRAINT FK_LIGHT_CONF_00 FOREIGN KEY REFERENCES WEBSITEUSER(id),
  description VARCHAR(100) NULL ,
  emailNotification INT NOT NULL ,
  PRIMARY KEY (id));
    
CREATE INDEX IX_LIGHT_CONF_00 ON LIGHT_CONF (idWebsiteUser);

CREATE TABLE ASYNC_JOB (
  id INT NOT NULL IDENTITY ,
  idJob INT NOT NULL ,
  idWebsiteUser INT NOT NULL 
     CONSTRAINT FK_ASYNC_JOB_00 FOREIGN KEY REFERENCES WEBSITEUSER(id),
  postDate DATETIME NOT NULL ,
  action INT NOT NULL ,
  idEntidad INT NOT NULL ,
  idLuz INT NOT NULL ,
  status INT NOT NULL ,
  PRIMARY KEY (id));

CREATE UNIQUE INDEX IX_ASYNC_JOB_00 ON ASYNC_JOB (idJob);

CREATE TABLE SYSPROPERTIES (
  id INT NOT NULL IDENTITY ,
  propKey VARCHAR(100) NOT NULL ,
  propValue VARCHAR(255) NULL ,
  description VARCHAR(300) NOT NULL ,
  deleted INT NOT NULL ,
  PRIMARY KEY (id));
  
CREATE UNIQUE INDEX UQ_SYSPROPERTIES_00 ON SYSPROPERTIES (propKey);

INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('front.server','http://localhost:8180/LoJackWeb','front.server',0);
INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('thalamus.server','http://localhost:8280/ThalamusWeb/','thalamus.server',0);
INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('thalamus.touchpoint.code','test','thalamus.touchpoint.code',0);
INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('thalamus.touchpoint.token','testtesttesttesttesttesttesttesttesttesttesttesttesttesttesttest','thalamus.touchpoint.token',0);
INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('gis.server','http://localhost:8180/GISWeb/gis/','gis.server',0);
INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('services.server','http://localhost:8180/GISWeb/services/','services.server',0);
INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('prevent.server','http://www.lojackgis.com.ar/PreventWCFServices/GISService.svc','prevent.server',0);
INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('prop.tmp.path','/home/mgodoy/icarus/apache-tomcat-6.0.32/temp','prop.tmp.path',0);
INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('camera.mobile.refreshTime','1000','camera.mobile.refreshTime',0);
INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('camera.applet.refreshTime','1000','camera.applet.refreshTime',0);
INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('job.refresh.time','1000','job.refresh.time',0);
INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('job.abort.time','60000','job.abort.time',0);
INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('job.client.refresh.time','1000','job.client.refresh.time',0);
INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('front.login.deplay','100','front.login.deplay',0);

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