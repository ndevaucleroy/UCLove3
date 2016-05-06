package lsinf1225.uclove;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
//TODO: image
public class FriendsRequestActivity extends AppCompatActivity {

    private ImageButton yesOrNoButton, friendsRequestButton, friendsButton, profileButton,
            settingsButton, otherProfileButton, yesButton, noButton;
    private TextView notFound, nameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_request);

        notFound= (TextView) findViewById(R.id.noFound);
        notFound.setVisibility(View.GONE);
        nameView = (TextView) findViewById(R.id.firstName);
        otherProfileButton = (ImageButton) findViewById(R.id.otherProfile);
        yesButton = (ImageButton) findViewById(R.id.yes);
        noButton = (ImageButton) findViewById(R.id.no);

        //////////////////BOUTONS DE MENU///////////////////////
        yesOrNoButton = (ImageButton) findViewById(R.id.YesOrNo);
        friendsRequestButton = (ImageButton) findViewById(R.id.FriendsRequests);
        friendsButton = (ImageButton) findViewById(R.id.Friends);
        profileButton = (ImageButton) findViewById(R.id.Profile);
        settingsButton = (ImageButton) findViewById(R.id.Settings);

        yesOrNoButton.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Toast.makeText(FriendsRequestActivity.this, "Refreshing...", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(v.getContext(), YesOrNoActivity.class);
                startActivity(intent);}});
        friendsRequestButton.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), FriendsRequestActivity.class);
                startActivity(intent); } });
        friendsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ListOfChatsActivity.class);
                startActivity(intent);
            }
        });
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ProfileActivity.class);
                startActivity(intent);
            }
        });
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SettingsActivity.class);
                startActivity(intent); } });
        /////////////////FIN BOUTONS DE MENU///////////////////////


        //Trouver un user ayant envoye un request
        MyApplication.setfriendRequestList(MyApplication.getUser(), this);
        if(MyApplication.getFriendRequestList().size() != 0) {
            try{
                MyApplication.setOtherUserR(MyApplication.getFriendRequestList().get(MyApplication.getPositionInFriendRequestList()));
                MyApplication.setUserToDisplayR(MyApplication.getOtherUserR());
                //TODO: display photo
                nameView.setText(MyApplication.getOtherUserR().getFirstNameStr());
                otherProfileButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(v.getContext(), FReqProfileActivity.class);
                        startActivity(intent);
                    }
                });
                yesButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            MyApplication.getUser().getFriends().acceptFriendRequest(MyApplication.getOtherUserR(), FriendsRequestActivity.this);
                            MyApplication.setPositionInFriendRequestList(MyApplication.getPositionInFriendRequestList() + 1);
                            Intent intent = new Intent(v.getContext(), FriendsRequestActivity.class);
                            startActivity(intent);    }
                });
                noButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            MyApplication.getUser().getFriends().refuseFriendRequest(MyApplication.getOtherUserR(), FriendsRequestActivity.this);
                            MyApplication.setPositionInFriendRequestList(MyApplication.getPositionInFriendRequestList()+1);
                            Intent intent = new Intent(v.getContext(), FriendsRequestActivity.class);
                            startActivity(intent);
                        }
                });
                    nameView.setText(MyApplication.getOtherUserR().getFirstNameStr());
            }catch(Exception e){
                Toast.makeText(FriendsRequestActivity.this, "Error in FriendsRequestActivity : " +e.getMessage(), Toast.LENGTH_SHORT).show();
                //display message saying "No more requests"
                notFound.setVisibility(View.VISIBLE);
                //hide other buttons and textviews
                otherProfileButton.setVisibility(View.GONE);
                yesButton.setVisibility(View.GONE);
                noButton.setVisibility(View.GONE);
            }
        }
        else{
            Toast.makeText(FriendsRequestActivity.this, "Error in FriendsRequestActivity : " , Toast.LENGTH_SHORT).show();
            //display message saying "No more requests"
            notFound.setVisibility(View.VISIBLE);
            //hide other buttons and textviews
            otherProfileButton.setVisibility(View.GONE);
            yesButton.setVisibility(View.GONE);
            noButton.setVisibility(View.GONE);
        }
    }
}
