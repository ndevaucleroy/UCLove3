package lsinf1225.uclove;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    //caracteres valides pour le login et le mot de passe
    private static final String VALID_LOGIN_CHAR = "[a-zA-Z0-9_-]{3,15}$";
    private static final String VALID_PASSWORD_CHAR = "[a-zA-Z0-9_-]{6,20}$";

    private EditText login, password;
    private EditText confirmPassword, email;
    private Button signInButton, registerButton;
    private boolean isRegisterShown = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        
        //faire correspondre les activitys au xml
        signInButton = (Button) findViewById(R.id.signin);
        registerButton = (Button) findViewById(R.id.register);
        login = (EditText) findViewById(R.id.login);
        password = (EditText) findViewById(R.id.password);
        registerButton = (Button) findViewById(R.id.register);
        confirmPassword = (EditText) findViewById(R.id.confirmpassword);
        email = (EditText) findViewById(R.id.email);
        
        //au clic de ce bouton, essai de connexion
        signInButton.setOnClickListener(new View.OnClickListener()
            {@Override public void onClick(View v) { tryToLogIn(); }});
        
        //cacher ces deux editTexts
        confirmPassword.setVisibility(View.GONE);
        email.setVisibility(View.GONE);

        //au clic de ce bouton, essai d'inscription
        registerButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                tryRegister();
            }
        });
    }

    private void tryToLogIn() {

        setErrorsToNull();

        String username = login.getText().toString();
        String stringpassword = password.getText().toString();
        
        if (TextUtils.isEmpty(username)) {
            sendError(login, "Error field required: please fill all the fields");
            return;
        }
        if (TextUtils.isEmpty(stringpassword)) {
            sendError(password, "Error field required: please fill all the fields");
            return;
        }

        if (!isUsernameValid(username)) {
            sendError(login, "Error: Login is invalid");
            return;
        }
        if (!isPasswordValid(stringpassword)) {
            sendError(password, "Error: Password is invalid");
            return;
        }
        
        //si toutes les erreurs sont gerees, on se connecte
        userLogin(username, stringpassword);
    }

    private void tryRegister() {

        setErrorsToNull();
        showRegister();
        String username = login.getText().toString();
        String stringpassword = password.getText().toString();
        String stringconfirmPassword = confirmPassword.getText().toString();
        String stringemail = email.getText().toString();

        // All fields are required.
        if (TextUtils.isEmpty(username)) {
            sendError(login, "Error field required: please fill all the fields");
            return;
        }
        if (TextUtils.isEmpty(stringpassword)) {
            sendError(password, "Error field required: please fill all the fields");
            return;
        }
        if (TextUtils.isEmpty(stringconfirmPassword)) {
            sendError(confirmPassword, "Error field required: please fill all the fields");
            return;
        }
        if (TextUtils.isEmpty(stringemail)) {
            sendError(email, "Error field required: please fill all the fields");
            return;
        }

        // All fields must be valid.
        if (!isUsernameValid(username)) {
            sendError(login, "Error: Login is invalid");
            return;
        }
        if (!isPasswordValid(stringpassword)) {
            sendError(password, "Error: Password is invalid");
            return;
        }
        if (!isEmailValid(stringemail)) {
            sendError(email, "Error: Email is invalid");
            return;

        }

        // The passwords must match.
        if (!stringpassword.equals(stringconfirmPassword)) {
            sendError(confirmPassword, "Error: password mismatch");
            return;
        }

        //si toutes les erreurs sont gerees, on inscrit le nouvel utilisateur
        userRegister(username, stringpassword, stringemail);
    }

    private void userLogin(String username, String stringpassword) {

        if (!User.isLoginAvailable(username, this)) {
            MyApplication.setUser(new User(username, this));
            if (MyApplication.getUser().getPasswordStr() != null){
                if (!stringpassword.equals(MyApplication.getUser().getPasswordStr())) {
                    //mot de passe incorrect
                    sendError(password, "Error: Incorrect Password");
                }
                else{
                    //demarrage d'une nouvelle activite
                    Toast.makeText(LoginActivity.this, "Correct password!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, ProfileActivity.class);
                    startActivity(intent);
                }
            }
            else{
                Toast.makeText(LoginActivity.this, "Password null", Toast.LENGTH_SHORT).show();
            }
        } else showRegister();
    }


    private void showRegister() {
        isRegisterShown = true;

        // Hide login button
        signInButton.setVisibility(View.GONE);

        // Display additional fields and register button.
        confirmPassword.setVisibility(View.VISIBLE);
        email.setVisibility(View.VISIBLE);
        registerButton.setVisibility(View.VISIBLE);

        // Change focus to the next field to fill.
        confirmPassword.requestFocus();
    }

    private void hideRegister(){
        isRegisterShown = false;

        // Hide additional fields and register button.
        registerButton.setVisibility(View.GONE);
        email.setVisibility(View.GONE);
        confirmPassword.setVisibility(View.GONE);

        // Display login button.
        signInButton.setVisibility(View.VISIBLE);

        // Change focus to the username field.
        login.requestFocus();
    }

    @Override
    public void onBackPressed() {
        if (isRegisterShown) {
            hideRegister();
        } else {
            finish();
        }
    }

    

    private void userRegister(String username, String stringpassword, String stringemail) {

        if (!User.isLoginAvailable(username, this)) {
            sendError(login, "Error: This username has already been taken");
            return;
        }
        //TODO: isEmailAvailable?
        MyApplication.setUser(new User(stringpassword, username, this));
        Intent intent = new Intent(this, StartupActivity.class);
        Toast.makeText(LoginActivity.this, "Redirection...", Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }

    private boolean isUsernameValid(String username) {
        return Pattern.compile("^"+VALID_LOGIN_CHAR+"{3,20}$").matcher(username).matches();
    }

    private boolean isPasswordValid(String password) {
        return Pattern.compile("^"+VALID_PASSWORD_CHAR+"{5,50}$").matcher(password).matches();
    }

    private boolean isEmailValid(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void sendError(EditText field, String message) {
        field.setError(message);
        field.requestFocus();
    }

    private void setErrorsToNull() {
        login.setError(null);
        password.setError(null);
        confirmPassword.setError(null);
        email.setError(null);
    }
}
