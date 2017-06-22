package com.bluecrabstudios.gpixel.database;

import android.os.AsyncTask;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;

public class GPixelAPI extends AsyncTask<String, Void, JSONObject> {

    private String BASE_URL = "192.168.1.12/gpixel/";
    private String API_KEY = "999";
    private GPixelParams params;

    public GPixelAPI(GPixelParams params){
        this.params = params;
        this.params.put("apiKey", API_KEY);
    }

    @Override
    protected JSONObject doInBackground(String... strings) {
        String response = "";

        try {
            HttpURLConnection conn = (HttpURLConnection) params.getURL().openConnection();
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);

            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            writer.write(getPostDataString(params.getParams()));
            writer.flush();
            writer.close();
            os.close();
            int responseCode=conn.getResponseCode();

            if (responseCode == HttpsURLConnection.HTTP_OK) {
                String line;
                BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line=br.readLine()) != null) {
                    response+=line;
                }
            }
            else {
                response="";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            JSONObject a = new JSONObject(response);
            return a;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException{
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for(Map.Entry<String, String> entry : params.entrySet()){
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }
        return result.toString();
    }

}
