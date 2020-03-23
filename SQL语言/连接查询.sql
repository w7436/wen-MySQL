SQL的四种连接查询

内连接：
inner join 或者 join
外连接：
1、左连接 left join 或者 left outer join
2、右连接 right join 或者 right outer join

3、完全外连接 full join 或者 full outer join

-- 创建两个表

create table person(
	id int,
	name varchar(20),
	cardId  int
);
insert into person values(1,'小绿',1);
insert into person values(2,'小红',3);
insert into person values(3,'小蓝',6);

create table card (
	id int,
	name varchar(20)
);
insert into card values (1,'饭卡');
insert into card values (2,'建行卡');
insert into card values (3,'农行卡');
insert into card values (4,'工商卡');
insert into card values (5,'邮政卡');

-- 没有创建外键
-- 内联查询：其实就是两张表中的数据，通过某个字段相对，查询出相关记录数据
select * from person 
inner join card on person.cardId=card.id;


select * from person 
join card on person.cardId=card.id;

+------+------+--------+------+--------+
| id   | name | cardId | id   | name   |
+------+------+--------+------+--------+
|    1 | 小绿 |      1 |    1 | 饭卡   |
|    2 | 小红 |      3 |    3 | 农行卡 |
+------+------+--------+------+--------+

-- 2、left join（左外连接）:会把左边表中所有数据取出来，而右边的数据，如果有相等的就显示出来
-- 如果没有，就会是null

select * from person 
left join card on person.cardId=card.id;

select * from person 
left outer join card on person.cardId=card.id;

+------+------+--------+------+--------+
| id   | name | cardId | id   | name   |
+------+------+--------+------+--------+
|    1 | 小绿 |      1 |    1 | 饭卡   |
|    2 | 小红 |      3 |    3 | 农行卡 |
|    3 | 小蓝 |      6 | NULL | NULL   |
+------+------+--------+------+--------+

-- 3、right join
select * from person 
right outer join card on person.cardId=card.id;

select * from person 
right join card on person.cardId=card.id;

+------+------+--------+------+--------+
| id   | name | cardId | id   | name   |
+------+------+--------+------+--------+
|    1 | 小绿 |      1 |    1 | 饭卡   |
|    2 | 小红 |      3 |    3 | 农行卡 |
| NULL | NULL |   NULL |    2 | 建行卡 |
| NULL | NULL |   NULL |    4 | 工商卡 |
| NULL | NULL |   NULL |    5 | 邮政卡 |
+------+------+--------+------+--------+

-- 4、full join
select * from person full join card on person.cardId=card.id;
mysql 不支持 full join

select * from person 
left join card on person.cardId=card.id
union
select * from person 
right outer join card on person.cardId=card.id;

+------+------+--------+------+--------+
| id   | name | cardId | id   | name   |
+------+------+--------+------+--------+
|    1 | 小绿 |      1 |    1 | 饭卡   |
|    2 | 小红 |      3 |    3 | 农行卡 |
|    3 | 小蓝 |      6 | NULL | NULL   |
| NULL | NULL |   NULL |    2 | 建行卡 |
| NULL | NULL |   NULL |    4 | 工商卡 |
| NULL | NULL |   NULL |    5 | 邮政卡 |
+------+------+--------+------+--------+