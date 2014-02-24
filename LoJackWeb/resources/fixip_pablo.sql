update SYSPROPERTIES
set propValue = 'http://192.168.1.3:8180/LoJackWeb'
where propKey = 'front.server';
