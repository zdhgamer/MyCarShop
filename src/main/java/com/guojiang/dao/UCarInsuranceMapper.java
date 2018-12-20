package com.guojiang.dao;

import com.guojiang.bean.UCarInsurance;

public interface UCarInsuranceMapper {
    int deleteByPrimaryKey(Integer ciId);

    int insert(UCarInsurance record);

    int insertSelective(UCarInsurance record);

    UCarInsurance selectByPrimaryKey(Integer ciId);

    int updateByPrimaryKeySelective(UCarInsurance record);

    int updateByPrimaryKey(UCarInsurance record);
}