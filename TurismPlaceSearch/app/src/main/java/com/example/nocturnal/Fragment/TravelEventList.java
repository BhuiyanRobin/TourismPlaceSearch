package com.example.nocturnal.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nocturnal.Adapter.TravelListViewAdapter;
import com.example.nocturnal.Model.TravelEvent;
import com.example.nocturnal.placesearch.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class TravelEventList extends Fragment {

    RecyclerView recyclerView;
    TravelListViewAdapter adapter;

    public TravelEventList() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_travel_event_list, container, false);
        view.setTag("MyTravelEvents");
        recyclerView= (RecyclerView) view.findViewById(R.id.myTravelEvents);
        adapter=new TravelListViewAdapter(getContext(),new ArrayList<TravelEvent>());
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        return view;
    }

}
