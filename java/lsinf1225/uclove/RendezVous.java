package lsinf1225.uclove;

/**
 * Pour pouvoir se rencontrer <3
 * Created by Guillaume on 29/04/16.
 */
public class RendezVous {
    private String login1;
    private String login2;
    private String meeting;

    public RendezVous(String login1, String login2, String meeting) {
        this.login1 = login1;
        this.login2 = login2;
        this.meeting = meeting;
    }

    public RendezVous() {

    }

    public String getLogin1() {
        return login1;
    }

    public void setLogin1(String login1) {
        this.login1 = login1;
    }

    public String getLogin2() {
        return login2;
    }

    public void setLogin2(String login2) {
        this.login2 = login2;
    }

    public String getMeeting() {
        return meeting;
    }

    public void setMeeting(String meeting) {
        this.meeting = meeting;
    }
}