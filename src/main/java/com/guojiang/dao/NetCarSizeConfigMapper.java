package com.guojiang.dao;

import com.guojiang.bean.NetCarSizeConfig;

import java.util.List;

public interface NetCarSizeConfigMapper {
    int deleteByPrimaryKey(Integer csId);

    int insert(NetCarSizeConfig record);

    int insertSelective(NetCarSizeConfig record);

    NetCarSizeConfig selectByPrimaryKey(Integer csId);

    int updateByPrimaryKeySelective(NetCarSizeConfig record);

    int updateByPrimaryKey(NetCarSizeConfig record);

    NetCarSizeConfig selectByType(Integer type);

    List<NetCarSizeConfig> selectAll();
}