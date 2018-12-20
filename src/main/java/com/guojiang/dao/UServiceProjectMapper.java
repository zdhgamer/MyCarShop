package com.guojiang.dao;

import com.guojiang.bean.UServiceProject;

import java.util.List;

public interface UServiceProjectMapper {
    int deleteByPrimaryKey(Integer uspId);

    int insert(UServiceProject record);

    int insertSelective(UServiceProject record);

    UServiceProject selectByPrimaryKey(Integer uspId);

    int updateByPrimaryKeySelective(UServiceProject record);

    int updateByPrimaryKey(UServiceProject record);

    List<UServiceProject> selectByOderId(Integer oderId);
}