package com.guojiang.service;

import com.guojiang.bean.UInvoiceTable;
import com.guojiang.dao.UInvoiceTableMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 发票信息
 */
@Service
public class UInvoiceTableService {

    @Autowired
    private UInvoiceTableMapper uInvoiceTableMapper;

    public int deleteByPrimaryKey(Integer uiId){
        return uInvoiceTableMapper.deleteByPrimaryKey(uiId);
    }

    public int insert(UInvoiceTable record){
        return uInvoiceTableMapper.insert(record);
    }

    public int insertSelective(UInvoiceTable record){
        return uInvoiceTableMapper.insertSelective(record);
    }

    public UInvoiceTable selectByPrimaryKey(Integer uiId){
        return uInvoiceTableMapper.selectByPrimaryKey(uiId);
    }

    public int updateByPrimaryKeySelective(UInvoiceTable record){
        return uInvoiceTableMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(UInvoiceTable record){
        return uInvoiceTableMapper.updateByPrimaryKey(record);
    }

    public UInvoiceTable selectByOderId(Integer oderId){
        return uInvoiceTableMapper.selectByOderId(oderId);
    }
}
