package com.jangni.jersey.service;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.jangni.jersey.core.BaseService;
import com.jangni.jersey.core.JobContext;
import com.jangni.jersey.core.RestResponse;
import com.jangni.jersey.dao.TranListDao;
import com.jangni.jersey.entity.TranList;
import com.jangni.jersey.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Nullable;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;

/**
 * @ClassName TranListService
 * @Description 交易流水
 * @Author Mr.Jangni
 * @Date 2019/3/23 23:57
 * @Version 1.0
 **/
@Component
public class TranListService extends BaseService {

    @Autowired
    TranListDao tranListDao;

    public TranList getTranListByTranNo(String tranNo) {
        return tranListDao.getTranListByTranNo(tranNo);
    }

    public void save() {
        TranList tranList = new TranList();
        tranList.setTranNo(UUID.randomUUID().toString().replace("-", ""));
        tranList.setTranType("01");
        tranList.setTranAmt("100000");
        tranList.setCcyCode("156");
        tranList.setOrderNo(UUID.randomUUID().toString().replace("-", ""));
        tranList.setOrderDate(DateUtils.formatByDateTimePattern(new Date(), DateUtils.DAY_YYYYMMDD));
        tranList.setOrderTime(DateUtils.formatByDateTimePattern(new Date(), DateUtils.DAY_HHMMSS));
        tranListDao.save(tranList);
    }

    public void veryExpensiveOperation(JobContext context) {

//        CompletableFuture<RestResponse> completableFuture = new CompletableFuture<>();
//
//        new Thread(() -> {
//            TranList tranList = tranListDao.getTranListByTranNo(context.getTranNo());
//            //do expensive stuff here
//            completableFuture.complete(new RestResponse(tranList));
//        }).start();
//        completableFuture.thenAccept(resp -> context.getAsyncResponse().resume(resp));


        ListenableFuture<TranList> listenableFuture = service.submit(new Callable<TranList>() {
            @Override
            public TranList call() throws Exception {
                TranList tranList = tranListDao.getTranListByTranNo(context.getTranNo());
                return tranList;
            }
        });
        Futures.addCallback(listenableFuture, new FutureCallback<TranList>() {
            @Override
            public void onSuccess(@Nullable TranList tranList) {
                    context.getAsyncResponse().resume(new RestResponse(tranList));
            }

            @Override
            public void onFailure(Throwable throwable) {

            }
        }, pool);
    }
}
