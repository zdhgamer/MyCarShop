package com.guojiang.bean;

public class UAdressTable {
    private Integer uaId;

    private Integer uaUid;

    private String uaRushpersonname;

    private String uaRushphonenumber;

    private String uaLocationprovece;

    private String uaLocationcity;

    private Integer uaIsdefalutadress;

    private String uaDetailslocation;

    private Double uaLocationpointw;

    private Double uaLocationpointj;

    private String uaRemake;

    public Integer getUaId() {
        return uaId;
    }

    public void setUaId(Integer uaId) {
        this.uaId = uaId;
    }

    public Integer getUaUid() {
        return uaUid;
    }

    public void setUaUid(Integer uaUid) {
        this.uaUid = uaUid;
    }

    public String getUaRushpersonname() {
        return uaRushpersonname;
    }

    public void setUaRushpersonname(String uaRushpersonname) {
        this.uaRushpersonname = uaRushpersonname == null ? null : uaRushpersonname.trim();
    }

    public String getUaRushphonenumber() {
        return uaRushphonenumber;
    }

    public void setUaRushphonenumber(String uaRushphonenumber) {
        this.uaRushphonenumber = uaRushphonenumber == null ? null : uaRushphonenumber.trim();
    }

    public String getUaLocationprovece() {
        return uaLocationprovece;
    }

    public void setUaLocationprovece(String uaLocationprovece) {
        this.uaLocationprovece = uaLocationprovece == null ? null : uaLocationprovece.trim();
    }

    public String getUaLocationcity() {
        return uaLocationcity;
    }

    public void setUaLocationcity(String uaLocationcity) {
        this.uaLocationcity = uaLocationcity == null ? null : uaLocationcity.trim();
    }

    public Integer getUaIsdefalutadress() {
        return uaIsdefalutadress;
    }

    public void setUaIsdefalutadress(Integer uaIsdefalutadress) {
        this.uaIsdefalutadress = uaIsdefalutadress;
    }

    public String getUaDetailslocation() {
        return uaDetailslocation;
    }

    public void setUaDetailslocation(String uaDetailslocation) {
        this.uaDetailslocation = uaDetailslocation == null ? null : uaDetailslocation.trim();
    }

    public Double getUaLocationpointw() {
        return uaLocationpointw;
    }

    public void setUaLocationpointw(Double uaLocationpointw) {
        this.uaLocationpointw = uaLocationpointw;
    }

    public Double getUaLocationpointj() {
        return uaLocationpointj;
    }

    public void setUaLocationpointj(Double uaLocationpointj) {
        this.uaLocationpointj = uaLocationpointj;
    }

    public String getUaRemake() {
        return uaRemake;
    }

    public void setUaRemake(String uaRemake) {
        this.uaRemake = uaRemake == null ? null : uaRemake.trim();
    }
}