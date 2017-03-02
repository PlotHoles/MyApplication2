package com.sparecode.yaaroz.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sparecode.yaaroz.R;
import com.sparecode.yaaroz.view.CustomEditText;
import com.sparecode.yaaroz.view.seekbar.RangeSeekBar;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Sanket on 2/2/2017.
 */

public class EditProfileFragment extends BaseFragment {
    @Bind(R.id.customEditText)
    CustomEditText customEditText;
    @Bind(R.id.ageRangeSeekBar)
    RangeSeekBar ageRangeSeekBar;

    @Override
    public void setToolbarForFragment() {

    }

    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_editprofile, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
       // ageRangeSeekBar.setRangeValues(18,65);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
