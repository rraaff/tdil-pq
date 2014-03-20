update SYSPROPERTIES set propValue = 'http://localhost:8180/LoJackWeb/'
where propKey = 'lojack.server.url';

UPDATE SYSPROPERTIES 
set propValue = 'http://localhost:8280/ThalamusWeb/PreventWCFServices/GISService.svc' where propKey = 'prevent.server';

update SYSPROPERTIES set propValue = '10000'
where propKey = 'prevent.timeout';

update SYSPROPERTIES set propValue = 'http://test.lojackgis.com.ar:8080/webgis/Prevent_WebUI/loginportal.ashx?'
where propKey = 'prevent.loginurl';

update SYSPROPERTIES set propValue = 'a5b0981a0188bb9a5b7fe44b6c32d894'
where propKey = 'prevent.token';

commit;