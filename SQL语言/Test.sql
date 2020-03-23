
-- 学生表（Student）
-- 学号
-- 性别
-- 姓名
-- 出生
-- 所在班级
create table student (
	sno varchar(20) primary key,
	sname varchar(20) not null,
	ssex varchar(20) not null,
	sbirthday datetime,
	class varchar(20)
);

insert into student values('100','小绿1','女','2000-05-05','95030');
insert into student values('101','小绿','男','2000-01-01','95031');
insert into student values('102','小黄','女','2000-01-02','95032');
insert into student values('103','小红','男','2000-01-03','95033');
insert into student values('104','小紫','女','2000-01-04','95034');
insert into student values('105','小橙','女','2000-01-05','95035');
insert into student values('106','小蓝','男','2000-01-06','95036');
insert into student values('107','小蓝1','男','2000-01-07','95031');





-- 教师表（teacher）
-- 教师编号
-- 教师名字
-- 教师性别
-- 出生
-- 职称
-- 所在部门
create table teacher(
	tno varchar(20) primary key,
	tname varchar(20) not null,
	tsex varchar(20) not null,
	tbirthday datetime,
	prof varchar(20) not null,
	depart varchar(20) not null
);


insert into teacher values('801','小明','男','1990-01-01','副教授','计算机系');
insert into teacher values('802','丹尼','男','1990-01-02','教授','电子工程系');
insert into teacher values('803','杰森','男','1990-01-03','讲师','通信工程系');
insert into teacher values('804','杰克','男','1990-01-04','助教','计算机系');


-- 课程表（course)
-- 课程号
-- 课程名称
-- 教师编号
create table course(
	cno varchar(20) primary key,
	cname varchar(20) not null,
	tno varchar(20) not null,
	foreign key(tno) references teacher (tno)
);

insert into course values ('3-101','高等数学','801');

insert into course values ('3-102','线性代数','802');

insert into course values ('3-103','大学物理','803');
insert into course values ('3-104','大学英语','804');


-- 成绩表（score）
-- 学号
-- 课程号
-- 成绩

create table score(
	sno varchar(20) primary key,
	cno varchar(20) not null,
	degree decimal,
	foreign key(sno) references student(sno),
	foreign key(cno) references course(cno)
);

insert into score values('101','3-101','88');
insert into score values('102','3-102','90');
insert into score values('103','3-103','91');
insert into score values('104','3-104','92');
insert into score values('105','3-101','93');
insert into score values('106','3-102','94');	
insert into score values('107','3-104','97');
insert into score values('100','3-101','96');





	
-- 查询教师中所有的单位即不重复的depart列
-- distinct 排除重复
select distinct depart from teacher;
mysql> select distinct depart from teacher;
+------------+
| depart     |
+------------+
| 计算机系   |
| 电子工程系 |
| 通信工程系 |
+------------+

-- 查找区间
select * from score where degree between 90 and 100;  
select * from score where degree>90 and degree<100;

-- 表示或关系的查询 in
select * from score where degree in(90,92);

-- 查询student 表中“95031”或性别为“女”的同学记录
select * from student where class='95031' or ssex='女';

--desc-->降序 asc--->升序
select * from student order by class desc;
select * from score order by cno asc,degree desc;

-- 查询“95031”班的学生人数
select count(*) from student where class='95031';

 -- 查询score表中最高分的学生学号和课程号
 select sno,cno from score where degree=(select max(degree) from score);
 1、找到最高分
 2、找最高分的sno,cno

 -- 排序的做法是：
 select sno,cno from score order by degree desc limit 0,1;
mysql> select sno,cno,degree from score order by degree desc limit 0,1;
+-----+-------+--------+
| sno | cno   | degree |
+-----+-------+--------+
| 106 | 3-102 |     94 |
+-----+-------+--------+
1 row in set (0.00 sec)
mysql> select sno,cno,degree from score order by degree desc limit 0,2;
+-----+-------+--------+
| sno | cno   | degree |
+-----+-------+--------+
| 106 | 3-102 |     94 |
| 105 | 3-101 |     93 |
+-----+-------+--------+
2 rows in set (0.00 sec)


