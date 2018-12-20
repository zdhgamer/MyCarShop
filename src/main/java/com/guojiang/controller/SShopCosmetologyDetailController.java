package com.guojiang.controller;

import com.guojiang.bean.*;
import com.guojiang.service.NetCarSizeConfigService;
import com.guojiang.service.SShopCosmetologyDetailService;
import com.guojiang.service.SShopCosmetologyServiceService;
import com.guojiang.service.SShopTotalServiceService;
import com.guojiang.util.EnumCode;
import com.guojiang.util.ResultCodeKeyEnum;
import com.guojiang.util.ResultMsg;
import javafx.scene.chart.PieChart;
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
public class SShopCosmetologyDetailController {

    public static final Logger logger = LogManager.getLogger(SShopCosmetologyDetailController.class);

    @Autowired
    private SShopCosmetologyDetailService sShopCosmetologyDetailService;

    @Autowired
    private SShopCosmetologyServiceService sShopCosmetologyServiceService;

    @Autowired
    private SShopTotalServiceService sShopTotalServiceService;

    /**
     * 查询当前类别下面的所有详细美容服务
     * @param session
     * @param serviceId
     * @return
     */
    @ResponseBody
    @RequestMapping("/api/FindAllCosmetologyDetailService")
    public ResultMsg FindAllCosmetologyDetailService(HttpSession session,
                                           @RequestParam("serviceId") Integer serviceId){
        logger.info("-->FindAllCosmetologyDetailService-->"+"查询当前类别下面的所有详细美容服务"+serviceId+" ");
        ResultMsg result = new ResultMsg();
        SShopInfo shopInfo = (SShopInfo) session.getAttribute("curShopInfo");
        if (shopInfo == null){
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoLogin,null);
        }
        else if(serviceId <=0){
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoServiceType,null);
        }
        else {
            SShopCosmetologyService sqlService = sShopCosmetologyServiceService.selectByPrimaryKey(serviceId);
            if(sqlService == null){
                result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoServiceType,null);
            }
            else {
                List<SShopCosmetologyDetail> list = sShopCosmetologyDetailService.selectByShopIdAndCosId(shopInfo.getSpId(),serviceId, EnumCode.CanUseCode.CanUse.ordinal());
                Map<String,Object> data = new HashMap<String, Object>();
                data.put("list",list);
                result.SetResult(ResultCodeKeyEnum.ResultCodeKey.Success,data);
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
    @RequestMapping("/api/VirtualDeleteCosmetologyDetailById")
    public ResultMsg VirtualDeleteCosmetologyDetailById(HttpSession session,
                                                        @RequestParam("deleteId") Integer deleteId){
        logger.info("-->VirtualDeleteCosmetologyDetailById-->"+"删除一个美容服务"+deleteId+" ");
        ResultMsg result = new ResultMsg();
        SShopInfo shopInfo = (SShopInfo) session.getAttribute("curShopInfo");
        if (shopInfo == null){
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoLogin,null);
        }
        else if(deleteId<=0){
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoServiceType,null);
        }
        else {
            SShopCosmetologyDetail detail = sShopCosmetologyDetailService.selectByPrimaryKey(deleteId);
            if(detail == null){
                result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoServiceType,null);
            }
            else if(detail.getScdsCheckstate() == EnumCode.CheckCodeState.Checking.ordinal() ||
                    detail.getScdsCheckstate().equals(EnumCode.CheckCodeState.Checking.ordinal()+"")){
                result.SetResult(ResultCodeKeyEnum.ResultCodeKey.Checking,null);
            }
            else {
                detail.setScdsState(EnumCode.CanUseCode.CanNotUse.ordinal());
                sShopCosmetologyDetailService.updateByPrimaryKey(detail);
                List<SShopCosmetologyDetail> list = sShopCosmetologyDetailService.selectByShopIdAndCosId(shopInfo.getSpId(),detail.getScdsScsId(), EnumCode.CanUseCode.CanUse.ordinal());
                Map<String,Object> data = new HashMap<String, Object>();
                data.put("list",list);
                result.SetResult(ResultCodeKeyEnum.ResultCodeKey.Success,data);
            }
        }

        return result;
    }

    /**
     * 新增一个美容详细服务
     * @param session
     * @param totalServiceId
     * @param serviceId
     * @param des
     * @param price
     * @return
     */
    @ResponseBody
    @RequestMapping("/api/AddOneCosmetologyDetail")
    public ResultMsg AddOneCosmetologyDetail(HttpSession session,
                                             @RequestParam("totalServiceId") Integer totalServiceId,
                                             @RequestParam("serviceId") Integer serviceId,
                                             @RequestParam("des") String des,
                                             @RequestParam("price") String price,
                                             @RequestParam(value = "finishTime",required = false) Integer finishTime){
        logger.info("-->AddOneCosmetologyDetail-->"+"新增一个美容详细服务"+totalServiceId+" " + serviceId +" "+des +" "+ price);
        ResultMsg result = new ResultMsg();
        SShopInfo shopInfo = (SShopInfo) session.getAttribute("curShopInfo");
        if (shopInfo == null){
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoLogin,null);
        }
        else if(totalServiceId<=0){
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoServiceType,null);
        }
        else if(serviceId <=0){
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoServiceType,null);
        }
        else if(des == null || des.trim().equals("") || des.trim().length()<=0){
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoDes,null);
        }
        else if(price == null || price.trim().equals("") ||price.trim().length()<=0){
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoPrice,null);
        }
