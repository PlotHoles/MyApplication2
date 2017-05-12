package com.sparecode.yaaroz.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sparecode.yaaroz.R;
import com.sparecode.yaaroz.activity.BaseActivity;
import com.sparecode.yaaroz.model.RoommatesMatchesData;
import com.sparecode.yaaroz.view.CustomButton;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Sanket on 1/16/2017.
 */

public class SelectCategoryFragment extends BaseFragment {

    @Bind(R.id.btnRoom)
    CustomButton btnRoom;
    @Bind(R.id.btnRoommates)
    CustomButton btnRoommates;
    @Bind(R.id.btnListroom)
    CustomButton btnListroom;
    private View view;
    RoommatesMatchesData roommatesMatchesData;

    public SelectCategoryFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_selectcategory, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void setToolbarForFragment() {
        ((BaseActivity) getActivity()).getAppbar().setVisibility(View.VISIBLE);
        ((BaseActivity) getActivity()).getTxtTitle().setText("Let's Get Started");
        ((BaseActivity) getActivity()).getTabLayout().setVisibility(View.GONE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.btnRoom, R.id.btnRoommates, R.id.btnListroom})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnRoom:
                mainNavInterface.openRoomMatchesScreen();
                break;
            case R.id.btnRoommates:
                mainNavInterface.openRoomMate();
                break;
            case R.id.btnListroom:
                mainNavInterface.openListARoom();
                break;
        }
    }
}
