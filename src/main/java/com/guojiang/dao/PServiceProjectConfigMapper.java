package com.guojiang.dao;

import com.guojiang.bean.PServiceProjectConfig;

import java.util.List;

public interface PServiceProjectConfigMapper {
    int deleteByPrimaryKey(Integer spId);

    int insert(PServiceProjectConfig record);

    int insertSelective(PServiceProjectConfig record);

    PServiceProjectConfig selectByPrimaryKey(Integer spId);

    int updateByPrimaryKeySelective(PServiceProjectConfig record);

    int updateByPrimaryKey(PServiceProjectConfig record);

    List<PServiceProjectConfig> selectAllByType(Integer type);
}