package com.guojiang.dao;

import com.guojiang.bean.PBanner;

public interface PBannerMapper {
    int deleteByPrimaryKey(Integer pbId);

    int insert(PBanner record);

    int insertSelective(PBanner record);

    PBanner selectByPrimaryKey(Integer pbId);

    int updateByPrimaryKeySelective(PBanner record);

    int updateByPrimaryKey(PBanner record);
}