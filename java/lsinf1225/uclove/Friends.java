package lsinf1225.uclove;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;


/**
 * Write a description of class Friends here.
 *
 * @author Groupe P
 * @version 25.04.2016
 */
public class Friends
{
    // instance variables
    private String listOwnerStr;
    private ArrayList<String> friendsListStr;
    private ArrayList<String> recFriendsRequestsStr;
    private ArrayList<String> sentFriendsRequestsStr;
    //private User[] friendsListUsr;  //on veut qu'il se delete des qu'on l'utilise plus, car des users ca prend de la mémoire
    /*private Chat friendChat;*/

    /**
     * Constructor for objects of class Friends
     */

    public Friends(String listOwnerStr, boolean alreadyCreated, Context context){
        if(alreadyCreated){
            this.listOwnerStr = listOwnerStr;
            this.friendsListStr = new ArrayList();
            this.recFriendsRequestsStr = new ArrayList();
            this.recFriendsRequestsStr = new ArrayList();
        }
        else{
            FriendshipManager fM = new FriendshipManager(context);
            fM.open();
            this.listOwnerStr = listOwnerStr;
            this.friendsListStr= fM.getFriendsStr(listOwnerStr);
            this.recFriendsRequestsStr= fM.getRecFriendsRequestStr(listOwnerStr);
            this.sentFriendsRequestsStr = fM.getSentFriendsRequestStr(listOwnerStr);
            //this.recFriendsRequestsStr= new ArrayList<String>(Arrays.asList(this.getRecFriendsRequestsStr()));
            //this.sentFriendsRequestsStr = new ArrayList<String>(Arrays.asList(this.getRecFriendsRequestsStr()));
            fM.close();
        }

    }

    public boolean sendFriendRequest(String target, Context context){ //sends true if worked, sends false if it was already a friend
            FriendshipManager fM = new FriendshipManager(context);
            fM.open();
            fM.addFriendship(new Friendship(listOwnerStr, target, null));
            sentFriendsRequestsStr = fM.getSentFriendsRequestStr(listOwnerStr);
            fM.close();
            return true;
        }

    public void acceptFriendRequest(User target, Context context){
        recFriendsRequestsStr.remove(target);
        friendsListStr.add(target.getLoginStr());
        FriendshipManager fM = new FriendshipManager(context);
        fM.open();
        fM.modFriendship(new Friendship(listOwnerStr, target.getLoginStr(), "b;" + listOwnerStr + "c;" + "Hi, my new Friend! How is it going?\n" + "d;"));
        fM.close();

    }
    public void refuseFriendRequest(User target, Context context){
        recFriendsRequestsStr.remove(target);
        FriendshipManager fM = new FriendshipManager(context);
        fM.open();
        if(fM.supFriendship(new Friendship(listOwnerStr, target.getLoginStr(), "")) == 0){
            System.err.println("error sql delete request");
        }
        fM.close();
    }
    public void deleteFriend(String target, Context context){
        friendsListStr.remove(target);
        FriendshipManager fM = new FriendshipManager(context);
        fM.open();
        if(fM.supFriendship(new Friendship(listOwnerStr, target, "")) == 0){
            fM.supFriendship(new Friendship(target, listOwnerStr, ""));
        }
        fM.close();
    }
    public String getListOwnerStr() {
        return listOwnerStr;
    }

    public String[] getFriendsListStr() {
        return (String[])friendsListStr.toArray();
    }

    public String[] getRecFriendsRequestsStr() {
        return (String[])recFriendsRequestsStr.toArray();
    }

    public String[] getSentFriendsRequestsStr() {
        return (String[])sentFriendsRequestsStr.toArray();
    }

    public User[] getFriendsListUsr(Context context) { //on load les users
        User []friendsListUsr = new User[friendsListStr.size()];
        for(int i = 0;i < friendsListStr.size();i++){
            friendsListUsr[i] = new User(friendsListStr.get(i), context);
        }
        return friendsListUsr;
    }

    public ArrayList<User> getFriendsUsr(Context context) {
        UserManager uM = new UserManager(context);
        ArrayList<User> FrUsr = new ArrayList();
        try{
            for(int i = 0;i<friendsListStr.size();i++) {
                FrUsr.add(i,new User(friendsListStr.get(i), context));
            }
        }
        catch(Exception e){
            FrUsr = null;
        }
        return FrUsr;
    }


    public ArrayList<User> getSentFriendsRequestsUsr(Context context) {
        UserManager uM = new UserManager(context);
        ArrayList<User> sentFrReqUsr = new ArrayList();
        try{
            for(int i = 0;i<sentFriendsRequestsStr.size();i++) {
                sentFrReqUsr.add(i,uM.getUserStr(sentFriendsRequestsStr.get(i)));
            }
        }
        catch(NullPointerException n){}

        return sentFrReqUsr;
    }

    public ArrayList<User> getRecFriendsRequestsUsr(Context context) {
        //UserManager uM = new UserManager(context);
        ArrayList<User> recFrReqUsr = new ArrayList();
        for(int i = 0;i<recFriendsRequestsStr.size();i++) {
            recFrReqUsr.add(i, new User(recFriendsRequestsStr.get(i),context));
            //recFrReqUsr.add(i,uM.getUserStr(recFriendsRequestsStr.get(i)));
        }
        return recFrReqUsr;
    }

}