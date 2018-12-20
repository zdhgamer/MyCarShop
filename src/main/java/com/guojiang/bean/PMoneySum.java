package com.guojiang.bean;

public class PMoneySum {
    private Integer pmId;

    private Long pmOrdertotal;

    private Long pmGettotalmoney;

    private Long pmWithdrawtotalmoney;

    private String pmRemake;

    public Integer getPmId() {
        return pmId;
    }

    public void setPmId(Integer pmId) {
        this.pmId = pmId;
    }

    public Long getPmOrdertotal() {
        return pmOrdertotal;
    }

    public void setPmOrdertotal(Long pmOrdertotal) {
        this.pmOrdertotal = pmOrdertotal;
    }

    public Long getPmGettotalmoney() {
        return pmGettotalmoney;
    }

    public void setPmGettotalmoney(Long pmGettotalmoney) {
        this.pmGettotalmoney = pmGettotalmoney;
    }

    public Long getPmWithdrawtotalmoney() {
        return pmWithdrawtotalmoney;
    }

    public void setPmWithdrawtotalmoney(Long pmWithdrawtotalmoney) {
        this.pmWithdrawtotalmoney = pmWithdrawtotalmoney;
    }

    public String getPmRemake() {
        return pmRemake;
    }

    public void setPmRemake(String pmRemake) {
        this.pmRemake = pmRemake == null ? null : pmRemake.trim();
    }
}