package lsinf1225.uclove;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

//TODO: image
//TODO: bouton vers modification du profil?

public class ProfileActivity extends AppCompatActivity {

    TextView firstNameView, nameView, placeView, birthdayView, descriptionView, orientationView,
        hairView, eyesView;
    User user;
    private ImageButton yesOrNoButton, friendsRequestButton, friendsButton, profileButton, settingsButton;
    private Button toProfileSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //////////////////BOUTONS DE MENU///////////////////////
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
                Toast.makeText(ProfileActivity.this, "Refreshing...", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(v.getContext(), ProfileActivity.class);
                startActivity(intent); } });
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SettingsActivity.class);
                startActivity(intent); } });
        ////////////////////FIN BOUTONS DE MENU///////////////////////

        toProfileSettings = (Button) findViewById(R.id.toprofilesettings);

        toProfileSettings.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ProfileSettingsActivity.class);
                startActivity(intent); } });

        try{
            firstNameView = (TextView) findViewById(R.id.firstName);
            nameView = (TextView) findViewById(R.id.name);
            placeView = (TextView) findViewById(R.id.place);
            birthdayView = (TextView) findViewById(R.id.birthday);
            descriptionView = (TextView) findViewById(R.id.description);
            orientationView = (TextView) findViewById(R.id.orientation);
            hairView= (TextView) findViewById(R.id.profhair);
            eyesView= (TextView) findViewById(R.id.profhair);

            firstNameView.setText("First Name: " +MyApplication.getUser().getFirstNameStr());
            nameView.setText("Name: " + MyApplication.getUser().getNameStr());
            placeView.setText("Lives in: " + MyApplication.getUser().getPlaceStr());
            birthdayView.setText("Birthday: "+MyApplication.getUser().getBirthdayStr());
            descriptionView.setText("\""+MyApplication.getUser().getDescriptionStr()+"\"");
            orientationView.setText(orientationToWord(MyApplication.getUser().getOrientationStr()));
            hairView.setText("Hair Color: " + MyApplication.getUser().getHairStr());
            eyesView.setText("Eyes Color: " + MyApplication.getUser().getEyesStr());
        }catch(Exception e){
            Toast.makeText(ProfileActivity.this, "In ProfileActivity: error "+e.getMessage(), Toast.LENGTH_SHORT).show();
        }


    }

    private String orientationToWord(String o){
        String ret="No orientation found";
        if (o.equals("M"))
                ret = "Interested in Men";
        if (o.equals("F"))
                ret = "Interested in Women";
        if (o.equals("B"))
                ret = "Interested in both women and men";
        return ret;
    }
}
