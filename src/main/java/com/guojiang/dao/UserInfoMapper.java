package com.guojiang.dao;

import com.guojiang.bean.UserInfo;

public interface UserInfoMapper {
    int deleteByPrimaryKey(Integer uId);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(Integer uId);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);
}