package com.guojiang.dao;

import com.guojiang.bean.UOderSystem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UOderSystemMapper {
    int deleteByPrimaryKey(Integer oId);

    int insert(UOderSystem record);

    int insertSelective(UOderSystem record);

    UOderSystem selectByPrimaryKey(Integer oId);

    int updateByPrimaryKeySelective(UOderSystem record);

    int updateByPrimaryKey(UOderSystem record);

    List<UOderSystem> selectAllByShopId(Integer shopId);

    List<UOderSystem> selectAllByShopIdAndState(@Param("0")Integer shopId, @Param("1")Integer state);

    Integer selectAllByShopIdAndStateCount(@Param("0")Integer shopId, @Param("1")Integer state);

    /**
     * 查找满足状态的list
     * @param shopId
     * @param minState 可以取得到
     * @param maxState 可以取得到
     * @return
     */
    List<UOderSystem> selectAllByShopIdAndMinMaxState(@Param("0")Integer shopId,@Param("1")Integer minState,@Param("2")Integer maxState);

    /**
     * 查找满足状态的list
     * @param shopId
     * @param minState 可以取得到
     * @param maxState 可以取得到
     * @return
     */
    Integer selectAllByShopIdAndMinMaxStateCount(@Param("0")Integer shopId,@Param("1")Integer minState,@Param("2")Integer maxState);
}