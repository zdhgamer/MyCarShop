package com.guojiang.controller;

import com.guojiang.bean.SShopInfo;
import com.guojiang.bean.UInvoiceTable;
import com.guojiang.bean.UOderSystem;
import com.guojiang.service.UInvoiceTableService;
import com.guojiang.service.UOderSystemService;
import com.guojiang.util.EnumCode;
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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 订单控制器
 */
@Transactional(rollbackFor=Throwable.class)
@Controller
public class UInvoiceTableController {

    public static final Logger logger = LogManager.getLogger(UOderSystemController.class);

    @Autowired
    private UInvoiceTableService uInvoiceTableService;

    @Autowired
    private UOderSystemService uOderSystemService;

    /**
     * 通过订单id查询发票信息
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping("/api/FindInvoiceByOderId")
    public ResultMsg FindInvoiceByOderId(HttpSession session,
                                         @RequestParam("oderId") Integer oderId){
        logger.info("-->FindInvoiceByOderId->" + "查找所有的已完成订单");
        ResultMsg result = new ResultMsg();
        SShopInfo shopInfo = (SShopInfo) session.getAttribute("curShopInfo");
        if (shopInfo == null) {
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoLogin, null);
        }
        else if(oderId<0){
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoOder, null);
        }
        else {
            UOderSystem oder = uOderSystemService.selectByPrimaryKey(oderId);
            if(oder == null){
                result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoOder, null);
            }
            else {
                UInvoiceTable invoice = uInvoiceTableService.selectByOderId(oderId);
                Map<String,Object> data = new HashMap<String, Object>();
                data.put("invoice",invoice);
                result.SetResult(ResultCodeKeyEnum.ResultCodeKey.Success, data);
            }
        }
        return result;
    }


}
