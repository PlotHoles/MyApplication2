package com.sparecode.yaaroz.fragments;

import android.content.Context;

import com.google.gson.Gson;
import com.sparecode.yaaroz.interfaces.OnResponse;
import com.sparecode.yaaroz.model.User;
import com.sparecode.yaaroz.utils.DebugLog;
import com.sparecode.yaaroz.webservice.GetRequest;
import com.sparecode.yaaroz.webservice.ReqestParameter;
import com.sparecode.yaaroz.webservice.RequestApi;

/**
 * Created by Sanket on 3/27/2017.
 */

class UserBackend {
    private Context mContext;
    private UserProvider userProvider;

    UserBackend(Context mContext, UserProvider userProvider) {
        this.mContext = mContext;
        this.userProvider = userProvider;
    }

    void callSignup(String email, String image, String lat, String lng, String fbid, String deviceId) {
        new GetRequest<User>().toGetRequest(mContext, RequestApi.BASEURL, new ReqestParameter().toLogin(email, image, lat, lng, fbid, "A", deviceId), User.class, new OnResponse<User>() {
            @Override
            public void onSuccess(User user) {
                if (user.getStatus() == 0) {
                    userProvider.onFailure(user.getMessage());
                } else {
                    userProvider.onUserUpdate(user);
                }
            }

            @Override
            public void onError() {
                userProvider.onFailure("Please try again !!");
            }
        });

    }

    void testResponse() {
        String str = "{ \"status\": 1, \"message\": \"Login Successful\", \"time\": \"04-04-2017 06:50:02\", \"data\": [ { \"id\": \"5\", \"fb_id\": \"109814332867215\", \"fname\": \"\", \"lname\": \"\", \"email\": \"sparecodecredential@gmail.com\", \"image\": \"sdf\", \"gender\": \"\", \"birthday\": \"\", \"nCityId\": \"2\", \"smoking\": \"0\", \"pets\": \"0\", \"school\": \"\", \"profession\": \"\", \"aboutme\": \"\", \"question\": \"\", \"age\": \"\" } ] }";
        Gson gson = new Gson();
        gson.fromJson(str, UserBackend.class);
        DebugLog.e("JSON OBJ::");
    }

    void callUpdateProfile(String fname, String lname, String profession, String school, String age, String smoking, String pets, String about, String userid, String questions) {
        new GetRequest<User>().toGetRequest(mContext, RequestApi.BASEURL, new ReqestParameter().toUpadateProfile(fname, lname, profession, school, age, smoking, pets, about, userid, String.valueOf(questions)), User.class, new OnResponse<User>() {
            @Override
            public void onSuccess(User user) {
                if (user.getStatus() == 0) {
                    userProvider.onFailure(user.getMessage());
                } else {
                    userProvider.onUserUpdate(user);
                }
            }

            @Override
            public void onError() {
                userProvider.onFailure("Please try again !!");
            }
        });
    }

 /*   @Override
    public User deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        if (jsonObject.get("questions").isJsonArray()) {
            jsonObject.remove("questions");
        }
        return new Gson().fromJson(jsonObject, User.class);
    }*/

    interface UserProvider {
        void onUserUpdate(User user);

        void onFailure(String msg);
    }
}
