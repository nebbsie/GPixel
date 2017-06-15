package com.bluecrabstudios.gpixel.activities.play;

import android.app.Activity;
import android.graphics.drawable.TransitionDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.bluecrabstudios.gpixel.Options;
import com.bluecrabstudios.gpixel.R;

import java.util.Random;

public class select_car_activity extends Activity {

    private Options options;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Sets the screen to fullscreen.
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.select_car_activity);
        setup();
    }

    private void setup(){
        RelativeLayout rl = (RelativeLayout) findViewById(R.id.rel_layout);
        TransitionDrawable transition = (TransitionDrawable) rl.getBackground();
        transition.startTransition(500);

        options = new Options();

        Button blue = (Button) findViewById(R.id.button);
        blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                options.car = "blue";
            }
        });

        Button red = (Button) findViewById(R.id.button2);
        red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                options.car = "red";
            }
        });

        Button green = (Button) findViewById(R.id.button3);
        green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                options.car = "green";
            }
        });

        Button black = (Button) findViewById(R.id.button8);
        black.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                options.car = "black";
            }
        });

        Button toothpaste = (Button) findViewById(R.id.button5);
        toothpaste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                options.car = "toothpaste";
            }
        });

        Button pokemon = (Button) findViewById(R.id.button6);
        pokemon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                options.car = "pokemon";
            }
        });

        Button herbie = (Button) findViewById(R.id.button7);
        herbie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                options.car = "herbie";
            }
        });

        Button flame = (Button) findViewById(R.id.button9);
        flame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                options.car = "flame";
            }
        });

        Button random = (Button) findViewById(R.id.random);
        random.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] opt = {"blue", "red", "green", "black", "toothpaste", "pokemon", "herbie", "fire"};
                options.car = opt[new Random().nextInt(opt.length)];
            }
        });
    }
}
