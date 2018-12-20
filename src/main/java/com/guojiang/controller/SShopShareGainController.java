package com.guojiang.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guojiang.bean.SShopDrawMoney;
import com.guojiang.bean.SShopInfo;
import com.guojiang.bean.SShopShareGain;
import com.guojiang.service.SShopShareGainService;
import com.guojiang.util.CommonConst;
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
public class SShopShareGainController {

    public static final Logger logger = LogManager.getLogger(SShopShareGainController.class);

    @Autowired
    private SShopShareGainService sShopShareGainService;

    /**
     * 分享收益分页
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping("/api/ShopShareGainPageList")
    public ResultMsg ShopShareGainPageList(HttpSession session,
                                           @RequestParam("pageIndex") Integer pageIndex){
        logger.info("-->ShopShareGainPageList->" + "分享收益分页"+pageIndex);
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
            List<SShopShareGain> list = sShopShareGainService.selectByShopId(shopInfo.getSpId());
            //使用PageInfo包装查询后的结果，只需要将PageInfo交给页面就行了
            //封装了详细的分页信息，包括有我们查询出来的数据，传入连续显示的页数
            PageInfo<SShopShareGain> page = new PageInfo<SShopShareGain>(list, CommonConst.navigatePages);

            Map<String, Object> data = new HashMap<String, Object>();
            data.put("page", page);
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.Success, data);
        }
        return result;
    }

}
