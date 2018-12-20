package com.guojiang.sms;

import java.util.TimerTask;

/**
 * 任务，发送商家登录时的验证码
 */
public class ShopLoginSendSMSTask extends TimerTask {

    private String phoneNumber;

    private  String code;

    public ShopLoginSendSMSTask(String phoneNumber,String code){
        this.phoneNumber = phoneNumber;
        this.code = code;
    }

    @Override
    public void run() {
        SMSSend.SendShopLoginCheckCode(phoneNumber);
    }

}
