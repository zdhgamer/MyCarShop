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
public class SShopUpkeepServiceDetailsController {

    public static final Logger logger = LogManager.getLogger(SShopUpkeepServiceDetailsController.class);

    @Autowired
    private SShopUpkeepServiceDetailsService sShopUpkeepServiceDetailsService;

    @Autowired
    private SShopUpkeepSubServiceService sShopUpkeepSubServiceService;

    @Autowired
    private SShopItemDataService sShopItemDataService;

    @Autowired
    private PShopItemConfigService pShopItemConfigService;

    /**
     * 查找所有的保养详细类服务
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping("/api/FindAllUpkeepDetailService")
    public ResultMsg FindAllUpkeepDetailService(HttpSession session,
                                                @RequestParam("subId") Integer subId) {
        logger.info("-->FindAllUpkeepDetailService->" + "查找所有的保养详细类服务"+" "+subId);
        ResultMsg result = new ResultMsg();
        SShopInfo shopInfo = (SShopInfo) session.getAttribute("curShopInfo");
        if (shopInfo == null) {
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoLogin, null);
        }
        else if(subId<=0){
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoServiceType,null);
        }
        else{
            SShopUpkeepSubService service = sShopUpkeepSubServiceService.selectByPrimaryKey(subId);
            if(service == null){
                result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoServiceType,null);
            }
            else {
                List<SShopUpkeepServiceDetails> list = sShopUpkeepServiceDetailsService.selectAllBySubId(shopInfo.getSpId(),subId,EnumCode.CanUseCode.CanUse.ordinal());
                if(list!=null){
                    for (int i=0;i<list.size();i++){
                        SShopItemData cc = sShopItemDataService.selectByPrimaryKey(list.get(i).getSpsdUkId());
                        list.get(i).getConfig().setpShopItemConfig(cc.getpShopItemConfig());
                    }
                }

                Map<String,Object> data = new HashMap<String, Object>();
                data.put("list",list);
                result.SetResult(ResultCodeKeyEnum.ResultCodeKey.Success,data);
            }
        }

        return result;
    }

    /**
     * 增加一个详细保养服务
     * @param session
     * @param subId
     * @param detailId 商品类别id
     * @return
     */
    @ResponseBody
    @RequestMapping("/api/AddOneUpkeepDetailService")
    public ResultMsg AddOneUpkeepDetailService(HttpSession session,
                                               @RequestParam("subId") Integer subId,
                                               @RequestParam("detailId") Integer detailId,
                                               @RequestParam(value = "finishTime",required = false) Integer finishTime){
        logger.info("-->AddOneUpkeepDetailService->" + "增加一个详细保养服务"+" "+subId+" " + detailId+" "+finishTime);
        ResultMsg result = new ResultMsg();
        SShopInfo shopInfo = (SShopInfo) session.getAttribute("curShopInfo");
        if (shopInfo == null) {
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoLogin, null);
        }
        else if(subId<=0){
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoServiceType,null);
        }
        else{
            SShopItemData config = sShopItemDataService.selectByPrimaryKey(detailId);
            SShopUpkeepSubService service = sShopUpkeepSubServiceService.selectByPrimaryKey(subId);
            if(service == null){
                result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoServiceType,null);
            }
            else if(service.getUkssCheckstate() == EnumCode.CheckCodeState.Checking.ordinal()){
                result.SetResult(ResultCodeKeyEnum.ResultCodeKey.Checking,null);
            }
            else if(config == null){
                result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoServiceType,null);
            }
            else {
                SShopUpkeepServiceDetails sqlService = sShopUpkeepServiceDetailsService.selectByDetailId(shopInfo.getSpId(),subId,detailId,EnumCode.CanUseCode.CanUse.ordinal());
                if(sqlService == null){
                    long time=new Date().getTime();
                    SShopUpkeepServiceDetails newService = new SShopUpkeepServiceDetails();
                    newService.setSpsdShopId(shopInfo.getSpId());
                    newService.setSpsdStsId(service.getUkssStsId());
                    newService.setSpsdUkssId(subId);
                    newService.setSpsdUkId(detailId);
                    newService.setSpsdState(EnumCode.CanUseCode.CanUse.ordinal());
                    newService.setSpsdCreatetime(time);
                    newService.setSpsdCheckstate(EnumCode.CheckCodeState.Nil.ordinal());
                    sShopUpkeepServiceDetailsService.insert(newService);

                    service.setUkssCheckstate(EnumCode.CheckCodeState.Nil.ordinal());
                    sShopUpkeepSubServiceService.updateByPrimaryKey(service);

                    List<SShopUpkeepServiceDetails> list = sShopUpkeepServiceDetailsService.selectAllBySubId(shopInfo.getSpId(),subId,EnumCode.CanUseCode.CanUse.ordinal());
                    if(list!=null){
                        for (int i=0;i<list.size();i++){
                            SShopItemData cc = sShopItemDataService.selectByPrimaryKey(list.get(i).getSpsdUkId());
                            list.get(i).getConfig().setpShopItemConfig(cc.getpShopItemConfig());
                        }
                    }
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
     * 删除一个详细保养服务
     * @param session
     * @param deleteId
     * @return
     */
    @ResponseBody
    @RequestMapping("/api/VirtualDeleteUpkeepDetailServiceById")
    public ResultMsg VirtualDeleteUpkeepDetailServiceById(HttpSession session,
                                                          @RequestParam("deleteId") Integer deleteId){
        logger.info("-->VirtualDeleteUpkeepDetailServiceById->" + "删除一个详细保养服务"+" "+deleteId+" ");
        ResultMsg result = new ResultMsg();
        SShopInfo shopInfo = (SShopInfo) session.getAttribute("curShopInfo");
        if (shopInfo == null) {
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoLogin, null);
        }
        else if(deleteId<=0){
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoServiceType,null);
        }
        else {
            SShopUpkeepServiceDetails serviceDetails = sShopUpkeepServiceDetailsService.selectByPrimaryKey(deleteId);
            if(serviceDetails == null){
                result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoServiceType,null);
            }
            else {
                SShopUpkeepSubService service = sShopUpkeepSubServiceService.selectByPrimaryKey(serviceDetails.getSpsdUkssId());
                if(service == null){
                    result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoServiceType,null);
                }
                else if(service.getUkssCheckstate() == EnumCode.CheckCodeState.Checking.ordinal()){
                    result.SetResult(ResultCodeKeyEnum.ResultCodeKey.Checking,null);
                }
                else {
                    if(service!=null){
                        service.setUkssCheckstate(EnumCode.CheckCodeState.Nil.ordinal());
                        sShopUpkeepSubServiceService.updateByPrimaryKey(service);
                    }
                    serviceDetails.setSpsdState(EnumCode.CanUseCode.CanNotUse.ordinal());
                    sShopUpkeepServiceDetailsService.updateByPrimaryKey(serviceDetails);
                    List<SShopUpkeepServiceDetails> list = sShopUpkeepServiceDetailsService.selectAllBySubId(shopInfo.getSpId(),serviceDetails.getSpsdUkssId(),EnumCode.CanUseCode.CanUse.ordinal());
                    if(list!=null){
                        for (int i=0;i<list.size();i++){
                            SShopItemData cc = sShopItemDataService.selectByPrimaryKey(list.get(i).getSpsdUkId());
                            list.get(i).getConfig().setpShopItemConfig(cc.getpShopItemConfig());
                        }
                    }
                    Map<String,Object> data = new HashMap<String, Object>();
                    data.put("list",list);
                    result.SetResult(ResultCodeKeyEnum.ResultCodeKey.Success,data);
                }
            }
        }

        return result;
    }
}
