UPDATE SYSPROPERTIES 
set propValue = 'http://localhost:8180/GISWeb/gis/' where propKey = 'gis.server';

UPDATE SYSPROPERTIES 
set propValue = 'http://localhost:8180/GISWeb/services/' where propKey = 'services.server';

commit;

