DELETE FROM VERSION;

INSERT INTO SYSPROPERTIES (propKey,propValue,deleted) VALUES('fb.app','251599304960063',0);
INSERT INTO SYSPROPERTIES (propKey,propValue,deleted) VALUES('server.name','http://www.djmagla.com',0);

INSERT INTO VERSION (versionNumber) VALUES(4);

COMMIT;