声明： 这个版本是从下面博客的地址发展而来，添加我自己总结的。 bms 作者您好， 如有版权，请联系我

# bms 后台管理系统
后台管理系统权限控制，对user、role、menu（通常菜单和按钮）进行增删改查。
springboot+shiro+freemarker+ace admin+mybatis+mysql+java1.8

ace admin是前端框架（bootstrap，jquery）

使用说明
1. 执行bms_demo.sql，初始化本地数据库
2. 浏览器启动localhost:8010/admin/login
3. 用户名admin，密码123456。或到数据库自行查找用户

具体图文简介请到下面地址查看
https://blog.csdn.net/bjjoy2009/article/details/80428486


/////
plan
1, 刷新所有泵站数据，需要与刘奇山商量两个地方： 
	(1)8082 和 8084 
	(2)权限问题，当前操作人下管辖的泵站的数据。多用户的问题
	(3)更新数据的泵站信息不全的问题...批量更新，导入导出
2, 告警信息
3, 地图的信息
4, 增加邮箱提醒，新泵站加入消息


//定时任务表达式在线生成器
http://cron.qqe2.com/

