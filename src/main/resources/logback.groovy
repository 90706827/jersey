import ch.qos.logback.classic.encoder.PatternLayoutEncoder

import java.nio.charset.Charset

//根路径
//根路径
//String user_home = System.getProperty("user.home")
//String logPath = "${user_home}/log"
String appPath = "E:/logs/"
//日志输出前置统一格式
String commonPattern = "BaseLog %d{yyyy-MM-dd HH:mm:ss SSS}|%-5level|%-30thread|%logger{36}|%msg%n"

// 基础日志文件配置
appender("base", RollingFileAppender) {
    //日志文件路径
    file = appPath + "base.log"
    encoder(PatternLayoutEncoder) {
        //日志记录信息内容以及格式
        pattern = commonPattern
        charset = Charset.forName("UTF-8")
    }
    //分割日志策略，按时间分割
    rollingPolicy(TimeBasedRollingPolicy) {
        //被分割的日志的路径
        fileNamePattern = appPath + "%d{yyyyMMdd, aux}/base_%d{HH}.log"
        //分割日志的时间，由fileNamePattern决定单位，此处为小时
        maxHistory = 840
    }
}


appender("console", ConsoleAppender) {
    encoder(PatternLayoutEncoder) {
        pattern = commonPattern
    }
}

//参数分别为类名，日志级别，日志文件，是否让root接收默认为true
logger("base", INFO, ["base"], true)
root(INFO, ["base","console"])
