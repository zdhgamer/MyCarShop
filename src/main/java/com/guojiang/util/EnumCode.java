package com.guojiang.util;

import org.aspectj.weaver.ast.Not;

/**
 * 各种枚举值
 */
public class EnumCode {

    public enum  CanUseCode{
        /**
         * 可以使用
         */
        CanUse,
        /**
         * 不可以使用
         */
        CanNotUse,
    }

    public enum CheckCodeState{
        /**
         * 默认
         */
        Nil,
        /**
         * 审核中
         */
        Checking,
        /**
         * 同意
         */
        Agree,
        /**
         * 不同意
         */
        DisAgree,
    }

    /**
     * 商品的默认状态
     */
    public enum DefaultShopItemState{
        /**
         * 不是默认
         */
        Not,
        /**
         * 是默认
         */
        Default,
    }

    /**
     * 运营状态
     */
    public enum RunState{
        /**
         * 正在运营
         */
        Run,
        /**
         * 未在运营
         */
        NotRun,
    }

    /**
     * 服务大类
     */
    public enum TotalServiceType {
        /**
         * 空
         */
        Nil,
        /**
         * 保养
         */
        UpKeep,
        /**
         * 美容
         */
        Cosmetology,
        /**
         * 维修
         */
        Repair,
        /**
         * 年度审核
         */
        AnnualCheck,
        /**
         * 汽车救援
         */
        HelpCar,
    }

    /**
     * 服务归属
     */
    public enum ServiceBelong{
        /**
         * 空
         */
        Nil,
        /**
         * 商家
         */
        Shop,
        /**
         * 平台
         */
        Plath,
    }

    /**
     * 是否需要取车人和还车
     */
    public enum NeedGetCarUser{
        /**
         * 不需要
         */
        NotNeed,
        /**
         * 需要
         */
        Need,
    }

    /**
     * 商家是否被平台停运
     */
    public enum ShopOperationState{
        /**
         * 正常运营
         */
        Run,
        /**
         * 被平台冻结
         */
        NotRun,
    }

    /**
     * 订单状态
     */
    public enum OderState{
        /**
         * 申请订单 0
         */
        Request,
        /**
         * 商家接单 1
         */
        ShopAgree,
        /**
         * 已指派取车人 2
         */
        SendGetCarAssist,
        /**
         * 已取车 3
         */
        AssistGetCar,
        /**
         * 开始服务 4
         */
        StartService,
        /**
         * 结束服务 5
         */
        EndService,
        /**
         * 指派归还人员 6
         */
        SendBackCarAssist,
        /**
         * 已还车 7
         */
        AssistBackCar,
        /**
         * 拒绝订单 8
         */
        Cancel,
        /**
         * 平台接单 9
         */
        PlatformAgree,
        /**
         * 用户订单已评价 10
         * */
        OrderHaveOpration,
    }

    /**
     * 支付状态
     */
    public enum OderPayWay{
        /**
         * 线下支付
         */
        OutLIne,
        /**
         * 微信支付
         */
        WeChat,
        /**
         * 支付宝支付
         */
        AliPay,
        /**
         * 订单待付钱 3
         * */
        OrderNoPayMoney,
    }

}
