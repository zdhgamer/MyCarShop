package com.guojiang.bean;

public class SShopDrawMoney {
    private Integer sdmId;

    private Integer sdmShopId;

    private String sdmDrawaccount;

    private Integer sdmDrawtype;

    private Long sdmDrawnumber;

    private Long sdmDrawcreatetime;

    private Long sdmDrawaggretime;

    private Integer sdmDrawstate;

    public Integer getSdmId() {
        return sdmId;
    }

    public void setSdmId(Integer sdmId) {
        this.sdmId = sdmId;
    }

    public Integer getSdmShopId() {
        return sdmShopId;
    }

    public void setSdmShopId(Integer sdmShopId) {
        this.sdmShopId = sdmShopId;
    }

    public String getSdmDrawaccount() {
        return sdmDrawaccount;
    }

    public void setSdmDrawaccount(String sdmDrawaccount) {
        this.sdmDrawaccount = sdmDrawaccount == null ? null : sdmDrawaccount.trim();
    }

    public Integer getSdmDrawtype() {
        return sdmDrawtype;
    }

    public void setSdmDrawtype(Integer sdmDrawtype) {
        this.sdmDrawtype = sdmDrawtype;
    }

    public Long getSdmDrawnumber() {
        return sdmDrawnumber;
    }

    public void setSdmDrawnumber(Long sdmDrawnumber) {
        this.sdmDrawnumber = sdmDrawnumber;
    }

    public Long getSdmDrawcreatetime() {
        return sdmDrawcreatetime;
    }

    public void setSdmDrawcreatetime(Long sdmDrawcreatetime) {
        this.sdmDrawcreatetime = sdmDrawcreatetime;
    }

    public Long getSdmDrawaggretime() {
        return sdmDrawaggretime;
    }

    public void setSdmDrawaggretime(Long sdmDrawaggretime) {
        this.sdmDrawaggretime = sdmDrawaggretime;
    }

    public Integer getSdmDrawstate() {
        return sdmDrawstate;
    }

    public void setSdmDrawstate(Integer sdmDrawstate) {
        this.sdmDrawstate = sdmDrawstate;
    }
}