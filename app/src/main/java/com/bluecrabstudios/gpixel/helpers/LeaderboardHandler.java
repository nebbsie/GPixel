package com.bluecrabstudios.gpixel.helpers;

import com.bluecrabstudios.gpixel.database.GPixelAPI;
import com.bluecrabstudios.gpixel.database.GPixelParams;
import com.bluecrabstudios.gpixel.objects.TrackScore;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class LeaderboardHandler {

    public static ArrayList<TrackScore> getTopScoresByUser(String username){
        try {
            GPixelParams params = new GPixelParams(new URL("http://192.168.1.12/gpixel/leaderboard/getTopScoreAllTracksByUser"));
            params.put("username", username);
            GPixelAPI api = new GPixelAPI(params);
            JSONObject obj = api.execute().get();

            JSONArray arr = obj.getJSONArray("data");

            ArrayList<TrackScore> scores = new ArrayList<>();

            for(int i = 0; i < arr.length(); i++){
                JSONObject o = arr.getJSONObject(i);
                scores.add(new TrackScore(o.getInt("trackID"), o.getInt("score")));
            }

            return scores;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static ArrayList<TrackScore> getAllScoresSpecificTrack(int trackID){
        try {
            GPixelParams params = new GPixelParams(new URL("http://192.168.1.12/gpixel/leaderboard/getAllBySpecificTrack"));
            params.put("trackID", Integer.toString(trackID));
            GPixelAPI api = new GPixelAPI(params);
            JSONObject obj = api.execute().get();

            JSONArray arr = obj.getJSONArray("data");

            ArrayList<TrackScore> scores = new ArrayList<>();

            for(int i = 0; i < arr.length(); i++){
                JSONObject o = arr.getJSONObject(i);
                scores.add(new TrackScore(o.getInt("trackID"), o.getInt("score")));
            }

            return scores;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static ArrayList<TrackScore> getTop10SpecificTrack(int trackID){
        try {
            GPixelParams params = new GPixelParams(new URL("http://192.168.1.12/gpixel/leaderboard/getTop10SpecificTrack"));
            params.put("trackID", Integer.toString(trackID));
            GPixelAPI api = new GPixelAPI(params);
            JSONObject obj = api.execute().get();

            JSONArray arr = obj.getJSONArray("data");

            ArrayList<TrackScore> scores = new ArrayList<>();

            for(int i = 0; i < arr.length(); i++){
                JSONObject o = arr.getJSONObject(i);
                scores.add(new TrackScore(o.getInt("trackID"), o.getInt("score")));
            }

            return scores;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

}



















