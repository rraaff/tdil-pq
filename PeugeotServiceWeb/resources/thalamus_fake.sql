UPDATE SYSPROPERTIES 
set propValue = 'http://localhost:8280/ThalamusWeb/' where propKey = 'thalamus.server';

UPDATE SYSPROPERTIES 
set propValue = 'test' where propKey = 'thalamus.touchpoint.code';

UPDATE SYSPROPERTIES 
set propValue = 'testtesttesttesttesttesttesttesttesttesttesttesttesttesttesttest' where propKey = 'thalamus.touchpoint.token';

commit;

UPDATE SYSPROPERTIES 
set propValue = 'http://localhost:8280/ThalamusWeb/PreventWCFServices/GISService.svc' where propKey = 'prevent.server';

UPDATE SYSPROPERTIES 
set propValue = 'http://a.tile.openstreetmap.org/${z}/${x}/${y}.png' where propKey = 'maps.url';

UPDATE SYSPROPERTIES 
set propValue = 'http://localhost:8180/PeugeotServiceWeb' where propKey = 'front.server';

commit;