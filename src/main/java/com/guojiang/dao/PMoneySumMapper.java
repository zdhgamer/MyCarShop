package com.guojiang.dao;

import com.guojiang.bean.PMoneySum;

public interface PMoneySumMapper {
    int deleteByPrimaryKey(Integer pmId);

    int insert(PMoneySum record);

    int insertSelective(PMoneySum record);

    PMoneySum selectByPrimaryKey(Integer pmId);

    int updateByPrimaryKeySelective(PMoneySum record);

    int updateByPrimaryKey(PMoneySum record);
}