package com.guojiang.bean;

public class PShopItemConfig {
    private Integer pshcId;

    private Integer pshcType;

    private String pshcDes;

    private Integer pshcState;

    public Integer getPshcId() {
        return pshcId;
    }

    public void setPshcId(Integer pshcId) {
        this.pshcId = pshcId;
    }

    public Integer getPshcType() {
        return pshcType;
    }

    public void setPshcType(Integer pshcType) {
        this.pshcType = pshcType;
    }

    public String getPshcDes() {
        return pshcDes;
    }

    public void setPshcDes(String pshcDes) {
        this.pshcDes = pshcDes == null ? null : pshcDes.trim();
    }

    public Integer getPshcState() {
        return pshcState;
    }

    public void setPshcState(Integer pshcState) {
        this.pshcState = pshcState;
    }
}