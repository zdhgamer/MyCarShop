package com.guojiang.sms;

import com.guojiang.util.CommonUtil;

import java.util.Timer;

/**
 * 发送短信
 */
public class SMSSend {

    /**
     * 定时器
     */
    private static Timer timer = new Timer();

    /**
     * 发送商家登录验证码
     * @param phoneNumber
     * @return
     */
    public static boolean SendShopLoginCheckCode(String phoneNumber){
        String checkCodeString = CommonUtil.getRandomNumCode(6);
        //todo 发送验证码
        if(true){
            SMSPhoneCodeMap.RemoveShopLoginPhoneCodeMap(phoneNumber);
            SMSPhoneCodeMap.AddShopLoginPhoneCodeMap(phoneNumber,checkCodeString);
            ShopLoginSendSMSTimerTask task = new ShopLoginSendSMSTimerTask(phoneNumber);
            timer.schedule(task,10*60*1000);//10分钟过期
        }
        return true;
    }

}
