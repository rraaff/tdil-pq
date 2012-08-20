DELETE FROM VERSION;

DELETE FROM SYSPROPERTIES WHERE propKey = 'fb.app';
DELETE FROM SYSPROPERTIES WHERE propKey = 'server.name';
INSERT INTO SYSPROPERTIES (propKey,propValue,deleted) VALUES('fb.app','423756277670563',0);
INSERT INTO SYSPROPERTIES (propKey,propValue,deleted) VALUES('server.name','http://ec2-23-21-18-79.compute-1.amazonaws.com:8080/DJMAG_WEB',0);

INSERT INTO VERSION (versionNumber) VALUES(4);

COMMIT;