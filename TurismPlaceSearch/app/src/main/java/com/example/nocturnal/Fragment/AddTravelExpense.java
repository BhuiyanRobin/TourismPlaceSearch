package com.example.nocturnal.Fragment;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nocturnal.Model.TravelExpense;
import com.example.nocturnal.Model.TravelExpenseRepository;
import com.example.nocturnal.placesearch.R;
import com.example.nocturnal.placesearch.databinding.FragmentAddTravelExpenseBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddTravelExpense extends Fragment {

FragmentAddTravelExpenseBinding binding;
    TravelExpenseRepository repository;
    public AddTravelExpense() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding= DataBindingUtil.setContentView(getActivity(),R.layout.fragment_add_travel_expense);
        repository=new TravelExpenseRepository();
        binding.saveExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TravelExpense expense=new TravelExpense();
                expense.setExpenseCost(Double.parseDouble(binding.expenseAmount.getText().toString()));
                expense.setExpenseDetail(binding.expenseDetails.getText().toString());
                String travelId="KkXeldQH1pxU_GgIAUs";
                expense.setTravelId(travelId);
                repository.Save(expense);
            }
        });

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_travel_expense, container, false);
    }

}
