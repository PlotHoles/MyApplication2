package com.sparecode.yaaroz.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import com.sparecode.yaaroz.R;
import com.sparecode.yaaroz.activity.BaseActivity;
import com.sparecode.yaaroz.view.CustomButton;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by prima on 8/2/17.
 */

public class RoommateMatchesFilterFragment extends BaseFragment {

    @Bind(R.id.spinnerRoommateGender)
    Spinner spinnerRoommateGender;
    @Bind(R.id.rangeSeekbarAge)
    com.sparecode.yaaroz.view.seekbar.RangeSeekBar rangeSeekbarAge;
    @Bind(R.id.btnContinue)
    CustomButton btnContinue;
    @Bind(R.id.btnReset)
    CustomButton btnReset;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_roommatematches_filter, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void setToolbarForFragment() {
        ((BaseActivity) getActivity()).getAppbar().setVisibility(View.VISIBLE);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        rangeSeekbarAge.setRangeValues(18, 65);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
