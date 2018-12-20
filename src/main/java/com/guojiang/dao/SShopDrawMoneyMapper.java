package com.guojiang.dao;

import com.guojiang.bean.SShopDrawMoney;

import java.util.List;

public interface SShopDrawMoneyMapper {
    int deleteByPrimaryKey(Integer sdmId);

    int insert(SShopDrawMoney record);

    int insertSelective(SShopDrawMoney record);

    SShopDrawMoney selectByPrimaryKey(Integer sdmId);

    int updateByPrimaryKeySelective(SShopDrawMoney record);

    int updateByPrimaryKey(SShopDrawMoney record);

    List<SShopDrawMoney> selectByShopId(Integer shopId);
}