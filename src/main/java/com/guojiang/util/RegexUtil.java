package com.guojiang.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则匹配工具
 */
public class RegexUtil {

    /**
     * 是否是验证码
     * @param code
     * @return
     */
    public static boolean isCheckCode(String code){
        String regex = "^[0-9]{6}$";
        if(code == null || code.length()!=6){
            return false;
        }
        else {
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(code);
            boolean isMatch = m.matches();
            return isMatch;
        }
    }

    /**
     * 是否是电话号码
     * @param phone
     * @return
     */
    public static boolean isPhone(String phone) {
        String regex = "^(((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9])))\\d{8}$";
        if (phone == null) {
            return false;
        } else {
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(phone);
            boolean isMatch = m.matches();
            return isMatch;
        }
    }

    /**
     * 是否是密码
     * @param passWord
     * @return
     */
    public static boolean isPassWord(String passWord) {
        String regex = "^[a-zA-Z]+[a-zA-Z0-9]+{6,20}$";
        if(passWord == null || passWord.length()<8 || passWord.length()>16){
            return false;
        }
        else
        {
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(passWord);
            boolean isMatch = m.matches();
            return isMatch;
        }
    }

    /**
     * 是否是真实姓名
     * @param name
     * @return
     */
    public static boolean isRealName(String name) {
        if(name == null || name.trim().equals("")){
            return false;
        }
        else {
            Pattern p = Pattern.compile("^[\u4E00-\u9FA5]+{2,16}");
            Matcher m = p.matcher(name);
            return m.matches();
        }
    }

    /**
     * 是否是身份证号码
     * @param IDCard
     * @return
     */
    public static boolean isIDCardNumber(String IDCard) {
        if (IDCard == null || "".equals(IDCard)) {
            return false;
        }

        String regularExpression = "(^[1-9]\\d{5}(18|19|20)\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)|" +
                "(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}$)";

        boolean matches = IDCard.matches(regularExpression);

        if (matches) {

            if (IDCard.length() == 18) {
                try {
                    char[] charArray = IDCard.toCharArray();
                    int[] idCardWi = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
                    String[] idCardY = {"1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2"};
                    int sum = 0;
                    for (int i = 0; i < idCardWi.length; i++) {
                        int current = Integer.parseInt(String.valueOf(charArray[i]));
                        int count = current * idCardWi[i];
                        sum += count;
                    }
                    char idCardLast = charArray[17];
                    int idCardMod = sum % 11;
                    if (idCardY[idCardMod].toUpperCase().equals(String.valueOf(idCardLast).toUpperCase())) {
                        return true;
                    } else {
                        System.out.println("错误:" + String.valueOf(idCardLast).toUpperCase() +
                                "应该是:" + idCardY[idCardMod].toUpperCase());
                        return false;
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("身份证验证报错:" + IDCard);
                    return false;
                }
            }

        }
        return matches;
    }
}
