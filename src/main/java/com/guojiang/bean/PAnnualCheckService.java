package com.guojiang.bean;

public class PAnnualCheckService {
    private Integer pacId;

    private Integer pacConfigId;

    private Long pacOrdermoney;

    private Integer pacState;

    private String pacCarDes;

    public Integer getPacId() {
        return pacId;
    }

    public void setPacId(Integer pacId) {
        this.pacId = pacId;
    }

    public Integer getPacConfigId() {
        return pacConfigId;
    }

    public void setPacConfigId(Integer pacConfigId) {
        this.pacConfigId = pacConfigId;
    }

    public Long getPacOrdermoney() {
        return pacOrdermoney;
    }

    public void setPacOrdermoney(Long pacOrdermoney) {
        this.pacOrdermoney = pacOrdermoney;
    }

    public Integer getPacState() {
        return pacState;
    }

    public void setPacState(Integer pacState) {
        this.pacState = pacState;
    }

    public String getPacCarDes() {
        return pacCarDes;
    }

    public void setPacCarDes(String pacCarDes) {
        this.pacCarDes = pacCarDes == null ? null : pacCarDes.trim();
    }
}