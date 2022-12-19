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
        Button checkStatus = (Button) findViewById(R.id.button);
        Button backButton = (Button) findViewById(R.id.button2);
        EditText flightNumber = (EditText) findViewById(R.id.editTextTextPersonName2);

        user use = new user();
        checkStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String fNum = flightNumber.getText().toString();
               boolean success = use.checkFlightStatus(fNum);
               if(success){
                   Intent FoundPage = new Intent(StatusPageActivity.this,FoundFlightStatus.class);
                   startActivity(FoundPage);
                   finish();
               }
               else{
                   Intent notFoundPage = new Intent(StatusPageActivity.this,NotFoundStatus.class);
                   startActivity(notFoundPage);
                   finish();
               }
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent home = new Intent(StatusPageActivity.this,HomeScreenActivity.class);
                startActivity(home);
                finish();
            }
        });

    }
}