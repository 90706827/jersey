# springboot 集成 Jersey
## 1、Jersey 简介
​	开发RESTful WebService意味着支持在多种媒体类型以及抽象底层的客户端-服务器通信细节，如果没有一个好的工具包可用，这将是一个困难的任务。

​	为了简化使用JAVA开发RESTful WebService及其客户端，一个轻量级的标准被提出：JAX-RS API。

​	Jersey RESTful WebService框架是一个开源的、产品级别的JAVA框架，支持JAX-RS API并且是一个JAX-RS(JSR 311和 JSR 339)的参考实现。

​	Jersey不仅仅是一个JAX-RS的参考实现，Jersey提供自己的API，其API继承自JAX-RS，提供更多的特性和功能以进一步简化RESTful service和客户端的开发。

## 2、Jersey 优点

- 开源框架
- 简单，轻量级
- 高性能
- 稳定，可靠
- 易于开发和维护
- 异步处理

## 3、Jersey实例

采用springboot框架集成Jersey

### 3.1、Tomcat 连接池配置 

配置tomcat连接池，采用JdbcTemplate 编写纯sql操作数据，对于交易类采用纯sql操作数据可以提高性能。

### 3.2、 配置过滤器 拦截器

Request、Response过滤器，RequestReader、ResponseWriter连接器

### 3.3、全局异常处理

实现ExceptionMapper全局异常处理，自定义Exception异常类，此全局异常解耦性强，方便制定。

### 3.4、AsyncResponse 异步技术

使用异步技术可以大幅提高吞吐量

### 3.5、Jersey分布式系统中高可用

后续增加

### 3.6、实例

GET、POST、表单提交、文件上传下载等。

