package com.guojiang.dao;

import com.guojiang.bean.SShopCosmetologyService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SShopCosmetologyServiceMapper {
    int deleteByPrimaryKey(Integer scsId);

    int insert(SShopCosmetologyService record);

    int insertSelective(SShopCosmetologyService record);

    SShopCosmetologyService selectByPrimaryKey(Integer scsId);

    int updateByPrimaryKeySelective(SShopCosmetologyService record);

    int updateByPrimaryKey(SShopCosmetologyService record);

    List<SShopCosmetologyService> selectAllByShopId(@Param("0") Integer shopId, @Param("1") Integer state);

    SShopCosmetologyService selectByCarSize(@Param("0") Integer shopId,@Param("1") Integer carSizeId, @Param("2") Integer state);
}