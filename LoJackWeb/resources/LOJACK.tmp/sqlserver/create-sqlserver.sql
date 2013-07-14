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
    
if exists (select * from INFORMATION_SCHEMA.TABLES where TABLE_NAME = 'SYSTEMUSER')
    drop table SYSTEMUSER;

CREATE TABLE WEBSITEUSER (
  id INT NOT NULL IDENTITY ,
  lojackUserId VARCHAR(50) NULL ,
  homeUserId VARCHAR(100) NULL ,
  petUserId VARCHAR(100) NULL ,
  preventUserId VARCHAR(100) NULL ,
  id_avatar INT NULL,
  ext_avatar VARCHAR(10) NULL ,
  PRIMARY KEY (id),
  UNIQUE (lojackUserId ASC));
  
CREATE INDEX IX_WEBSITEUSER_01 ON WEBSITEUSER (homeUserId);
CREATE INDEX IX_WEBSITEUSER_02 ON WEBSITEUSER (petUserId);
CREATE INDEX IX_WEBSITEUSER_03 ON WEBSITEUSER (preventUserId);
  
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

CREATE TABLE SYSTEMUSER (
  id INT NOT NULL IDENTITY ,
  username VARCHAR(20) NULL ,
  password VARCHAR(50) NULL ,
  loggingAccess INT NULL,
  syspropAccess INT NULL,
  deleted INT NOT NULL ,
  PRIMARY KEY (id));
  
CREATE INDEX IX_SYSTEMUSER_00 ON SYSTEMUSER (username);
INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('proxy.http','localhost:9090','proxy.http',1);
INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('proxy.https','localhost:9091','proxy.https',1);

INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('front.server','http://localhost:8180/LoJackWeb','front.server',0);
INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('thalamus.server','http://localhost:8280/ThalamusWeb/','thalamus.server',0);
INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('thalamus.touchpoint.code','test','thalamus.touchpoint.code',0);
INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('thalamus.touchpoint.token','testtesttesttesttesttesttesttesttesttesttesttesttesttesttesttest','thalamus.touchpoint.token',0);
INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('thalamus.timeout','5000','Timeout en millis',0);
INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('thalamus.proxy','true','thalamus.proxy',0);

INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('guid','2b64c399-69aa-4b8f-bd79-d5e8bf6075ee','gui',0);

INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('mw.protocol','json','mw.protocol',0);
INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('gis.server','http://localhost:8180/GISWeb/gis/','gis.server',0);
INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('services.server','http://localhost:8180/GISWeb/services/','services.server',0);
INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('services.timeout','5000','Timeout en millis',0);
INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('middleware.proxy','true','middleware.proxy',0);

INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('mw.showAgenda','false','Mostrar agendas [true,false]',0);
INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('mw.showEmailNotification','false','Mostrar email notification [true,false]',0);

INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('prevent.server','http://www.lojackgis.com.ar/PreventWCFServices/GISService.svc','prevent.server',0);
INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('prevent.timeout','10000','Timeout en millis',0);
INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('prevent.loginurl','http://test.lojackgis.com.ar:8080/webgis/Prevent_WebUI/loginportal.ashx?','URL de prevent',0);
INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('prevent.token','a5b0981a0188bb9a5b7fe44b6c32d894','Token de prevent',0);
INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('prevent.proxy','true','prevent.proxy',0);

INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('pets.loginurl','http://test.lojackgis.com.ar:8080/webgis/LojackPets/loginportal.ashx?','URL de pets',0);
INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('pets.mobile.loginurl','http://test.lojackgis.com.ar:8080/webgis/lojackpetsmobile/loginportal.ashx?','URL de pets mobile',0);
INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('pets.token','a5b0981a0188bb9a5b7fe44b6c32d894','Token de pets',0);

INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('prop.tmp.path','/home/mgodoy/icarus/apache-tomcat-6.0.32/temp','prop.tmp.path',0);
INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('camera.mobile.refreshTime','1000','camera.mobile.refreshTime',0);
INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('camera.applet.refreshTime','1000','camera.applet.refreshTime',0);
INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('camera.proxy','false','camera.proxy',0);
INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('camera.connectTimeOut','2000','camera.connectTimeOut',0);
INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('camera.readTimeOut','2000','camera.readTimeOut',0);

INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('camera.mobile.mode','local','camera.mobile.mode [local,proxy,external]',0);
INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('camera.mobile.external.url','http://23.23.84.70/LoJackWeb/','camera.mobile.external.url',0);

INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('job.refresh.time','1000','job.refresh.time',0);
INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('job.abort.time','60000','job.abort.time',0);
INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('job.client.refresh.time','1000','job.client.refresh.time',0);
INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('front.login.deplay','100','front.login.deplay',0);

INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('mail.smtp.user','test.lojack.front@gmail.com','mail.smtp.user',0);
INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('mail.smtp.host','smtp.gmail.com','mail.smtp.host',0);
INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('mail.smtp.port','465','mail.smtp.port',0);
INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('mail.smtp.starttls.enable','true','mail.smtp.starttls.enable',0);
INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('mail.smtp.auth','true','mail.smtp.auth',0);
INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('mail.smtp.socketFactory.port','465','mail.smtp.socketFactory.port',0);
INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('mail.smtp.socketFactory.class','javax.net.ssl.SSLSocketFactory','mail.smtp.socketFactory.class',0);
INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('mail.smtp.socketFactory.fallback','false','mail.smtp.socketFactory.fallback',0);
INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('mail.smtp.password','t3stl0j4k','mail.smtp.password',0);

INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('mail.proxy','localhost:9090','mail.proxy',0);

INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('contactform.email','subcmd@gmail.com','Email del formulario de contacto',0);

INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('video.car','http://www.youtube.com/embed/5Xe5pODPq1I','video.car',0);
INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('video.home','http://www.youtube.com/embed/Iz_VvsFwXQI','video.home',0);
INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('video.pets','http://www.youtube.com/embed/M8VhrMM0j-Q','video.pets',0);
INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('video.loapp','http://www.youtube.com/embed/G18ElDg9s-o','video.loapp',0);

INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('video.mobile.car','http://youtu.be/5Xe5pODPq1I','video.mobile.car',0);
INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('video.mobile.home','http://youtu.be/Iz_VvsFwXQI','video.mobile.home',0);
INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('video.mobile.pets','http://youtu.be/M8VhrMM0j-Q','video.mobile.pets',0);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Leandro N. Alem 755 - Agronomía',-34.5891975,-58.4829834);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida San Martín 4785 - Agronomía',-34.5975628,-58.4857077);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Av. García del Río 3152 - Agronomía',-34.5501424,-58.4766122);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento Público','Avenida de los Constituyentes 4080 - Agronomía',-34.5849850,-58.4886606);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Billinghurst 340 - Almagro',-34.6064537,-58.4161931);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Billinghurst 1195 - Almagro',-34.5956541,-58.4138780);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Belgrano 3545 - Almagro',-34.6156566,-58.4163417);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Boedo 149 - Almagro',-34.6131609,-58.4175299);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Boedo 250 - Almagro',-34.6157185,-58.4170306);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Boedo 560 - Almagro',-34.6192841,-58.4166611);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Boedo 645 - Almagro',-34.6203554,-58.4165736);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Córdoba 3471 - Almagro',-34.5978693,-58.4155955);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Córdoba 3471 - Almagro',-34.5978693,-58.4155955);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Córdoba 3266 - Almagro',-34.5980027,-58.4127826);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Corrientes 3844 - Almagro',-34.6034002,-58.4186555);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Corrientes 1781 - Almagro',-34.6029415,-58.4233246);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Corrientes 3571 - Almagro',-34.6038980,-58.4153310);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Diaz Vélez 4150 - Almagro',-34.6086482,-58.4254389);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Diaz Vélez 3750 - Almagro',-34.6085404,-58.4194114);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Rivadavia 3964 - Almagro',-34.6118390,-58.4218910);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Rivadavia 4035 - Almagro',-34.6122300,-58.4227332);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Rivadavia 4045 - Almagro',-34.6122935,-58.4228658);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Billinghurst 1051 - Almagro',-34.5973168,-58.4144145);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Castro Barros 65 - Almagro',-34.6125737,-58.4207659);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Castro Barros 470 - Almagro',-34.6172954,-58.4197308);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Colombres 755  - Almagro',-34.6219286,-58.4180011);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Colombres 476 - Almagro',-34.6183674,-58.4182390);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Gallo 479 - Almagro',-34.6034350,-58.4130167);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Guardia Vieja 3855 - Almagro',-34.6008918,-58.4185527);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Guardia Vieja 4187 - Almagro',-34.6003966,-58.4235281);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Mario Bravo 967 - Almagro',-34.5983390,-58.4160254);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Mario Bravo 37 - Almagro',-34.6104468,-58.4173409);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Mario Bravo 230 - Almagro',-34.6080632,-58.4174504);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Mario Bravo 277 - Almagro',-34.6072687,-58.4173276);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Mario Bravo 461 - Almagro',-34.6044625,-58.4168631);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Maza 82 - Almagro',-34.6119770,-58.4168896);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Maza 68 - Almagro',-34.6117975,-58.4169665);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Medrano 747 - Almagro',-34.6013359,-58.4207047);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Medrano 638 - Almagro',-34.6027017,-58.4209060);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Medrano 556 - Almagro',-34.6039457,-58.4210557);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Garage Privado Sin Datos','México 3679 - Almagro',-34.6199214,-58.4178383);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','México 4350 - Almagro',-34.6206418,-58.4274667);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','México 3952 - Almagro',-34.6201362,-58.4215603);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','México 3538 - Almagro',-34.6197545,-58.4157363);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Jerónimo Salguero 168 - Almagro',-34.6089249,-58.4200048);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','San Luis 3350 - Almagro',-34.5992357,-58.4128257);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Sarmiento 3738 - Almagro',-34.6054083,-58.4175275);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Sarmiento 3578 - Almagro',-34.6057004,-58.4157825);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Tucumán 3650 - Almagro',-34.5990993,-58.4167699);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Tucumán 3626 - Almagro',-34.5992089,-58.4164318);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento','Rauch 3951 - Almagro',-34.6000802,-58.4198180);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Gascón 1150 - Almagro',-34.5970312,-58.4232458);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Gascón 1173 - Almagro',-34.5966990,-58.4232006);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Guardia Vieja 4340 - Almagro',-34.6001809,-58.4258511);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Guardia Vieja 4468 - Almagro',-34.5999962,-58.4277317);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Yatay 752 - Almagro',-34.6032488,-58.4286886);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Yatay 759 - Almagro',-34.6031138,-58.4286680);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Aguero 720 - Balvanera',-34.6017683,-58.4111620);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Aguero 550 - Balvanera',-34.6034971,-58.4115678);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Aguero 718 - Balvanera',-34.6017989,-58.4111694);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Alberti 639 - Balvanera',-34.6169860,-58.4011362);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Alberti 434 - Balvanera',-34.6147830,-58.4013475);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Alberti 240 - Balvanera',-34.6126305,-58.4015389);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Anchorena 886 - Balvanera',-34.5982662,-58.4082959);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Callao 662 - Balvanera',-34.6012683,-58.3927035);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Córdoba 2645 - Balvanera',-34.5980388,-58.4045025);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Corrientes 1781 - Balvanera',-34.6044594,-58.3920171);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Corrientes 2474 - Balvanera',-34.6047042,-58.4017879);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Corrientes 3187 - Balvanera',-34.6040353,-58.4101575);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Corrientes 3571 - Balvanera',-34.6038980,-58.4153310);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Corrientes 3346 - Balvanera',-34.6041315,-58.4123823);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Corrientes 3372 - Balvanera',-34.6041541,-58.4127648);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Corrientes 2856 - Balvanera',-34.6045185,-58.4061219);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Corrientes 2150 - Balvanera',-34.6045580,-58.3974945);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Corrientes 1919 - Balvanera',-34.6044890,-58.3941310);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Corrientes 1854 - Balvanera',-34.6044654,-58.3930502);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Pueyrredón 653 - Balvanera',-34.6025477,-58.4049761);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Azcuénaga 864 - Balvanera',-34.5985412,-58.4008303);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Azcuénaga 710 - Balvanera',-34.6009941,-58.4009715);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Apart Car','Azcuénaga 376 - Balvanera',-34.6050440,-58.4008173);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Combate de los Pozos 634 - Balvanera',-34.6161905,-58.3931847);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Combate de los Pozos 847 - Balvanera',-34.6188388,-58.3932162);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Garage Privado Sin Datos','Combate de los Pozos 655 - Balvanera',-34.6164439,-58.3931931);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Gallo 479 - Balvanera',-34.6034350,-58.4130167);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Hipólito Yrigoyen 2375 - Balvanera',-34.6108897,-58.4000703);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Hipólito Yrigoyen 2121 - Balvanera',-34.6106149,-58.3965025);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Hipólito Yrigoyen 1941 - Balvanera',-34.6104581,-58.3939623);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Jean Jaures 874 - Balvanera',-34.6000688,-58.4073727);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Tte. Gral. Juan Domingo Perón 2367 - Balvanera',-34.6073727,-58.4001828);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Tte. Gral. Juan Domingo Perón 2251 - Balvanera',-34.6072647,-58.3985715);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Tte. Gral. Juan Domingo Perón 2029 - Balvanera',-34.6070759,-58.3954481);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Tte. Gral. Juan Domingo Perón 1840 - Balvanera',-34.6068240,-58.3926670);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Tte. Gral. Juan Domingo Perón 1835 - Balvanera',-34.6068161,-58.3926100);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Larrea 239 - Balvanera',-34.6070013,-58.4020017);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Larrea 553 - Balvanera',-34.6027130,-58.4022777);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Lavalle 3187 - Balvanera',-34.6021269,-58.4110455);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','México 2407 - Balvanera',-34.6164475,-58.4002782);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','México 2152 - Balvanera',-34.6160679,-58.3969607);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Viamonte 2565 - Balvanera',-34.6009973,-58.4030637);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Viamonte 2071 - Balvanera',-34.6009742,-58.3967033);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Pasco 57 - Balvanera',-34.6102106,-58.3975407);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Abasto Shopping Estacionamiento','Anchorena 551 - Balvanera',-34.6032431,-58.4100360);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Tte. Gral. Juan Domingo Perón 2408 - Balvanera',-34.6074049,-58.4007421);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Azcuénaga 265 - Balvanera',-34.6065781,-58.4006650);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Matheu 191 - Balvanera',-34.6120235,-58.4003452);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Matheu 280 - Balvanera',-34.6129949,-58.4002500);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Pichincha 545 - Balvanera',-34.6156900,-58.3992007);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Garage Privado Sin Datos','Pichincha 741 - Balvanera',-34.6178810,-58.3990268);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Belgrano 2375 - Balvanera',-34.6143391,-58.3998072);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Independencia 2410 - Balvanera',-34.6186038,-58.4001026);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Azcuénaga 751 - Balvanera',-34.6003785,-58.4010145);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Apart Car','Lavalle 2454 - Balvanera',-34.6034296,-58.4016137);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Tte. Gral. Juan Domingo Perón 2124 - Balvanera',-34.6071531,-58.3967936);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Garage Azul','Sarmiento 1854  - Balvanera',-34.6057168,-58.3929748);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'PARKING BAIRES ','BARTOLOME MITRE 2174 - Balvanera',-34.6083618,-58.3973615);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento Público','Avenida Córdoba 1856 - Balvanera',-34.5996694,-58.3937531);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento Público','Avenida Córdoba 1856 - Balvanera',-34.5996694,-58.3937531);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Santa María del Buen Aire 456 - Barracas',-34.6484655,-58.3830925);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','11 de septiembre - Belgrano',-34.5597242,-58.4503626);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','3 de Febrero 2231 - Belgrano',-34.5580506,-58.4531700);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Garage Privado Sin Datos','Amenabar 2150 - Belgrano',-34.5626642,-58.4589675);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Amenabar 1959 - Belgrano',-34.5644793,-58.4574035);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Garage Privado Sin Datos','Arribeños 1530 - Belgrano',-34.5636005,-58.4456488);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Cabildo 1513 - Belgrano',-34.5667821,-58.4510084);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Cabildo 2563 - Belgrano',-34.5578059,-58.4604151);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Cabildo 2675 - Belgrano',-34.5569572,-58.4611936);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Libertador 5032 - Belgrano',-34.5633204,-58.4371143);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Ciudad de la Paz 1770 - Belgrano',-34.5657112,-58.4550145);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Ciudad de la Paz 2076 - Belgrano',-34.5628448,-58.4574908);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Garage Privado Sin Datos','Echeverría 3055 - Belgrano',-34.5666475,-58.4620862);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Echeverría 2951 - Belgrano',-34.5660076,-58.4610002);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Freire 2255 - Belgrano',-34.5651309,-58.4656461);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Freire 2232 - Belgrano',-34.5653552,-58.4654637);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Jorge Newbery 2480 - Belgrano',-34.5728816,-58.4414812);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Mendoza 2048 - Belgrano',-34.5591497,-58.4542105);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Moldes 1744 - Belgrano',-34.5669642,-58.4566325);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Garage Privado Sin Datos','O Higgins 1333 - Belgrano',-34.5672544,-58.4465926);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Olazabal 2266 - Belgrano',-34.5594096,-58.4569945);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Olazabal 2650 - Belgrano',-34.5615362,-58.4605964);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Santos Dumont 2657 - Belgrano',-34.5754785,-58.4405997);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Santos Dumont 2479 - Belgrano',-34.5742603,-58.4393818);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Sarmiento 1215 - Belgrano',-34.5606607,-58.4541105);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Soldado de la Independencia 850 - Belgrano',-34.5670159,-58.4353894);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Garage Privado Sin Datos','Virrey del Pino 2297 - Belgrano',-34.5656698,-58.4512316);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida del Libertador 6540 - Belgrano',-34.5518301,-58.4518790);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Montañeses 2380 - Belgrano',-34.5547918,-58.4511558);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Monroe 1550 - Belgrano',-34.5533088,-58.4515146);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Av. Monroe 3430 - Belgrano',-34.5644731,-58.4698299);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Apart Car','Arribeños 2511 - Belgrano',-34.5542415,-58.4533803);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Melian 2437 - Belgrano',-34.5658188,-58.4710409);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Boedo 1159 - Boedo',-34.6275048,-58.4159543);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Boedo 1159 - Boedo',-34.6275048,-58.4159543);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Directorio 64 - Boedo',-34.6271410,-58.4276377);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida San Juan 3649 - Boedo',-34.6255706,-58.4168696);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida San Juan 3649 - Boedo',-34.6255706,-58.4168696);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida San Juan 3848 - Boedo',-34.6259417,-58.4194979);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Castro Barros 936 - Boedo',-34.6233963,-58.4192221);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Colombres 755  - Boedo',-34.6219286,-58.4180011);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Colombres 755  - Boedo',-34.6219286,-58.4180011);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Colombres 476 - Boedo',-34.6183674,-58.4182390);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Maza 1350 - Boedo',-34.6273107,-58.4145894);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Acoyte 566 - Caballito',-34.6121419,-58.4386930);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Directorio 64 - Caballito',-34.6271410,-58.4276377);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Directorio 439 - Caballito',-34.6278736,-58.4329152);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Rivadavia 5222 - Caballito',-34.6195489,-58.4392775);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Rivadavia 5458 - Caballito',-34.6210961,-58.4420787);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Formosa 545 - Caballito',-34.6213642,-58.4359203);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Formosa 249 - Caballito',-34.6209850,-58.4318375);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','México 4350 - Caballito',-34.6206418,-58.4274667);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Garage Privado Sin Datos','Dr. Nicolas Repetto 55 - Caballito',-34.6205624,-58.4430766);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Córdoba 5640 - Chacarita ',-34.5872686,-58.4406402);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Dorrego 1639 - Chacarita ',-34.5825678,-58.4439957);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Dorrego 1560 - Chacarita ',-34.5837541,-58.4445624);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Ricardo Balbín 3250 - Coghland',-34.5580620,-58.4762987);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Rómulo Naón 2756 - Coghland',-34.5641764,-58.4755488);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Av. Monroe 3430 - Coghland',-34.5644731,-58.4698299);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Av. Congreso 3130 - Coghland',-34.5590636,-58.4694726);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','José Pascual Tamborini 3224 - Coghland',-34.5569425,-58.4730869);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Amenabar 1461 - Colegiales',-34.5685937,-58.4529119);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Dorrego 1639 - Colegiales',-34.5825678,-58.4439957);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Dorrego 1560 - Colegiales',-34.5837541,-58.4445624);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Conde 239 - Colegiales',-34.5797915,-58.4461275);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Olleros 2646 - Colegiales',-34.5725966,-58.4451208);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Olleros 2787 - Colegiales',-34.5737509,-58.4458578);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Palpa 2474 - Colegiales',-34.5702319,-58.4473093);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Zabala 2556 - Colegiales',-34.5696147,-58.4503812);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Gral. Enrique Martínez 876 - Colegiales',-34.5773370,-58.4533105);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Palpa 314 - Colegiales',-34.5744591,-58.4501389);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Giribone 1133 - Colegiales',-34.5797864,-58.4580405);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento Público','Avenida Elcano 3347 - Colegiales',-34.5746075,-58.4598520);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento Público','Avenida Elcano 2958 - Colegiales',-34.5698497,-58.4577495);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Entre Ríos 1300 - Constitución',-34.6240050,-58.3914213);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Entre Ríos 1170 - Constitución',-34.6223844,-58.3915124);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Entre Ríos 1100 - Constitución',-34.6215419,-58.3915633);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida San Juan 1701 - Constitución',-34.6226621,-58.3898931);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Estados Unidos 1767 - Constitución',-34.6190639,-58.3912135);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Estados Unidos 1685 - Constitución',-34.6189660,-58.3900681);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Estados Unidos 1307 - Constitución',-34.6186108,-58.3843847);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Estados Unidos 1557 - Constitución',-34.6188434,-58.3882862);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Humberto Primo 1836 - Constitución',-34.6215748,-58.3921041);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Garage Privado Sin Datos','Pres. Luis Sáenz Pena 947 - Constitución',-34.6193234,-58.3874018);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Garage Privado Sin Datos','Pres. Luis Sáenz Pena 752 - Constitución',-34.6173079,-58.3874771);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Santiago del Estero 971 - Constitución',-34.6194571,-58.3843202);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Santiago del Estero 971 - Constitución',-34.6194571,-58.3843202);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Solis 1271 - Constitución',-34.6238149,-58.3897916);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Park@Go','Bdo. de Irigoyen 1545 - Constitución',-34.6267299,-58.3787897);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Lafuente 50 - Flores',-34.6310531,-58.4679979);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Gaona 4651 - Floresta',-34.6269575,-58.4883684);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Concordia 53 - Floresta',-34.6316009,-58.4760010);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Olavarría 576 - La Boca',-34.6371918,-58.3609462);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Rivadavia 10331 - Liniers',-34.6389422,-58.5091266);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Montiel 2173 - Mataderos',-34.6611580,-58.5129585);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Bolivar 427 - Monserrat',-34.6128699,-58.3733118);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Bolivar 453 - Monserrat',-34.6131754,-58.3732941);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Bolivar 120 - Monserrat',-34.6092978,-58.3735005);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Bolivar 262 - Monserrat',-34.6109919,-58.3734093);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Bolivar 317 - Monserrat',-34.6115870,-58.3733722);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Paseo Colón 403 - Monserrat',-34.6123540,-58.3694732);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Defensa 355 - Monserrat',-34.6119537,-58.3719532);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Defensa 260 - Monserrat',-34.6108410,-58.3720165);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Hipólito Yrigoyen 1941 - Monserrat',-34.6104581,-58.3939623);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Hipólito Yrigoyen 1542 - Monserrat',-34.6100494,-58.3881604);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Hipólito Yrigoyen 1451 - Monserrat',-34.6100104,-58.3869001);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Hipólito Yrigoyen 981 - Monserrat',-34.6095905,-58.3803685);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Hipólito Yrigoyen 860 - Monserrat',-34.6094672,-58.3785909);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Hipólito Yrigoyen 835 - Monserrat',-34.6094346,-58.3782616);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Hipólito Yrigoyen 762 - Monserrat',-34.6093656,-58.3772125);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Hipólito Yrigoyen 739 - Monserrat',-34.6093512,-58.3768919);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Hipólito Yrigoyen 639 - Monserrat',-34.6092425,-58.3754609);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Hipólito Yrigoyen 876 - Monserrat',-34.6094879,-58.3788106);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','México 457 - Monserrat',-34.6148632,-58.3725919);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','México 257 - Monserrat',-34.6147877,-58.3701645);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Moreno 305 - Monserrat',-34.6112587,-58.3706527);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Moreno 476 - Monserrat',-34.6113738,-58.3730316);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Pres. Luis Sáenz Pena 625 - Monserrat',-34.6158143,-58.3875034);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Garage Privado Sin Datos','Pres. Luis Sáenz Pena 529 - Monserrat',-34.6149181,-58.3874996);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Pres. Luis Sáenz Pena 283 - Monserrat',-34.6121716,-58.3874767);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Garage Privado Sin Datos','Pres. Luis Sáenz Pena 236 - Monserrat',-34.6115911,-58.3874578);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Virrey Cevallos 236 - Monserrat',-34.6117065,-58.3889263);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Virrey Cevallos 153 - Monserrat',-34.6107285,-58.3889914);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Apart Car','Avenida Belgrano 1216 - Monserrat',-34.6132388,-58.3834136);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Alfa','Avenida Paseo Colón 465 - Monserrat',-34.6131369,-58.3694661);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Alvarez Jonte 4240 - Monte castro',-34.6164527,-58.4994729);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Ventana 3870 - Nueva Pompeya',-34.6507288,-58.4157766);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Arcos 3134 - Nuñez',-34.5510805,-58.4625121);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Nuñez 2769 - Nuñez',-34.5519683,-58.4707559);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida del Libertador 6540 - Nuñez',-34.5518301,-58.4518790);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','3 de Febrero 3276 - Nuñez',-34.5485873,-58.4605286);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Crisólogo Larralde 2847 - Nuñez',-34.5512239,-58.4722524);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Crisólogo Larralde 3353 - Nuñez',-34.5535701,-58.4765362);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Crisólogo Larralde 3689 - Nuñez',-34.5552558,-58.4795594);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','José Antonio Cabrera 4354 - Palermo',-34.5945312,-58.4258103);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','José Antonio Cabrera 5555 - Palermo',-34.5860443,-58.4376734);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Cabello 3750 - Palermo',-34.5801974,-58.4124402);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Bulnes 2562 - Palermo',-34.5824657,-58.4068569);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Bonpland 2461 - Palermo',-34.5773202,-58.4304492);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Bonpland 1744 - Palermo',-34.5830030,-58.4369390);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Bonpland 1943 - Palermo',-34.5814456,-58.4351870);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Billinghurst 1195 - Palermo',-34.5956541,-58.4138780);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','3 de Febrero 835 - Palermo',-34.5703759,-58.4411668);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Acuña de Figueroa 1257 - Palermo',-34.5969864,-58.4216351);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Araoz 2750 - Palermo',-34.5843596,-58.4130113);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Araoz 2346 - Palermo',-34.5871737,-58.4173172);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Garage Privado Sin Datos','Araoz 2355 - Palermo',-34.5870931,-58.4171746);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Arenales 3319 - Palermo',-34.5880763,-58.4097880);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Garage Privado Sin Datos','Arenales 3359 - Palermo',-34.5876594,-58.4103821);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Garage Privado Sin Datos','Arenales 3493 - Palermo',-34.5863595,-58.4121978);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Arévalo 1960 - Palermo',-34.5789373,-58.4381058);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Garage Privado Sin Datos','Arévalo 2251 - Palermo',-34.5766780,-58.4355255);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Armenia 1263 - Palermo',-34.5924016,-58.4317757);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Cabildo 76 - Palermo',-34.5712891,-58.4422340);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Córdoba 5640 - Palermo',-34.5872686,-58.4406402);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Córdoba 4953 - Palermo',-34.5917274,-58.4339334);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Dorrego 1639 - Palermo',-34.5825678,-58.4439957);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Dorrego 1560 - Palermo',-34.5837541,-58.4445624);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Libertador 5032 - Palermo',-34.5633204,-58.4371143);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Libertador 3864 - Palermo',-34.5713993,-58.4226812);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Santa Fe 4748 - Palermo',-34.5780519,-58.4269295);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Santa Fe 5091 - Palermo',-34.5766852,-58.4314383);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Santa Fe 3952 - Palermo',-34.5829346,-58.4191641);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Santa Fe 3840 - Palermo',-34.5840682,-58.4175456);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Scalabrini Ortiz 1512 - Palermo',-34.5914528,-58.4257966);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Garage Privado Sin Datos','Avenida Scalabrini Ortiz 2370 - Palermo',-34.5863604,-58.4178412);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Scalabrini Ortiz 2369 - Palermo',-34.5863617,-58.4178400);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Scalabrini Ortiz 2060 - Palermo',-34.5881212,-58.4207066);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Apart Car','Baez 351 - Palermo',-34.5717325,-58.4317001);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Beruti 3387 - Palermo',-34.5866653,-58.4103910);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Billinghurst 2150 - Palermo',-34.5867950,-58.4070345);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Billinghurst 2045 - Palermo',-34.5877399,-58.4079006);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Billinghurst 2340 - Palermo',-34.5851731,-58.4055556);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Billinghurst 1051 - Palermo',-34.5973168,-58.4144145);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Charcas 3302 - Palermo',-34.5916668,-58.4123476);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Garage Privado Sin Datos','Charcas 4511 - Palermo',-34.5823386,-58.4249080);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Charcas 4195 - Palermo',-34.5845680,-58.4219365);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida General Indalesio Chenaut 1856 - Palermo',-34.5710033,-58.4319961);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Costa Rica 5751 - Palermo',-34.5814734,-58.4362046);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Costa Rica 5530 - Palermo',-34.5831935,-58.4339830);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Costa Rica 4862 - Palermo',-34.5870086,-58.4287611);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','El Lazo 3154  - Palermo',-34.5808131,-58.4096011);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','El Salvador 5748 - Palermo',-34.5821288,-58.4368674);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','El Salvador 5565 - Palermo',-34.5836157,-58.4350913);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Federico Lacroze 1733 - Palermo',-34.5639715,-58.4390819);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Fitz Roy 2349 - Palermo',-34.5790357,-58.4304407);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Garage Privado Sin Datos','Godoy Cruz 2628 - Palermo',-34.5794301,-58.4259271);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Gorostiaga 1650 - Palermo',-34.5656161,-58.4366527);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Gorostiaga 1647 - Palermo',-34.5655979,-58.4366399);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Gorriti 3646 - Palermo',-34.5955704,-58.4158135);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Güemes 4331 - Palermo',-34.5831663,-58.4214519);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Güemes 4232 - Palermo',-34.5840493,-58.4202340);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Güemes 4150 - Palermo',-34.5848791,-58.4190933);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Gurruchaga 2435 - Palermo',-34.5829674,-58.4208216);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Gurruchaga 1340 - Palermo',-34.5911392,-58.4320336);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Honduras 3845 - Palermo',-34.5941022,-58.4172526);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Honduras 5490 - Palermo',-34.5849697,-58.4352271);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Humboldt 2356 - Palermo',-34.5796650,-58.4294498);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Humboldt 1645 - Palermo',-34.5852990,-58.4358496);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Humboldt 2493 - Palermo',-34.5780032,-58.4275161);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Humboldt 2436 - Palermo',-34.5787897,-58.4284336);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Jorge Newbery 2480 - Palermo',-34.5728816,-58.4414812);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Jorge Newbery 2480 - Palermo',-34.5728816,-58.4414812);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Juan B. Justo 881 - Palermo',-34.5807678,-58.4288477);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Julián Alvarez 2252 - Palermo',-34.5884268,-58.4174690);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Julián Alvarez 2744 - Palermo',-34.5850077,-58.4121779);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Julián Alvarez 2467 - Palermo',-34.5867582,-58.4146834);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Julián Alvarez 2755 - Palermo',-34.5849347,-58.4120827);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Julián Alvarez 1810 - Palermo',-34.5907809,-58.4212372);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Julián Alvarez 1250 - Palermo',-34.5946326,-58.4268100);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Luis María Campos 350 - Palermo',-34.5732128,-58.4329301);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Malabia 1741 - Palermo',-34.5898838,-58.4256521);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Malabia 1316 - Palermo',-34.5928359,-58.4301286);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Mansilla 3328 - Palermo',-34.5927207,-58.4123193);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Mason 4448 - Palermo',-34.5946259,-58.4277475);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Medrano 1048 - Palermo',-34.5971751,-58.4200621);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Medrano 1457 - Palermo',-34.5921131,-58.4192256);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Medrano 1741 - Palermo',-34.5898017,-58.4182477);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Migueletes 1133 - Palermo',-34.5641636,-58.4379899);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Garage Privado Sin Datos','Migueletes 756 - Palermo',-34.5667371,-58.4335947);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Migueletes 534 - Palermo',-34.5680220,-58.4313960);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Jorge Newbery 1682 - Palermo',-34.5671561,-58.4346954);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Cnel. Niceto Vega 5122 - Palermo',-34.5900835,-58.4345428);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Garage Privado Sin Datos','O Higgins 1333 - Palermo',-34.5672544,-58.4465926);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Olleros 2646 - Palermo',-34.5725966,-58.4451208);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Olleros 2787 - Palermo',-34.5737509,-58.4458578);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Palpa 2474 - Palermo',-34.5702319,-58.4473093);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Paraguay 4258 - Palermo',-34.5865006,-58.4218523);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Paraguay 3760 - Palermo',-34.5906658,-58.4165549);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Garage Privado Sin Datos','Jerónimo Salguero 2109 - Palermo',-34.5862484,-58.4122377);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Jerónimo Salguero 1920 - Palermo',-34.5879268,-58.4142844);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Jerónimo Salguero 1836 - Palermo',-34.5888063,-58.4153257);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','San Mateo 3751 - Palermo',-34.5874171,-58.4146658);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Thames 2160 - Palermo',-34.5843066,-58.4256341);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Uriarte 1635 - Palermo',-34.5872370,-58.4317430);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Uriarte 1358 - Palermo',-34.5890685,-58.4345252);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Villanueva 1350 - Palermo',-34.5650437,-58.4429620);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Prueba 1','Araoz 1001 - Palermo',-34.5957369,-58.4305164);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Gascón 1150 - Palermo',-34.5970312,-58.4232458);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Gascón 1173 - Palermo',-34.5966990,-58.4232006);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Paseo Alcorta','Jerónimo Salguero 3172 - Palermo',-34.5756142,-58.4040119);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Apart Car','Lafinur 3250 - Palermo',-34.5782173,-58.4129290);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Apart Car','Avenida Sarmiento 2720 - Palermo',-34.5799570,-58.4197691);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento','Billinghurst 2122 - Palermo',-34.5870483,-58.4072721);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Martinez Castro 962 - Parque Avellaneda',-34.6431823,-58.4730381);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Directorio 64 - Parque Chacabuco',-34.6271410,-58.4276377);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Directorio 439 - Parque Chacabuco',-34.6278736,-58.4329152);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Miro 1428 - Parque Chacabuco',-34.6373877,-58.4438346);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Bauness 1048 - Parque Chas',-34.5850308,-58.4791583);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento Público','Avenida de los Constituyentes 4080 - Parque Chas',-34.5850308,-58.4791583);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Alberti 1754 - Parque Patricios',-34.6304816,-58.3993538);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Punta Arenas 1250 - Paternal',-34.5909118,-58.4694734);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Bollini 2139 - Recoleta',-34.5873974,-58.4041919);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Billinghurst 1195 - Recoleta',-34.5956541,-58.4138780);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Sánchez de Bustamante 2041 - Recoleta',-34.5885008,-58.4070438);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Sánchez de Bustamante 2629 - Recoleta',-34.5830326,-58.4018807);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Billinghurst 1051 - Recoleta',-34.5973168,-58.4144145);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Anchorena 1168 - Recoleta',-34.5957974,-58.4069089);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Anchorena 1239 - Recoleta',-34.5949240,-58.4064692);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Arenales 1846 - Recoleta',-34.5947797,-58.3937765);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Arenales 1934 - Recoleta',-34.5946746,-58.3949413);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Arenales 2346 - Recoleta',-34.5939847,-58.4000281);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Austria 2059 - Recoleta',-34.5883798,-58.4045241);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Garage Privado Sin Datos','Austria 2064 - Recoleta',-34.5883439,-58.4044775);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Garage Privado Sin Datos','Austria 2070 - Recoleta',-34.5882919,-58.4044057);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Austria 2527 - Recoleta',-34.5848463,-58.3997058);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Callao 1662 - Recoleta',-34.5903438,-58.3903404);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Callao 1833 - Recoleta',-34.5888871,-58.3886847);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Callao 1234 - Recoleta',-34.5945010,-58.3931505);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Callao 1859 - Recoleta',-34.5886950,-58.3884579);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Córdoba 2645 - Recoleta',-34.5980388,-58.4045025);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Córdoba 3471 - Recoleta',-34.5978693,-58.4155955);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Córdoba 986  - Recoleta',-34.5990497,-58.3810121);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Córdoba 3471 - Recoleta',-34.5978693,-58.4155955);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Córdoba 3266 - Recoleta',-34.5980027,-58.4127826);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Figueroa Alcorta 2250 - Recoleta',-34.5810300,-58.3973700);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Francisco Romero - Recoleta',-34.5843357,-58.3902851);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Las Heras 2242 - Recoleta',-34.5881425,-58.3963902);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Garage Privado Sin Datos','Avenida Libertador 754 - Recoleta',-34.5879399,-58.3833760);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Pueyrredón 1761 - Recoleta',-34.5907136,-58.4006030);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Pueyrredón 1770 - Recoleta',-34.5906043,-58.4005482);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Santa Fe 2347 - Recoleta',-34.5951746,-58.3997814);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Santa Fe 1947 - Recoleta',-34.5957788,-58.3953393);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Santa Fe 2743 - Recoleta',-34.5930354,-58.4055341);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Garage Privado Sin Datos','Avenida Santa Fe 2813 - Recoleta',-34.5925000,-58.4062363);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Santa Fe 3047 - Recoleta',-34.5904859,-58.4083843);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Santa Fe 3069 - Recoleta',-34.5902964,-58.4085994);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Azcuénaga 1866 - Recoleta',-34.5881268,-58.3954930);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Apart Car','Azcuénaga 1048 - Recoleta',-34.5960206,-58.3999823);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Azcuénaga 864 - Recoleta',-34.5985412,-58.4008303);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Azcuénaga 710 - Recoleta',-34.6009941,-58.4009715);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Azcuénaga 376 - Recoleta',-34.6050440,-58.4008173);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Azcuénaga 1750 - Recoleta',-34.5890325,-58.3962546);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Garage Privado Sin Datos','Azcuénaga 2001 - Recoleta',-34.5868273,-58.3943934);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Beruti 2833 - Recoleta',-34.5908608,-58.4046062);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Beruti 3387 - Recoleta',-34.5866653,-58.4103910);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Billinghurst 2150 - Recoleta',-34.5867950,-58.4070345);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Billinghurst 2045 - Recoleta',-34.5877399,-58.4079006);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Billinghurst 2340 - Recoleta',-34.5851731,-58.4055556);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Charcas 3302 - Recoleta',-34.5916668,-58.4123476);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Charcas 3045 - Recoleta',-34.5929275,-58.4090062);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Charcas 3156 - Recoleta',-34.5926316,-58.4103486);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Gallo 1351 - Recoleta',-34.5945219,-58.4106576);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Gallo 1577 - Recoleta',-34.5919675,-58.4092159);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Gorriti 3646 - Recoleta',-34.5955704,-58.4158135);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Honduras 3845 - Recoleta',-34.5941022,-58.4172526);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Laprida 1761 - Recoleta',-34.5897077,-58.4024338);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Juncal 3128 - Recoleta',-34.5859958,-58.4085346);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Juncal 2658 - Recoleta',-34.5899326,-58.4038768);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Laprida 1344 - Recoleta',-34.5931613,-58.4067483);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Larrea 937 - Recoleta',-34.5971949,-58.4018865);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Mansilla 3328 - Recoleta',-34.5927207,-58.4123193);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','José Andrés Pacheco de Melo 2115 - Recoleta',-34.5901086,-58.3959095);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Peña 2627 - Recoleta',-34.5883113,-58.4011688);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Garage Privado Sin Datos','Peña 2637 - Recoleta',-34.5882483,-58.4012764);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Peña 2841 - Recoleta',-34.5870957,-58.4032586);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Peña 3051 - Recoleta',-34.5859179,-58.4052138);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Pres. José Evaristo Uriburu 1148 - Recoleta',-34.5948748,-58.3983007);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Vicente Lopez 2240 - Recoleta',-34.5874371,-58.3956381);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Ayacucho 1650 - Recoleta',-34.5905115,-58.3924343);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Garage Privado Sin Datos','Laprida 1980 - Recoleta',-34.5878773,-58.4000915);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','José Andrés Pacheco de Melo 2430 - Recoleta',-34.5882019,-58.3990593);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Garage Privado Sin Datos','Vicente Lopez 2268 - Recoleta',-34.5871911,-58.3959565);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Recoleta Mall','Pres. José Evaristo Uriburu 1765 - Recoleta',-34.5888550,-58.3944282);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','José Andrés Pacheco de Melo 2283 - Recoleta',-34.5893779,-58.3967700);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Azcuénaga 1877 - Recoleta',-34.5880489,-58.3954244);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Julio Victor Gonzalez - Recoleta',-34.5836830,-58.3916664);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Córdoba 1689 - Recoleta',-34.5995540,-58.3913500);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Fundación Asistencia Social del Hospital de Clínicas','Azcuénaga 815 - Recoleta',-34.5992594,-58.4010115);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Azcuénaga 961 - Recoleta',-34.5972344,-58.4004290);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Larrea 1344 - Recoleta',-34.5915098,-58.3996325);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento','Billinghurst 2122 - Recoleta',-34.5870483,-58.4072721);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Pres. José Evaristo Uriburu 1266 - Recoleta',-34.5933615,-58.3976819);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','French 2483 - Recoleta',-34.5902955,-58.4001416);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Paraguay 1523 - Recoleta',-34.5983910,-58.3885590);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Paraguay 1439 - Recoleta',-34.5983095,-58.3874138);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Juncal 2021 - Recoleta',-34.5932690,-58.3960222);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'AGUERO-FRENCH','Aguero 2030 - Recoleta',-34.5883844,-58.4027552);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento','Ecuador 1363 - Recoleta',-34.5945019,-58.4038005);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento','Ecuador 1344 - Recoleta',-34.5947261,-58.4039454);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Garage Privado Sin Datos','Avenida Libertador 85 - Retiro',-34.5927253,-58.3753214);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Garage Privado Sin Datos','Avenida Libertador 754 - Retiro',-34.5879399,-58.3833760);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Libertador 502 - Retiro',-34.5892703,-58.3809164);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Apart Car','San Martin 940 - Retiro',-34.5969580,-58.3742805);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Libertad 1163 - Retiro',-34.5948421,-58.3843704);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Posadas 1263 - Retiro',-34.5891054,-58.3844278);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Talcahuano 901 - Retiro',-34.5981516,-58.3855211);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Talcahuano 748 - Retiro',-34.5999040,-58.3854240);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Apart Car','Libertad 1056 - Retiro',-34.5961781,-58.3842212);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Marcelo T. de Alvear 1250 - Retiro',-34.5969414,-58.3848861);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Marcelo T. de Alvear 1276 - Retiro',-34.5969564,-58.3852760);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Jaramillo 2652 - Saavedra',-34.5494032,-58.4712841);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Conesa 4148 - Saavedra',-34.5476315,-58.4762744);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Av. Cabildo 4235 - Saavedra',-34.5450709,-58.4711964);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Arias 3343 - Saavedra',-34.5455830,-58.4824818);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Besares 2674 - Saavedra',-34.5460975,-58.4732430);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Crisólogo Larralde 2847 - Saavedra',-34.5512239,-58.4722524);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Crisólogo Larralde 3353 - Saavedra',-34.5535701,-58.4765362);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Crisólogo Larralde 3689 - Saavedra',-34.5552558,-58.4795594);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Entre Ríos 1300 - San Cristobal',-34.6240050,-58.3914213);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Entre Ríos 1170 - San Cristobal',-34.6223844,-58.3915124);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Entre Ríos 1100 - San Cristobal',-34.6215419,-58.3915633);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida San Juan 1701 - San Cristobal',-34.6226621,-58.3898931);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Catamarca 1164 - San Cristobal',-34.6235486,-58.4041165);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Estados Unidos 1767 - San Cristobal',-34.6190639,-58.3912135);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Humberto Primo 1836 - San Cristobal',-34.6215748,-58.3921041);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Humberto Primo 2065 - San Cristobal',-34.6217438,-58.3952347);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Humberto Primo 2030 - San Cristobal',-34.6217404,-58.3944615);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Humberto Primo 2631 - San Cristobal',-34.6223484,-58.4017244);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Pichincha 945 - San Cristobal',-34.6201788,-58.3987079);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Pichincha 1149 - San Cristobal',-34.6225417,-58.3980130);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Pichincha 1469 - San Cristobal',-34.6265740,-58.3971358);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Pichincha 1550 - San Cristobal',-34.6275097,-58.3969418);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Independencia 2410 - San Cristobal',-34.6186038,-58.4001026);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida San Juan 2350 - San Cristobal',-34.6233259,-58.3985372);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Córdoba 986  - San Nicolás',-34.5990497,-58.3810121);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Corrientes 1237 - San Nicolás',-34.6038515,-58.3842273);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Corrientes 1238 - San Nicolás',-34.6038531,-58.3842272);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Corrientes 1640 - San Nicolás',-34.6042842,-58.3899658);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Corrientes 1919 - San Nicolás',-34.6044890,-58.3941310);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Corrientes 1854 - San Nicolás',-34.6044654,-58.3930502);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Corrientes 1320 - San Nicolás',-34.6039337,-58.3854067);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Corrientes 1241 - San Nicolás',-34.6038553,-58.3842850);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Corrientes 1151 - San Nicolás',-34.6037788,-58.3829648);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Corrientes 867 - San Nicolás',-34.6035430,-58.3789974);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Corrientes 951 - San Nicolás',-34.6036314,-58.3802281);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Leandro N. Alem 395 - San Nicolás',-34.6030408,-58.3700136);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Leandro N. Alem 645 - San Nicolás',-34.6001083,-58.3703623);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Leandro N. Alem 795 - San Nicolás',-34.5984660,-58.3708498);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Carlos Pellegrini 719 - San Nicolás',-34.5999963,-58.3811968);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Tte. Gral. Juan Domingo Perón 1840 - San Nicolás',-34.6068240,-58.3926670);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Tte. Gral. Juan Domingo Perón 1835 - San Nicolás',-34.6068161,-58.3926100);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Tte. Gral. Juan Domingo Perón 1835 - San Nicolás',-34.6068161,-58.3926100);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Lavalle 1479 - San Nicolás',-34.6028892,-58.3876903);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Lavalle 1632 - San Nicolás',-34.6030188,-58.3898820);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Lavalle 1656 - San Nicolás',-34.6030393,-58.3902107);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Lavalle 1641 - San Nicolás',-34.6030345,-58.3901595);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Lavalle 1667 - San Nicolás',-34.6030622,-58.3906047);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Lavalle 1750 - San Nicolás',-34.6031265,-58.3916413);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Lavalle 1760 - San Nicolás',-34.6031355,-58.3917875);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Sarmiento 1148 - San Nicolás',-34.6050536,-58.3828348);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Sarmiento 1229 - San Nicolás',-34.6051237,-58.3840346);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Sarmiento 1245 - San Nicolás',-34.6051378,-58.3842648);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Sarmiento 365 - San Nicolás',-34.6043319,-58.3719188);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Sarmiento 1358 - San Nicolás',-34.6052346,-58.3858818);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Sarmiento 1475 - San Nicolás',-34.6053417,-58.3875435);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Sarmiento 1537 - San Nicolás',-34.6053884,-58.3884377);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Sarmiento 1614 - San Nicolás',-34.6054504,-58.3895539);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Sarmiento 1653 - San Nicolás',-34.6054839,-58.3901201);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Sarmiento 1665 - San Nicolás',-34.6054944,-58.3902900);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Sarmiento 1750 - San Nicolás',-34.6055818,-58.3914635);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Sarmiento 1751 - San Nicolás',-34.6055826,-58.3914914);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Talcahuano 365 - San Nicolás',-34.6043596,-58.3851039);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Tucumán 1743 - San Nicolás',-34.6019609,-58.3918583);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Tucumán 465 - San Nicolás',-34.6009114,-58.3734826);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Viamonte 1768 - San Nicolás',-34.6007868,-58.3923333);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Viamonte 1557 - San Nicolás',-34.6006864,-58.3889547);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Viamonte 1443 - San Nicolás',-34.6006045,-58.3873175);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Montevideo 350 - San Nicolás',-34.6048419,-58.3893796);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Montevideo 369 - San Nicolás',-34.6045985,-58.3893817);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Montevideo 441 - San Nicolás',-34.6037162,-58.3894178);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Montevideo 541 - San Nicolás',-34.6025012,-58.3894911);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Córdoba 1689 - San Nicolás',-34.5995540,-58.3913500);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Playa Tokio','Av. Corrientes 436 - San Nicolás',-34.6032019,-58.3729450);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Apart Car','Avenida Corrientes 680 - San Nicolás',-34.6033904,-58.3763678);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Apart Car','Suipacha 153 - San Nicolás',-34.6065503,-58.3793076);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Apart Car','Esmeralda 425 - San Nicolás',-34.6032123,-58.3780769);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Uruguay 705 - San Nicolás',-34.6005104,-58.3867345);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento Público','Libertad 650 - San Nicolás',-34.6008419,-58.3839529);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento','Tucumán 341 - San Nicolás',-34.6007700,-58.3718038);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento','Tucumán 361 - San Nicolás',-34.6007904,-58.3720641);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento','Lavalle 385 - San Nicolás',-34.6020174,-58.3723319);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Independencia 330 - San Telmo',-34.6171950,-58.3703996);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Independencia 346 - San Telmo',-34.6172093,-58.3706881);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Goya 140 - Velez Sarsfield ',-34.6331944,-58.4861116);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Echenagucia 1240 - Versailles',-34.6037232,-58.3815931);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Acevedo 1089 - Villa Crespo',-34.5932510,-58.4330799);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Araoz 1059 - Villa Crespo',-34.5952214,-58.4297265);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Córdoba 4953 - Villa Crespo',-34.5917274,-58.4339334);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Julián Alvarez 1250 - Villa Crespo',-34.5946326,-58.4268100);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Serrano 546 - Villa Crespo',-34.5959835,-58.4419056);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Thames 1122 - Villa Crespo',-34.5912686,-58.4361246);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Gascón 1150 - Villa Crespo',-34.5970312,-58.4232458);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Gascón 1173 - Villa Crespo',-34.5966990,-58.4232006);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Helguera 3362 - Villa del Parque',-34.5981628,-58.4957748);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Concordia 3355 - Villa del Parque',-34.6012667,-58.5011547);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Francisco Beiro 4701 - Villa Devoto',-34.6089397,-58.5168623);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Manuel Ricardo Trelles 2244 - Villa Gral Mitre',-34.6026083,-58.4670725);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Bolivia 1715 - Villa Gral Mitre',-34.6122731,-58.4753458);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Cnel. Martiniano Chilavert 5641 - Villa Lugano',-34.6808470,-58.4695315);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Lope de Vega 443 - Villa Luro',-34.6334361,-58.5002734);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Gral. Mosconi 2883 - Villa Pueyrredón',-34.5861568,-58.5019799);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Avenida Lope de Vega 2481 - Villa Real',-34.6185213,-58.5191835);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','Camarones 2959 - Villa Santa Rita',-34.6128061,-58.4813837);
INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, 'Estacionamiento sin datos','José Martí 3322 - Villa Soldati',-34.6628478,-58.4401767);
COMMIT;
