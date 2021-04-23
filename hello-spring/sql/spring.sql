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
    
--회원테이블 추가
create table member(
    id varchar2(20),
    password varchar2(300) not null,
    name varchar2(256) not null,
    gender char(1),
    birthday date,
    email varchar2(256),
    phone char(11) not null,
    address varchar2(512),
    hobby varchar2(256),
    enroll_date date default sysdate,
    enabled number default 1, -- 활성화 여부 : 1(활성화), 0(비활성화)
    
    constraint pk_member_id primary key(id),
    constraint ck_member_gender check(gender in ('M', 'F')),
    constraint ck_member_enabled check(enabled in (1, 0))
);

insert into spring.member values ('abcde','1234','아무개','M',to_date('88-01-25','rr-mm-dd'),'abcde@naver.com','01012345678','서울시 강남구','운동,등산,독서',default,default);
insert into spring.member values ('qwerty','1234','김말년','F',to_date('78-02-25','rr-mm-dd'),'qwerty@naver.com','01098765432','서울시 관악구','운동,등산',default,default);
insert into spring.member values ('admin','1234','관리자','F',to_date('90-12-25','rr-mm-dd'),'admin@naver.com','01012345678','서울시 강남구','독서',default,default);
commit;

select * from member;

--비밀번호 수동 업데이트
update member
set password = '$2a$10$8.1m9h4mAMGkrWd19LOvju8s4scca26AO1qaHjRnJFHs0iwb8kRw2'
where id = 'join0420';

update member
set password = '$2a$10$LEONgA/04/RxHDzxAFY97.Jeb1d5pd.TY95Con7yQz5GF4hR0Sitq'
where id = 'jjappa123';

update member
set password = '$2a$10$LHX0yHcWHUORrWdzoX6GEe9FSFBinT6jYdMMktbXGryH0SspjjNZ6'
where id = 'sinsa';
update member
set password = '$2a$10$4XYr.mX/DTwKiDZoIsn9wupB8SY03oP4xF8eQ1ZMkLn08zzNoy1yG'
where id = 'abcde';
update member
set password = '$2a$10$grMcXtNmWqvkM1spRwLAdejmDcpJBBIQUbN4LE/mIadQk/sehbwYy'
where id = 'qwerty';
update member
set password = '$2a$10$nP12zdQe.3z8Ymjc5vBhI.JLl4jmRDMLepPJXyVzaakkAip/jL/tC'
where id = 'admin';

--MEMO테이블 생성

create table memo(
    no number,
    memo varchar2(2000),
    password char(4) not null,
    reg_date date default sysdate,
    constraint pk_Memo primary key(no)
);
create sequence seq_memo_no;

insert into
    memo
values(
    seq_memo_no.nextval, '안녕하세요, 봄날입니다.', '1234', default
);

select * from memo;