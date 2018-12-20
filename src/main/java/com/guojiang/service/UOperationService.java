package com.guojiang.service;

import com.guojiang.bean.UOperation;
import com.guojiang.dao.UOperationMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UOperationService {

    @Autowired
    private UOperationMapper uOperationMapper;

    public int deleteByPrimaryKey(Integer uooId){
        return uOperationMapper.deleteByPrimaryKey(uooId);
    }

    public int insert(UOperation record){
        return uOperationMapper.insert(record);
    }

    public int insertSelective(UOperation record){
        return uOperationMapper.insertSelective(record);
    }

    public UOperation selectByPrimaryKey(Integer uooId){
        return uOperationMapper.selectByPrimaryKey(uooId);
    }

    public int updateByPrimaryKeySelective(UOperation record){
        return uOperationMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(UOperation record){
        return uOperationMapper.updateByPrimaryKey(record);
    }

    public List<UOperation> selectByOderId(Integer oderId){
        return uOperationMapper.selectByOderId(oderId);
    }

    public UOperation selectByOderIdAndState(@Param("0") Integer oderId, @Param("1") Integer state){
        return uOperationMapper.selectByOderIdAndState(oderId,state);
    }

}
