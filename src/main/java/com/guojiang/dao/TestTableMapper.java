package com.guojiang.dao;

import com.guojiang.bean.TestTable;

public interface TestTableMapper {
    int deleteByPrimaryKey(Integer tId);

    int insert(TestTable record);

    int insertSelective(TestTable record);

    TestTable selectByPrimaryKey(Integer tId);

    int updateByPrimaryKeySelective(TestTable record);

    int updateByPrimaryKey(TestTable record);
}