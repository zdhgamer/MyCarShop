package com.guojiang.service;

import com.guojiang.bean.SShopUpkeepSubService;
import com.guojiang.dao.SShopUpkeepSubServiceMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SShopUpkeepSubServiceService {


    @Autowired
    private SShopUpkeepSubServiceMapper sShopUpkeepSubServiceMapper;


    public int deleteByPrimaryKey(Integer ukssId){
        return sShopUpkeepSubServiceMapper.deleteByPrimaryKey(ukssId);
    }

    public int insert(SShopUpkeepSubService record){
        return sShopUpkeepSubServiceMapper.insert(record);
    }

    public int insertSelective(SShopUpkeepSubService record){
        return sShopUpkeepSubServiceMapper.insertSelective(record);
    }

    public SShopUpkeepSubService selectByPrimaryKey(Integer ukssId){
        return sShopUpkeepSubServiceMapper.selectByPrimaryKey(ukssId);
    }

    public int updateByPrimaryKeySelective(SShopUpkeepSubService record){
        return sShopUpkeepSubServiceMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(SShopUpkeepSubService record){
        return sShopUpkeepSubServiceMapper.updateByPrimaryKey(record);
    }

    public List<SShopUpkeepSubService> selectAllByShopId(@Param("0")Integer shopId, @Param("1") Integer state){
        return sShopUpkeepSubServiceMapper.selectAllByShopId(shopId,state);
    }

}
