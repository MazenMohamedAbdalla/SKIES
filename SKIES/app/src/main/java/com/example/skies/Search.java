package com.example.skies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class Search extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        user use = new user();
        flights flight = new flights();
        Button home = (Button) findViewById(R.id.home_button);
        Button status = (Button) findViewById(R.id.button3);
        Button settings = (Button) findViewById(R.id.button4);
        Button backButton = (Button) findViewById(R.id.button2);
        Button searchBtt = (Button) findViewById(R.id.button);
        EditText from = (EditText) findViewById(R.id.editTextTextPersonName3);
        EditText to = (EditText) findViewById(R.id.editTextTextPersonName4);
        EditText depDate = (EditText) findViewById(R.id.editTextDate);
        EditText numberOfTickets = (EditText) findViewById(R.id.editTextNumber);
        EditText flightClass = (EditText) findViewById(R.id.editTextTextPersonName5);

        // move to status
        status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent status = new Intent(Search.this,StatusPageActivity.class);
                startActivity(status);
                finish();
            }
        });

        // move to settings
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent settings = new Intent(Search.this,SettingActivity.class);
                startActivity(settings);
                finish();
            }
        });

        // move to home
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent search = new Intent(Search.this,HomeScreenActivity.class);
                startActivity(search);
                finish();
            }
        });

        searchBtt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList <String> results = use.searchFlight(from.getText().toString(),to.getText().toString(),depDate.getText().toString(),flightClass.getText().toString());
                if(results.size() > 0){
                    flight.setStatus(from.getText().toString());
                    flight.setDestination(to.getText().toString());
                    flight.setDepartureDate(depDate.getText().toString());
                    flight.setFlightClass(flightClass.getText().toString()); //
                    Intent searchResults = new Intent(Search .this,searchResults.class);
                    startActivity(searchResults);
                    finish();
                }
                else{
                    Intent searchNotFound = new Intent(Search.this,searchNotFound.class);
                    startActivity(searchNotFound);
                    finish();
                }
            }
        });
    }
}