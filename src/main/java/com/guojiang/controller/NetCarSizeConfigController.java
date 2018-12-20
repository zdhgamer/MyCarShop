package com.guojiang.controller;

import com.guojiang.bean.NetCarSizeConfig;
import com.guojiang.bean.TestTable;
import com.guojiang.service.NetCarSizeConfigService;
import com.guojiang.util.ResultCodeKeyEnum;
import com.guojiang.util.ResultMsg;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional(rollbackFor=Throwable.class)
@Controller
public class NetCarSizeConfigController {

    public static final Logger logger = LogManager.getLogger(NetCarSizeConfigController.class);

    @Autowired
    private NetCarSizeConfigService netCarSizeConfigService;


    /**
     * 查询所有的车的大小分类
     * @return
     */
    @ResponseBody
    @RequestMapping("/api/FindAllCarSizeConfig")
    public ResultMsg FindAllCarSizeConfig(){
        logger.info("-->FindAllCarSizeConfig->"+"查询所有的车的大小分类");
        ResultMsg result = new ResultMsg();
        List<NetCarSizeConfig> list = netCarSizeConfigService.selectAll();
        Map<String,Object> data = new HashMap<String, Object>();
        data.put("list",list);
        result.SetResult(ResultCodeKeyEnum.ResultCodeKey.Success,data);
        return result;
    }

}
