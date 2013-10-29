UPDATE SYSPROPERTIES 
set propValue = 'https://testa-lojack-rest.thalamuslive.com/lojack' where propKey = 'thalamus.server';

UPDATE SYSPROPERTIES 
set propValue = 'LO-JACK-WEB' where propKey = 'thalamus.touchpoint.code';

UPDATE SYSPROPERTIES 
set propValue = 'txv0zcdgj5etd9eefqw25789viibuvrpl20ry2ojycu1ebhtl11avk8wv05898gw' where propKey = 'thalamus.touchpoint.token';

commit;