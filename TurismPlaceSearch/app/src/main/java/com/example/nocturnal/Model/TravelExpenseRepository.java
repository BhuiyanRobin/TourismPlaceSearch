package com.example.nocturnal.Model;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by bhuiy on 5/20/2017.
 */

public class TravelExpenseRepository {
    DatabaseReference database;
    public static ArrayList<TravelExpense>expenses;
    public TravelExpenseRepository()
    {
        database= FirebaseDatabase.getInstance().getReference("TravelExpense");
        expenses=new ArrayList<>();
    }
    public boolean Save(TravelExpense travelExpense)
    {
        database.push().setValue(travelExpense);
        return true;
    }
    public boolean Update(TravelEvent travelEvent)
    {
        return true;
    }
    public boolean Delete(TravelExpense travelExpense)
    {
        return true;
    }
    public TravelEvent GetById(String id){
        return new TravelEvent();
    }
    public void AlltravelExpense()
    {
        database= FirebaseDatabase.getInstance().getReference().child("TravelEvent");
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data:dataSnapshot.getChildren()) {
                    TravelExpense expense=data.getValue(TravelExpense.class);
                    expense.setId(data.getKey());
                    expenses.add(expense);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
