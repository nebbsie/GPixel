package com.bluecrabstudios.gpixel;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.bluecrabstudios.gpixel.helpers.FriendHandler;
import com.bluecrabstudios.gpixel.helpers.UserHandler;
import com.bluecrabstudios.gpixel.objects.User;
import java.util.ArrayList;

public class social_activity extends AppCompatActivity {

    private SharedPreferences prefs;
    private EditText usernameSearch;
    private Button searchUsers;
    private LinearLayout LL;

    private TextView pendingAmount;
    private Button viewPendingButton;
    private int pending;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.social_activity);


        // Setups the shared preferences.
        prefs = this.getSharedPreferences("org.bluecrabstudios.gpixel", Context.MODE_PRIVATE);

        // Setup the buttons and text edits.
        setupUI();

        // Sets the list to all of the users.
        populateUserList(UserHandler.getAllUsers());


    }

    private void searchUser(String username){
        ArrayList<User> users = UserHandler.searchUser(username);
        if(users.size() > 0) {
            RelativeLayout rl = (RelativeLayout) findViewById(R.id.layoutBase);
            rl.removeAllViews();
            populateUserList(users);
        }
    }

    private void setupUI(){
        usernameSearch = (EditText) findViewById(R.id.searchFriendField);
        searchUsers = (Button) findViewById(R.id.searchFriendButton);
        searchUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(usernameSearch.getText().length() > 0){
                    searchUser(usernameSearch.getText().toString());
                }
            }
        });


        pendingAmount = (TextView) findViewById(R.id.pendingAmountView);
        viewPendingButton = (Button) findViewById(R.id.viewPendingButton);
        viewPendingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        String name = prefs.getString("username", "NULL");

        if(!name.equals("NULL")){
            pending = FriendHandler.getPendingAmount(name);
            if(pending > 0){
                pendingAmount.setVisibility(View.VISIBLE);
                pendingAmount.setText(pending + " pending requests.");
                viewPendingButton.setVisibility(View.VISIBLE);
            }else{
                pendingAmount.setVisibility(View.INVISIBLE);
                viewPendingButton.setVisibility(View.INVISIBLE);
            }
        }


    }

    private void populateUserList(ArrayList<User> users){

        LL = new LinearLayout(this);
        LL.setOrientation(LinearLayout.VERTICAL);
        LL.setWeightSum(6f);
        LL.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        for(User u : users){
            LinearLayout v = new LinearLayout(this);
            v.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 200));
            v.setWeightSum(1f);

            TextView t = new TextView(this);
            t.setTextSize(30);
            t.setWidth(500);
            t.setText(u.getUsername());

            Button b = new Button(this);
            b.setText("Send Friend Request");

            final int id = u.getID();

            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sendRequest(id);
                }
            });

            v.addView(t);
            v.addView(b);
            LL.addView(v);
        }

        RelativeLayout rl= (RelativeLayout) findViewById(R.id.layoutBase);
        rl.addView(LL);

        System.out.println(LL.getChildCount());
    }

    private void sendRequest(int friendID){
        String loggedin = prefs.getString("username", "NULL");
        if(!loggedin.equals("NULL")){
            boolean success = FriendHandler.sendFriendRequest(friendID);
            if(success){
                Toast.makeText(getApplicationContext(), "Send Request To: " + friendID, Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getApplicationContext(), "Failed To Send Request.", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
