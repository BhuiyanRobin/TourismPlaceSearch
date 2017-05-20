package com.example.nocturnal.Adapter;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nocturnal.Model.TravelEvent;
import com.example.nocturnal.placesearch.R;
import com.example.nocturnal.placesearch.databinding.TravelListViewBinding;

import java.util.ArrayList;

/**
 * Created by bhuiy on 5/20/2017.
 */

public class TravelListViewAdapter extends RecyclerView.Adapter<TravelListViewAdapter.TravelEveViewHolder> {
    private ArrayList<TravelEvent> events;
    private Context context;
    private RecyclerView recyclerView;

    public TravelListViewAdapter(Context context, ArrayList<TravelEvent> events)
    {
        this.events=events;
        this.context=context;
        events.add(new TravelEvent("Dhaka",100,"2012-2-10","2015-2-2","1",""));
        events.add(new TravelEvent("Comilla",100,"2012-2-10","2015-2-2","1",""));

    }
    @Override
    public TravelEveViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.travel_list_view,parent,false);
        return new TravelEveViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TravelEveViewHolder holder, int position) {
        holder.binding.ShowBudget.setText(Double.toString(events.get(position).getBudget())+" TK.");
        holder.binding.ShowDestination.setText(events.get(position).getDestination());
        holder.binding.ShowFromDate.setText(events.get(position).getFromDate());
        holder.binding.ShowTodate.setText(events.get(position).getToDate());
    }

    @Override
    public int getItemCount() {
        return events.size();
    }
    public class TravelEveViewHolder extends RecyclerView.ViewHolder{

        TravelListViewBinding binding;
        public TravelEveViewHolder(View itemView) {
            super(itemView);
            binding= DataBindingUtil.setContentView((Activity)context,R.layout.travel_list_view);

        }
    }
}
