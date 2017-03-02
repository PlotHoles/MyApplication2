package com.sparecode.yaaroz.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import com.sparecode.yaaroz.R;
import com.sparecode.yaaroz.activity.BaseActivity;
import com.sparecode.yaaroz.view.CustomTextView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by prima on 2/2/17.
 */

public class ListARoomFragment extends BaseFragment {
    @Bind(R.id.txtSelectCity)
    com.sparecode.yaaroz.view.CustomTextView txtSelectCity;
    @Bind(R.id.spinnerSelectCity)
    Spinner spinnerSelectCity;
    @Bind(R.id.txtAddress)
    CustomTextView txtAddress;
    @Bind(R.id.txtPhoto)
    CustomTextView txtPhoto;
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
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
