package com.example.nocturnal.Adapter;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nocturnal.Model.TravelEvent;
import com.example.nocturnal.placesearch.R;
import com.example.nocturnal.placesearch.databinding.TravelListViewBinding;

import java.util.ArrayList;

/**
 * Created by bhuiy on 5/20/2017.
 */

public class TravelLsitAdapter extends ArrayAdapter<TravelEvent> {
    private Context context;
    private ArrayList<TravelEvent> travelEvents;
    TravelListViewBinding binding;

    public TravelLsitAdapter(@NonNull Context context, ArrayList<TravelEvent> travelEvents) {
        super(context, R.layout.travel_list_view,travelEvents);
        this.context=context;
        this.travelEvents=travelEvents;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        //binding= DataBindingUtil.setContentView((Activity) context,R.layout.travel_list_view);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.travel_list_view,parent,false);

        TextView Show_Destination = (TextView) convertView.findViewById(R.id.Show_Destination);
        TextView Show_FromDate = (TextView) convertView.findViewById(R.id.Show_FromDate);
        TextView Show_Todate = (TextView) convertView.findViewById(R.id.Show_Todate);
        TextView Show_Budget = (TextView) convertView.findViewById(R.id.Show_Budget);


        Show_Destination.setText(travelEvents.get(position).getDestination());
        Show_FromDate.setText(travelEvents.get(position).getFromDate());
        Show_Todate.setText(travelEvents.get(position).getToDate());
        Show_Budget.setText(travelEvents.get(position).getBudget()+"");

        /*binding.ShowBudget.setText(Double.toString(travelEvents.get(position).getBudget()));
        binding.ShowDestination.setText(travelEvents.get(position).getDestination().toString());
        binding.ShowFromDate.setText(travelEvents.get(position).getFromDate().toString());
        binding.ShowTodate.setText(travelEvents.get(position).getToDate().toString());*/
        return convertView;
    }
}
