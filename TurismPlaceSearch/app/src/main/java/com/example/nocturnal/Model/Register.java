package com.example.nocturnal.Model;

import android.graphics.Bitmap;

/**
 * Created by bhuiy on 4/25/2017.
 */

public class Register {
    private String fullName;
    private String password;
    private String emergencyConNo;
    private String address;
    private String email;
    private Bitmap profileImage;
    private String imagePath;
    private String imageUtl;

    public Register() {
    }

    public Register(String fullName, String password, String emergencyConNo, String address, String email, Bitmap profileImage, String imagePath, String imageUtl) {
        this.fullName = fullName;
        this.password = password;
        this.emergencyConNo = emergencyConNo;
        this.address = address;
        this.email = email;
        this.profileImage = profileImage;
        this.imagePath = imagePath;
        this.imageUtl = imageUtl;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmergencyConNo() {
        return emergencyConNo;
    }

    public void setEmergencyConNo(String emergencyConNo) {
        this.emergencyConNo = emergencyConNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Bitmap getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(Bitmap profileImage) {
        this.profileImage = profileImage;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getImageUtl() {
        return imageUtl;
    }

    public void setImageUtl(String imageUtl) {
        this.imageUtl = imageUtl;
    }
}
