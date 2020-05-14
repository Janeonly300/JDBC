# JDBC就是一套接口、
## 数据的持久化
- 把数据保存到可掉电的存储设备以供使用。而持久的实现过程大多通过关系数据库来完成；
- 持久化的主要应用将内存中的数据存储在关系数据库当中。


## JDBC介绍
- ==通用的SQL，就是一个公用的接口。==
- API (java.sql javax.sql)
---
## 我们认为的数据库碱性
### 如果没有JDBC

```
graph LR
JAVA-->MySQL
JAVA-->Oracle
JAVA-->SQLServer
JAVA-->DB2
```
### 有的JDBC之后

```
graph LR
JAVA-->JDBC
JDBC-->MySQL
JDBC-->Oracle
JDBC-->SQLServer
JDBC-->DB2
```
#### 真实的连接

```
graph TD
A[java程序]-->B(JDBC)
B-->C{不同数据库厂商驱动jar}
C-->Mysql
C-->Orcal
C-->DB2
C-->SQLServer
```





### method
- ==PreparedStatement==
    - ResultSet executeQuery(); //返回一个结果集
- ==ResultSet==
    - boolean next() //判断结果集下一条是否由数据，如果由数据返回true，并指针下移，如果返回false结束
    - Object getObject() //提取字段

-==ORM编程思想(Object relational mapping)==
 * 一个数据表对应一个java类
 * 记录对应对象
 * 表中字段对应属性
 
 - ==[RestSetMetaData]== 
- MetaData getMetaData(); //获取元数据 [ResultSet]
- int getSColumCount(); //获取结果集当中的字段数
- String getColumName(); //获取字段的名字；
- String getColumLabel(); //获取字段的别名 推荐使用
