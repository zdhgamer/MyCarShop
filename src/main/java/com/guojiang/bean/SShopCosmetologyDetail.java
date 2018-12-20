package com.guojiang.bean;

public class SShopCosmetologyDetail {
    private Integer scdsId;

    private Integer scdsSpShopId;

    private Integer scdsSpStsId;

    private Integer scdsScsId;

    private String scdsCtsId;

    private Long scdsServicecharge;

    private Integer scdsState;

    private Long scdsCreatetime;

    private Long scdsChecktime;

    private Integer scdsCheckstate;

    private String scdsCheckdes;

    private Integer scdsFinishtime;

    public Integer getScdsId() {
        return scdsId;
    }

    public void setScdsId(Integer scdsId) {
        this.scdsId = scdsId;
    }

    public Integer getScdsSpShopId() {
        return scdsSpShopId;
    }

    public void setScdsSpShopId(Integer scdsSpShopId) {
        this.scdsSpShopId = scdsSpShopId;
    }

    public Integer getScdsSpStsId() {
        return scdsSpStsId;
    }

    public void setScdsSpStsId(Integer scdsSpStsId) {
        this.scdsSpStsId = scdsSpStsId;
    }

    public Integer getScdsScsId() {
        return scdsScsId;
    }

    public void setScdsScsId(Integer scdsScsId) {
        this.scdsScsId = scdsScsId;
    }

    public String getScdsCtsId() {
        return scdsCtsId;
    }

    public void setScdsCtsId(String scdsCtsId) {
        this.scdsCtsId = scdsCtsId == null ? null : scdsCtsId.trim();
    }

    public Long getScdsServicecharge() {
        return scdsServicecharge;
    }

    public void setScdsServicecharge(Long scdsServicecharge) {
        this.scdsServicecharge = scdsServicecharge;
    }

    public Integer getScdsState() {
        return scdsState;
    }

    public void setScdsState(Integer scdsState) {
        this.scdsState = scdsState;
    }

    public Long getScdsCreatetime() {
        return scdsCreatetime;
    }

    public void setScdsCreatetime(Long scdsCreatetime) {
        this.scdsCreatetime = scdsCreatetime;
    }

    public Long getScdsChecktime() {
        return scdsChecktime;
    }

    public void setScdsChecktime(Long scdsChecktime) {
        this.scdsChecktime = scdsChecktime;
    }

    public Integer getScdsCheckstate() {
        return scdsCheckstate;
    }

    public void setScdsCheckstate(Integer scdsCheckstate) {
        this.scdsCheckstate = scdsCheckstate;
    }

    public String getScdsCheckdes() {
        return scdsCheckdes;
    }

    public void setScdsCheckdes(String scdsCheckdes) {
        this.scdsCheckdes = scdsCheckdes == null ? null : scdsCheckdes.trim();
    }

    public Integer getScdsFinishtime() {
        return scdsFinishtime;
    }

    public void setScdsFinishtime(Integer scdsFinishtime) {
        this.scdsFinishtime = scdsFinishtime;
    }
}