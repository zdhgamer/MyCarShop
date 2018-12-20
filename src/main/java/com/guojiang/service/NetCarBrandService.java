package com.guojiang.service;

import com.guojiang.bean.NetCarBrand;
import com.guojiang.dao.NetCarBrandMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NetCarBrandService {

    @Autowired
    private NetCarBrandMapper netCarBrandMapper;

    public int deleteByPrimaryKey(Integer ncbId){
        return netCarBrandMapper.deleteByPrimaryKey(ncbId);
    }

    public int insert(NetCarBrand record){
        return netCarBrandMapper.insert(record);
    }

    public int insertSelective(NetCarBrand record){
        return netCarBrandMapper.insertSelective(record);
    }

    public NetCarBrand selectByPrimaryKey(Integer ncbId){
        return netCarBrandMapper.selectByPrimaryKey(ncbId);
    }

    public int updateByPrimaryKeySelective(NetCarBrand record){
        return netCarBrandMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(NetCarBrand record){
        return netCarBrandMapper.updateByPrimaryKey(record);
    }

    /**
     * 通过carId查找
     * @param carId
     * @return
     */
    public NetCarBrand selectByCarId(Integer carId){
        return netCarBrandMapper.selectByCarId(carId);
    }

    /**
     * 查询所有
     * @return
     */
    public List<NetCarBrand> selectAll(){
        return netCarBrandMapper.selectAll();
    }

}
