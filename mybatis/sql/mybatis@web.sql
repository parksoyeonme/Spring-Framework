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
