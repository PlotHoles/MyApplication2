package com.sparecode.yaaroz.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sparecode.yaaroz.R;
import com.sparecode.yaaroz.activity.BaseActivity;

/**
 * Created by master on 29-12-2016.
 */

public class HomeFragment extends BaseFragment {
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_splashscreen, container, false);
        return view;
    }

    @Override
    public void setToolbarForFragment() {
        ((BaseActivity)getActivity()).getAppbar().setVisibility(View.VISIBLE);
    }
}
