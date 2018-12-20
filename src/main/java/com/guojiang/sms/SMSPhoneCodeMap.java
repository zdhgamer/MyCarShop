package com.guojiang.sms;

import java.util.HashMap;

public class SMSPhoneCodeMap {

    /**
     * 商家登录时发送的验证码
     */
    private static HashMap<String, String> shopLoginPhoneCodeMap = new HashMap<String, String>();


    /**
     * 检查是否商家登录发送过登录时验证码
     * @param phoneNumber
     * @param code
     * @return
     */
    public static boolean CheckShopLoginPhoneCodeMap(String phoneNumber,String code){
        if (shopLoginPhoneCodeMap.containsKey(phoneNumber)){
            return shopLoginPhoneCodeMap.get(phoneNumber).equals(code);
        }
        else {
            return false;
        }
    }

    /**
     * 移除一个商家登录发送的验证码
     * @param phoneNumber
     * @return
     */
    public static void RemoveShopLoginPhoneCodeMap(String phoneNumber){
        if (shopLoginPhoneCodeMap.containsKey(phoneNumber)){
            shopLoginPhoneCodeMap.remove(phoneNumber);
        }
    }

    /**
     * 添加一个商家登录发送的验证码
     * @param phoneNumber
     * @param code
     */
    public static void AddShopLoginPhoneCodeMap(String phoneNumber,String code){
        if (shopLoginPhoneCodeMap.containsKey(phoneNumber)){
            shopLoginPhoneCodeMap.remove(phoneNumber);
        }
        shopLoginPhoneCodeMap.put(phoneNumber,code);
    }
}