-- 查询每门课的成绩的平均成绩
select * from course;
-- avg(degree)
select avg(degree) from score where cno='3-101';
select cno,avg(degree) from score group by cno;

-- 查询score表中至少有2名学生选修并且以3开头的课程的平均成绩
select cno,avg(degree) from score group by cno having count(cno)>=2 ;

-- 查询分数大于90 ，小于100的sno列
select degree,sno from score 
where degree>90 and degree<100;

select degree ,sno from score 
where degree between 90 and 100;


## 多表查询

### 两表关联查询
-- 查询所有学生的sname,cno和 degree列
select sname,cno,degree from student,score
where student.sno=score.sno;
mysql> select sname,cno,degree from student,score
    -> where student.sno=score.sno;
+-------+-------+--------+
| sname | cno   | degree |
+-------+-------+--------+
| 小绿  | 3-101 |     88 |
| 小黄  | 3-102 |     90 |
| 小红  | 3-103 |     91 |
| 小紫  | 3-104 |     92 |
| 小橙  | 3-101 |     93 |
| 小蓝  | 3-102 |     94 |
+-------+-------+--------+
6 rows in set (0.02 sec)

-- 查询所有学生的sno、cname和degree
select sno,cname,degree from course,score
where course.cno=score.cno;
mysql> select sno,cname,degree from course,score
    -> where course.cno=score.cno;
+-----+----------+--------+
| sno | cname    | degree |
+-----+----------+--------+
| 101 | 高等数学 |     88 |
| 102 | 线性代数 |     90 |
| 103 | 大学物理 |     91 |
| 104 | 大学英语 |     92 |
| 105 | 高等数学 |     93 |
| 106 | 线性代数 |     94 |
+-----+----------+--------+
6 rows in set (0.00 sec)
### 三表关联查询
-- 查询所有学生的sname、cname和degree列
select sname,cname,degree from student,course,score 
where student.sno=score.sno and course.cno=score.cno;

mysql> select sname,cname,degree from student,course,score
    -> where student.sno=score.sno and course.cno=score.cno;
+-------+----------+--------+
| sname | cname    | degree |
+-------+----------+--------+
| 小绿  | 高等数学 |     88 |
| 小黄  | 线性代数 |     90 |
| 小红  | 大学物理 |     91 |
| 小紫  | 大学英语 |     92 |
| 小橙  | 高等数学 |     93 |
| 小蓝  | 线性代数 |     94 |
+-------+----------+--------+
6 rows in set (0.00 sec)

## 子查询
-- 查询“95031”班每门课的平均分 
select * from student where class='95031';

select * from score where sno in
(select sno from student where class='95031');
mysql> select * from score where sno in
    ->  (select sno from student where class='95031');
+-----+-------+--------+
| sno | cno   | degree |
+-----+-------+--------+
| 101 | 3-101 |     88 |
| 107 | 3-104 |     97 |
+-----+-------+--------+

select cno,avg(degree)from score where sno in
(select sno from student where class='95031')
group by cno;
mysql> select cno,avg(degree)from score where sno in
    -> (select sno from student where class='95031')
    -> group by cno;
+-------+-------------+
| cno   | avg(degree) |
+-------+-------------+
| 3-101 |     88.0000 |
| 3-104 |     97.0000 |
+-------+-------------+
2 rows in set (0.00 sec)

## 子查询
-- 查询选修‘3-101’课程的成绩高于‘101’号学生的‘3-101’成绩的所有的同学的记录
select degree from score where sno ='101' and cno='3-101';

select * from score where degree>
(select degree from score where sno ='101' and cno='3-101') 
and cno='3-101';

mysql> select * from score where degree>
    -> (select degree from score where sno ='101' and cno='3-101')
    -> and cno='3-101';
+-----+-------+--------+
| sno | cno   | degree |
+-----+-------+--------+
| 105 | 3-101 |     93 |
+-----+-------+--------+
1 row in set (0.00 sec)

-- 查询和学号为‘101’、‘102’的学生同年出生的所有学生的sno,sname,sbirthday列
select year(sbirthday) from student where sno in(101,102);

select sno,sname,sbirthday from student 
where year(sbirthday) in 
(select year(sbirthday) from student where sno in(101,102));

