package com.sparecode.yaaroz.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Sanket on 3/28/2017.
 */

public class CityData {

    @SerializedName("nCityId")
    @Expose
    private String nCityId;
    @SerializedName("sCityName")
    @Expose
    private String sCityName;

    public String getNCityId() {
        return nCityId;
    }

    public void setNCityId(String nCityId) {
        this.nCityId = nCityId;
    }

    public String getSCityName() {
        return sCityName;
    }

    public void setSCityName(String sCityName) {
        this.sCityName = sCityName;
    }

}
