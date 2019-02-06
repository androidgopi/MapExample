package com.sreeyainfotech.mapexample.model;

import com.google.gson.annotations.SerializedName;

public class Location_sub {

    @SerializedName("Id")
    private String Id;

    @SerializedName("Name")
    private String Name;

    @SerializedName("Series")
    private Series series=new Series();

    public Series getSeries() {
        return series;
    }

    public void setSeries(Series series) {
        this.series = series;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
