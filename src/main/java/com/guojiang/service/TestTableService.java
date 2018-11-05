package com.guojiang.service;

import com.guojiang.bean.TestTable;
import com.guojiang.dao.TestTableMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestTableService {

    @Autowired
    private TestTableMapper testTableMapper;

    public int deleteByPrimaryKey(Integer tId){
        return testTableMapper.deleteByPrimaryKey(tId);
    }

    public int insert(TestTable record){
        return testTableMapper.insert(record);
    }

    public int insertSelective(TestTable record){
        return testTableMapper.insertSelective(record);
    }

    public TestTable selectByPrimaryKey(Integer tId){
        return testTableMapper.selectByPrimaryKey(tId);
    }

    public int updateByPrimaryKeySelective(TestTable record){
        return testTableMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(TestTable record){
        return testTableMapper.updateByPrimaryKey(record);
    }
}
