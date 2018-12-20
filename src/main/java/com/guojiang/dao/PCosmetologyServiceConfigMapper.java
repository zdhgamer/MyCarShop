package com.guojiang.dao;

import com.guojiang.bean.PCosmetologyServiceConfig;

public interface PCosmetologyServiceConfigMapper {
    int deleteByPrimaryKey(Integer ctsId);

    int insert(PCosmetologyServiceConfig record);

    int insertSelective(PCosmetologyServiceConfig record);

    PCosmetologyServiceConfig selectByPrimaryKey(Integer ctsId);

    int updateByPrimaryKeySelective(PCosmetologyServiceConfig record);

    int updateByPrimaryKey(PCosmetologyServiceConfig record);
}