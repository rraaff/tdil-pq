DELETE FROM SYSPROPERTIES WHERE propKey = 'vlu.deleteRepaired.range';
DELETE FROM SYSPROPERTIES WHERE propKey = 'vlu.deleteRepaired.url';
DELETE FROM SYSPROPERTIES WHERE propKey = 'vlu.deleteRepaired.proxy';
commit;

INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('vlu.deleteRepaired.range','00:00-23:59','Rango de importacion de dominios reparados VLU (HH24:MM-HH24:MM)',0);
INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('vlu.deleteRepaired.url','http://pic.dhe.ibm.com/infocenter/tivihelp/v41r1/topic/com.ibm.ismsaas.doc/reference/OperatingSystemsNotInstalledSample.csv','URL completa del csv de dominios reparados',0);
INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('vlu.deleteRepaired.proxy','false','Usar proxy para acceder al csv de dominios reparados',0);

commit;

