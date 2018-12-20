package com.guojiang.bean;

public class UCarToUser {
    private Integer cId;

    private Integer cUid;

    private Integer cType;

    private String cBrand;

    private String cModel;

    private String cDescription;

    private String cEnginecapacity;

    private String cProductiveyear;

    private String cImagedes;

    private String cTheroadoftime;

    private Double cRoadhaul;

    private Long cRegistertime;

    private String cRemake;

    public Integer getcId() {
        return cId;
    }

    public void setcId(Integer cId) {
        this.cId = cId;
    }

    public Integer getcUid() {
        return cUid;
    }

    public void setcUid(Integer cUid) {
        this.cUid = cUid;
    }

    public Integer getcType() {
        return cType;
    }

    public void setcType(Integer cType) {
        this.cType = cType;
    }

    public String getcBrand() {
        return cBrand;
    }

    public void setcBrand(String cBrand) {
        this.cBrand = cBrand == null ? null : cBrand.trim();
    }

    public String getcModel() {
        return cModel;
    }

    public void setcModel(String cModel) {
        this.cModel = cModel == null ? null : cModel.trim();
    }

    public String getcDescription() {
        return cDescription;
    }

    public void setcDescription(String cDescription) {
        this.cDescription = cDescription == null ? null : cDescription.trim();
    }

    public String getcEnginecapacity() {
        return cEnginecapacity;
    }

    public void setcEnginecapacity(String cEnginecapacity) {
        this.cEnginecapacity = cEnginecapacity == null ? null : cEnginecapacity.trim();
    }

    public String getcProductiveyear() {
        return cProductiveyear;
    }

    public void setcProductiveyear(String cProductiveyear) {
        this.cProductiveyear = cProductiveyear == null ? null : cProductiveyear.trim();
    }

    public String getcImagedes() {
        return cImagedes;
    }

    public void setcImagedes(String cImagedes) {
        this.cImagedes = cImagedes == null ? null : cImagedes.trim();
    }

    public String getcTheroadoftime() {
        return cTheroadoftime;
    }

    public void setcTheroadoftime(String cTheroadoftime) {
        this.cTheroadoftime = cTheroadoftime == null ? null : cTheroadoftime.trim();
    }

    public Double getcRoadhaul() {
        return cRoadhaul;
    }

    public void setcRoadhaul(Double cRoadhaul) {
        this.cRoadhaul = cRoadhaul;
    }

    public Long getcRegistertime() {
        return cRegistertime;
    }

    public void setcRegistertime(Long cRegistertime) {
        this.cRegistertime = cRegistertime;
    }

    public String getcRemake() {
        return cRemake;
    }

    public void setcRemake(String cRemake) {
        this.cRemake = cRemake == null ? null : cRemake.trim();
    }
}