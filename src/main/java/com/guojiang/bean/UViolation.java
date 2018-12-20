package com.guojiang.bean;

public class UViolation {
    private Integer vId;

    private String vProvience;

    private Integer vDtype;

    private Integer vFormat;

    private String vCallback;

    private String vKey;

    public Integer getvId() {
        return vId;
    }

    public void setvId(Integer vId) {
        this.vId = vId;
    }

    public String getvProvience() {
        return vProvience;
    }

    public void setvProvience(String vProvience) {
        this.vProvience = vProvience == null ? null : vProvience.trim();
    }

    public Integer getvDtype() {
        return vDtype;
    }

    public void setvDtype(Integer vDtype) {
        this.vDtype = vDtype;
    }

    public Integer getvFormat() {
        return vFormat;
    }

    public void setvFormat(Integer vFormat) {
        this.vFormat = vFormat;
    }

    public String getvCallback() {
        return vCallback;
    }

    public void setvCallback(String vCallback) {
        this.vCallback = vCallback == null ? null : vCallback.trim();
    }

    public String getvKey() {
        return vKey;
    }

    public void setvKey(String vKey) {
        this.vKey = vKey == null ? null : vKey.trim();
    }
}