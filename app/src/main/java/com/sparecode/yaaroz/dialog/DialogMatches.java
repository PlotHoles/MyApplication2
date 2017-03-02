package com.sparecode.yaaroz.dialog;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.sparecode.yaaroz.R;
import com.sparecode.yaaroz.adapter.RoomMatchesDialogPagerAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Sanket on 1/30/2017.
 */
@SuppressLint("ValidFragment")
public class DialogMatches extends DialogFragment {

    @Bind(R.id.dialog_matches_viewPager)
    ViewPager dialogMatchesViewPager;
    @Bind(R.id.imgPreviousMatch)
    ImageView imgPreviousMatch;
    @Bind(R.id.imgNextMatch)
    ImageView imgNextMatch;
    @Bind(R.id.dialogParentLinear)
    LinearLayout dialogParentLinear;
    private View view;
    int dialogWidth;

    public DialogMatches(int dialogWidth) {
        this.dialogWidth = dialogWidth;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.dialog_matches, container, false);
        Window window = getDialog().getWindow();
        ButterKnife.bind(this, view);
        return view;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final RelativeLayout root = new RelativeLayout(getActivity());
        root.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        // creating the fullscreen dialog
        final Dialog dialog = new Dialog(getActivity(), R.style.DialogAnimation);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        WindowManager.LayoutParams wlp = dialog.getWindow().getAttributes();
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        wlp.gravity = Gravity.BOTTOM;
        wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;

        dialog.getWindow().setAttributes(wlp);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(root);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        return dialog;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getDialog().getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialogMatchesViewPager.setAdapter(new RoomMatchesDialogPagerAdapter(getActivity()));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.imgPreviousMatch)
    void onPreviousMatchClick() {
        dialogMatchesViewPager.setCurrentItem(getItem(-1), true);
    }

    @OnClick(R.id.imgNextMatch)
    void onNextMatchClick() {
        dialogMatchesViewPager.setCurrentItem(getItem(+1), true);
    }

    private int getItem(int i) {
        return dialogMatchesViewPager.getCurrentItem() + i;
    }
}
