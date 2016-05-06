package lsinf1225.uclove;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class ProfileSettingsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private ImageButton yesOrNoButton, friendsRequestButton, friendsButton, profileButton, settingsButton;
    private ImageButton saveNameButton, saveFirstNameButton;
    private ImageButton saveBirthButton;
    private EditText nameView, firstNameView;
    private Button description, avaibility,editAlbumButton;
    private String day, month, year;
    private static final String VALID_NAME = "[a-zA-Z]{2,15}$";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_settings);

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
        /////////////////FIN BOUTONS DE MENU//////////////////////

        day= "1";
        month = "January";
        year="1998";
        description = (Button) findViewById(R.id.editDescription);
        description.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDescription();
            }
        });
        saveNameButton = (ImageButton) findViewById(R.id.confirm1);
        saveFirstNameButton = (ImageButton) findViewById(R.id.confirm2);
        saveBirthButton = (ImageButton) findViewById(R.id.confirm5);
        avaibility = (Button) findViewById(R.id.avaibility);
        editAlbumButton = (Button) findViewById(R.id.album);


        nameView = (EditText) findViewById(R.id.editName);

        editAlbumButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AlbumActivity.class);
                startActivity(intent);
            }
        });

        avaibility.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DisponibilityActivity.class);
                startActivity(intent);
            }
        });
        firstNameView = (EditText) findViewById(R.id.editFirstName);

        saveNameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tryChangeName(nameView.getText().toString());
            }
        });
        saveFirstNameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tryChangeFirstName(firstNameView.getText().toString());
            }
        });
        saveBirthButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyApplication.getUser().setBirthdayStr(year + "-" + User.monthToInt(month) + "-" + day);
                Toast.makeText(ProfileSettingsActivity.this, "Birth saved to: " + MyApplication.getUser().getBirthdayStr(), Toast.LENGTH_SHORT).show();
            }
        });

        //Initializing spinners
        Spinner pspinner = (Spinner) findViewById(R.id.editPlace);
        pspinner.setOnItemSelectedListener(this);
        List<String> pcategories = new ArrayList();//c'est moi qui ai viré le <String> pour voir
        pcategories.add("Anvers");
        pcategories.add("Brabant Wallon");
        pcategories.add("Brabant Flamand");
        pcategories.add("Région de Bruxelles-Capitale");
        pcategories.add("Flandre Occidentale");
        pcategories.add("Flandre Orientale");
        pcategories.add("Hainaut");
        pcategories.add("Liège");
        pcategories.add("Province du Luxembourg");
        pcategories.add("Namur");
        ArrayAdapter<String> pdataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, pcategories);
        pdataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pspinner.setAdapter(pdataAdapter);
        if(MyApplication.getUser().getPlaceStr().equals("Brabant Wallon"))
            pspinner.setSelection(1);//2
        if(MyApplication.getUser().getPlaceStr().equals("Brabant Flamand"))
            pspinner.setSelection(2);//3
        if(MyApplication.getUser().getPlaceStr().equals("Région de Bruxelles-Capitale"))
            pspinner.setSelection(3);//4
        if(MyApplication.getUser().getPlaceStr().equals("Flandre Occidentale"))
            pspinner.setSelection(4);//5
        if(MyApplication.getUser().getPlaceStr().equals("Flandre Orientale"))
            pspinner.setSelection(5);//6
        if(MyApplication.getUser().getPlaceStr().equals("Hainaut"))
            pspinner.setSelection(6);//7
        if(MyApplication.getUser().getPlaceStr().equals("Liège"))
            pspinner.setSelection(7);//8
        if(MyApplication.getUser().getPlaceStr().equals("Province du Luxembourg"))
            pspinner.setSelection(8);//9
        if(MyApplication.getUser().getPlaceStr().equals("Namur"))
            pspinner.setSelection(9);//10

        Spinner spinner = (Spinner) findViewById(R.id.editOrientation);
        spinner.setOnItemSelectedListener(this);
        List<String> categories = new ArrayList();
        categories.add("Men");
        categories.add("Women");
        categories.add("Both");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        if(MyApplication.getUser().getOrientationStr().equals("M"))
            spinner.setSelection(0); //avant c'était 1
        if(MyApplication.getUser().getOrientationStr().equals("F"))
            spinner.setSelection(1); //avant c'était 2
        if(MyApplication.getUser().getOrientationStr().equals("B"))
            spinner.setSelection(2); //avant c'estait 3

        Spinner hspinner = (Spinner) findViewById(R.id.editHair);
        hspinner.setOnItemSelectedListener(this);
        List<String> hcategories = new ArrayList/*<String>*/();
        hcategories.add("Black");
        hcategories.add("Blond");
        hcategories.add("Brown");
        hcategories.add("Red");
        hcategories.add("Other");
        ArrayAdapter<String> hdataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, hcategories);
        hdataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hspinner.setAdapter(hdataAdapter);
        if(MyApplication.getUser().getHairStr().equals("black"))
            hspinner.setSelection(0);//2
        if(MyApplication.getUser().getHairStr().equals("blond"))
            hspinner.setSelection(1);//2
        if(MyApplication.getUser().getHairStr().equals("brown"))
            hspinner.setSelection(2);//3
        if(MyApplication.getUser().getHairStr().equals("red"))
            hspinner.setSelection(3);//4
        if(MyApplication.getUser().getHairStr().equals("other"))
            hspinner.setSelection(4);//5

        Spinner espinner = (Spinner) findViewById(R.id.editEyes);
        espinner.setOnItemSelectedListener(this);
        List<String> ecategories = new ArrayList<String>();
        ecategories.add("Black");
        ecategories.add("Blue");
        ecategories.add("Brown");
        ecategories.add("Green");
        ArrayAdapter<String> edataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ecategories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        espinner.setAdapter(edataAdapter);
        if(MyApplication.getUser().getEyesStr().equals("black"))
            espinner.setSelection(0);
        if(MyApplication.getUser().getEyesStr().equals("blue"))
            espinner.setSelection(1);
        if(MyApplication.getUser().getEyesStr().equals("brown"))
            espinner.setSelection(2);
        if(MyApplication.getUser().getEyesStr().equals("green"))
            espinner.setSelection(3);

        Spinner bdspinner = (Spinner) findViewById(R.id.xday);
        bdspinner.setOnItemSelectedListener(this);

        Spinner bmspinner = (Spinner) findViewById(R.id.xmonth);
        bmspinner.setOnItemSelectedListener(this);

        Spinner byspinner = (Spinner) findViewById(R.id.xyear);
        byspinner.setOnItemSelectedListener(this);


    }

    private void tryChangeName(String change){
        setErrorsToNull();
        if (TextUtils.isEmpty(change)) {
            sendError(nameView, "Error field required: please fill in the field");
            return;
        }
        else if(!isValid(change)){
            sendError(nameView, "Error invalid name: please use only letters and capitals");
            return;
        }
        else
            changeName(change);
    }
    private void tryChangeFirstName(String change){
        setErrorsToNull();
        if (TextUtils.isEmpty(change)) {
            sendError(firstNameView, "Error field required: please fill in the field");
            return;
        }
        else if(!isValid(change)){
            sendError(firstNameView, "Error invalid name: please use only letters and capitals");
            return;
        }
        else
            changeFirstName(change);
    }

    private void changeName(String change){
        MyApplication.getUser().setNameStr(change);
        if (MyApplication.getUser().getNameStr().equals(nameView.getText().toString()))
            Toast.makeText(ProfileSettingsActivity.this, "Name successfully saved!", Toast.LENGTH_SHORT).show();
    }
    private void changeFirstName(String change){
        MyApplication.getUser().setFirstNameStr(change);
        if(MyApplication.getUser().getFirstNameStr().equals(firstNameView.getText().toString())){
            Toast.makeText(ProfileSettingsActivity.this, "First name successfully saved!", Toast.LENGTH_SHORT).show();
        }
    }

    private void sendError(EditText field, String message) {
        field.setError(message);
        field.requestFocus();
    }
    private void setErrorsToNull() {
        nameView.setError(null);
        firstNameView.setError(null);
    }
    private boolean isValid(String username) {
        return Pattern.compile("^" + VALID_NAME + "{3,20}$").matcher(username).matches();
    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        // On selecting a spinner item
        switch(parent.getId()){
            case R.id.editPlace:
                setPlace(pos);
                break;
            case R.id.editOrientation :
                setOrientation(pos);
                break;
            case R.id.editHair :
                setHair(pos);
                break;
            case R.id.editEyes :
                setEyes(pos);
                break;
            case R.id.xday:
                day = parent.getSelectedItem().toString();
                break;
            case R.id.xmonth:
                month=parent.getSelectedItem().toString();
                break;
            case R.id.xyear:
                year= parent.getSelectedItem().toString();
                break;
        }
        MyApplication.getUser().updateDatabase(this);
    }
    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

    public void setOrientation(int pos){
        try{
            switch(pos){
                case 0 :
                    MyApplication.getUser().setOrientationStr("M");
                    break;
                case 1 :
                    MyApplication.getUser().setOrientationStr("F");
                    break;
                case 2 :
                    MyApplication.getUser().setOrientationStr("B");
                    break;
            }
            Toast.makeText(ProfileSettingsActivity.this, "Now " + orientationToWord(MyApplication.getUser().getOrientationStr()), Toast.LENGTH_SHORT).show();
        }
        catch(Exception e){
            Toast.makeText(ProfileSettingsActivity.this, "Error in setorientation! "+ e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        MyApplication.getUser().updateDatabase(this);

    }
    public void setPlace(int pos){
        try{
            switch(pos){
                case 0 :
                    MyApplication.getUser().setPlaceStr("Anvers");
                    break;
                case 1 :
                    MyApplication.getUser().setPlaceStr("Brabant Wallon");
                    break;
                case 2 :
                    MyApplication.getUser().setPlaceStr("Brabant Flamand");
                    break;
                case 3 :
                    MyApplication.getUser().setPlaceStr("Région de Bruxelles-Capitale");
                    break;
                case 4 :
                    MyApplication.getUser().setPlaceStr("Flandre Occidentale");
                    break;
                case 5 :
                    MyApplication.getUser().setPlaceStr("Flandre Orientale");
                    break;
                case 6 :
                    MyApplication.getUser().setPlaceStr("Hainaut");
                    break;
                case 7 :
                    MyApplication.getUser().setPlaceStr("Liège");
                    break;
                case 8 :
                    MyApplication.getUser().setPlaceStr("Province du Luxembourg");
                    break;
                case 9 :
                    MyApplication.getUser().setPlaceStr("Namur");
                    break;
            }

            Toast.makeText(ProfileSettingsActivity.this, "New location: " + MyApplication.getUser().getPlaceStr(), Toast.LENGTH_SHORT).show();
        }
        catch(Exception e){
            Toast.makeText(ProfileSettingsActivity.this, "Error in setHair! "+ e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        MyApplication.getUser().updateDatabase(this);
    }
    public void setHair(int pos){

        try{
            switch(pos){
                case 0 :
                    MyApplication.getUser().setHairStr("black");
                    break;
                case 1 :
                    MyApplication.getUser().setHairStr("blond");
                    break;
                case 2 :
                    MyApplication.getUser().setHairStr("brown");
                    break;
                case 3 :
                    MyApplication.getUser().setHairStr("red");
                    break;
                case 4 :
                    MyApplication.getUser().setHairStr("other");
                    break;
            }
            Toast.makeText(ProfileSettingsActivity.this, "New Hair Color: " + MyApplication.getUser().getHairStr(), Toast.LENGTH_SHORT).show();
        }
        catch(Exception e){
            Toast.makeText(ProfileSettingsActivity.this, "Error in setHair! "+ e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        MyApplication.getUser().updateDatabase(this);
    }
    public void setEyes(int pos){
        try{
            switch (pos){
                case 0 :
                    MyApplication.getUser().setEyesStr("black");
                    break;
                case 1 :
                    MyApplication.getUser().setEyesStr("blue");
                    break;
                case 2 :
                    MyApplication.getUser().setEyesStr("brown");
                    break;
                case 3 :
                    MyApplication.getUser().setEyesStr("green");
                    break;
            }
            Toast.makeText(ProfileSettingsActivity.this, "New Eyes Color: " + MyApplication.getUser().getEyesStr(), Toast.LENGTH_SHORT).show();
        }catch(Exception e){
            Toast.makeText(ProfileSettingsActivity.this, "Error in setEyes! "+ e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        MyApplication.getUser().updateDatabase(this);
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
    public void showDescription(){
        final AlertDialog.Builder descriptionU = new AlertDialog.Builder(this);
        final EditText myDescription = new EditText(this);
        LinearLayout wid = new LinearLayout(ProfileSettingsActivity.this);
        wid.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT));
        myDescription.setText(MyApplication.getUser().getDescriptionStr());
        descriptionU.setTitle("Edit Description");
        descriptionU.setMessage("Do you want to change your description ?");
        descriptionU.setView(wid);
        wid.addView(myDescription);
        descriptionU.setPositiveButton("Save",
                new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int which){
                        MyApplication.getUser().setDescriptionStr(myDescription.getText().toString());
                        Toast.makeText(ProfileSettingsActivity.this, "Description updated", Toast.LENGTH_LONG).show();
                        dialog.dismiss();
                    }
                });

        descriptionU.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id){
                        dialog.cancel();
                    }
                });

        descriptionU.create();
        descriptionU.show();
    }
}
