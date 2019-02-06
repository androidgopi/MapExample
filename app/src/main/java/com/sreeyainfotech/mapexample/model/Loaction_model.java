package com.sreeyainfotech.mapexample.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Loaction_model {

    @SerializedName("locations")
    private List<Location_sub> locations=new ArrayList<>();

    public List<Location_sub> getLocations() {
        return locations;
    }

    public void setLocations(List<Location_sub> locations) {
        this.locations = locations;
    }
}