-- 查询“小明”教师任课的学生成绩
select tno from teacher where tname='小明'；

select cno from course where tno 
=(select tno from teacher where tname='小明');

select * from score where cno 
=(select cno from course where tno 
=(select tno from teacher where tname='小明'));
mysql> select * from score where cno
    -> =(select cno from course where tno
    -> =(select tno from teacher where tname='小明'));
+-----+-------+--------+
| sno | cno   | degree |
+-----+-------+--------+
| 101 | 3-101 |     88 |
| 105 | 3-101 |     93 |
+-----+-------+--------+
2 rows in set (0.00 sec)

-- 查询选修某课程的同学人数多于3人的教师名称
select cno from score group by cno having count(*)>=3;
mysql> select cno from score group by cno having count(*)>=3;
+-------+
| cno   |
+-------+
| 3-101 |
+-------+
1 row in set (0.00 sec)

select tno from course where 
cno=(select cno from score group by cno having count(*)>=3);

select tname from teacher where 
tno=(select tno from course where 
cno=(select cno from score group by cno having count(*)>=3)
);
mysql> select tname from teacher where
    -> tno=(select tno from course where
    -> cno=(select cno from score group by cno having count(*)>=3)
    -> );
+-------+
| tname |
+-------+
| 小明  |
+-------+
1 row in set (0.00 sec)

## in表示或者关系
-- 查询95033班和95031班全体学生记录
select * from student where class in(95033,95031);
mysql> select * from student where class in(95033,95031);
+-----+-------+------+---------------------+-------+
| sno | sname | ssex | sbirthday           | class |
+-----+-------+------+---------------------+-------+
| 101 | 小绿  | 男   | 2000-01-01 00:00:00 | 95031 |
| 103 | 小红  | 男   | 2000-01-03 00:00:00 | 95033 |
| 107 | 小蓝1 | 男   | 2000-01-07 00:00:00 | 95031 |
+-----+-------+------+---------------------+-------+
3 rows in set (0.00 sec)

## where 条件查询
-- 查询存在85分以上的成绩的课程cno
select cno from score where degree>85;

## 子查询
-- 查询“计算机系”教师的所教课程的成绩表
select tno from teacher where depart='计算机系';

select cno from course 
where tno in(select tno from teacher where depart='计算机系');

select cno,degree from score 
where cno 
in(select cno from course 
where tno in(select tno from teacher where depart='计算机系'));

mysql> select cno,degree from score
    -> where cno
    -> in(select cno from course
    -> where tno in(select tno from teacher where depart='计算机系'));
+-------+--------+
| cno   | degree |
+-------+--------+
| 3-101 |     96 |
| 3-101 |     88 |
| 3-101 |     93 |
| 3-104 |     92 |
| 3-104 |     97 |
+-------+--------+
5 rows in set (0.00 sec)

 ## union(求并集) 和not in
 -- 查询“计算机系”与“电子工程系”不同职称的教师的tname和prof
insert into teacher values('805','玛丽','男','1990-01-05','副教授','电子工程系');

select prof from teacher where depart='电子工程系';
select * from teacher where depart='计算机系' and prof not 
in(select prof from teacher where depart='电子工程系');

mysql> select * from teacher where depart='计算机系' and prof not
    -> in(select prof from teacher where depart='电子工程系');
+-----+-------+------+---------------------+------+----------+
| tno | tname | tsex | tbirthday           | prof | depart   |
+-----+-------+------+---------------------+------+----------+
| 804 | 杰克  | 男   | 1990-01-04 00:00:00 | 助教 | 计算机系 |
+-----+-------+------+---------------------+------+----------+
1 row in set (0.00 sec)


select * from teacher where depart='电子工程系' and prof not 
in(select prof from teacher where depart='计算机系');

mysql> select * from teacher where depart='电子工程系' and prof not
    -> in(select prof from teacher where depart='计算机系');
+-----+-------+------+---------------------+------+------------+
| tno | tname | tsex | tbirthday           | prof | depart     |
+-----+-------+------+---------------------+------+------------+
| 802 | 丹尼  | 男   | 1990-01-02 00:00:00 | 教授 | 电子工程系 |
+-----+-------+------+---------------------+------+------------+
1 row in set (0.00 sec)

