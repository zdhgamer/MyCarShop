package com.guojiang.bean;

public class NetCarBrand {
    private Integer ncbId;

    private Integer ncbReturnid;

    private String ncdName;

    private String ncbBfirstletter;

    private String ncbLogopic;

    public Integer getNcbId() {
        return ncbId;
    }

    public void setNcbId(Integer ncbId) {
        this.ncbId = ncbId;
    }

    public Integer getNcbReturnid() {
        return ncbReturnid;
    }

    public void setNcbReturnid(Integer ncbReturnid) {
        this.ncbReturnid = ncbReturnid;
    }

    public String getNcdName() {
        return ncdName;
    }

    public void setNcdName(String ncdName) {
        this.ncdName = ncdName == null ? null : ncdName.trim();
    }

    public String getNcbBfirstletter() {
        return ncbBfirstletter;
    }

    public void setNcbBfirstletter(String ncbBfirstletter) {
        this.ncbBfirstletter = ncbBfirstletter == null ? null : ncbBfirstletter.trim();
    }

    public String getNcbLogopic() {
        return ncbLogopic;
    }

    public void setNcbLogopic(String ncbLogopic) {
        this.ncbLogopic = ncbLogopic == null ? null : ncbLogopic.trim();
    }
}