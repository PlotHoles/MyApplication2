package com.sparecode.yaaroz.fragments;

import android.content.Context;

import com.sparecode.yaaroz.interfaces.OnResponse;
import com.sparecode.yaaroz.model.User;
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

    interface UserProvider {
        void onUserUpdate(User user);

        void onFailure(String msg);
    }
}
