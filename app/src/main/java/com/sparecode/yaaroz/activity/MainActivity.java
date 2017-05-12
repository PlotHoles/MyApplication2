package com.sparecode.yaaroz.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;

import com.sparecode.yaaroz.R;
import com.sparecode.yaaroz.permission.PiemissionsUtils;
import com.sparecode.yaaroz.utils.DebugLog;

import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        PiemissionsUtils.init(this);
        openSplashScreen();

        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.main_icon));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.room_matches));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.chat_icon));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.menu));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0 )
                    openSelectCategoryScreen();
                else if (tab.getPosition() == 1)
                    openRoomMatchesScreen();
                else if (tab.getPosition() == 2){}
                   // openPersonChat();
                else if (tab.getPosition() == 3)
                    openMOreScreen();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        Fragment f = getSupportFragmentManager().findFragmentById(R.id.container);
        DebugLog.e("COUNT::" + getSupportFragmentManager().getBackStackEntryCount());
        if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
            finish();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PiemissionsUtils.onRequestResult(requestCode, permissions, grantResults);
    }
}
