package com.jangni.jersey.core;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

/**
 * @ClassName TaskExecutors
 * @Description
 * @Author Mr.Jangni
 * @Date 2019/3/29 0:14
 * @Version 1.0
 **/
public class TaskExecutors {

    public static ThreadPoolTaskExecutor pool = getThreadPoolTaskExecutor();

    private static ThreadPoolTaskExecutor getThreadPoolTaskExecutor() {
        pool = new ThreadPoolTaskExecutor();
        pool.setCorePoolSize(4);
        pool.setMaxPoolSize(8);
        pool.setKeepAliveSeconds(10);
        pool.setAllowCoreThreadTimeOut(true);
        pool.setThreadNamePrefix("taks-jangni-");
        pool.afterPropertiesSet();
        return pool;
    }
}