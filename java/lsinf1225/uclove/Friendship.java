package lsinf1225.uclove;

/**
 * Created by cariamole on 29.04.16.
 */
public class Friendship {
    private String login1;
    private String login2;
    private String chat;

    public Friendship(String login1, String login2, String chat){
        this.login1=login1;
        this.login2=login2;
        this.chat=chat;
    }

    public Friendship(){}

    public String getLogin1(){
        return this.login1;
    }

    public String getLogin2(){
        return this.login2;
    }

    public String getChat(){
        return this.chat;
    }

    public void setLogin1(String s) {
        this.login1 =s;
    }

    public void setLogin2(String s) {
        this.login2 =s;
    }

    public void setChat(String s) {
        this.chat=s;
    }
}