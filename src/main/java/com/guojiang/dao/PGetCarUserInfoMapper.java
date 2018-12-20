package com.guojiang.dao;

import com.guojiang.bean.PGetCarUserInfo;

public interface PGetCarUserInfoMapper {
    int deleteByPrimaryKey(Integer pgcId);

    int insert(PGetCarUserInfo record);

    int insertSelective(PGetCarUserInfo record);

    PGetCarUserInfo selectByPrimaryKey(Integer pgcId);

    int updateByPrimaryKeySelective(PGetCarUserInfo record);

    int updateByPrimaryKey(PGetCarUserInfo record);
}