package com.sparecode.yaaroz.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sparecode.yaaroz.interfaces.MainNavInterface;

/**
 * Created by master on 29-12-2016.
 */

public abstract class BaseFragment extends Fragment {
    public abstract void setToolbarForFragment();

    MainNavInterface mainNavInterface;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mainNavInterface = (MainNavInterface) getActivity();
        setToolbarForFragment();
    }
}
