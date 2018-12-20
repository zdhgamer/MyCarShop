package com.guojiang.dao;

import com.guojiang.bean.PAdmin;

public interface PAdminMapper {
    int deleteByPrimaryKey(Integer paId);

    int insert(PAdmin record);

    int insertSelective(PAdmin record);

    PAdmin selectByPrimaryKey(Integer paId);

    int updateByPrimaryKeySelective(PAdmin record);

    int updateByPrimaryKey(PAdmin record);
}