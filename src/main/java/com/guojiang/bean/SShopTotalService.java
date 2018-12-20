package com.guojiang.bean;

public class SShopTotalService {
    private Integer stsId;

    private Integer stsSpShopId;

    private Integer stsSpId;

    private Integer stsState;

    private Integer stsPauseState;

    private PServiceProjectConfig config;

    public Integer getStsId() {
        return stsId;
    }

    public void setStsId(Integer stsId) {
        this.stsId = stsId;
    }

    public Integer getStsSpShopId() {
        return stsSpShopId;
    }

    public void setStsSpShopId(Integer stsSpShopId) {
        this.stsSpShopId = stsSpShopId;
    }

    public Integer getStsSpId() {
        return stsSpId;
    }

    public void setStsSpId(Integer stsSpId) {
        this.stsSpId = stsSpId;
    }

    public Integer getStsState() {
        return stsState;
    }

    public void setStsState(Integer stsState) {
        this.stsState = stsState;
    }

    public Integer getStsPauseState() {
        return stsPauseState;
    }

    public void setStsPauseState(Integer stsPauseState) {
        this.stsPauseState = stsPauseState;
    }

    public PServiceProjectConfig getConfig() {
        return config;
    }

    public void setConfig(PServiceProjectConfig config) {
        this.config = config;
    }
}