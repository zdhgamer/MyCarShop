package com.guojiang.dao;

import com.guojiang.bean.PAnnualCheckService;

public interface PAnnualCheckServiceMapper {
    int deleteByPrimaryKey(Integer pacId);

    int insert(PAnnualCheckService record);

    int insertSelective(PAnnualCheckService record);

    PAnnualCheckService selectByPrimaryKey(Integer pacId);

    int updateByPrimaryKeySelective(PAnnualCheckService record);

    int updateByPrimaryKey(PAnnualCheckService record);
}