package com.sparecode.yaaroz.fragments;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.ClusterManager;
import com.sparecode.yaaroz.R;
import com.sparecode.yaaroz.activity.BaseActivity;
import com.sparecode.yaaroz.dialog.DialogMatches;
import com.sparecode.yaaroz.interfaces.LocationProvider;
import com.sparecode.yaaroz.location.LocationHelper;
import com.sparecode.yaaroz.utils.DebugLog;
import com.sparecode.yaaroz.utils.MyItem;
import com.sparecode.yaaroz.view.CircularImageView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.sparecode.yaaroz.R.id.map;

/**
 * Created by master on 02-01-2017.
 */

public class MapScreenFragment extends BaseFragment implements OnMapReadyCallback, LocationProvider {


    @Bind(R.id.mapLayout)
    LinearLayout mapLayout;
    @Bind(R.id.mapLoader)
    ProgressBar mapLoader;
    @Bind(R.id.fabMapArrowIcon)
    FloatingActionButton fabMapArrowIcon;
    @Bind(R.id.fabMapFilterIcon)
    FloatingActionButton fabMapFilterIcon;
    private View view;
    private LocationHelper locationHelper;
    private GoogleMap googleMap;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mapscreen, container, false);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(map);
        mapFragment.getMapAsync(this);

        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        locationHelper = new LocationHelper(getActivity(), this);
        mapLayout.setVisibility(View.GONE);
        mapLoader.setVisibility(View.VISIBLE);


    }

    @Override
    public void setToolbarForFragment() {
        DebugLog.e("TOOLBAR FOR FRAGMENT");
        ((BaseActivity) getActivity()).getAppbar().setVisibility(View.VISIBLE);
        DebugLog.e("::->" + ((BaseActivity) getActivity()).getAppbar().getVisibility());
        ((BaseActivity) getActivity()).getTxtTitle().setText("TEST TEST");
        DebugLog.e("::DATA::" + ((BaseActivity) getActivity()).getTxtTitle().getText());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mapLayout.setVisibility(View.VISIBLE);
                mapLoader.setVisibility(View.GONE);
            }
        }, 1500);

    }

    LatLng previousLatLng;

    @Override
    public void onNewLcoationReceived(Location location) {
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        if (previousLatLng == null) {
            previousLatLng = latLng;
            addMarker(previousLatLng);
        } else {
            if (previousLatLng.latitude == location.getLatitude() && previousLatLng.longitude == location.getLongitude()) {
                return;
            } else {
                addMarker(latLng);


            }
        }
    }

    @OnClick(R.id.fabMapArrowIcon)
    void onFabMapArrowClick() {
        openMatchesDialog();
    }

    @OnClick(R.id.fabMapFilterIcon)
    void onFabMapFilterClick() {
        DebugLog.e("::FILTER CLICKED::");
    }

    Marker marker;
    private ClusterManager<MyItem> mClusterManager;

    private void addMarker(final LatLng latLng) {


        final View markerView = ((LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.layout_marker, null);
        final CircularImageView userImage = (CircularImageView) markerView.findViewById(R.id.imgMarker);

        Picasso.with(getActivity()).load("https://fakeimg.pl/300/").into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                userImage.setImageBitmap(bitmap);
                addItems(latLng);
                marker = googleMap.addMarker(new MarkerOptions()
                        .position(latLng)
                        .icon(BitmapDescriptorFactory.fromBitmap(createDrawableFromView(getActivity(), markerView))));
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        });


        mClusterManager = new ClusterManager<MyItem>(getActivity(), googleMap);


/*        marker = googleMap.addMarker(new MarkerOptions()
                .position(latLng));*/
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 14);

        googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                DebugLog.e("CLICKED MARKER!!");
                openMatchesDialog();
                return false;
            }
        });
        googleMap.animateCamera(cameraUpdate);
    }

    private void addItems(LatLng latLng) {

        // Set some lat/lng coordinates to start with.
        double lat;
        double lng;

        // Add ten cluster items in close proximity, for purposes of this example.
        for (int i = 0; i < 10; i++) {
            double offset = i / 60d;
            lat = latLng.latitude + offset;
            lng = latLng.longitude + offset;
            MyItem offsetItem = new MyItem(lat, lng);
            mClusterManager.addItem(offsetItem);
        }
    }

    private void openMatchesDialog() {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        DialogMatches dialogMatches = new DialogMatches(getDisplayWidth());

        dialogMatches.show(fragmentTransaction, "matches");
    }

    private int getDisplayWidth() {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int height = displaymetrics.heightPixels;
        int width = displaymetrics.widthPixels;
        DebugLog.e("HEIGHT::" + height + "::WIDTH::" + width);
        return width;
    }

    private Bitmap getMarkerBitmapFromView(@DrawableRes int resId) {

        View customMarkerView = ((LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.layout_marker, null);
        ImageView markerImageView = (ImageView) customMarkerView.findViewById(R.id.imgMarker);
        markerImageView.setImageResource(resId);
        customMarkerView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        customMarkerView.layout(0, 0, customMarkerView.getMeasuredWidth(), customMarkerView.getMeasuredHeight());
        customMarkerView.buildDrawingCache();
        Bitmap returnedBitmap = Bitmap.createBitmap(customMarkerView.getMeasuredWidth(), customMarkerView.getMeasuredHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(returnedBitmap);
        canvas.drawColor(Color.WHITE, PorterDuff.Mode.SRC_IN);
        Drawable drawable = customMarkerView.getBackground();
        if (drawable != null)
            drawable.draw(canvas);
        customMarkerView.draw(canvas);
        return returnedBitmap;
    }

    public static Bitmap createDrawableFromView(Context context, View view) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        view.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        view.measure(displayMetrics.widthPixels, displayMetrics.heightPixels);
        view.layout(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels);
        view.buildDrawingCache();
        Bitmap bitmap = Bitmap.createBitmap(view.getMeasuredWidth(), view.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);

        return bitmap;
    }
}
