package com.guojiang.bean;

public class SShopMaintainService {
    private Integer smoId;

    private Integer smoShopId;

    private Integer smoStsId;

    private Long smoOrdermoney;

    private Integer smoState;

    private Integer smoCarsizeId;

    private Long smoCreatetime;

    private Long smoChecktime;

    private Integer smoCheckstate;

    private String smoCheckdes;

    private NetCarSizeConfig config;

    public Integer getSmoId() {
        return smoId;
    }

    public void setSmoId(Integer smoId) {
        this.smoId = smoId;
    }

    public Integer getSmoShopId() {
        return smoShopId;
    }

    public void setSmoShopId(Integer smoShopId) {
        this.smoShopId = smoShopId;
    }

    public Integer getSmoStsId() {
        return smoStsId;
    }

    public void setSmoStsId(Integer smoStsId) {
        this.smoStsId = smoStsId;
    }

    public Long getSmoOrdermoney() {
        return smoOrdermoney;
    }

    public void setSmoOrdermoney(Long smoOrdermoney) {
        this.smoOrdermoney = smoOrdermoney;
    }

    public Integer getSmoState() {
        return smoState;
    }

    public void setSmoState(Integer smoState) {
        this.smoState = smoState;
    }

    public Integer getSmoCarsizeId() {
        return smoCarsizeId;
    }

    public void setSmoCarsizeId(Integer smoCarsizeId) {
        this.smoCarsizeId = smoCarsizeId;
    }

    public Long getSmoCreatetime() {
        return smoCreatetime;
    }

    public void setSmoCreatetime(Long smoCreatetime) {
        this.smoCreatetime = smoCreatetime;
    }

    public Long getSmoChecktime() {
        return smoChecktime;
    }

    public void setSmoChecktime(Long smoChecktime) {
        this.smoChecktime = smoChecktime;
    }

    public Integer getSmoCheckstate() {
        return smoCheckstate;
    }

    public void setSmoCheckstate(Integer smoCheckstate) {
        this.smoCheckstate = smoCheckstate;
    }

    public String getSmoCheckdes() {
        return smoCheckdes;
    }

    public void setSmoCheckdes(String smoCheckdes) {
        this.smoCheckdes = smoCheckdes == null ? null : smoCheckdes.trim();
    }

    public NetCarSizeConfig getConfig() {
        return config;
    }

    public void setConfig(NetCarSizeConfig config) {
        this.config = config;
    }
}