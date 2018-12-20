package com.guojiang.controller;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.guojiang.bean.*;
import com.guojiang.netJson.ShopItemByDetailIdsJson;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional(rollbackFor=Throwable.class)
@Controller
public class SShopUpkeepDetailsItemController {

    public static final Logger logger = LogManager.getLogger(SShopUpkeepDetailsItemController.class);

    @Autowired
    private SShopUpkeepDetailsItemService sShopUpkeepDetailsItemService;

    @Autowired
    private PShopItemConfigService pShopItemConfigService;

    @Autowired
    private SShopUpkeepSubServiceService sShopUpkeepSubServiceService;

    @Autowired
    private SShopUpkeepServiceDetailsService sShopUpkeepServiceDetailsService;

    @Autowired
    private SShopItemDataService sShopItemDataService;

    @Autowired
    private SShopItemDetailsService sShopItemDetailsService;


    /**
     * 查找所有的保养详细类商品详情
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping("/api/FindAllShopItemByDetailId")
    public ResultMsg FindAllShopItemByDetailId(HttpSession session,
                                                @RequestParam("detailId") Integer detailId) {
        logger.info("-->FindAllShopItemByDetailId->" + "查找所有的保养详细类商品详情"+" "+detailId);
        ResultMsg result = new ResultMsg();
        SShopInfo shopInfo = (SShopInfo) session.getAttribute("curShopInfo");
        if (shopInfo == null) {
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoLogin, null);
        }
        else if(detailId<=0){
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoServiceType,null);
        }
        else{
            SShopUpkeepServiceDetails details = sShopUpkeepServiceDetailsService.selectByPrimaryKey(detailId);
            if(details == null){
                result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoServiceType,null);
            }
            else {
                List<SShopItemDetails> list = sShopItemDetailsService.selectAllByTypeId(shopInfo.getSpId(), details.getSpsdUkId(), EnumCode.CanUseCode.CanUse.ordinal());
                List<SShopUpkeepDetailsItem> listTemp = sShopUpkeepDetailsItemService.selectAllByDetailId(shopInfo.getSpId(),detailId);
                if(list!=null && list.size()>0 && listTemp!=null && listTemp.size()>0){
                    for (int i=0;i<list.size();i++){
                        for (int j=0;j<listTemp.size();j++){
                            if(list.get(i).getSshdId() == listTemp.get(j).getSuksdiSshdId()){
                                list.get(i).setToggled(true);
                            }
                        }
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
     * 新增详细服务商品
     * @param session
     * @param idJson
     * @return
     */
    @ResponseBody
    @RequestMapping("/api/AddNewShopItemByDetailIds")
    public ResultMsg AddNewShopItemByDetailIds(HttpSession session,
                                               @RequestParam("detailId") Integer detailId,
                                               @RequestParam("idJson") String idJson){
//        {
//            "idJson":[
//            1,2,3,4
//            ]
//        }
        logger.info("-->AddNewShopItemByDetailIds->" + "查找所有的保养详细类服务"+" "+detailId+" "+idJson);
        ResultMsg result = new ResultMsg();
        SShopInfo shopInfo = (SShopInfo) session.getAttribute("curShopInfo");
        if (shopInfo == null) {
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoLogin, null);
        }
        else if(detailId<=0){
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoServiceType, null);
        }
        else if(idJson == null || idJson.trim().equals("") || idJson.trim().length()<=0){
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoShopItemType, null);
        }
        else {

            SShopUpkeepServiceDetails detail = sShopUpkeepServiceDetailsService.selectByPrimaryKey(detailId);
            if(detail!=null){
                SShopUpkeepSubService service = sShopUpkeepSubServiceService.selectByPrimaryKey(detail.getSpsdUkssId());
                if(service!=null && service.getUkssCheckstate() == EnumCode.CheckCodeState.Checking.ordinal()){
                    result.SetResult(ResultCodeKeyEnum.ResultCodeKey.Checking, null);
                    return result;
                }
                Gson gson = new Gson();
                try {
                    ShopItemByDetailIdsJson josn = gson.fromJson(idJson,ShopItemByDetailIdsJson.class);
                    if(josn!=null){
                        if(josn.idJson!=null && josn.idJson.size()>0){
                            List<SShopItemDetails> items = new ArrayList<SShopItemDetails>();
                            for (int i=0;i<josn.idJson.size();i++){
                                if(josn.idJson.get(i) == null){
                                    continue;
                                }
                                SShopItemDetails finded = sShopItemDetailsService.selectByPrimaryKey(josn.idJson.get(i));
                                if(finded != null){
                                    items.add(finded);
                                }
                                else {
                                    result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoShopItemType, null);
                                    return result;
                                }
                            }

                            if(items.size()>0){
                                for (int i=0;i<items.size();i++){
                                    SShopUpkeepDetailsItem newItem = new SShopUpkeepDetailsItem();
                                    newItem.setSuksdiShopId(shopInfo.getSpId());
                                    newItem.setSuksdiStsId(detail.getSpsdStsId());
                                    newItem.setSuksdiUkssId(detail.getSpsdUkssId());
                                    newItem.setSuksdiSpsdId(detailId);
                                    newItem.setSuksdiSshId(items.get(i).getSshdSshId());
                                    newItem.setSuksdiSshdId(items.get(i).getSshdId());
                                    sShopUpkeepDetailsItemService.insert(newItem);
                                }
                                List<SShopItemDetails> list = sShopItemDetailsService.selectAllByTypeId(shopInfo.getSpId(), detail.getSpsdUkId(), EnumCode.CanUseCode.CanUse.ordinal());
                                List<SShopUpkeepDetailsItem> listTemp = sShopUpkeepDetailsItemService.selectAllByDetailId(shopInfo.getSpId(),detailId);
                                if(list!=null && list.size()>0 && listTemp!=null && listTemp.size()>0){
                                    for (int i=0;i<list.size();i++){
                                        for (int j=0;j<listTemp.size();j++){
                                            if(list.get(i).getSshdId() == listTemp.get(j).getSuksdiSshdId()){
                                                list.get(i).setToggled(true);
                                            }
                                        }
                                    }
                                }
                                Map<String,Object> data = new HashMap<String, Object>();
                                data.put("list",list);
                                result.SetResult(ResultCodeKeyEnum.ResultCodeKey.Success,data);


                                if(service!=null){
                                    service.setUkssCheckstate(EnumCode.CheckCodeState.Nil.ordinal());
                                    sShopUpkeepSubServiceService.updateByPrimaryKey(service);
                                }

                            }
                            else {
                                result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoShopItemType, null);
                                return result;
                            }
                        }
                        else {
                            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoShopItemType, null);
                        }
                    }
                    else {
                        result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoShopItemType, null);
                    }
                }
                catch (JsonSyntaxException e){
                    logger.info("-->AddNewShopItemByDetailIds->"+ e);
                    result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoShopItemType, null);
                    throw new RuntimeException(result.toString());
                }
            }
            else {
                result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoShopItemType, null);
            }
        }
        return  result;
    }


    /**
     * 更新服务的商品详情
     * @param session
     * @param idJson
     * @return
     */
    @ResponseBody
    @RequestMapping("/api/UpdateShopItemByDetailIds")
    public ResultMsg UpdateShopItemByDetailIds(HttpSession session,
                                               @RequestParam("detailId") Integer detailId,
                                               @RequestParam("idJson") String idJson){
//        {
//            "idJson":[
//            1,2,3,4
//            ]
//        }
        logger.info("-->UpdateShopItemByDetailIds->" + "更新服务的商品详情"+" "+detailId+" "+idJson);
        ResultMsg result = new ResultMsg();
        SShopInfo shopInfo = (SShopInfo) session.getAttribute("curShopInfo");
        if (shopInfo == null) {
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoLogin, null);
        }
        else if(detailId<=0){
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoServiceType, null);
        }
        else if(idJson == null || idJson.trim().equals("") || idJson.trim().length()<=0){
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoShopItemType, null);
        }
        else {

            SShopUpkeepServiceDetails detail = sShopUpkeepServiceDetailsService.selectByPrimaryKey(detailId);
            if(detail!=null){
                SShopUpkeepSubService service = sShopUpkeepSubServiceService.selectByPrimaryKey(detail.getSpsdUkssId());
                if(service!=null && service.getUkssCheckstate() == EnumCode.CheckCodeState.Checking.ordinal()){
                    result.SetResult(ResultCodeKeyEnum.ResultCodeKey.Checking, null);
                    return result;
                }
                Gson gson = new Gson();
                try {
                    ShopItemByDetailIdsJson josn = gson.fromJson(idJson,ShopItemByDetailIdsJson.class);
                    if(josn!=null){
                        if(josn.idJson!=null && josn.idJson.size()>0){
                            List<SShopItemDetails> items = new ArrayList<SShopItemDetails>();
                            for (int i=0;i<josn.idJson.size();i++){
                                if(josn.idJson.get(i) == null){
                                    continue;
                                }
                                SShopItemDetails finded = sShopItemDetailsService.selectByPrimaryKey(josn.idJson.get(i));
                                if(finded != null){
                                    items.add(finded);
                                }
                                else {
                                    result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoShopItemType, null);
                                    return result;
                                }
                            }

                            if(items.size()>0){

                                List<SShopUpkeepDetailsItem> deleteList = sShopUpkeepDetailsItemService.selectAllByDetailId(shopInfo.getSpId(),detailId);

                                if(deleteList!=null && deleteList.size()>0){
                                    for (int i=0;i<deleteList.size();i++){
                                        sShopUpkeepDetailsItemService.deleteByPrimaryKey(deleteList.get(i).getSuksdiId());
                                    }
                                }

                                for (int i=0;i<items.size();i++){
                                    SShopUpkeepDetailsItem newItem = new SShopUpkeepDetailsItem();
                                    newItem.setSuksdiShopId(shopInfo.getSpId());
                                    newItem.setSuksdiStsId(detail.getSpsdStsId());
                                    newItem.setSuksdiUkssId(detail.getSpsdUkssId());
                                    newItem.setSuksdiSpsdId(detailId);
                                    newItem.setSuksdiSshId(items.get(i).getSshdSshId());
                                    newItem.setSuksdiSshdId(items.get(i).getSshdId());

                                    sShopUpkeepDetailsItemService.insert(newItem);
                                }
                                List<SShopItemDetails> list = sShopItemDetailsService.selectAllByTypeId(shopInfo.getSpId(), detail.getSpsdUkId(), EnumCode.CanUseCode.CanUse.ordinal());
                                List<SShopUpkeepDetailsItem> listTemp = sShopUpkeepDetailsItemService.selectAllByDetailId(shopInfo.getSpId(),detailId);
                                if(list!=null && list.size()>0 && listTemp!=null && listTemp.size()>0){
                                    for (int i=0;i<list.size();i++){
                                        for (int j=0;j<listTemp.size();j++){
                                            if(list.get(i).getSshdId() == listTemp.get(j).getSuksdiSshdId()){
                                                list.get(i).setToggled(true);
                                            }
                                        }
                                    }
                                }
                                Map<String,Object> data = new HashMap<String, Object>();
                                data.put("list",list);
                                result.SetResult(ResultCodeKeyEnum.ResultCodeKey.Success,data);

                                if(service!=null){
                                    service.setUkssCheckstate(EnumCode.CheckCodeState.Nil.ordinal());
                                    sShopUpkeepSubServiceService.updateByPrimaryKey(service);
                                }
                            }
                            else {
                                result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoShopItemType, null);
                                return result;
                            }
                        }
                        else {
                            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoShopItemType, null);
                        }
                    }
                    else {
                        result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoShopItemType, null);
                    }
                }
                catch (JsonSyntaxException e){
                    logger.info("-->AddNewShopItemByDetailIds->"+ e);
                    result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoShopItemType, null);
                    throw new RuntimeException(result.toString());
                }
            }
            else {
                result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoShopItemType, null);
            }
        }
        return  result;
    }


}
