package com.guojiang.dao;

import com.guojiang.bean.PShopItemConfig;

import java.util.List;

public interface PShopItemConfigMapper {
    int deleteByPrimaryKey(Integer pshcId);

    int insert(PShopItemConfig record);

    int insertSelective(PShopItemConfig record);

    PShopItemConfig selectByPrimaryKey(Integer pshcId);

    int updateByPrimaryKeySelective(PShopItemConfig record);

    int updateByPrimaryKey(PShopItemConfig record);

    /**
     * 查找所有配置信息
     * @return
     */
    List<PShopItemConfig> selectAllConfig();
}