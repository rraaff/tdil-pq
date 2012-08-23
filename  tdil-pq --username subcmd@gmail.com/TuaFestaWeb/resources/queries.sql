SELECT *
FROM PROF_PROD_CATEGORY cat1 left outer join PROF_PROD_CATEGORY cat2
on cat1.id = cat2.parent_id left outer join PROF_PROD_CATEGORY cat3
on cat2.id = cat3.parent_id left outer join PROF_PROD_CATEGORY cat4
on cat3.id = cat4.parent_id left outer join PROF_PROD_CATEGORY cat5
on cat4.id = cat5.parent_id 

SELECT *
FROM PROF_PROD_CATEGORY cat1 
where cat1.id = 1
union all
SELECT *
FROM PROF_PROD_CATEGORY cat2
where cat2.parent_id = 1
union all
SELECT cat3.*
FROM PROF_PROD_CATEGORY cat3, PROF_PROD_CATEGORY cat2 
where cat2.parent_id = 1
and cat3.parent_id = cat2.id;