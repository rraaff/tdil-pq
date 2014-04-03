delete c from CITY as c where not EXISTS (select 1 from DEALER where id_city = c.id);
commit;

delete s from STATE as s where not EXISTS (select 1 from CITY where id_state = s.id);
commit;