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
    }
}
