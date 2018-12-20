package com.guojiang.bean;

public class SShopEvaluation {
    private Integer svId;

    private Integer svOderId;

    private Integer svUserId;

    private Integer svStar;

    private String svDes;

    public Integer getSvId() {
        return svId;
    }

    public void setSvId(Integer svId) {
        this.svId = svId;
    }

    public Integer getSvOderId() {
        return svOderId;
    }

    public void setSvOderId(Integer svOderId) {
        this.svOderId = svOderId;
    }

    public Integer getSvUserId() {
        return svUserId;
    }

    public void setSvUserId(Integer svUserId) {
        this.svUserId = svUserId;
    }

    public Integer getSvStar() {
        return svStar;
    }

    public void setSvStar(Integer svStar) {
        this.svStar = svStar;
    }

    public String getSvDes() {
        return svDes;
    }

    public void setSvDes(String svDes) {
        this.svDes = svDes == null ? null : svDes.trim();
    }
}