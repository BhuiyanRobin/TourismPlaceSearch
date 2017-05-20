package com.example.nocturnal.Model;

import android.graphics.Bitmap;

/**
 * Created by bhuiy on 5/13/2017.
 */

public class TravelMoment {
    private String travelId;
    private String momentDetails;
    private Bitmap momentPic;
    private String iamge;
    private String dateTime;
    private String id;

    public TravelMoment() {
    }

    public TravelMoment(String travelId, String momentDetails, Bitmap momentPic, String iamge, String dateTime, String id) {
        this.travelId = travelId;
        this.momentDetails = momentDetails;
        this.momentPic = momentPic;
        this.iamge = iamge;
        this.dateTime = dateTime;
        this.id = id;
    }

    public String getTravelId() {
        return travelId;
    }

    public void setTravelId(String travelId) {
        this.travelId = travelId;
    }

    public String getMomentDetails() {
        return momentDetails;
    }

    public void setMomentDetails(String momentDetails) {
        this.momentDetails = momentDetails;
    }

    public Bitmap getMomentPic() {
        return momentPic;
    }

    public void setMomentPic(Bitmap momentPic) {
        this.momentPic = momentPic;
    }

    public String getIamge() {
        return iamge;
    }

    public void setIamge(String iamge) {
        this.iamge = iamge;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
