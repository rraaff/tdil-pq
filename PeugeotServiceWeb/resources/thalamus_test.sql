UPDATE SYSPROPERTIES 
set propValue = 'https://proda-lojack-rest.thalamuslive.com/lojack' where propKey = 'thalamus.server';

UPDATE SYSPROPERTIES 
set propValue = 'PEUGEOT-SMARTPHONE-APP' where propKey = 'thalamus.touchpoint.code';

UPDATE SYSPROPERTIES 
set propValue = '3870s6wox7dizomqkd6ngz5k6fw6bk5zx1yrh2dv7lqpggkey3ibo8q6w0u8uocx' where propKey = 'thalamus.touchpoint.token';

commit;