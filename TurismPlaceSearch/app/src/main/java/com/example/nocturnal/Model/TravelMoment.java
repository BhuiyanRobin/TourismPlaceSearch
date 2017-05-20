package com.example.nocturnal.Model;

import android.graphics.Bitmap;

/**
 * Created by bhuiy on 5/13/2017.
 */

public class TravelMoment {
    private String travelId;
    private String momentDetails;
    private Bitmap momentPic;

    public TravelMoment() {
    }

    public TravelMoment(String travelId, String momentDetails, Bitmap momentPic) {
        this.travelId = travelId;
        this.momentDetails = momentDetails;
        this.momentPic = momentPic;
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
}
