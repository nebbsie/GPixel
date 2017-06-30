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

    public static ArrayList<User> getPendingAmount(String username){
        try {
            GPixelParams params = new GPixelParams(new URL("http://192.168.1.12/gpixel/friends/getPending"));
            params.put("username", username);
            GPixelAPI api = new GPixelAPI(params);
            JSONObject obj = api.execute().get();

            JSONArray arr = obj.getJSONArray("data");
            ArrayList<User> users = new ArrayList<>();


            if(obj.getBoolean("success")){
                for(int i = 0; i < arr.length(); i++){
                    JSONObject o = arr.getJSONObject(i);

                    users.add(new User("YOKO",o.getInt("user1ID")));
                }
            }


            return users;

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
