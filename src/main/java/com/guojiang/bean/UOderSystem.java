package com.guojiang.bean;

public class UOderSystem {
    private Integer oId;

    private Integer oSid;

    private Integer oUid;

    private String oLocationdetails;

    private Long oPaytime;

    private String oSerialnumber;

    private Long oServiceprice;

    private Long oAlldiscounts;

    private Long oNeedpay;

    private Long oSubscription;

    private Long oActualpayment;

    private Long oRemainingpayment;

    private Integer oUooId;

    private Integer oState;

    private Long oCreatetime;

    private String oRemake;

    private Integer oDaycount;

    private Integer oPayway;

    private Integer oUserCarId;

    private Integer oIsgetcaruser;

    private Long oGetcarmoney;

    private UserInfo userInfo;

    public Integer getoId() {
        return oId;
    }

    public void setoId(Integer oId) {
        this.oId = oId;
    }

    public Integer getoSid() {
        return oSid;
    }

    public void setoSid(Integer oSid) {
        this.oSid = oSid;
    }

    public Integer getoUid() {
        return oUid;
    }

    public void setoUid(Integer oUid) {
        this.oUid = oUid;
    }

    public String getoLocationdetails() {
        return oLocationdetails;
    }

    public void setoLocationdetails(String oLocationdetails) {
        this.oLocationdetails = oLocationdetails == null ? null : oLocationdetails.trim();
    }

    public Long getoPaytime() {
        return oPaytime;
    }

    public void setoPaytime(Long oPaytime) {
        this.oPaytime = oPaytime;
    }

    public String getoSerialnumber() {
        return oSerialnumber;
    }

    public void setoSerialnumber(String oSerialnumber) {
        this.oSerialnumber = oSerialnumber == null ? null : oSerialnumber.trim();
    }

    public Long getoServiceprice() {
        return oServiceprice;
    }

    public void setoServiceprice(Long oServiceprice) {
        this.oServiceprice = oServiceprice;
    }

    public Long getoAlldiscounts() {
        return oAlldiscounts;
    }

    public void setoAlldiscounts(Long oAlldiscounts) {
        this.oAlldiscounts = oAlldiscounts;
    }

    public Long getoNeedpay() {
        return oNeedpay;
    }

    public void setoNeedpay(Long oNeedpay) {
        this.oNeedpay = oNeedpay;
    }

    public Long getoSubscription() {
        return oSubscription;
    }

    public void setoSubscription(Long oSubscription) {
        this.oSubscription = oSubscription;
    }

    public Long getoActualpayment() {
        return oActualpayment;
    }

    public void setoActualpayment(Long oActualpayment) {
        this.oActualpayment = oActualpayment;
    }

    public Long getoRemainingpayment() {
        return oRemainingpayment;
    }

    public void setoRemainingpayment(Long oRemainingpayment) {
        this.oRemainingpayment = oRemainingpayment;
    }

    public Integer getoUooId() {
        return oUooId;
    }

    public void setoUooId(Integer oUooId) {
        this.oUooId = oUooId;
    }

    public Integer getoState() {
        return oState;
    }

    public void setoState(Integer oState) {
        this.oState = oState;
    }

    public Long getoCreatetime() {
        return oCreatetime;
    }

    public void setoCreatetime(Long oCreatetime) {
        this.oCreatetime = oCreatetime;
    }

    public String getoRemake() {
        return oRemake;
    }

    public void setoRemake(String oRemake) {
        this.oRemake = oRemake == null ? null : oRemake.trim();
    }

    public Integer getoDaycount() {
        return oDaycount;
    }

    public void setoDaycount(Integer oDaycount) {
        this.oDaycount = oDaycount;
    }

    public Integer getoPayway() {
        return oPayway;
    }

    public void setoPayway(Integer oPayway) {
        this.oPayway = oPayway;
    }

    public Integer getoUserCarId() {
        return oUserCarId;
    }

    public void setoUserCarId(Integer oUserCarId) {
        this.oUserCarId = oUserCarId;
    }

    public Integer getoIsgetcaruser() {
        return oIsgetcaruser;
    }

    public void setoIsgetcaruser(Integer oIsgetcaruser) {
        this.oIsgetcaruser = oIsgetcaruser;
    }

    public Long getoGetcarmoney() {
        return oGetcarmoney;
    }

    public void setoGetcarmoney(Long oGetcarmoney) {
        this.oGetcarmoney = oGetcarmoney;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
}