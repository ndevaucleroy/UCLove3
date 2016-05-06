package lsinf1225.uclove;

import android.app.AlertDialog;
import android.app.Application;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class FriendProfileActivity extends AppCompatActivity {

    TextView firstNameView, nameView, placeView, birthdayView, descriptionView, orientationView, headTextView;
    private Button chat, rating, propose;
    private ImageButton yesOrNoButton, friendsRequestButton, friendsButton, profileButton, settingsButton;
    private Spinner date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_profile);

        rating = (Button) findViewById(R.id.rating);
        rating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRate(); //lance la boite de dialogue de cotation
            }
        });

        chat = (Button) findViewById(R.id.chat);
        //lance  la discussion quand clique
        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DiscussionActivity.class);
                startActivity(intent);
            }
        });
        // spiner montrant les disponibilite communes
        date = (Spinner) findViewById(R.id.date);
        final AvailabilityManager aM = new AvailabilityManager(this);
        aM.open();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, aM.getSameAvailability(MyApplication.getUser(), MyApplication.getUserChat()));
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        date.setAdapter(dataAdapter);
        // fin du set du spinner
        
        //bouton creant un rendez-vous selon la date selectionnee
        propose = (Button) findViewById(R.id.propose);
        propose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RendezVousManager rV = new RendezVousManager(v.getContext());
                rV.open();
                rV.addRendezVous(new RendezVous (MyApplication.getUser().getLoginStr(), MyApplication.getUserChat().getLoginStr(), date.getSelectedItem().toString()));
                rV.close();
            }
        });
        aM.close();

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
                Intent intent = new Intent(v.getContext(), ProfileActivity.class);
                startActivity(intent); } });
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SettingsActivity.class);
                startActivity(intent); } });
        ////////////////////FIN BOUTONS DE MENU///////////////////////

        try{
            firstNameView = (TextView) findViewById(R.id.firstName);
            nameView = (TextView) findViewById(R.id.name);
            placeView = (TextView) findViewById(R.id.place);
            birthdayView = (TextView) findViewById(R.id.birthday);
            descriptionView = (TextView) findViewById(R.id.description);
            orientationView = (TextView) findViewById(R.id.orientation);
            headTextView= (TextView) findViewById(R.id.headText);

            firstNameView.setText(MyApplication.getUserChat().getFirstNameStr());
            nameView.setText(MyApplication.getUserChat().getNameStr());
            placeView.setText(MyApplication.getUserChat().getPlaceStr());
            birthdayView.setText(MyApplication.getUserChat().getBirthdayStr());
            descriptionView.setText(MyApplication.getUserChat().getDescriptionStr());
            orientationView.setText(MyApplication.getUserChat().getOrientationStr());
            headTextView.setText(MyApplication.getUserChat().getFirstNameStr()+" "+MyApplication.getUserChat().getNameStr());
        }catch(Exception e){
            Toast.makeText(FriendProfileActivity.this, "In FriendProfileActivity: error "+ e.getMessage(), Toast.LENGTH_SHORT).show();
        }


    }
    //cree la boite de dialogue de cotation
    public void showRate(){
        final AlertDialog.Builder rateD = new AlertDialog.Builder(this);
        final RatingBar rate = new RatingBar(this);
        LinearLayout wid = new LinearLayout(FriendProfileActivity.this);
        wid.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        //initialisation de la ratebare
        rate.setMax(5);
        rate.setNumStars(5);
        rate.setStepSize(1);
        rateD.setTitle("Rating");
        rateD.setMessage("Do you want to rate " + MyApplication.getUserChat().getNameStr());
        rateD.setView(wid);
        wid.addView(rate);
        //valide la cotation
        rateD.setPositiveButton("Rate",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Score score = new Score(MyApplication.getUser().getLoginStr(), MyApplication.getUserChat().getLoginStr(), rate.getProgress());
                        Score.addScore(MyApplication.getUser(), MyApplication.getUserChat(), rate.getProgress(), FriendProfileActivity.this); //modifie la BDD avec la note
                        Toast.makeText(FriendProfileActivity.this, "Rating : " + String.valueOf(rate.getRating()), Toast.LENGTH_LONG).show(); //retour visuel
                        dialog.dismiss();
                    }
                });

        //annule la cotation
        rateD.setNegativeButton("Nope",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        rateD.create();
        rateD.show();
    }
}
