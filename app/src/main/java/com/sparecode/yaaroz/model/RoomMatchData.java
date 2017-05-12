package com.sparecode.yaaroz.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Sanket on 4/26/2017.
 */

public class RoomMatchData  {
    @SerializedName("nRoomId")
    @Expose
    private String nRoomId;
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
    @SerializedName("age")
    @Expose
    private String age;
    @SerializedName("image")
    @Expose
    private String image;

    public String getNRoomId() {
        return nRoomId;
    }

    public void setNRoomId(String nRoomId) {
        this.nRoomId = nRoomId;
    }

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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
