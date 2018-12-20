package com.guojiang.dao;

import com.guojiang.bean.UViolationData;

public interface UViolationDataMapper {
    int deleteByPrimaryKey(Integer vdId);

    int insert(UViolationData record);

    int insertSelective(UViolationData record);

    UViolationData selectByPrimaryKey(Integer vdId);

    int updateByPrimaryKeySelective(UViolationData record);

    int updateByPrimaryKey(UViolationData record);
}