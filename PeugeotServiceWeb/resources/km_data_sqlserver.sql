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
8200,
GETDATE(),
'Peugeot',
'208 GTI',
GETDATE(),
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
GETDATE(),
'Peugeot',
'RCZ',
GETDATE(),
'1',
'1',
'1',
'2',
'1',
0
);
commit;
