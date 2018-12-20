package com.guojiang.controller;

import com.guojiang.bean.NetCarSizeConfig;
import com.guojiang.bean.SShopInfo;
import com.guojiang.bean.SShopMaintainService;
import com.guojiang.bean.SShopTotalService;
import com.guojiang.service.NetCarSizeConfigService;
import com.guojiang.service.SShopMaintainServiceService;
import com.guojiang.service.SShopTotalServiceService;
import com.guojiang.util.CommonUtil;
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
public class SShopMaintainServiceController {

    public static final Logger logger = LogManager.getLogger(SShopMaintainServiceController.class);

    @Autowired
    private SShopMaintainServiceService sShopMaintainServiceService;

    @Autowired
    private NetCarSizeConfigService netCarSizeConfigService;

    @Autowired
    private SShopTotalServiceService sShopTotalServiceService;

    /**
     * 查找所有的维修服务
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping("/api/FindAllMaintainService")
    public ResultMsg FindAllMaintainService(HttpSession session) {
        logger.info("-->FindAllMaintainService->" + "查找所有的维修服务");
        ResultMsg result = new ResultMsg();
        SShopInfo shopInfo = (SShopInfo) session.getAttribute("curShopInfo");
        if (shopInfo == null) {
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoLogin, null);
        }
        else{
            List<SShopMaintainService> list = sShopMaintainServiceService.selectAllByShopId(shopInfo.getSpId(), EnumCode.CanUseCode.CanUse.ordinal());
            Map<String,Object> data = new HashMap<String, Object>();
            data.put("list",list);
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.Success,data);
        }

        return result;
    }

    /**
     * 新增一个维修预约服务
     * @param session
     * @param carSizeId
     * @param price
     * @return
     */
    @ResponseBody
    @RequestMapping("/api/AddNewMaintainServiceByConfigId")
    public ResultMsg AddNewMaintainServiceByConfigId(HttpSession session,
                                                     @RequestParam("totalServiceId") Integer totalServiceId,
                                                     @RequestParam("carSizeId") Integer carSizeId,
                                                     @RequestParam("price") String price){
        logger.info("-->AddNewMaintainServiceByConfigId->" + "新增一个维修预约服务"+totalServiceId+" "+carSizeId+" "+price);
        ResultMsg result = new ResultMsg();
        SShopInfo shopInfo = (SShopInfo) session.getAttribute("curShopInfo");
        if (shopInfo == null) {
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoLogin, null);
        }
        else if(totalServiceId<=0){
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoServiceType, null);
        }
        else if(carSizeId<=0){
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoCarSize, null);
        }
        else if(price == null || price.trim().equals("") || price.trim().length()<0){
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoPrice, null);
        }
        else {

            SShopTotalService total = sShopTotalServiceService.selectByPrimaryKey(totalServiceId);
            if(total == null){
                result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoServiceType, null);
            }
            else {
                NetCarSizeConfig config = netCarSizeConfigService.selectByPrimaryKey(carSizeId);
                if(config == null){
                    result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoCarSize, null);
                }
                else {
                    SShopMaintainService sqlService = sShopMaintainServiceService.selectByCarSizeId(shopInfo.getSpId(),carSizeId,EnumCode.CanUseCode.CanUse.ordinal());

                    if(sqlService == null){
                        Long lPrice = Long.parseLong(0+"");
                        try {
                            lPrice = Long.parseLong(price);
                        }
                        catch (NumberFormatException e){
                            logger.info("-->AddNewMaintainServiceByConfigId->"+e);
                            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoPrice,null);
                            throw new RuntimeException(result.toString());
                        }

                        if(lPrice<=0){
                            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.LetPositiveNumber,null);
                            return result;
                        }
                        else {
                            long time=new Date().getTime();
                            SShopMaintainService service = new SShopMaintainService();
                            service.setSmoCarsizeId(carSizeId);
                            service.setSmoCheckstate(EnumCode.CheckCodeState.Checking.ordinal());
                            service.setSmoCreatetime(time);
                            service.setSmoOrdermoney(lPrice);
                            service.setSmoShopId(shopInfo.getSpId());
                            service.setSmoState(EnumCode.CanUseCode.CanUse.ordinal());
                            service.setSmoStsId(totalServiceId);

                            sShopMaintainServiceService.insert(service);

                            List<SShopMaintainService> list = sShopMaintainServiceService.selectAllByShopId(shopInfo.getSpId(), EnumCode.CanUseCode.CanUse.ordinal());
                            Map<String,Object> data = new HashMap<String, Object>();
                            data.put("list",list);
                            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.Success,data);
                        }
                    }
                    else {
                        result.SetResult(ResultCodeKeyEnum.ResultCodeKey.ExistThisServiceType,null);
                    }
                }
            }
        }

        return result;
    }


    /**
     * 更新一个维修预约
     * @param session
     * @param updateId
     * @param price
     * @return
     */
    @ResponseBody
    @RequestMapping("/api/UpdateMaintainServiceById")
    public ResultMsg UpdateMaintainServiceById(HttpSession session,
                                                     @RequestParam("updateId") Integer updateId,
                                               @RequestParam("price") String price){
        logger.info("-->UpdateMaintainServiceById->" + "更新一个维修预约"+" " + updateId +" "+ price);
        ResultMsg result = new ResultMsg();
        SShopInfo shopInfo = (SShopInfo) session.getAttribute("curShopInfo");
        if (shopInfo == null) {
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoLogin, null);
        }
        else if(updateId<=0){
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoServiceType, null);
        }
        else if(price == null || price.trim().equals("") || price.trim().length()<=0){
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoPrice, null);
        }
        else {
            SShopMaintainService service = sShopMaintainServiceService.selectByPrimaryKey(updateId);
            if(service == null){
                result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoServiceType, null);
            }
            else if(service.getSmoCheckstate() == EnumCode.CheckCodeState.Checking.ordinal() ||
                    service.getSmoCheckstate().equals(EnumCode.CheckCodeState.Checking.ordinal())){
                result.SetResult(ResultCodeKeyEnum.ResultCodeKey.Checking, null);
            }else {
                Long lPrice = Long.parseLong(0+"");
                try {
                    lPrice = Long.parseLong(price);
                }
                catch (NumberFormatException e){
                    logger.info("-->UpdateMaintainServiceById->"+e);
                    result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoPrice,null);
                    throw new RuntimeException(result.toString());
                }

                if(lPrice<=0){
                    result.SetResult(ResultCodeKeyEnum.ResultCodeKey.LetPositiveNumber,null);
                    return result;
                }
                else {
                    long time=new Date().getTime();
                    service.setSmoCreatetime(time);
                    service.setSmoOrdermoney(lPrice);
                    service.setSmoCheckstate(EnumCode.CheckCodeState.Checking.ordinal());
                    sShopMaintainServiceService.updateByPrimaryKey(service);
                    List<SShopMaintainService> list = sShopMaintainServiceService.selectAllByShopId(shopInfo.getSpId(), EnumCode.CanUseCode.CanUse.ordinal());
                    Map<String,Object> data = new HashMap<String, Object>();
                    data.put("list",list);
                    result.SetResult(ResultCodeKeyEnum.ResultCodeKey.Success,data);
                }
            }
        }

        return result;
    }

    /**
     * 删除一个预约维修
     * @param session
     * @param deleteId
     * @return
     */
    @ResponseBody
    @RequestMapping("/api/VirtualDeleteMaintainServiceById")
    public ResultMsg VirtualDeleteMaintainServiceById(HttpSession session,
                                                      @RequestParam("deleteId") Integer deleteId){
        logger.info("-->VirtualDeleteMaintainServiceById->" + "删除一个预约维修"+" " + deleteId +" ");
        ResultMsg result = new ResultMsg();
        SShopInfo shopInfo = (SShopInfo) session.getAttribute("curShopInfo");
        if (shopInfo == null) {
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoLogin, null);
        }
        else if(deleteId<=0){
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoServiceType, null);
        }
        else {

            SShopMaintainService service = sShopMaintainServiceService.selectByPrimaryKey(deleteId);
            if(service == null){
                result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoServiceType, null);
            }
            else if(service.getSmoCheckstate() == EnumCode.CheckCodeState.Checking.ordinal() ||
                    service.getSmoCheckstate().equals(EnumCode.CheckCodeState.Checking.ordinal())) {
                result.SetResult(ResultCodeKeyEnum.ResultCodeKey.Checking, null);
            }
            else {
                service.setSmoState(EnumCode.CanUseCode.CanNotUse.ordinal());
                sShopMaintainServiceService.updateByPrimaryKey(service);
                List<SShopMaintainService> list = sShopMaintainServiceService.selectAllByShopId(shopInfo.getSpId(), EnumCode.CanUseCode.CanUse.ordinal());
                Map<String,Object> data = new HashMap<String, Object>();
                data.put("list",list);
                result.SetResult(ResultCodeKeyEnum.ResultCodeKey.Success,data);
            }
        }

        return result;
    }

}