select * from teacher where depart='电子工程系' and prof not 
in(select prof from teacher where depart='计算机系')
union
select * from teacher where depart='计算机系' and prof not 
in(select prof from teacher where depart='电子工程系');

mysql> select * from teacher where depart='电子工程系' and prof not
    -> in(select prof from teacher where depart='计算机系')
    -> union
    -> select * from teacher where depart='计算机系' and prof not
    -> in(select prof from teacher where depart='电子工程系');
+-----+-------+------+---------------------+------+------------+
| tno | tname | tsex | tbirthday           | prof | depart     |
+-----+-------+------+---------------------+------+------------+
| 802 | 丹尼  | 男   | 1990-01-02 00:00:00 | 教授 | 电子工程系 |
| 804 | 杰克  | 男   | 1990-01-04 00:00:00 | 助教 | 计算机系   |
+-----+-------+------+---------------------+------+------------+
2 rows in set (0.01 sec)

-- 查询选修编号为“3-102”课程至少高于选修编号为“3-101”的同学的cno,sno,degree
-- 并且按照degree从高到低的顺序
select * from score where cno='3-101';

select * from score 
where cno='3-102' 
and degree > any(select degree from score where cno='3-101')
order by degree desc ;
+-----+-------+--------+
| sno | cno   | degree |
+-----+-------+--------+
| 106 | 3-102 |     94 |
| 102 | 3-102 |     90 |
+-----+-------+--------+
2 rows in set (0.00 sec)

## all所有

查询选修编号为“3-102”课程且高于选修编号为“3-101”的同学的cno,sno,degree
select * from score 
where cno='3-102' 
and degree > all(select degree from score where cno='3-101')
order by degree desc ;

## 取别名as,并集union
-- 查询所有的教师和同学的name msex,birthday
select tname as name,tsex as sex,tbirthday as birthday from teacher
union
select sname,ssex,sbirthday from student;


## 查询成绩比该课程平均分低的同学的成绩表
select avg(degree) from score group by cno;

select * from score a where degree <
(select avg(degree) from score b where a.cno=b.cno); 
+-----+-------+--------+
| sno | cno   | degree |
+-----+-------+--------+
| 101 | 3-101 |     88 |
| 102 | 3-102 |     90 |
| 104 | 3-104 |     92 |
+-----+-------+--------+
3 rows in set (0.00 sec)


## notlike模糊查询取反
-- 查询student 表中不姓‘小’的同学记录
select * from student where sname not like '小%';

## year函数与now函数
-- 查询student 表中每个学生的姓名和年龄
select year(now());

select year(sbirthday) from student;

select sname,year(now())-year(sbirthday) as age from student;

## 查询sudent最大值和最小的sbirthday
select max(sbirthday),min(sbirthday) from student order by sbirthday;

## 查询
-- 查询所有任课教师tanme和depart

select tname,depart from teacher 
where tno in(select tno from course);

-- 查询至少有两名男生的班号
select class from student where ssex='男' 
group by class having count(*)>1;

-- 以班号和年龄从小到大的顺序查询student表中的全部记录
select * from student order by class desc ,sbirthday;

-- 查询‘男’教师及其所上的课程
select tno from teacher where tsex='男';
select cname from course 
where tno 
in(select tno from teacher where tsex='男');

-- 查询最高分同学的sno,cno,degree;
select max(degree) from score;
select * from score where degree in(select max(degree) from score);

-- 查询和“小绿”性别相同的所有同学的sname

select * from student where ssex 
in (select ssex from student where sname='小绿');

-- 查询所有选修“高等数学”课程的“男”同学的成绩表


select * from score 
where cno 
in (select cno from course where cname ='高等数学')
and sno 
in (select sno from student where ssex='男');
+-----+-------+--------+
| sno | cno   | degree |
+-----+-------+--------+
| 101 | 3-101 |     88 |
+-----+-------+--------+

## 按等级查询
create table grade(
	low int(3),
	upp int(3),
	grade char(1)
);
insert into grade values(90,100,'A');
insert into grade values(80,89,'B');

-- 查询所有同学的sno，cno和garde列
select sno,cno,grade from score,grade where degree between low  and upp;