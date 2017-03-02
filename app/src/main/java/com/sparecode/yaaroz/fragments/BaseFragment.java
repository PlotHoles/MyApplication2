package com.sparecode.yaaroz.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

/**
 * Created by master on 29-12-2016.
 */

public abstract class BaseFragment extends Fragment {
    public abstract void setToolbarForFragment();


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setToolbarForFragment();
    }
}
