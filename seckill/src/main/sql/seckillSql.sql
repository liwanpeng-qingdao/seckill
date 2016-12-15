-- 数据库初始化脚本
-- mysql的用户增加：登录root用户：mysql -uroot -p >>>>输入密码>>>>mysql：
-- 1.用户创建：create user'lwp'@'localhost'IDENTIFIED BY'123456';
-- 2.赋权限：GRANT ALL ON *.* TO 'lwp'@'%';
-- 3.刷新权限：flush privileges;
-- 登录lwp用户，创建数据库:
-- 需要注意的是，comment关键字后没有"="
 CREATE DATABASE seckill;
-- 使用数据库
  USE seckill;
-- 创建秒杀表:习惯使用mysql的命令行来创建表，索引的创建
CREATE TABLE seckill(
`seckill_id` bigint  NOT NULL  AUTO_INCREMENT COMMENT '秒杀id',
`name` varchar(120) NOT NULL  COMMENT '秒杀名称',
`number` int NOT NULL COMMENT '秒杀库存数量',
`start_time` TIMESTAMP NOT NULL  COMMENT  '秒杀开始时间',
`end_time` TIMESTAMP  NOT NULL  COMMENT  '秒杀结束时间',
`create_time` TIMESTAMP NOT NULL DEFAULT  current_timestamp COMMENT  '秒杀创建时间',
PRIMARY  KEY (seckill_id),
KEY idx_start_time(start_time),
KEY  idx_end_time(end_time),
KEY  idx_create_time(create_time)
)ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT  CHARSET=utf8 COMMENT '秒杀库存表';
-- 插入数据
INSERT seckill(name,number,start_time,end_time) VALUES
 ('1000元秒杀iPhone6',800,'2016-12-10 00:00:00','2016-12-10 00:00:00'),
 ('500元秒杀iPad2',500,'2016-12-10 00:00:00','2016-12-10 00:00:00'),
 ('300元秒杀小米4',300,'2016-12-10 00:00:00','2016-12-10 00:00:00'),
('200元秒杀红米Not',1000,'2016-12-10 00:00:00','2016-12-10 00:00:00')
-- 秒杀记录表
CREATE TABLE success_seckill(
`seckill_id` BIGINT NOT NULL COMMENT '秒杀的商品id',
 `user_phone` BIGINT NOT NULL  COMMENT '秒杀用户的手机号',
 `state` TINYINT NOT NULL DEFAULT -1 COMMENT '此秒杀记录的状态',/*-1:无效，0：成功，1：已付款*/
 `create_time` TIMESTAMP NOT NULL  COMMENT '秒杀成功的时间',
 PRIMARY KEY (seckill_id,user_phone),/*联合主键,防止同一用户的重复秒杀*/
 KEY idx_create_time (create_time)

)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='秒杀成功表'