package com.example.nocturnal.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.nocturnal.Adapter.TravelListViewAdapter;
import com.example.nocturnal.Adapter.TravelLsitAdapter;
import com.example.nocturnal.Model.TravelEvent;
import com.example.nocturnal.placesearch.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class TravelEventList extends Fragment {

    /*RecyclerView recyclerView;
    TravelListViewAdapter adapter;*/
    private ListView myListview;
    private ArrayList<TravelEvent> myTravelEvents;

    TravelEvent travelEvent;
    TravelLsitAdapter travelLsitAdapter;


    public TravelEventList() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_travel_event_list, container, false);

        myListview = (ListView) view.findViewById(R.id.myListview);
        myTravelEvents = new ArrayList<>();
        travelEvent = new TravelEvent();

        myTravelEvents = travelEvent.getAllInfo();

        travelLsitAdapter = new TravelLsitAdapter(getContext(),myTravelEvents);

        myListview.setAdapter(travelLsitAdapter);

        return view;
    }

}
