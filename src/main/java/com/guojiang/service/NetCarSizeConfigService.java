package com.guojiang.service;

import com.guojiang.bean.NetCarSizeConfig;
import com.guojiang.dao.NetCarSizeConfigMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NetCarSizeConfigService {

    @Autowired
    private NetCarSizeConfigMapper netCarSizeConfigMapper;


    public int deleteByPrimaryKey(Integer csId){
        return netCarSizeConfigMapper.deleteByPrimaryKey(csId);
    }

    public int insert(NetCarSizeConfig record){
        return netCarSizeConfigMapper.insert(record);
    }

    public int insertSelective(NetCarSizeConfig record){
        return netCarSizeConfigMapper.insertSelective(record);
    }

    public NetCarSizeConfig selectByPrimaryKey(Integer csId){
        return netCarSizeConfigMapper.selectByPrimaryKey(csId);
    }

    public int updateByPrimaryKeySelective(NetCarSizeConfig record){
        return netCarSizeConfigMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(NetCarSizeConfig record){
        return netCarSizeConfigMapper.updateByPrimaryKey(record);
    }

    public NetCarSizeConfig selectByType(Integer type){
        return netCarSizeConfigMapper.selectByType(type);
    }

    public List<NetCarSizeConfig> selectAll(){
        return netCarSizeConfigMapper.selectAll();
    }
}
