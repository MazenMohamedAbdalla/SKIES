package com.example.skies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class MyBookingsAdapter extends RecyclerView.Adapter<MyBookingsAdapter.MyViewHolder>{
    private final RecycleInterface click_cancel;
    private Context context;
    private ArrayList passengerName,flightNumber,cost,seatNumber,source,destination,departureDate,departureTime,arrivalDate,arrivalTime,flightClass;
    public MyBookingsAdapter(Context context,ArrayList passengerName,ArrayList flightNumber,ArrayList cost,ArrayList seatNumber,ArrayList source,ArrayList destination,ArrayList departureDate,ArrayList departureTime,ArrayList arrivalDate,ArrayList arrivalTime,ArrayList flightClass,RecycleInterface click_cancel){
        this.context = context;
        this.passengerName = passengerName;
        this.flightNumber = flightNumber;
        this.source = source;
        this.destination = destination;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.arrivalDate = arrivalDate;
        this.arrivalTime = arrivalTime;
        this.cost = cost;
        this.seatNumber = seatNumber;
        this.flightClass = flightClass;
        this.click_cancel = click_cancel;
    }

    @NonNull
    @NotNull
    @Override
    public MyBookingsAdapter.MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.booked_row,parent,false);
        return new MyBookingsAdapter.MyViewHolder(v,click_cancel);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MyBookingsAdapter.MyViewHolder holder, int position) {
        holder.passengerName.setText(String.valueOf(passengerName.get(position)));
        holder.flightNumber.setText(String.valueOf(flightNumber.get(position)));
        holder.source.setText(String.valueOf(source.get(position)));
        holder.destination.setText(String.valueOf(destination.get(position)));
        holder.departureDate.setText(String.valueOf(departureDate.get(position)));
        holder.departureTime.setText(String.valueOf(departureTime.get(position)));
        holder.arrivalDate.setText(String.valueOf(arrivalDate.get(position)));
        holder.arrivalTime.setText(String.valueOf(arrivalTime.get(position)));
        holder.cost.setText(String.valueOf(cost.get(position)));
        holder.seatNumber.setText(String.valueOf(seatNumber.get(position)));
        holder.flightClass.setText(String.valueOf(flightClass.get(position)));
    }

    @Override
    public int getItemCount() {
        return passengerName.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView passengerName;
        TextView departureTime;
        TextView arrivalTime;
        TextView departureDate;
        TextView arrivalDate;
        TextView source;
        TextView destination;
        TextView flightNumber;
        TextView seatNumber;
        TextView cost;
        TextView flightClass;
        public MyViewHolder(@NonNull @NotNull View itemView,RecycleInterface click_cancel) {
            super(itemView);
            passengerName =  itemView.findViewById(R.id.passangerName);
            departureTime = itemView.findViewById(R.id.depTime);
            arrivalTime = itemView.findViewById(R.id.arrTime);
            departureDate = itemView.findViewById(R.id.textView48);
            arrivalDate = itemView.findViewById(R.id.textView49);
            source = itemView.findViewById(R.id.textView46);
            destination = itemView.findViewById(R.id.textView47);
            flightNumber = itemView.findViewById(R.id.flightNumber);
            seatNumber = itemView.findViewById(R.id.seatNumber);
            flightClass = itemView.findViewById(R.id.flightClass);
            cost = itemView.findViewById(R.id.CostValue);


            itemView.findViewById(R.id.Cencel).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(click_cancel!=null){
                        int pos = getAdapterPosition();
                        if(pos!= RecyclerView.NO_POSITION){
                            click_cancel.clicked(pos);
                        }
                    }
                }
            });
        }
    }
}
