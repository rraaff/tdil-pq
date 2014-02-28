delete from SYSPROPERTIES where propKey = 'peugeot.sign';
INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('peugeot.sign','p3ug30t','Clave de firma del mensaje de acceso peugeot',0);
commit;