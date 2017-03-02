package com.sparecode.yaaroz.interfaces;

import com.sparecode.yaaroz.model.RecentChatData;
import com.sparecode.yaaroz.model.RoommatesMatchesData;

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

    void openRoomDetailsPage();

    void openEditProfilePage();

    void openSelectCity();

    void openRecentChat(RecentChatData recentChatData);

    void openRoommateMatches(RoommatesMatchesData roommatesMatchesData);

    void openPersonChat();

    void openListARoom();

    void openInviteFriends();

    void openRoomMatchesFilter();

    void openRoommateMatchesFiler();

    void openFavoriteScreen();
}
