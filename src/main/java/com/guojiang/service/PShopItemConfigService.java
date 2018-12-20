package com.guojiang.service;

import com.guojiang.bean.PShopItemConfig;
import com.guojiang.dao.PShopItemConfigMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PShopItemConfigService {

    @Autowired
    private PShopItemConfigMapper pShopItemConfigMapper;


    public int deleteByPrimaryKey(Integer pshcId){
        return pShopItemConfigMapper.deleteByPrimaryKey(pshcId);
    }

    public int insert(PShopItemConfig record){
        return pShopItemConfigMapper.insert(record);
    }

    public int insertSelective(PShopItemConfig record){
        return pShopItemConfigMapper.insertSelective(record);
    }

    public PShopItemConfig selectByPrimaryKey(Integer pshcId){
        return pShopItemConfigMapper.selectByPrimaryKey(pshcId);
    }

    public int updateByPrimaryKeySelective(PShopItemConfig record){
        return pShopItemConfigMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(PShopItemConfig record){
        return pShopItemConfigMapper.updateByPrimaryKey(record);
    }

    /**
     * 查找所有配置信息
     * @return
     */
    public List<PShopItemConfig> selectAllConfig(){
        return pShopItemConfigMapper.selectAllConfig();
    }
}
