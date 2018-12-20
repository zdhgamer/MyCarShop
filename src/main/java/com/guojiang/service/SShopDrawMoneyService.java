package com.guojiang.service;

import com.guojiang.bean.SShopDrawMoney;
import com.guojiang.dao.SShopDrawMoneyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SShopDrawMoneyService {

    @Autowired
    private SShopDrawMoneyMapper sShopDrawMoneyMapper;

    public int deleteByPrimaryKey(Integer sdmId){
        return sShopDrawMoneyMapper.deleteByPrimaryKey(sdmId);
    }

    public int insert(SShopDrawMoney record){
        return sShopDrawMoneyMapper.insert(record);
    }

    public int insertSelective(SShopDrawMoney record){
        return sShopDrawMoneyMapper.insertSelective(record);
    }

    public SShopDrawMoney selectByPrimaryKey(Integer sdmId){
        return sShopDrawMoneyMapper.selectByPrimaryKey(sdmId);
    }

    public int updateByPrimaryKeySelective(SShopDrawMoney record){
        return sShopDrawMoneyMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(SShopDrawMoney record){
        return sShopDrawMoneyMapper.updateByPrimaryKey(record);
    }

    public List<SShopDrawMoney> selectByShopId(Integer shopId){
        return sShopDrawMoneyMapper.selectByShopId(shopId);
    }

}
