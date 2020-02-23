# 基于HBase的网站日志数据分析系统
  
## 系统说明

### 1. 数据库设计

#### LogData

- 该表用于存储经数据清洗、转化后的数据
- 数据库类型： HBase
- 表结构

 Rowkey|         prop              |
 ----|:---------------------------:|
 |	rowkey  | IP	/ BYTES / URL / DATES / METHOD / FYDM / BYTES|
- RowKey 结构设计说明
>  RowKey 分为 日期 + 网站代码后三位 + 六位数ID  
>  各部分说明如下：   

 字段 |  解释 | 例子
----| ----- |----
日期 |日志文件的产生日期(纯数字，不含空格和-) | 20170808
公司代码| 公司代码后三位 |200
ID | 从100000开始的六位数字，用于唯一的标明数据并对齐 | 100001 
> 完整例子  
> 201708082001000000 表示代号为200点公司在2017-08-08产生的一次请求
	 
- 建表语句
> create "LogData", "prop"

-

#### LogAna
- 该表用于存储分析之后的数据
- 数据库类型： HBase
- 表结构 

	RowKey | IP | URL | BYTES | MTHOD_STATE |REQ
	-------|----|-----|-------|-------------|---
	rowkey |IPSumVal  IPTotalNum  IPList |URLList  MaxURL | BytesSecList  BytesHourList /  TotalBytes  | MethodList StateList | ReqHourList ReqSecList ReqSum
- 字段说明

	字段 |  解释 | 例子
	 ----| ----- |----
	 IPTotalNum| IP总数，不包含重复的 | 100 表示当天有100个IP访问网站
	 IPSumVal | IP总数，包含重复 | 100表示有100个IP访问网站，IP可重复
	 IPList | IP和对应访问量的排行，结构为 由mutable.Map[String, Int]转变来的JSON文件| {"190.1.1.1"： 1000}  表示190.1.1.1的IP共在网站产生1000次请求）
	 URLList | 被请求次数最多的10个URL，结构为Json | {"test.aj":100, "test2.aj":90, ...}
	 MaxURL | 请求次数最多的URL(现在前端已经放弃使用这字段) |{"test.aj": 100}
	 BytesSecList | 统计每秒内产生的流量，单位为Byte,但是前端展示时转化为MB | {"2017-08-08 01:00:00":9000， "2017-08-08 01:00:00": 500, ...}
	 BytesHourList | 统计一天内每小时内产生的流量，单位为Byte,但是前端展示时转化为MB | {"08":9000， "09": 500, ...}， 08 表示 8点到9点内产生的流量
	 TotalBytes | 一天内产生的总流量大小,单位为Byte,但是前端展示时转化为MB | 3000, 表示当天产生 3000b bytes的流量
	 MethodList | 出现过的请求方法统计 | {"POST":3446,"OPTIONS":5,"HEAD":4}
	 StateList | 出现过的请求状态中级 | {"501":8,"302":801,"404":1,"200":14738,"400":2,"405":4}
	 ReqHourList | 按小时统计请求次数 | {"15":2350,"09":3503,"00":690,"11":1903}
	 ReqSecList | 按秒统计请求次数 | {"2017-08-08 10:44:08":1,"2017-08-08 09:45:05":4,"2017-08-08 10:06:58":4}
	 ReqSum | 一天内总请求次数 | 1000，表示当天内共有1000次请求
	 
- RowKey 结构设计说明
>  RowKey 分为 日期 + 公司代码后三位
>  各部分说明如下：   

		字段 |  解释 | 例子
		 ----| ----- |----
	 	日期 |日志文件的产生日期(纯数字，不含空格和-) | 20170808
	 	公司代码| 公司代码后三位 |200， 需要注意的是000表示当天所有网站数据
	 
	> example:
	20170808200 表示天津高院在2017-08-08的所有数据
	20170808000 表示所有法院在2017-08-08点所有数据
	 
- 建表语句  
> create "LogAna", "IP", "URL", "BYTES", "METHOD_STATE", "REQ"


### 2. 项目代码描述
- 本项目分为三个子项目， 包括数据采集、数据存储和展示、数据离线分析   

