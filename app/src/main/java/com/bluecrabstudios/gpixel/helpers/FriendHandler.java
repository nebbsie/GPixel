package com.bluecrabstudios.gpixel.helpers;

import android.content.SharedPreferences;

import com.bluecrabstudios.gpixel.database.GPixelAPI;
import com.bluecrabstudios.gpixel.database.GPixelParams;
import com.bluecrabstudios.gpixel.objects.TrackScore;
import com.bluecrabstudios.gpixel.objects.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


public class FriendHandler {


    public static boolean sendFriendRequest(int id){
        try {
            GPixelParams params = new GPixelParams(new URL("http://192.168.1.12/gpixel/friends/add"));
            params.put("friendID", Integer.toString(id));
            params.put("username", "nebbsie");
            GPixelAPI api = new GPixelAPI(params);
            JSONObject obj = api.execute().get();

            if(obj.getBoolean("success")){
                return true;
            }else{
                return false;
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static int getPendingAmount(String username){
        try {
            GPixelParams params = new GPixelParams(new URL("http://192.168.1.12/gpixel/friends/getPending"));
            params.put("username", username);
            GPixelAPI api = new GPixelAPI(params);
            JSONObject obj = api.execute().get();

            JSONArray arr = obj.getJSONArray("data");

            if(obj.getBoolean("success")){
                return arr.length();
            }else{
                return 0;
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 0;
    }


}
