package com.guojiang.dao;

import com.guojiang.bean.UInvoiceTable;

public interface UInvoiceTableMapper {
    int deleteByPrimaryKey(Integer uiId);

    int insert(UInvoiceTable record);

    int insertSelective(UInvoiceTable record);

    UInvoiceTable selectByPrimaryKey(Integer uiId);

    int updateByPrimaryKeySelective(UInvoiceTable record);

    int updateByPrimaryKey(UInvoiceTable record);

    UInvoiceTable selectByOderId(Integer oderId);

}