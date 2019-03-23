package com.jangni.jersey.entity;

import lombok.Data;

/**
 * @ClassName TranList
 * @Description 交易流水
 * @Author Mr.Jangni
 * @Date 2019/3/23 15:27
 * @Version 1.0
 **/
@Data
public class TranList {

    private String tranNo;
    private String tranType;
    private String TranAmt;
    private String ccyCode;
    private String orderNo;
    private String orderDate;
    private String orderTime;
    private String origTranNo;
    private String bankNo;
    private String bankDate;
    private String bankTime;
    private String origBankNo;

}
