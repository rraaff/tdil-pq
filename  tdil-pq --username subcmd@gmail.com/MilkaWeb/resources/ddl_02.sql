DROP TABLE IF EXISTS VERSION;

CREATE TABLE VERSION (
	versionNumber INT NOT NULL)
ENGINE = MYISAM;

INSERT INTO VERSION (versionNumber) VALUES(2);

COMMIT;