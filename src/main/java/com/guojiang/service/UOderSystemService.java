package com.guojiang.service;

import com.guojiang.bean.UOderSystem;
import com.guojiang.dao.UOderSystemMapper;
import com.guojiang.util.EnumCode;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UOderSystemService {

    @Autowired
    private UOderSystemMapper uOderSystemMapper;

    public int deleteByPrimaryKey(Integer oId){
        return uOderSystemMapper.deleteByPrimaryKey(oId);
    }

    public int insert(UOderSystem record){
        return uOderSystemMapper.insert(record);
    }

    public int insertSelective(UOderSystem record){
        return uOderSystemMapper.insertSelective(record);
    }

    public UOderSystem selectByPrimaryKey(Integer oId){
        return uOderSystemMapper.selectByPrimaryKey(oId);
    }

    public int updateByPrimaryKeySelective(UOderSystem record){
        return uOderSystemMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(UOderSystem record){
        return uOderSystemMapper.updateByPrimaryKey(record);
    }

    public List<UOderSystem> selectAllByShopId(Integer shopId){
        return uOderSystemMapper.selectAllByShopId(shopId);
    }

    public List<UOderSystem> selectAllByShopIdAndState(@Param("0")Integer shopId, @Param("1")Integer state){
        return uOderSystemMapper.selectAllByShopIdAndState(shopId,state);
    }

    public Integer selectAllByShopIdAndStateCount(@Param("0")Integer shopId, @Param("1")Integer state){
        return uOderSystemMapper.selectAllByShopIdAndStateCount(shopId,state);
    }

    /**
     * 查找满足状态的list
     * @param shopId
     * @param minState 可以取得到
     * @param maxState 可以取得到
     * @return
     */
    public List<UOderSystem> selectAllByShopIdAndMinMaxState(@Param("0")Integer shopId,@Param("1")Integer minState,@Param("2")Integer maxState){
        return uOderSystemMapper.selectAllByShopIdAndMinMaxState(shopId,minState,maxState);
    }

    /**
     * 查找满足状态的list
     * @param shopId
     * @param minState 可以取得到
     * @param maxState 可以取得到
     * @return
     */
    public Integer selectAllByShopIdAndMinMaxStateCount(@Param("0")Integer shopId,@Param("1")Integer minState,@Param("2")Integer maxState){
        return uOderSystemMapper.selectAllByShopIdAndMinMaxStateCount(shopId,minState,maxState);
    }
}
