UPDATE SYSPROPERTIES 
set propValue = 'http://localhost:8280/ThalamusWeb/' where propKey = 'thalamus.server';

UPDATE SYSPROPERTIES 
set propValue = 'test' where propKey = 'thalamus.touchpoint.code';

UPDATE SYSPROPERTIES 
set propValue = 'testtesttesttesttesttesttesttesttesttesttesttesttesttesttesttest' where propKey = 'thalamus.touchpoint.token';

commit;