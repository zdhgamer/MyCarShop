package com.guojiang.service;

import com.guojiang.bean.NetCarDetailsData;
import com.guojiang.dao.NetCarDetailsDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NetCarDetailsDataService {

    @Autowired
    private NetCarDetailsDataMapper netCarDetailsDataMapper;


    public int deleteByPrimaryKey(Integer ncdId){
        return netCarDetailsDataMapper.deleteByPrimaryKey(ncdId);
    }

    public int insert(NetCarDetailsData record){
        return netCarDetailsDataMapper.insert(record);
    }

    public int insertSelective(NetCarDetailsData record){
        return netCarDetailsDataMapper.insertSelective(record);
    }

    public NetCarDetailsData selectByPrimaryKey(Integer ncdId){
        return netCarDetailsDataMapper.selectByPrimaryKey(ncdId);
    }

    public int updateByPrimaryKeySelective(NetCarDetailsData record){
        return netCarDetailsDataMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(NetCarDetailsData record){
        return netCarDetailsDataMapper.updateByPrimaryKey(record);
    }

    /**
     * 通过I查找
     * @param I
     * @return
     */
    public NetCarDetailsData selectByI(Integer I){
        return netCarDetailsDataMapper.selectByI(I);
    }

    /**
     * 查找所有
     * @return
     */
    public List<NetCarDetailsData> selectAll(){
        return netCarDetailsDataMapper.selectAll();
    }

}
