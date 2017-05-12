package com.sparecode.yaaroz.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Sanket on 4/29/2017.
 */

public class RoomMatchFilterData {
    @SerializedName("nCityId")
    @Expose
    private String nCityId;
    @SerializedName("nLocationId")
    @Expose
    private String nLocationId;
    @SerializedName("nUserId")
    @Expose
    private String nUserId;
    @SerializedName("sDesc")
    @Expose
    private String sDesc;
    @SerializedName("sRent")
    @Expose
    private String sRent;
    @SerializedName("sPhoto")
    @Expose
    private String sPhoto;

    public String getNCityId() {
        return nCityId;
    }

    public void setNCityId(String nCityId) {
        this.nCityId = nCityId;
    }

    public String getNLocationId() {
        return nLocationId;
    }

    public void setNLocationId(String nLocationId) {
        this.nLocationId = nLocationId;
    }

    public String getNUserId() {
        return nUserId;
    }

    public void setNUserId(String nUserId) {
        this.nUserId = nUserId;
    }

    public String getSDesc() {
        return sDesc;
    }

    public void setSDesc(String sDesc) {
        this.sDesc = sDesc;
    }

    public String getSRent() {
        return sRent;
    }

    public void setSRent(String sRent) {
        this.sRent = sRent;
    }

    public String getSPhoto() {
        return sPhoto;
    }

    public void setSPhoto(String sPhoto) {
        this.sPhoto = sPhoto;
    }
}
