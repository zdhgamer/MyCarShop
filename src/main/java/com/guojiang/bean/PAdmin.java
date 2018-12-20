package com.guojiang.bean;

public class PAdmin {
    private Integer paId;

    private String paAcount;

    private String paPwd;

    private Integer paJurisdiction;

    public Integer getPaId() {
        return paId;
    }

    public void setPaId(Integer paId) {
        this.paId = paId;
    }

    public String getPaAcount() {
        return paAcount;
    }

    public void setPaAcount(String paAcount) {
        this.paAcount = paAcount == null ? null : paAcount.trim();
    }

    public String getPaPwd() {
        return paPwd;
    }

    public void setPaPwd(String paPwd) {
        this.paPwd = paPwd == null ? null : paPwd.trim();
    }

    public Integer getPaJurisdiction() {
        return paJurisdiction;
    }

    public void setPaJurisdiction(Integer paJurisdiction) {
        this.paJurisdiction = paJurisdiction;
    }
}