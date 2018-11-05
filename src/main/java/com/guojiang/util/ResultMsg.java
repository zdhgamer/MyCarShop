package com.guojiang.util;

import com.guojiang.util.ResultCodeKeyEnum;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wei.bin on 2017/9/14.
 */
public class ResultMsg {


    //状态码
    private int code;

    //提示信息
    private String msg;

    //用户要返回给浏览器的数据
    private Map<String, Object> data = new HashMap<String, Object>();

    public static ResultMsg Success(Map<String, Object> data){
        ResultMsg result = new ResultMsg();
        result.setData(data);
        result.setCode(ResultCodeKeyEnum.ResultCodeKey.Success);
        result.setMsg("处理成功");
        return result;
    }

    public static ResultMsg Fail(Map<String, Object> data){
        ResultMsg result = new ResultMsg();
        result.setData(data);
        result.setCode(ResultCodeKeyEnum.ResultCodeKey.Fail);
        result.setMsg("处理失败");
        return result;
    }


    /**
     * 设置结果
     * @param code
     * @param data
     */
    public void SetResult(ResultCodeKeyEnum.ResultCodeKey code,Map<String, Object> data){
        setCode(code);
        setData(data);
        setMsg(ResultCodeMap.getCodeDes(code));
    }

    public int getCode() {
        return code;
    }

    public void setCode(ResultCodeKeyEnum.ResultCodeKey code) {
        this.code = code.ordinal();
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
