package com.guojiang.bean;

public class SShopItemData {
    private Integer sshId;

    private Integer sshShopId;

    /**
     * 配置信息的id
     */
    private Integer sshPshcId;

    private Integer sshState;

    /**
     * 所属的配置信息
     */
    private PShopItemConfig pShopItemConfig;

    public Integer getSshId() {
        return sshId;
    }

    public void setSshId(Integer sshId) {
        this.sshId = sshId;
    }

    public Integer getSshShopId() {
        return sshShopId;
    }

    public void setSshShopId(Integer sshShopId) {
        this.sshShopId = sshShopId;
    }

    public Integer getSshPshcId() {
        return sshPshcId;
    }

    public void setSshPshcId(Integer sshPshcId) {
        this.sshPshcId = sshPshcId;
    }

    public Integer getSshState() {
        return sshState;
    }

    public void setSshState(Integer sshState) {
        this.sshState = sshState;
    }

    public PShopItemConfig getpShopItemConfig() {
        return pShopItemConfig;
    }

    public void setpShopItemConfig(PShopItemConfig pShopItemConfig) {
        this.pShopItemConfig = pShopItemConfig;
    }
}