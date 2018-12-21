# Spring 
Spring 是一个轻量级的控制反转（ IoC ）和面向切面（ AOP ）的容器框架。[Spring 学习链接](https://www.yiibai.com/spring/)
# SpringMVC
Spring web MVC框架提供了MVC(模型 - 视图 - 控制器)架构和用于开发灵活和松散耦合的Web应用程序的组件。 MVC模式导致应用程序的不同方面(输入逻辑，业务逻辑和UI逻辑)分离，同时提供这些元素之间的松散耦合。[SpringMVC 学习链接](https://www.yiibai.com/spring_mvc/springmvc_overview.html)
# MyBatis
MyBatis 是支持普通 SQL 查询,存储过程和高级映射的优秀持久层框架。[MyBatis 学习链接](https://www.yiibai.com/mybatis/)
# MySql8
数据库使用mysql8进行存贮
# Jar包
jar包使用Maven进行管理[Maven Repository](https://mvnrepository.com/)
# IDE
IntelliJ IDEA 2018.2.2 x64
# 开发平台
windows10 64位
# 已包含技术
SSM框架搭建
MyBatis逆向生成dao代码、mapper资源
MyBatis连表查询
aop事务配置和使用
log4j2日志配置集成
数据库分页查询
测试代码集成
文件上传集成
....
# Tomcat内存配置
服务器内存 1G
    set JAVA_OPTS=-Xms256m -Xmx512m

服务器内存 8G，一般 PermSize 配置是主要保证系统能稳定起来就行：

   SET "JAVA_OPTS=-server-Xms6144m -Xmx6144m -XX:NewSize=1024m -XX:MaxNewSize=2048m -XX:PermSize=512m -XX:MaxPermSize=512m -XX:MaxTenuringThreshold=10 -XX:NewRatio=2-XX:+DisableExplicitGC" 

服务器内存 16G，一般 PermSize 配置是主要保证系统能稳定起来就行：

    SET "JAVA_OPTS=-server -Xms13312m -Xmx13312m -XX:NewSize=3072m -XX:MaxNewSize=4096m -XX:PermSize=512m -XX:MaxPermSize=512m -XX:MaxTenuringThreshold=10 -XX:NewRatio=2-XX:+DisableExplicitGC"

服务器内存 32G，一般 PermSize 配置是主要保证系统能稳定起来就行：

    SET "JAVA_OPTS=-server-Xms29696m -Xmx29696m -XX:NewSize=6144m -XX:MaxNewSize=9216m -XX:PermSize=1024m -XX:MaxPermSize=1024m -XX:MaxTenuringThreshold=10 -XX:NewRatio=2-XX:+DisableExplicitGC"

 注意：如果服务器内存是8G，catalina.bat 中配置了16G的配置，将造成Tomcat无法启动。