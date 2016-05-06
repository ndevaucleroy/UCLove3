package lsinf1225.uclove;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.graphics.Movie;
import java.util.ArrayList;

public class ListOfChatsActivity extends AppCompatActivity {

    private static int i;
    private static ArrayList<User> listOfFriends;
    private ImageButton yesOrNoButton, friendsRequestButton, friendsButton, profileButton, settingsButton;
    private ImageView noFriendsImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_chats);

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
            @Override
            public void onClick(View v) {
                Toast.makeText(ListOfChatsActivity.this, "Refreshing...", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(v.getContext(), ListOfChatsActivity.class);
                startActivity(intent);
            }
        });
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ProfileActivity.class);
                startActivity(intent); } });
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SettingsActivity.class);
                startActivity(intent); } });
        ///////////////////FIN BOUTONS DE MENU//////////////////////

        noFriendsImage = (ImageView) findViewById(R.id.nofriends);

        try{
            listOfFriends = MyApplication.getUser().getFriends().getFriendsUsr(this);

            final LinearLayout ll = (LinearLayout) findViewById(R.id.listoffriends);
            if (listOfFriends.size() != 0)
                noFriendsImage.setVisibility(View.GONE);

            for(i = 0 ; i<listOfFriends.size(); i++){
                try{
                    LinearLayout wid = new LinearLayout(ListOfChatsActivity.this);
                    wid.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

                    Button btn = new Button(ListOfChatsActivity.this);
                    LinearLayout.LayoutParams layoutparam = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                    layoutparam.setMargins(10, 5, 10, 5);
                    btn.setLayoutParams(layoutparam);
                    btn.setText("Chat with " + listOfFriends.get(i).getFirstNameStr() +" " + listOfFriends.get(i).getNameStr() + "!");
                    btn.setBackgroundColor(0xffe36899);
                    btn.setId(i);

                    wid.addView(btn);
                    ll.addView(wid);

                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            MyApplication.setUserChat(listOfFriends.get(v.getId()));
                            Intent intent = new Intent(v.getContext(), DiscussionActivity.class);
                            startActivity(intent);
                        }
                    });
                }
                catch(IndexOutOfBoundsException e){
                    break;
                }
            }
        }catch(Exception e){
            Toast.makeText(ListOfChatsActivity.this, "Error in ListOfChatsActivity : " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }


    }
}