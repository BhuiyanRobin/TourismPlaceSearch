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

public class TravelMomentRepository {
    DatabaseReference database;
    public static ArrayList<TravelMoment> moments;
    public TravelMomentRepository()
    {
        database= FirebaseDatabase.getInstance().getReference("TravelMoment");
        moments=new ArrayList<>();
    }
    public boolean Save(TravelMoment moment)
    {
        database.push().setValue(moment);
        return true;
    }
    public boolean Update(TravelMoment moment)
    {
        return true;
    }
    public boolean Delete(TravelMoment moment)
    {
        return true;
    }
    public TravelMoment GetById(String id){
        return new TravelMoment();
    }
    public void AllTravelMoment()
    {
        database= FirebaseDatabase.getInstance().getReference().child("TravelEvent");
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data:dataSnapshot.getChildren()) {
                    TravelMoment moment=data.getValue(TravelMoment.class);
                    moment.setId(data.getKey());
                    moments.add(moment);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
