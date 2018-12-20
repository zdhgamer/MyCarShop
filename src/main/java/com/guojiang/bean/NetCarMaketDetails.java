package com.guojiang.bean;

public class NetCarMaketDetails {
    private Integer nmdId;

    private Integer nmdI;

    private String nmdN;

    private String nmdJson;

    public Integer getNmdId() {
        return nmdId;
    }

    public void setNmdId(Integer nmdId) {
        this.nmdId = nmdId;
    }

    public Integer getNmdI() {
        return nmdI;
    }

    public void setNmdI(Integer nmdI) {
        this.nmdI = nmdI;
    }

    public String getNmdN() {
        return nmdN;
    }

    public void setNmdN(String nmdN) {
        this.nmdN = nmdN == null ? null : nmdN.trim();
    }

    public String getNmdJson() {
        return nmdJson;
    }

    public void setNmdJson(String nmdJson) {
        this.nmdJson = nmdJson == null ? null : nmdJson.trim();
    }
}