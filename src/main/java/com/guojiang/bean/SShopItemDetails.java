package com.guojiang.bean;

public class SShopItemDetails {
    private Integer sshdId;

    private Integer sshdShopId;

    private Integer sshdSshId;

    private String sshdDespicone;

    private String sshdUsedes;

    private Long sshdPrice;

    private String sshdSsfcShow;

    private String sshdSsfcJson;

    private Integer sshdState;

    private boolean isToggled;

    public Integer getSshdId() {
        return sshdId;
    }

    public void setSshdId(Integer sshdId) {
        this.sshdId = sshdId;
    }

    public Integer getSshdShopId() {
        return sshdShopId;
    }

    public void setSshdShopId(Integer sshdShopId) {
        this.sshdShopId = sshdShopId;
    }

    public Integer getSshdSshId() {
        return sshdSshId;
    }

    public void setSshdSshId(Integer sshdSshId) {
        this.sshdSshId = sshdSshId;
    }

    public String getSshdDespicone() {
        return sshdDespicone;
    }

    public void setSshdDespicone(String sshdDespicone) {
        this.sshdDespicone = sshdDespicone == null ? null : sshdDespicone.trim();
    }

    public String getSshdUsedes() {
        return sshdUsedes;
    }

    public void setSshdUsedes(String sshdUsedes) {
        this.sshdUsedes = sshdUsedes == null ? null : sshdUsedes.trim();
    }

    public Long getSshdPrice() {
        return sshdPrice;
    }

    public void setSshdPrice(Long sshdPrice) {
        this.sshdPrice = sshdPrice;
    }

    public String getSshdSsfcShow() {
        return sshdSsfcShow;
    }

    public void setSshdSsfcShow(String sshdSsfcShow) {
        this.sshdSsfcShow = sshdSsfcShow == null ? null : sshdSsfcShow.trim();
    }

    public String getSshdSsfcJson() {
        return sshdSsfcJson;
    }

    public void setSshdSsfcJson(String sshdSsfcJson) {
        this.sshdSsfcJson = sshdSsfcJson == null ? null : sshdSsfcJson.trim();
    }

    public Integer getSshdState() {
        return sshdState;
    }

    public void setSshdState(Integer sshdState) {
        this.sshdState = sshdState;
    }

    public boolean isToggled() {
        return isToggled;
    }

    public void setToggled(boolean toggled) {
        isToggled = toggled;
    }
}