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
    private Button viewAll;
    private boolean viewingPending;


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

    // Function called by the search user button.
    private void searchUser(String username){
        ArrayList<User> users = UserHandler.searchUser(username);
        if(users.size() > 0) {
            RelativeLayout rl = (RelativeLayout) findViewById(R.id.layoutBase);
            rl.removeAllViews();
            populateUserList(users);
        }
    }

    // Updates the screen and shows different UI depending on activity.
    private void updateScreen(boolean in){
        viewingPending = in;
        if(viewingPending){
            viewAll.setVisibility(View.VISIBLE);
        }else{
            viewAll.setVisibility(View.INVISIBLE);
            populateUserList(UserHandler.getAllUsers());

        }
    }

    // Sets up the UI elements.
    private void setupUI(){
        // Check if logged in and have a username.
        final String name = prefs.getString("username", "NULL");
        viewingPending = false;

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


        // The UI for viewing the pending requests.
        pendingAmount = (TextView) findViewById(R.id.pendingAmountView);
        viewPendingButton = (Button) findViewById(R.id.viewPendingButton);
        viewPendingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RelativeLayout rl = (RelativeLayout) findViewById(R.id.layoutBase);
                rl.removeAllViews();
                populatePendingRequests(FriendHandler.getPendingAmount(name));
                updateScreen(true);
            }
        });


        viewAll = (Button) findViewById(R.id.viewAll);
        viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RelativeLayout rl = (RelativeLayout) findViewById(R.id.layoutBase);
                rl.removeAllViews();
                populateUserList(UserHandler.getAllUsers());
                updateScreen(false);
            }
        });



        /*

        Checks if the username is valid if it is will get the pending requests and put them in the
        list

        */
        if(!name.equals("NULL")){
            ArrayList<User> users = FriendHandler.getPendingAmount(name);
            pending = users.size();

            if(users != null){
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
    }

    // Updates the list that shows the pending requests.
    private void updatePendingList(){
        final String name = prefs.getString("username", "NULL");
        RelativeLayout rl = (RelativeLayout) findViewById(R.id.layoutBase);
        rl.removeAllViews();

        ArrayList<User> users = FriendHandler.getPendingAmount(name);

        if(users.size() > 0){
            populatePendingRequests(FriendHandler.getPendingAmount(name));
        }else{
            updateScreen(false);
        }


    }

    // Denys the friendship.
    private void denyFriend(int id){
        Toast.makeText(getApplicationContext(),Boolean.toString(FriendHandler.deny(id)), Toast.LENGTH_SHORT).show();
        setupUI();
        updatePendingList();
    }

    // Accepts the friendship.
    private void acceptFriend(int id){
        Toast.makeText(getApplicationContext(),Boolean.toString(FriendHandler.accept(id)), Toast.LENGTH_SHORT).show();
        setupUI();
        updatePendingList();
    }

    // Populate the list with the pending requests.
    private void populatePendingRequests(ArrayList<User> users){
        LL = new LinearLayout(this);
        LL.setOrientation(LinearLayout.VERTICAL);
        LL.setWeightSum(6f);
        LL.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        for(User u : users){
            final LinearLayout v = new LinearLayout(this);
            v.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 200));
            v.setWeightSum(1f);

            TextView t = new TextView(this);
            t.setTextSize(30);
            t.setWidth(500);
            t.setText(u.getUsername());

            Button b = new Button(this);
            b.setText("Accept");
            Button b2 = new Button(this);
            b2.setText("Deny");

            final int id = u.getID();

            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    acceptFriend(id);
                }
            });

            b2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    denyFriend(id);
                }
            });

            v.addView(t);
            v.addView(b);
            v.addView(b2);
            LL.addView(v);
        }

        RelativeLayout rl= (RelativeLayout) findViewById(R.id.layoutBase);
        rl.addView(LL);
    }

    // Updates the list with the users array passed to it.
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
    }

    // Sends a friend request to the user passed in as a parameter.
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
