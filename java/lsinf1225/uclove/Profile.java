package lsinf1225.uclove;


/**
 * Write a description of class Profil here.
 *
 * @author Groupe P
 * @version 25.04.2016
 */
public class Profile
{
    // instance variables - replace the example below with your own
    private String loginStr, firstNameStr, nameStr, placeStr, birthdayStr, languageStr,
            hairStr, eyesStr, descriptionStr, genderStr, orientationStr;

    /**
     * Constructor for objects of class Profil
     */
    public Profile(String loginStr, String  firstNameStr, String  nameStr, String  placeStr,
                   String birthdayStr, String languageStr, String hairStr, String  eyesStr,
                   String descriptionStr, String genderStr, String  orientationStr)
    {
        // initialise instance variables
        this.loginStr = loginStr;
        this.firstNameStr = firstNameStr;
        this.nameStr = nameStr;
        this.placeStr = placeStr;
        this.birthdayStr = birthdayStr;
        this.languageStr = languageStr;
        this.hairStr = hairStr;
        this.eyesStr = eyesStr;
        this.descriptionStr = descriptionStr;
        this.genderStr = genderStr;
        this.orientationStr = orientationStr;
    }

    public Profile(){}

    public String getLoginStr() {
        return loginStr;
    }

    public void setLoginStr(String loginStr) {
        this.loginStr = loginStr;
    }

    public String getFirstNameStr() {
        return firstNameStr;
    }

    public void setFirstNameStr(String firstNameStr) {
        this.firstNameStr = firstNameStr;
    }

    public String getNameStr() {
        return nameStr;
    }

    public void setNameStr(String nameStr) {
        this.nameStr = nameStr;
    }

    public String getPlaceStr() {
        return placeStr;
    }

    public void setPlaceStr(String placeStr) {
        this.placeStr = placeStr;
    }

    public String getBirthdayStr() {
        return birthdayStr;
    }

    public void setBirthdayStr(String birthdayStr) {
        this.birthdayStr = birthdayStr;
    }

    public String getLanguageStr() {
        return languageStr;
    }

    public void setLanguageStr(String languageStr) {
        this.languageStr = languageStr;
    }

    public String getHairStr() {
        return hairStr;
    }

    public void setHairStr(String hairStr) {
        this.hairStr = hairStr;
    }

    public String getEyesStr() {
        return eyesStr;
    }

    public void setEyesStr(String eyesStr) {
        this.eyesStr = eyesStr;
    }

    public String getDescriptionStr() {
        return descriptionStr;
    }

    public void setDescriptionStr(String descriptionStr) {
        this.descriptionStr = descriptionStr;
    }

    public String getGenderStr() {
        return genderStr;
    }

    public void setGenderStr(String genderStr) {
        this.genderStr = genderStr;
    }

    public String getOrientationStr() {
        return orientationStr;
    }

    public void setOrientationStr(String orientationStr) {
        this.orientationStr = orientationStr;
    }
}
