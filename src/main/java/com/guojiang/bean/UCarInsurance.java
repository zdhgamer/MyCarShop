package com.guojiang.bean;

public class UCarInsurance {
    private Integer ciId;

    private Integer ciUid;

    private String ciCompany;

    private String ciExpirationtime;

    private String ciHostrealname;

    private String ciIdcar;

    private String ciCity;

    private String ciRemake;

    public Integer getCiId() {
        return ciId;
    }

    public void setCiId(Integer ciId) {
        this.ciId = ciId;
    }

    public Integer getCiUid() {
        return ciUid;
    }

    public void setCiUid(Integer ciUid) {
        this.ciUid = ciUid;
    }

    public String getCiCompany() {
        return ciCompany;
    }

    public void setCiCompany(String ciCompany) {
        this.ciCompany = ciCompany == null ? null : ciCompany.trim();
    }

    public String getCiExpirationtime() {
        return ciExpirationtime;
    }

    public void setCiExpirationtime(String ciExpirationtime) {
        this.ciExpirationtime = ciExpirationtime == null ? null : ciExpirationtime.trim();
    }

    public String getCiHostrealname() {
        return ciHostrealname;
    }

    public void setCiHostrealname(String ciHostrealname) {
        this.ciHostrealname = ciHostrealname == null ? null : ciHostrealname.trim();
    }

    public String getCiIdcar() {
        return ciIdcar;
    }

    public void setCiIdcar(String ciIdcar) {
        this.ciIdcar = ciIdcar == null ? null : ciIdcar.trim();
    }

    public String getCiCity() {
        return ciCity;
    }

    public void setCiCity(String ciCity) {
        this.ciCity = ciCity == null ? null : ciCity.trim();
    }

    public String getCiRemake() {
        return ciRemake;
    }

    public void setCiRemake(String ciRemake) {
        this.ciRemake = ciRemake == null ? null : ciRemake.trim();
    }
}