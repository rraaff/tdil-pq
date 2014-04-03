if exists (select * from INFORMATION_SCHEMA.TABLES where TABLE_NAME = 'NATIVE_APP')
    drop table NATIVE_APP;  

CREATE TABLE NATIVE_APP (
  id INT NOT NULL IDENTITY ,
  code VARCHAR(20) NULL ,
  title VARCHAR(100) NULL ,
  version VARCHAR(100) NULL ,
  url VARCHAR(400) NULL ,
  deleted INT NOT NULL,
  PRIMARY KEY (id));
  
CREATE INDEX IX_NATIVE_APP_00 ON NATIVE_APP (code);

insert into NATIVE_APP(code,title,version,url,deleted)
values('android','Peugeot Service Android','1.0.0','http://www.google.com',0);
commit;

ALTER TABLE SYSTEMUSER ADD nativeAppAccess INT NULL;
update SYSTEMUSER set nativeAppAccess = 1;
commit;