package com.sparecode.yaaroz.fragments;

import android.Manifest;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.SeekBar;
import android.widget.Spinner;

import com.sparecode.yaaroz.R;
import com.sparecode.yaaroz.activity.BaseActivity;
import com.sparecode.yaaroz.model.CityWrapper;
import com.sparecode.yaaroz.model.LocationWrapper;
import com.sparecode.yaaroz.permission.PiemissionsCallback;
import com.sparecode.yaaroz.permission.PiemissionsRequest;
import com.sparecode.yaaroz.permission.PiemissionsUtils;
import com.sparecode.yaaroz.utils.DebugLog;
import com.sparecode.yaaroz.view.CustomButton;
import com.sparecode.yaaroz.view.CustomTextView;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import gun0912.tedbottompicker.TedBottomPicker;

/**
 * Created by prima on 2/2/17.
 */

public class ListARoomFragment extends BaseFragment implements ListARoomBackend.ListARoomDataProvider {


    @Bind(R.id.txtSelectCity)
    CustomTextView txtSelectCity;
    @Bind(R.id.spinnerSelectCity)
    Spinner spinnerSelectCity;
    @Bind(R.id.txtAddress)
    CustomTextView txtAddress;
    @Bind(R.id.AutoCompletetxtAddress)
    AutoCompleteTextView AutoCompletetxtAddress;
    @Bind(R.id.txtPhoto)
    CustomTextView txtPhoto;
    @Bind(R.id.CustomBtnAdd)
    CustomButton CustomBtnAdd;
    @Bind(R.id.txtDescription)
    CustomTextView txtDescription;
    @Bind(R.id.txtRent)
    CustomTextView txtRent;
    @Bind(R.id.txtDate)
    CustomTextView txtDate;
    @Bind(R.id.spinnerDate)
    Spinner spinnerDate;
    @Bind(R.id.txtNumRoommate)
    CustomTextView txtNumRoommate;
    @Bind(R.id.txtGender)
    CustomTextView txtGender;
    @Bind(R.id.spinnerGender)
    Spinner spinnerGender;
    @Bind(R.id.txtLength)
    CustomTextView txtLength;
    @Bind(R.id.LeaseLength)
    SeekBar LeaseLength;
    @Bind(R.id.txtSelectLocation)
    CustomTextView txtSelectLocation;
    @Bind(R.id.spinnerSelectLocation)
    Spinner spinnerSelectLocation;

    private ListARoomBackend listARoomBackend;

    @Override
    public void setToolbarForFragment() {
        ((BaseActivity) getActivity()).getAppbar().setVisibility(View.VISIBLE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_room, container, false);

        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        listARoomBackend = new ListARoomBackend(getActivity(), this);
        listARoomBackend.getCity();
    }

    private static final String[] PERMISSIONS = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
    private static final int PERMISSIONS_CODE = 1337;

    @OnClick(R.id.CustomBtnAdd)
    void onAddImagesClicked() {
        PiemissionsRequest request;
        request = new PiemissionsRequest(PERMISSIONS_CODE, PERMISSIONS);
        request.setCallback(new PiemissionsCallback() {
            @Override
            public void onGranted() {
                TedBottomPicker tedBottomPicker = new TedBottomPicker.Builder(getActivity()).setSelectMaxCount(6).setOnMultiImageSelectedListener(new TedBottomPicker.OnMultiImageSelectedListener() {
                    @Override
                    public void onImagesSelected(ArrayList<Uri> uriList) {
                        DebugLog.e("SELECTED IMAGES LIST::" + uriList);
                    }
                }).create();
                tedBottomPicker.show(getActivity().getSupportFragmentManager());
            }

            @Override
            public boolean onDenied(HashMap<String, Boolean> rationalizablePermissions) {
                DebugLog.e("Permission Denied" + rationalizablePermissions);
                return false;
            }
        });
        PiemissionsUtils.requestPermission(request);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private ArrayList<String> cityList;
    private ArrayList<String> locationList;

    @Override
    public void provideCityList(final CityWrapper cityWrapper) {
        if (getActivity() != null) {

            cityList = new ArrayList<>();
            for (int i = 0; i < cityWrapper.getData().size(); i++) {
                cityList.add(cityWrapper.getData().get(i).getSCityName());
            }
            ArrayAdapter<String> cityAdatper = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, cityList);
            spinnerSelectCity.setAdapter(cityAdatper);

            spinnerSelectCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    DebugLog.e("CITY ID IS:" + cityWrapper.getData().get(position).getNCityId());
                    listARoomBackend.getLoations(cityWrapper.getData().get(position).getNCityId());
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
    }

    @Override
    public void provideLocationList(LocationWrapper locationWrapper) {
        if (getActivity() != null) {
            locationList = new ArrayList<>();
            for (int i = 0; i < locationWrapper.getData().size(); i++) {
                locationList.add(locationWrapper.getData().get(i).getSLocationName());
            }
            ArrayAdapter<String> locationAdatper = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, locationList);
            spinnerSelectLocation.setAdapter(locationAdatper);
        }
    }

    @Override
    public void onRoomAddFailure(String msg) {

    }
}
