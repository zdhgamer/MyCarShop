package com.guojiang.dao;

import com.guojiang.bean.SShopEvaluation;

public interface SShopEvaluationMapper {
    int deleteByPrimaryKey(Integer svId);

    int insert(SShopEvaluation record);

    int insertSelective(SShopEvaluation record);

    SShopEvaluation selectByPrimaryKey(Integer svId);

    int updateByPrimaryKeySelective(SShopEvaluation record);

    int updateByPrimaryKey(SShopEvaluation record);

    SShopEvaluation selectByOderId(Integer oderId);
}