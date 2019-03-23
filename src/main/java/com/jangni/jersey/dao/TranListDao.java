package com.jangni.jersey.dao;

import com.jangni.jersey.entity.TranList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

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
            tranList.setTranNo(rs.getString(0));
            tranList.setTranType(rs.getString(1));
            tranList.setTranAmt(rs.getString(2));
            tranList.setCcyCode(rs.getString(3));
            tranList.setOrderNo(rs.getString(4));
            tranList.setOrderDate(rs.getString(5));
            tranList.setOrderTime(rs.getString(6));
            tranList.setOrigTranNo(rs.getString(7));
            tranList.setBankNo(rs.getString(8));
            tranList.setBankDate(rs.getString(9));
            tranList.setBankTime(rs.getString(10));
            tranList.setOrigBankNo(rs.getString(11));
        }
        return tranList;
    }

    public int save(TranList tranList) {
        String sql = "inset into t_l_tran_list(tran_no,tran_type,tran_amt,ccy_code,order_no,order_date,order_time,orig_tran_no,bank_no,bank_date,bank_time,orig_bank_no) values(?,?,?,?,?,?,?,?,?,?,?)";
        return jdbcTemplate.update(sql,
                tranList.getTranNo(),
                tranList.getTranType(),
                tranList.getTranAmt(),
                tranList.getCcyCode(),
                tranList.getOrderNo(),
                tranList.getOrderDate(),
                tranList.getOrderTime(),
                tranList.getOrigTranNo(),
                tranList.getBankNo(),
                tranList.getBankDate(),
                tranList.getBankTime(),
                tranList.getOrigBankNo());
    }


}
