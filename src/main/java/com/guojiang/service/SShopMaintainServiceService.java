package com.guojiang.service;

import com.guojiang.bean.SShopMaintainService;
import com.guojiang.dao.SShopMaintainServiceMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SShopMaintainServiceService {

    @Autowired
    private SShopMaintainServiceMapper sShopMaintainServiceMapper;

    public int deleteByPrimaryKey(Integer smoId){
        return sShopMaintainServiceMapper.deleteByPrimaryKey(smoId);
    }

    public int insert(SShopMaintainService record){
        return sShopMaintainServiceMapper.insert(record);
    }

    public int insertSelective(SShopMaintainService record){
        return sShopMaintainServiceMapper.insertSelective(record);
    }

    public SShopMaintainService selectByPrimaryKey(Integer smoId){
        return sShopMaintainServiceMapper.selectByPrimaryKey(smoId);
    }

    public int updateByPrimaryKeySelective(SShopMaintainService record){
        return sShopMaintainServiceMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(SShopMaintainService record){
        return sShopMaintainServiceMapper.updateByPrimaryKey(record);
    }

    public List<SShopMaintainService> selectAllByShopId(@Param("0")Integer shopId, @Param("1") Integer state){
        return sShopMaintainServiceMapper.selectAllByShopId(shopId,state);
    }

    public SShopMaintainService selectByCarSizeId(@Param("0")Integer shopId,@Param("1") Integer carSizeId,@Param("2") Integer state){
        return sShopMaintainServiceMapper.selectByCarSizeId(shopId,carSizeId,state);
    }

}
