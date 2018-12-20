package com.guojiang.service;

import com.guojiang.bean.SShopCosmetologyDetail;
import com.guojiang.dao.SShopCosmetologyDetailMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SShopCosmetologyDetailService {

    @Autowired
    private SShopCosmetologyDetailMapper sShopCosmetologyDetailMapper;

    public int deleteByPrimaryKey(Integer scdsId){
        return sShopCosmetologyDetailMapper.deleteByPrimaryKey(scdsId);
    }

    public int insert(SShopCosmetologyDetail record){
        return sShopCosmetologyDetailMapper.insert(record);
    }

    public int insertSelective(SShopCosmetologyDetail record){
        return sShopCosmetologyDetailMapper.insertSelective(record);
    }

    public SShopCosmetologyDetail selectByPrimaryKey(Integer scdsId){
        return sShopCosmetologyDetailMapper.selectByPrimaryKey(scdsId);
    }

    public int updateByPrimaryKeySelective(SShopCosmetologyDetail record){
        return sShopCosmetologyDetailMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(SShopCosmetologyDetail record){
        return sShopCosmetologyDetailMapper.updateByPrimaryKey(record);
    }

    public List<SShopCosmetologyDetail> selectByShopIdAndCosId(@Param("0") Integer shopId, @Param("1") Integer cosId, @Param("2") Integer state){
        return sShopCosmetologyDetailMapper.selectByShopIdAndCosId(shopId,cosId,state);
    }

    public List<SShopCosmetologyDetail> selectByShopIdAndCosIdAndCheck(@Param("0") Integer shopId, @Param("1") Integer cosId,@Param("2") Integer checkState, @Param("3") Integer state){
        return sShopCosmetologyDetailMapper.selectByShopIdAndCosIdAndCheck(shopId,cosId,checkState,state);
    }
}
