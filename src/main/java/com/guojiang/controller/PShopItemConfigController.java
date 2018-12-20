package com.guojiang.controller;

import com.guojiang.bean.PShopItemConfig;
import com.guojiang.bean.SShopInfo;
import com.guojiang.service.PShopItemConfigService;
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

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional(rollbackFor=Throwable.class)
@Controller
public class PShopItemConfigController {

    public static final Logger logger = LogManager.getLogger(PShopItemConfigController.class);

    @Autowired
    private PShopItemConfigService pShopItemConfigService;

    /**
     * 超找所有商品的配置信息
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping("/api/FindAllShopItemConfig")
    public ResultMsg FindAllShopItemConfig(HttpSession session){
        logger.info("-->FindAllShopItemConfig-->"+"超找所有商品的配置信息");
        ResultMsg result = new ResultMsg();
        SShopInfo shopInfo = (SShopInfo) session.getAttribute("curShopInfo");
        if(shopInfo == null){
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoLogin,null);
        }
        else {
            List<PShopItemConfig> list = pShopItemConfigService.selectAllConfig();
            Map<String,Object> data = new HashMap<String, Object>();
            data.put("configList",list);
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.Success,data);
        }
        return result;
    }

}
