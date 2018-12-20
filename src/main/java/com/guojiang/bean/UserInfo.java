package com.guojiang.bean;

public class UserInfo {
    private Integer uId;

    private String uOpenid;

    private String uNikename;

    private Integer uGender;

    private String uProvince;

    private String uCity;

    private String uCountry;

    private String uHeadimages;

    private Double uLatitude;

    private Double uLongitude;

    private String uPhonenumbe;

    private Long uRegistertime;

    private Integer uState;

    private Integer uSpeatiallevl;

    private String uRemake;

    public Integer getuId() {
        return uId;
    }

    public void setuId(Integer uId) {
        this.uId = uId;
    }

    public String getuOpenid() {
        return uOpenid;
    }

    public void setuOpenid(String uOpenid) {
        this.uOpenid = uOpenid == null ? null : uOpenid.trim();
    }

    public String getuNikename() {
        return uNikename;
    }

    public void setuNikename(String uNikename) {
        this.uNikename = uNikename == null ? null : uNikename.trim();
    }

    public Integer getuGender() {
        return uGender;
    }

    public void setuGender(Integer uGender) {
        this.uGender = uGender;
    }

    public String getuProvince() {
        return uProvince;
    }

    public void setuProvince(String uProvince) {
        this.uProvince = uProvince == null ? null : uProvince.trim();
    }

    public String getuCity() {
        return uCity;
    }

    public void setuCity(String uCity) {
        this.uCity = uCity == null ? null : uCity.trim();
    }

    public String getuCountry() {
        return uCountry;
    }

    public void setuCountry(String uCountry) {
        this.uCountry = uCountry == null ? null : uCountry.trim();
    }

    public String getuHeadimages() {
        return uHeadimages;
    }

    public void setuHeadimages(String uHeadimages) {
        this.uHeadimages = uHeadimages == null ? null : uHeadimages.trim();
    }

    public Double getuLatitude() {
        return uLatitude;
    }

    public void setuLatitude(Double uLatitude) {
        this.uLatitude = uLatitude;
    }

    public Double getuLongitude() {
        return uLongitude;
    }

    public void setuLongitude(Double uLongitude) {
        this.uLongitude = uLongitude;
    }

    public String getuPhonenumbe() {
        return uPhonenumbe;
    }

    public void setuPhonenumbe(String uPhonenumbe) {
        this.uPhonenumbe = uPhonenumbe == null ? null : uPhonenumbe.trim();
    }

    public Long getuRegistertime() {
        return uRegistertime;
    }

    public void setuRegistertime(Long uRegistertime) {
        this.uRegistertime = uRegistertime;
    }

    public Integer getuState() {
        return uState;
    }

    public void setuState(Integer uState) {
        this.uState = uState;
    }

    public Integer getuSpeatiallevl() {
        return uSpeatiallevl;
    }

    public void setuSpeatiallevl(Integer uSpeatiallevl) {
        this.uSpeatiallevl = uSpeatiallevl;
    }

    public String getuRemake() {
        return uRemake;
    }

    public void setuRemake(String uRemake) {
        this.uRemake = uRemake == null ? null : uRemake.trim();
    }
}