package com.example.nocturnal.Model;

/**
 * Created by bhuiy on 5/17/2017.
 */

public class TravelEvent {
    private String destination;
    private double budget;
    private String fromDate;
    private String toDate;
    private String userId;

    public TravelEvent() {

    }

    public TravelEvent(String destination, double budget, String fromDate, String toDate, String userId) {
        this.destination = destination;
        this.budget = budget;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.userId = userId;
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
}
