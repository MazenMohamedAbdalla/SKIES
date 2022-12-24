package com.example.skies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class myBookingsNotFound extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_bookings_not_found);

        Button backButton = (Button) findViewById(R.id.button2);
        Button bookBtt = (Button) findViewById(R.id.button5);

        // Back to Status page
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent home = new Intent(myBookingsNotFound.this,HomeScreenActivity.class);
                startActivity(home);
                finish();
            }
        });

        // search page
        bookBtt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent bookFlight = new Intent(myBookingsNotFound.this,Search.class);
                startActivity(bookFlight);
                finish();
            }
        });

    }
}