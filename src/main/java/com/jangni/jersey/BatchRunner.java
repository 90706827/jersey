package com.jangni.jersey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.concurrent.Callable;

/**
 * @ClassName BatchRunner
 * @Description
 * @Author Mr.Jangni
 * @Date 2019/3/22 0:44
 * @Version 1.0
 **/

class BatchRunner implements Callable<String> {
    private String books;
    private Logger logger = LoggerFactory.getLogger(BatchRunner.class);

    public BatchRunner(String books) {
        this.books = books;
    }

    @Override
    public String call() {
        String ids = null;
        try {
            ids = batchSave();
            logger.info(">>>>>>>>>>" + ids);
        } catch (InterruptedException e) {
            logger.error("", e);
        }
        return ids;
    }

    private String batchSave() throws InterruptedException {
        if (!StringUtils.isEmpty(books)) {

            return "1";
        } else {
            return "0";
        }
    }
}