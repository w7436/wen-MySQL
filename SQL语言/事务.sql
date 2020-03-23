MySQL事务

-- mysql中，事务其实就是一个最小的不可分割的工作单元。事务可以保证一个业务的完整性
-- 自动提交？@@autocommit=1
-- 手动提交？ commit
-- 事务回滚？ rollback
-- mysql中如何控制事务
1、mysql 默认是开启事务的

mysql> select @@autocommit;
+--------------+
| @@autocommit |
+--------------+
|            1 |
+--------------+
1 row in set (0.00 sec)

-- 默认事务开启的作用是什么？？
-- 当我们去执行一个sql语句的时候，效果会立即体现出来，且不能回滚
create table user(
	id int primary key,
	name varchar(20),
	money int
);

insert into user values (1,'q',1000);

--事务回滚：撤销sql语句执行的效果
rollback; 

mysql> rollback;
Query OK, 0 rows affected (0.01 sec)

mysql> select * from user;
+----+------+-------+
| id | name | money |
+----+------+-------+
|  1 | q    |  1000 |
+----+------+-------+
1 row in set (0.00 sec)

-- 设置mysql自动提交为false
set autocommit=0;

mysql> select @@autocommit;
+--------------+
| @@autocommit |
+--------------+
|            0 |
+--------------+
1 row in set (0.00 sec)

insert into user values(2,'a',2000);

	mysql> select * from user;
+----+------+-------+
| id | name | money |
+----+------+-------+
|  1 | q    |  1000 |
+----+------+-------+
1 row in set (0.00 sec)

-- 再一次插入数据
mysql> insert into user values(2,'a',2000);
Query OK, 1 row affected (0.00 sec)

-- 手动提交数据
mysql> commit;
Query OK, 0 rows affected (0.01 sec)

-- 再撤销是不可以撤销的
mysql> rollback;
Query OK, 0 rows affected (0.00 sec)

mysql> select * from user;
+----+------+-------+
| id | name | money |
+----+------+-------+
|  1 | q    |  1000 |
|  2 | a    |  2000 |
+----+------+-------+
2 rows in set (0.00 sec)


begin;
-- 或者 
start transaction ;
-- 都可以帮助我们手动开启一个事务
-- 事务开启之后，一旦commit提交，就不可以回滚（也就是说当前的这个事务在提交的时候就结束了），

事务的四大特征
A 原子性：事务是最小的单位，不可以分割
B 一致性：事务要求，同一事务中的 SQL 语句，必须同时成功或者失败
C 隔离性：之间具有隔离性
D 持久性：事务一旦结束（commit,rollback）,就不可以返回


事务开启：
1、修改默认提交 set autocommit=0;
2、begin；
3、start transaction;

事务提交：commit；
事务手动回滚：rollback；


C 隔离性：之间具有隔离性

1、read uncommitted;---------读未提交的
2、read committes;-----------读已经提交的
3、repeatable read;----------可以重复化
4、serializable;-------------串行化

1-read uncommitted;
如果有事务a,和事务b,
a事务对数据进行操作，在操作的过程中，事务没有被提交，但是b可以看见a的操作的结果

insert into user values (3,'小明','1000');
insert into user values (4,'淘宝','1000');

mysql> select * from user;
+----+------+-------+
| id | name | money |
+----+------+-------+
|  1 | q    |  1000 |
|  2 | a    |  2000 |
|  3 | 小明 |  1000 |
|  4 | 淘宝 |  1000 |
+----+------+-------+

-- 如何查看数据库的隔离几级别

mysql 8.0
-- 系统级别
select @@global.transaction_isolation;
-- 会话级别
select @@transaction_isolation;

-- mysql 默认级别 REPEATABLE-READ 
mysql> select @@global.transaction_isolation;

+--------------------------------+
| @@global.transaction_isolation |
+--------------------------------+
| REPEATABLE-READ                |
+--------------------------------+
1 row in set (0.00 sec)

mysql 5.x
select @@global.tx_isolation;
select @@tx_isolation;

-- 修改隔离级别？
set global transaction isolation level read uncommitted;

mysql> set global transaction isolation level read uncommitted;
Query OK, 0 rows affected (0.00 sec)

mysql> select @@global.transaction_isolation;
+--------------------------------+
| @@global.transaction_isolation |
+--------------------------------+
| READ-UNCOMMITTED               |
+--------------------------------+


-- 转账：小明买800鞋子
mysql> select * from user;
+----+------+-------+
| id | name | money |
+----+------+-------+
|  1 | q    |  1000 |
|  2 | a    |  2000 |
|  3 | 小明 |  1000 |
|  4 | 淘宝 |  1000 |
+----+------+-------+

start transaction;
update set money=money-800 where name='小明';
update set money=money+800 where name='淘宝';

mysql> select *from user;
+----+------+-------+
| id | name | money |
+----+------+-------+
|  1 | q    |  1000 |
|  2 | a    |  2000 |
|  3 | 小明 |   200 |
|  4 | 淘宝 |  1800 |
+----+------+-------+

-- 淘宝

mysql> select *from user;
+----+------+-------+
| id | name | money |
+----+------+-------+
|  1 | q    |  1000 |
|  2 | a    |  2000 |
|  3 | 小明 |   200 |
|  4 | 淘宝 |  1800 |
+----+------+-------+

-- 小明 
rollback;
mysql> select * from user;
+----+------+-------+
| id | name | money |
+----+------+-------+
|  1 | q    |  1000 |
|  2 | a    |  2000 |
|  3 | 小明 |  1000 |
|  4 | 淘宝 |  1000 |
+----+------+-------+


