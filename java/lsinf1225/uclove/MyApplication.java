package lsinf1225.uclove;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by Maxence on 28-04-16.
 */
public class MyApplication extends Application {

    private static User user;
    private static User otherUserYon;
    private static User otherUserR;
    private static User userToDisplay;
    private static User userToDisplayR;
    private static User userChat;
    private static ArrayList<User> matchingUsers;
    private static ArrayList<User> friendRequestList;
    private static int positionInMatchingUsers = 0;
    private static int positionInFriendRequestList = 0;



    public static void setMatchingUsers(User user, Context context) {
        UserManager uM = new UserManager(context);
        uM.open();
        matchingUsers = uM.generateListResearch(user, context);
        uM.close();
    }

    public static User getUserChat() {
        return otherUserR;
    }
    public static void setUserChat(User otherUserR) {
        MyApplication.otherUserR = otherUserR;
    }

    public static void setfriendRequestList(User user, Context context) {
        friendRequestList = user.getFriends().getRecFriendsRequestsUsr(context);
    }

    public static User getUserToDisplayR() {
        return userToDisplayR;
    }
    public static void setUserToDisplayR(User userToDisplayR) {
        MyApplication.userToDisplayR = userToDisplayR;
    }
    public static User getOtherUserR() {
        return otherUserR;
    }
    public static void setOtherUserR(User otherUserR) {
        MyApplication.otherUserR = otherUserR;
    }
    public static int getPositionInFriendRequestList() {
        return positionInFriendRequestList;
    }
    public static void setPositionInFriendRequestList(int positionInFriendRequestList) {
        MyApplication.positionInFriendRequestList = positionInFriendRequestList;
    }
    public static ArrayList<User> getFriendRequestList() {
        return friendRequestList;
    }
    public static void setFriendRequestList(ArrayList<User> friendRequestList) {
        MyApplication.friendRequestList = friendRequestList;
    }
    public static int getPositionInMatchingUsers() {
        return positionInMatchingUsers;
    }
    public static void setPositionInMatchingUsers(int positionInMatchingUsers) {
        MyApplication.positionInMatchingUsers = positionInMatchingUsers;
    }
    public static ArrayList<User> getMatchingUsers(Context context) {
        return matchingUsers;
    }

    public static User getUser() {
        return user;
    }
    public static void setUser(User user) {
        MyApplication.user = user;
    }
    public static User getOtherUserYon() {
        return otherUserYon;
    }
    public static void setOtherUserYon(User otherUserYon) {
        MyApplication.otherUserYon = otherUserYon;
    }
    public static User getUserToDisplay() {
        return userToDisplay;
    }
    public static void setUserToDisplay(User userToDisplay) {
        MyApplication.userToDisplay = userToDisplay;
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onTerminate() {
        Toast.makeText(MyApplication.this, "Goodbye :(", Toast.LENGTH_SHORT).show();
        super.onTerminate();
    }


}