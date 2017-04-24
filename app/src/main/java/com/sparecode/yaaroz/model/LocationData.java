package com.sparecode.yaaroz.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Sanket on 4/10/2017.
 */

public class LocationData {
    @SerializedName("nLocationId")
    @Expose
    private String nLocationId;
    @SerializedName("nCityId")
    @Expose
    private String nCityId;
    @SerializedName("sLocationName")
    @Expose
    private String sLocationName;

    public String getNLocationId() {
        return nLocationId;
    }

    public void setNLocationId(String nLocationId) {
        this.nLocationId = nLocationId;
    }

    public String getNCityId() {
        return nCityId;
    }

    public void setNCityId(String nCityId) {
        this.nCityId = nCityId;
    }

    public String getSLocationName() {
        return sLocationName;
    }

    public void setSLocationName(String sLocationName) {
        this.sLocationName = sLocationName;
    }
}
