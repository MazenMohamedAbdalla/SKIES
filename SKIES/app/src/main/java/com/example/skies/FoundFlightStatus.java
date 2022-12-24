package com.example.skies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FoundFlightStatus extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_found_flight);

        flights flight = new flights();

        TextView flightNumber = (TextView) findViewById(R.id.textView22);
        TextView status = (TextView) findViewById(R.id.textView23);
        TextView source = (TextView) findViewById(R.id.textView24);
        TextView depDate = (TextView) findViewById(R.id.textView25);
        TextView depTime = (TextView) findViewById(R.id.textView28);
        TextView destination = (TextView) findViewById(R.id.textView26);
        TextView arrDate = (TextView) findViewById(R.id.textView27);
        TextView arrTime = (TextView) findViewById(R.id.textView29);
        Button backButton = findViewById(R.id.button2);

        flightNumber.setText(flight.getFlightNumber());
        status.setText(flight.getStatus());
        source.setText(flight.getSource());
        depDate.setText(flight.getDepartureDate());
        depTime.setText(flight.getDepartureTime());
        destination.setText(flight.getDestination());
        arrDate.setText(flight.getArrivalDate());
        arrTime.setText(flight.getArrivalTime());
        // Back to Status page
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent statusPage = new Intent(FoundFlightStatus.this,StatusPageActivity.class);
                startActivity(statusPage);
                finish();
            }
        });
    }
}