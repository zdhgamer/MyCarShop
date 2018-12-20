package com.guojiang.service;

import com.guojiang.bean.NetCarSizeDetail;
import com.guojiang.dao.NetCarSizeDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NetCarSizeDetailService {

    @Autowired
    private NetCarSizeDetailMapper netCarSizeDetailMapper;

    public int deleteByPrimaryKey(Integer nsdId){
        return netCarSizeDetailMapper.deleteByPrimaryKey(nsdId);
    }

    public int insert(NetCarSizeDetail record){
        return netCarSizeDetailMapper.insert(record);
    }

    public int insertSelective(NetCarSizeDetail record){
        return netCarSizeDetailMapper.insertSelective(record);
    }

    public NetCarSizeDetail selectByPrimaryKey(Integer nsdId){
        return netCarSizeDetailMapper.selectByPrimaryKey(nsdId);
    }

    public int updateByPrimaryKeySelective(NetCarSizeDetail record){
        return netCarSizeDetailMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(NetCarSizeDetail record){
        return netCarSizeDetailMapper.updateByPrimaryKey(record);
    }

    /**
     * 查找当前车型下面的所有车
     * @param sizeId
     * @return
     */
    public List<NetCarSizeDetail> selectBySizeId(Integer sizeId){
        return netCarSizeDetailMapper.selectBySizeId(sizeId);
    }

    /**
     * 通过车的seriesId查找车
     * @param seriesId
     * @return
     */
    public NetCarSizeDetail selectBySeriesId(Integer seriesId){
        return netCarSizeDetailMapper.selectBySeriesId(seriesId);
    }
}
