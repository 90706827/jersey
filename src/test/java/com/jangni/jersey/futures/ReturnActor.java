package com.jangni.jersey.futures;

import akka.actor.AbstractActor;
import akka.actor.Props;

import javax.annotation.concurrent.GuardedBy;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName ReturnActor
 * @Description
 * @Author Mr.Jangni
 * @Date 2019/3/31 15:15
 * @Version 1.0
 **/
public class ReturnActor extends AbstractActor {
    @GuardedBy("DATE_FORMAT")
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    public static Props props() {
        return Props.create(ReturnActor.class);
    }

    @Override
    public AbstractActor.Receive createReceive() {
        return receiveBuilder().match(String.class, s -> {
            System.out.println("Got String:" + s + ", at:" + DATE_FORMAT.format(new Date()));
            getContext().stop(getSelf());
            getContext().actorOf(ReturnActor.props(),"print");
//            TimeUnit.SECONDS.sleep(5);
//            getSender().tell(28,getSelf());
        }).build();
    }
}