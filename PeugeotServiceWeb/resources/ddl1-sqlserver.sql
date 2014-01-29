drop table SYSTEMUSER;
CREATE TABLE SYSTEMUSER (
  id INT NOT NULL IDENTITY ,
  username VARCHAR(20) NULL ,
  password VARCHAR(50) NULL ,
  loggingAccess INT NULL,
  syspropAccess INT NULL,
  deleted INT NOT NULL ,
  PRIMARY KEY (id));
  
CREATE INDEX IX_SYSTEMUSER_00 ON SYSTEMUSER (username);

INSERT INTO SYSTEMUSER(username,password,loggingAccess,syspropAccess, deleted) VALUES('tdil','f5314a8a0b0a34239a8bf78104f2ff4754a7a890', 1, 1, 0);
COMMIT;