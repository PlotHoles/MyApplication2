package com.sparecode.yaaroz.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Sanket on 5/9/2017.
 */

public class AddroomData {

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
    @SerializedName("sAddress")
    @Expose
    private String sAddress;
    @SerializedName("sLat")
    @Expose
    private String sLat;
    @SerializedName("sLong")
    @Expose
    private String sLong;
    @SerializedName("sPhoto")
    @Expose
    private String sPhoto;
    @SerializedName("sDesc")
    @Expose
    private String sDesc;
    @SerializedName("sRent")
    @Expose
    private String sRent;
    @SerializedName("dAvailableDate")
    @Expose
    private String dAvailableDate;
    @SerializedName("sRoommates")
    @Expose
    private String sRoommates;
    @SerializedName("sGender")
    @Expose
    private String sGender;
    @SerializedName("sLength")
    @Expose
    private String sLength;
    @SerializedName("sFurnish")
    @Expose
    private String sFurnish;
    @SerializedName("sBathroom")
    @Expose
    private String sBathroom;
    @SerializedName("sSmoking")
    @Expose
    private String sSmoking;
    @SerializedName("sPets")
    @Expose
    private String sPets;
    @SerializedName("sLookingForGender")
    @Expose
    private String sLookingForGender;
    @SerializedName("sLookingForAge")
    @Expose
    private String sLookingForAge;
    @SerializedName("sLookingForAgeMax")
    @Expose
    private String sLookingForAgeMax;
    @SerializedName("sStatus")
    @Expose
    private String sStatus;
    @SerializedName("dDate")
    @Expose
    private String dDate;

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

    public String getSAddress() {
        return sAddress;
    }

    public void setSAddress(String sAddress) {
        this.sAddress = sAddress;
    }

    public String getSLat() {
        return sLat;
    }

    public void setSLat(String sLat) {
        this.sLat = sLat;
    }

    public String getSLong() {
        return sLong;
    }

    public void setSLong(String sLong) {
        this.sLong = sLong;
    }

    public String getSPhoto() {
        return sPhoto;
    }

    public void setSPhoto(String sPhoto) {
        this.sPhoto = sPhoto;
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

    public String getDAvailableDate() {
        return dAvailableDate;
    }

    public void setDAvailableDate(String dAvailableDate) {
        this.dAvailableDate = dAvailableDate;
    }

    public String getSRoommates() {
        return sRoommates;
    }

    public void setSRoommates(String sRoommates) {
        this.sRoommates = sRoommates;
    }

    public String getSGender() {
        return sGender;
    }

    public void setSGender(String sGender) {
        this.sGender = sGender;
    }

    public String getSLength() {
        return sLength;
    }

    public void setSLength(String sLength) {
        this.sLength = sLength;
    }

    public String getSFurnish() {
        return sFurnish;
    }

    public void setSFurnish(String sFurnish) {
        this.sFurnish = sFurnish;
    }

    public String getSBathroom() {
        return sBathroom;
    }

    public void setSBathroom(String sBathroom) {
        this.sBathroom = sBathroom;
    }

    public String getSSmoking() {
        return sSmoking;
    }

    public void setSSmoking(String sSmoking) {
        this.sSmoking = sSmoking;
    }

    public String getSPets() {
        return sPets;
    }

    public void setSPets(String sPets) {
        this.sPets = sPets;
    }

    public String getSLookingForGender() {
        return sLookingForGender;
    }

    public void setSLookingForGender(String sLookingForGender) {
        this.sLookingForGender = sLookingForGender;
    }

    public String getSLookingForAge() {
        return sLookingForAge;
    }

    public void setSLookingForAge(String sLookingForAge) {
        this.sLookingForAge = sLookingForAge;
    }

    public String getSLookingForAgeMax() {
        return sLookingForAgeMax;
    }

    public void setSLookingForAgeMax(String sLookingForAgeMax) {
        this.sLookingForAgeMax = sLookingForAgeMax;
    }

    public String getSStatus() {
        return sStatus;
    }

    public void setSStatus(String sStatus) {
        this.sStatus = sStatus;
    }

    public String getDDate() {
        return dDate;
    }

    public void setDDate(String dDate) {
        this.dDate = dDate;
    }
}
