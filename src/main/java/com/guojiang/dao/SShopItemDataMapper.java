package com.guojiang.dao;

import com.guojiang.bean.SShopItemData;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SShopItemDataMapper {
    int deleteByPrimaryKey(Integer sshId);

    int insert(SShopItemData record);

    int insertSelective(SShopItemData record);

    SShopItemData selectByPrimaryKey(Integer sshId);

    int updateByPrimaryKeySelective(SShopItemData record);

    int updateByPrimaryKey(SShopItemData record);

    /**
     * 通过商品类别查找
     * @param shopId
     * @param shopItemType
     * @param state
     * @return
     */
    SShopItemData selectByShopType(@Param("0") Integer shopId,@Param("1") Integer shopItemType,@Param("2") Integer state);

    /***
     * 查找商家所有的商品类别
     * @param shopId
     * @param state
     * @return
     */
    List<SShopItemData> selectByShopId(@Param("0") Integer shopId,@Param("1") Integer state);
}