package com.bluecrabstudios.gpixel.activities.menu;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.bluecrabstudios.gpixel.R;
import com.bluecrabstudios.gpixel.activities.account.account_activity;
import com.bluecrabstudios.gpixel.activities.play.select_car_activity;

public class menu_activity extends Activity {

    public static final String TAG = "MENU_ACTIVITY";

    private int width, height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Sets the screen to fullscreen.
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Sets the view to the menu activity.
        setContentView(R.layout.menu_activity);

        // Get display settings.
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        width = size.x;
        height = size.y;

        // Setup the buttons on the home screen.
        setup();
    }

    private void setup() {

        RelativeLayout rl = (RelativeLayout) findViewById(R.id.rel_layout);
        TransitionDrawable transition = (TransitionDrawable) rl.getBackground();
        transition.startTransition(500);

        final Button play = (Button) findViewById(R.id.play);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setClass(getApplicationContext(), select_car_activity.class);
                startActivity(i);
            }
        });

        final Button options = (Button) findViewById(R.id.options);
        options.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        final Button about = (Button) findViewById(R.id.about);
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        final Button highScores = (Button) findViewById(R.id.highScores);
        highScores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        final Button myAccount = (Button) findViewById(R.id.MyAccountButton);
        myAccount.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                i.setClass(getApplicationContext(), account_activity.class);
                startActivity(i);
            }
        });

    }


}
