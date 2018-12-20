package com.guojiang.bean;

public class UUSerevaluate {
    private Integer ulId;

    private Integer ulUid;

    private Integer ulOId;

    private Integer ulServicelevl;

    private Integer ulSid;

    private Integer ulWorkerlevel;

    private String ulDes;

    private Long ulCreatetime;

    private String ulRemake;

    public Integer getUlId() {
        return ulId;
    }

    public void setUlId(Integer ulId) {
        this.ulId = ulId;
    }

    public Integer getUlUid() {
        return ulUid;
    }

    public void setUlUid(Integer ulUid) {
        this.ulUid = ulUid;
    }

    public Integer getUlOId() {
        return ulOId;
    }

    public void setUlOId(Integer ulOId) {
        this.ulOId = ulOId;
    }

    public Integer getUlServicelevl() {
        return ulServicelevl;
    }

    public void setUlServicelevl(Integer ulServicelevl) {
        this.ulServicelevl = ulServicelevl;
    }

    public Integer getUlSid() {
        return ulSid;
    }

    public void setUlSid(Integer ulSid) {
        this.ulSid = ulSid;
    }

    public Integer getUlWorkerlevel() {
        return ulWorkerlevel;
    }

    public void setUlWorkerlevel(Integer ulWorkerlevel) {
        this.ulWorkerlevel = ulWorkerlevel;
    }

    public String getUlDes() {
        return ulDes;
    }

    public void setUlDes(String ulDes) {
        this.ulDes = ulDes == null ? null : ulDes.trim();
    }

    public Long getUlCreatetime() {
        return ulCreatetime;
    }

    public void setUlCreatetime(Long ulCreatetime) {
        this.ulCreatetime = ulCreatetime;
    }

    public String getUlRemake() {
        return ulRemake;
    }

    public void setUlRemake(String ulRemake) {
        this.ulRemake = ulRemake == null ? null : ulRemake.trim();
    }
}