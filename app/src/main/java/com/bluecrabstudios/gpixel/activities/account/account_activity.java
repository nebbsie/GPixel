package com.bluecrabstudios.gpixel.activities.account;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bluecrabstudios.gpixel.R;
import com.bluecrabstudios.gpixel.helpers.UserHandler;
import com.bluecrabstudios.gpixel.objects.User;
import com.google.firebase.auth.FirebaseAuth;

public class account_activity extends Activity {

    public static String TAG = "ACCOUNT_ACTIVITY";
    private EditText username;
    private EditText password;
    private Button loginButton;

    private EditText createEmail;
    private EditText createUsername;
    private EditText createPassword1;
    private EditText createPassword2;
    private Button createRegisterButton;
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_activity);


        prefs = this.getSharedPreferences("org.bluecrabstudios.gpixel", Context.MODE_PRIVATE);



        // Login section
        username = (EditText) findViewById(R.id.usernameInput);
        password = (EditText) findViewById(R.id.passwordInput);
        loginButton = (Button) findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User u = UserHandler.login(username.getText().toString(), password.getText().toString());
                System.out.println(u.getEmail());
                if(u != null){
                    Toast.makeText(getApplicationContext(),u.getUsername() + " logged in.", Toast.LENGTH_SHORT).show();
                    prefs.edit().putString("username", u.getUsername()).apply();
                    prefs.edit().putString("email", u.getEmail()).apply();
                    prefs.edit().putInt("id", u.getID()).apply();
                    prefs.edit().putString("EXTERNAL_ID", u.getEXTERNAL_ID()).apply();
                    prefs.edit().putString("EXTERNAL_TYPE", u.getEXTERNAL_TYPE()).apply();
                    prefs.edit().putBoolean("loggedIn", true);
                }else{
                    Toast.makeText(getApplicationContext(),"ERROR logging in.", Toast.LENGTH_SHORT).show();
                    prefs.edit().putBoolean("loggedIn", false);
                }

            }
        });

        // Register section
        createEmail = (EditText) findViewById(R.id.createEmailInput);
        createUsername = (EditText) findViewById(R.id.createUsernameInput);
        createPassword1 = (EditText) findViewById(R.id.createPassword1);
        createPassword2 = (EditText) findViewById(R.id.createPassword2);
        createRegisterButton = (Button) findViewById(R.id.createRegisterButton);

        createRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(createPassword1.getText().toString().equals(createPassword2.getText().toString())){
                    boolean b = UserHandler.register(createUsername.getText().toString(), createPassword1.getText().toString(), createEmail.getText().toString());
                    if(b){
                        Toast.makeText(getApplicationContext(),"Created USER: " + createUsername +  ".", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getApplicationContext(),"Failed creating USER" + createUsername, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
