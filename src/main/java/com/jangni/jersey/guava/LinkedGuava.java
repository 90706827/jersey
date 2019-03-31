package com.jangni.jersey.guava;

import com.google.common.util.concurrent.*;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;

/**
 * @ClassName LinkedGuava
 * @Description
 * @Author Mr.Jangni
 * @Date 2019/3/31 17:09
 * @Version 1.0
 **/
public class LinkedGuava {

    public static void main(String[] args) {
        final CountDownLatch latch = new CountDownLatch(1);
        final ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));

        ListenableFuture<String> explosion = service.submit(new Callable<String>() {
            public String call() throws Exception {
                System.out.println("任务线程正在执行...");
                if(false){
                    throw  new RuntimeException();
                }
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return "开始任务线程的结果";
            }
        });

        ListenableFuture<String> first = Futures.transformAsync(explosion, new AsyncFunction<String, String>() {
            public ListenableFuture<String> apply(final String input) throws Exception {
                ListenableFuture<String> temp = service.submit(new Callable<String>() {
                    public String call() throws Exception {
                        System.out.println("第1个回调线程正在执行...");
                        try {
                            Thread.sleep(1000);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return input + " & 第1个回调线程的结果 ";
                    }
                });
                return temp;
            }
        }, service);


        ListenableFuture<String> second = Futures.transformAsync(first, new AsyncFunction<String, String>() {
            public ListenableFuture<String> apply(final String input) throws Exception {
                ListenableFuture<String> temp = service.submit(new Callable<String>() {
                    public String call() throws Exception {
                        System.out.println("第2个回调线程正在执行...");
                        try {
                            Thread.sleep(1000);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return input + " & 第2个回调线程的结果 ";
                    }
                });
                return temp;
            }
        }, service);

        ListenableFuture<String> third = Futures.transformAsync(second, new AsyncFunction<String, String>() {
            public ListenableFuture<String> apply(final String input) throws Exception {
                ListenableFuture<String> temp = service.submit(new Callable<String>() {
                    public String call() throws Exception {
                        System.out.println("第3个回调线程正在执行...");
                        try {
                            Thread.sleep(1000);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return input + " & 第3个回调线程的结果 ";
                    }
                });
                return temp;
            }
        }, service);

        ListenableFuture<String> forth = Futures.transformAsync(third, new AsyncFunction<String, String>() {
            public ListenableFuture<String> apply(final String input) throws Exception {
                ListenableFuture<String> temp = service.submit(new Callable<String>() {
                    public String call() throws Exception {
                        System.out.println("第4个回调线程正在执行...");
                        try {
                            Thread.sleep(1000);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return input + " & 第4个回调线程的结果 ";
                    }
                });
                return temp;
            }
        }, service);

        Futures.addCallback(forth, new FutureCallback<String>() {
            public void onSuccess(String result) {
                latch.countDown();
                System.out.println("结果: " + result);
            }

            public void onFailure(Throwable t) {
                t.printStackTrace();
                System.out.println("结果: "+t.getMessage());
            }
        },service);
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        service.shutdown();
    }


}