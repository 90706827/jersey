package com.jangni.jersey.core;

import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executors;

/**
 * @ClassName BaseService
 * @Description
 * @Author Mr.Jangni
 * @Date 2019/3/31 14:36
 * @Version 1.0
 **/
public class BaseService {
    protected ThreadPoolTaskExecutor pool = getThreadPoolTaskExecutor();
    protected ListeningExecutorService executorService = MoreExecutors.listeningDecorator(Executors.newCachedThreadPool());
    private ThreadPoolTaskExecutor getThreadPoolTaskExecutor() {
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