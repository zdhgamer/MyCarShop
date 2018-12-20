package com.guojiang.service;

import com.guojiang.bean.USubService;
import com.guojiang.dao.USubServiceMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class USubServiceService {

    @Autowired
    private USubServiceMapper uSubServiceMapper;

    public int deleteByPrimaryKey(Integer ussId){
        return uSubServiceMapper.deleteByPrimaryKey(ussId);
    }

    public int insert(USubService record){
        return uSubServiceMapper.insert(record);
    }

    public int insertSelective(USubService record){
        return uSubServiceMapper.insertSelective(record);
    }

    public USubService selectByPrimaryKey(Integer ussId){
        return uSubServiceMapper.selectByPrimaryKey(ussId);
    }

    public int updateByPrimaryKeySelective(USubService record){
        return uSubServiceMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(USubService record){
        return uSubServiceMapper.updateByPrimaryKey(record);
    }

    public List<USubService> selectByOderId(Integer oderId){
        return uSubServiceMapper.selectByOderId(oderId);
    }

    public List<USubService> selectByOderIdAndTotal(@Param("0")Integer oderId, @Param("1") Integer totalId){
        return uSubServiceMapper.selectByOderIdAndTotal(oderId,totalId);
    }

}
