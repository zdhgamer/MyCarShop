package com.guojiang.dao;

import com.guojiang.bean.PHelpCarService;

public interface PHelpCarServiceMapper {
    int deleteByPrimaryKey(Integer phsId);

    int insert(PHelpCarService record);

    int insertSelective(PHelpCarService record);

    PHelpCarService selectByPrimaryKey(Integer phsId);

    int updateByPrimaryKeySelective(PHelpCarService record);

    int updateByPrimaryKey(PHelpCarService record);
}