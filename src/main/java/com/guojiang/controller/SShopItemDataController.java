package com.guojiang.controller;

import com.guojiang.bean.PShopItemConfig;
import com.guojiang.bean.SShopInfo;
import com.guojiang.bean.SShopItemData;
import com.guojiang.service.PShopItemConfigService;
import com.guojiang.service.SShopItemDataService;
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
public class SShopItemDataController{

    public static final Logger logger = LogManager.getLogger(SShopItemDataController.class);

    @Autowired
    private SShopItemDataService sShopItemDataService;

    @Autowired
    private PShopItemConfigService pShopItemConfigService;


    /**
     * 删除一个商家商品类别
     * @param session
     * @param deleteId
     * @return
     */
    @ResponseBody
    @RequestMapping("/api/VirtualDeleteShopItemType")
    public ResultMsg VirtualDeleteShopItemType(HttpSession session,
                                                 @RequestParam("deleteId") Integer deleteId){
        logger.info("-->VirtualDeleteShopItemType-->"+"商家删除一个商品类别"+deleteId);
        ResultMsg result = new ResultMsg();
        SShopInfo shopInfo = (SShopInfo) session.getAttribute("curShopInfo");
        if(shopInfo == null){
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoLogin,null);
        }else if(deleteId < 0){
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoShopItemType,null);
        }
        else {
            SShopItemData item = sShopItemDataService.selectByPrimaryKey(deleteId);
            if(item == null){
                result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoShopItemType,null);
            }
            else {
                item.setSshState(EnumCode.CanUseCode.CanNotUse.ordinal());
                sShopItemDataService.updateByPrimaryKey(item);

                List<SShopItemData> list = sShopItemDataService.selectByShopId(shopInfo.getSpId(),EnumCode.CanUseCode.CanUse.ordinal());
                Map<String,Object> data = new HashMap<String, Object>();
                data.put("itemTypeList",list);
                result.SetResult(ResultCodeKeyEnum.ResultCodeKey.Success,data);
            }
        }
        return result;
    }

    /**
     * 查找所有的商品类别
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping("/api/FindAllShopItemTypes")
    public ResultMsg FindAllShopItemTypes(HttpSession session){
        logger.info("-->FindAllShopItemTypes-->"+"查找所有的商品类别");
        ResultMsg result = new ResultMsg();
        SShopInfo shopInfo = (SShopInfo) session.getAttribute("curShopInfo");
        if(shopInfo == null){
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoLogin,null);
        }
        else {
            List<SShopItemData> list = sShopItemDataService.selectByShopId(shopInfo.getSpId(),EnumCode.CanUseCode.CanUse.ordinal());
            Map<String,Object> data = new HashMap<String, Object>();
            data.put("itemTypeList",list);
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.Success,data);
        }
        return result;
    }

    /**
     * 添加一个商品类别
     * @param session
     * @param typeId
     * @return
     */
    @ResponseBody
    @RequestMapping("/api/AddOneShopItemType")
    public ResultMsg AddOneShopItemType(HttpSession session,
                                        @RequestParam("typeId") Integer typeId){
        logger.info("-->AddOneShopItemType-->"+"添加一个商品类别");
        ResultMsg result = new ResultMsg();
        SShopInfo shopInfo = (SShopInfo) session.getAttribute("curShopInfo");
        if(shopInfo == null){
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoLogin,null);
        }
        else if(typeId <0){
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoShopItemType,null);
        }
        else {
            SShopItemData findItem = sShopItemDataService.selectByShopType(shopInfo.getSpId(),typeId,EnumCode.CanUseCode.CanUse.ordinal());
            if(findItem == null){
                PShopItemConfig config = pShopItemConfigService.selectByPrimaryKey(typeId);
                if(config == null){
                    result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoShopItemType,null);
                }
                else {
                    SShopItemData item = new SShopItemData();
                    item.setSshState(EnumCode.CanUseCode.CanUse.ordinal());
                    item.setSshPshcId(typeId);
                    item.setSshShopId(shopInfo.getSpId());
                    sShopItemDataService.insert(item);
                    List<SShopItemData> list = sShopItemDataService.selectByShopId(shopInfo.getSpId(),EnumCode.CanUseCode.CanUse.ordinal());
                    Map<String,Object> data = new HashMap<String, Object>();
                    data.put("itemTypeList",list);
                    result.SetResult(ResultCodeKeyEnum.ResultCodeKey.Success,data);
                }
            }
            else {
                result.SetResult(ResultCodeKeyEnum.ResultCodeKey.ExistThisShopItemType,null);
            }
        }

        return result;
    }

}
