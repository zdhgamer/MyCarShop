package com.guojiang.dao;

import com.guojiang.bean.SShopTotalService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SShopTotalServiceMapper {
    int deleteByPrimaryKey(Integer stsId);

    int insert(SShopTotalService record);

    int insertSelective(SShopTotalService record);

    SShopTotalService selectByPrimaryKey(Integer stsId);

    int updateByPrimaryKeySelective(SShopTotalService record);

    int updateByPrimaryKey(SShopTotalService record);

    List<SShopTotalService> selectByShopId(@Param("0")Integer shopId, @Param("1") Integer state);

    SShopTotalService selectByServiceId(@Param("0")Integer shopId, @Param("1") Integer serviceId, @Param("2") Integer state);
}