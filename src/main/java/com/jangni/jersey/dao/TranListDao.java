package com.jangni.jersey.dao;

import com.jangni.jersey.entity.TranList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName TranListDao
 * @Description 交易流水
 * @Author Mr.Jangni
 * @Date 2019/3/23 15:26
 * @Version 1.0
 **/
@Repository
public class TranListDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public TranList getTranListByTranNo(String tranNo) {
        SqlRowSet rs = jdbcTemplate.queryForRowSet("select * from t_l_tran_list where tran_no = ?", tranNo);
        TranList tranList = null;
        if (rs.next()) {
            tranList = new TranList();
            tranList.setTranNo(rs.getString(1));
            tranList.setTranType(rs.getString(2));
            tranList.setTranAmt(rs.getString(3));
            tranList.setCcyCode(rs.getString(4));
            tranList.setOrderNo(rs.getString(5));
            tranList.setOrderDate(rs.getString(6));
            tranList.setOrderTime(rs.getString(7));
            tranList.setOrigTranNo(rs.getString(8));
            tranList.setBankNo(rs.getString(9));
            tranList.setBankDate(rs.getString(10));
            tranList.setBankTime(rs.getString(11));
            tranList.setOrigBankNo(rs.getString(12));
        }
        return tranList;
    }

    public int save(TranList tranList) {
        String sql = "insert into t_l_tran_list(tran_no,tran_type,tran_amt,ccy_code,order_no,order_date,order_time) values(?,?,?,?,?,?,?)";
        return jdbcTemplate.update(sql,
                tranList.getTranNo(),
                tranList.getTranType(),
                tranList.getTranAmt(),
                tranList.getCcyCode(),
                tranList.getOrderNo(),
                tranList.getOrderDate(),
                tranList.getOrderTime());
    }


}
