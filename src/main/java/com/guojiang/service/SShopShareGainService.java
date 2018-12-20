package com.guojiang.service;

import com.guojiang.bean.SShopShareGain;
import com.guojiang.dao.SShopShareGainMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SShopShareGainService {

    @Autowired
    private SShopShareGainMapper sShopShareGainMapper;

    public int deleteByPrimaryKey(Integer ssgId){
        return sShopShareGainMapper.deleteByPrimaryKey(ssgId);
    }

    public int insert(SShopShareGain record){
        return sShopShareGainMapper.insert(record);
    }

    public int insertSelective(SShopShareGain record){
        return sShopShareGainMapper.insertSelective(record);
    }

    public SShopShareGain selectByPrimaryKey(Integer ssgId){
        return sShopShareGainMapper.selectByPrimaryKey(ssgId);
    }

    public int updateByPrimaryKeySelective(SShopShareGain record){
        return sShopShareGainMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(SShopShareGain record){
        return sShopShareGainMapper.updateByPrimaryKey(record);
    }

    public List<SShopShareGain> selectByShopId(Integer shopId){
        return sShopShareGainMapper.selectByShopId(shopId);
    }

}
