package com.guojiang.bean;

public class PBanner {
    private Integer pbId;

    private String pbImages;

    private Integer pbIndex;

    private String pbWebsite;

    public Integer getPbId() {
        return pbId;
    }

    public void setPbId(Integer pbId) {
        this.pbId = pbId;
    }

    public String getPbImages() {
        return pbImages;
    }

    public void setPbImages(String pbImages) {
        this.pbImages = pbImages == null ? null : pbImages.trim();
    }

    public Integer getPbIndex() {
        return pbIndex;
    }

    public void setPbIndex(Integer pbIndex) {
        this.pbIndex = pbIndex;
    }

    public String getPbWebsite() {
        return pbWebsite;
    }

    public void setPbWebsite(String pbWebsite) {
        this.pbWebsite = pbWebsite == null ? null : pbWebsite.trim();
    }
}