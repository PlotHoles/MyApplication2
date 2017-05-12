package com.sparecode.yaaroz.activity;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sparecode.yaaroz.R;
import com.sparecode.yaaroz.fragments.CompleteProfileFragment;
import com.sparecode.yaaroz.fragments.EditProfileFragment;
import com.sparecode.yaaroz.fragments.FavoritesFragment;
import com.sparecode.yaaroz.fragments.InviteFriendsFragment;
import com.sparecode.yaaroz.fragments.ListARoomFragment;
import com.sparecode.yaaroz.fragments.LoginFragment;
import com.sparecode.yaaroz.fragments.MapScreenFragment;
import com.sparecode.yaaroz.fragments.MoreOptionsFragment;
import com.sparecode.yaaroz.fragments.PersonChatFragment;
import com.sparecode.yaaroz.fragments.RecentChatFragment;
import com.sparecode.yaaroz.fragments.RoomDetailsFragment;
import com.sparecode.yaaroz.fragments.RoomMatchesFragment;
import com.sparecode.yaaroz.fragments.RoomMateFragment;
import com.sparecode.yaaroz.fragments.RoommatchesFilterFragment;
import com.sparecode.yaaroz.fragments.RoommateMatchesFilterFragment;
import com.sparecode.yaaroz.fragments.RoommateMatchesFragment;
import com.sparecode.yaaroz.fragments.SelectCategoryFragment;
import com.sparecode.yaaroz.fragments.SelectCityFragment;
import com.sparecode.yaaroz.fragments.SplashFragment;
import com.sparecode.yaaroz.interfaces.MainNavInterface;
import com.sparecode.yaaroz.interfaces.NetworkChangeListener;
import com.sparecode.yaaroz.model.RecentChatData;
import com.sparecode.yaaroz.receivers.NetworkChangeReceiver;
import com.sparecode.yaaroz.utils.DebugLog;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by master on 29-12-2016.
 */

