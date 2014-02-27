DELETE from DATA_IMPORT where type = 'KM';
INSERT INTO `KM_DATA`
(`dominio`,
`km`,
`fechareporte`,
`marca`,
`modelo`,
`fechaalta`,
`identidad`,
`idequipo`,
`userlogin`,
`cliente_nombre`,
`cliente_cuit`,
`deleted`)
VALUES
(
'CZP075',
8100,
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
commit;
