package com.guojiang.service;

import com.guojiang.bean.NetCarMaketDetails;
import com.guojiang.dao.NetCarMaketDetailsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.nio.ch.Net;

@Service
public class NetCarMaketDetailsService {

    @Autowired
    private NetCarMaketDetailsMapper netCarMaketDetailsMapper;

    public int deleteByPrimaryKey(Integer nmdId){
        return netCarMaketDetailsMapper.deleteByPrimaryKey(nmdId);
    }

    public int insert(NetCarMaketDetails record){
        return netCarMaketDetailsMapper.insert(record);
    }

    public int insertSelective(NetCarMaketDetails record){
        return netCarMaketDetailsMapper.insertSelective(record);
    }

    public NetCarMaketDetails selectByPrimaryKey(Integer nmdId){
        return netCarMaketDetailsMapper.selectByPrimaryKey(nmdId);
    }

    public int updateByPrimaryKeySelective(NetCarMaketDetails record){
        return netCarMaketDetailsMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(NetCarMaketDetails record){
        return netCarMaketDetailsMapper.updateByPrimaryKey(record);
    }

    /**
     * 通过I查找
     * @param I
     * @return
     */
    public NetCarMaketDetails selectByI(Integer I){
        return netCarMaketDetailsMapper.selectByI(I);
    }

}
