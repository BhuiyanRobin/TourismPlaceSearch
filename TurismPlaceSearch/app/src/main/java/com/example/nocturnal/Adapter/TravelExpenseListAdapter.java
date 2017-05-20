package com.example.nocturnal.Adapter;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nocturnal.Model.TravelExpense;
import com.example.nocturnal.placesearch.R;
import com.example.nocturnal.placesearch.databinding.TravelListViewBinding;

import java.util.ArrayList;

/**
 * Created by bhuiy on 5/20/2017.
 */

public class TravelExpenseListAdapter extends RecyclerView.Adapter<TravelExpenseListAdapter.TravelExViewHolder> {
    Context context;
    ArrayList<TravelExpense> expenses;
    public TravelExpenseListAdapter(Context context, ArrayList<TravelExpense> events)
    {
        this.expenses=events;
        this.context=context;
    }
    @Override
    public TravelExViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.travel_list_view,parent,false);
        return new TravelExViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TravelExViewHolder holder, int position) {

    }


    @Override
    public int getItemCount() {
        return 0;
    }
    public class TravelExViewHolder extends RecyclerView.ViewHolder{

        TravelListViewBinding binding;
        public TravelExViewHolder(View itemView) {
            super(itemView);
            binding= DataBindingUtil.setContentView((Activity)context, R.layout.travel_list_view);

        }
    }
}
