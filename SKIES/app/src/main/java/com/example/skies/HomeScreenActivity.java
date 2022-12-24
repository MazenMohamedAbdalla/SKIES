package com.example.skies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HomeScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        Button search = (Button) findViewById(R.id.search_button);
        Button status = (Button) findViewById(R.id.button3);
        Button settings = (Button) findViewById(R.id.button4);
        Button myBookings = (Button) findViewById(R.id.button7);
        Button feedBack = (Button) findViewById(R.id.button8);
        TextView txtName = (TextView) findViewById(R.id.textView16);

        user use = new user();
        txtName.setText(use.getName());

        // move to search
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent search = new Intent(HomeScreenActivity.this,Search.class);
                startActivity(search);
                finish();
            }
        });

        // move to status
        status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent status = new Intent(HomeScreenActivity.this,StatusPageActivity.class);
                startActivity(status);
                finish();
            }
        });

        // move to settings
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent settings = new Intent(HomeScreenActivity.this,SettingActivity.class);
                startActivity(settings);
                finish();
            }
        });

        // move to myBookings
        myBookings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myBooking = new Intent(HomeScreenActivity.this,mybooking.class);
                startActivity(myBooking);
                finish();
            }
        });

        // move to feedback
        feedBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent feedback = new Intent(HomeScreenActivity.this,feedbackActivity.class);
                startActivity(feedback);
                finish();
            }
        });
    }
}