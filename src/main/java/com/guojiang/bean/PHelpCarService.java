package com.guojiang.bean;

public class PHelpCarService {
    private Integer phsId;

    private Integer phsConfigId;

    private Long phsOrdermoney;

    private Integer phsState;

    private String phsCarDes;

    public Integer getPhsId() {
        return phsId;
    }

    public void setPhsId(Integer phsId) {
        this.phsId = phsId;
    }

    public Integer getPhsConfigId() {
        return phsConfigId;
    }

    public void setPhsConfigId(Integer phsConfigId) {
        this.phsConfigId = phsConfigId;
    }

    public Long getPhsOrdermoney() {
        return phsOrdermoney;
    }

    public void setPhsOrdermoney(Long phsOrdermoney) {
        this.phsOrdermoney = phsOrdermoney;
    }

    public Integer getPhsState() {
        return phsState;
    }

    public void setPhsState(Integer phsState) {
        this.phsState = phsState;
    }

    public String getPhsCarDes() {
        return phsCarDes;
    }

    public void setPhsCarDes(String phsCarDes) {
        this.phsCarDes = phsCarDes == null ? null : phsCarDes.trim();
    }
}