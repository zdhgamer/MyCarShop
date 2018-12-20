package com.guojiang.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guojiang.bean.SShopDrawMoney;
import com.guojiang.bean.SShopInfo;
import com.guojiang.bean.UInvoiceTable;
import com.guojiang.bean.UOderSystem;
import com.guojiang.service.SShopDrawMoneyService;
import com.guojiang.service.SShopInfoService;
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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional(rollbackFor=Throwable.class)
@Controller
public class SShopDrawMoneyController {

    public static final Logger logger = LogManager.getLogger(SShopDrawMoneyController.class);

    @Autowired
    private SShopDrawMoneyService sShopDrawMoneyService;

    @Autowired
    private SShopInfoService sShopInfoService;


    /**
     * 商家发起提现
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping("/api/ShopDrawMoney")
    public ResultMsg ShopDrawMoney(HttpSession session,
                                         @RequestParam("money") Long money){
        logger.info("-->ShopDrawMoney->" + "商家发起提现"+money);
        ResultMsg result = new ResultMsg();
        SShopInfo shopInfo = (SShopInfo) session.getAttribute("curShopInfo");
        if (shopInfo == null) {
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoLogin, null);
        }
        else if(money<0){
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.LetPositiveNumber, null);
        }
        else {
            shopInfo = sShopInfoService.selectByPrimaryKey(shopInfo.getSpId());
            if(money > shopInfo.getSpLastmoney()){
                result.SetResult(ResultCodeKeyEnum.ResultCodeKey.LastMoneyNotFull, null);
            }
            else if(money < CommonConst.minDrawMoeny){
                result.SetResult(ResultCodeKeyEnum.ResultCodeKey.LessMinDrawMoney, null);
            }
            else {
                Long time = new Date().getTime();
                SShopDrawMoney drawMoney = new SShopDrawMoney();
                drawMoney.setSdmDrawaccount(shopInfo.getSpWeichataccount());
                drawMoney.setSdmDrawcreatetime(time);
                drawMoney.setSdmDrawnumber(money);
                drawMoney.setSdmDrawtype(EnumCode.OderPayWay.WeChat.ordinal());
                drawMoney.setSdmShopId(shopInfo.getSpId());
                drawMoney.setSdmDrawstate(EnumCode.CheckCodeState.Checking.ordinal());
                sShopDrawMoneyService.insert(drawMoney);
                logger.info("-->ShopDrawMoney->" + "商家发起提现"+money+"提现前余额："+shopInfo.getSpLastmoney() +"提现后余额："+(shopInfo.getSpLastmoney() - money)+"");
                shopInfo.setSpLastmoney(shopInfo.getSpLastmoney() - money);
                sShopInfoService.updateByPrimaryKey(shopInfo);
                Map<String,Object> data = new HashMap<String, Object>();
                data.put("shopInfo",shopInfo);
                session.setAttribute("curShopInfo", shopInfo);
                result.SetResult(ResultCodeKeyEnum.ResultCodeKey.Success, data);
            }
        }
        return result;
    }

    /**
     * 商家提现分页
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping("/api/ShopDrawMoneyPageList")
    public ResultMsg ShopDrawMoneyPageList(HttpSession session,
                                   @RequestParam("pageIndex") Integer pageIndex){
        logger.info("-->ShopDrawMoneyPageList->" + "商家提现分页"+pageIndex);
        ResultMsg result = new ResultMsg();
        SShopInfo shopInfo = (SShopInfo) session.getAttribute("curShopInfo");
        if (shopInfo == null) {
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoLogin, null);
        }
        else if(pageIndex<0){
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoPage, null);
        }
        else {
            PageHelper.startPage(pageIndex, CommonConst.PageSize);
            //startPage后面紧跟的这个查询就是一个分页查询
            List<SShopDrawMoney> list = sShopDrawMoneyService.selectByShopId(shopInfo.getSpId());
            //使用PageInfo包装查询后的结果，只需要将PageInfo交给页面就行了
            //封装了详细的分页信息，包括有我们查询出来的数据，传入连续显示的页数
            PageInfo<SShopDrawMoney> page = new PageInfo<SShopDrawMoney>(list, CommonConst.navigatePages);

            Map<String, Object> data = new HashMap<String, Object>();
            data.put("page", page);
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.Success, data);
        }
        return result;
    }

}
