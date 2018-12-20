package com.guojiang.controller;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.guojiang.bean.SShopInfo;
import com.guojiang.bean.SShopTotalService;
import com.guojiang.netJson.ShopInfoPicsJson;
import com.guojiang.service.SShopInfoService;
import com.guojiang.service.SShopTotalServiceService;
import com.guojiang.sms.SMSPhoneCodeMap;
import com.guojiang.sms.SMSSend;
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

/**
 * 商家信息管理
 */
@Transactional(rollbackFor=Throwable.class)
@Controller
public class SShopInfoController {

    public static final Logger logger = LogManager.getLogger(SShopInfoController.class);

    @Autowired
    private SShopInfoService sShopInfoService;

    @Autowired
    private SShopTotalServiceService sShopTotalServiceService;

    /**
     * 商家填写手机号码
     *
     * @param phoneNumber
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping("/api/ShopLoginCheckPhoneNumber")
    public ResultMsg ShopLoginCheckPhoneNumber(@RequestParam("phoneNumber") String phoneNumber,
                                               HttpSession session) {
        logger.info("-->ShopLoginCheckPhoneNumber-->" + "商家：" + phoneNumber + "开始登陆");
        ResultMsg result = new ResultMsg();
        if (phoneNumber == null || phoneNumber.trim().equals("") || phoneNumber.trim().length() <= 0) {
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoPhoneNumber, null);
        } else if (!RegexUtil.isPhone(phoneNumber.trim())) {
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.ErrorFPhoneNumber, null);
        } else {
            SShopInfo shopInfo = sShopInfoService.selectByPhoneNumber(phoneNumber.trim());
            if (shopInfo == null) {
                result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoShopInfo, null);
            } else {
                result.SetResult(ResultCodeKeyEnum.ResultCodeKey.Success, null);
                session.setAttribute("curShopInfo", phoneNumber);
            }
        }
        return result;
    }

    /**
     * 商家发送登录验证码
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping("/api/ShopLoginSendCheckCode")
    public ResultMsg ShopLoginSendCheckCode(HttpSession session) {
        String phoneNumber = (String) session.getAttribute("curShopInfo");
        ResultMsg result = new ResultMsg();
        logger.info("-->ShopLoginSendCheckCode-->" + "商家：" + phoneNumber + "发送验证码");
        if (phoneNumber == null || phoneNumber.trim().equals("") || phoneNumber.trim().length() <= 0) {
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoPhoneNumber, null);
        } else {
            boolean suce = SMSSend.SendShopLoginCheckCode(phoneNumber);
            if (suce) {
                result.SetResult(ResultCodeKeyEnum.ResultCodeKey.Success, null);
            } else {
                result.SetResult(ResultCodeKeyEnum.ResultCodeKey.Fail, null);
            }
        }
        return result;
    }

    /**
     * 商家登录
     *
     * @param checkCode
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping("/api/ShopLogin")
    public ResultMsg ShopLogin(@RequestParam("checkCode") String checkCode,
                               HttpSession session) {
        String phoneNumber = (String) session.getAttribute("curShopInfo");
        ResultMsg result = new ResultMsg();
        logger.info("-->ShopLogin-->" + "商家：" + phoneNumber + "登录");
        if (phoneNumber == null || phoneNumber.trim().equals("") || phoneNumber.trim().length() <= 0) {
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoPhoneNumber, null);
        } else if (checkCode == null || checkCode.trim().equals("") || checkCode.trim().length() < 6) {
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoCheckCode, null);
        } else if (!RegexUtil.isCheckCode(checkCode.trim())) {
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.ErrorFCheckCode, null);
        } else if (!SMSPhoneCodeMap.CheckShopLoginPhoneCodeMap(phoneNumber, checkCode.trim())) {
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.ErrorCheckCode, null);
        } else {
            SShopInfo shopInfo = sShopInfoService.selectByPhoneNumber(phoneNumber.trim());
            if (shopInfo == null) {
                result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoShopInfo, null);
            }
            else if(shopInfo.getSpShopoperationstate()!=null && shopInfo.getSpShopoperationstate() == EnumCode.ShopOperationState.NotRun.ordinal()){
                result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoCantOperation, null);
            }
            else {
                Map<String, Object> data = new HashMap<String, Object>();
                data.put("shopInfo", shopInfo);
                result.SetResult(ResultCodeKeyEnum.ResultCodeKey.Success, data);
                session.setAttribute("curShopInfo", shopInfo);
            }
        }
        return result;
    }


    /**
     * 新增banner图片
     *
     * @param session
     * @param request
     * @param pic
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/api/AddNewShopBannerPic", method = RequestMethod.POST)
    public ResultMsg AddNewShopBannerPic(HttpSession session,
                                         HttpServletRequest request,
                                         @RequestParam(value = "pic", required = false) MultipartFile pic) {
        logger.info("-->AddNewShopBannerPic-->" + "新增banner图片" + pic);
        ResultMsg result = new ResultMsg();
        SShopInfo shopInfo = (SShopInfo) session.getAttribute("curShopInfo");
        if (shopInfo == null) {
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoLogin, null);
        } else if (pic == null || pic.isEmpty()) {
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoImage, null);
        } else if (pic.getSize() > FinalData.FileSize) {
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.GreaterFileSize, null);
        } else {
            String sqlPth = SaveShopBannerPic(shopInfo, request, result, pic);
            if (!sqlPth.equals("")) {
                Gson gson = new Gson();
                shopInfo = sShopInfoService.selectByPhoneNumber(shopInfo.getSpPhonenumber());
                String sql = shopInfo.getSpShoppicone();
                ShopInfoPicsJson list = new ShopInfoPicsJson();
                list.shopPics = new ArrayList<String>();
                if (sql != null && !sql.trim().equals("")) {
                    try {
                        list = gson.fromJson(sql, ShopInfoPicsJson.class);
                    } catch (JsonSyntaxException e) {
                        logger.info("-->AddNewShopBannerPic-->" + "新增banner图片" + e);
                        throw new RuntimeException(result.toString());
                    }
                }
                if (list == null || list.shopPics == null) {
                    list = new ShopInfoPicsJson();
                    list.shopPics = new ArrayList<String>();
                }
                list.shopPics.add(sqlPth);
                String newSql = gson.toJson(list);
                shopInfo.setSpShoppicone(newSql);
                sShopInfoService.updateByPrimaryKey(shopInfo);
                Map<String, Object> data = new HashMap<String, Object>();
                data.put("shopInfo", shopInfo);
                result.SetResult(ResultCodeKeyEnum.ResultCodeKey.Success, data);
                session.setAttribute("curShopInfo", shopInfo);
            } else {
                result.SetResult(ResultCodeKeyEnum.ResultCodeKey.FileSaveFail, null);
            }
        }

        return result;
    }

    /**
     * 删除一张banner图片
     *
     * @param session
     * @param index
     * @return
     */
    @ResponseBody
    @RequestMapping("/api/DeleteShopBannerPicBuyIndex")
    public ResultMsg DeleteShopBannerPicBuyIndex(HttpSession session,
                                                 @RequestParam(value = "index") Integer index) {
        logger.info("-->DeleteShopBannerPicBuyIndex-->" + "删除一张banner图片" + index);
        ResultMsg result = new ResultMsg();
        SShopInfo shopInfo = (SShopInfo) session.getAttribute("curShopInfo");
        if (shopInfo == null) {
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoLogin, null);
        } else if (index < 0) {
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.Fail, null);
        } else {
            Gson gson = new Gson();
            shopInfo = sShopInfoService.selectByPhoneNumber(shopInfo.getSpPhonenumber());
            String sql = shopInfo.getSpShoppicone();
            ShopInfoPicsJson list = new ShopInfoPicsJson();
            list.shopPics = new ArrayList<String>();
            if (sql != null && !sql.trim().equals("")) {
                try {
                    list = gson.fromJson(sql, ShopInfoPicsJson.class);
                } catch (JsonSyntaxException e) {
                    logger.info("-->DeleteShopBannerPicBuyIndex-->" + "删除一张banner图片" + e);
                    throw new RuntimeException(result.toString());
                }
            }
            if (list != null && list.shopPics != null && list.shopPics.size() > 0) {
                if (index >= list.shopPics.size()) {
                    result.SetResult(ResultCodeKeyEnum.ResultCodeKey.Fail, null);
                } else {

                    List<String> temp = new ArrayList<String>();
                    for (int i = 0; i < list.shopPics.size(); i++) {
                        if (index == i) {
                            continue;
                        } else {
                            temp.add(list.shopPics.get(i));
                        }
                    }

                    list.shopPics = temp;
                    String newSql = gson.toJson(list);
                    shopInfo.setSpShoppicone(newSql);
                    sShopInfoService.updateByPrimaryKey(shopInfo);
                    Map<String, Object> data = new HashMap<String, Object>();
                    data.put("shopInfo", shopInfo);
                    result.SetResult(ResultCodeKeyEnum.ResultCodeKey.Success, data);
                    session.setAttribute("curShopInfo", shopInfo);
                }
            } else {
                result.SetResult(ResultCodeKeyEnum.ResultCodeKey.Fail, null);
            }
        }

        return result;
    }


    /**
     * 新增detail图片
     *
     * @param session
     * @param request
     * @param pic
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/api/AddNewShopDetailPic", method = RequestMethod.POST)
    public ResultMsg AddNewShopDetailPic(HttpSession session,
                                         HttpServletRequest request,
                                         @RequestParam(value = "pic", required = false) MultipartFile pic) {
        logger.info("-->AddNewShopDetailPic-->" + "新增detail图片" + pic);
        ResultMsg result = new ResultMsg();
        SShopInfo shopInfo = (SShopInfo) session.getAttribute("curShopInfo");
        if (shopInfo == null) {
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoLogin, null);
        } else if (pic == null || pic.isEmpty()) {
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoImage, null);
        } else if (pic.getSize() > FinalData.FileSize) {
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.GreaterFileSize, null);
        } else {
            String sqlPth = SaveShopDetailPic(shopInfo, request, result, pic);
            if (!sqlPth.equals("")) {
                Gson gson = new Gson();
                shopInfo = sShopInfoService.selectByPhoneNumber(shopInfo.getSpPhonenumber());
                String sql = shopInfo.getSpShoppictwo();
                ShopInfoPicsJson list = new ShopInfoPicsJson();
                list.shopPics = new ArrayList<String>();
                if (sql != null && !sql.trim().equals("")) {
                    try {
                        list = gson.fromJson(sql, ShopInfoPicsJson.class);
                    } catch (JsonSyntaxException e) {
                        logger.info("-->AddNewShopDetailPic-->" + "新增detail图片" + e);
                        throw new RuntimeException(result.toString());
                    }
                }
                if (list == null || list.shopPics == null) {
                    list = new ShopInfoPicsJson();
                    list.shopPics = new ArrayList<String>();
                }
                list.shopPics.add(sqlPth);
                String newSql = gson.toJson(list);
                shopInfo.setSpShoppictwo(newSql);
                sShopInfoService.updateByPrimaryKey(shopInfo);
                Map<String, Object> data = new HashMap<String, Object>();
                data.put("shopInfo", shopInfo);
                result.SetResult(ResultCodeKeyEnum.ResultCodeKey.Success, data);
                session.setAttribute("curShopInfo", shopInfo);
            } else {
                result.SetResult(ResultCodeKeyEnum.ResultCodeKey.FileSaveFail, null);
            }
        }

        return result;
    }

    /**
     * 删除一张detail图片
     *
     * @param session
     * @param index
     * @return
     */
    @ResponseBody
    @RequestMapping("/api/DeleteShopDetailPicBuyIndex")
    public ResultMsg DeleteShopDetailPicBuyIndex(HttpSession session,
                                                 @RequestParam(value = "index") Integer index) {
        logger.info("-->DeleteShopDetailPicBuyIndex-->" + "删除一张detail图片" + index);
        ResultMsg result = new ResultMsg();
        SShopInfo shopInfo = (SShopInfo) session.getAttribute("curShopInfo");
        if (shopInfo == null) {
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoLogin, null);
        } else if (index < 0) {
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.Fail, null);
        } else {
            Gson gson = new Gson();
            shopInfo = sShopInfoService.selectByPhoneNumber(shopInfo.getSpPhonenumber());
            String sql = shopInfo.getSpShoppictwo();
            ShopInfoPicsJson list = new ShopInfoPicsJson();
            list.shopPics = new ArrayList<String>();
            if (sql != null && !sql.trim().equals("")) {
                try {
                    list = gson.fromJson(sql, ShopInfoPicsJson.class);
                } catch (JsonSyntaxException e) {
                    logger.info("-->DeleteShopDetailPicBuyIndex-->" + "删除一张detail图片" + e);
                    throw new RuntimeException(result.toString());
                }
            }
            if (list != null && list.shopPics != null && list.shopPics.size() > 0) {
                if (index >= list.shopPics.size()) {
                    result.SetResult(ResultCodeKeyEnum.ResultCodeKey.Fail, null);
                } else {
                    List<String> temp = new ArrayList<String>();
                    for (int i = 0; i < list.shopPics.size(); i++) {
                        if (index == i) {
                            continue;
                        } else {
                            temp.add(list.shopPics.get(i));
                        }
                    }

                    list.shopPics = temp;
                    String newSql = gson.toJson(list);
                    shopInfo.setSpShoppictwo(newSql);
                    sShopInfoService.updateByPrimaryKey(shopInfo);
                    Map<String, Object> data = new HashMap<String, Object>();
                    data.put("shopInfo", shopInfo);
                    result.SetResult(ResultCodeKeyEnum.ResultCodeKey.Success, data);
                    session.setAttribute("curShopInfo", shopInfo);
                }
            } else {
                result.SetResult(ResultCodeKeyEnum.ResultCodeKey.Fail, null);
            }
        }

        return result;
    }

    /**
     * 存贮banner图片
     *
     * @param shopInfo
     * @param request
     * @param result
     * @param pic
     * @return
     */
    private String SaveShopBannerPic(SShopInfo shopInfo,
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


            String path = request.getSession().getServletContext().getRealPath(CommonUtil.getShopGetShopBannerImagesStorePath());
            String fileName = CommonUtil.makeFileName(shopInfo.getSpId() + "", fileAllName);
            File targetFile = new File(path, fileName);
            if (!targetFile.exists()) {
                targetFile.mkdirs();
            }
            //将上传的文件保存
            try {
                pic.transferTo(targetFile);
                sqlPath = CommonUtil.getShopGetShopBannerImagesSQLPath(fileName);
                sqlPath = sqlPath.replace('\\','/');
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


    /**
     * 存贮detail图片
     *
     * @param shopInfo
     * @param request
     * @param result
     * @param pic
     * @return
     */
    private String SaveShopDetailPic(SShopInfo shopInfo,
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


            String path = request.getSession().getServletContext().getRealPath(CommonUtil.getShopGetShopDetailImagesStorePath());
            String fileName = CommonUtil.makeFileName(shopInfo.getSpId() + "", fileAllName);
            File targetFile = new File(path, fileName);
            if (!targetFile.exists()) {
                targetFile.mkdirs();
            }
            //将上传的文件保存
            try {
                pic.transferTo(targetFile);
                sqlPath = CommonUtil.getShopGetShopDetailImagesSQLPath(fileName);
                sqlPath = sqlPath.replace('\\','/');
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

    /**
     * 查询当前商家信息
     *
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping("/api/FindSelfShopInfo")
    public ResultMsg FindSelfShopInfo(HttpSession session) {
        logger.info("-->FindSelfShopInfo-->" + "查询当前商家信息");
        ResultMsg result = new ResultMsg();
        SShopInfo shopInfo = (SShopInfo) session.getAttribute("curShopInfo");
        if (shopInfo == null) {
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoLogin, null);
        } else {
            shopInfo = sShopInfoService.selectByPrimaryKey(shopInfo.getSpId());
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("shopInfo", shopInfo);
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.Success, data);
            session.setAttribute("curShopInfo", shopInfo);
        }
        return result;
    }

    /**
     * 设置总的服务车位
     * @param session
     * @param totalCarPos
     * @return
     */
    @ResponseBody
    @RequestMapping("/api/SetTotalCarPos")
    public ResultMsg SetTotalCarPos(HttpSession session,@RequestParam(value = "totalCarPos") Integer totalCarPos){
        logger.info("-->SetTotalCarPos-->" + "设置总的服务车位"+totalCarPos);
        ResultMsg result = new ResultMsg();
        SShopInfo shopInfo = (SShopInfo) session.getAttribute("curShopInfo");
        if (shopInfo == null) {
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoLogin, null);
        }
        else if(totalCarPos<=0){
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.LetPositiveNumber, null);
        }else {
            shopInfo = sShopInfoService.selectByPrimaryKey(shopInfo.getSpId());
            if(shopInfo.getSpTotalpos() == null || shopInfo.getSpLastpos() == null){
                shopInfo.setSpTotalpos(totalCarPos);
                shopInfo.setSpLastpos(totalCarPos);
                sShopInfoService.updateByPrimaryKey(shopInfo);
                Map<String, Object> data = new HashMap<String, Object>();
                data.put("shopInfo", shopInfo);
                result.SetResult(ResultCodeKeyEnum.ResultCodeKey.Success, data);
                session.setAttribute("curShopInfo", shopInfo);
            }
            else if((shopInfo.getSpTotalpos() - shopInfo.getSpLastpos()<=totalCarPos)){
                int servicing = shopInfo.getSpTotalpos() - shopInfo.getSpLastpos();
                shopInfo.setSpTotalpos(totalCarPos);
                shopInfo.setSpLastpos(totalCarPos-servicing);
                sShopInfoService.updateByPrimaryKey(shopInfo);
                Map<String, Object> data = new HashMap<String, Object>();
                data.put("shopInfo", shopInfo);
                result.SetResult(ResultCodeKeyEnum.ResultCodeKey.Success, data);
                session.setAttribute("curShopInfo", shopInfo);
            }
            else {
                result.SetResult(ResultCodeKeyEnum.ResultCodeKey.LetTotalCarPosMoreServing, null);
            }
        }
        return result;
    }


    /**
     * 设置满减和取车费用
     * @param session
     * @param condition
     * @param subCount
     * @param count
     * @return
     */
    @ResponseBody
    @RequestMapping("/api/SetGetCarCharge")
    public ResultMsg SetGetCarCharge(HttpSession session,
                                     @RequestParam(value = "condition") Long condition,
                                     @RequestParam(value = "subCount") Long subCount,
                                     @RequestParam(value = "count") Long count){
        logger.info("-->SetGetCarCharge-->" + "设置满减和取车费用"+condition+" "+subCount+" "+count);
        ResultMsg result = new ResultMsg();
        SShopInfo shopInfo = (SShopInfo) session.getAttribute("curShopInfo");
        if (shopInfo == null) {
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoLogin, null);
        }
        else if(condition<0){
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.LetPositiveNumber, null);
        }
        else if(subCount<0){
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.LetPositiveNumber, null);
        }
        else if(count<0){
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.LetPositiveNumber, null);
        }
        else {
            shopInfo = sShopInfoService.selectByPrimaryKey(shopInfo.getSpId());
            shopInfo.setSpShopgetcarcondition(condition);
            shopInfo.setSpShopgetcarsub(subCount);
            shopInfo.setSpGetcarcharge(count);
            sShopInfoService.updateByPrimaryKey(shopInfo);
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("shopInfo", shopInfo);
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.Success, data);
            session.setAttribute("curShopInfo", shopInfo);
        }
        return result;
    }

    /**
     * 设置商家运营状态
     * @return
     */
    @ResponseBody
    @RequestMapping("/api/SetShopState")
    public ResultMsg SetShopState(HttpSession session){
        logger.info("-->SetShopState-->" + "设置商家运营状态");
        ResultMsg result = new ResultMsg();
        SShopInfo shopInfo = (SShopInfo) session.getAttribute("curShopInfo");
        if (shopInfo == null) {
            result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoLogin, null);
        }
        else {
            shopInfo = sShopInfoService.selectByPrimaryKey(shopInfo.getSpId());
            if(shopInfo.getSpShopstate() == null || shopInfo.getSpShopstate() == EnumCode.RunState.Run.ordinal()){
                shopInfo.setSpShopstate(EnumCode.RunState.NotRun.ordinal());
                sShopInfoService.updateByPrimaryKey(shopInfo);
                Map<String, Object> data = new HashMap<String, Object>();
                data.put("shopInfo", shopInfo);
                result.SetResult(ResultCodeKeyEnum.ResultCodeKey.Success, data);
                session.setAttribute("curShopInfo", shopInfo);
            }
            else {
                boolean can = false;
                List<SShopTotalService> all = sShopTotalServiceService.selectByShopId(shopInfo.getSpId(), EnumCode.CanUseCode.CanUse.ordinal());
                if(all!=null && all.size()>0){
                    for (int k=0;k<all.size();k++){
                        if(all.get(k).getStsPauseState() == EnumCode.RunState.Run.ordinal()){
                            can = true;
                            break;
                        }
                    }
                }
                if(can){
                    shopInfo.setSpShopstate(EnumCode.RunState.Run.ordinal());
                    sShopInfoService.updateByPrimaryKey(shopInfo);
                    Map<String, Object> data = new HashMap<String, Object>();
                    data.put("shopInfo", shopInfo);
                    result.SetResult(ResultCodeKeyEnum.ResultCodeKey.Success, data);
                    session.setAttribute("curShopInfo", shopInfo);
                }
                else {
                    result.SetResult(ResultCodeKeyEnum.ResultCodeKey.NoAnyService, null);
                }
            }
        }

        return result;
    }

}
