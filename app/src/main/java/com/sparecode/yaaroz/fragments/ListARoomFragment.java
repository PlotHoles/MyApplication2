package com.sparecode.yaaroz.fragments;

import android.Manifest;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.util.Pair;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.sparecode.yaaroz.R;
import com.sparecode.yaaroz.activity.BaseActivity;
import com.sparecode.yaaroz.interfaces.OnResponse;
import com.sparecode.yaaroz.model.AddroomWrapper;
import com.sparecode.yaaroz.model.CityWrapper;
import com.sparecode.yaaroz.model.LocationWrapper;
import com.sparecode.yaaroz.model.User;
import com.sparecode.yaaroz.permission.PiemissionsCallback;
import com.sparecode.yaaroz.permission.PiemissionsRequest;
import com.sparecode.yaaroz.permission.PiemissionsUtils;
import com.sparecode.yaaroz.utils.DebugLog;
import com.sparecode.yaaroz.utils.Prefs;
import com.sparecode.yaaroz.view.CustomButton;
import com.sparecode.yaaroz.view.CustomTextView;
import com.sparecode.yaaroz.view.seekbar.RangeSeekBar;
import com.sparecode.yaaroz.webservice.ReqestParameter;
import com.sparecode.yaaroz.webservice.RequestApi;
import com.sparecode.yaaroz.webservice.VideoPostRequest;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
    @Bind(R.id.edtRoomMates)
    EditText edtRoomMates;

    String nCityId;
    String nLocationId;
    String address;
    String desc;
    String rent;
    String roommates;
    String gender;
    String sLength;
    String furnish;
    String bathroom;
    String smoking;
    String pets;
    String lookingforgender;
    String lookingforage;
    String availdate;
    ArrayList<Uri> images;
    @Bind(R.id.btn_List)
    Button btnList;
    @Bind(R.id.room_Description)
    AutoCompleteTextView roomDescription;
    @Bind(R.id.rent_month)
    EditText rentMonth;
    @Bind(R.id.txtfurnished)
    CustomTextView txtfurnished;
    @Bind(R.id.txtbathroom)
    CustomTextView txtbathroom;
    @Bind(R.id.txtsmoking)
    CustomTextView txtsmoking;
    @Bind(R.id.txtpets)
    CustomTextView txtpets;
    @Bind(R.id.txtgender)
    CustomTextView txtgender;
    @Bind(R.id.txtAge)
    CustomTextView txtAge;
    @Bind(R.id.bathroomradiogroup)
    RadioGroup bathroomradiogroup;
    @Bind(R.id.genderradiogroup)
    RadioGroup genderradiogroup;
    @Bind(R.id.furnishedbedroomgroup)
    RadioGroup furnishedbedroomgroup;
    @Bind(R.id.smokinggroup)
    RadioGroup smokinggroup;
    @Bind(R.id.petsgroup)
    RadioGroup petsgroup;
    @Bind(R.id.Age)
    RangeSeekBar Age;
    private ListARoomBackend listARoomBackend;
    List<File> fileList;
    private String userChoosenTask;
    View view;
    String selectionbathroomradiobutton;
    String selectiongenderradiobutton;
    String selectionfurnishedradiobutton;
    String selectionfurnishedpositive, selectionfurnishednegative;
    String selectionpetsradiobutton, selectionpetspositive, selectionpetsnegative;
    String selectionsmokingradiobutton, selectionsmokingpositive;
    Integer leaseprogress;
    String minage,maxage;
    @Override
    public void setToolbarForFragment() {
        ((BaseActivity) getActivity()).getAppbar().setVisibility(View.VISIBLE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_list_room, container, false);

        ButterKnife.bind(this, view);

        Age.setOnRangeSeekBarChangeListener(new RangeSeekBar.OnRangeSeekBarChangeListener() {
            @Override
            public void onRangeSeekBarValuesChanged(RangeSeekBar bar, Number minValue, Number maxValue) {
                Toast.makeText(getActivity(),minValue+""+maxValue,Toast.LENGTH_SHORT).show();
                minage = String.valueOf(minValue);
                maxage = String.valueOf(maxValue);
            }
        });
        furnishedbedroomgroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                RadioButton radioButton = (RadioButton) view.findViewById(checkedId);
                selectionfurnishedradiobutton = (String) radioButton.getText();
                Toast.makeText(getActivity(), selectionfurnishedradiobutton, Toast.LENGTH_SHORT).show();
                if (selectionfurnishedradiobutton.equalsIgnoreCase("yes")) {
                    selectionfurnishedpositive = "1";
                } else if (selectionfurnishedradiobutton.equalsIgnoreCase("no")) {
                    selectionfurnishedpositive = "0";
                }
            }
        });

        petsgroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                RadioButton radioButton = (RadioButton) view.findViewById(checkedId);
                selectionpetsradiobutton = (String) radioButton.getText();
                Toast.makeText(getActivity(), selectionpetsradiobutton, Toast.LENGTH_SHORT).show();
                if (selectionpetsradiobutton.equalsIgnoreCase("yes")) {
                    selectionpetspositive = "1";
                } else if (selectionpetsradiobutton.equalsIgnoreCase("no")) {
                    selectionpetspositive = "0";
                }
            }
        });
        smokinggroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                RadioButton radioButton = (RadioButton) view.findViewById(checkedId);
                selectionsmokingradiobutton = (String) radioButton.getText();
                Toast.makeText(getActivity(), selectionsmokingradiobutton, Toast.LENGTH_SHORT).show();
                if (selectionsmokingradiobutton.equalsIgnoreCase("yes")) {
                    selectionsmokingpositive = "1";
                } else if (selectionsmokingradiobutton.equalsIgnoreCase("no")) {
                    selectionsmokingpositive = "0";
                }
            }
        });
        bathroomradiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                RadioButton radioButton = (RadioButton) view.findViewById(checkedId);
                selectionbathroomradiobutton = (String) radioButton.getText();
                Toast.makeText(getActivity(), selectionbathroomradiobutton, Toast.LENGTH_SHORT).show();
            }
        });
        genderradiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                RadioButton radioButton = (RadioButton) view.findViewById(checkedId);
                selectiongenderradiobutton = (String) radioButton.getText();
                Toast.makeText(getActivity(), selectiongenderradiobutton, Toast.LENGTH_SHORT).show();
            }
        });

        LeaseLength.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int value = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                value = progress;
                leaseprogress = progress;
                Toast.makeText(getActivity(), "Select:" + leaseprogress + "/" + seekBar.getMax(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
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
        //selectImage();
        PiemissionsRequest request;
        request = new PiemissionsRequest(PERMISSIONS_CODE, PERMISSIONS);
        request.setCallback(new PiemissionsCallback() {
            @Override
            public void onGranted() {
                TedBottomPicker tedBottomPicker = new TedBottomPicker.Builder(getActivity()).setSelectMaxCount(6).setOnMultiImageSelectedListener(new TedBottomPicker.OnMultiImageSelectedListener() {
                    @Override
                    public void onImagesSelected(ArrayList<Uri> uriList) {
                        DebugLog.e("SELECTED IMAGES LIST::" + uriList);
                        images = uriList;
                        Log.e("uri", String.valueOf(uriList.size()));
                        /*for (int i=0; i<=uriList.size();i++)
                        {
                           AddImage(uriList.get(i));
                        }*/
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


    /*public void AddImage(Uri image) {
        File imgfile = new File(image.getPath());
        fileList = new ArrayList<>();
        fileList.add(imgfile);
        Log.e("filelist", String.valueOf(fileList.size()));
    }*/
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
                    nCityId = cityWrapper.getData().get(position).getNCityId();
                    listARoomBackend.getLoations(cityWrapper.getData().get(position).getNCityId());
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
    }

    @Override
    public void provideLocationList(final LocationWrapper locationWrapper) {
        if (getActivity() != null) {
            locationList = new ArrayList<>();
            for (int i = 0; i < locationWrapper.getData().size(); i++) {
                locationList.add(locationWrapper.getData().get(i).getSLocationName());
            }
            ArrayAdapter<String> locationAdatper = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, locationList);
            spinnerSelectLocation.setAdapter(locationAdatper);
            spinnerSelectLocation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    nLocationId = locationWrapper.getData().get(position).getNCityId();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
    }

    public void callservice() {
        Log.e("file", String.valueOf(fileList));
        Log.e("cityid", nCityId);
        Log.e("cityid", AutoCompletetxtAddress.getText().toString());
        Log.e("cityid", roomDescription.getText().toString());
        Log.e("cityid", rentMonth.getText().toString());
        Log.e("cityid", edtRoomMates.getText().toString());
        Log.e("cityid", spinnerGender.getSelectedItem().toString());
        List<Pair<String, String>> list = new ReqestParameter().toAddroom(nCityId, AutoCompletetxtAddress.getText().toString().trim(), roomDescription.getText().toString().trim()
                , rentMonth.getText().toString().trim(), edtRoomMates.getText().toString(), spinnerGender.getSelectedItem().toString(), String.valueOf(leaseprogress), selectionfurnishedpositive, selectionbathroomradiobutton, selectionsmokingpositive, selectionsmokingpositive, selectiongenderradiobutton, minage, spinnerDate.getSelectedItem().toString(), nLocationId, getUser().getData().getId(), maxage, "1.1", "1.1");
        new VideoPostRequest<AddroomWrapper>().onPostRequest1(getActivity(), RequestApi.POST_ROOM, list, images, AddroomWrapper.class, new OnResponse<AddroomWrapper>() {
            @Override
            public void onSuccess(AddroomWrapper addroomWrapper) {
                if (addroomWrapper.getStatus() == 1) {
                    Toast.makeText(getActivity(), addroomWrapper.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onError() {

            }
        });
    }

    private User user;

    private User getUser() {
        user = new Gson().fromJson(Prefs.getString("user", ""), User.class);
        return user;
    }

    @Override
    public void onRoomAddFailure(String msg) {


    }

    @OnClick(R.id.btn_List)
    public void onClick() {
        callservice();
    }
}
