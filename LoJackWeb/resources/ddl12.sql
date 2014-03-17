ALTER TABLE VLU_IMPORT ADD importType INT NULL;

update VLU_IMPORT set importType = 0;

commit;