package com.guojiang.dao;

import com.guojiang.bean.SShopUpkeepSubService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SShopUpkeepSubServiceMapper {
    int deleteByPrimaryKey(Integer ukssId);

    int insert(SShopUpkeepSubService record);

    int insertSelective(SShopUpkeepSubService record);

    SShopUpkeepSubService selectByPrimaryKey(Integer ukssId);

    int updateByPrimaryKeySelective(SShopUpkeepSubService record);

    int updateByPrimaryKey(SShopUpkeepSubService record);

    List<SShopUpkeepSubService> selectAllByShopId(@Param("0")Integer shopId, @Param("1") Integer state);
}