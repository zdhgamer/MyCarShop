package com.guojiang.controller;

import com.guojiang.bean.SShopEvaluation;
import com.guojiang.bean.SShopInfo;
import com.guojiang.bean.SShopItemData;
import com.guojiang.bean.UOderSystem;
import com.guojiang.service.SShopEvaluationService;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 订单完成之后，对用户进行评价
 */
@Transactional(rollbackFor=Throwable.class)
@Controller
public class SShopEvaluationController {

    public static final Logger logger = LogManager.getLogger(NetCarBrandController.class);

    @Autowired
    private SShopEvaluationService sShopEvaluationService;

    @Autowired
    private UOderSystemService uOderSystemService;


    /**
     * 通过订单id评价订单
     * @param session
     * @param oderId
     * @return
     */
    @ResponseBody
    @RequestMapping("/api/EvaluationByOderId")
    public ResultMsg EvaluationByOderId(HttpSession session,
                                        @RequestParam("oderId") Integer oderId,
                                        @RequestParam("star") Integer star,
                                        @RequestParam(value = "des",required = false) String des){
        logger.info("-->EvaluationByOderId-->"+"通过订单id评价订单"+oderId);
        ResultMsg result = new ResultMsg();
        SShopInfo shopInfo = (SShopInfo) session.getAttribute("curShopInfo");
        if(shopInfo == null){
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoLogin,null);
        }else if(oderId < 0){
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoOder,null);
        }
        else if(star<0){
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.LetPositiveStar,null);
        }
        else {
            UOderSystem oder = uOderSystemService.selectByPrimaryKey(oderId);
            SShopEvaluation sqlEvaluation = sShopEvaluationService.selectByOderId(oderId);
            if(oder == null){
                result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoOder,null);
            }
            else if(oder.getoState() != EnumCode.OderState.AssistBackCar.ordinal()){
                result.SetResult(ResultCodeKeyEnum.ResultCodeKey.OderNotFinish,null);
            }
            else if(sqlEvaluation!=null){
                result.SetResult(ResultCodeKeyEnum.ResultCodeKey.ExistEvaluation,null);
            }
            else {
                SShopEvaluation evaluation = new SShopEvaluation();
                evaluation.setSvOderId(oderId);
                evaluation.setSvUserId(oder.getoUid());
                evaluation.setSvStar(star);
                evaluation.setSvDes(des);
                sShopEvaluationService.insert(evaluation);
                result.SetResult(ResultCodeKeyEnum.ResultCodeKey.Success,null);
            }
        }
        return result;
    }

    /**
     * 通过订单id查找评价
     * @param session
     * @param oderId
     * @return
     */
    @ResponseBody
    @RequestMapping("/api/FindEvaluationByOderId")
    public ResultMsg FindEvaluationByOderId(HttpSession session,
                                        @RequestParam("oderId") Integer oderId){
        logger.info("-->FindEvaluationByOderId-->"+"通过订单id查找评价"+oderId);
        ResultMsg result = new ResultMsg();
        SShopInfo shopInfo = (SShopInfo) session.getAttribute("curShopInfo");
        if(shopInfo == null){
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoLogin,null);
        }else if(oderId < 0){
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoOder,null);
        }
        else {
            UOderSystem oder = uOderSystemService.selectByPrimaryKey(oderId);
            SShopEvaluation sqlEvaluation = sShopEvaluationService.selectByOderId(oderId);
            if(oder == null){
                result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoOder,null);
            }
            else if(oder.getoState() != EnumCode.OderState.AssistBackCar.ordinal()){
                result.SetResult(ResultCodeKeyEnum.ResultCodeKey.OderNotFinish,null);
            }
            else {
                Map<String,Object> data = new HashMap<String, Object>();
                data.put("evaluation",sqlEvaluation);
                result.SetResult(ResultCodeKeyEnum.ResultCodeKey.Success,data);
            }
        }
        return result;
    }


}
