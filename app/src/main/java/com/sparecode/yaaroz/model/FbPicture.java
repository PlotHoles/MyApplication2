package com.sparecode.yaaroz.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Sanket on 3/27/2017.
 */

public class FbPicture {
    @SerializedName("data")
    @Expose
    private FbData data;

    public FbData getData() {
        return data;
    }

    public void setData(FbData data) {
        this.data = data;
    }
}
