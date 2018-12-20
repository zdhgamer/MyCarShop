package com.guojiang.bean;

import java.util.List;

public class UServiceProject {
    private Integer uspId;

    private Integer uspServiceConfigId;

    private Integer uspShopServiceId;

    private Integer uspPlathServiceId;

    private String uspDes;

    private String uspRemake;

    private Integer uspUId;

    private Integer uspOId;

    private Long uspHowmuch;

    private PServiceProjectConfig totalServiceConfig;

    private SShopTotalService shopTotalService;
    /**
     * 保养服务
     */
    private List<SShopUpkeepSubService> upkeepSubService;

    /**
     * 美容服务
     */
    private List<SShopCosmetologyDetail> cosmetologyDetail;

    /**
     * 维修服务
     */
    private List<SShopMaintainService> maintainService;

    public Integer getUspId() {
        return uspId;
    }

    public void setUspId(Integer uspId) {
        this.uspId = uspId;
    }

    public Integer getUspServiceConfigId() {
        return uspServiceConfigId;
    }

    public void setUspServiceConfigId(Integer uspServiceConfigId) {
        this.uspServiceConfigId = uspServiceConfigId;
    }

    public Integer getUspShopServiceId() {
        return uspShopServiceId;
    }

    public void setUspShopServiceId(Integer uspShopServiceId) {
        this.uspShopServiceId = uspShopServiceId;
    }

    public Integer getUspPlathServiceId() {
        return uspPlathServiceId;
    }

    public void setUspPlathServiceId(Integer uspPlathServiceId) {
        this.uspPlathServiceId = uspPlathServiceId;
    }

    public String getUspDes() {
        return uspDes;
    }

    public void setUspDes(String uspDes) {
        this.uspDes = uspDes == null ? null : uspDes.trim();
    }

    public String getUspRemake() {
        return uspRemake;
    }

    public void setUspRemake(String uspRemake) {
        this.uspRemake = uspRemake == null ? null : uspRemake.trim();
    }

    public Integer getUspUId() {
        return uspUId;
    }

    public void setUspUId(Integer uspUId) {
        this.uspUId = uspUId;
    }

    public Integer getUspOId() {
        return uspOId;
    }

    public void setUspOId(Integer uspOId) {
        this.uspOId = uspOId;
    }

    public Long getUspHowmuch() {
        return uspHowmuch;
    }

    public void setUspHowmuch(Long uspHowmuch) {
        this.uspHowmuch = uspHowmuch;
    }

    public SShopTotalService getShopTotalService() {
        return shopTotalService;
    }

    public void setShopTotalService(SShopTotalService shopTotalService) {
        this.shopTotalService = shopTotalService;
    }

    public PServiceProjectConfig getTotalServiceConfig() {
        return totalServiceConfig;
    }

    public void setTotalServiceConfig(PServiceProjectConfig totalServiceConfig) {
        this.totalServiceConfig = totalServiceConfig;
    }

    public List<SShopMaintainService> getMaintainService() {
        return maintainService;
    }

    public void setMaintainService(List<SShopMaintainService> maintainService) {
        this.maintainService = maintainService;
    }

    public List<SShopCosmetologyDetail> getCosmetologyDetail() {
        return cosmetologyDetail;
    }

    public void setCosmetologyDetail(List<SShopCosmetologyDetail> cosmetologyDetail) {
        this.cosmetologyDetail = cosmetologyDetail;
    }

    public List<SShopUpkeepSubService> getUpkeepSubService() {
        return upkeepSubService;
    }

    public void setUpkeepSubService(List<SShopUpkeepSubService> upkeepSubService) {
        this.upkeepSubService = upkeepSubService;
    }
}