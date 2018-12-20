package com.guojiang.bean;

import java.util.List;

public class SShopUpkeepSubService {
    private Integer ukssId;

    private Integer ukssShopId;

    private Integer ukssStsId;

    private String ukssUkname;

    private Integer ukssState;

    private Long ukssCreatetime;

    private Long ukssChecktime;

    private Integer ukssCheckstate;

    private String ukssCheckdes;

    private Long ukssMoney;

    private List<SShopUpkeepDetailsItem> itemDataList;

    public Integer getUkssId() {
        return ukssId;
    }

    public void setUkssId(Integer ukssId) {
        this.ukssId = ukssId;
    }

    public Integer getUkssShopId() {
        return ukssShopId;
    }

    public void setUkssShopId(Integer ukssShopId) {
        this.ukssShopId = ukssShopId;
    }

    public Integer getUkssStsId() {
        return ukssStsId;
    }

    public void setUkssStsId(Integer ukssStsId) {
        this.ukssStsId = ukssStsId;
    }

    public String getUkssUkname() {
        return ukssUkname;
    }

    public void setUkssUkname(String ukssUkname) {
        this.ukssUkname = ukssUkname == null ? null : ukssUkname.trim();
    }

    public Integer getUkssState() {
        return ukssState;
    }

    public void setUkssState(Integer ukssState) {
        this.ukssState = ukssState;
    }

    public Long getUkssCreatetime() {
        return ukssCreatetime;
    }

    public void setUkssCreatetime(Long ukssCreatetime) {
        this.ukssCreatetime = ukssCreatetime;
    }

    public Long getUkssChecktime() {
        return ukssChecktime;
    }

    public void setUkssChecktime(Long ukssChecktime) {
        this.ukssChecktime = ukssChecktime;
    }

    public Integer getUkssCheckstate() {
        return ukssCheckstate;
    }

    public void setUkssCheckstate(Integer ukssCheckstate) {
        this.ukssCheckstate = ukssCheckstate;
    }

    public String getUkssCheckdes() {
        return ukssCheckdes;
    }

    public void setUkssCheckdes(String ukssCheckdes) {
        this.ukssCheckdes = ukssCheckdes == null ? null : ukssCheckdes.trim();
    }

    public Long getUkssMoney() {
        return ukssMoney;
    }

    public void setUkssMoney(Long ukssMoney) {
        this.ukssMoney = ukssMoney;
    }

    public List<SShopUpkeepDetailsItem> getItemDataList() {
        return itemDataList;
    }

    public void setItemDataList(List<SShopUpkeepDetailsItem> itemDataList) {
        this.itemDataList = itemDataList;
    }
}