package com.guojiang.dao;

import com.guojiang.bean.NetCarDetailsData;

import java.util.List;

public interface NetCarDetailsDataMapper {
    int deleteByPrimaryKey(Integer ncdId);

    int insert(NetCarDetailsData record);

    int insertSelective(NetCarDetailsData record);

    NetCarDetailsData selectByPrimaryKey(Integer ncdId);

    int updateByPrimaryKeySelective(NetCarDetailsData record);

    int updateByPrimaryKey(NetCarDetailsData record);

    /**
     * 通过I查找
     * @param I
     * @return
     */
    NetCarDetailsData selectByI(Integer I);

    /**
     * 查找所有
     * @return
     */
    List<NetCarDetailsData> selectAll();
}