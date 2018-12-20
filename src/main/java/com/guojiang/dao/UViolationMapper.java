package com.guojiang.dao;

import com.guojiang.bean.UViolation;

public interface UViolationMapper {
    int deleteByPrimaryKey(Integer vId);

    int insert(UViolation record);

    int insertSelective(UViolation record);

    UViolation selectByPrimaryKey(Integer vId);

    int updateByPrimaryKeySelective(UViolation record);

    int updateByPrimaryKey(UViolation record);
}