package com.guojiang.controller;

import com.guojiang.bean.*;
import com.guojiang.service.*;
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

@Transactional(rollbackFor=Throwable.class)
@Controller
public class SShopUpkeepSubServiceController {


    public static final Logger logger = LogManager.getLogger(SShopUpkeepSubServiceController.class);

    @Autowired
    private SShopUpkeepSubServiceService  sShopUpkeepSubServiceService;

    @Autowired
    private SShopTotalServiceService sShopTotalServiceService;

    @Autowired
    private SShopUpkeepServiceDetailsService sShopUpkeepServiceDetailsService;

    @Autowired
    private SShopUpkeepDetailsItemService sShopUpkeepDetailsItemService;

    @Autowired
    private SShopItemDetailsService sShopItemDetailsService;

    @Autowired
    private SShopItemDataService sShopItemDataService;


    /**
     * 查找所有的保养小类服务
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping("/api/FindAllUpkeepSubService")
    public ResultMsg FindAllUpkeepSubService(HttpSession session) {
        logger.info("-->FindAllUpkeepSubService->" + "查找所有的保养小类服务");
        ResultMsg result = new ResultMsg();
        SShopInfo shopInfo = (SShopInfo) session.getAttribute("curShopInfo");
        if (shopInfo == null) {
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoLogin, null);
        }
        else{
            List<SShopUpkeepSubService> list = sShopUpkeepSubServiceService.selectAllByShopId(shopInfo.getSpId(), EnumCode.CanUseCode.CanUse.ordinal());
            Map<String,Object> data = new HashMap<String, Object>();
            data.put("list",list);
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.Success,data);
        }

        return result;
    }


    /**
     * 发送套餐去平台审核
     * @param session
     * @param checkId
     * @return
     */
    @ResponseBody
    @RequestMapping("/api/SendSubServiceToCheck")
    public ResultMsg SendSubServiceToCheck(HttpSession session,
                                           @RequestParam("checkId") Integer checkId){
        logger.info("-->SendSubServiceToCheck->" + "发送套餐去平台审核"+checkId+" ");
        ResultMsg result = new ResultMsg();
        SShopInfo shopInfo = (SShopInfo) session.getAttribute("curShopInfo");
        if (shopInfo == null) {
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoLogin, null);
        }
        else if(checkId<=0){
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoServiceType, null);
        }
        else {
            SShopUpkeepSubService subService = sShopUpkeepSubServiceService.selectByPrimaryKey(checkId);
            if(subService == null){
                result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoServiceType, null);
            }
            else {
                boolean can = false;
                List<SShopUpkeepServiceDetails> detailsList = sShopUpkeepServiceDetailsService.selectAllBySubId(shopInfo.getSpId(),checkId,EnumCode.CanUseCode.CanUse.ordinal());
                if(detailsList!=null && detailsList.size()>0){
                    for (int i=0;i<detailsList.size();i++){
                        List<SShopItemDetails> itemList = sShopItemDetailsService.selectAllByTypeId(shopInfo.getSpId(), detailsList.get(i).getSpsdUkId(), EnumCode.CanUseCode.CanUse.ordinal());
                        List<SShopUpkeepDetailsItem> listTemp = sShopUpkeepDetailsItemService.selectAllByDetailId(shopInfo.getSpId(),detailsList.get(i).getSpsdId());
                        if(itemList!=null && itemList.size()>0 && listTemp!=null && listTemp.size()>0){
                            for (int k=0;k<itemList.size();k++){
                                for (int n=0;n<listTemp.size();n++){
                                    if(itemList.get(k).getSshdId() == listTemp.get(n).getSuksdiSshdId()){
                                        can = true;
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
                if(can){
                    Long time = new Date().getTime();
                    subService.setUkssCreatetime(time);
                    subService.setUkssCheckstate(EnumCode.CheckCodeState.Checking.ordinal());
                    sShopUpkeepSubServiceService.updateByPrimaryKey(subService);
                    List<SShopUpkeepSubService> list = sShopUpkeepSubServiceService.selectAllByShopId(shopInfo.getSpId(), EnumCode.CanUseCode.CanUse.ordinal());
                    Map<String,Object> data = new HashMap<String, Object>();
                    data.put("list",list);
                    result.SetResult(ResultCodeKeyEnum.ResultCodeKey.Success,data);
                }
                else {
                    result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoAnyService,null);
                }
            }
        }
        return result;
    }

    /**
     *  添加一个保养套餐
     * @param session
     * @param totalServiceId 商家自己的大类服务id
     * @param subName 套餐名字
     * @param price 价格
     * @return
     */
    @ResponseBody
    @RequestMapping("/api/AddOneUpkeepSubService")
    public ResultMsg AddOneUpkeepSubService(HttpSession session,
                                            @RequestParam("totalServiceId") Integer totalServiceId,
                                            @RequestParam("subName") String subName,
                                            @RequestParam("price") Long price){
        logger.info("-->AddOneUpkeepSubService->" + "添加一个保养套餐"+totalServiceId+" "+subName+" "+price);
        ResultMsg result = new ResultMsg();
        SShopInfo shopInfo = (SShopInfo) session.getAttribute("curShopInfo");
        if (shopInfo == null) {
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoLogin, null);
        }
        else if(totalServiceId<=0){
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoServiceType, null);
        }
        else if(subName == null || subName.trim() == "" || subName.trim().length()<=0){
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoServiceName, null);
        }
        else if(price<=0){
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.LetPositiveNumber, null);
        }
        else {
            SShopTotalService total = sShopTotalServiceService.selectByPrimaryKey(totalServiceId);

            if(total == null){
                result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoServiceType, null);
            }
            else {
                long time=new Date().getTime();
                SShopUpkeepSubService service = new SShopUpkeepSubService();
                service.setUkssShopId(shopInfo.getSpId());
                service.setUkssStsId(totalServiceId);
                service.setUkssState(EnumCode.CanUseCode.CanUse.ordinal());
                service.setUkssUkname(subName);
                service.setUkssMoney(price);
                service.setUkssCreatetime(time);
                service.setUkssCheckstate(EnumCode.CheckCodeState.Nil.ordinal());

                sShopUpkeepSubServiceService.insert(service);

                List<SShopUpkeepSubService> list = sShopUpkeepSubServiceService.selectAllByShopId(shopInfo.getSpId(), EnumCode.CanUseCode.CanUse.ordinal());
                Map<String,Object> data = new HashMap<String, Object>();
                data.put("list",list);
                result.SetResult(ResultCodeKeyEnum.ResultCodeKey.Success,data);
            }
        }

        return result;
    }

    /**
     * 删除一个保养子类服务
     * @param session
     * @param deleteId
     * @return
     */
    @ResponseBody
    @RequestMapping("/api/VirtualDeleteUpkeepSubServiceById")
    public ResultMsg VirtualDeleteUpkeepSubServiceById(HttpSession session,
                                                       @RequestParam("deleteId") Integer deleteId){
        logger.info("-->VirtualDeleteUpkeepSubServiceById->" + "删除一个保养子类服务"+deleteId+" ");
        ResultMsg result = new ResultMsg();
        SShopInfo shopInfo = (SShopInfo) session.getAttribute("curShopInfo");
        if (shopInfo == null) {
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoLogin, null);
        }
        else if(deleteId <=0){
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoServiceType, null);
        }
        else {
            SShopUpkeepSubService sqlService = sShopUpkeepSubServiceService.selectByPrimaryKey(deleteId);
            if(sqlService == null){
                result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoServiceType, null);
            }
            else {
                sqlService.setUkssState(EnumCode.CanUseCode.CanNotUse.ordinal());
                sShopUpkeepSubServiceService.updateByPrimaryKey(sqlService);

                List<SShopUpkeepSubService> list = sShopUpkeepSubServiceService.selectAllByShopId(shopInfo.getSpId(), EnumCode.CanUseCode.CanUse.ordinal());
                Map<String,Object> data = new HashMap<String, Object>();
                data.put("list",list);
                result.SetResult(ResultCodeKeyEnum.ResultCodeKey.Success,data);
            }
        }

        return result;
    }


    /**
     * 更新一个保养子类服务
     * @param session
     * @param updateId
     * @param subName
     * @param price
     * @return
     */
    @ResponseBody
    @RequestMapping("/api/UpdateUpkeepSubServiceById")
    public ResultMsg UpdateUpkeepSubServiceById(HttpSession session,
                                                @RequestParam("updateId") Integer updateId,
                                                @RequestParam("subName") String subName,
                                                @RequestParam("price") Long price){
        logger.info("-->UpdateUpkeepSubServiceById->" + "更新一个保养子类服务"+updateId+" "+subName+" "+price);
        ResultMsg result = new ResultMsg();
        SShopInfo shopInfo = (SShopInfo) session.getAttribute("curShopInfo");
        if (shopInfo == null) {
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoLogin, null);
        }
        else if(updateId <=0){
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoServiceType, null);
        }
        else if(subName == null || subName.trim() == "" || subName.trim().length()<=0){
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoServiceName, null);
        }
        else if(price<=0){
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.LetPositiveNumber, null);
        }
        else {
            SShopUpkeepSubService sqlService = sShopUpkeepSubServiceService.selectByPrimaryKey(updateId);
            if(sqlService == null){
                result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoServiceType, null);
            }
            else if(sqlService.getUkssCheckstate() == EnumCode.CheckCodeState.Checking.ordinal()){
                result.SetResult(ResultCodeKeyEnum.ResultCodeKey.Checking, null);
            }
            else {
                long time=new Date().getTime();
                sqlService.setUkssUkname(subName);
                sqlService.setUkssMoney(price);
                sqlService.setUkssCreatetime(time);
                sqlService.setUkssCheckstate(EnumCode.CheckCodeState.Nil.ordinal());
                sShopUpkeepSubServiceService.updateByPrimaryKey(sqlService);

                List<SShopUpkeepSubService> list = sShopUpkeepSubServiceService.selectAllByShopId(shopInfo.getSpId(), EnumCode.CanUseCode.CanUse.ordinal());
                Map<String,Object> data = new HashMap<String, Object>();
                data.put("list",list);
                result.SetResult(ResultCodeKeyEnum.ResultCodeKey.Success,data);
            }
        }

        return result;
    }


}
