package lsinf1225.uclove;
import android.content.Context;

import java.util.Calendar;
import java.util.GregorianCalendar;


/**
 * Write a description of class Chat here.
 *
 * @author Groupe P
 * @version 25.04.2016
 */
public class Chat
{
    // instance variables - replace the example below with your own
    private String friend1;
    private String friend2;
    private String chatHistory;

    /**
     * Constructor for objects of class Chat
     */
    public Chat(String friend1, String friend2, String chatHistory){
        // initialise instance variables, mais on est pas sensé utiliser mais
        this.friend1 = friend1;
        this.friend2 = friend2;
        this.chatHistory = chatHistory;
    }

    public Chat(String friend1, String friend2, Context context) {
        this.friend1 = friend1;
        this.friend2 = friend2;
        FriendshipManager fM = new FriendshipManager(context);
        fM.open();
        String string = fM.getFriendshipStr(friend1, friend2).getChat();
        if(string == null){

            string = fM.getFriendshipStr(friend2, friend1).getChat();
            this.friend1 = friend2;
            this.friend2 = friend1;
        }
        fM.close();
    }

    public String getFriend1() {
        return friend1;
    }
    public String getFriend2() {
        return friend2;
    }


    public String getChatHistory() {
        return chatHistory;
    }

    public void setChatHistory(String chatHistory) {
        this.chatHistory = chatHistory;

    }
    public void sendMessage(String message, String friend, Context context){
        //TODO fucking date !
        String trueMsg = chatHistory + '['+ friend + ',';
        Calendar calendar = GregorianCalendar.getInstance();
        trueMsg+=calendar.get(Calendar.HOUR_OF_DAY)+":"+calendar.get(Calendar.MINUTE)+"] "+message+"\n";
        FriendshipManager fM = new FriendshipManager(context);
        fM.open();
        Friendship bla = new Friendship(friend1, friend2, chatHistory);
        fM.modFriendship(bla);
        fM.close();
    }
}