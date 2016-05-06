package lsinf1225.uclove;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class StartupActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private Button proceedButton;
    private EditText nameView, firstNameView;
    private String day, month, year;
    private String strName, strFirstName, strPlace, strOrientation, strHair, strEyes,
            strGender;
    private static final String VALID_NAME = "[a-zA-Z]{2,15}$";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup);
        day= "1";
        month = "January";
        year="1998";
        proceedButton =(Button) findViewById(R.id.proceed);
        nameView = (EditText) findViewById(R.id.seditName);
        firstNameView = (EditText) findViewById(R.id.seditFirstName);

        //Spinners
        Spinner ospinner = (Spinner) findViewById(R.id.setOrientation);
        ospinner.setOnItemSelectedListener(this);
        List<String> ocategories = new ArrayList<String>();
        ocategories.add("Men");
        ocategories.add("Women");
        ocategories.add("Both");
        ArrayAdapter<String> odataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ocategories);
        odataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ospinner.setAdapter(odataAdapter);

        Spinner pspinner = (Spinner) findViewById(R.id.seditPlace);
        pspinner.setOnItemSelectedListener(this);
        List<String> pcategories = new ArrayList();
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

        Spinner hspinner = (Spinner) findViewById(R.id.setHair);
        hspinner.setOnItemSelectedListener(this);
        List<String> hcategories = new ArrayList<String>();
        hcategories.add("Black");
        hcategories.add("Blond");
        hcategories.add("Brown");
        hcategories.add("Red");
        hcategories.add("Other");
        ArrayAdapter<String> hdataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, hcategories);
        hdataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hspinner.setAdapter(hdataAdapter);

        Spinner espinner = (Spinner) findViewById(R.id.setEyes);
        espinner.setOnItemSelectedListener(this);
        List<String> ecategories = new ArrayList<String>();
        ecategories.add("Black");
        ecategories.add("Blue");
        ecategories.add("Brown");
        ecategories.add("Green");
        ArrayAdapter<String> edataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ecategories);
        edataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        espinner.setAdapter(edataAdapter);

        Spinner gspinner = (Spinner) findViewById(R.id.setGender);
        gspinner.setOnItemSelectedListener(this);
        List<String> gcategories = new ArrayList<String>();
        gcategories.add("M");
        gcategories.add("F");
        ArrayAdapter<String> gdataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, gcategories);
        gdataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gspinner.setAdapter(gdataAdapter);

        Spinner bdspinner = (Spinner) findViewById(R.id.bday);
        bdspinner.setOnItemSelectedListener(this);
        Spinner bmspinner = (Spinner) findViewById(R.id.bmonth);
        bmspinner.setOnItemSelectedListener(this);
        Spinner byspinner = (Spinner) findViewById(R.id.byear);
        byspinner.setOnItemSelectedListener(this);

        //end spinners

        proceedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                trySave();
            }
        });


    }

    public void trySave(){
        strName = nameView.getText().toString();
        strFirstName = firstNameView.getText().toString();
        setErrorsToNull();
        if (TextUtils.isEmpty(strName)) {
            sendError(nameView, "Error field required: please fill all the fields");
            return;
        }
        else if (TextUtils.isEmpty(strFirstName)) {
            sendError(firstNameView, "Error field required: please fill all the fields");
            return;
        }
        else if(!isValid(strName)){
            sendError(nameView, "Error invalid name: please use only letters and capitals");
            return;
        }
        else if(!isValid(strFirstName)){
            sendError(firstNameView, "Error invalid first name: please use only letters and capitals");
            return;
        }
        else
            save();
    }

    public void save(){
        MyApplication.getUser().setNameStr(strName);
        MyApplication.getUser().setFirstNameStr(strFirstName);
        MyApplication.getUser().setBirthdayStr(year + "-" + User.monthToInt(month) + "-" + day);

        UserManager uM = new UserManager(this);
        uM.open();
        uM.modUser(MyApplication.getUser());
        uM.close();

        Intent intent = new Intent(this, ResearchSettingsActivity.class);
        Toast.makeText(StartupActivity.this, "Redirection...", Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        // On selecting a spinner item
        switch(parent.getId()){
            case R.id.seditPlace:
                setPlace(parent, view, pos, id);
                break;
            case R.id.setOrientation :
                setOrientation(parent, view, pos, id);
                break;
            case R.id.setHair :
                setHair(parent, view, pos, id);
                break;
            case R.id.setEyes :
                setEyes(parent, view, pos, id);
                break;
            case R.id.setGender :
                setGender(parent, view, pos, id);
                break;
            case R.id.bday:
                day = parent.getSelectedItem().toString();;
                break;
            case R.id.bmonth:
                month=parent.getSelectedItem().toString();;
                break;
            case R.id.byear:
                year= parent.getSelectedItem().toString();;
                break;
        }
    }
    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }
    public void setOrientation(AdapterView<?> parent, View view, int pos, long id){
        switch(pos){
            case 0 :
                strOrientation = "M";
                break;
            case 1 :
                strOrientation = "F";
                break;
            case 2 :
                strOrientation = "B";
                break;
        }
    }
    public void setHair(AdapterView<?> parent, View view, int pos, long id){
        switch(pos){
            case 1 :
                strHair = "black";
                break;
            case 2 :
                strHair = "blond";
                break;
            case 3 :
                strHair = "brown";
                break;
            case 4 :
                strHair = "red";
                break;
            case 5 :
                strHair = "other";
                break;
        }
    }
    public void setEyes(AdapterView<?> parent, View view, int pos, long id){
        switch (pos){
            case 1 :
                strEyes = "black";
                break;
            case 2 :
                strEyes = "blue";
                break;
            case 3 :
                strEyes = "brown";
                break;
            case 4 :
                strEyes = "green";
                break;
        }
    }
    public void setGender(AdapterView<?> parent, View view, int pos, long id){
        switch(pos){
            case 1 :
                strGender = "M";
                break;
            case 2 :
                strGender = "F";
                break;
        }
    }

    public void setPlace(AdapterView<?> parent, View view, int pos, long id){
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
        }
        catch(Exception e){
            Toast.makeText(StartupActivity.this, "Error in setHair! "+ e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        Toast.makeText(StartupActivity.this, "Place : "+MyApplication.getUser().getPlaceStr(), Toast.LENGTH_SHORT).show();
        MyApplication.getUser().updateDatabase(this);
    }

    private boolean isValid(String username) {
        return Pattern.compile("^" + VALID_NAME + "{3,20}$").matcher(username).matches();
    }

    private void sendError(EditText field, String message) {
        field.setError(message);
        field.requestFocus();
    }
    private void setErrorsToNull() {
        nameView.setError(null);
        firstNameView.setError(null);
    }
}
