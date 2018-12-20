package com.guojiang.service;

import com.guojiang.bean.SShopItemDetails;
import com.guojiang.dao.SShopItemDetailsMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SShopItemDetailsService {

    @Autowired
    private SShopItemDetailsMapper sShopItemDetailsMapper;

    public int deleteByPrimaryKey(Integer sshdId){
        return sShopItemDetailsMapper.deleteByPrimaryKey(sshdId);
    }

    public int insert(SShopItemDetails record){
        return sShopItemDetailsMapper.insert(record);
    }

    public int insertSelective(SShopItemDetails record){
        return sShopItemDetailsMapper.insertSelective(record);
    }

    public SShopItemDetails selectByPrimaryKey(Integer sshdId){
        return sShopItemDetailsMapper.selectByPrimaryKey(sshdId);
    }

    public int updateByPrimaryKeySelective(SShopItemDetails record){
        return sShopItemDetailsMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(SShopItemDetails record){
        return sShopItemDetailsMapper.updateByPrimaryKey(record);
    }

    /**
     * 获取所有的该类型下面的商品
     * @param shopId
     * @param typeId
     * @param canUse
     * @return
     */
    public List<SShopItemDetails> selectAllByTypeId(@Param("0")Integer shopId, @Param("1") Integer typeId, @Param("2")Integer canUse){
        return sShopItemDetailsMapper.selectAllByTypeId(shopId,typeId,canUse);
    }

}
