package com.guojiang.util;

import java.io.File;
import java.util.Random;
import java.util.UUID;

/**
 * 公共方法
 */
public class CommonUtil {

    /**
     * 获取去掉小数点后的long值
     * @param value
     * @return
     */
    public static Long getLongStringSubPoint(String value){
        value = "0" + value;
        String[] Initmoney1 = value.split("\\.");
        long result = new Long(Initmoney1[0]);
        return result;
    }

    /**
     * 生成随机长度纯数字字符串
     *
     * @param number
     * @return
     */
    public static String getRandomNumCode(int number) {
        String codeNum = "";
        int[] numbers = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        Random random = new Random();
        for (int i = 0; i < number; i++) {
            int next = random.nextInt(10000);
            System.out.println(next);
            codeNum += numbers[next % 10];
        }
        System.out.println("生成的数字验证码是:--------"+codeNum);
        return codeNum;
    }

    /**
     * 获取物理存贮的商家取车人驾驶证存贮路径
     * @return
     */
    public static String getShopGetCarDriverImagesStorePath(){
        return  "files"+ File.separator+"uploadfiles"+File.separator+"driverImages";
    }

    /**
     * 获取数据库里面应该存贮的存贮的商家取车人驾驶证存贮路径
     * @param fileName
     * @return
     */
    public static String getShopGetCarDriverImagesSQLPath(String fileName){
        return  File.separator+"files"+ File.separator+"uploadfiles"+File.separator+"driverImages"+File.separator+fileName;
    }


    /**
     * 获取物理存贮的商家商品图片地址
     * @return
     */
    public static String getShopGetShopItemImagesStorePath(){
        return  "files"+ File.separator+"uploadfiles"+File.separator+"shopItemImages";
    }

    /**
     * 获取数据库里面应该存贮的存贮的商家商品图片地址
     * @param fileName
     * @return
     */
    public static String getShopGetShopItemImagesSQLPath(String fileName){
        return  File.separator+"files"+ File.separator+"uploadfiles"+File.separator+"shopItemImages"+File.separator+fileName;
    }

    /**
     * 获取物理存贮的商家商品图片地址
     * @return
     */
    public static String getShopGetShopBannerImagesStorePath(){
        return  "files"+ File.separator+"uploadfiles"+File.separator+"shopBannerImages";
    }

    /**
     * 获取数据库里面应该存贮的存贮的商家商品图片地址
     * @param fileName
     * @return
     */
    public static String getShopGetShopBannerImagesSQLPath(String fileName){
        return  File.separator+"files"+ File.separator+"uploadfiles"+File.separator+"shopBannerImages"+File.separator+fileName;
    }

    /**
     * 获取物理存贮的商家商品图片地址
     * @return
     */
    public static String getShopGetShopDetailImagesStorePath(){
        return  "files"+ File.separator+"uploadfiles"+File.separator+"shopBannerImages";
    }

    /**
     * 获取数据库里面应该存贮的存贮的商家商品图片地址
     * @param fileName
     * @return
     */
    public static String getShopGetShopDetailImagesSQLPath(String fileName){
        return  File.separator+"files"+ File.separator+"uploadfiles"+File.separator+"shopBannerImages"+File.separator+fileName;
    }

    /**
     * 获取文件唯一id
     * @param userAccount
     * @param filename
     * @return
     */
    public static String makeFileName(String userAccount,String filename){  //2.jpg
        return UUID.randomUUID().toString() + "_" +userAccount+"_"+ filename;
    }

}
