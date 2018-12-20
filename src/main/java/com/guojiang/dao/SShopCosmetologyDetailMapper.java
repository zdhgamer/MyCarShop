package com.guojiang.dao;

import com.guojiang.bean.SShopCosmetologyDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SShopCosmetologyDetailMapper {
    int deleteByPrimaryKey(Integer scdsId);

    int insert(SShopCosmetologyDetail record);

    int insertSelective(SShopCosmetologyDetail record);

    SShopCosmetologyDetail selectByPrimaryKey(Integer scdsId);

    int updateByPrimaryKeySelective(SShopCosmetologyDetail record);

    int updateByPrimaryKey(SShopCosmetologyDetail record);

    List<SShopCosmetologyDetail> selectByShopIdAndCosId(@Param("0") Integer shopId, @Param("1") Integer cosId, @Param("2") Integer state);

    List<SShopCosmetologyDetail> selectByShopIdAndCosIdAndCheck(@Param("0") Integer shopId, @Param("1") Integer cosId,@Param("2") Integer checkState, @Param("3") Integer state);

}