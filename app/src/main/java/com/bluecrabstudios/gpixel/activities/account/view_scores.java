package com.bluecrabstudios.gpixel.activities.account;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.bluecrabstudios.gpixel.R;
import com.bluecrabstudios.gpixel.helpers.LeaderboardHandler;
import com.bluecrabstudios.gpixel.objects.TrackScore;

import java.util.ArrayList;

public class view_scores extends AppCompatActivity {

    private Button getScores;
    private ListView scoresList;
    private ArrayAdapter<Integer> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_scores_layout);
        getScores = (Button) findViewById(R.id.getAllScores);
        scoresList = (ListView) findViewById(R.id.scoreList);




        getScores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<TrackScore> scores = LeaderboardHandler.getTop10SpecificTrack(5);
                ArrayList<Integer> times = new ArrayList<Integer>();

                for(TrackScore t : scores){
                    times.add(t.getScore());
                }


                adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, times);


                scoresList.setAdapter(adapter);

                adapter.notifyDataSetChanged();
            }
        });
    }
}
