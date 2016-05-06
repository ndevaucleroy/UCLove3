package lsinf1225.uclove;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class FReqProfileActivity extends AppCompatActivity {

    TextView firstNameView, nameView, placeView, birthdayView, descriptionView, orientationView;
    private ImageButton yes, no;
    private ImageButton yesOrNoButton, friendsRequestButton, friendsButton, profileButton, settingsButton;
    private RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_freq_profile);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        ratingBar.setRating((float)Score.getAverage(MyApplication.getOtherUserYon(), this));


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

        firstNameView = (TextView) findViewById(R.id.firstName);
        nameView = (TextView) findViewById(R.id.name);
        placeView = (TextView) findViewById(R.id.place);
        birthdayView = (TextView) findViewById(R.id.birthday);
        descriptionView = (TextView) findViewById(R.id.description);
        orientationView = (TextView) findViewById(R.id.orientation);
        yes = (ImageButton) findViewById(R.id.yes);
        no = (ImageButton) findViewById(R.id.no);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyApplication.getUser().getFriends().sendFriendRequest(MyApplication.getOtherUserYon().getLoginStr(), v.getContext());
                MyApplication.setPositionInMatchingUsers(MyApplication.getPositionInMatchingUsers() + 1);
                Intent intent = new Intent(v.getContext(), YesOrNoActivity.class);
                startActivity(intent);
            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyApplication.setPositionInMatchingUsers(MyApplication.getPositionInMatchingUsers() + 1);
                Intent intent = new Intent(v.getContext(), YesOrNoActivity.class);
                startActivity(intent);
            }
        });

        firstNameView.setText(MyApplication.getOtherUserYon().getFirstNameStr());
        nameView.setText(MyApplication.getOtherUserYon().getNameStr());
        placeView.setText(MyApplication.getOtherUserYon().getPlaceStr());
        birthdayView.setText(MyApplication.getOtherUserYon().getBirthdayStr());
        descriptionView.setText(MyApplication.getOtherUserYon().getDescriptionStr());
        orientationView.setText(MyApplication.getOtherUserYon().getOrientationStr());

    }

}
