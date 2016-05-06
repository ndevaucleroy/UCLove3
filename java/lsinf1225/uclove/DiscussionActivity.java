package lsinf1225.uclove;

import android.content.Intent;
import android.os.Bundle;
//import android.support.design.widget.FloatingActionButton;
//import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Calendar;
import java.util.Date;

public class DiscussionActivity extends AppCompatActivity {

    private ImageButton yesOrNoButton, friendsRequestButton, friendsButton, profileButton, settingsButton;
    private ImageButton sendButton, friendPic;
    private EditText msg;
    private TextView historic, headText;
    private ScrollView scroll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discussion);
        headText = (TextView) findViewById(R.id.headText);
        headText.setText(MyApplication.getUserChat().getFirstNameStr());
        historic = (TextView) findViewById(R.id.hist);
        FriendshipManager fM = new FriendshipManager(this);
        fM.open();
        historic.setText(fM.getChatHistory(MyApplication.getUser(), MyApplication.getUserChat()));
        fM.close();
        friendPic = (ImageButton) findViewById(R.id.friendPic);
        scroll = (ScrollView) findViewById(R.id.scroll);
        scroll.fullScroll(View.FOCUS_DOWN);

        friendPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), FriendProfileActivity.class);
                startActivity(intent);
            }
        });

        //////////////////BOUTONS DE MENU///////////////////////
        yesOrNoButton = (ImageButton) findViewById(R.id.YesOrNo);
        friendsRequestButton = (ImageButton) findViewById(R.id.FriendsRequests);
        friendsButton = (ImageButton) findViewById(R.id.Friends);
        profileButton = (ImageButton) findViewById(R.id.Profile);
        settingsButton = (ImageButton) findViewById(R.id.Settings);

        yesOrNoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), YesOrNoActivity.class);
                startActivity(intent);
            }
        });
        friendsRequestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), FriendsRequestActivity.class);
                startActivity(intent);
            }
        });
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
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SettingsActivity.class);
                startActivity(intent);
            }
        });
        /////////////////FIN BOUTONS DE MENU//////////////////////

        sendButton = (ImageButton) findViewById(R.id.send);

        msg = (EditText) findViewById(R.id.msg);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!msg.getText().toString().equals("")) {
                    FriendshipManager fM = new FriendshipManager(v.getContext());
                    fM.open();
                    String newHistory = historic.getText().toString();
                    Calendar calendar = GregorianCalendar.getInstance();
                    newHistory += "[" + MyApplication.getUser().getFirstNameStr() + ", " + calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE) + "] " + msg.getText().toString() + "\n";
                    Friendship fs = new Friendship(MyApplication.getUser().getLoginStr(), MyApplication.getUserChat().getLoginStr(), newHistory);
                    fM.modFriendship(fs);
                    fs = new Friendship(MyApplication.getUserChat().getLoginStr(), MyApplication.getUser().getLoginStr(), newHistory);
                    fM.modFriendship(fs);
                    fM.close();
                    historic.setText(newHistory);
                    msg.setText("");
                    scroll.fullScroll(View.FOCUS_DOWN);
                }
            }
        });
    }
}