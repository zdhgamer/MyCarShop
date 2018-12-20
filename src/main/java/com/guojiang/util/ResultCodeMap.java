package com.guojiang.util;

import java.util.HashMap;
import java.util.Map;

public class ResultCodeMap {

    private static ResultCodeMap instance;

    private static Map<ResultCodeKeyEnum.ResultCodeKey,String> codeMap;

    public static ResultCodeMap getInstance(){
        if(instance == null){
            instance = new ResultCodeMap();
        }
        createCodeMap();
        return instance;
    }

    /**
     * 生成code和描述信息
     */
    private static void createCodeMap(){
        if(codeMap == null){
            codeMap = new HashMap<ResultCodeKeyEnum.ResultCodeKey, String>();
        }
    }

    /**
     * 获取描述信息
     * @param code
     * @return
     */
    public static String getCodeDes(ResultCodeKeyEnum.ResultCodeKey code){
        if(codeMap == null){
            createCodeMap();
            addCodeMap();
        }
        return codeMap.get(code);
    }


    /**
     * 添加code和描述信息
     */
    private static void addCodeMap(){
        codeMap.put(ResultCodeKeyEnum.ResultCodeKey.Fail,"失败");
        codeMap.put(ResultCodeKeyEnum.ResultCodeKey.Success,"成功");
        codeMap.put(ResultCodeKeyEnum.ResultCodeKey.NoPhoneNumber,"手机号码为空");
        codeMap.put(ResultCodeKeyEnum.ResultCodeKey.ErrorFPhoneNumber,"手机号码格式不正确");
        codeMap.put(ResultCodeKeyEnum.ResultCodeKey.NoShopInfo,"没有该商家");
        codeMap.put(ResultCodeKeyEnum.ResultCodeKey.NoCheckCode,"没有验证码");
        codeMap.put(ResultCodeKeyEnum.ResultCodeKey.ErrorCheckCode,"验证码错误");
        codeMap.put(ResultCodeKeyEnum.ResultCodeKey.ErrorFCheckCode,"验证码格式错误");
        codeMap.put(ResultCodeKeyEnum.ResultCodeKey.NoLogin,"未登录操作");
        codeMap.put(ResultCodeKeyEnum.ResultCodeKey.NoRealName,"真实姓名不能为空");
        codeMap.put(ResultCodeKeyEnum.ResultCodeKey.ErrorFRealName,"真实姓名格式错误");
        codeMap.put(ResultCodeKeyEnum.ResultCodeKey.NoIdCardNumber,"身份证号码为空");
        codeMap.put(ResultCodeKeyEnum.ResultCodeKey.ErrorFIdCardNumber,"身份证号码格式错误");
        codeMap.put(ResultCodeKeyEnum.ResultCodeKey.NoImage,"图片为空");
        codeMap.put(ResultCodeKeyEnum.ResultCodeKey.ErrorFImage,"图片格式不正确");
        codeMap.put(ResultCodeKeyEnum.ResultCodeKey.GreaterFileSize,"超过文件限制大小");
        codeMap.put(ResultCodeKeyEnum.ResultCodeKey.ExistThisGetCarUser,"已有该取车人");
        codeMap.put(ResultCodeKeyEnum.ResultCodeKey.FileSaveFail,"文件存贮失败");
        codeMap.put(ResultCodeKeyEnum.ResultCodeKey.NoGetCarUser,"没有该取车人");
        codeMap.put(ResultCodeKeyEnum.ResultCodeKey.NoShopItemType,"没有商品类别");
        codeMap.put(ResultCodeKeyEnum.ResultCodeKey.ExistThisShopItemType,"已存在该商品类别");
        codeMap.put(ResultCodeKeyEnum.ResultCodeKey.NoShopItemDes,"没有商品描述信息");
        codeMap.put(ResultCodeKeyEnum.ResultCodeKey.NoPrice,"没有价格");
        codeMap.put(ResultCodeKeyEnum.ResultCodeKey.NoCarType,"没有选择车型");
        codeMap.put(ResultCodeKeyEnum.ResultCodeKey.LetPositiveNumber,"请填写正数");
        codeMap.put(ResultCodeKeyEnum.ResultCodeKey.NoThisCarType,"没有该类型的车");
        codeMap.put(ResultCodeKeyEnum.ResultCodeKey.NoThisShopItem,"该商品不存在");
        codeMap.put(ResultCodeKeyEnum.ResultCodeKey.NoServiceType,"没有该类服务");
        codeMap.put(ResultCodeKeyEnum.ResultCodeKey.ExistThisServiceType,"存在该类服务");
        codeMap.put(ResultCodeKeyEnum.ResultCodeKey.NoCarSize,"没有该车型");
        codeMap.put(ResultCodeKeyEnum.ResultCodeKey.Checking,"正在审核中");
        codeMap.put(ResultCodeKeyEnum.ResultCodeKey.NoDes,"没有描述信息");
        codeMap.put(ResultCodeKeyEnum.ResultCodeKey.ExistSubChecking,"有子项在审核中");
        codeMap.put(ResultCodeKeyEnum.ResultCodeKey.NoDefaultShopItem,"没有默认商品");
        codeMap.put(ResultCodeKeyEnum.ResultCodeKey.LetPositiveTime,"请填写正的时间");
        codeMap.put(ResultCodeKeyEnum.ResultCodeKey.NoOder,"订单不存在");
        codeMap.put(ResultCodeKeyEnum.ResultCodeKey.NoPage,"分页不存在");
        codeMap.put(ResultCodeKeyEnum.ResultCodeKey.ErrorState,"状态错误");
        codeMap.put(ResultCodeKeyEnum.ResultCodeKey.LetTotalCarPosMoreServing,"总车位必须大于服务中的车位数");
        codeMap.put(ResultCodeKeyEnum.ResultCodeKey.LetPositiveStar,"选择整数的星星");
        codeMap.put(ResultCodeKeyEnum.ResultCodeKey.ExistEvaluation,"已存在评价");
        codeMap.put(ResultCodeKeyEnum.ResultCodeKey.OderNotFinish,"订单未完成");
        codeMap.put(ResultCodeKeyEnum.ResultCodeKey.LastMoneyNotFull,"余额不足");
        codeMap.put(ResultCodeKeyEnum.ResultCodeKey.LessMinDrawMoney,"小于最小提现限制");
        codeMap.put(ResultCodeKeyEnum.ResultCodeKey.NoServiceName,"没有服务名字");
        codeMap.put(ResultCodeKeyEnum.ResultCodeKey.NoCantOperation,"不允许操作，请联系平台管理员");
        codeMap.put(ResultCodeKeyEnum.ResultCodeKey.NoAnyService,"不允许操作，没有任何服务提供");
    }
}
