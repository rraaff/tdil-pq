DELETE FROM VERSION;
INSERT INTO VERSION (versionNumber) VALUES(4);

COMMIT;

DELETE FROM SYSPROPERTIES WHERE propKey = 'buendia.url';
DELETE FROM NOTIFICATION_EMAIL WHERE notificationType = 'buendia';
COMMIT;

INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('buendia.url','http://www.milka.com.ar/buendia.jsp','Url de la experiencia buen dia',0);

INSERT INTO NOTIFICATION_EMAIL(notificationType,description,content,deleted) VALUES('buendia', 'Aprobacion de buen dia','contenido del email',0);

COMMIT;
