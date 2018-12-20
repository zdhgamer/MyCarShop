package com.guojiang.service;

import com.guojiang.bean.SShopInfo;
import com.guojiang.dao.SShopInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 商家信息服务
 */
@Service
public class SShopInfoService {

    @Autowired
    private SShopInfoMapper sShopInfoMapper;

    public int deleteByPrimaryKey(Integer spId){
        return sShopInfoMapper.deleteByPrimaryKey(spId);
    }

    public int insert(SShopInfo record){
        return sShopInfoMapper.insert(record);
    }

    public int insertSelective(SShopInfo record){
        return sShopInfoMapper.insertSelective(record);
    }

    public SShopInfo selectByPrimaryKey(Integer spId){
        return sShopInfoMapper.selectByPrimaryKey(spId);
    }

    public int updateByPrimaryKeySelective(SShopInfo record){
        return sShopInfoMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(SShopInfo record){
        return sShopInfoMapper.updateByPrimaryKey(record);
    }

    /**
     * 通过手机号码查找商家
     * @param phoneNumber
     * @return
     */
    public SShopInfo selectByPhoneNumber(String phoneNumber){
        return sShopInfoMapper.selectByPhoneNumber(phoneNumber);
    }
}
