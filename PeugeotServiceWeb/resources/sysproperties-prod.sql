UPDATE SYSPROPERTIES 
set propValue = 'https://proda-lojack-rest.thalamuslive.com/lojack' where propKey = 'thalamus.server';

UPDATE SYSPROPERTIES 
set propValue = 'PEUGEOT-SMARTPHONE-APP' where propKey = 'thalamus.touchpoint.code';

UPDATE SYSPROPERTIES 
set propValue = '3870s6wox7dizomqkd6ngz5k6fw6bk5zx1yrh2dv7lqpggkey3ibo8q6w0u8uocx' where propKey = 'thalamus.touchpoint.token';

UPDATE SYSPROPERTIES 
set propValue = 'http://www.lojackgis.com.ar/PreventWCFServices/GISService.svc' where propKey = 'prevent.server';

UPDATE SYSPROPERTIES 
set propValue = 'http://www.lojackprevent.com.ar/loginportal.ashx?' where propKey = 'prevent.loginurl';

UPDATE SYSPROPERTIES 
set propValue = 'a5b0981a0188bb9a5b7fe44b6c32d894' where propKey = 'prevent.token';

commit;
