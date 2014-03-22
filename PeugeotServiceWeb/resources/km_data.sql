DELETE from DATA_IMPORT where type = 'KM';
DELETE FROM KM_DATA;
INSERT INTO KM_DATA
(dominio,
km,
fechareporte,
marca,
modelo,
fechaalta,
identidad,
idequipo,
userlogin,
cliente_nombre,
cliente_cuit,
deleted)
VALUES
(
'CZP075',
19200,
CURDATE(),
'Peugeot',
'208 GTI',
CURDATE(),
'1',
'1',
'1',
'2',
'1',
0
);

INSERT INTO KM_DATA
(dominio,
km,
fechareporte,
marca,
modelo,
fechaalta,
identidad,
idequipo,
userlogin,
cliente_nombre,
cliente_cuit,
deleted)
VALUES
(
'IJJ-519',
2000,
CURDATE(),
'Peugeot',
'RCZ',
CURDATE() - INTERVAL 800 DAY,
'1',
'1',
'1',
'2',
'1',
0
);
commit;
