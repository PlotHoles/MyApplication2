package com.sparecode.yaaroz.application;

import android.app.Application;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.sparecode.yaaroz.dialog.SweetAlertDialog;
import com.sparecode.yaaroz.utils.Prefs;
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
        Prefs.initPrefs(getApplicationContext());
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread thread, Throwable e) {
                handleUncaughtException(thread, e);
            }
        });
    }

    public void handleUncaughtException(Thread thread, Throwable e) {
        new SweetAlertDialog(getApplicationContext()).setTitleText("OOOPSS SOMETHING FUCKED UP !! Please press ok to restart app.").show();
        /*
        e.printStackTrace(); // not all Android versions will print the stack trace automatically

        Intent intent = new Intent ();
        intent.setAction ("com.mydomain.SEND_LOG"); // see step 5.
        intent.setFlags (Intent.FLAG_ACTIVITY_NEW_TASK); // required when starting from Application
        startActivity (intent);

        System.exit(1); // kill off the crashed app*/
    }

    public static RequestBuilder getRequestBuilder() {
        return requestBuilder;
    }
}
