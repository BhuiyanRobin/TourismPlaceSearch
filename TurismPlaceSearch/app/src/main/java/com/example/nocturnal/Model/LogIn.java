package com.example.nocturnal.Model;

/**
 * Created by bhuiy on 4/25/2017.
 */

public class LogIn {
    private String userName;
    private String password;

    public LogIn() {
    }

    public LogIn(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
