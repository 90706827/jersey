package com.jangni.jersey.service;

import com.jangni.jersey.dao.TranListDao;
import com.jangni.jersey.entity.TranList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @ClassName TranListService
 * @Description 交易流水
 * @Author Mr.Jangni
 * @Date 2019/3/23 23:57
 * @Version 1.0
 **/
@Component
public class TranListService {

    @Autowired
    TranListDao tranListDao;

    public void save(){
        TranList tranList = new TranList();
        tranList.setTranNo(UUID.randomUUID().toString().replace("-",""));
        tranListDao.save(tranList);
    }

}
