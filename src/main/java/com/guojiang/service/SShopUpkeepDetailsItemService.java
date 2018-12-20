package com.guojiang.service;

import com.guojiang.bean.SShopUpkeepDetailsItem;
import com.guojiang.dao.SShopUpkeepDetailsItemMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SShopUpkeepDetailsItemService {

    @Autowired
    private SShopUpkeepDetailsItemMapper sShopUpkeepDetailsItemMapper;


    public int deleteByPrimaryKey(Integer suksdiId){
        return sShopUpkeepDetailsItemMapper.deleteByPrimaryKey(suksdiId);
    }

    public int insert(SShopUpkeepDetailsItem record){
        return sShopUpkeepDetailsItemMapper.insert(record);
    }

    public int insertSelective(SShopUpkeepDetailsItem record){
        return sShopUpkeepDetailsItemMapper.insertSelective(record);
    }

    public SShopUpkeepDetailsItem selectByPrimaryKey(Integer suksdiId){
        return sShopUpkeepDetailsItemMapper.selectByPrimaryKey(suksdiId);
    }

    public int updateByPrimaryKeySelective(SShopUpkeepDetailsItem record){
        return sShopUpkeepDetailsItemMapper.updateByPrimaryKeySelective(record);
    }

    public  int updateByPrimaryKey(SShopUpkeepDetailsItem record){
        return sShopUpkeepDetailsItemMapper.updateByPrimaryKey(record);
    }

    public  List<SShopUpkeepDetailsItem> selectAllByDetailId(@Param("0")Integer shopId, @Param("1")Integer detailId){
        return sShopUpkeepDetailsItemMapper.selectAllByDetailId(shopId,detailId);
    }

    public SShopUpkeepDetailsItem selectByItemId(@Param("0")Integer shopId,@Param("1")Integer detailId,@Param("2")Integer itemId){
        return sShopUpkeepDetailsItemMapper.selectByItemId(shopId,detailId,itemId);
    }
}
