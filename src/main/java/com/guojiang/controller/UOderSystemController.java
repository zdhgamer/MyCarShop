package com.guojiang.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guojiang.bean.*;
import com.guojiang.service.*;
import com.guojiang.util.CommonConst;
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
import java.util.*;

@Transactional(rollbackFor=Throwable.class)
@Controller
public class UOderSystemController {

    public static final Logger logger = LogManager.getLogger(UOderSystemController.class);

    @Autowired
    private UOderSystemService uOderSystemService;

    @Autowired
    private UServiceProjectService uServiceProjectService;

    @Autowired
    private USubServiceService uSubServiceService;

    @Autowired
    private SShopMaintainServiceService sShopMaintainServiceService;

    @Autowired
    private SShopCosmetologyDetailService sShopCosmetologyDetailService;

    @Autowired
    private UInvoiceTableService uInvoiceTableService;

    @Autowired
    private UOperationService uOperationService;

    @Autowired
    private SShopUpkeepSubServiceService  sShopUpkeepSubServiceService;

    @Autowired
    private SShopUpkeepDetailsItemService sShopUpkeepDetailsItemService;

    @Autowired
    private SShopUpkeepServiceDetailsService sShopUpkeepServiceDetailsService;


    /**
     * 查询所有的订单个数
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping("/api/FindAllOderCount")
    public ResultMsg FindAllOderCount(HttpSession session) {
        logger.info("-->FindAllOderCount->" + "查询所有的订单个数");
        ResultMsg result = new ResultMsg();
        SShopInfo shopInfo = (SShopInfo) session.getAttribute("curShopInfo");
        if (shopInfo == null) {
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoLogin, null);
        } else {
            long time = new Date().getTime();
            Integer newCount = uOderSystemService.selectAllByShopIdAndStateCount(shopInfo.getSpId(),EnumCode.OderState.Request.ordinal());
            Integer ingCount = uOderSystemService.selectAllByShopIdAndMinMaxStateCount(shopInfo.getSpId(), EnumCode.OderState.ShopAgree.ordinal(), EnumCode.OderState.SendBackCarAssist.ordinal());
            Integer endCount = uOderSystemService.selectAllByShopIdAndStateCount(shopInfo.getSpId(),EnumCode.OderState.AssistBackCar.ordinal());
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("newCount", newCount);
            data.put("ingCount", ingCount);
            data.put("endCount", endCount);
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.Success, data);
            session.setAttribute("lastGetNewOderTime", time);
        }
        return result;
    }

    /**
     * 查找所有新到的订单
     *
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping("/api/FindAllNewOder")
    public ResultMsg FindAllNewOder(HttpSession session) {
        logger.info("-->FindAllNewOder->" + "查找所有新到的订单");
        ResultMsg result = new ResultMsg();
        SShopInfo shopInfo = (SShopInfo) session.getAttribute("curShopInfo");
        if (shopInfo == null) {
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoLogin, null);
        } else {
            long time = new Date().getTime();
            List<UOderSystem> list = uOderSystemService.selectAllByShopIdAndState(shopInfo.getSpId(), EnumCode.OderState.Request.ordinal());
            Map<String, Object> data = new HashMap<String, Object>();
            Map<String,Object> page = new HashMap<String, Object>();
            page.put("list",list);
            data.put("page", page);
            data.put("time", time);
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.Success, data);
            session.setAttribute("lastGetNewOderTime", time);
        }
        return result;
    }

    /**
     * 查询所有的服务中的订单
     *
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping("/api/FindAllServicingOder")
    public ResultMsg FindAllServicingOder(HttpSession session) {
        logger.info("-->FindAllServicingOder->" + "查询所有的服务中的订单");
        ResultMsg result = new ResultMsg();
        SShopInfo shopInfo = (SShopInfo) session.getAttribute("curShopInfo");
        if (shopInfo == null) {
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoLogin, null);
        } else {
            long time = new Date().getTime();
            List<UOderSystem> list = uOderSystemService.selectAllByShopIdAndMinMaxState(shopInfo.getSpId(), EnumCode.OderState.ShopAgree.ordinal(), EnumCode.OderState.SendBackCarAssist.ordinal());
            Map<String, Object> data = new HashMap<String, Object>();
            Map<String,Object> page = new HashMap<String, Object>();
            page.put("list",list);
            data.put("page", page);
            data.put("time", time);
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.Success, data);
            session.setAttribute("lastGetServicingOderTime", time);
        }
        return result;
    }


    /**
     * 查找所有的已完成订单
     *
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping("/api/FindAllFinishOder")
    public ResultMsg FindAllFinishOder(HttpSession session,
                                       @RequestParam("pageIndex") Integer pageIndex) {
        logger.info("-->FindAllFinishOder->" + "查找所有的已完成订单");
        ResultMsg result = new ResultMsg();
        SShopInfo shopInfo = (SShopInfo) session.getAttribute("curShopInfo");
        if (shopInfo == null) {
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoLogin, null);
        }
        else if(pageIndex<=0){
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoPage, null);
        }
        else {
            long time = new Date().getTime();
            PageHelper.startPage(pageIndex, CommonConst.PageSize);
            //startPage后面紧跟的这个查询就是一个分页查询
            List<UOderSystem> list = uOderSystemService.selectAllByShopIdAndMinMaxState(shopInfo.getSpId(), EnumCode.OderState.AssistBackCar.ordinal(), EnumCode.OderState.OrderHaveOpration.ordinal());
            //使用PageInfo包装查询后的结果，只需要将PageInfo交给页面就行了
            //封装了详细的分页信息，包括有我们查询出来的数据，传入连续显示的页数
            PageInfo<UOderSystem> page = new PageInfo<UOderSystem>(list, CommonConst.navigatePages);

            Map<String, Object> data = new HashMap<String, Object>();
            data.put("page", page);
            data.put("time", time);
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.Success, data);
            session.setAttribute("lastGetFinishOderTime", time);
        }
        return result;
    }


    /**
     * 查找订单详细信息
     *
     * @param session
     * @param oderId
     * @return
     */
    @ResponseBody
    @RequestMapping("/api/FindOderInfoByOderId")
    public ResultMsg FindOderInfoByOderId(HttpSession session,
                                          @RequestParam("oderId") Integer oderId) {
        logger.info("-->FindOderInfoByOderId->" + "查找订单详细信息" + oderId);
        ResultMsg result = new ResultMsg();
        Map<String, Object> data = new HashMap<String, Object>();
        SShopInfo shopInfo = (SShopInfo) session.getAttribute("curShopInfo");
        if (shopInfo == null) {
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoLogin, null);
        } else if (oderId <= 0) {
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoOder, null);
        } else {
            UOderSystem oder = uOderSystemService.selectByPrimaryKey(oderId);
            if (oder == null) {
                result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoOder, null);
            } else {
                List<UServiceProject> total = uServiceProjectService.selectByOderId(oderId);

                if (total != null && total.size() > 0) {
                    for (int i = 0; i < total.size(); i++) {
                        List<USubService> subList = uSubServiceService.selectByOderIdAndTotal(oderId, total.get(i).getUspId());
                        if (total.get(i).getUspServiceConfigId() == EnumCode.TotalServiceType.UpKeep.ordinal()) {
                            if(subList!=null && subList.size()>0){
                                if(total.get(i).getUpkeepSubService() == null){
                                    total.get(i).setUpkeepSubService(new ArrayList<SShopUpkeepSubService>());
                                }
                                for (int k=0;k<subList.size();k++){
                                    //拿到套餐
                                    SShopUpkeepSubService sub = sShopUpkeepSubServiceService.selectByPrimaryKey(subList.get(k).getUssShopSubserviceId());
                                    if(sub!=null){
                                        //拿到套餐下面的商品类别
                                        List<SShopUpkeepServiceDetails> list = sShopUpkeepServiceDetailsService.selectAllBySubId(shopInfo.getSpId(),sub.getUkssId(),EnumCode.CanUseCode.CanUse.ordinal());
                                        if(list!=null && list.size()>0){
                                            if(sub.getItemDataList() == null){
                                                sub.setItemDataList(new ArrayList<SShopUpkeepDetailsItem>());
                                            }
                                           for (int q=0;q<list.size();q++){
                                               //拿到具体商品
                                               List<SShopUpkeepDetailsItem> items = sShopUpkeepDetailsItemService.selectAllByDetailId(shopInfo.getSpId(),list.get(q).getSpsdId());
                                               sub.getItemDataList().addAll(items);
                                           }
                                        }
                                    }

                                    total.get(i).getUpkeepSubService().add(sub);
                                }
                                data.put("upkeep", total.get(i));
                            }
                            else {
                                result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoServiceType, null);
                                return result;
                            }
                        } else if (total.get(i).getUspServiceConfigId() == EnumCode.TotalServiceType.Cosmetology.ordinal()) {
                            if (subList != null && subList.size() > 0) {
                                if(total.get(i).getCosmetologyDetail() == null){
                                    total.get(i).setCosmetologyDetail(new ArrayList<SShopCosmetologyDetail>());
                                }
                                for (int j=0;j<subList.size();j++){
                                    SShopCosmetologyDetail sub =  sShopCosmetologyDetailService.selectByPrimaryKey(subList.get(j).getUssShopSubserviceId());
                                    total.get(i).getCosmetologyDetail().add(sub);
                                }
                                data.put("cosmetology", total.get(i));
                            }
                            else {
                                result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoServiceType, null);
                                return result;
                            }
                        } else if (total.get(i).getUspServiceConfigId() == EnumCode.TotalServiceType.Repair.ordinal()) {
                            if (subList != null && subList.size() > 0) {
                                if (total.get(i).getMaintainService() == null) {
                                    total.get(i).setMaintainService(new ArrayList<SShopMaintainService>());
                                }
                                for (int j = 0; j < subList.size(); j++) {
                                    SShopMaintainService sub = sShopMaintainServiceService.selectByPrimaryKey(subList.get(j).getUssShopSubserviceId());
                                    total.get(i).getMaintainService().add(sub);
                                }
                                data.put("maintain", total.get(i));
                            }
                            else {
                                result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoServiceType, null);
                                return result;
                            }
                        }
                        else {
                            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoServiceType, null);
                            return result;
                        }
                    }
                    UInvoiceTable invoice = uInvoiceTableService.selectByOderId(oderId);
                    List<UOperation> operations =  uOperationService.selectByOderId(oderId);
                    data.put("operations",operations);
                    data.put("invoice",invoice);
                    data.put("oder",oder);
                    result.SetResult(ResultCodeKeyEnum.ResultCodeKey.Success, data);
                }
                else {
                    result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoOder, null);
                }
            }
        }
        return result;
    }

}
