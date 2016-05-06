package lsinf1225.uclove;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;



public class DisponibilityActivity extends AppCompatActivity {
    private Button save;
    private ImageButton yesOrNoButton, friendsRequestButton, friendsButton, profileButton, settingsButton;
    private CheckBox[] dates = new CheckBox [30]; // un mois de dates possibles



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disponibility);
        Calendar calendar = GregorianCalendar.getInstance(); //permet d'obtenir les dates
        final User user=MyApplication.getUser();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        final AvailabilityManager aM = new AvailabilityManager (this);
        aM.open();
        // mets les 30 checkbox aux 30 prochains jours et les coche si l'on a deja precise sa disponibilite
        for (int i = 0; i < 30; i++)
        {
            int resID = getResources().getIdentifier("c"+i, "id", getPackageName());
            dates[i] = (CheckBox) findViewById(resID);
            String date = format.format(calendar.getTime());
            dates[i].setText(date);
            dates[i].setChecked(aM.isAvailable(user, date));
            calendar.add(Calendar.DATE,1);
        }
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
        save =(Button) findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aM.open();
                for(int i =0; i<30; i++) {
                    //verifie si la checkbox est coche, se met disponible dans la BDD si oui et indisponible sinon
                    if (dates[i].isChecked()) { 
                        aM.addAvailability(new Availability(user.getLoginStr(), dates[i].getText().toString()));
                    } else {
                        aM.supAvailability(new Availability(user.getLoginStr(), dates[i].getText().toString()));
                    }
                }
                aM.close();
                Toast.makeText(DisponibilityActivity.this, "Availabilities Saved", Toast.LENGTH_LONG).show();
                //retour a profil
                Intent intent = new Intent(v.getContext(), ProfileActivity.class);
                startActivity(intent);
            }
        });
    }
}
