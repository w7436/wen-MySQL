create database if not exists stu_sys  ;

use stu_sys;
drop table student;
# 创建学生表
create table student(
    Id int primary key auto_increment,
    name varchar(10) not null comment '姓名',
    sex varchar(10) not null comment '性别',
    bithday date not null comment '出生日期',
    password varchar(10) not null comment '密码',
    depart varchar(20) not null comment '专业',
    phone varchar(20) not null comment '手机号',
    email varchar(20) not null comment '邮箱'
);

insert into student(name,sex,bithday,password,depart,phone,email) values('小红','女','2000-10-11','111','电子信息工程','15592123201','3234897843@qq.com');
insert into student(name,sex,bithday,password,depart,phone,email) values('小蓝','男','1999-04-08','111','软件工程','13492123201','3531117843@qq.com');
insert into student(name,sex,bithday,password,depart,phone,email) values('小绿','女','2000-05-15','111','通信工程','17792123632','2234857843@qq.com');
insert into student(name,sex,bithday,password,depart,phone,email) values('小黄','女','2000-06-06','111','电子信息工程','14192123201','6234897843@qq.com');



# 创建课程表
create table course(
    Id int primary key auto_increment,
    name varchar(20) not null comment '课程名',
    credit int not null comment '学分'
);
insert into course(name,credit) values ('数据结构',3);
insert into course(name,credit) values ('离散数学',3);
insert into course(name,credit) values ('大学物理',6);


drop table teacher;
# 教师表
create table teacher(
    Id int primary key auto_increment,
    name varchar(10) not null comment '姓名',
    sex varchar(10) not null comment '性别',
    task varchar(10) not null comment '职称',
    phone varchar(20) not null comment '电话',
    password varchar(10) not null comment '密码',
    email varchar(20) not null comment '邮箱'
);

delete from teacher where name = '小白';
insert into teacher (name, sex, task, phone,password, email) values ('白老师','女','讲师','1592343253','123','2343254321@qq.com');
insert into teacher (name, sex, task, phone,password, email) values ('黄老师','女','讲师','1472343253','123','2343232321@qq.com');
insert into teacher (name, sex, task, phone, password,email) values ('赵老师','男','教授','1552343253','123','2543254321@qq.com');


create table manager (
    id int primary key auto_increment,
    username varchar(10) not null ,
    password varchar(10) not null
);

INSERT INTO manager(username, password) VALUES ('admin', '123');

drop table selecting;
# 选课信息表
create table selecting(
    s_Id int,
    c_Id int,
    score int not null ,
    foreign key (s_Id) references student(Id),
    foreign key (c_Id) references course(Id)
);

drop table teaching;
# 授课表
create table teaching(
    c_Id int,
    t_Id int,
    date Datetime not null ,
    foreign key (c_Id) references course(Id),
    foreign key (t_Id) references teacher(Id)
);

select student.name,student.sex,student.password,student.depart,student.phone,
       course.name,course.credit,teacher.name
from student,course,teacher,teaching,selecting
where selecting.s_Id = student.Id and selecting.c_Id = course.Id
  and teaching.c_Id = course.Id and teaching.t_Id = teacher.Id;