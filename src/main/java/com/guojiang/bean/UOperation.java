package com.guojiang.bean;

public class UOperation {
    private Integer uooId;

    private Long uooSubmittime;

    private String uooDes;

    private Integer uooState;

    private Integer uooOId;

    private Integer uooGetCarId;

    private Integer uooBackCarId;

    private Integer uooShopId;

    private PGetCarUserInfo getCarUserInfo;

    private PGetCarUserInfo backCarUserInfo;

    public Integer getUooId() {
        return uooId;
    }

    public void setUooId(Integer uooId) {
        this.uooId = uooId;
    }

    public Long getUooSubmittime() {
        return uooSubmittime;
    }

    public void setUooSubmittime(Long uooSubmittime) {
        this.uooSubmittime = uooSubmittime;
    }

    public String getUooDes() {
        return uooDes;
    }

    public void setUooDes(String uooDes) {
        this.uooDes = uooDes == null ? null : uooDes.trim();
    }

    public Integer getUooState() {
        return uooState;
    }

    public void setUooState(Integer uooState) {
        this.uooState = uooState;
    }

    public Integer getUooOId() {
        return uooOId;
    }

    public void setUooOId(Integer uooOId) {
        this.uooOId = uooOId;
    }

    public Integer getUooGetCarId() {
        return uooGetCarId;
    }

    public void setUooGetCarId(Integer uooGetCarId) {
        this.uooGetCarId = uooGetCarId;
    }

    public Integer getUooBackCarId() {
        return uooBackCarId;
    }

    public void setUooBackCarId(Integer uooBackCarId) {
        this.uooBackCarId = uooBackCarId;
    }

    public Integer getUooShopId() {
        return uooShopId;
    }

    public void setUooShopId(Integer uooShopId) {
        this.uooShopId = uooShopId;
    }

    public PGetCarUserInfo getGetCarUserInfo() {
        return getCarUserInfo;
    }

    public void setGetCarUserInfo(PGetCarUserInfo getCarUserInfo) {
        this.getCarUserInfo = getCarUserInfo;
    }

    public PGetCarUserInfo getBackCarUserInfo() {
        return backCarUserInfo;
    }

    public void setBackCarUserInfo(PGetCarUserInfo backCarUserInfo) {
        this.backCarUserInfo = backCarUserInfo;
    }
}