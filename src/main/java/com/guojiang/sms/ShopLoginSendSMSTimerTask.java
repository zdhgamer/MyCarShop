package com.guojiang.sms;

import java.util.TimerTask;

public class ShopLoginSendSMSTimerTask extends TimerTask {

    private String phoneNumber;

    public ShopLoginSendSMSTimerTask(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void run() {
        System.out.println("一个商家登录的验证码过期："+phoneNumber);
        SMSPhoneCodeMap.RemoveShopLoginPhoneCodeMap(phoneNumber);
    }
}
