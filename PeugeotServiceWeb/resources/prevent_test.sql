update SYSPROPERTIES set propValue = 'http://www.lojack-app.com.ar:81'
where propKey = 'lojack.server.url';

update SYSPROPERTIES set propValue = 'http://test.lojackgis.com.ar/webgis/PreventWCFServices/GISService.svc/'
where propKey = 'prevent.server';

update SYSPROPERTIES set propValue = '10000'
where propKey = 'prevent.timeout';

update SYSPROPERTIES set propValue = 'http://test.lojackgis.com.ar:8080/webgis/Prevent_WebUI/loginportal.ashx?'
where propKey = 'prevent.loginurl';

update SYSPROPERTIES set propValue = 'a5b0981a0188bb9a5b7fe44b6c32d894'
where propKey = 'prevent.token';

commit;