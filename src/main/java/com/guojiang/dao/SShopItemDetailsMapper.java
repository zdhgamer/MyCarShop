package com.guojiang.dao;

import com.guojiang.bean.SShopItemDetails;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SShopItemDetailsMapper {
    int deleteByPrimaryKey(Integer sshdId);

    int insert(SShopItemDetails record);

    int insertSelective(SShopItemDetails record);

    SShopItemDetails selectByPrimaryKey(Integer sshdId);

    int updateByPrimaryKeySelective(SShopItemDetails record);

    int updateByPrimaryKey(SShopItemDetails record);

    List<SShopItemDetails> selectAllByTypeId(@Param("0")Integer shopId, @Param("1") Integer typeId, @Param("2")Integer canUse);
}