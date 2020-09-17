create table reports (
  id int,
  pid int,
  title varchar(256)
);

CREATE SEQUENCE reports_seq
  MAXVALUE 10000
  START WITH 1000
  INCREMENT BY 1
  CACHE 20;

