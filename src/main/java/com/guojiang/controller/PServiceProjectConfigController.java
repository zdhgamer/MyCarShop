package com.guojiang.controller;

import com.guojiang.bean.PServiceProjectConfig;
import com.guojiang.service.PServiceProjectConfigService;
import com.guojiang.util.EnumCode;
import com.guojiang.util.ResultCodeKeyEnum;
import com.guojiang.util.ResultMsg;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional(rollbackFor=Throwable.class)
@Controller
public class PServiceProjectConfigController {
    public static final Logger logger = LogManager.getLogger(PServiceProjectConfigController.class);

    @Autowired
    private PServiceProjectConfigService pServiceProjectConfigService;

    /**
     * 查询所有服务的大类配置
     * @return
     */
    @ResponseBody
    @RequestMapping("/api/FindAllServiceProjectConfig")
    public ResultMsg FindAllServiceProjectConfig(){
        logger.info("-->FindAllServiceProjectConfig->>"+"查询所有服务的大类配置");
        ResultMsg result = new ResultMsg();
        List<PServiceProjectConfig> list = pServiceProjectConfigService.selectAllByType(EnumCode.ServiceBelong.Shop.ordinal());
        Map<String,Object> data = new HashMap<String, Object>();
        data.put("list",list);
        result.SetResult(ResultCodeKeyEnum.ResultCodeKey.Success,data);
        return result;
    }

}
