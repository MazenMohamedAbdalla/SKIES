package com.example.skies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

public class mybooking extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mybooking);


        user use = new user();
        ArrayList <String> bookings = use.myBookings();
        if(bookings.size() > 0){

        }
        else{
            Intent notFound = new Intent(mybooking.this,myBookingsNotFound.class);
            startActivity(notFound);
            finish();
        }
    }
}