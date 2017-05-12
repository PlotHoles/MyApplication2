package com.sparecode.yaaroz.interfaces;

import com.sparecode.yaaroz.model.RecentChatData;

/**
 * Created by master on 29-12-2016.
 */

public interface MainNavInterface {
    void openSplashScreen();

    void openLoginScreen();

    void openHomePage();

    void openMapScreen();

    void openSelectCategoryScreen();

    void openRoomMatchesScreen();

    void openRoomDetailsPage(String id);

    void openEditProfilePage();

    void openSelectCity(boolean isFromLogin);

    void openRecentChat(RecentChatData recentChatData);

    //void openRoommateMatches(RoommatesMatchesData roommatesMatchesData);

    void openRoommateMatches();

    void openPersonChat();

    void openListARoom();

    void openInviteFriends();

    void openRoomMatchesFilter();

    void openRoommateMatchesFiler();

    void openFavoriteScreen();

    void openCompleteProfileFragment();

    void openMOreScreen();

    void openRoomMate();
}
