package com.guojiang.dao;

import com.guojiang.bean.NetCarBrand;

import java.util.List;

public interface NetCarBrandMapper {
    int deleteByPrimaryKey(Integer ncbId);

    int insert(NetCarBrand record);

    int insertSelective(NetCarBrand record);

    NetCarBrand selectByPrimaryKey(Integer ncbId);

    int updateByPrimaryKeySelective(NetCarBrand record);

    int updateByPrimaryKey(NetCarBrand record);

    /**
     * 通过carId查找
     * @param carId
     * @return
     */
    NetCarBrand selectByCarId(Integer carId);

    /**
     * 查询所有
     * @return
     */
    List<NetCarBrand> selectAll();
}