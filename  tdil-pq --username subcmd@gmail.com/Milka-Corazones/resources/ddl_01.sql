ALTER TABLE FBUSER ADD DNI VARCHAR(15);
DROP VIEW IF EXISTS USER_PARTICIPATION;

CREATE VIEW USER_PARTICIPATION
AS SELECt us.fbid FacebookId, us.fbname FacebookName, us.DNI DNI, pa.creationDate FechaParticipacion, pa.coord Corazon
FROM FBUSER us, PARTICIPATION pa
where us.id = pa.fbuserID
order by pa.creationDate;


ALTER TABLE PARTICIPATION ADD dailyPrizeId INT;