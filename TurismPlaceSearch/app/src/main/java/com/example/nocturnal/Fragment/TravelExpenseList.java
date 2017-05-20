package com.example.nocturnal.Fragment;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nocturnal.placesearch.R;
import com.example.nocturnal.placesearch.databinding.FragmentTravelExpenseListBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class TravelExpenseList extends Fragment {



    public TravelExpenseList() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentTravelExpenseListBinding binding;
        binding= DataBindingUtil.setContentView(getActivity(),R.layout.fragment_travel_event_list);
        return inflater.inflate(R.layout.fragment_travel_expense_list, container, false);
    }

}
