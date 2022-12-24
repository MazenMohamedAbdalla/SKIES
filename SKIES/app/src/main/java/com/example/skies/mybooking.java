package com.example.skies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class mybooking extends AppCompatActivity implements RecycleInterface{

    user use = new user();
    ArrayList<ArrayList<String>> myBookings = use.myBookings();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mybooking);
        RecyclerView rcs = (RecyclerView) findViewById(R.id.myBookingsRecycle);
        Button back = (Button) findViewById(R.id.button2);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent home = new Intent(mybooking.this,HomeScreenActivity.class);
                startActivity(home);
                finish();
            }
        });

        if(myBookings.get(0).size()>0){
            MyBookingsAdapter adp = new MyBookingsAdapter(this,myBookings.get(0),myBookings.get(1),myBookings.get(2),myBookings.get(3),myBookings.get(4),myBookings.get(5),myBookings.get(6),myBookings.get(7),myBookings.get(8),myBookings.get(9),myBookings.get(10),this);
            rcs.setAdapter(adp);
            rcs.setLayoutManager(new LinearLayoutManager(this));
        }
        else{
            Intent notFound = new Intent(mybooking.this,myBookingsNotFound.class);
            startActivity(notFound);
            finish();
        }


    }


    @Override
    public void clicked(int position) {
        Intent cancelPage = new Intent(mybooking.this,cancelBookedFlight.class);
        cancelPage.putExtra("passengerName",myBookings.get(0).get(position));
        cancelPage.putExtra("flightNumber",myBookings.get(1).get(position));
        startActivity(cancelPage);
        finish();
    }

}