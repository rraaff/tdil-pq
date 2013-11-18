DELETE FROM SYSPROPERTIES WHERE propKey = 'clubLoJack.url';

commit;

INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('clubLoJack.url','http://www.clublojack.com.ar/','Url de beneficios del club lo jack',0);

commit;

INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('clubLoJack.show','false','Identifica si se activa o no la funcionalidad de club lojack',0);

commit;