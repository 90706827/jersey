DROP TABLE IF EXISTS t_l_tran_list;
CREATE TABLE t_l_tran_list (
  `tran_no` varchar(64) NOT NULL COMMENT '平台流水号',
  `tran_type` varchar(2) NOT NULL COMMENT '交易类型01付款 02退款',
  `tran_amt` varchar(17) NOT NULL COMMENT '交易金额（分）',
  `ccy_code` varchar(3) NOT NULL COMMENT '币种类型',
  `order_no` varchar(64) NOT NULL COMMENT '订单号',
  `order_date` varchar(8) NOT NULL COMMENT '订单日期',
  `order_time` varchar(6) NOT NULL COMMENT '订单时间',
  `orig_tran_no` varchar(64) DEFAULT NULL COMMENT '原订单号',
  `bank_no` varchar(64) DEFAULT NULL COMMENT '银行流水号',
  `bank_date` varchar(8) DEFAULT NULL COMMENT '银行交易日期',
  `bank_time` varchar(6) DEFAULT NULL COMMENT '银行交易日期',
  `orig_bank_no` varchar(64) DEFAULT NULL COMMENT '原银行流水号',
  PRIMARY KEY (`tran_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;