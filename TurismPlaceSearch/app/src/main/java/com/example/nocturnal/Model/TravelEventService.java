package com.example.nocturnal.Model;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created by bhuiy on 5/20/2017.
 */

public class TravelEventService {
    DatabaseReference database;
    public TravelEventService()
    {
        database= FirebaseDatabase.getInstance().getReference("TravelEvent");
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
    public ArrayList<TravelEvent> AllEvents()
    {
        return new ArrayList<>();
    }
}
