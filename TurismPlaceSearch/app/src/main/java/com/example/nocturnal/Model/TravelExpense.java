package com.example.nocturnal.Model;

/**
 * Created by bhuiy on 5/13/2017.
 */

public class TravelExpense {
    private String expenseDetail;
    private double expenseCost;
    private String travelId;

    public TravelExpense(String expenseDetail, double expenseCost, String travelId) {
        this.expenseDetail = expenseDetail;
        this.expenseCost = expenseCost;
        this.travelId = travelId;
    }

    public TravelExpense() {
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
}
