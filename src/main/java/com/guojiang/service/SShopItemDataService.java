package com.guojiang.service;

import com.guojiang.bean.SShopItemData;
import com.guojiang.dao.SShopItemDataMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SShopItemDataService {

    @Autowired
    private SShopItemDataMapper sShopItemDataMapper;


    public int deleteByPrimaryKey(Integer sshId){
        return sShopItemDataMapper.deleteByPrimaryKey(sshId);
    }

    public int insert(SShopItemData record){
        return sShopItemDataMapper.insert(record);
    }

    public int insertSelective(SShopItemData record){
        return sShopItemDataMapper.insertSelective(record);
    }

    public SShopItemData selectByPrimaryKey(Integer sshId){
        return sShopItemDataMapper.selectByPrimaryKey(sshId);
    }

    public int updateByPrimaryKeySelective(SShopItemData record){
        return sShopItemDataMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(SShopItemData record){
        return sShopItemDataMapper.updateByPrimaryKey(record);
    }

    /**
     * 通过商品类别查找
     * @param shopId
     * @param shopItemType
     * @param state
     * @return
     */
    public SShopItemData selectByShopType(@Param("0") Integer shopId, @Param("1") Integer shopItemType, @Param("2") Integer state){
        return sShopItemDataMapper.selectByShopType(shopId,shopItemType,state);
    }

    /***
     * 查找商家所有的商品类别
     * @param shopId
     * @param state
     * @return
     */
    public List<SShopItemData> selectByShopId(@Param("0") Integer shopId, @Param("1") Integer state){
        return sShopItemDataMapper.selectByShopId(shopId,state);
    }

}
