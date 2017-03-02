package com.sparecode.yaaroz.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.sparecode.yaaroz.R;
import com.sparecode.yaaroz.view.CustomTextView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Sanket on 2/21/2017.
 */

public class FavoritesFragment extends BaseFragment {
    @Bind(R.id.txtFavNotFound)
    CustomTextView txtFavNotFound;
    @Bind(R.id.favoriteLoader)
    ProgressBar favoriteLoader;
    @Bind(R.id.tabLayoutFavorites)
    TabLayout tabLayoutFavorites;
    @Bind(R.id.viewPagerFavorites)
    ViewPager viewPagerFavorites;
    @Bind(R.id.favMainLoader)
    LinearLayout favMainLoader;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_favorites, container, false);
        ButterKnife.bind(this, view);
        showProgress();
        return view;
    }

    @Override
    public void setToolbarForFragment() {

    }

    private void setupFavoritePage() {

    }

    private void showProgress() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                favoriteLoader.setVisibility(View.GONE);
                favMainLoader.setVisibility(View.VISIBLE);
            }
        }, 1500);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
