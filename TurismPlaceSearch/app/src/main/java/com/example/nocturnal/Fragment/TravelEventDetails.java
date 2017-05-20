package com.example.nocturnal.Fragment;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nocturnal.placesearch.R;
import com.example.nocturnal.placesearch.databinding.FragmentTravelEventDetailsBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class TravelEventDetails extends Fragment {

FragmentTravelEventDetailsBinding binding;
    public TravelEventDetails() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.setContentView(getActivity(),R.layout.fragment_travel_event_details);
        binding.budget.setText(getArguments().getString("budget").toString());
        binding.destination.setText(getArguments().getString("destination").toString());
        binding.fromdate.setText(getArguments().getString("fromdate").toString());
        binding.todate.setText(getArguments().getString("todate").toString());
        return inflater.inflate(R.layout.fragment_travel_event_details, container, false);
    }

}
