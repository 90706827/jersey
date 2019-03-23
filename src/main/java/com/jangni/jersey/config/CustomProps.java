package com.jangni.jersey.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @ClassName AutoProps
 * @Description 加载自定义数据库配置属性
 * @Author Mr.Jangni
 * @Date 2019/3/22 19:43
 * @Version 1.0
 **/
@Component
@ConfigurationProperties(prefix = "db.tomcat")
public class CustomProps {

    private String driverClassName;
    private String url;
    private String username;
    private String password;
    private String maxActive;
    private String maxIdle;
    private String defaultAutoCommit;
    private String timeBetweenEvictionRunsMillis;
    private String minEvictableIdleTimeMillis;
    private String testOnBorrow;
    private String validationQuery;
    private String maxWait;
    private String validationQueryTimeout;
    private String validationInterval;

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMaxActive() {
        return maxActive;
    }

    public void setMaxActive(String maxActive) {
        this.maxActive = maxActive;
    }

    public String getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(String maxIdle) {
        this.maxIdle = maxIdle;
    }

    public String getDefaultAutoCommit() {
        return defaultAutoCommit;
    }

    public void setDefaultAutoCommit(String defaultAutoCommit) {
        this.defaultAutoCommit = defaultAutoCommit;
    }

    public String getTimeBetweenEvictionRunsMillis() {
        return timeBetweenEvictionRunsMillis;
    }

    public void setTimeBetweenEvictionRunsMillis(String timeBetweenEvictionRunsMillis) {
        this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
    }

    public String getMinEvictableIdleTimeMillis() {
        return minEvictableIdleTimeMillis;
    }

    public void setMinEvictableIdleTimeMillis(String minEvictableIdleTimeMillis) {
        this.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
    }

    public String getTestOnBorrow() {
        return testOnBorrow;
    }

    public void setTestOnBorrow(String testOnBorrow) {
        this.testOnBorrow = testOnBorrow;
    }

    public String getValidationQuery() {
        return validationQuery;
    }

    public void setValidationQuery(String validationQuery) {
        this.validationQuery = validationQuery;
    }

    public String getMaxWait() {
        return maxWait;
    }

    public void setMaxWait(String maxWait) {
        this.maxWait = maxWait;
    }

    public String getValidationQueryTimeout() {
        return validationQueryTimeout;
    }

    public void setValidationQueryTimeout(String validationQueryTimeout) {
        this.validationQueryTimeout = validationQueryTimeout;
    }

    public String getValidationInterval() {
        return validationInterval;
    }

    public void setValidationInterval(String validationInterval) {
        this.validationInterval = validationInterval;
    }
}
