package com.guojiang.dao;

import com.guojiang.bean.UCarToUser;

public interface UCarToUserMapper {
    int deleteByPrimaryKey(Integer cId);

    int insert(UCarToUser record);

    int insertSelective(UCarToUser record);

    UCarToUser selectByPrimaryKey(Integer cId);

    int updateByPrimaryKeySelective(UCarToUser record);

    int updateByPrimaryKey(UCarToUser record);
}