package com.bluecrabstudios.gpixel.helpers;

import com.bluecrabstudios.gpixel.database.GPixelAPI;
import com.bluecrabstudios.gpixel.database.GPixelParams;
import com.bluecrabstudios.gpixel.objects.User;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class UserHandler {

    public static User login(String username, String password){
        try {
            GPixelParams params = new GPixelParams(new URL("http://192.168.1.12/gpixel/user/login"));
            params.put("username", username);
            params.put("password", password);
            GPixelAPI gp = new GPixelAPI(params);
            JSONObject obj = gp.execute().get();

            User user = new User();
            if(obj.getBoolean("success")){
                JSONObject data = obj.getJSONObject("data");
                user.setPassword(data.getString("password"));
                user.setUsername(data.getString("username"));
                user.setEmail(data.getString("userEmail"));
                user.setEXTERNAL_TYPE(data.getString("EXTERNAL_TYPE"));
                user.setEXTERNAL_ID(data.getString("EXTERNAL_ID"));
                return user;
            }else{
                return null;
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
        return null;
    }

    public static boolean register(String username, String password, String email){
        try {
            GPixelParams params = new GPixelParams(new URL("http://192.168.1.12/gpixel/user/create"));
            params.put("username", username);
            params.put("password", password);
            params.put("email", email);
            GPixelAPI gp = new GPixelAPI(params);
            JSONObject obj = gp.execute().get();

            return obj.getBoolean("success");

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

    public static ArrayList<User> searchUser(String username){
        try {
            GPixelParams params = new GPixelParams(new URL("http://192.168.1.12/gpixel/user/search"));
            params.put("username", username);
            GPixelAPI api = new GPixelAPI(params);
            JSONObject obj = api.execute().get();

            JSONArray arr = obj.getJSONArray("data");

            ArrayList<User> users = new ArrayList<>();

            for(int i = 0; i < arr.length(); i++){
                JSONObject o = arr.getJSONObject(i);
                users.add(new User(o.getString("username"),o.getInt("userID")));
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

    public static ArrayList<User> getAllUsers(){
        try {
            GPixelParams params = new GPixelParams(new URL("http://192.168.1.12/gpixel/user/getAll"));
            GPixelAPI api = new GPixelAPI(params);
            JSONObject obj = api.execute().get();

            JSONArray arr = obj.getJSONArray("data");

            ArrayList<User> users = new ArrayList<>();

            for(int i = 0; i < arr.length(); i++){
                JSONObject o = arr.getJSONObject(i);
                users.add(new User(o.getString("username"),o.getInt("userID")));
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
