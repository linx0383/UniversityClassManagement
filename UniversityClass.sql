use universityclass;

drop table classroomschedule;
drop table classroom;
drop table universityclass;
create table universityclass(
cid int auto_increment,
cname varchar(50) not null,
major varchar(20) not null,
enrolledstudent varchar(100),
CONSTRAINT cid_pk PRIMARY KEY(cid)
);
ALTER TABLE universityclass AUTO_INCREMENT = 1001;

insert into universityclass(cname,major) values('Data Structure', 'CS');
insert into universityclass(cname,major) values('Operating System', 'CS');
insert into universityclass(cname,major) values('Analog Circuit Design', 'EE');
insert into universityclass(cname,major) values('Digital Signal Processing', 'EE');
insert into universityclass(cname,major) values('Linear Algebra', 'Math');


drop table student;
create table student(
sname varchar(20) not null,
stid int auto_increment,
spassword varchar(20) default 'pass',
constraint sid_pk primary key(stid)
);

ALTER TABLE student AUTO_INCREMENT = 100001;

insert into student(sname) values('Lebron James');
insert into student(sname) values('Steven Curry');
insert into student(sname) values('Kevin Durant');
insert into student(sname) values('Kobe Bryant');

create table classroom(
cid int not null unique,
crname varchar(30) not null,
seat int not null,
constraint cid_fk foreign key(cid) references universityclass(cid)
);
insert into classroom(cid,crname, seat) values(1001,'Keller Hall 3-220',80);
insert into classroom(cid,crname, seat) values(1002,'Richard Hall 1-130',50);
insert into classroom(cid,crname, seat) values(1003,'Keller Hall 3-260',30);
insert into classroom(cid,crname, seat) values(1004,'Richard Hall 2-130',50);
insert into classroom(cid,crname, seat) values(1005,'Keller Hall 4-250',70);

create table classroomschedule(
scheduleid int not null,
startdate date not null,
enddate date not null,
starttime time not null,
endtime time not null,
CONSTRAINT scheduleid_fk FOREIGN KEY(scheduleid) REFERENCES classroom(cid)
);
insert into classroomschedule(scheduleid,startdate,enddate,starttime,endtime) values(1001,'2016-09-01','2016-12-21','10:00:00','12:00:00');

drop table administrator;
create table administrator(
stid varchar(20) not null,
spassword varchar(20) default 'password'
);
insert into administrator(stid) values('admin');

select * from universityclass;
select * from student;
select * from classroom;
select * from classroomschedule;
select * from administrator;

DROP VIEW universityclass_details;

create view universityclass_details as
SELECT universityclass.*, classroom.crname AS classidclassroom FROM universityclass INNER JOIN classroom
WHERE universityclass.cid = classroom.cid;
-- SELECT * FROM universityclass inner join classroom on universityclass.cid=classroom.cid;
-- select stid,spassword from student union select stid,spassword from administrator;
select stid,spassword from student union select stid,spassword from administrator;