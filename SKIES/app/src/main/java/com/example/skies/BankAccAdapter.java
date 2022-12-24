package com.example.skies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class BankAccAdapter  extends RecyclerView.Adapter<BankAccAdapter.MyViewHolder>{
    private final BankRecycleInterface BankRecycleInterface;
    private Context context;
    private ArrayList cardHolder,accountN,accBalance;

    public BankAccAdapter(Context context,ArrayList cardHolder,ArrayList accountN,ArrayList accBalance,BankRecycleInterface BankRecycleInterface){
        this.context = context;
        this.cardHolder = cardHolder;
        this.accountN = accountN;
        this.accBalance = accBalance;
        this.BankRecycleInterface = BankRecycleInterface;
    }

    @NonNull
    @NotNull
    @Override
    public BankAccAdapter.MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.bank_acc_row,parent,false);
        return new BankAccAdapter.MyViewHolder(v,BankRecycleInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull BankAccAdapter.MyViewHolder holder, int position) {
        holder.cardHolder.setText(String.valueOf(cardHolder.get(position)));
        holder.accountN.setText(String.valueOf(accountN.get(position)));
        holder.accBalance.setText(String.valueOf(accBalance.get(position)));
    }

    @Override
    public int getItemCount() {
        return cardHolder.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView cardHolder;
        TextView accountN;
        TextView accBalance;
        public MyViewHolder(@NonNull @NotNull View itemView,BankRecycleInterface BankRecycleInterface) {
            super(itemView);
            cardHolder =  itemView.findViewById(R.id.cardHolder);
            accountN = itemView.findViewById(R.id.cardNumber);
            accBalance = itemView.findViewById(R.id.textView39);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(BankRecycleInterface!=null){
                        int pos = getAdapterPosition();
                        if(pos!= RecyclerView.NO_POSITION){
                            BankRecycleInterface.setClicked(pos);
                        }
                    }
                }
            });

            itemView.findViewById(R.id.UPDATE).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(BankRecycleInterface!=null){
                        int pos = getAdapterPosition();
                        if(pos!= RecyclerView.NO_POSITION){
                            BankRecycleInterface.updateClicked(pos);
                        }
                    }
                }
            });

            itemView.findViewById(R.id.DELETE).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(BankRecycleInterface!=null){
                        int pos = getAdapterPosition();
                        if(pos!= RecyclerView.NO_POSITION){
                            BankRecycleInterface.deletedClicked(pos);
                        }
                    }
                }
            });
        }
    }
}