-- 淘宝花费1800,发现钱不够
mysql> select * from user;
+----+------+-------+
| id | name | money |
+----+------+-------+
|  1 | q    |  1000 |
|  2 | a    |  2000 |
|  3 | 小明 |  1000 |
|  4 | 淘宝 |  1000 |
+----+------+-------+


-- 如果两个不同的地方，都在进行操作，如果事务a开启，他的数据可以被其他事务读取到
-- 这样就会出现（脏读）
-- 脏读：一个数据读到了另一个事务没有提交的数据，就叫做脏读


2-read committed; 读已经提交的

-- 修改隔离级别
set global transaction isolation level read committed;

select @@global.transaction_isolation;


小张：
start transaction;
select * from user;

mysql> start transaction;
Query OK, 0 rows affected (0.00 sec)

mysql> select * from user;
+----+------+-------+
| id | name | money |
+----+------+-------+
|  1 | q    |  1000 |
|  2 | a    |  2000 |
|  3 | 小明 |  1000 |
|  4 | 淘宝 |  1000 |
+----+------+-------+
4 rows in set (0.00 sec)



小王：

start transaction;
insert into user values(5,'c',100);
commit;

mysql> select * from user;
+----+------+-------+
| id | name | money |
+----+------+-------+
|  1 | q    |  1000 |
|  2 | a    |  2000 |
|  3 | 小明 |  1000 |
|  4 | 淘宝 |  1000 |
|  5 | c    |   100 |
+----+------+-------+
5 rows in set (0.00 sec)

-- 小张回来后,对数据库进行操作
select avg(money) from user; 

mysql> select avg(money) from user;
+------------+
| avg(money) |
+------------+
|  1020.0000 |
+------------+
1 row in set (0.00 sec)

-- 平均值不是1250，变少了


-- 虽然我只能读到另一个事务提交的数据，但还是会出问题，就是读取同一个表中的数据，发现前后不一致 
-- 不可重复的现象


3-repeatable read 可以重复读

set global transaction isolation level repeatable read;

select @@global.transaction_isolation;


-- 在这种隔离模式下出现的问题
 select * from user;
 mysql>  select * from user;

+----+------+-------+
| id | name | money |
+----+------+-------+
|  1 | q    |  1000 |
|  2 | a    |  2000 |
|  3 | 小明 |  1000 |
|  4 | 淘宝 |  1000 |
|  5 | c    |   100 |
+----+------+-------+
5 rows in set (0.00 sec)

-- 张
start transaction;


-- 王
start transaction;

-- 张
insert into user values(6,'d',1000);

-- 王
select * from user；
+----+------+-------+
| id | name | money |
+----+------+-------+
|  1 | q    |  1000 |
|  2 | a    |  2000 |
|  3 | 小明 |  1000 |
|  4 | 淘宝 |  1000 |
|  5 | c    |   100 |
+----+------+-------+

-- 张
commit；

-- 王
mysql> select * from user;
+----+------+-------+
| id | name | money |
+----+------+-------+
|  1 | q    |  1000 |
|  2 | a    |  2000 |
|  3 | 小明 |  1000 |
|  4 | 淘宝 |  1000 |
|  5 | c    |   100 |
+----+------+-------+
5 rows in set (0.00 sec)

insert into user values(6,'d',1000);
mysql> insert into user values(6,'d',1000);
ERROR 1062 (23000): Duplicate entry '6' for key 'PRIMARY'

-- 这种现象就叫做幻读
-- 事务a操作和事务b同时操作一张表，事务a提交的数据，也不能被事务B读到，就会造成幻读



4-serializable;-------------串行化

set global transaction isolation level serializable;

select @@global.transaction_isolation;

+----+------+-------+
| id | name | money |
+----+------+-------+
|  1 | q    |  1000 |
|  2 | a    |  2000 |
|  3 | 小明 |  1000 |
|  4 | 淘宝 |  1000 |
|  5 | c    |   100 |
|  6 | d    |  1000 |
+----+------+-------+

-- 张
start transaction;


-- 王
start transaction;

-- 张
insert into user values(7,'你好',1000);
commit；
select * from user;
+----+------+-------+
| id | name | money |
+----+------+-------+
|  1 | q    |  1000 |
|  2 | a    |  2000 |
|  3 | 小明 |  1000 |
|  4 | 淘宝 |  1000 |
|  5 | c    |   100 |
|  6 | d    |  1000 |
|  7 | 你好 |  1000 |
+----+------+-------+

-- 王
select * from user;
+----+------+-------+
| id | name | money |
+----+------+-------+
|  1 | q    |  1000 |
|  2 | a    |  2000 |
|  3 | 小明 |  1000 |
|  4 | 淘宝 |  1000 |
|  5 | c    |   100 |
|  6 | d    |  1000 |
|  7 | 你好 |  1000 |
+----+------+-------+

-- 张
start transaction;

-- sql语句被卡住了：由于王没有commit，在提交的瞬间，就插入成功
insert into user values(8,'王小花',1000);
 
-- 当user表被另一个事务操作的时候，其他事务里面的写操作，是不可以进行的
-- 进入排队状态，知道另一个事务结束之后，其他事务这个写操作才会执行
-- 在没有等待超时的情况下

-- 王
commit；

-- 张
Query OK, 0 rows affected (0.00 sec)



1、read uncommitted  >  read committes > repeatable read > serializable


-- 串行化的问题是，性能特别差
-- 隔离级别越高，性能越差
