package lsinf1225.uclove;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ResearchSettingsActivity extends AppCompatActivity /*implements AdapterView.OnItemSelectedListener*/{

    private ImageButton yesOrNoButton, friendsRequestButton, friendsButton, profileButton, settingsButton;
    private CheckBox blackH, blondH, brownH,redH, otherH, blackE, blueE, brownE, greenE, match;
    private Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_research_settings);

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
        //////////////////FIN BOUTONS DE MENU///////////////////////
        blackH = (CheckBox) findViewById(R.id.BlackH);
        blondH = (CheckBox) findViewById(R.id.BlondH);
        brownH = (CheckBox) findViewById(R.id.BrownH);
        redH = (CheckBox) findViewById(R.id.RedH);
        otherH = (CheckBox) findViewById(R.id.OtherH);
        blackE = (CheckBox) findViewById(R.id.BlackE);
        brownE = (CheckBox) findViewById(R.id.BrownE);
        blueE = (CheckBox) findViewById(R.id.BlueE);
        greenE = (CheckBox) findViewById(R.id.GreenE);
        match = (CheckBox) findViewById(R.id.Match);
        save = (Button) findViewById(R.id.Save);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean hair[] = {blackH.isChecked(), blondH.isChecked(), brownH.isChecked(), redH.isChecked(), otherH.isChecked()};
                boolean eyes[] = {blackE.isChecked(), blueE.isChecked(), brownE.isChecked(), greenE.isChecked()};
                boolean same = match.isChecked();
                Favorite fav = new Favorite(hair, eyes, same, 0, 100);
                MyApplication.getUser().setFavorite(fav);

                Toast.makeText(ResearchSettingsActivity.this, "Preferences saved", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(v.getContext(), YesOrNoActivity.class);
                Toast.makeText(v.getContext(), "Redirection...", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
    }
}
