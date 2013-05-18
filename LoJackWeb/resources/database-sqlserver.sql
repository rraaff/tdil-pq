--DROP USER LOJACK_USER;
DROP LOGIN LOJACK_USER;
DROP DATABASE LOJACK_DB;

CREATE DATABASE LOJACK_DB;

CREATE LOGIN LOJACK_USER WITH PASSWORD = 'LOJACK_USER'
,DEFAULT_DATABASE = LOJACK_DB;

USE LOJACK_DB

CREATE USER LOJACK_USER FOR LOGIN LOJACK_USER;

EXEC sp_addrolemember 'db_datareader', 'LOJACK_USER'
EXEC sp_addrolemember 'db_datawriter', 'LOJACK_USER'
EXEC sp_addrolemember 'db_accessadmin', 'LOJACK_USER'
EXEC sp_addrolemember 'db_backupoperator', 'LOJACK_USER'
EXEC sp_addrolemember 'db_ddladmin', 'LOJACK_USER'
EXEC sp_addrolemember 'db_owner', 'LOJACK_USER'
EXEC sp_addrolemember 'db_securityadmin', 'LOJACK_USER'

EXEC sp_droprolemember 'db_denydatareader', 'LOJACK_USER'
EXEC sp_droprolemember 'db_denydatawriter', 'LOJACK_USER'

USE MASTER