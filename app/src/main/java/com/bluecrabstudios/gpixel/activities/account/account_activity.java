package com.bluecrabstudios.gpixel.activities.account;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_activity);

        username = (EditText) findViewById(R.id.usernameInput);
        password = (EditText) findViewById(R.id.passwordInput);
        loginButton = (Button) findViewById(R.id.loginButton);

        createEmail = (EditText) findViewById(R.id.createEmailInput);
        createUsername = (EditText) findViewById(R.id.createUsernameInput);
        createPassword1 = (EditText) findViewById(R.id.createPassword1);
        createPassword2 = (EditText) findViewById(R.id.createPassword2);
        createRegisterButton = (Button) findViewById(R.id.createRegisterButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User u = UserHandler.login(username.getText().toString(), password.getText().toString());
                System.out.println(u.getEmail());
            }
        });

        createRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(createPassword1.getText().toString().equals(createPassword2.getText().toString())){
                    boolean b = UserHandler.register(createUsername.getText().toString(), createPassword1.getText().toString(), createEmail.getText().toString());
                    System.out.println(b);
                }
            }
        });

    }
}
