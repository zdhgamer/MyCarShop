package com.guojiang.dao;

import com.guojiang.bean.SShopInfo;

public interface SShopInfoMapper {
    int deleteByPrimaryKey(Integer spId);

    int insert(SShopInfo record);

    int insertSelective(SShopInfo record);

    SShopInfo selectByPrimaryKey(Integer spId);

    int updateByPrimaryKeySelective(SShopInfo record);

    int updateByPrimaryKey(SShopInfo record);

    SShopInfo selectByPhoneNumber(String phoneNumber);
}