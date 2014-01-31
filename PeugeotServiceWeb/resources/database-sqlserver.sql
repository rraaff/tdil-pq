

CREATE DATABASE PEUGEOTSVC_DB;

CREATE LOGIN PEUGEOTSVC_USER WITH PASSWORD = 'p4l0ll1n4'
,DEFAULT_DATABASE = PEUGEOTSVC_DB;

USE PEUGEOTSVC_DB

CREATE USER PEUGEOTSVC_USER FOR LOGIN PEUGEOTSVC_USER;

EXEC sp_addrolemember 'db_datareader', 'PEUGEOTSVC_USER'
EXEC sp_addrolemember 'db_datawriter', 'PEUGEOTSVC_USER'
EXEC sp_addrolemember 'db_accessadmin', 'PEUGEOTSVC_USER'
EXEC sp_addrolemember 'db_backupoperator', 'PEUGEOTSVC_USER'
EXEC sp_addrolemember 'db_ddladmin', 'PEUGEOTSVC_USER'
EXEC sp_addrolemember 'db_owner', 'PEUGEOTSVC_USER'
EXEC sp_addrolemember 'db_securityadmin', 'PEUGEOTSVC_USER'

EXEC sp_droprolemember 'db_denydatareader', 'PEUGEOTSVC_USER'
EXEC sp_droprolemember 'db_denydatawriter', 'PEUGEOTSVC_USER'

USE MASTER