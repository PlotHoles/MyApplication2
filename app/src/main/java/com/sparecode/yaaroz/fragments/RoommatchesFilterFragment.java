package com.sparecode.yaaroz.fragments;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.sparecode.yaaroz.R;
import com.sparecode.yaaroz.activity.BaseActivity;
import com.sparecode.yaaroz.model.RoomMatchData;
import com.sparecode.yaaroz.model.RoomMatchWrapper;
import com.sparecode.yaaroz.view.CustomButton;
import com.sparecode.yaaroz.view.CustomTextView;
import com.sparecode.yaaroz.view.seekbar.RangeSeekBar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by prima on 7/2/17.
 */

public class RoommatchesFilterFragment extends BaseFragment implements RoomMatchFilterBackend.RoomMatchFilterProvider {

    @Bind(R.id.spinnerRoommateGender)
    Spinner spinnerRoommateGender;
    @Bind(R.id.rangeSeekbarAge)
    RangeSeekBar rangeSeekbarAge;
    @Bind(R.id.rangeSeekbarLength)
    RangeSeekBar rangeSeekbarLength;
    @Bind(R.id.btncontinue)
    CustomButton btncontinue;
    RoomMatchFilterBackend roomMatchFilterBackend;
    @Bind(R.id.rangeSeekbarBudget)
    RangeSeekBar rangeSeekbarBudget;
    @Bind(R.id.startdate)
    CustomTextView startdate;
    @Bind(R.id.enddate)
    CustomTextView enddate;
    @Bind(R.id.furnishedradiogroup)
    RadioGroup furnishedradiogroup;
    @Bind(R.id.bathroomradiogroup)
    RadioGroup bathroomradiogroup;
    @Bind(R.id.smokingradiogroup)
    RadioGroup smokingradiogroup;
    @Bind(R.id.petsradiogroup)
    RadioGroup petsradiogroup;
    @Bind(R.id.btnReset)
    CustomButton btnReset;
    private List<RoomMatchData> data;
    private int mYear, mMonth, mDay;
    String minLength,maxLength;
    String minAge,maxAge;
    String minBudget,maxBudget;
    View view;
    String selectionbathroomradiobutton;
    String selectionfurnishedradiobutton,selectionfurnishedpositive;
    String selectionsmokingradiobutton,selectionsmokingpositive;
    String selectionpetsradiobutton,selectionpetspositive;
    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_roommatches_filter, container, false);
        ButterKnife.bind(this, view);

        furnishedradiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                RadioButton radioButton = (RadioButton) view.findViewById(checkedId);
                selectionfurnishedradiobutton = (String) radioButton.getText();
                Toast.makeText(getActivity(), selectionfurnishedradiobutton, Toast.LENGTH_SHORT).show();
                if (selectionfurnishedradiobutton.equalsIgnoreCase("yes")) {
                    selectionfurnishedpositive = "1";
                } else if (selectionfurnishedradiobutton.equalsIgnoreCase("no")) {
                    selectionfurnishedpositive = "0";
                } else if (selectionfurnishedradiobutton.equalsIgnoreCase("No Preferance")){
                    selectionfurnishedpositive = "2";
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
        smokingradiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                RadioButton radioButton = (RadioButton) view.findViewById(checkedId);
                selectionsmokingradiobutton = (String) radioButton.getText();
                Toast.makeText(getActivity(), selectionsmokingradiobutton, Toast.LENGTH_SHORT).show();
                if (selectionsmokingradiobutton.equalsIgnoreCase("yes")) {
                    selectionsmokingpositive = "1";
                } else if (selectionsmokingradiobutton.equalsIgnoreCase("no")) {
                    selectionsmokingpositive = "0";
                } else if (selectionsmokingradiobutton.equalsIgnoreCase("No Preferance")){
                    selectionsmokingpositive = "2";
                }
            }
        });
        petsradiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                RadioButton radioButton = (RadioButton) view.findViewById(checkedId);
                selectionpetsradiobutton = (String) radioButton.getText();
                Toast.makeText(getActivity(), selectionpetsradiobutton, Toast.LENGTH_SHORT).show();
                if (selectionpetsradiobutton.equalsIgnoreCase("yes")) {
                    selectionpetspositive = "1";
                } else if (selectionpetsradiobutton.equalsIgnoreCase("no")) {
                    selectionpetspositive = "0";
                } else if (selectionpetsradiobutton.equalsIgnoreCase("No Preferance")){
                    selectionpetspositive = "2";
                }
            }
        });
        return view;
    }

    @Override
    public void setToolbarForFragment() {
        ((BaseActivity) getActivity()).getAppbar().setVisibility(View.VISIBLE);
        ((BaseActivity) getActivity()).getTxtTitle().setText("Filters");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        rangeSeekbarAge.setRangeValues(18, 65);
        data = new ArrayList<>();
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        startdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                Log.e("fromdate", year + "-" + (monthOfYear + 1) + "-" + dayOfMonth + "");
                                startdate.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
        enddate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                Log.e("fromdate", year + "-" + (monthOfYear + 1) + "-" + dayOfMonth + "");
                                enddate.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
        rangeSeekbarLength.setOnRangeSeekBarChangeListener(new RangeSeekBar.OnRangeSeekBarChangeListener() {
            @Override
            public void onRangeSeekBarValuesChanged(RangeSeekBar bar, Number minValue, Number maxValue) {
                minLength = String.valueOf(minValue);
                maxLength = String.valueOf(maxValue);
            }
        });
        rangeSeekbarAge.setOnRangeSeekBarChangeListener(new RangeSeekBar.OnRangeSeekBarChangeListener() {
            @Override
            public void onRangeSeekBarValuesChanged(RangeSeekBar bar, Number minValue, Number maxValue) {
                minAge = String.valueOf(minValue);
                maxAge = String.valueOf(maxValue);
            }
        });
        rangeSeekbarBudget.setOnRangeSeekBarChangeListener(new RangeSeekBar.OnRangeSeekBarChangeListener() {
            @Override
            public void onRangeSeekBarValuesChanged(RangeSeekBar bar, Number minValue, Number maxValue) {
                minBudget = String.valueOf(minValue);
                maxBudget = String.valueOf(maxValue);
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.btncontinue)
    public void onClick() {
        roomMatchFilterBackend = new RoomMatchFilterBackend(getActivity(), minLength, minBudget, startdate.getText().toString(), enddate.getText().toString(), selectionfurnishedpositive, selectionbathroomradiobutton, selectionsmokingpositive,selectionpetspositive, minAge, maxAge, minLength, maxLength, minBudget, maxBudget, this);
    }

    @Override
    public void onRoomListReceived(RoomMatchWrapper roomMatchWrapper) {
        mainNavInterface.openRoomMatchesScreen();
        RoomMatchesFragment roomMatchesFragment = new RoomMatchesFragment(roomMatchWrapper);
        data.addAll(roomMatchWrapper.getData());
    }

    @Override
    public void onError(String msg) {

    }
}
