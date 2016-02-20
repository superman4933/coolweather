package com.superman.coolweather.model;

/**
 * Created by Administrator on 2016/2/19.
 */
public class County  {
    private int id;
    private String countyName;
    private String countyCode;
    private int cityId;

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getCountCode() {
        return countyCode;
    }

    public void setCountyCode(String countCode) {
        this.countyCode = countCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }




}
