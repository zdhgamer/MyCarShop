package com.guojiang.bean;

public class PCosmetologyServiceConfig {
    private Integer ctsId;

    private Integer ctsSpId;

    private Integer ctsType;

    private String ctsDes;

    public Integer getCtsId() {
        return ctsId;
    }

    public void setCtsId(Integer ctsId) {
        this.ctsId = ctsId;
    }

    public Integer getCtsSpId() {
        return ctsSpId;
    }

    public void setCtsSpId(Integer ctsSpId) {
        this.ctsSpId = ctsSpId;
    }

    public Integer getCtsType() {
        return ctsType;
    }

    public void setCtsType(Integer ctsType) {
        this.ctsType = ctsType;
    }

    public String getCtsDes() {
        return ctsDes;
    }

    public void setCtsDes(String ctsDes) {
        this.ctsDes = ctsDes == null ? null : ctsDes.trim();
    }
}