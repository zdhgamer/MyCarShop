package com.guojiang.service;

import com.guojiang.bean.SShopTotalService;
import com.guojiang.dao.SShopTotalServiceMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SShopTotalServiceService {

    @Autowired
    private SShopTotalServiceMapper sShopTotalServiceMapper;

    public int deleteByPrimaryKey(Integer stsId){
        return sShopTotalServiceMapper.deleteByPrimaryKey(stsId);
    }

    public int insert(SShopTotalService record){
        return sShopTotalServiceMapper.insert(record);
    }

    public int insertSelective(SShopTotalService record){
        return sShopTotalServiceMapper.insertSelective(record);
    }

    public SShopTotalService selectByPrimaryKey(Integer stsId){
        return sShopTotalServiceMapper.selectByPrimaryKey(stsId);
    }

    public int updateByPrimaryKeySelective(SShopTotalService record){
        return sShopTotalServiceMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(SShopTotalService record){
        return sShopTotalServiceMapper.updateByPrimaryKey(record);
    }

    public List<SShopTotalService> selectByShopId(@Param("0")Integer shopId, @Param("1") Integer state){
        return sShopTotalServiceMapper.selectByShopId(shopId,state);
    }

    public SShopTotalService selectByServiceId(@Param("0")Integer shopId, @Param("1") Integer serviceId, @Param("2") Integer state){
        return sShopTotalServiceMapper.selectByServiceId(shopId,serviceId,state);
    }

}
