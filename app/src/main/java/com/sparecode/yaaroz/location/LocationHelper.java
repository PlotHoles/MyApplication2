package com.sparecode.yaaroz.location;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;


import com.sparecode.yaaroz.interfaces.LocationProvider;
import com.sparecode.yaaroz.utils.DebugLog;


/**
 * Created by master on 18-07-2016.
 */
public class LocationHelper implements
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {
    LocationProvider locationProvider;
    Location mLastLocation;

    private LocationRequest mLocationRequest;
    private GoogleApiClient googleApiClient;
    private static final long INTERVAL = 100;
    private static final long FASTEST_INTERVAL = 100;
    private Context mContext;
    private Location mCurrentLocation;


    public LocationHelper(Context mContext, LocationProvider locationProvider) {
        this.mContext = mContext;
        this.locationProvider = locationProvider;
        if (isGooglePlayServicesAvailable()) {
            createLocationRequest();
            googleApiClient = new GoogleApiClient.Builder(mContext)
                    .addApi(LocationServices.API)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .build();
            doLocationConnect();
        } else {
            ((Activity) mContext).finish();
        }
    }

    public void doLocationConnect() {
        googleApiClient.connect();
    }

    public void doLicationDisconnect() {
        googleApiClient.disconnect();
    }


    protected void startLocationUpdates() {

        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        PendingResult<Status> pendingResult = LocationServices.FusedLocationApi.requestLocationUpdates(
                googleApiClient, mLocationRequest, this);

    }

    private boolean isGooglePlayServicesAvailable() {
        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(mContext);
        if (ConnectionResult.SUCCESS == status) {
            return true;
        } else {
            GooglePlayServicesUtil.getErrorDialog(status, (Activity) mContext, 0).show();
            return false;
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        DebugLog.e("CONNECTED");
        startLocationUpdates();
    }

    protected void createLocationRequest() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(INTERVAL);
        mLocationRequest.setFastestInterval(FASTEST_INTERVAL);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onLocationChanged(Location location) {
        //DebugLog.e("LOCATION:" + location);
        mCurrentLocation = location;
        locationProvider.onNewLcoationReceived(location);
    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        DebugLog.e("Fail" + connectionResult.getErrorMessage());
    }
}
