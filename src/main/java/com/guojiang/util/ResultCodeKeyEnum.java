package com.guojiang.util;

import org.junit.internal.runners.statements.Fail;

public class ResultCodeKeyEnum {

    public enum ResultCodeKey {
        /**
         * 失败
         */
        Fail,
        /**
         * 成功
         */
        Success,
        /**
         * 手机号码为空
         */
        NoPhoneNumber,
        /**
         * 手机号码格式不正确
         */
        ErrorFPhoneNumber,
        /**
         * 没有商家信息
         */
        NoShopInfo,
        /**
         * 没有验证码
         */
        NoCheckCode,
        /**
         * 验证码错误
         */
        ErrorCheckCode,
        /**
         * 验证码格式不正确
         */
        ErrorFCheckCode,
        /**
         * 未登录操作
         */
        NoLogin,
        /**
         * 真实姓名不能为空
         */
        NoRealName,
        /**
         * 真实姓名格式错误
         */
        ErrorFRealName,
        /**
         * 身份证号码为空
         */
        NoIdCardNumber,
        /**
         * 身份证号码格式错误
         */
        ErrorFIdCardNumber,
        /**
         * 图片为空
         */
        NoImage,
        /**
         * 图片格式不正确
         */
        ErrorFImage,
        /**
         * 超过文件限制大小
         */
        GreaterFileSize,
        /**
         * 已有该取车人
         */
        ExistThisGetCarUser,
        /**
         * 文件存贮失败
         */
        FileSaveFail,
        /**
         * 没有该取车人
         */
        NoGetCarUser,
        /**
         * 没有该商品类别
         */
        NoShopItemType,
        /**
         * 已存在该商品类别
         */
        ExistThisShopItemType,
        /**
         * 没有商品描述信息
         */
        NoShopItemDes,
        /**
         * 没有价格
         */
        NoPrice,
        /**
         * 没有选择车型
         */
        NoCarType,
        /**
         * 请填写正数
         */
        LetPositiveNumber,

        /**
         * 没有该类型的车
         */
        NoThisCarType,

        /**
         * 该商品不存在
         */
        NoThisShopItem,

        /**
         * 没有该类服务
         */
        NoServiceType,

        /**
         * 存在该类服务
         */
        ExistThisServiceType,

        /**
         * 没有该车型
         */
        NoCarSize,

        /**
         * 正在审核中
         */
        Checking,
        /**
         * 没有描述信息
         */
        NoDes,
        /**
         * 有子项在审核中
         */
        ExistSubChecking,

        /**
         * 没有默认商品
         */
        NoDefaultShopItem,
        /**
         * 请填写正的时间
         */
        LetPositiveTime,
        /**
         * 订单不存在
         */
        NoOder,
        /**
         * 分页不存在
         */
        NoPage,
        /**
         * 状态错误
         */
        ErrorState,
        /**
         * 总车位必须大于服务中的车位数
         */
        LetTotalCarPosMoreServing,
        /**
         * 选择整数的星星
         */
        LetPositiveStar,
        /**
         * 已存在评价
         */
        ExistEvaluation,
        /**
         * 订单未完成
         */
        OderNotFinish,
        /**
         * 余额不足
         */
        LastMoneyNotFull,
        /**
         * 小于最小提现限制
         */
        LessMinDrawMoney,
        /**
         * 没有服务名字
         */
        NoServiceName,
        /**
         * 不允许操作，请联系平台管理员
         */
        NoCantOperation,
        /**
         * 没有任何服务提供
         */
        NoAnyService,
    }
}
