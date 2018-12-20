package com.guojiang.bean;

public class UInvoiceTable {
    private Integer uiId;

    private Integer uiOderId;

    private Integer uiType;

    private String uiTypecontent;

    private String uiDutyparagraph;

    private String uiInvoicetitle;

    private String uiInvoicecontent;

    private Long uiMoney;

    private String uiMoredes;

    private String uiRemake;

    private String uiAddress;

    private String uiPhonenumber;

    private String uiOpeningbank;

    private String uiBankacount;

    public Integer getUiId() {
        return uiId;
    }

    public void setUiId(Integer uiId) {
        this.uiId = uiId;
    }

    public Integer getUiOderId() {
        return uiOderId;
    }

    public void setUiOderId(Integer uiOderId) {
        this.uiOderId = uiOderId;
    }

    public Integer getUiType() {
        return uiType;
    }

    public void setUiType(Integer uiType) {
        this.uiType = uiType;
    }

    public String getUiTypecontent() {
        return uiTypecontent;
    }

    public void setUiTypecontent(String uiTypecontent) {
        this.uiTypecontent = uiTypecontent == null ? null : uiTypecontent.trim();
    }

    public String getUiDutyparagraph() {
        return uiDutyparagraph;
    }

    public void setUiDutyparagraph(String uiDutyparagraph) {
        this.uiDutyparagraph = uiDutyparagraph == null ? null : uiDutyparagraph.trim();
    }

    public String getUiInvoicetitle() {
        return uiInvoicetitle;
    }

    public void setUiInvoicetitle(String uiInvoicetitle) {
        this.uiInvoicetitle = uiInvoicetitle == null ? null : uiInvoicetitle.trim();
    }

    public String getUiInvoicecontent() {
        return uiInvoicecontent;
    }

    public void setUiInvoicecontent(String uiInvoicecontent) {
        this.uiInvoicecontent = uiInvoicecontent == null ? null : uiInvoicecontent.trim();
    }

    public Long getUiMoney() {
        return uiMoney;
    }

    public void setUiMoney(Long uiMoney) {
        this.uiMoney = uiMoney;
    }

    public String getUiMoredes() {
        return uiMoredes;
    }

    public void setUiMoredes(String uiMoredes) {
        this.uiMoredes = uiMoredes == null ? null : uiMoredes.trim();
    }

    public String getUiRemake() {
        return uiRemake;
    }

    public void setUiRemake(String uiRemake) {
        this.uiRemake = uiRemake == null ? null : uiRemake.trim();
    }

    public String getUiAddress() {
        return uiAddress;
    }

    public void setUiAddress(String uiAddress) {
        this.uiAddress = uiAddress == null ? null : uiAddress.trim();
    }

    public String getUiPhonenumber() {
        return uiPhonenumber;
    }

    public void setUiPhonenumber(String uiPhonenumber) {
        this.uiPhonenumber = uiPhonenumber == null ? null : uiPhonenumber.trim();
    }

    public String getUiOpeningbank() {
        return uiOpeningbank;
    }

    public void setUiOpeningbank(String uiOpeningbank) {
        this.uiOpeningbank = uiOpeningbank == null ? null : uiOpeningbank.trim();
    }

    public String getUiBankacount() {
        return uiBankacount;
    }

    public void setUiBankacount(String uiBankacount) {
        this.uiBankacount = uiBankacount == null ? null : uiBankacount.trim();
    }
}