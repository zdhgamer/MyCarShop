package com.guojiang.service;

import com.guojiang.bean.SShopEvaluation;
import com.guojiang.dao.SShopEvaluationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SShopEvaluationService {

    @Autowired
    private SShopEvaluationMapper sShopEvaluationMapper;

    public int deleteByPrimaryKey(Integer svId){
        return sShopEvaluationMapper.deleteByPrimaryKey(svId);
    }

    public int insert(SShopEvaluation record){
        return sShopEvaluationMapper.insert(record);
    }

    public int insertSelective(SShopEvaluation record){
        return sShopEvaluationMapper.insertSelective(record);
    }

    public SShopEvaluation selectByPrimaryKey(Integer svId){
        return sShopEvaluationMapper.selectByPrimaryKey(svId);
    }

    public int updateByPrimaryKeySelective(SShopEvaluation record){
        return sShopEvaluationMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(SShopEvaluation record){
        return sShopEvaluationMapper.updateByPrimaryKey(record);
    }

    public SShopEvaluation selectByOderId(Integer oderId){
        return sShopEvaluationMapper.selectByOderId(oderId);
    }

}
