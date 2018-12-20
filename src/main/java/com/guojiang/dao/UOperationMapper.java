package com.guojiang.dao;

import com.guojiang.bean.UOperation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UOperationMapper {
    int deleteByPrimaryKey(Integer uooId);

    int insert(UOperation record);

    int insertSelective(UOperation record);

    UOperation selectByPrimaryKey(Integer uooId);

    int updateByPrimaryKeySelective(UOperation record);

    int updateByPrimaryKey(UOperation record);

    List<UOperation> selectByOderId(Integer oderId);

    UOperation selectByOderIdAndState(@Param("0") Integer oderId, @Param("1") Integer state);
}