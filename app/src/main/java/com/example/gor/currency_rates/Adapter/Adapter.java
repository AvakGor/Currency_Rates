package com.example.gor.currency_rates.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gor.currency_rates.R;
import com.example.gor.currency_rates.Model.Valute;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private ArrayList<Valute> mValutes;

    public Adapter(ArrayList<Valute> valutes) {
        this.mValutes= valutes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Valute valute = mValutes.get(position);
        holder.textViewName.setText(valute.getName());
        holder.textViewValue.setText(valute.getValue());
    }

    @Override
    public int getItemCount() {
        return mValutes.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        TextView textViewName;
        TextView textViewValue;

        ViewHolder(View itemView) {
            super(itemView);

            textViewName = itemView.findViewById(R.id.textViewName);
            textViewValue = itemView.findViewById(R.id.textViewValue);
        }
    }
}
