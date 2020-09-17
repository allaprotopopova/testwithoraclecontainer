alter database default tablespace users;

create user PERSONAL identified by "1qaz@WSX";
alter user PERSONAL quota unlimited on users;
grant connect to PERSONAL;
grant create table to PERSONAL;
grant create view to PERSONAL;
grant create sequence to PERSONAL;
grant create trigger to PERSONAL;
grant create procedure to PERSONAL;
grant create type to PERSONAL;
grant create any context to PERSONAL;

create user REPORTS identified by "1qaz@WSX";
alter user REPORTS quota unlimited on users;
grant connect to REPORTS;
grant create table to REPORTS;
grant create view to REPORTS;
grant create sequence to REPORTS;
grant create trigger to REPORTS;
grant create procedure to REPORTS;
grant create type to REPORTS;
grant create any context to REPORTS;
