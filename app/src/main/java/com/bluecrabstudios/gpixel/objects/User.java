package com.bluecrabstudios.gpixel.objects;

import java.io.Serializable;

public class User implements Serializable {
    private String username;
    private String password;
    private String email;
    private String EXTERNAL_TYPE;
    private String EXTERNAL_ID;
    private int ID;


    public User(){}

    public User(String username, int id){
        this.username = username;
        this.ID = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEXTERNAL_TYPE() {
        return EXTERNAL_TYPE;
    }

    public void setEXTERNAL_TYPE(String EXTERNAL_TYPE) {
        this.EXTERNAL_TYPE = EXTERNAL_TYPE;
    }

    public String getEXTERNAL_ID() {
        return EXTERNAL_ID;
    }

    public void setEXTERNAL_ID(String EXTERNAL_ID) {
        this.EXTERNAL_ID = EXTERNAL_ID;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
