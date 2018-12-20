package com.guojiang.service;

import com.guojiang.bean.SShopCosmetologyService;
import com.guojiang.dao.SShopCosmetologyServiceMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SShopCosmetologyServiceService {

    @Autowired
    private SShopCosmetologyServiceMapper sShopCosmetologyServiceMapper;

    public int deleteByPrimaryKey(Integer scsId){
        return sShopCosmetologyServiceMapper.deleteByPrimaryKey(scsId);
    }

    public int insert(SShopCosmetologyService record){
        return sShopCosmetologyServiceMapper.insert(record);
    }

    public int insertSelective(SShopCosmetologyService record){
        return sShopCosmetologyServiceMapper.insertSelective(record);
    }

    public SShopCosmetologyService selectByPrimaryKey(Integer scsId){
        return sShopCosmetologyServiceMapper.selectByPrimaryKey(scsId);
    }

    public int updateByPrimaryKeySelective(SShopCosmetologyService record){
        return sShopCosmetologyServiceMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(SShopCosmetologyService record){
        return sShopCosmetologyServiceMapper.updateByPrimaryKey(record);
    }

    public List<SShopCosmetologyService> selectAllByShopId(@Param("0") Integer shopId, @Param("1") Integer state){
        return sShopCosmetologyServiceMapper.selectAllByShopId(shopId,state);
    }

    public SShopCosmetologyService selectByCarSize(@Param("0") Integer shopId,@Param("1") Integer carSizeId, @Param("2") Integer state){
        return sShopCosmetologyServiceMapper.selectByCarSize(shopId,carSizeId,state);
    }

}
