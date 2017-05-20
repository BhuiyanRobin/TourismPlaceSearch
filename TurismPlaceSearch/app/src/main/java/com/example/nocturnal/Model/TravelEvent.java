package com.example.nocturnal.Model;

import java.util.ArrayList;

/**
 * Created by bhuiy on 5/17/2017.
 */

public class TravelEvent {
    private String destination;
    private double budget;
    private String fromDate;
    private String toDate;
    private String userId;
    public String id;

    public TravelEvent(String destination, double budget, String fromDate, String toDate, String userId, String id) {
        this.destination = destination;
        this.budget = budget;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.userId = userId;
        this.id = id;
    }

    public TravelEvent() {
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

<<<<<<< HEAD
    public ArrayList<TravelEvent> getAllInfo(){
        ArrayList<TravelEvent> travelEvents = new ArrayList<>();

        travelEvents.add(new TravelEvent("aghdvasd",10.00,"maha","kaha","2"));
        travelEvents.add(new TravelEvent("aghdvasd",10.00,"maha","kaha","2"));
        travelEvents.add(new TravelEvent("aghdvasd",10.00,"maha","kaha","2"));
        travelEvents.add(new TravelEvent("aghdvasd",10.00,"maha","kaha","2"));
        travelEvents.add(new TravelEvent("aghdvasd",10.00,"maha","kaha","2"));

        return travelEvents;
=======
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
>>>>>>> 35986223bfbff32d2f1ac59ed8de12608ea9875f
    }
}
