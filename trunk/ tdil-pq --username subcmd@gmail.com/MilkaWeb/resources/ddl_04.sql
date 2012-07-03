DELETE FROM SYSPROPERTIES WHERE propKey = 'limitar.participaciones';
COMMIT;

INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('limitar.participaciones','true','Limita las participaciones a una por dia',0);
COMMIT;