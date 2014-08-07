INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('proxy.http','localhost:9092','proxy.http',1);
INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('proxy.https','localhost:9092','proxy.https',1);
INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('proxy.socks','localhost:9090','proxy.socks',0);

INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('front.server','http://localhost:8180/PeugeotServiceWeb','front.server',0);
INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('thalamus.server','http://localhost:8280/ThalamusWeb/','thalamus.server',0);
INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('thalamus.touchpoint.code','PEUGEOT-SMARTPHONE-APP','thalamus.touchpoint.code',0);
INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('thalamus.touchpoint.token','3870s6wox7dizomqkd6ngz5k6fw6bk5zx1yrh2dv7lqpggkey3ibo8q6w0u8uocx','thalamus.touchpoint.token',0);
INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('thalamus.timeout','5000','Timeout en millis',0);
INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('thalamus.proxy','true','thalamus.proxy',0);

INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('guid','2b64c399-69aa-4b8f-bd79-d5e8bf6075ee','gui',0);

INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('prevent.server','http://www.lojackgis.com.ar/PreventWCFServices/GISService.svc','prevent.server',0);
INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('prevent.timeout','10000','Timeout en millis',0);
INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('prevent.loginurl','http://test.lojackgis.com.ar:8080/webgis/Prevent_WebUI/loginportal.ashx?','URL de prevent',0);
INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('prevent.token','a5b0981a0188bb9a5b7fe44b6c32d894','Token de prevent',0);
INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('prevent.proxy','true','prevent.proxy',0);

INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('prop.tmp.path','/home/mgodoy/icarus/apache-tomcat-6.0.32/temp','prop.tmp.path',0);

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

INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('prevent.native.url','http://www.lojackprevent.com.ar/','prevent.native.url',0);

INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('maps.url','http://a.tile.openstreetmap.org/${z}/${x}/${y}.png','Url de los mapas',0);

INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('import.range.km','04:00-05:00','Rango de importacion de kms (HH24:MM-HH24:MM)',0);
INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('email.send.range','10:00-18:00','Rango de envio de notificaciones (HH24:MM-HH24:MM)',0);

INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('peugeot.sign','p3ug30t','Clave de firma del mensaje de acceso peugeot',0);

INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('lojack.server.url','http://localhost:8180/LoJackWeb/','Servidor del front de LoJack',0);
INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('lojack.server.proxy','false','Usar proxy para acceder a front de LoJack',0);