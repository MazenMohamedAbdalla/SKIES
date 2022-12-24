package com.example.skies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class cancelBookedFlight extends AppCompatActivity {
    user use = new user();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel_booked_flight);

        Button back = (Button) findViewById(R.id.button2);
        Button ok = (Button) findViewById(R.id.okBtt);
        String passName = getIntent().getStringExtra("passengerName");
        String flightNumber = getIntent().getStringExtra("flightNumber");

        // back to home
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent home = new Intent(cancelBookedFlight.this,HomeScreenActivity.class);
                startActivity(home);
                finish();
            }
        });


        // back to cancel
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean cancel = use.cancelFlight(flightNumber,passName);
                if(cancel){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(cancelBookedFlight.this,"Canceled",Toast.LENGTH_LONG).show();
                        }
                    });
                    Intent home = new Intent(cancelBookedFlight.this,HomeScreenActivity.class);
                    startActivity(home);
                    finish();
                }
            }
        });
    }
}