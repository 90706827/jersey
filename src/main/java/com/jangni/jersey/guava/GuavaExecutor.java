package com.jangni.jersey.guava;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.util.concurrent.*;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import javax.annotation.Nullable;
import java.util.List;
import java.util.concurrent.*;

/**
 * @ClassName GuavaExecutor
 * @Description Guava 线程池
 * @Author Mr.Jangni
 * @Date 2019/3/30 17:11
 * @Version 1.0
 **/
public class GuavaExecutor {
    //真正干活的线程池
    ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(
            5,
            5,
            0,
            TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(100),
            new CustomizableThreadFactory("demo"),
            new ThreadPoolExecutor.DiscardPolicy());

    public static void main(String[] args) {
//        GuavaExecutor executor = new GuavaExecutor();
//        executor.test4();
        ListeningExecutorService executorService = MoreExecutors.listeningDecorator(Executors.newCachedThreadPool());
        final ListenableFuture<Integer> listenableFuture = executorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println("call execute..");
                TimeUnit.SECONDS.sleep(1);
                return 7;
            }
        });
        Futures.allAsList();
        Futures.addCallback(listenableFuture, new FutureCallback<Integer>() {
            @Override
            public void onSuccess(@Nullable Integer integer) {
                System.out.println(integer);
            }

            @Override
            public void onFailure(Throwable throwable) {
                throwable.printStackTrace();
            }
        },Executors.newCachedThreadPool());

        RateLimiter limiter = RateLimiter.create(5.0);// 每秒不超过4个任务被提交

        for (int i = 0; i < 1; i++) {
            limiter.acquire(); // 请求RateLimiter, 超过permits会被阻塞
            System.out.println(i);
        }
    }

    public void test3() {
        ListeningExecutorService executorService = MoreExecutors.listeningDecorator(Executors.newCachedThreadPool());
        // 执行任务
        final ListenableFuture<Integer> listenableFuture = executorService.submit(new Callable<Integer>() {
            public Integer call() throws Exception {
                System.out.println("新任务。。。");
                TimeUnit.SECONDS.sleep(1);
                return 7;
            }

        });
        // 任务完成回掉函数
        final FutureCallback<Integer> futureCallback = new FutureCallback<Integer>() {
            @Override
            public void onSuccess(Integer result) {
                System.out.println("任务执行成功，对任务进行操作。");
            }

            @Override
            public void onFailure(Throwable t) {
                System.out.println("任务执行失败。");
            }
        };

        // 绑定任务以及回调函数
        Futures.addCallback(listenableFuture, futureCallback, poolExecutor);
    }

    public void test1() {

        //guava的接口ListeningExecutorService继承了jdk原生ExecutorService接口，重写了submit方法，
        // 修改返回值类型为ListenableFuture
        ListeningExecutorService listeningExecutor = MoreExecutors.listeningDecorator(poolExecutor);

        //获得一个随着jvm关闭而关闭的线程池，通过Runtime.getRuntime().addShutdownHook(hook)实现
        //修改ThreadFactory为创建守护线程，默认jvm关闭时最多等待120秒关闭线程池，重载方法可以设置时间
        ExecutorService newPoolExecutor = MoreExecutors.getExitingExecutorService(poolExecutor);

        //只增加关闭线程池的钩子，不改变ThreadFactory
        MoreExecutors.addDelayedShutdownHook(poolExecutor, 120, TimeUnit.SECONDS);


        //像线程池提交任务，并得到ListenableFuture
        ListenableFuture<String> listenableFuture = listeningExecutor.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(300000);
                return "call over";
            }
        });
        //可以通过addListener对listenableFuture注册回调，但是通常使用Futures中的工具方法
        Futures.addCallback(listenableFuture, new FutureCallback<String>() {
            @Override
            public void onSuccess(String result) {
                System.out.println("success " + result);
            }

            @Override
            public void onFailure(Throwable t) {
                System.out.println("failure");
            }
        }, poolExecutor);
    }

    public void test2() {
        ListeningExecutorService service = MoreExecutors.listeningDecorator(poolExecutor);

        List<Integer> list = Lists.newArrayList(6, 5, 4, 3, 2, 1);

        for (Integer item : list) {
            ListenableFuture future = service.submit(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    TimeUnit.SECONDS.sleep(item);
                    return item;
                }
            });


            Futures.addCallback(future, new FutureCallback<Integer>() {

                @Override
                public void onSuccess(Integer result) {
                    System.out.println("------");
                    System.out.println(item);
                    System.out.println(result);
                    System.out.println("------");
                }

                @Override
                public void onFailure(Throwable t) {
                    t.printStackTrace();
                }
            }, poolExecutor);
        }

    }

    public void test4() {
        ListenableFutureTask<String> task1 = ListenableFutureTask.create(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "task1";
            }
        });
        new Thread(task1).start();
        task1.addListener(new Runnable() {
            @Override
            public void run() {
                ListenableFutureTask<String> task2 = ListenableFutureTask.create(new Callable<String>() {
                    @Override
                    public String call() throws Exception {
                        return  "task2 ";
                    }
                });
                task2.addListener(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println(" task 2 listener");
                    }
                }, MoreExecutors.directExecutor());
                new Thread(task2).start();
            }
        }, MoreExecutors.directExecutor());
        //当task1执行完毕会回调执行Function的apply方法，如果有task1有异常抛出，则task2也抛出相同异常，不执行apply
        ListenableFuture<String> task2 = Futures.transform(task1, new Function<String, String>() {
            @Override
            public String apply(String input) {
                return "task2";
            }
        }, poolExecutor);
        ListenableFuture<String> task3 = Futures.transform(task2, new Function<String, String>() {
            @Override
            public String apply(String input) {
                return "task3";
            }
        },poolExecutor);
        //处理最终的异步任务
        Futures.addCallback(task3, new FutureCallback<String>() {
            @Override
            public void onSuccess(String result) {
                System.out.println("success");
            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
            }
        }, poolExecutor);
    }

}