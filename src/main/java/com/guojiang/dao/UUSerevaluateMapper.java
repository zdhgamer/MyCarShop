package com.guojiang.dao;

import com.guojiang.bean.UUSerevaluate;

public interface UUSerevaluateMapper {
    int deleteByPrimaryKey(Integer ulId);

    int insert(UUSerevaluate record);

    int insertSelective(UUSerevaluate record);

    UUSerevaluate selectByPrimaryKey(Integer ulId);

    int updateByPrimaryKeySelective(UUSerevaluate record);

    int updateByPrimaryKey(UUSerevaluate record);
}