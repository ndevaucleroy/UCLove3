package lsinf1225.uclove;

/**
 * Classe liant un login et une photo qu'il a rajouter.
 *
 * @author Groupe P
 * @version 25.04.2016
 */
public class Album {

    private String login;
    private String picture;

    // Constructeur
    public Album(String login, String picture) {
        this.login=login;
        this.picture=picture;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

}