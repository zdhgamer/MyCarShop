package com.guojiang.bean;

public class NetCarSizeConfig {
    private Integer csId;

    private Integer csType;

    private String csDes;

    public Integer getCsId() {
        return csId;
    }

    public void setCsId(Integer csId) {
        this.csId = csId;
    }

    public Integer getCsType() {
        return csType;
    }

    public void setCsType(Integer csType) {
        this.csType = csType;
    }

    public String getCsDes() {
        return csDes;
    }

    public void setCsDes(String csDes) {
        this.csDes = csDes == null ? null : csDes.trim();
    }
}