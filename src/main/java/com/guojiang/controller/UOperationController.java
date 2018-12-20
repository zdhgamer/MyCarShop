package com.guojiang.controller;

import com.guojiang.bean.SShopInfo;
import com.guojiang.bean.UOderSystem;
import com.guojiang.bean.UOperation;
import com.guojiang.service.SShopInfoService;
import com.guojiang.service.UOderSystemService;
import com.guojiang.service.UOperationService;
import com.guojiang.util.*;
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

@Transactional(rollbackFor = Throwable.class)
@Controller
public class UOperationController {

    public static final Logger logger = LogManager.getLogger(UOperationController.class);

    @Autowired
    private UOperationService uOperationService;

    @Autowired
    private UOderSystemService uOderSystemService;

    @Autowired
    private SShopInfoService sShopInfoService;


    /**
     * 获取订单下面的所有状态
     *
     * @param session
     * @param oderId
     * @return
     */
    @ResponseBody
    @RequestMapping("/api/GetAllOperationOderByOderId")
    public ResultMsg GetAllOperationOderByOderId(HttpSession session,
                                                 @RequestParam("oderId") Integer oderId) {
        logger.info("-->GetAllOperationOderByOderId->" + "获取订单下面的所有状态" + oderId);
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
                List<UOperation> operations = uOperationService.selectByOderId(oderId);
                data.put("operations", operations);
                result.SetResult(ResultCodeKeyEnum.ResultCodeKey.Success, data);
            }
        }
        return result;
    }


    /**
     * 接受订单
     *
     * @param session
     * @param oderId
     * @return
     */
    @ResponseBody
    @RequestMapping("/api/AcceptOderByOderId")
    public ResultMsg AcceptOderByOderId(HttpSession session,
                                        @RequestParam("oderId") Integer oderId) {
        logger.info("-->AcceptOderByOderId->" + "接受订单" + oderId);
        ResultMsg result = new ResultMsg();
        Map<String, Object> data = new HashMap<String, Object>();
        SShopInfo shopInfo = (SShopInfo) session.getAttribute("curShopInfo");
        if (shopInfo == null) {
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoLogin, null);
        } else if (oderId <= 0) {
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoOder, null);
        } else {
            shopInfo = sShopInfoService.selectByPrimaryKey(shopInfo.getSpId());
            UOderSystem oder = uOderSystemService.selectByPrimaryKey(oderId);
            if (oder == null) {
                result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoOder, null);
            } else if (shopInfo == null || (shopInfo.getSpShopoperationstate() != null && shopInfo.getSpShopoperationstate() == EnumCode.ShopOperationState.NotRun.ordinal()) ||
                    (shopInfo.getSpShopstate() == EnumCode.RunState.NotRun.ordinal())) {
                result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoCantOperation, null);
            } else {
                Long time = new Date().getTime();
                Integer state = oder.getoState();
                if (state == EnumCode.OderState.Request.ordinal() && oder.getoPayway() !=EnumCode.OderPayWay.OrderNoPayMoney.ordinal()) {
                    oder.setoState(EnumCode.OderState.ShopAgree.ordinal());
                    UOperation operation = new UOperation();
                    operation.setUooSubmittime(time);
                    operation.setUooDes("商家已接单");
                    operation.setUooState(EnumCode.OderState.ShopAgree.ordinal());
                    operation.setUooOId(oderId);
                    operation.setUooGetCarId(0);
                    operation.setUooBackCarId(0);
                    uOperationService.insert(operation);

                    uOderSystemService.updateByPrimaryKey(oder);

                    data.put("oder", oder);
                    List<UOperation> operations = uOperationService.selectByOderId(oderId);
                    data.put("operations", operations);
                    result.SetResult(ResultCodeKeyEnum.ResultCodeKey.Success, data);

                    result.SetResult(ResultCodeKeyEnum.ResultCodeKey.Success, data);
                } else {
                    result.SetResult(ResultCodeKeyEnum.ResultCodeKey.ErrorState, null);
                }
            }
        }
        return result;
    }

    /**
     * 拒绝订单
     * @param session
     * @param oderId
     * @return
     */
    @ResponseBody
    @RequestMapping("/api/RefuseOderByOderId")
    public ResultMsg RefuseOderByOderId(HttpSession session,
                                        @RequestParam("oderId") Integer oderId) {
        logger.info("-->RefuseOderByOderId->" + "拒绝订单" + oderId);
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
                Long time = new Date().getTime();
                Integer state = oder.getoState();
                if (state == EnumCode.OderState.Request.ordinal() && oder.getoPayway() !=EnumCode.OderPayWay.OrderNoPayMoney.ordinal()) {
                    oder.setoState(EnumCode.OderState.Cancel.ordinal());
                    UOperation operation = new UOperation();
                    operation.setUooSubmittime(time);
                    operation.setUooDes("商家已拒绝接单");
                    operation.setUooState(EnumCode.OderState.Cancel.ordinal());
                    operation.setUooOId(oderId);
                    operation.setUooGetCarId(0);
                    operation.setUooBackCarId(0);
                    uOperationService.insert(operation);

                    uOderSystemService.updateByPrimaryKey(oder);

                    data.put("oder", oder);
                    List<UOperation> operations = uOperationService.selectByOderId(oderId);
                    data.put("operations", operations);
                    result.SetResult(ResultCodeKeyEnum.ResultCodeKey.Success, data);

                    result.SetResult(ResultCodeKeyEnum.ResultCodeKey.Success, data);
                } else {
                    result.SetResult(ResultCodeKeyEnum.ResultCodeKey.ErrorState, null);
                }
            }
        }
        return result;
    }


    /**
     * 已取车
     *
     * @param session
     * @param oderId
     * @return
     */
    @ResponseBody
    @RequestMapping("/api/GetedCarByOderId")
    public ResultMsg GetedCarByOderId(HttpSession session,
                                      @RequestParam("oderId") Integer oderId) {
        logger.info("-->GetedCarByOderId->" + "已取车" + oderId + " ");
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
                Long time = new Date().getTime();
                Integer state = oder.getoState();
                //需要取车并且指派了取车人
                if (state == EnumCode.OderState.SendGetCarAssist.ordinal() && oder.getoIsgetcaruser() == EnumCode.NeedGetCarUser.Need.ordinal()) {

                    List<UOperation> operations = uOperationService.selectByOderId(oderId);
                    if (operations != null && operations.size() > 0) {
                        UOperation last = operations.get(operations.size() - 1);
                        String back = MyHttp.sendGet(CommonConst.PlathUrl + CommonConst.getCarOperationMethod, "oderId=" + oderId + "&" + "getCarUserId=" + last.getUooGetCarId());
                        if (back.contains("success")) {
                            oder.setoState(EnumCode.OderState.AssistGetCar.ordinal());
                            UOperation operation = new UOperation();
                            operation.setUooSubmittime(time);
                            operation.setUooDes("已取车");
                            operation.setUooState(EnumCode.OderState.AssistGetCar.ordinal());
                            operation.setUooOId(oderId);
                            operation.setUooGetCarId(0);
                            operation.setUooBackCarId(0);
                            uOperationService.insert(operation);

                            uOderSystemService.updateByPrimaryKey(oder);

                            data.put("oder", oder);
                            operations = uOperationService.selectByOderId(oderId);
                            data.put("operations", operations);
                            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.Success, data);

                            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.Success, data);
                        } else {
                            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.Fail, null);
                            return result;
                        }
                    } else {
                        result.SetResult(ResultCodeKeyEnum.ResultCodeKey.Fail, null);
                        return result;
                    }
                } else {
                    result.SetResult(ResultCodeKeyEnum.ResultCodeKey.ErrorState, null);
                }
            }
        }
        return result;
    }


    /**
     * 开始保养
     *
     * @param session
     * @param oderId
     * @return
     */
    @ResponseBody
    @RequestMapping("/api/StartServiceByOderId")
    public ResultMsg StartServiceByOderId(HttpSession session,
                                          @RequestParam("oderId") Integer oderId) {
        logger.info("-->StartServiceByOderId->" + "开始保养" + oderId + " ");
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
                Long time = new Date().getTime();
                Integer state = oder.getoState();

                if ((oder.getoIsgetcaruser() == EnumCode.NeedGetCarUser.Need.ordinal() && state == EnumCode.OderState.AssistGetCar.ordinal())
                        || (oder.getoIsgetcaruser() == EnumCode.NeedGetCarUser.NotNeed.ordinal() && state == EnumCode.OderState.ShopAgree.ordinal())) {
                    oder.setoState(EnumCode.OderState.StartService.ordinal());
                    UOperation operation = new UOperation();
                    operation.setUooSubmittime(time);
                    operation.setUooDes("开始服务");
                    operation.setUooState(EnumCode.OderState.StartService.ordinal());
                    operation.setUooOId(oderId);
                    operation.setUooGetCarId(0);
                    operation.setUooBackCarId(0);
                    uOperationService.insert(operation);

                    uOderSystemService.updateByPrimaryKey(oder);

                    data.put("oder", oder);
                    List<UOperation> operations = uOperationService.selectByOderId(oderId);
                    data.put("operations", operations);
                    result.SetResult(ResultCodeKeyEnum.ResultCodeKey.Success, data);

                    result.SetResult(ResultCodeKeyEnum.ResultCodeKey.Success, data);
                } else {
                    result.SetResult(ResultCodeKeyEnum.ResultCodeKey.ErrorState, null);
                }
            }
        }
        return result;
    }


    /**
     * 保养结束
     *
     * @param session
     * @param oderId
     * @return
     */
    @ResponseBody
    @RequestMapping("/api/EndServiceByOderId")
    public ResultMsg EndServiceByOderId(HttpSession session,
                                        @RequestParam("oderId") Integer oderId) {
        logger.info("-->EndServiceByOderId->" + "保养结束" + oderId + " ");
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
                Long time = new Date().getTime();
                Integer state = oder.getoState();
                if (state == EnumCode.OderState.StartService.ordinal()) {
                    oder.setoState(EnumCode.OderState.EndService.ordinal());
                    UOperation operation = new UOperation();
                    operation.setUooSubmittime(time);
                    operation.setUooDes("服务结束");
                    operation.setUooState(EnumCode.OderState.EndService.ordinal());
                    operation.setUooOId(oderId);
                    operation.setUooGetCarId(0);
                    operation.setUooBackCarId(0);
                    uOperationService.insert(operation);

                    uOderSystemService.updateByPrimaryKey(oder);

                    data.put("oder", oder);
                    List<UOperation> operations = uOperationService.selectByOderId(oderId);
                    data.put("operations", operations);
                    result.SetResult(ResultCodeKeyEnum.ResultCodeKey.Success, data);

                    result.SetResult(ResultCodeKeyEnum.ResultCodeKey.Success, data);
                } else {
                    result.SetResult(ResultCodeKeyEnum.ResultCodeKey.ErrorState, null);
                }
            }
        }
        return result;
    }

    /**
     * 结束订单
     *
     * @param session
     * @param oderId
     * @return
     */
    @ResponseBody
    @RequestMapping("/api/FinishServiceByOderId")
    public ResultMsg FinishServiceByOderId(HttpSession session,
                                           @RequestParam("oderId") Integer oderId) {
        logger.info("-->FinishServiceByOderId->" + "结束订单" + oderId + " ");
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
                shopInfo = sShopInfoService.selectByPrimaryKey(shopInfo.getSpId());
                Long time = new Date().getTime();
                Integer state = oder.getoState();
                if ((oder.getoIsgetcaruser() == EnumCode.NeedGetCarUser.Need.ordinal() && state == EnumCode.OderState.SendBackCarAssist.ordinal())) {

                    List<UOperation> operations = uOperationService.selectByOderId(oderId);

                    if (operations != null && operations.size() > 0) {
                        UOperation last = operations.get(operations.size() - 1);
                        String back = MyHttp.sendGet(CommonConst.PlathUrl + CommonConst.getCarOperationMethod, "oderId=" + oderId + "&" + "getCarUserId=" + last.getUooBackCarId());
                        if (back.contains("success")) {
                            oder.setoState(EnumCode.OderState.AssistBackCar.ordinal());
                            UOperation operation = new UOperation();
                            operation.setUooSubmittime(time);
                            operation.setUooDes("已还车");
                            operation.setUooState(EnumCode.OderState.AssistBackCar.ordinal());
                            operation.setUooOId(oderId);
                            operation.setUooGetCarId(0);
                            operation.setUooBackCarId(0);
                            uOperationService.insert(operation);

                            uOderSystemService.updateByPrimaryKey(oder);

                            int old = 0;
                            if (shopInfo.getSpTotalsale() == null) {
                                old = 0;
                            } else {
                                old = shopInfo.getSpTotalsale();
                            }
                            shopInfo.setSpTotalsale(old + 1);

                            sShopInfoService.updateByPrimaryKey(shopInfo);

                            data.put("oder", oder);
                            operations = uOperationService.selectByOderId(oderId);
                            data.put("operations", operations);
                            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.Success, data);

                            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.Success, data);
                        } else {
                            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.Fail, null);
                        }
                    } else {
                        result.SetResult(ResultCodeKeyEnum.ResultCodeKey.Fail, null);
                    }
                } else if ((oder.getoIsgetcaruser() == EnumCode.NeedGetCarUser.NotNeed.ordinal() && state == EnumCode.OderState.EndService.ordinal())) {
                    oder.setoState(EnumCode.OderState.AssistBackCar.ordinal());
                    UOperation operation = new UOperation();
                    operation.setUooSubmittime(time);
                    operation.setUooDes("已还车");
                    operation.setUooState(EnumCode.OderState.AssistBackCar.ordinal());
                    operation.setUooOId(oderId);
                    operation.setUooGetCarId(0);
                    operation.setUooBackCarId(0);
                    uOperationService.insert(operation);

                    uOderSystemService.updateByPrimaryKey(oder);

                    int old = 0;
                    if (shopInfo.getSpTotalsale() == null) {
                        old = 0;
                    } else {
                        old = shopInfo.getSpTotalsale();
                    }
                    shopInfo.setSpTotalsale(old + 1);

                    sShopInfoService.updateByPrimaryKey(shopInfo);

                    data.put("oder", oder);
                    List<UOperation> operations = uOperationService.selectByOderId(oderId);
                    data.put("operations", operations);
                    result.SetResult(ResultCodeKeyEnum.ResultCodeKey.Success, data);

                    result.SetResult(ResultCodeKeyEnum.ResultCodeKey.Success, data);
                } else {
                    result.SetResult(ResultCodeKeyEnum.ResultCodeKey.ErrorState, null);
                }
            }
        }
        return result;
    }


}
