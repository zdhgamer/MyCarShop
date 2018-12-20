package com.guojiang.controller;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.guojiang.bean.*;
import com.guojiang.netJson.NetCarDetailsDataJsonData;
import com.guojiang.netJson.NetCarDetailsDataJsonDataSubJson;
import com.guojiang.netJson.NetShopItemCarListJson;
import com.guojiang.netJson.NetShopItemCarListJsonDataJson;
import com.guojiang.service.*;
import com.guojiang.util.*;
import org.apache.commons.io.FilenameUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional(rollbackFor = Throwable.class)
@Controller
public class SShopItemDetailsController {

    public static final Logger logger = LogManager.getLogger(SShopItemDetailsController.class);

    @Autowired
    private SShopItemDetailsService sShopItemDetailsService;

    @Autowired
    private SShopItemDataService sShopItemDataService;

    @Autowired
    private PShopItemConfigService pShopItemConfigService;

    @Autowired
    private NetCarBrandService netCarBrandService;

    @Autowired
    private NetCarDetailsDataService netCarDetailsDataService;


    /**
     * 通过商品id，查找当前类型下面的所有商品
     *
     * @param session
     * @param typeId
     * @return
     */
    @ResponseBody
    @RequestMapping("/api/FindAllShopItemByTypeId")
    public ResultMsg FindAllShopItemByTypeId(HttpSession session,
                                             @RequestParam("typeId") Integer typeId) {
        logger.info("-->FindAllShopItemByTypeId->" + "通过商品id，查找当前类型下面的所有商品" + typeId + "");
        ResultMsg result = new ResultMsg();
        SShopInfo shopInfo = (SShopInfo) session.getAttribute("curShopInfo");
        if (shopInfo == null) {
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoLogin, null);
        } else if (typeId <= 0) {
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoShopItemType, null);
        } else {
            SShopItemData itemType = sShopItemDataService.selectByPrimaryKey(typeId);
            if (itemType == null) {
                result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoShopItemType, null);
            } else {
                List<SShopItemDetails> list = sShopItemDetailsService.selectAllByTypeId(shopInfo.getSpId(), typeId, EnumCode.CanUseCode.CanUse.ordinal());
                Map<String, Object> data = new HashMap<String, Object>();
                data.put("list", list);
                result.SetResult(ResultCodeKeyEnum.ResultCodeKey.Success, data);
            }
        }

        return result;
    }


    /**
     * 通过商品id，查找该商品详细
     *
     * @param session
     * @param selectId
     * @return
     */
    @ResponseBody
    @RequestMapping("/api/FindShopItemDetailById")
    public ResultMsg FindShopItemDetailById(HttpSession session,
                                            @RequestParam("selectId") Integer selectId) {
        logger.info("-->FindShopItemDetailById->" + "通过商品id，查找该商品详细" + selectId + "");
        ResultMsg result = new ResultMsg();
        SShopInfo shopInfo = (SShopInfo) session.getAttribute("curShopInfo");
        if (shopInfo == null) {
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoLogin, null);
        } else if (selectId <= 0) {
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoShopItemType, null);
        } else {
            SShopItemDetails detail = sShopItemDetailsService.selectByPrimaryKey(selectId);
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("detail", detail);
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.Success, data);
        }

        return result;
    }


    /**
     * 删除一个详细的商品信息
     *
     * @param session
     * @param typeId
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/api/VirtualDeleteShopItemDetailById")
    public ResultMsg VirtualDeleteShopItemDetailById(HttpSession session,
                                                     @RequestParam("typeId") Integer typeId,
                                                     @RequestParam("id") Integer id) {
        logger.info("-->VirtualDeleteShopItemDetailByTypeId->" + "删除一个详细的商品信息" + id + "");
        ResultMsg result = new ResultMsg();
        SShopInfo shopInfo = (SShopInfo) session.getAttribute("curShopInfo");
        if (shopInfo == null) {
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoLogin, null);
        } else if (typeId <= 0) {
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoShopItemType, null);
        } else if (id <= 0) {
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoThisShopItem, null);
        } else {
            SShopItemDetails item = sShopItemDetailsService.selectByPrimaryKey(id);
            if (item == null) {
                result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoThisShopItem, null);
            } else {

                item.setSshdState(EnumCode.CanUseCode.CanNotUse.ordinal());
                sShopItemDetailsService.updateByPrimaryKey(item);

                List<SShopItemDetails> list = sShopItemDetailsService.selectAllByTypeId(shopInfo.getSpId(), typeId, EnumCode.CanUseCode.CanUse.ordinal());
                Map<String, Object> data = new HashMap<String, Object>();
                data.put("list", list);
                result.SetResult(ResultCodeKeyEnum.ResultCodeKey.Success, data);
            }
        }

        return result;
    }

    /**
     * 更新商品信息
     *
     * @param session
     * @param request
     * @param typeId
     * @param id
     * @param pic
     * @param useDes
     * @param price
     * @param carList
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/api/UpdateShopItemInfo", method = RequestMethod.POST)
    public ResultMsg UpdateShopItemInfo(HttpSession session,
                                        HttpServletRequest request,
                                        @RequestParam(value = "typeId") Integer typeId,
                                        @RequestParam(value = "id") Integer id,
                                        @RequestParam(value = "pic", required = false) MultipartFile pic,
                                        @RequestParam(value = "useDes", required = false) String useDes,
                                        @RequestParam(value = "price", required = false) String price,
                                        @RequestParam(value = "carList", required = false) String carList,
                                        @RequestParam(value = "carListText", required = false) String carListText) {
        logger.info("-->UpdateShopItemInfo->" + "更新商品信息" + typeId + pic + useDes + price + carList + " " + carListText);
        ResultMsg result = new ResultMsg();
        SShopInfo shopInfo = (SShopInfo) session.getAttribute("curShopInfo");
        if (shopInfo == null) {
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoLogin, null);
        } else if (typeId <= 0) {
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoShopItemType, null);
        } else if (id <= 0) {
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoThisShopItem, null);
        } else {
            SShopItemDetails item = sShopItemDetailsService.selectByPrimaryKey(id);
            if (item == null) {
                result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoThisShopItem, null);
            } else {
                if (useDes != null && !useDes.trim().equals("") && useDes.trim().length() > 0) {
                    item.setSshdUsedes(useDes.trim());
                }
//                if (price != null && !price.trim().equals("") && price.trim().length() > 0) {
//                    Long lPrice = Long.parseLong(0 + "");
//                    try {
//                        lPrice = Long.parseLong(price);
//                    } catch (NumberFormatException e) {
//                        logger.info("-->UpdateShopItemInfo->" + e);
//                        result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoPrice, null);
//                        throw new RuntimeException(result.toString());
//                    }
//
//                    if (lPrice > 0) {
//                        item.setSshdPrice(lPrice);
//                    } else {
//                        result.SetResult(ResultCodeKeyEnum.ResultCodeKey.LetPositiveNumber, null);
//                        return result;
//                    }
//                }
                if (carList != null && !carList.trim().equals("")) {
                    Gson gson = new Gson();
                    NetShopItemCarListJson listJosn = null;
                    try {
                        listJosn = gson.fromJson(carList, NetShopItemCarListJson.class);
                    } catch (JsonSyntaxException e) {
                        logger.info("-->AddNewShopItemInfo->" + e);
                        result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoCarType, null);
                        return result;
                    }

                    boolean can = true;
                    if (listJosn != null) {
                        for (int i = 0; i < listJosn.allCarList.size(); i++) {
                            if (listJosn.allCarList.get(i) == null) {
                                continue;
                            }
                            Integer carId = listJosn.allCarList.get(i).carId;
                            NetCarBrand car = netCarBrandService.selectByCarId(listJosn.allCarList.get(i).carId);
                            if (car == null) {
                                can = false;
                                Map<String, Object> data = new HashMap<String, Object>();
                                data.put("carType", listJosn.allCarList.get(i).carId);
                                result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoThisCarType, data);
                                return result;
                            } else {
                                if (listJosn.allCarList.get(i).subCarIdList == null) {
                                    System.out.println(listJosn.allCarList.get(i).carId);
                                    NetCarDetailsData info = netCarDetailsDataService.selectByI(carId);
                                    if(info!=null){
                                        listJosn.allCarList.get(i).subCarIdList = new ArrayList<NetShopItemCarListJsonDataJson>();
                                        NetCarDetailsDataJsonData subCar = gson.fromJson("{List:"+info.getNcdJsondata()+"}", NetCarDetailsDataJsonData.class);
                                        if (subCar.List != null) {
                                            for (int q = 0; q < subCar.List.size(); q++) {
                                                NetCarDetailsDataJsonDataSubJson dd = subCar.List.get(q);
                                                if (dd != null && dd.List.size() > 0) {
                                                    for (int p = 0; p < dd.List.size(); p++) {
                                                        NetShopItemCarListJsonDataJson a = new NetShopItemCarListJsonDataJson();
                                                        a.carSubId = dd.List.get(p).I;
                                                        listJosn.allCarList.get(i).subCarIdList.add(a);
                                                        carList = gson.toJson(listJosn);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                                if (listJosn.allCarList.get(i).subCarIdList != null) {
                                    NetCarDetailsData subCar = netCarDetailsDataService.selectByI(listJosn.allCarList.get(i).carId);
                                    for (int j = 0; j < listJosn.allCarList.get(i).subCarIdList.size(); j++) {
                                        if (listJosn.allCarList.get(i).subCarIdList.get(j) == null) {
                                            continue;
                                        }
                                        if (subCar == null) {
                                            can = false;
                                            Map<String, Object> data = new HashMap<String, Object>();
                                            data.put("carType", listJosn.allCarList.get(i).carId);
                                            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoThisCarType, data);
                                            return result;

                                        } else if (!subCar.getNcdJsondata().contains(listJosn.allCarList.get(i).subCarIdList.get(j).carSubId + "")) {
                                            can = false;
                                            Map<String, Object> data = new HashMap<String, Object>();
                                            data.put("carType", listJosn.allCarList.get(i).subCarIdList.get(j).carSubId);
                                            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoThisCarType, data);
                                            return result;
                                        }
                                    }
                                }
                            }
                        }
                        if (can) {
                            item.setSshdSsfcJson(carList);
                            item.setSshdSsfcShow(carListText);
                        }
                    }

                }

                if (pic != null) {
                    String sqlPth = SaveShopItemPic(shopInfo, request, result, pic);
                    if (!sqlPth.equals("")) {
                        item.setSshdDespicone(sqlPth);
                    }
                }

                sShopItemDetailsService.updateByPrimaryKey(item);

                List<SShopItemDetails> list = sShopItemDetailsService.selectAllByTypeId(shopInfo.getSpId(), typeId, EnumCode.CanUseCode.CanUse.ordinal());
                Map<String, Object> data = new HashMap<String, Object>();
                data.put("list", list);
                result.SetResult(ResultCodeKeyEnum.ResultCodeKey.Success, data);
            }
        }
        return result;
    }


    /**
     * 添加新的商品详情
     *
     * @param session
     * @param request
     * @param typeId
     * @param pic
     * @param useDes
     * @param price   *10000 的结果
     * @param carList
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/api/AddNewShopItemInfo", method = RequestMethod.POST)
    public ResultMsg AddNewShopItemInfo(HttpSession session,
                                        HttpServletRequest request,
                                        @RequestParam("typeId") Integer typeId,
                                        @RequestParam(value = "pic", required = false) MultipartFile pic,
                                        @RequestParam("useDes") String useDes,
                                        @RequestParam(value = "price", required = false) String price,
                                        @RequestParam("carList") String carList,
                                        @RequestParam("carListText") String carListText) {
        logger.info("-->AddNewShopItemInfo->" + "添加新的商品详情" + typeId + " " + pic + " " + useDes + " " + price + " " + carList + " " + carListText);
        ResultMsg result = new ResultMsg();
        SShopInfo shopInfo = (SShopInfo) session.getAttribute("curShopInfo");
        if (shopInfo == null) {
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoLogin, null);
        } else if (typeId <= 0) {
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoShopItemType, null);
        } else if (pic == null || pic.isEmpty()) {
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoImage, null);
        } else if (pic.getSize() > FinalData.FileSize) {
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.GreaterFileSize, null);
        } else if (useDes == null || useDes.trim().equals("") || useDes.trim().length() <= 0) {
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoShopItemDes, null);
        }
//        else if (price == null || price.trim().equals("") || price.trim().length() <= 0) {
//            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoPrice, null);
//        }
        else if (carList == null || carList.trim().equals("") || carList.trim().length() <= 0) {
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoCarType, null);
        } else if (carListText == null || carListText.length() <= 0) {
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoCarType, null);
        } else {
            SShopItemData itemType = sShopItemDataService.selectByPrimaryKey(typeId);

            Gson gson = new Gson();
//            Long lPrice = Long.parseLong(0 + "");
//            try {
//                lPrice = Long.parseLong(price);
//            } catch (NumberFormatException e) {
//                logger.info("-->AddNewShopItemInfo->" + e);
//                result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoPrice, null);
//                throw new RuntimeException(result.toString());
//            }

            NetShopItemCarListJson listJosn = null;
            try {
                listJosn = gson.fromJson(carList, NetShopItemCarListJson.class);
            } catch (JsonSyntaxException e) {
                logger.info("-->AddNewShopItemInfo->" + e);
                result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoCarType, null);
                return result;
            }

//            if (lPrice <= 0) {
//                result.SetResult(ResultCodeKeyEnum.ResultCodeKey.LetPositiveNumber, null);
//            } else
            if (itemType == null) {
                result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoShopItemType, null);
            } else if (listJosn == null || listJosn.allCarList == null || listJosn.allCarList.size() <= 0) {
                result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoCarType, null);
            } else {
                boolean can = true;
                for (int i = 0; i < listJosn.allCarList.size(); i++) {
                    if (listJosn.allCarList.get(i) == null) {
                        continue;
                    }
                    Integer carId = listJosn.allCarList.get(i).carId;
                    System.out.println(listJosn.allCarList.get(i).carId);
                    NetCarBrand car = netCarBrandService.selectByCarId(carId);
                    if (car == null) {
                        can = false;
                        Map<String, Object> data = new HashMap<String, Object>();
                        data.put("carType", listJosn.allCarList.get(i).carId);
                        result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoThisCarType, data);
                        return result;
                    } else {
                        if (listJosn.allCarList.get(i).subCarIdList == null) {
                            System.out.println(listJosn.allCarList.get(i).carId);
                            NetCarDetailsData info = netCarDetailsDataService.selectByI(carId);
                            if(info!=null){
                                listJosn.allCarList.get(i).subCarIdList = new ArrayList<NetShopItemCarListJsonDataJson>();
                                NetCarDetailsDataJsonData subCar = gson.fromJson("{List:"+info.getNcdJsondata()+"}", NetCarDetailsDataJsonData.class);
                                if (subCar.List != null) {
                                    for (int q = 0; q < subCar.List.size(); q++) {
                                        NetCarDetailsDataJsonDataSubJson dd = subCar.List.get(q);
                                        if (dd != null && dd.List.size() > 0) {
                                            for (int p = 0; p < dd.List.size(); p++) {
                                                NetShopItemCarListJsonDataJson a = new NetShopItemCarListJsonDataJson();
                                                a.carSubId = dd.List.get(p).I;
                                                listJosn.allCarList.get(i).subCarIdList.add(a);
                                                carList = gson.toJson(listJosn);
                                            }
                                        }
                                    }
                                }
                            }
                        }

                        if (listJosn.allCarList.get(i).subCarIdList != null) {
                            NetCarDetailsData subCar = netCarDetailsDataService.selectByI(listJosn.allCarList.get(i).carId);
                            for (int j = 0; j < listJosn.allCarList.get(i).subCarIdList.size(); j++) {
                                if (listJosn.allCarList.get(i).subCarIdList.get(j) == null) {
                                    continue;
                                }
                                if (subCar == null) {
                                    can = false;
                                    Map<String, Object> data = new HashMap<String, Object>();
                                    data.put("carType", listJosn.allCarList.get(i).carId);
                                    result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoThisCarType, data);
                                    return result;

                                } else if (!subCar.getNcdJsondata().contains(listJosn.allCarList.get(i).subCarIdList.get(j).carSubId + "")) {
                                    can = false;
                                    Map<String, Object> data = new HashMap<String, Object>();
                                    data.put("carType", listJosn.allCarList.get(i).subCarIdList.get(j).carSubId);
                                    result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoThisCarType, data);
                                    return result;
                                }
                            }
                        }
                    }
                }

                if (can) {
                    String sqlPth = SaveShopItemPic(shopInfo, request, result, pic);
                    if (!sqlPth.equals("")) {
                        SShopItemDetails detail = new SShopItemDetails();
                        detail.setSshdDespicone(sqlPth);
//                        detail.setSshdPrice(lPrice);
                        detail.setSshdShopId(shopInfo.getSpId());
                        detail.setSshdSsfcJson(carList);
                        detail.setSshdSshId(typeId);
                        detail.setSshdUsedes(useDes);
                        detail.setSshdState(EnumCode.CanUseCode.CanUse.ordinal());
                        detail.setSshdSsfcShow(carListText);
                        sShopItemDetailsService.insert(detail);

                        List<SShopItemDetails> list = sShopItemDetailsService.selectAllByTypeId(shopInfo.getSpId(), typeId, EnumCode.CanUseCode.CanUse.ordinal());
                        Map<String, Object> data = new HashMap<String, Object>();
                        data.put("list", list);
                        result.SetResult(ResultCodeKeyEnum.ResultCodeKey.Success, data);
                    } else {
                        result.SetResult(ResultCodeKeyEnum.ResultCodeKey.FileSaveFail, null);
                    }
                } else {
                    result.SetResult(ResultCodeKeyEnum.ResultCodeKey.Fail, null);
                }
            }
        }
        return result;
    }


    /**
     * 存贮商品图片
     *
     * @param shopInfo
     * @param request
     * @param result
     * @param pic
     * @return
     */
    private String SaveShopItemPic(SShopInfo shopInfo,
                                   HttpServletRequest request,
                                   ResultMsg result,
                                   MultipartFile pic) {
        String fileAllName = pic.getOriginalFilename();
        //获取源文件名后缀
        String prefixName = FilenameUtils.getExtension(fileAllName);
        String sqlPath = "";
        if (prefixName.equalsIgnoreCase("jpg")
                || prefixName.equalsIgnoreCase("png")
                || prefixName.equalsIgnoreCase("jpeg")
                || prefixName.equalsIgnoreCase("pneg")) {


            String path = request.getSession().getServletContext().getRealPath(CommonUtil.getShopGetShopItemImagesStorePath());
            String fileName = CommonUtil.makeFileName(shopInfo.getSpId() + "", fileAllName);
            File targetFile = new File(path, fileName);
            if (!targetFile.exists()) {
                targetFile.mkdirs();
            }
            //将上传的文件保存
            try {
                pic.transferTo(targetFile);
                sqlPath = CommonUtil.getShopGetShopItemImagesSQLPath(fileName);
                sqlPath = sqlPath.replace('\\', '/');
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("存贮图片失败：" + e);
                sqlPath = "";
                result.SetResult(ResultCodeKeyEnum.ResultCodeKey.FileSaveFail, null);
                throw new RuntimeException(result.toString());
            }
            if (!sqlPath.equals("")) {
                System.out.println("图片存贮地址为：" + sqlPath);
            } else {
                result.SetResult(ResultCodeKeyEnum.ResultCodeKey.FileSaveFail, null);
            }
        } else {
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.ErrorFImage, null);
        }
        return sqlPath;
    }
}