//        else if(finishTime<0){
//            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.LetPositiveTime,null);
//        }
        else {
            SShopTotalService totalService = sShopTotalServiceService.selectByPrimaryKey(totalServiceId);
            SShopCosmetologyService sqlService = sShopCosmetologyServiceService.selectByPrimaryKey(serviceId);
            if(totalService == null){
                result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoServiceType,null);
            }
            else if(sqlService == null){
                result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoServiceType,null);
            }
            else {
                Long lPrice = Long.parseLong(0+"");
                try {
                    lPrice = Long.parseLong(price);
                }
                catch (NumberFormatException e){
                    logger.info("-->AddOneCosmetologyDetail->"+e);
                    result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoPrice,null);
                    throw new RuntimeException(result.toString());
                }

                if(lPrice<=0){
                    result.SetResult(ResultCodeKeyEnum.ResultCodeKey.LetPositiveNumber,null);
                    return result;
                }
                else {
                    long time=new Date().getTime();

                    SShopCosmetologyDetail item = new SShopCosmetologyDetail();
                    item.setScdsSpShopId(shopInfo.getSpId());
                    item.setScdsSpStsId(totalServiceId);
                    item.setScdsScsId(serviceId);
                    item.setScdsCtsId(des);
                    item.setScdsServicecharge(lPrice);
                    item.setScdsState(EnumCode.CanUseCode.CanUse.ordinal());
                    item.setScdsCreatetime(time);
                    item.setScdsCheckstate(EnumCode.CheckCodeState.Checking.ordinal());
//                    item.setScdsFinishtime(finishTime);
                    sShopCosmetologyDetailService.insert(item);

                    List<SShopCosmetologyDetail> list = sShopCosmetologyDetailService.selectByShopIdAndCosId(shopInfo.getSpId(),serviceId, EnumCode.CanUseCode.CanUse.ordinal());
                    Map<String,Object> data = new HashMap<String, Object>();
                    data.put("list",list);
                    result.SetResult(ResultCodeKeyEnum.ResultCodeKey.Success,data);
                }
            }
        }

        return result;
    }

    /**
     * 更新一个美容服务
     * @param session
     * @param updateId
     * @return
     */
    @ResponseBody
    @RequestMapping("/api/UpdateCosmetologyDetailById")
    public ResultMsg UpdateCosmetologyDetailById(HttpSession session,
                                                 @RequestParam("updateId") Integer updateId,
                                                 @RequestParam("des") String des,
                                                 @RequestParam("price") String price,
                                                 @RequestParam(value = "finishTime",required = false) Integer finishTime){
        logger.info("-->UpdateCosmetologyDetailById-->"+"更新一个美容服务"+updateId+" ");
        ResultMsg result = new ResultMsg();
        SShopInfo shopInfo = (SShopInfo) session.getAttribute("curShopInfo");
        if (shopInfo == null){
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoLogin,null);
        }
        else if(updateId<=0){
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoServiceType,null);
        }
        else if(des == null || des.trim().equals("") || des.trim().length()<=0){
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoDes,null);
        }
        else if(price == null || price.trim().equals("") ||price.trim().length()<=0){
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoPrice,null);
        }
//        else if(finishTime<0){
//            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.LetPositiveTime,null);
//        }
        else {
            SShopCosmetologyDetail detail = sShopCosmetologyDetailService.selectByPrimaryKey(updateId);
            if(detail == null){
                result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoServiceType,null);
            }
            else if(detail.getScdsCheckstate() == EnumCode.CheckCodeState.Checking.ordinal() ||
                    detail.getScdsCheckstate().equals(EnumCode.CheckCodeState.Checking.ordinal()+"")){
                result.SetResult(ResultCodeKeyEnum.ResultCodeKey.Checking,null);
            }
            else {
                Long lPrice = Long.parseLong(0+"");
                try {
                    lPrice = Long.parseLong(price);
                }
                catch (NumberFormatException e){
                    logger.info("-->UpdateCosmetologyDetailById->"+e);
                    result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoPrice,null);
                    throw new RuntimeException(result.toString());
                }

                if(lPrice<=0){
                    result.SetResult(ResultCodeKeyEnum.ResultCodeKey.LetPositiveNumber,null);
                    return result;
                }
                else{
                    detail.setScdsCheckstate(EnumCode.CheckCodeState.Checking.ordinal());
                    detail.setScdsCtsId(des);
                    detail.setScdsServicecharge(lPrice);
//                    detail.setScdsFinishtime(finishTime);
                    sShopCosmetologyDetailService.updateByPrimaryKey(detail);

                    List<SShopCosmetologyDetail> list = sShopCosmetologyDetailService.selectByShopIdAndCosId(shopInfo.getSpId(),detail.getScdsScsId(), EnumCode.CanUseCode.CanUse.ordinal());
                    Map<String,Object> data = new HashMap<String, Object>();
                    data.put("list",list);
                    result.SetResult(ResultCodeKeyEnum.ResultCodeKey.Success,data);
                }
            }
        }

        return result;
    }

}
