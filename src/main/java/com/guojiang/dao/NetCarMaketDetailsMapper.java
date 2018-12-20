package com.guojiang.dao;

import com.guojiang.bean.NetCarMaketDetails;

public interface NetCarMaketDetailsMapper {
    int deleteByPrimaryKey(Integer nmdId);

    int insert(NetCarMaketDetails record);

    int insertSelective(NetCarMaketDetails record);

    NetCarMaketDetails selectByPrimaryKey(Integer nmdId);

    int updateByPrimaryKeySelective(NetCarMaketDetails record);

    int updateByPrimaryKey(NetCarMaketDetails record);

    /**
     * 通过I查找
     * @param I
     * @return
     */
    NetCarMaketDetails selectByI(Integer I);
}