package com.example.nocturnal.Model;

/**
 * Created by bhuiy on 5/13/2017.
 */

public class TravelExpense {
    private String expenseDetail;
    private double expenseCost;
    private String travelId;
    private String id;
    public TravelExpense() {
    }

    public TravelExpense(String expenseDetail, double expenseCost, String travelId, String id) {
        this.expenseDetail = expenseDetail;
        this.expenseCost = expenseCost;
        this.travelId = travelId;
        this.id = id;
    }

    public String getExpenseDetail() {
        return expenseDetail;
    }

    public void setExpenseDetail(String expenseDetail) {
        this.expenseDetail = expenseDetail;
    }

    public double getExpenseCost() {
        return expenseCost;
    }

    public void setExpenseCost(double expenseCost) {
        this.expenseCost = expenseCost;
    }

    public String getTravelId() {
        return travelId;
    }

    public void setTravelId(String travelId) {
        this.travelId = travelId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
