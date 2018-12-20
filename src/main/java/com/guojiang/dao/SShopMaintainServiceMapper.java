package com.guojiang.dao;

import com.guojiang.bean.SShopMaintainService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SShopMaintainServiceMapper {
    int deleteByPrimaryKey(Integer smoId);

    int insert(SShopMaintainService record);

    int insertSelective(SShopMaintainService record);

    SShopMaintainService selectByPrimaryKey(Integer smoId);

    int updateByPrimaryKeySelective(SShopMaintainService record);

    int updateByPrimaryKey(SShopMaintainService record);

    List<SShopMaintainService> selectAllByShopId(@Param("0")Integer shopId, @Param("1") Integer state);

    SShopMaintainService selectByCarSizeId(@Param("0")Integer shopId,@Param("1") Integer carSizeId,@Param("2") Integer state);
}