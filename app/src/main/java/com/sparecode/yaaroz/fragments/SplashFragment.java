package com.sparecode.yaaroz.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.sparecode.yaaroz.R;
import com.sparecode.yaaroz.activity.BaseActivity;
import com.sparecode.yaaroz.database.YaarozDatabaseHelper;
import com.sparecode.yaaroz.model.User;
import com.sparecode.yaaroz.utils.ParticleView;
import com.sparecode.yaaroz.utils.Prefs;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by master on 29-12-2016.
 */

public class SplashFragment extends BaseFragment {
    @Bind(R.id.imgSplashLogo)
    ImageView imgSplashLogo;
    @Bind(R.id.pv_1)
    ParticleView pv1;
    private View view;
    private YaarozDatabaseHelper yaarozDatabaseHelper;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_splashscreen, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        pv1.startAnim();
        yaarozDatabaseHelper = new YaarozDatabaseHelper(getActivity());
        pv1.setOnParticleAnimListener(new ParticleView.ParticleAnimListener() {
            @Override
            public void onAnimationEnd() {

                if (getActivity() != null) {
                    if (getUser() != null) {
                        //mainNavInterface.openSelectCategoryScreen();
                        if (yaarozDatabaseHelper.isUserHasSelectedCity(getUser().getData().getId())) {
                            mainNavInterface.openListARoom();
                        } else {
                            mainNavInterface.openSelectCity(true);
                        }
                    } else {
                        ((BaseActivity) getActivity()).openLoginScreen();
                    }
                    //((BaseActivity) getActivity()).openSelectCity();
                    //((BaseActivity) getActivity()).openMapScreen();
                    //((BaseActivity) getActivity()).openMapScreen();
                }
            }
        });
       /* ObjectAnimator fadeOut = ObjectAnimator.ofFloat(imgSplashLogo, "alpha", 1f, .3f);
        fadeOut.setDuration(1500);
        ObjectAnimator fadeIn = ObjectAnimator.ofFloat(imgSplashLogo, "alpha", .3f, 1f);
        fadeIn.setDuration(1500);

        final AnimatorSet mAnimationSet = new AnimatorSet();

        mAnimationSet.play(fadeIn).after(fadeOut);

        mAnimationSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mAnimationSet.start();
            }
        });
        mAnimationSet.start();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ((BaseActivity) getActivity()).openSelectCategoryScreen();
            }
        }, 3000);*/

    }

    private User user;

    private User getUser() {
        user = new Gson().fromJson(Prefs.getString("user", ""), User.class);
        return user;
    }

    @Override
    public void setToolbarForFragment() {
        ((BaseActivity) getActivity()).getAppbar().setVisibility(View.GONE);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