#### 数据采集

- 工程名：CollectTomcatLogs
- 功能说明：   

	> 收集指定路径下的tomcat日志  
	> 重命名文件之后上传到HDFS或FTP服务器   
	> 保存日志，记录是否上传成功
 
- 部署说明： 部署在各个需要采集日志的服务器上，在 my.properties 里指定公司代码和日志路径
- 配置管理： maven
- 主要技术： Java FTPClient, HDFS
- 测试用例说明： 主要用于测试重命名后的文件是否正常
- 文件重命名： 在localhost_XXXXX.txt文件前加上法院代码，以此来区分各公司数据

#### 数据存储和展示
- 工程名： RestoreData
- 功能说明：

	> 数据预处理： 包括数据解析、清洗和转化  
	> 数据存储： 将转化后的数据保存在一个List中，批量插入HBase数据库  
	> 前端展示：展示分析得到的数据  
	> 数据查询： 根据各种输入条件查询对应的数据
- 开发环境： 
	> JDK 8   
	> Hadoop 2.7   
	> Hbase 1.2
	> tomcat 8   
- 部署说明:在 my.properties 里配置好各项数据,注意JDK、Hadoop的版本兼容
- 配置管理： maven
- 主要技术：Spring MVC / Hadoop / JSP
- 测试用例说明：
	> HbaseBatchInsertTest.java: 用于测试批量插入  
	> HbaseConnectionTest.java: 用于测试Hbase连接是否正常  
	> ParseLogTest.java: 用于测试日志解析  
	> ListBean.java: 打印所有的bean，用于应付@Autowried失败的情况  
- 前端部分：  
	>####代码部分
	> index.jsp： 默认加载页面，加载完成后会请求数据，展示前一天所有网站数据  
	> index.js： 用于处理index.jsp中的各种请求和数据解析
	
	> ---------- 
	> queryData.jsp: 用于查询各家网站数据，输入为日期 + 网站，支持多选  
	> queryData.js: 用于处理queryData.jsp中的各种请求和数据解析（待完成）
	
	> ---------
	> dataGrid.jsp: 以表格的形式展示数据（待完成）
	
	> --------
	> myCharts.js: 使用echarts绘制各种图表(注意dom的初始化在外部完成）  
	> inputCheck.js: 检查输入是否合法
	
	>---------
	> mystyle.css: 定制各类样式
	>####第三方库
	> Bootstrap: 主要是用其格栅系统  
	> Bootstrap-select: 多选框的实现  
	> BootstrapDatepickr: 日期输入  
	> echarts: 绘制各类图表  
	> jQuery: 框架  
	> font-awesome: 各类小图标

	
#### 数据离线分析

- 工程名： ScalaReadAndWrite
- 功能说明：

	> 离线分析各类数据，共13个指标，详情见数据库表 LogAna 设计
	
- 开发环境：
	> Scala 2.11  
	> Spark 1.52  
	> Hadoop 2.7  
- 特别说明：
	> spark 中全局变量只有两种实现方式，广播变量或累加器，本项目使用累加器  
	> 自定义的累加器的时候非常一定要注意输入输出类型一定要正确
	> 一定要实现全部六个重载函数  
	> 一个累加器只能传递一种变量，这个变量可以是复杂的对象  
	> 不这样做的话累加器会失效！
- 部署说明：暂无
- 配置管理：maven
- 主要技术：Spark
- 项目结构说明：
	> Accumulator:累加器，包含各种自定义的累加器  
	> analysis: 主要分析代码
	> DAO: 解析实体类，并存入到HBase  
	> Entity： 两个实体类  
	> util: 各种工具类
	
	#### 3. 项目截图：
	
	- Hbase数据库截图  
	![image](https://github.com/Maicius/WebLogsAnalysisSystem/blob/master/image/p2.png)
	  
	- 数据展示界面  
	![image](https://github.com/Maicius/WebLogsAnalysisSystem/blob/master/image/p1.png)  
	
	- 数据展示界面  
	![image](https://github.com/Maicius/WebLogsAnalysisSystem/blob/master/image/p3.png)

	


	
	


	 

 
 
 
