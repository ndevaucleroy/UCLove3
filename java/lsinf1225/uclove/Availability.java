package lsinf1225.uclove;

import android.content.Context;

import java.util.ArrayList;

/**
 * Classe Availability lie un login et une date pour lequel le login est libre. 
 * Created by Guillaume on 29/04/16.
 */
public class Availability {
    private String login;
    private String date;

    // Constructeur basic qui initialise le login et la date.
    public Availability(String login, String date) {
        this.login = login;
        this.date = date;
    }

    public Availability(){

    }
    // Les fonctions get et set pour avoir acces aux variables et pour pouvoir les modifier
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getDate(){
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    // Appel la methode addAvailability de AvailabilityManager
    public void addAvailability(User user, String date, Context context) {
        AvailabilityManager aM = new AvailabilityManager(context);
        aM.open();
        aM.addAvailability(new Availability(user.getLoginStr(),date));
        aM.close();
    }

    // Appel la methode getSameAvailability de AvailabilityManager
    public ArrayList<String> getSameAvailability(User user1, User user2, Context context) {
        AvailabilityManager aM = new AvailabilityManager(context);
        aM.open();
        ArrayList<String> same = aM.getSameAvailability(user1,user2);
        aM.close();
        return same;
    }
}
