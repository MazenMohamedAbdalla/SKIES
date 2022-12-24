package com.example.skies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class flightResultsRycAdapter extends RecyclerView.Adapter<flightResultsRycAdapter.MyViewHolder> {
    private final RecycleInterface click_book;
    private Context context;
    private ArrayList flightNumber,from,to,departureDate,departureTime,arrivalDate,arrivalTime,cost,numberOfSeats;

    public flightResultsRycAdapter(Context context, ArrayList flightNumber,ArrayList from,ArrayList to,ArrayList departureDate,ArrayList departureTime,ArrayList arrivalDate,ArrayList arrivalTime,ArrayList cost,ArrayList numberOfSeats,RecycleInterface click_book) {
        this.context = context;
        this.flightNumber = flightNumber;
        this.from = from;
        this.to = to;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.arrivalDate = arrivalDate;
        this.arrivalTime = arrivalTime;
        this.cost = cost;
        this.numberOfSeats = numberOfSeats;
        this.click_book = click_book;
    }

    @NonNull
    @NotNull
    @Override
    public flightResultsRycAdapter.MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.flight_row,parent,false);
        return new MyViewHolder(v,click_book);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull flightResultsRycAdapter.MyViewHolder holder, int position) {
        holder.flightNumber.setText(String.valueOf(flightNumber.get(position)));
        holder.from.setText(String.valueOf(from.get(position)));
        holder.to.setText(String.valueOf(to.get(position)));
        holder.departureDate.setText(String.valueOf(departureDate.get(position)));
        holder.departureTime.setText(String.valueOf(departureTime.get(position)));
        holder.arrivalDate.setText(String.valueOf(arrivalDate.get(position)));
        holder.arrivalTime.setText(String.valueOf(arrivalTime.get(position)));
        holder.cost.setText(String.valueOf(cost.get(position)));
        holder.numberOfSeats.setText(String.valueOf(numberOfSeats.get(position)));

    }

    @Override
    public int getItemCount() {
        return flightNumber.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView departureTime;
        TextView arrivalTime;
        TextView departureDate;
        TextView arrivalDate;
        TextView from;
        TextView to;
        TextView flightNumber;
        TextView numberOfSeats;
        TextView cost;

        public MyViewHolder(@NonNull @NotNull View itemView,RecycleInterface click_book) {
            super(itemView);

            img = itemView.findViewById(R.id.imageView14);
            departureTime = itemView.findViewById(R.id.depTime);
            arrivalTime = itemView.findViewById(R.id.arrTime);
            departureDate = itemView.findViewById(R.id.textView48);
            arrivalDate = itemView.findViewById(R.id.textView49);
            from = itemView.findViewById(R.id.textView46);
            to = itemView.findViewById(R.id.textView47);
            flightNumber = itemView.findViewById(R.id.flightNumber);
            numberOfSeats = itemView.findViewById(R.id.numberOfSeats);
            cost = itemView .findViewById(R.id.CostValue);

            itemView.findViewById(R.id.Book).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(click_book!=null){
                        int pos = getAdapterPosition();
                        if(pos!= RecyclerView.NO_POSITION){
                            click_book.clicked(pos);
                        }
                    }
                }
            });

        }
    }
}
