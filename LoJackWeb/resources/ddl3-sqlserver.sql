CREATE TABLE CAMERA_CONF (
  id INT NOT NULL IDENTITY ,
  url VARCHAR(200) NULL ,
  idWebsiteUser INT NOT NULL 
    CONSTRAINT FK_CAMERA_CONF_00 FOREIGN KEY REFERENCES WEBSITEUSER(id),
  description VARCHAR(100) NULL ,
  PRIMARY KEY (id));
    
CREATE INDEX IX_CAMERA_CONF_00 ON CAMERA_CONF (idWebsiteUser);