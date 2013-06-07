UPDATE SYSPROPERTIES 
set propValue = 'http://test.lojackgis.com.ar:8181/Carpathia.Middleware/Service1.svc/' where propKey = 'gis.server';

UPDATE SYSPROPERTIES 
set propValue = 'http://test.lojackgis.com.ar:8181/Carpathia.Middleware/Service1.svc/' where propKey = 'services.server';

UPDATE SYSPROPERTIES 
set propValue = 'soap-json' where propKey = 'mw.protocol';

commit;

