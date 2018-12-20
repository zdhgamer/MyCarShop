package com.guojiang.bean;

public class USubService {
    private Integer ussId;

    private Integer ussUspId;

    private Integer ussUId;

    private Integer ussOId;

    private Integer ussSubserviceConfigId;

    private Integer ussShopSubserviceId;

    private String ussDes;

    private Long ussHowmuch;

    private String ussRemake;

    private UServiceProject serviceProject;

    public Integer getUssId() {
        return ussId;
    }

    public void setUssId(Integer ussId) {
        this.ussId = ussId;
    }

    public Integer getUssUspId() {
        return ussUspId;
    }

    public void setUssUspId(Integer ussUspId) {
        this.ussUspId = ussUspId;
    }

    public Integer getUssUId() {
        return ussUId;
    }

    public void setUssUId(Integer ussUId) {
        this.ussUId = ussUId;
    }

    public Integer getUssOId() {
        return ussOId;
    }

    public void setUssOId(Integer ussOId) {
        this.ussOId = ussOId;
    }

    public Integer getUssSubserviceConfigId() {
        return ussSubserviceConfigId;
    }

    public void setUssSubserviceConfigId(Integer ussSubserviceConfigId) {
        this.ussSubserviceConfigId = ussSubserviceConfigId;
    }

    public Integer getUssShopSubserviceId() {
        return ussShopSubserviceId;
    }

    public void setUssShopSubserviceId(Integer ussShopSubserviceId) {
        this.ussShopSubserviceId = ussShopSubserviceId;
    }

    public String getUssDes() {
        return ussDes;
    }

    public void setUssDes(String ussDes) {
        this.ussDes = ussDes == null ? null : ussDes.trim();
    }

    public Long getUssHowmuch() {
        return ussHowmuch;
    }

    public void setUssHowmuch(Long ussHowmuch) {
        this.ussHowmuch = ussHowmuch;
    }

    public String getUssRemake() {
        return ussRemake;
    }

    public void setUssRemake(String ussRemake) {
        this.ussRemake = ussRemake == null ? null : ussRemake.trim();
    }

    public UServiceProject getServiceProject() {
        return serviceProject;
    }

    public void setServiceProject(UServiceProject serviceProject) {
        this.serviceProject = serviceProject;
    }
}