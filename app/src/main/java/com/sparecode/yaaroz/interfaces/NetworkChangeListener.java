package com.sparecode.yaaroz.interfaces;

/**
 * Created by Sanket on 2/20/2017.
 */

public interface NetworkChangeListener {
    void onInternetConnected();
    void onInternetDisconnected(String msg);
}
