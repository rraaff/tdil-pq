DELETE FROM NOTIFICATION_EMAIL WHERE notificationType LIKE 'disapprove%';

INSERT INTO NOTIFICATION_EMAIL(notificationType,description,content,subject,from_,deleted) 
VALUES('disapprove.personal', 'Desaprobacion de datos personales','La modificacion de tus datos personales no ha sido aprobada.[MOTIVE]', 'Modificacion no aprobada', 'tuafesta.test@gmail.com', 0);

INSERT INTO NOTIFICATION_EMAIL(notificationType,description,content,subject,from_,deleted) 
VALUES('disapprove.business', 'Desaprobacion de datos profesionales','La modificacion de tus datos profesionales no ha sido aprobada.[MOTIVE]', 'Modificacion no aprobada', 'tuafesta.test@gmail.com', 0);

INSERT INTO NOTIFICATION_EMAIL(notificationType,description,content,subject,from_,deleted) 
VALUES('disapprove.sell', 'Desaprobacion de datos venta','La venta [SELL_NAME] no ha sido aprobada.[MOTIVE]','Venta no aprobada', 'tuafesta.test@gmail.com', 0);

COMMIT;