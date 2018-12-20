package com.guojiang.bean;

public class PServiceProjectConfig {
    private Integer spId;

    private Integer spServicetype;

    private String spDes;

    private String spRemake;

    public Integer getSpId() {
        return spId;
    }

    public void setSpId(Integer spId) {
        this.spId = spId;
    }

    public Integer getSpServicetype() {
        return spServicetype;
    }

    public void setSpServicetype(Integer spServicetype) {
        this.spServicetype = spServicetype;
    }

    public String getSpDes() {
        return spDes;
    }

    public void setSpDes(String spDes) {
        this.spDes = spDes == null ? null : spDes.trim();
    }

    public String getSpRemake() {
        return spRemake;
    }

    public void setSpRemake(String spRemake) {
        this.spRemake = spRemake == null ? null : spRemake.trim();
    }
}