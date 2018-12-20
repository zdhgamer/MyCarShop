package com.guojiang.bean;

public class UViolationData {
    private Integer vdId;

    private Integer vdErrorCode;

    private String vdProvinceCode;

    private String vdProvince;

    private String vdCitys;

    private String vdCityCode;

    private String vdCityName;

    private Integer vdEngine;

    private Integer vdEngineno;

    private Integer vdClass;

    private Integer vdClassa;

    private Integer vdClassno;

    private String vdRemake;

    public Integer getVdId() {
        return vdId;
    }

    public void setVdId(Integer vdId) {
        this.vdId = vdId;
    }

    public Integer getVdErrorCode() {
        return vdErrorCode;
    }

    public void setVdErrorCode(Integer vdErrorCode) {
        this.vdErrorCode = vdErrorCode;
    }

    public String getVdProvinceCode() {
        return vdProvinceCode;
    }

    public void setVdProvinceCode(String vdProvinceCode) {
        this.vdProvinceCode = vdProvinceCode == null ? null : vdProvinceCode.trim();
    }

    public String getVdProvince() {
        return vdProvince;
    }

    public void setVdProvince(String vdProvince) {
        this.vdProvince = vdProvince == null ? null : vdProvince.trim();
    }

    public String getVdCitys() {
        return vdCitys;
    }

    public void setVdCitys(String vdCitys) {
        this.vdCitys = vdCitys == null ? null : vdCitys.trim();
    }

    public String getVdCityCode() {
        return vdCityCode;
    }

    public void setVdCityCode(String vdCityCode) {
        this.vdCityCode = vdCityCode == null ? null : vdCityCode.trim();
    }

    public String getVdCityName() {
        return vdCityName;
    }

    public void setVdCityName(String vdCityName) {
        this.vdCityName = vdCityName == null ? null : vdCityName.trim();
    }

    public Integer getVdEngine() {
        return vdEngine;
    }

    public void setVdEngine(Integer vdEngine) {
        this.vdEngine = vdEngine;
    }

    public Integer getVdEngineno() {
        return vdEngineno;
    }

    public void setVdEngineno(Integer vdEngineno) {
        this.vdEngineno = vdEngineno;
    }

    public Integer getVdClass() {
        return vdClass;
    }

    public void setVdClass(Integer vdClass) {
        this.vdClass = vdClass;
    }

    public Integer getVdClassa() {
        return vdClassa;
    }

    public void setVdClassa(Integer vdClassa) {
        this.vdClassa = vdClassa;
    }

    public Integer getVdClassno() {
        return vdClassno;
    }

    public void setVdClassno(Integer vdClassno) {
        this.vdClassno = vdClassno;
    }

    public String getVdRemake() {
        return vdRemake;
    }

    public void setVdRemake(String vdRemake) {
        this.vdRemake = vdRemake == null ? null : vdRemake.trim();
    }
}