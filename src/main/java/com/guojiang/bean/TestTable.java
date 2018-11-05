package com.guojiang.bean;

public class TestTable {
    private Integer tId;

    private String tDd;

    public Integer gettId() {
        return tId;
    }

    public void settId(Integer tId) {
        this.tId = tId;
    }

    public String gettDd() {
        return tDd;
    }

    public void settDd(String tDd) {
        this.tDd = tDd == null ? null : tDd.trim();
    }
}