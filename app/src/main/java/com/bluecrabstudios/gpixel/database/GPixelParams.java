package com.bluecrabstudios.gpixel.database;

import java.net.URL;
import java.util.HashMap;

public class GPixelParams {

    private HashMap<String, String> params;
    private URL url;

    public GPixelParams(URL url){
        params = new HashMap<>();
        this.url = url;
    }

    public void put(String name, String value){
        params.put(name, value);
    }

    public HashMap<String, String> getParams(){
        return params;
    }

    public URL getURL(){
        return url;
    }

}
