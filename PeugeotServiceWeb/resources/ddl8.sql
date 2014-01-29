DELETE FROM SYSPROPERTIES WHERE propKey = 'maps.url';
commit;

INSERT INTO SYSPROPERTIES (propKey,propValue,description,deleted) VALUES('maps.url','http://a.tile.openstreetmap.org/${z}/${x}/${y}.png','Url de los mapas',0);

commit;