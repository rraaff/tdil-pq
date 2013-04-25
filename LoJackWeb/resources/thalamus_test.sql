UPDATE SYSPROPERTIES 
set propValue = 'https://testa-lojack-rest.thalamuslive.com/lojack' where propKey = 'thalamus.server';

UPDATE SYSPROPERTIES 
set propValue = 'REAL-LIFE-SMARTPHONE-APP' where propKey = 'thalamus.touchpoint.code';

UPDATE SYSPROPERTIES 
set propValue = 'k8exyct1v6edf9q50fryuq3r02upn0m98twst4etlbjgc9cnsq585879gxlcbu11' where propKey = 'thalamus.touchpoint.token';

commit;