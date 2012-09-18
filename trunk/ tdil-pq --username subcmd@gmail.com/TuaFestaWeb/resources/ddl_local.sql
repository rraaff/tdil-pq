UPDATE SYSPROPERTIES 
set propValue = 'http://localhost:8180/TuaFestaWeb/'
where propKey = 'server.name';

UPDATE SYSPROPERTIES 
set propValue = '156203561187667'
where propKey = 'fb.api_key';

UPDATE SYSPROPERTIES 
set propValue = '079f8aed673c87cef4bb25340077ab04'
where propKey = 'fb.secret';
