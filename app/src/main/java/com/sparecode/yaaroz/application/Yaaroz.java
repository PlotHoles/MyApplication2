package com.sparecode.yaaroz.application;

import android.app.Application;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.sparecode.yaaroz.webservice.RequestBuilder;

/**
 * Created by Sanket on 1/28/2017.
 */

public class Yaaroz extends Application {
    private static RequestBuilder requestBuilder;

    @Override
    public void onCreate() {
        super.onCreate();
        requestBuilder = new RequestBuilder();
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
    }

    public static RequestBuilder getRequestBuilder() {
        return requestBuilder;
    }
}
