--mybatis 시작

--student테이블 생성
create table student(
    no number,
    name varchar2(100) not null,
    tel char(11) not null,
    constraint pk_student_no primary key(no)
);

create sequence seq_student_no;

select * from student order by no desc;

--mybatis 동적쿼리
--select * from student where no = #{no}
--1. 컬럼명/테이블명 식별자에 대한 동적처리 ${}
--2. 조건식 if, choose
--3. in 조건절 동적처리 where dept_code in ('D5', 'D7') (in 내부 개수 달라짐)

--web계정에서 kh계정 데이터 접근 처리

select * from kh.employee;
select * from kh.department;
select * from kh.job;

-----------------------------------
--kh계정
-----------------------------------
--접속계정변경
grant all on kh.employee to web;
grant select on kh.department to web;
grant select on kh.job to web;

--동의어 Synonym
--kh.employee emp (별칭과 동일하게 생각해도 ok)
--관리자로부터 create synonym권한 먼저 부여받을 것
create synonym emp for kh.employee;
create synonym dept for kh.department;
create synonym job for kh.job;

-------------------------
--관리자 system
-----------------------
grant create synonym to web;
-------------------------

select * from emp;
select * from dept;
select * from job;
