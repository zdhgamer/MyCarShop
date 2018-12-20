package com.guojiang.dao;

import com.guojiang.bean.USubService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface USubServiceMapper {
    int deleteByPrimaryKey(Integer ussId);

    int insert(USubService record);

    int insertSelective(USubService record);

    USubService selectByPrimaryKey(Integer ussId);

    int updateByPrimaryKeySelective(USubService record);

    int updateByPrimaryKey(USubService record);

    List<USubService> selectByOderId(Integer oderId);

    List<USubService> selectByOderIdAndTotal(@Param("0")Integer oderId,@Param("1") Integer totalId);
}