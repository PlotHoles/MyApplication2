package com.sparecode.yaaroz.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Sanket on 3/30/2017.
 */

public class UserCitySelectionData {
    @SerializedName("nCityId")
    @Expose
    private String nCityId;

    public String getNCityId() {
        return nCityId;
    }

    public void setNCityId(String nCityId) {
        this.nCityId = nCityId;
    }
}
