package com.guojiang.dao;

import com.guojiang.bean.SShopUpkeepDetailsItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SShopUpkeepDetailsItemMapper {
    int deleteByPrimaryKey(Integer suksdiId);

    int insert(SShopUpkeepDetailsItem record);

    int insertSelective(SShopUpkeepDetailsItem record);

    SShopUpkeepDetailsItem selectByPrimaryKey(Integer suksdiId);

    int updateByPrimaryKeySelective(SShopUpkeepDetailsItem record);

    int updateByPrimaryKey(SShopUpkeepDetailsItem record);

    List<SShopUpkeepDetailsItem> selectAllByDetailId(@Param("0")Integer shopId,@Param("1")Integer detailId);

    SShopUpkeepDetailsItem selectByItemId(@Param("0")Integer shopId,@Param("1")Integer detailId,@Param("2")Integer itemId);

}