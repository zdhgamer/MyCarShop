package com.guojiang.dao;

import com.guojiang.bean.SShopUpkeepServiceDetails;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SShopUpkeepServiceDetailsMapper {
    int deleteByPrimaryKey(Integer spsdId);

    int insert(SShopUpkeepServiceDetails record);

    int insertSelective(SShopUpkeepServiceDetails record);

    SShopUpkeepServiceDetails selectByPrimaryKey(Integer spsdId);

    int updateByPrimaryKeySelective(SShopUpkeepServiceDetails record);

    int updateByPrimaryKey(SShopUpkeepServiceDetails record);

    List<SShopUpkeepServiceDetails> selectAllBySubId(@Param("0")Integer shopId, @Param("1") Integer subId, @Param("2") Integer state);

    List<SShopUpkeepServiceDetails> selectAllBySubIdAndCheck(@Param("0")Integer shopId, @Param("1") Integer subId, @Param("2") Integer checkState,@Param("3") Integer state);

    SShopUpkeepServiceDetails selectByDetailId(@Param("0")Integer shopId, @Param("1") Integer subId,@Param("2") Integer detailId, @Param("3") Integer state);
}