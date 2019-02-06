package com.sreeyainfotech.mapexample.model;

import com.google.gson.annotations.SerializedName;

public class Series {

    @SerializedName("Lat")
    private String Lat;

    @SerializedName("Lang")
    private String Lang;

    public String getLat() {
        return Lat;
    }

    public void setLat(String lat) {
        Lat = lat;
    }

    public String getLang() {
        return Lang;
    }

    public void setLang(String lang) {
        Lang = lang;
    }
}
