package com.sparecode.yaaroz.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sparecode.yaaroz.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by Sanket on 1/25/2017.
 */

public class MoreOptionsFragment extends BaseFragment {

    @Bind(R.id.profile)
    CardView profile;
    @Bind(R.id.contactus)
    CardView contactus;
    @Bind(R.id.faq)
    CardView faq;
    @Bind(R.id.invitefriends)
    CardView invitefriends;
    @Bind(R.id.settings)
    CardView settings;
    @Bind(R.id.logout)
    CardView logout;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_moreoptions, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void setToolbarForFragment() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.profile, R.id.contactus, R.id.faq, R.id.invitefriends, R.id.settings, R.id.logout})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.profile:
                mainNavInterface.openEditProfilePage();
                break;
            case R.id.contactus:
                break;
            case R.id.faq:
                break;
            case R.id.invitefriends:
                break;
            case R.id.settings:
                break;
            case R.id.logout:
                break;
        }
    }
}
