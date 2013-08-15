update SYSPROPERTIES
set propValue = 'http://192.168.252.100:8180/LoJackWeb'
where propKey = 'front.server';
