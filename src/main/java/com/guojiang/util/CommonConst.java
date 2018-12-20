package com.guojiang.util;

public class CommonConst {
    /**
     * 分页展示的每一页数据
     */
    public static final int PageSize = 10;

    /**
     * 显示可达到的页数
     */
    public static final int navigatePages = 5;

    /**
     * 最小提现金额 1000
     */
    public static  final Long minDrawMoeny = Long.parseLong(10000000+"");

    /**
     * 平台地址
     */
    public static  final String PlathUrl = "http://192.168.0.107:80";

    /**
     * 更新取车和还车状态
     */
    public static final String getCarOperationMethod = "/api/UpdateOrderNum";

}
