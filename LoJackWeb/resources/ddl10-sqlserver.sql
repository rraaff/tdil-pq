ALTER TABLE SYSTEMUSER ADD vluAccess INT NULL;

update SYSTEMUSER set vluAccess = 1;

commit;