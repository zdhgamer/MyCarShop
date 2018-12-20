package com.guojiang.bean;

public class SShopShareGain {
    private Integer ssgId;

    private Integer ssgSpId;

    private Integer ssgUid;

    private Long ssgGainnumber;

    private Long ssgGaintime;

    private UserInfo userInfo;

    public Integer getSsgId() {
        return ssgId;
    }

    public void setSsgId(Integer ssgId) {
        this.ssgId = ssgId;
    }

    public Integer getSsgSpId() {
        return ssgSpId;
    }

    public void setSsgSpId(Integer ssgSpId) {
        this.ssgSpId = ssgSpId;
    }

    public Integer getSsgUid() {
        return ssgUid;
    }

    public void setSsgUid(Integer ssgUid) {
        this.ssgUid = ssgUid;
    }

    public Long getSsgGainnumber() {
        return ssgGainnumber;
    }

    public void setSsgGainnumber(Long ssgGainnumber) {
        this.ssgGainnumber = ssgGainnumber;
    }

    public Long getSsgGaintime() {
        return ssgGaintime;
    }

    public void setSsgGaintime(Long ssgGaintime) {
        this.ssgGaintime = ssgGaintime;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
}