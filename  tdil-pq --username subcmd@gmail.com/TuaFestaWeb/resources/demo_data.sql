insert into PROF_PROD_CATEGORY (id, name, description, parent_id, deleted) values (1, 'Consumibles', 'Consumibles', 0, 0);
	insert into PROF_PROD_CATEGORY (id, name, description, parent_id, deleted) values (2, 'Tortas', 'Tortas', 1, 0);
		insert into PROF_PROD_CATEGORY (id, name, description, parent_id, deleted) values (5, 'Budin', 'Budin', 2, 0);
		insert into PROF_PROD_CATEGORY (id, name, description, parent_id, deleted) values (6, 'Bizcochuelo', 'Bizcochuelo', 2, 0);
		insert into PROF_PROD_CATEGORY (id, name, description, parent_id, isother, deleted) values (10, 'Otros', 'Otros', 2, 1, 0);
	insert into PROF_PROD_CATEGORY (id, name, description, parent_id, deleted) values (4, 'Tartas', 'Tartas', 1, 0);
		insert into PROF_PROD_CATEGORY (id, name, description, parent_id, deleted) values (7, 'Coco', 'Coco', 4, 0);
		insert into PROF_PROD_CATEGORY (id, name, description, parent_id, deleted) values (8, 'Pasta Frola', 'Pasta Frola', 4, 0);
		insert into PROF_PROD_CATEGORY (id, name, description, parent_id, isother, deleted) values (11, 'Otros', 'Otros', 4, 1, 0);
	insert into PROF_PROD_CATEGORY (id, name, description, parent_id, isother, deleted) values (9, 'Otros', 'Otros', 1, 1, 0);
insert into PROF_PROD_CATEGORY (id, name, description, parent_id, deleted) values (3, 'Cotillon', 'Cotillon', 0, 0);



insert into PROF_PRODUCT values (1, 'Bizcochuelo (kilo)', 'Bizcochuelo (kilo)', NULL, 1, 2, 0);
insert into PROF_PRODUCT values (2, 'Budin (kilo)', 'Budin (kilo)', NULL, 1, 2, 0);
insert into PROF_PRODUCT values (3, 'Maraca banana', 'Maraca banana', NULL, 1, 3, 0);
insert into PROF_PRODUCT values (4, 'Anillo luminoso', 'Anillo luminoso', NULL, 1, 3, 0);
insert into PROF_PRODUCT values (5, 'Sombrero luminoso', 'Sombrero luminoso', NULL, 1, 3, 0);

insert into PROF_SERV_CATEGORY values (1, 'Animacion', 'Animacion', 0, 0);
insert into PROF_SERV_CATEGORY values (2, 'Personajes Infantiles', 'Personajes Infantiles', 1, 0);
insert into PROF_SERV_CATEGORY values (3, 'Alquiler', 'Alquiler', 0, 0);

insert into PROF_SERVICE values (1, 'Barnie', 'Barnie', NULL, 1, 2, 0);
insert into PROF_SERVICE values (2, 'Ben 10', 'Ben 10', NULL, 1, 2, 0);
insert into PROF_SERVICE values (3, 'Vajilla', 'Vajilla', NULL, 1, 3, 0);
insert into PROF_SERVICE values (4, 'Sillas', 'Sillas', NULL, 1, 3, 0);
insert into PROF_SERVICE values (5, 'Manteles', 'Manteles', NULL, 1, 3, 0);
