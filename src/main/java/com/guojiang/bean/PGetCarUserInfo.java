package com.guojiang.bean;

public class PGetCarUserInfo {
    private Integer pgcId;

    private String pgcUsername;

    private String pgcPhonenumber;

    private String pgcIdcardnumber;

    private String pgcDriverpic;

    private Integer pgcState;

    private Integer pgcGetcarnum;

    private String pgcOrderjson;

    public Integer getPgcId() {
        return pgcId;
    }

    public void setPgcId(Integer pgcId) {
        this.pgcId = pgcId;
    }

    public String getPgcUsername() {
        return pgcUsername;
    }

    public void setPgcUsername(String pgcUsername) {
        this.pgcUsername = pgcUsername == null ? null : pgcUsername.trim();
    }

    public String getPgcPhonenumber() {
        return pgcPhonenumber;
    }

    public void setPgcPhonenumber(String pgcPhonenumber) {
        this.pgcPhonenumber = pgcPhonenumber == null ? null : pgcPhonenumber.trim();
    }

    public String getPgcIdcardnumber() {
        return pgcIdcardnumber;
    }

    public void setPgcIdcardnumber(String pgcIdcardnumber) {
        this.pgcIdcardnumber = pgcIdcardnumber == null ? null : pgcIdcardnumber.trim();
    }

    public String getPgcDriverpic() {
        return pgcDriverpic;
    }

    public void setPgcDriverpic(String pgcDriverpic) {
        this.pgcDriverpic = pgcDriverpic == null ? null : pgcDriverpic.trim();
    }

    public Integer getPgcState() {
        return pgcState;
    }

    public void setPgcState(Integer pgcState) {
        this.pgcState = pgcState;
    }

    public Integer getPgcGetcarnum() {
        return pgcGetcarnum;
    }

    public void setPgcGetcarnum(Integer pgcGetcarnum) {
        this.pgcGetcarnum = pgcGetcarnum;
    }

    public String getPgcOrderjson() {
        return pgcOrderjson;
    }

    public void setPgcOrderjson(String pgcOrderjson) {
        this.pgcOrderjson = pgcOrderjson == null ? null : pgcOrderjson.trim();
    }
}