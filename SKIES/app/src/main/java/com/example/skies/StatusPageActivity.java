package com.example.skies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class StatusPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_page);

        Button home = (Button) findViewById(R.id.home_button);
        Button search = (Button) findViewById(R.id.search_button);
        Button settings = (Button) findViewById(R.id.button4);
        Button checkStatus = (Button) findViewById(R.id.button);
        EditText flightNumber = (EditText) findViewById(R.id.editTextTextPersonName2);
        user use = new user();

        // move to home
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent search = new Intent(StatusPageActivity.this,HomeScreenActivity.class);
                startActivity(search);
                finish();
            }
        });

        // move to search
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent search = new Intent(StatusPageActivity.this,Search.class);
                startActivity(search);
                finish();
            }
        });

        // move to settings
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent settings = new Intent(StatusPageActivity.this,SettingActivity.class);
                startActivity(settings);
                finish();
            }
        });

        // check status of flight
        checkStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               boolean found = use.flightStatus(flightNumber.getText().toString());
               if(found){
                   Intent foundStatus = new Intent(StatusPageActivity.this,FoundFlightStatus.class);
                   startActivity(foundStatus);
                   finish();
               }
               else{
                   Intent notFoundStatus = new Intent(StatusPageActivity.this,NotFoundStatus.class);
                   startActivity(notFoundStatus);
                   finish();
               }
            }
        });
    }
}