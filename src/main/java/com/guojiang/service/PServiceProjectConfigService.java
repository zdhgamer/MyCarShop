package com.guojiang.service;

import com.guojiang.bean.PServiceProjectConfig;
import com.guojiang.dao.PServiceProjectConfigMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PServiceProjectConfigService {

    @Autowired
    private PServiceProjectConfigMapper pServiceProjectConfigMapper;

    public int deleteByPrimaryKey(Integer spId){
        return pServiceProjectConfigMapper.deleteByPrimaryKey(spId);
    }

    public int insert(PServiceProjectConfig record){
        return pServiceProjectConfigMapper.insert(record);
    }

    public int insertSelective(PServiceProjectConfig record){
        return pServiceProjectConfigMapper.insertSelective(record);
    }

    public PServiceProjectConfig selectByPrimaryKey(Integer spId){
        return pServiceProjectConfigMapper.selectByPrimaryKey(spId);
    }

    public int updateByPrimaryKeySelective(PServiceProjectConfig record){
        return pServiceProjectConfigMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(PServiceProjectConfig record){
        return pServiceProjectConfigMapper.updateByPrimaryKey(record);
    }

    public List<PServiceProjectConfig> selectAllByType(Integer type){
        return pServiceProjectConfigMapper.selectAllByType(type);
    }

}
