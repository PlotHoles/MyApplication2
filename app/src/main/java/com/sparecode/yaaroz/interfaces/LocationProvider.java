package com.sparecode.yaaroz.interfaces;

import android.location.Location;

/**
 * Created by master on 18-07-2016.
 */
public interface LocationProvider {
    void onNewLcoationReceived(Location location);
}
