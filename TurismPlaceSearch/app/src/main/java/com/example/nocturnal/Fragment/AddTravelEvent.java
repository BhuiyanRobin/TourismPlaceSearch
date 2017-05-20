package com.example.nocturnal.Fragment;


import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import com.example.nocturnal.Model.TravelEvent;
import com.example.nocturnal.Model.TravelEventService;
import com.example.nocturnal.PreferedShare.LogInPref;
import com.example.nocturnal.placesearch.R;
import com.example.nocturnal.placesearch.databinding.FragmentAddTravelEventBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddTravelEvent extends Fragment {
    FragmentAddTravelEventBinding binding;
    Calendar myCalendar;
    LogInPref logInPref;
    SharedPreferences sharedPreferences;
    TravelEventService eventService;
    public AddTravelEvent() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding= DataBindingUtil.setContentView(getActivity(),R.layout.fragment_add_travel_event);
        sharedPreferences=getActivity().getSharedPreferences(LogInPref.Log_In_SHARE_PREFERENCE_NAME,MODE_PRIVATE);
        logInPref=new LogInPref(sharedPreferences);
        myCalendar = Calendar.getInstance(Locale.getDefault());
        eventService=new TravelEventService();
        myCalendar.set(Calendar.YEAR, 2017);
        myCalendar.set(Calendar.MONTH, 5);
        myCalendar.set(Calendar.DAY_OF_MONTH, 21);
        binding.fromdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(getActivity(),listener,2017,4,21);
                dialog.show();
            }
        });
        binding.todate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(getActivity(),listener1, 2017, 4, 22);
                dialog.show();
            }
        });
        binding.saveTravelEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TravelEvent event=new TravelEvent();
                event.setBudget(Double.parseDouble(binding.budget.getText().toString()));
                event.setDestination(binding.destination.getText().toString());
                event.setFromDate(binding.fromdate.getText().toString());
                event.setToDate(binding.fromdate.getText().toString());
                event.setUserId(logInPref.GetUserKey());
                eventService.Save(event);
            }
        });
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_travel_event, container, false);
    }
    private DatePickerDialog.OnDateSetListener listener=new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            myCalendar.set(year,month,dayOfMonth);
            String date=sdf.format(myCalendar.getTime());
            binding.fromdate.setText(date);
        }
    };
    private DatePickerDialog.OnDateSetListener listener1=new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            myCalendar.set(year,month,dayOfMonth);
            String date=sdf.format(myCalendar.getTime());
            binding.todate.setText(date);
        }
    };

}
