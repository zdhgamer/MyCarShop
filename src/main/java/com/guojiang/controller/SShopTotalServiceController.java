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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional(rollbackFor=Throwable.class)
@Controller
public class SShopTotalServiceController {

    public static final Logger logger = LogManager.getLogger(SShopTotalServiceController.class);

    @Autowired
    private SShopTotalServiceService sShopTotalServiceService;

    @Autowired
    private PServiceProjectConfigService pServiceProjectConfigService;

    @Autowired
    private SShopInfoService sShopInfoService;

    @Autowired
    private SShopUpkeepSubServiceService sShopUpkeepSubServiceService;

    @Autowired
    private SShopCosmetologyServiceService sShopCosmetologyServiceService;

    @Autowired
    private SShopCosmetologyDetailService sShopCosmetologyDetailService;

    @Autowired
    private SShopMaintainServiceService sShopMaintainServiceService;


    /**
     * 超找所有的大类服务
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping("/api/FindAllTotalService")
    public ResultMsg FindAllTotalService(HttpSession session){
        logger.info("-->FindAllTotalService-->"+"超找所有的大类服务");
        ResultMsg result = new ResultMsg();
        SShopInfo shopInfo = (SShopInfo) session.getAttribute("curShopInfo");
        if(shopInfo == null){
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoLogin,null);
        }
        else {
            List<SShopTotalService> all = sShopTotalServiceService.selectByShopId(shopInfo.getSpId(), EnumCode.CanUseCode.CanUse.ordinal());
            Map<String,Object> data = new HashMap<String, Object>();
            data.put("list",all);
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.Success,data);
        }
        return result;
    }

    /**
     * 删除一个服务大类
     * @param session
     * @param deleteId
     * @return
     */
    @ResponseBody
    @RequestMapping("/api/VirtualDeleteByServiceId")
    public ResultMsg VirtualDeleteByServiceId(HttpSession session,
                                              @RequestParam("deleteId") Integer deleteId){
        logger.info("-->VirtualDeleteByServiceId-->"+"删除一个服务大类"+deleteId);
        ResultMsg result = new ResultMsg();
        SShopInfo shopInfo = (SShopInfo) session.getAttribute("curShopInfo");
        if(shopInfo == null){
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoLogin,null);
        }
        else if(deleteId<=0){
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoServiceType,null);
        }
        else {
            SShopTotalService service = sShopTotalServiceService.selectByPrimaryKey(deleteId);
            if(service == null){
                result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoServiceType,null);
            }
            else {
                service.setStsState(EnumCode.CanUseCode.CanNotUse.ordinal());
                sShopTotalServiceService.updateByPrimaryKey(service);

                List<SShopTotalService> list = sShopTotalServiceService.selectByShopId(shopInfo.getSpId(),EnumCode.CanUseCode.CanUse.ordinal());
                Map<String,Object> data = new HashMap<String, Object>();
                data.put("list",list);
                result.SetResult(ResultCodeKeyEnum.ResultCodeKey.Success,data);
            }
        }

        return result;
    }

    /**
     * 添加一个大类服务
     * @param session
     * @param typeId 配置表id
     * @return
     */
    @ResponseBody
    @RequestMapping("/api/AddTotalServiceType")
    public ResultMsg AddTotalServiceType(HttpSession session,
                                         @RequestParam("typeId") Integer typeId){
        logger.info("-->AddTotalServiceType-->"+"添加一个大类服务"+typeId);
        ResultMsg result = new ResultMsg();
        SShopInfo shopInfo = (SShopInfo) session.getAttribute("curShopInfo");
        if(shopInfo == null){
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoLogin,null);
        }
        else if(typeId<=0){
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoServiceType,null);
        }
        else {
            PServiceProjectConfig config = pServiceProjectConfigService.selectByPrimaryKey(typeId);
            if(config == null){
                result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoServiceType,null);
            }
            else {
                SShopTotalService service = sShopTotalServiceService.selectByServiceId(shopInfo.getSpId(),typeId,EnumCode.CanUseCode.CanUse.ordinal());
                if(service == null){
                    SShopTotalService sqlData = new SShopTotalService();
                    sqlData.setStsSpShopId(shopInfo.getSpId());
                    sqlData.setStsSpId(typeId);
                    sqlData.setStsState(EnumCode.CanUseCode.CanUse.ordinal());
                    sqlData.setStsPauseState(EnumCode.RunState.NotRun.ordinal());
                    sShopTotalServiceService.insert(sqlData);

                    List<SShopTotalService> list = sShopTotalServiceService.selectByShopId(shopInfo.getSpId(),EnumCode.CanUseCode.CanUse.ordinal());
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
     * 暂停一个大类服务
     *
     * @param session
     * @param totalId
     * @return
     */
    @ResponseBody
    @RequestMapping("/api/StopTotalServiceById")
    public ResultMsg StopTotalServiceById(HttpSession session,
                                          @RequestParam(value = "totalId") Integer totalId) {
        logger.info("-->StopTotalServiceById-->" + "暂停一个大类服务" +" " + totalId);
        ResultMsg result = new ResultMsg();
        SShopInfo shopInfo = (SShopInfo) session.getAttribute("curShopInfo");
        if (shopInfo == null) {
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoLogin, null);
        } else if (totalId <= 0) {
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoServiceType, null);
        } else {
            shopInfo = sShopInfoService.selectByPhoneNumber(shopInfo.getSpPhonenumber());
            SShopTotalService service = sShopTotalServiceService.selectByPrimaryKey(totalId);
            if (service == null) {
                result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoServiceType, null);
            } else {
                if (service.getStsSpId() == EnumCode.TotalServiceType.UpKeep.ordinal() ||
                        service.getStsSpId().equals(EnumCode.TotalServiceType.UpKeep.ordinal() + "")) {
                    shopInfo.setSpUpkeepstate(EnumCode.RunState.NotRun.ordinal());
                } else if (service.getStsSpId() == EnumCode.TotalServiceType.Cosmetology.ordinal() ||
                        service.getStsSpId().equals(EnumCode.TotalServiceType.Cosmetology.ordinal() + "")) {
                    shopInfo.setSpCosmetologystate(EnumCode.RunState.NotRun.ordinal());
                } else if (service.getStsSpId() == EnumCode.TotalServiceType.Repair.ordinal() ||
                        service.getStsSpId().equals(EnumCode.TotalServiceType.Repair.ordinal() + "")) {
                    shopInfo.setSpRepairstate(EnumCode.RunState.NotRun.ordinal());
                } else if(service.getStsSpId() == EnumCode.TotalServiceType.AnnualCheck.ordinal() ||
                        service.getStsSpId().equals(EnumCode.TotalServiceType.AnnualCheck.ordinal() + "")){
                    shopInfo.setSpAnnualcheckstate(EnumCode.RunState.NotRun.ordinal());
                }
                else {
                    result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoServiceType, null);
                }
                service.setStsPauseState(EnumCode.RunState.NotRun.ordinal());
                sShopInfoService.updateByPrimaryKey(shopInfo);
                sShopTotalServiceService.updateByPrimaryKey(service);
                List<SShopTotalService> all = sShopTotalServiceService.selectByShopId(shopInfo.getSpId(), EnumCode.CanUseCode.CanUse.ordinal());
                Map<String,Object> data = new HashMap<String, Object>();
                data.put("list",all);
                result.SetResult(ResultCodeKeyEnum.ResultCodeKey.Success, data);
            }

        }
        return result;
    }


    /**
     * 恢复一个大类服务
     *
     * @param session
     * @param totalId
     * @return
     */
    @ResponseBody
    @RequestMapping("/api/ResumeTotalServiceById")
    public ResultMsg ResumeTotalServiceById(HttpSession session,
                                            @RequestParam(value = "totalId") Integer totalId) {
        logger.info("-->ResumeTotalServiceById-->" + "恢复一个大类服务" + " " + totalId);
        ResultMsg result = new ResultMsg();
        SShopInfo shopInfo = (SShopInfo) session.getAttribute("curShopInfo");
        if (shopInfo == null) {
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoLogin, null);
        }else if (totalId <= 0) {
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoServiceType, null);
        } else {
            shopInfo = sShopInfoService.selectByPhoneNumber(shopInfo.getSpPhonenumber());
            SShopTotalService service = sShopTotalServiceService.selectByPrimaryKey(totalId);
            if (service == null) {
                result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoServiceType, null);
            } else {
                if (service.getStsSpId() == EnumCode.TotalServiceType.UpKeep.ordinal() ||
                        service.getStsSpId().equals(EnumCode.TotalServiceType.UpKeep.ordinal() + "")) {
                    boolean can = false;
                    List<SShopUpkeepSubService> subs = sShopUpkeepSubServiceService.selectAllByShopId(shopInfo.getSpId(),EnumCode.CanUseCode.CanUse.ordinal());
                    if(subs!=null && subs.size()>0){
                        for (int k=0;k<subs.size();k++){
                            if(subs.get(k).getUkssCheckstate() == EnumCode.CheckCodeState.Agree.ordinal()){
                                can = true;
                                break;
                            }
                        }
                    }
                    setServiceToCan(result, shopInfo, service, can);
                } else if (service.getStsSpId() == EnumCode.TotalServiceType.Cosmetology.ordinal() ||
                        service.getStsSpId().equals(EnumCode.TotalServiceType.Cosmetology.ordinal() + "")) {
                    boolean can = false;
                    List<SShopCosmetologyService> list = sShopCosmetologyServiceService.selectAllByShopId(shopInfo.getSpId(), EnumCode.CanUseCode.CanUse.ordinal());
                    if(list!=null && list.size()>0){
                        for (int k=0;k<list.size();k++){
                            List<SShopCosmetologyDetail> subList = sShopCosmetologyDetailService.selectByShopIdAndCosId(shopInfo.getSpId(),list.get(k).getScsId(), EnumCode.CanUseCode.CanUse.ordinal());
                            if(subList!=null && subList.size()>0){
                                for (int n=0;n<subList.size();n++){
                                    if(subList.get(n).getScdsCheckstate() == EnumCode.CheckCodeState.Agree.ordinal()){
                                        can = true;
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    setServiceToCan(result, shopInfo, service, can);
                } else if (service.getStsSpId() == EnumCode.TotalServiceType.Repair.ordinal() ||
                        service.getStsSpId().equals(EnumCode.TotalServiceType.Repair.ordinal() + "")) {
                    boolean can = false;
                    List<SShopMaintainService> list = sShopMaintainServiceService.selectAllByShopId(shopInfo.getSpId(), EnumCode.CanUseCode.CanUse.ordinal());
                    if(list!=null && list.size()>0){
                        for (int k=0;k<list.size();k++){
                            if(list.get(k).getSmoCheckstate() == EnumCode.CheckCodeState.Agree.ordinal()){
                                can = true;
                                break;
                            }
                        }
                    }

                    setServiceToCan(result, shopInfo, service, can);
                } else {
                    result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoServiceType, null);
                }
            }

        }
        return result;
    }

    /**
     * 是否可以更新
     * @param result
     * @param shopInfo
     * @param service
     * @param can
     */
    public void setServiceToCan(ResultMsg result, SShopInfo shopInfo, SShopTotalService service, boolean can) {
        if (can) {
            shopInfo.setSpUpkeepstate(EnumCode.RunState.Run.ordinal());
            service.setStsPauseState(EnumCode.RunState.Run.ordinal());
            sShopInfoService.updateByPrimaryKey(shopInfo);
            sShopTotalServiceService.updateByPrimaryKey(service);
            List<SShopTotalService> all = sShopTotalServiceService.selectByShopId(shopInfo.getSpId(), EnumCode.CanUseCode.CanUse.ordinal());
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("list", all);
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.Success, data);
        } else {
            shopInfo.setSpUpkeepstate(EnumCode.RunState.NotRun.ordinal());
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.ExistSubChecking, null);
        }
    }


}
