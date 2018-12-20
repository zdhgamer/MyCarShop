package com.guojiang.controller;

import com.guojiang.bean.NetCarDetailsData;
import com.guojiang.bean.NetCarMaketDetails;
import com.guojiang.service.NetCarMaketDetailsService;
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
import sun.nio.ch.Net;

import java.util.HashMap;

@Transactional(rollbackFor=Throwable.class)
@Controller
public class NetCarMaketDetailsController {

    public static final Logger logger = LogManager.getLogger(NetCarMaketDetailsController.class);

    @Autowired
    private NetCarMaketDetailsService netCarMaketDetailsService;


    /**
     * 查询当前子类车下的详细
     * @param carIId
     * @return
     */
    @ResponseBody
    @RequestMapping("/api/GetCarDetailInfo")
    public ResultMsg GetCarDetailInfo(@RequestParam("carIId") Integer carIId){
        logger.info("-->GetCarDetailInfo-->"+"查询当前子类车下的详细"+carIId);
        NetCarMaketDetails info = netCarMaketDetailsService.selectByI(carIId);
        ResultMsg resultMsg = new ResultMsg();
        HashMap<String,Object> data = new HashMap<String, Object>();
        data.put("info",info);
        resultMsg.SetResult(ResultCodeKeyEnum.ResultCodeKey.Success,data);
        return resultMsg;
    }

}
