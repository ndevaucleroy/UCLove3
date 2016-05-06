package lsinf1225.uclove;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class AppSettingsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private ImageButton yesOrNoButton, friendsRequestButton, friendsButton, profileButton, settingsButton;
    private ImageButton changeLanguageButton, changePasswordButton, changeThemeButton;
    private EditText passwordView, confirmPasswordView;
    
    //Mot de passe valide
    private static final String VALID_PASSWORD_CHAR = "[a-zA-Z0-9_-]{6,20}$";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_settings);

        //////////////////BOUTONS DE MENU/////////////////////////
        yesOrNoButton = (ImageButton) findViewById(R.id.YesOrNo);
        friendsRequestButton = (ImageButton) findViewById(R.id.FriendsRequests);
        friendsButton = (ImageButton) findViewById(R.id.Friends);
        profileButton = (ImageButton) findViewById(R.id.Profile);
        settingsButton = (ImageButton) findViewById(R.id.Settings);

        yesOrNoButton.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), YesOrNoActivity.class);
                startActivity(intent);}});
        friendsRequestButton.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), FriendsRequestActivity.class);
                startActivity(intent); } });
        friendsButton.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ListOfChatsActivity.class);
                startActivity(intent); } });
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ProfileActivity.class);
                startActivity(intent); } });
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SettingsActivity.class);
                startActivity(intent); } });
        ///////////////////FIN BOUNTONS DE MENU///////////////////
        
        //assignation des boutons aux id correspondant
        changeLanguageButton = (ImageButton) findViewById(R.id.confirm1);
        changePasswordButton = (ImageButton) findViewById(R.id.confirm3);
        changeThemeButton = (ImageButton) findViewById(R.id.confirm4);
        passwordView = (EditText) findViewById(R.id.changePassword);
        confirmPasswordView = (EditText) findViewById(R.id.confirmChangePassword);
        
        changeLanguageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tryChangePassword(passwordView.getText().toString(), confirmPasswordView.getText().toString());
            }
        });
        
        //Spinner Language
        Spinner spinnerLanguage = (Spinner) findViewById(R.id.languageSpinner);
        spinnerLanguage.setOnItemSelectedListener(this);
        List<String> categoriesLanguage = new ArrayList<String>();
        categoriesLanguage.add("English");
        categoriesLanguage.add("Français");
        ArrayAdapter<String> dataAdapterLanguage = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categoriesLanguage);
        dataAdapterLanguage.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLanguage.setAdapter(dataAdapterLanguage);
        if(MyApplication.getUser().getLanguageStr().equals("Français"))
            spinnerLanguage.setSelection(2);


        /*//Spinner Theme
        Spinner spinnerTheme = (Spinner) findViewById(R.id.themeSpinner);
        spinnerTheme.setOnItemSelectedListener(this);
        List<String> categoriesTheme = new ArrayList<String>();
        categoriesTheme.add("Black");
        categoriesTheme.add("Pink");
        categoriesTheme.add("Blue");
        ArrayAdapter<String> dataAdapterTheme = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categoriesTheme);
        dataAdapterTheme.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTheme.setAdapter(dataAdapterTheme);*/

    }

    private void tryChangePassword(String passwordStr, String confirmPasswordStr){
        //reinitialisation des erreurs
        setErrorsToNull();
        //si le mot de passe est incorrect
        if(!passwordStr.equals(confirmPasswordStr)){
            sendError(confirmPasswordView,"Passwords don't match");
            return;
        }
        //si mot de passe non valide
        if(!isPasswordValid(passwordStr)){
            sendError(passwordView,"Invalid password: password can only contain letters and numbers");
            sendError(confirmPasswordView,"Invalid password: password can only contain letters and numbers");
            return;
        }
        //si mot de passe correct
        changePassword(passwordStr);
    }
    
    //change le mot de passe du user dans la base de donnees
    private void changePassword(String newPassword){
        MyApplication.getUser().setPasswordStr(newPassword);
        UserManager uM=new UserManager(this);
        uM.open();
        uM.modUser(MyApplication.getUser());
        uM.close();
    }
    
    private boolean isPasswordValid(String password) {
        return Pattern.compile("^" + VALID_PASSWORD_CHAR + "{5,50}$").matcher(password).matches();
    }
    private void sendError(EditText field, String message) {
        field.setError(message);
        field.requestFocus();
    }
    private void setErrorsToNull() {
        passwordView.setError(null);
        confirmPasswordView.setError(null);
    }
    
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        // On selecting a spinner item
        String item;

        switch(parent.getId()) {
            case R.id.languageSpinner:
                item = parent.getItemAtPosition(pos).toString();
                MyApplication.getUser().setLanguageStr(item);
                Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
                break;
            /*case R.id.themeSpinner:
                item = parent.getItemAtPosition(pos).toString();
                MyApplication.getUser().setThemeStr(item);
                Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
                break;*/
        }
        UserManager uM=new UserManager(this);
        uM.open();
        uM.modUser(MyApplication.getUser());
        uM.close();
    }

    public void onNothingSelected(AdapterView<?> parent) { }

}