public class BaseActivity extends AppCompatActivity implements MainNavInterface, NetworkChangeListener {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.appbar)
    AppBarLayout appbar;
    @Bind(R.id.container)
    FrameLayout container;
    @Bind(R.id.txtTitle)
    TextView txtTitle;
    @Bind(R.id.activity_main)
    LinearLayout activityMain;

    @Bind(R.id.imgToolbarBack)
    ImageView imgToolbarBack;
    NetworkChangeReceiver mReceiver;
    IntentFilter mIntentFilter;
    @Bind(R.id.tabLayout)
    TabLayout tabLayout;

    public TabLayout getTabLayout() {
        return tabLayout;
    }

    public void setTabLayout(TabLayout tabLayout) {
        this.tabLayout = tabLayout;
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

    public static boolean isCall;

    public void setToolbar(Toolbar toolbar) {
        this.toolbar = toolbar;
    }

    public AppBarLayout getAppbar() {
        return appbar;
    }


    public void setAppbar(AppBarLayout appbar) {
        this.appbar = appbar;
    }

    public TextView getTxtTitle() {
        return txtTitle;
    }

    public void setImgToolbarBack(ImageView imgToolbarBack) {
        this.imgToolbarBack = imgToolbarBack;
    }

    public ImageView getImgToolbarBack() {
        return imgToolbarBack;
    }

    View view;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = getLayoutInflater().inflate(R.layout.activity_main, null, false);
        setContentView(R.layout.activity_main);
        mReceiver = new NetworkChangeReceiver(this);
        ButterKnife.bind(this);
    }

    public void replaceFragment(Fragment mFragment, int id, String tag, boolean addToStack) {
        FragmentTransaction mTransaction = getSupportFragmentManager().beginTransaction();
        mTransaction.replace(id, mFragment);
        DebugLog.e("TAG::" + tag);
        if (addToStack) {
            mTransaction.addToBackStack(tag);
        }
        mTransaction.commit();
    }

    public void addFragment(Fragment mFragment, int id, String tag) {
        FragmentTransaction mTransaction = getSupportFragmentManager().beginTransaction();
        mTransaction.add(id, mFragment);
        DebugLog.e("TAG::" + tag);
        mTransaction.addToBackStack(tag);
        mTransaction.commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mIntentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(mReceiver, mIntentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(mReceiver);
    }

    @Override
    public void openSplashScreen() {
        replaceFragment(new SplashFragment(), R.id.container, null, false);
    }

    @Override
    public void openLoginScreen() {
        replaceFragment(new LoginFragment(), R.id.container, LoginFragment.class.getName(), true);
    }

    @Override
    public void openHomePage() {

    }

    @Override
    public void openMapScreen() {
        replaceFragment(new MapScreenFragment(), R.id.container, MapScreenFragment.class.getName(), true);
    }

    @Override
    public void openSelectCategoryScreen() {
        replaceFragment(new SelectCategoryFragment(), R.id.container, SelectCategoryFragment.class.getName(), true);
    }

    @Override
    public void openRoomMatchesScreen() {
        replaceFragment(new RoomMatchesFragment(), R.id.container, RoomMatchesFragment.class.getName(), true);
    }

    @Override
    public void openRoomDetailsPage(String id) {
        replaceFragment(new RoomDetailsFragment(id), R.id.container, RoomDetailsFragment.class.getName(), true);
    }

    @Override
    public void openEditProfilePage() {
        replaceFragment(new EditProfileFragment(), R.id.container, EditProfileFragment.class.getName(), true);
    }

    @Override
    public void openSelectCity(boolean isFromLogin) {
        if (isFromLogin) {
            replaceFragment(new SelectCityFragment(true), R.id.container, SelectCityFragment.class.getName(), false);
        } else {
            replaceFragment(new SelectCityFragment(false), R.id.container, SelectCityFragment.class.getName(), true);
        }
    }

    @Override
    public void openRecentChat(RecentChatData recentChatData) {
        replaceFragment(new RecentChatFragment(recentChatData), R.id.container, RecentChatFragment.class.getName(), true);
    }

    /*@Override
    public void openRoommateMatches(RoommatesMatchesData roommatesMatchesData) {
        replaceFragment(new RoommateMatchesFragment(roommatesMatchesData), R.id.container, RoommateMatchesFragment.class.getName(), true);
    }*/

    @Override
    public void openRoommateMatches() {
        replaceFragment(new RoommateMatchesFragment(), R.id.container, RoommateMatchesFragment.class.getName(), true);
    }


    @Override
    public void openPersonChat() {
        replaceFragment(new PersonChatFragment(), R.id.container, PersonChatFragment.class.getName(), true);
    }

    @Override
    public void openListARoom() {
        replaceFragment(new ListARoomFragment(), R.id.container, ListARoomFragment.class.getName(), true);
    }

    @Override
    public void openInviteFriends() {
        replaceFragment(new InviteFriendsFragment(), R.id.container, InviteFriendsFragment.class.getName(), true);
    }

    @Override
    public void openRoomMatchesFilter() {
        replaceFragment(new RoommatchesFilterFragment(), R.id.container, RoommatchesFilterFragment.class.getName(), true);
    }

    @Override
    public void openRoommateMatchesFiler() {
        replaceFragment(new RoommateMatchesFilterFragment(), R.id.container, RoommateMatchesFilterFragment.class.getName(), true);
    }

    @Override
    public void openFavoriteScreen() {
        replaceFragment(new FavoritesFragment(), R.id.container, FavoritesFragment.class.getName(), true);
    }

    @Override
    public void openCompleteProfileFragment() {
        replaceFragment(new CompleteProfileFragment(), R.id.container, CompleteProfileFragment.class.getName(), true);
    }

    @Override
    public void openMOreScreen() {
        replaceFragment(new MoreOptionsFragment(),R.id.container,MoreOptionsFragment.class.getName(),true);
    }

    @Override
    public void openRoomMate() {
        replaceFragment(new RoomMateFragment(),R.id.container,RoomMateFragment.class.getName(),true);
    }


    @Override
    public void onInternetConnected() {
        DebugLog.e("KEEP CALLING WS !!");
    }

    @Override
    public void onInternetDisconnected(String msg) {
        Toast.makeText(this, "" + msg, Toast.LENGTH_LONG).show();
    }


}
