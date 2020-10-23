Create table delete_member as
(select member_id, member_name, gender, phone, enroll_date
from member where 1=0);

alter table delete_member add
constraint ID_PRI primary key(member_id);

alter table delete_member add(END_DATE DATE);

ALTER TABLE MEMBER ADD DROP_YN CHAR(1);

UPDATE MEMBER SET DROP_YN = 'N';

commit;

SELECT * FROM MEMBER;
