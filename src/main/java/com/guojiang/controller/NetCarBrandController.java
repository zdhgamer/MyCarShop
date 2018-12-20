package com.guojiang.controller;

import com.guojiang.bean.NetCarBrand;
import com.guojiang.service.NetCarBrandService;
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
public class NetCarBrandController {

    public static final Logger logger = LogManager.getLogger(NetCarBrandController.class);

    @Autowired
    private NetCarBrandService netCarBrandService;

    /**
     * 获取所有车型
     * @return
     */
    @ResponseBody
    @RequestMapping("/api/GetAllCanUseCar")
    public ResultMsg GetAllCanUseCar(){
        logger.info("-->GetAllCanUseCar-->"+"获取所有车型");
        List<NetCarBrand> list = netCarBrandService.selectAll();
        ResultMsg resultMsg = new ResultMsg();
        HashMap<String,Object> data = new HashMap<String, Object>();
        data.put("List",list);
        resultMsg.SetResult(ResultCodeKeyEnum.ResultCodeKey.Success,data);
        return resultMsg;
    }

    /**
     * 通过车辆id查询
     * @param carId
     * @return
     */
    @ResponseBody
    @RequestMapping("/api/GetCanInfoByCarId")
    public ResultMsg GetCanInfoByCarId(@RequestParam("carId") Integer carId){
        logger.info("-->GetCanInfoByCarId-->"+"通过车辆id查询"+carId);
        NetCarBrand info = netCarBrandService.selectByCarId(carId);
        ResultMsg resultMsg = new ResultMsg();
        HashMap<String,Object> data = new HashMap<String, Object>();
        data.put("info",info);
        resultMsg.SetResult(ResultCodeKeyEnum.ResultCodeKey.Success,data);
        return resultMsg;
    }

}
