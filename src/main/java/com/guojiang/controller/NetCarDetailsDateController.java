package com.guojiang.controller;

import com.guojiang.bean.NetCarBrand;
import com.guojiang.bean.NetCarDetailsData;
import com.guojiang.service.NetCarDetailsDataService;
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

@Transactional(rollbackFor=Throwable.class)
@Controller
public class NetCarDetailsDateController {

    public static final Logger logger = LogManager.getLogger(NetCarDetailsDateController.class);

    @Autowired
    private NetCarDetailsDataService netCarDetailsDataService;


    /**
     * 查询当前车下的子类
     * @param carId
     * @return
     */
    @ResponseBody
    @RequestMapping("/api/GetCarSubInfo")
    public ResultMsg GetCarSubInfo(@RequestParam("carId") Integer carId){
        logger.info("-->GetCarSubInfo-->"+"查询当前车下的子类"+carId);
        NetCarDetailsData info = netCarDetailsDataService.selectByI(carId);
        ResultMsg resultMsg = new ResultMsg();
        HashMap<String,Object> data = new HashMap<String, Object>();
        data.put("info",info);
        resultMsg.SetResult(ResultCodeKeyEnum.ResultCodeKey.Success,data);
        return resultMsg;
    }

    /**
     * 查询所有的子类车信息
     * @return
     */
    @ResponseBody
    @RequestMapping("/api/GetAllCarSubInfo")
    public ResultMsg GetAllCarSubInfo(){
        logger.info("-->GetAllCarSubInfo-->"+"查询所有的子类车信息");
        List<NetCarDetailsData> list = netCarDetailsDataService.selectAll();
        ResultMsg resultMsg = new ResultMsg();
        HashMap<String,Object> data = new HashMap<String, Object>();
        data.put("list",list);
        resultMsg.SetResult(ResultCodeKeyEnum.ResultCodeKey.Success,data);
        return resultMsg;
    }

}
