package com.guojiang.service;

import com.guojiang.bean.UServiceProject;
import com.guojiang.dao.UServiceProjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UServiceProjectService {

    @Autowired
    private UServiceProjectMapper uServiceProjectMapper;

    public int deleteByPrimaryKey(Integer uspId){
        return uServiceProjectMapper.deleteByPrimaryKey(uspId);
    }

    public int insert(UServiceProject record){
        return uServiceProjectMapper.insert(record);
    }

    public int insertSelective(UServiceProject record){
        return uServiceProjectMapper.insertSelective(record);
    }

    public UServiceProject selectByPrimaryKey(Integer uspId){
        return uServiceProjectMapper.selectByPrimaryKey(uspId);
    }

    public int updateByPrimaryKeySelective(UServiceProject record){
        return uServiceProjectMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(UServiceProject record){
        return uServiceProjectMapper.updateByPrimaryKey(record);
    }

    public List<UServiceProject> selectByOderId(Integer oderId){
        return uServiceProjectMapper.selectByOderId(oderId);
    }
}
