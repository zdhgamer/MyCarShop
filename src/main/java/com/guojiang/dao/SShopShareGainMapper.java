package com.guojiang.dao;

import com.guojiang.bean.SShopShareGain;

import java.util.List;

public interface SShopShareGainMapper {
    int deleteByPrimaryKey(Integer ssgId);

    int insert(SShopShareGain record);

    int insertSelective(SShopShareGain record);

    SShopShareGain selectByPrimaryKey(Integer ssgId);

    int updateByPrimaryKeySelective(SShopShareGain record);

    int updateByPrimaryKey(SShopShareGain record);

    List<SShopShareGain> selectByShopId(Integer shopId);
}