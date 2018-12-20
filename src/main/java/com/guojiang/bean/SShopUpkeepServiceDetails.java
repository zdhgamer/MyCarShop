package com.guojiang.bean;

public class SShopUpkeepServiceDetails {
    private Integer spsdId;

    private Integer spsdShopId;

    private Integer spsdStsId;

    private Integer spsdUkssId;

    private Integer spsdUkId;

    private Integer spsdState;

    private Long spsdCreatetime;

    private Long spsdChecktime;

    private Integer spsdCheckstate;

    private String spsdCheckdes;

    private Integer spsdFinishtime;

    private SShopItemData config;

    public Integer getSpsdId() {
        return spsdId;
    }

    public void setSpsdId(Integer spsdId) {
        this.spsdId = spsdId;
    }

    public Integer getSpsdShopId() {
        return spsdShopId;
    }

    public void setSpsdShopId(Integer spsdShopId) {
        this.spsdShopId = spsdShopId;
    }

    public Integer getSpsdStsId() {
        return spsdStsId;
    }

    public void setSpsdStsId(Integer spsdStsId) {
        this.spsdStsId = spsdStsId;
    }

    public Integer getSpsdUkssId() {
        return spsdUkssId;
    }

    public void setSpsdUkssId(Integer spsdUkssId) {
        this.spsdUkssId = spsdUkssId;
    }

    public Integer getSpsdUkId() {
        return spsdUkId;
    }

    public void setSpsdUkId(Integer spsdUkId) {
        this.spsdUkId = spsdUkId;
    }

    public Integer getSpsdState() {
        return spsdState;
    }

    public void setSpsdState(Integer spsdState) {
        this.spsdState = spsdState;
    }

    public Long getSpsdCreatetime() {
        return spsdCreatetime;
    }

    public void setSpsdCreatetime(Long spsdCreatetime) {
        this.spsdCreatetime = spsdCreatetime;
    }

    public Long getSpsdChecktime() {
        return spsdChecktime;
    }

    public void setSpsdChecktime(Long spsdChecktime) {
        this.spsdChecktime = spsdChecktime;
    }

    public Integer getSpsdCheckstate() {
        return spsdCheckstate;
    }

    public void setSpsdCheckstate(Integer spsdCheckstate) {
        this.spsdCheckstate = spsdCheckstate;
    }

    public String getSpsdCheckdes() {
        return spsdCheckdes;
    }

    public void setSpsdCheckdes(String spsdCheckdes) {
        this.spsdCheckdes = spsdCheckdes == null ? null : spsdCheckdes.trim();
    }

    public Integer getSpsdFinishtime() {
        return spsdFinishtime;
    }

    public void setSpsdFinishtime(Integer spsdFinishtime) {
        this.spsdFinishtime = spsdFinishtime;
    }

    public SShopItemData getConfig() {
        return config;
    }

    public void setConfig(SShopItemData config) {
        this.config = config;
    }
}