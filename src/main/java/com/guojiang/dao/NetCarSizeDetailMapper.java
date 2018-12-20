package com.guojiang.dao;

import com.guojiang.bean.NetCarSizeDetail;
import sun.nio.ch.Net;

import java.util.List;

public interface NetCarSizeDetailMapper {
    int deleteByPrimaryKey(Integer nsdId);

    int insert(NetCarSizeDetail record);

    int insertSelective(NetCarSizeDetail record);

    NetCarSizeDetail selectByPrimaryKey(Integer nsdId);

    int updateByPrimaryKeySelective(NetCarSizeDetail record);

    int updateByPrimaryKey(NetCarSizeDetail record);

    List<NetCarSizeDetail> selectBySizeId(Integer sizeId);

    NetCarSizeDetail selectBySeriesId(Integer seriesId);
}