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

public class TravelEventService {
    DatabaseReference database;
    public static ArrayList<TravelEvent> events;
    public TravelEventService()
    {
        database= FirebaseDatabase.getInstance().getReference("TravelEvent");
        events=new ArrayList<>();
    }
    public boolean Save(TravelEvent event)
    {
        database.push().setValue(event);
        return true;
    }
    public boolean Update(TravelEvent event)
    {
        return true;
    }
    public boolean Delete(String key)
    {
        return true;
    }
    public TravelEvent GetTravelEvent()
    {
        return new TravelEvent();
    }
    public void AllEvents()
    {
        database= FirebaseDatabase.getInstance().getReference().child("TravelEvent");
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data:dataSnapshot.getChildren()) {
                    TravelEvent travelEvent=data.getValue(TravelEvent.class);
                    travelEvent.setId(data.getKey());
                    events.add(travelEvent);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
