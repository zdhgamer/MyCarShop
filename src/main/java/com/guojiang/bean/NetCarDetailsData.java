package com.guojiang.bean;

public class NetCarDetailsData {
    private Integer ncdId;

    private Integer ncdI;

    private String ncdL;

    private String ncdN;

    private String ncdJsondata;

    public Integer getNcdId() {
        return ncdId;
    }

    public void setNcdId(Integer ncdId) {
        this.ncdId = ncdId;
    }

    public Integer getNcdI() {
        return ncdI;
    }

    public void setNcdI(Integer ncdI) {
        this.ncdI = ncdI;
    }

    public String getNcdL() {
        return ncdL;
    }

    public void setNcdL(String ncdL) {
        this.ncdL = ncdL == null ? null : ncdL.trim();
    }

    public String getNcdN() {
        return ncdN;
    }

    public void setNcdN(String ncdN) {
        this.ncdN = ncdN == null ? null : ncdN.trim();
    }

    public String getNcdJsondata() {
        return ncdJsondata;
    }

    public void setNcdJsondata(String ncdJsondata) {
        this.ncdJsondata = ncdJsondata == null ? null : ncdJsondata.trim();
    }
}