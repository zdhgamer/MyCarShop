package com.guojiang.service;

import com.guojiang.bean.SShopUpkeepServiceDetails;
import com.guojiang.dao.SShopUpkeepServiceDetailsMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SShopUpkeepServiceDetailsService {

    @Autowired
    private SShopUpkeepServiceDetailsMapper sShopUpkeepServiceDetailsMapper;


    public int deleteByPrimaryKey(Integer spsdId){
        return sShopUpkeepServiceDetailsMapper.deleteByPrimaryKey(spsdId);
    }

    public int insert(SShopUpkeepServiceDetails record){
        return sShopUpkeepServiceDetailsMapper.insert(record);
    }

    public int insertSelective(SShopUpkeepServiceDetails record){
        return sShopUpkeepServiceDetailsMapper.insert(record);
    }

    public SShopUpkeepServiceDetails selectByPrimaryKey(Integer spsdId){
        return sShopUpkeepServiceDetailsMapper.selectByPrimaryKey(spsdId);
    }

    public int updateByPrimaryKeySelective(SShopUpkeepServiceDetails record){
        return sShopUpkeepServiceDetailsMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(SShopUpkeepServiceDetails record){
        return sShopUpkeepServiceDetailsMapper.updateByPrimaryKey(record);
    }

    public List<SShopUpkeepServiceDetails> selectAllBySubId(@Param("0")Integer shopId, @Param("1") Integer subId, @Param("2") Integer state){
        return sShopUpkeepServiceDetailsMapper.selectAllBySubId(shopId,subId,state);
    }

    public List<SShopUpkeepServiceDetails> selectAllBySubIdAndCheck(@Param("0")Integer shopId, @Param("1") Integer subId, @Param("2") Integer checkState,@Param("3") Integer state){
        return sShopUpkeepServiceDetailsMapper.selectAllBySubIdAndCheck(shopId,subId,checkState,state);
    }

    public SShopUpkeepServiceDetails selectByDetailId(@Param("0")Integer shopId, @Param("1") Integer subId,@Param("2") Integer detailId, @Param("3") Integer state){
        return sShopUpkeepServiceDetailsMapper.selectByDetailId(shopId,subId,detailId,state);
    }

}
