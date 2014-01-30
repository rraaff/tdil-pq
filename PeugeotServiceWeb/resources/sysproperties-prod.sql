UPDATE SYSPROPERTIES 
set propValue = 'https://proda-lojack-rest.thalamuslive.com/lojack' where propKey = 'thalamus.server';

UPDATE SYSPROPERTIES 
set propValue = 'REAL-LIFE-SMARTPHONE-APP' where propKey = 'thalamus.touchpoint.code';

UPDATE SYSPROPERTIES 
set propValue = 'cpe5ohjt6y0rk1sz5r3ddy2bhcjybtgcvgz7x7b9282gyjm05p3rf1ygvc053o3o' where propKey = 'thalamus.touchpoint.token';

UPDATE SYSPROPERTIES 
set propValue = 'http://www.lojackgis.com.ar/PreventWCFServices/GISService.svc' where propKey = 'prevent.server';

UPDATE SYSPROPERTIES 
set propValue = 'http://www.lojackprevent.com.ar/loginportal.ashx?' where propKey = 'prevent.loginurl';

UPDATE SYSPROPERTIES 
set propValue = 'a5b0981a0188bb9a5b7fe44b6c32d894' where propKey = 'prevent.token';

commit;
