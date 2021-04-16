--==========================
--관리자계정 system
--======================
create user spring
identified by spring
default tablespace users;

grant connect, resource to spring;

--===========================
--spring계정
--==========================
create table dev(
    no number,
    name varchar2(100) not null,
    career number not null,
    email varchar2(200) not null,
    gender char(1),
    lang varchar2(100) not null,
    constraint pk_dev_no primary key(no),
    constraint ck_dev_gender check(gender in ('M', 'F'))
);

create sequence seq_dev_no;

select
    *
from
    dev;


