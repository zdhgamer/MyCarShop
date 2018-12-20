package com.guojiang.controller;

import com.guojiang.bean.*;
import com.guojiang.service.NetCarSizeConfigService;
import com.guojiang.service.SShopCosmetologyDetailService;
import com.guojiang.service.SShopCosmetologyServiceService;
import com.guojiang.service.SShopTotalServiceService;
import com.guojiang.util.EnumCode;
import com.guojiang.util.ResultCodeKeyEnum;
import com.guojiang.util.ResultMsg;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
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
public class SShopCosmetologyServiceController {

    public static final Logger logger = LogManager.getLogger(SShopCosmetologyServiceController.class);

    @Autowired
    private SShopCosmetologyServiceService sShopCosmetologyServiceService;

    @Autowired
    private NetCarSizeConfigService netCarSizeConfigService;

    @Autowired
    private SShopTotalServiceService sShopTotalServiceService;

    @Autowired
    private SShopCosmetologyDetailService sShopCosmetologyDetailService;

    /**
     * 查询所有的美容服务
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping("/api/FindAllCosmetologyService")
    public ResultMsg FindAllCosmetologyService(HttpSession session){
        logger.info("-->FindAllCosmetologyService-->"+"查询所有的美容服务");
        ResultMsg result = new ResultMsg();
        SShopInfo shopInfo = (SShopInfo) session.getAttribute("curShopInfo");
        if (shopInfo == null){
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoLogin,null);
        }
        else {
            List<SShopCosmetologyService> list = sShopCosmetologyServiceService.selectAllByShopId(shopInfo.getSpId(), EnumCode.CanUseCode.CanUse.ordinal());
            Map<String,Object> data = new HashMap<String, Object>();
            data.put("list",list);
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.Success,data);
        }
        return result;
    }

    /**
     * 新增一个美容服务
     * @param session
     * @param serviceTypeId
     * @param carSizeId
     * @return
     */
    @ResponseBody
    @RequestMapping("/api/AddCosmetologyService")
    public ResultMsg AddCosmetologyService(HttpSession session,
                                           @RequestParam("serviceTypeId") Integer serviceTypeId,
                                           @RequestParam("carSizeId") Integer carSizeId){
        logger.info("-->AddCosmetologyService-->"+"新增一个美容服务"+serviceTypeId+" "+carSizeId+" ");
        ResultMsg result = new ResultMsg();
        SShopInfo shopInfo = (SShopInfo) session.getAttribute("curShopInfo");
        if (shopInfo == null){
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoLogin,null);
        }
        else if(serviceTypeId<=0){
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoServiceType,null);
        }
        else if(carSizeId<=0){
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoCarSize,null);
        }
        else {
            NetCarSizeConfig sizeConfig = netCarSizeConfigService.selectByPrimaryKey(carSizeId);
            SShopTotalService totalService = sShopTotalServiceService.selectByPrimaryKey(serviceTypeId);

            if(sizeConfig == null){
                result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoCarSize,null);
            }
            else if(totalService == null){
                result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoServiceType,null);
            }
            else {
                SShopCosmetologyService sqlService = sShopCosmetologyServiceService.selectByCarSize(shopInfo.getSpId(),carSizeId,EnumCode.CanUseCode.CanUse.ordinal());
                if(sqlService == null){
                    SShopCosmetologyService service = new SShopCosmetologyService();
                    service.setScsSpShopId(shopInfo.getSpId());
                    service.setScsState(EnumCode.CanUseCode.CanUse.ordinal());
                    service.setScsStsId(serviceTypeId);
                    service.setScsVmsId(carSizeId);
                    sShopCosmetologyServiceService.insert(service);
                    List<SShopCosmetologyService> list = sShopCosmetologyServiceService.selectAllByShopId(shopInfo.getSpId(), EnumCode.CanUseCode.CanUse.ordinal());
                    Map<String,Object> data = new HashMap<String, Object>();
                    data.put("list",list);
                    result.SetResult(ResultCodeKeyEnum.ResultCodeKey.Success,data);
                }
                else {
                    result.SetResult(ResultCodeKeyEnum.ResultCodeKey.ExistThisServiceType,null);
                }
            }
        }
        return result;
    }

    /**
     * 删除一个美容服务
     * @param session
     * @param deleteId
     * @return
     */
    @ResponseBody
    @RequestMapping("/api/VirtualDeleteCosmetologyById")
    public ResultMsg VirtualDeleteCosmetologyById(HttpSession session,
                                                  @RequestParam("deleteId") Integer deleteId){
        logger.info("-->VirtualDeleteCosmetologyById-->"+"删除一个美容服务"+deleteId+" "+" ");
        ResultMsg result = new ResultMsg();
        SShopInfo shopInfo = (SShopInfo) session.getAttribute("curShopInfo");
        if (shopInfo == null){
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoLogin,null);
        }
        else if(deleteId<=0){
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoServiceType,null);
        }
        else {
            SShopCosmetologyService service = sShopCosmetologyServiceService.selectByPrimaryKey(deleteId);
            if(service == null){
                result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoServiceType,null);
            }
            else {
                List<SShopCosmetologyDetail> list = sShopCosmetologyDetailService.selectByShopIdAndCosIdAndCheck(shopInfo.getSpId(),service.getScsId(),EnumCode.CheckCodeState.Checking.ordinal(),EnumCode.CanUseCode.CanUse.ordinal());
                if(list!=null && list.size()>0){
                    result.SetResult(ResultCodeKeyEnum.ResultCodeKey.ExistSubChecking,null);
                }
                else {
                    service.setScsState(EnumCode.CanUseCode.CanNotUse.ordinal());
                    sShopCosmetologyServiceService.updateByPrimaryKey(service);

                    List<SShopCosmetologyService> back = sShopCosmetologyServiceService.selectAllByShopId(shopInfo.getSpId(), EnumCode.CanUseCode.CanUse.ordinal());
                    Map<String,Object> data = new HashMap<String, Object>();
                    data.put("list",back);
                    result.SetResult(ResultCodeKeyEnum.ResultCodeKey.Success,data);
                }
            }
        }

        return result;
    }

}
