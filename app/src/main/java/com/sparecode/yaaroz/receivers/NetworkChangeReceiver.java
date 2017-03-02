package com.sparecode.yaaroz.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.sparecode.yaaroz.interfaces.NetworkChangeListener;
import com.sparecode.yaaroz.utils.NetworkUtil;

public class NetworkChangeReceiver extends BroadcastReceiver {

    public NetworkChangeReceiver(NetworkChangeListener networkChangeListener) {
        this.networkChangeListener = networkChangeListener;
    }

    NetworkChangeListener networkChangeListener;

    @Override
    public void onReceive(final Context context, final Intent intent) {

        String status = NetworkUtil.getConnectivityStatusString(context);
          Toast.makeText(context, status, Toast.LENGTH_LONG).show();

        if (NetworkUtil.getConnectivityStatusBool(context)) {
            networkChangeListener.onInternetConnected();
        } else {
            networkChangeListener.onInternetDisconnected("Please check your connection !!!");
        }
    }
}