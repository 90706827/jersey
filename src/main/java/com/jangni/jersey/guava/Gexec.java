package com.jangni.jersey.guava;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;
import com.google.common.util.concurrent.*;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @ClassName Gexec
 * @Description
 * @Author Mr.Jangni
 * @Date 2019/3/30 18:27
 * @Version 1.0
 **/
public class Gexec {
    //真正干活的线程池
    public static ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(
            5,
            5,
            0,
            TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(100),
            new CustomizableThreadFactory("demo"),
            new ThreadPoolExecutor.DiscardPolicy());


    public static void main(String[] args) {
        ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));

        ListenableFuture future1 = service.submit(new Callable<Integer>() {
            public Integer call() throws InterruptedException {
                Thread.sleep(1000);
                System.out.println("call future 1.");
                return 1;
            }
        });

        ListenableFuture future2 = service.submit(new Callable<Integer>() {
            public Integer call() throws InterruptedException {
                Thread.sleep(1000);
                System.out.println("call future 2.");
                //    throw new RuntimeException("----call future 2.");
                return 2;
            }
        });

        ListenableFuture future3 = service.submit(new Callable<Integer>() {
            public Integer call() throws InterruptedException {
                Thread.sleep(1000);
                System.out.println("call future 3.");
                //    throw new RuntimeException("----call future 2.");
                return 3;
            }
        });

        ListenableFuture future4 = service.submit(new Callable<Integer>() {
            public Integer call() throws InterruptedException {
                Thread.sleep(1000);
                System.out.println("call future 4.");
                //    throw new RuntimeException("----call future 2.");
                return 4;
            }
        });

        ListenableFuture future5 = service.submit(new Callable<Integer>() {
            public Integer call() throws InterruptedException {
                Thread.sleep(1000);
                System.out.println("call future 5.");
                //    throw new RuntimeException("----call future 2.");
                return 5;
            }
        });

        ListenableFuture future6 = service.submit(new Callable<Integer>() {
            public Integer call() throws InterruptedException {
                Thread.sleep(1000);
                System.out.println("call future 6.");
                //    throw new RuntimeException("----call future 2.");
                return 6;
            }
        });

        ListenableFuture future7 = service.submit(new Callable<Integer>() {
            public Integer call() throws InterruptedException {
                Thread.sleep(1000);
                System.out.println("call future 7.");
                //    throw new RuntimeException("----call future 2.");
                return 7;
            }
        });

        ListenableFuture future8 = service.submit(new Callable<Integer>() {
            public Integer call() throws InterruptedException {
                Thread.sleep(1000);
                System.out.println("call future 8.");
                //    throw new RuntimeException("----call future 2.");
                return 8;
            }
        });
        LinkedList<ListenableFuture> linkedList = new LinkedList<>();
        linkedList.add(future1);
        linkedList.add(future2);
        linkedList.add(future3);
        linkedList.add(future4);
        linkedList.add(future5);
        linkedList.add(future6);
        linkedList.add(future7);
        linkedList.add(future8);


        ImmutableList list = ImmutableList.builder().addAll(linkedList).build();

        final ListenableFuture allFutures = Futures.allAsList(list);

        final ListenableFuture transform = Futures.transform(allFutures, new Function<List<Integer>, Boolean>() {
            @Override
            public Boolean apply(List<Integer> results)  {
                return true;
            }
        },poolExecutor);

        Futures.addCallback(transform, new FutureCallback<Object>() {

            public void onSuccess(Object result) {
                System.out.printf("success with: %s%n", result);
            }

            public void onFailure(Throwable thrown) {
                System.out.printf("onFailure%s%n", thrown.getMessage());
            }
        },poolExecutor);


    }


}