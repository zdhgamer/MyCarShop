package com.guojiang.dao;

import com.guojiang.bean.UAdressTable;

public interface UAdressTableMapper {
    int deleteByPrimaryKey(Integer uaId);

    int insert(UAdressTable record);

    int insertSelective(UAdressTable record);

    UAdressTable selectByPrimaryKey(Integer uaId);

    int updateByPrimaryKeySelective(UAdressTable record);

    int updateByPrimaryKey(UAdressTable record);
}