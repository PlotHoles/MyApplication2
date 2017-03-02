package com.sparecode.yaaroz.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.sparecode.yaaroz.R;
import com.sparecode.yaaroz.activity.BaseActivity;
import com.sparecode.yaaroz.view.CustomButton;
import com.sparecode.yaaroz.view.CustomTextView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by prima on 6/2/17.
 */

public class InviteFriendsFragment extends BaseFragment {

    @Bind(R.id.txtSMS)
    CustomTextView txtSMS;
    @Bind(R.id.txtEmail)
    CustomTextView txtEmail;
    @Bind(R.id.EdittxtNum)
    EditText EdittxtNum;
    @Bind(R.id.imgContact)
    ImageView imgContact;
    @Bind(R.id.edittxtWriteDetail)
    EditText edittxtWriteDetail;
    @Bind(R.id.btnInvite)
    CustomButton btnInvite;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_invitefriends, container, false);
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


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
