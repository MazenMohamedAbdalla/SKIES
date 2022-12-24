package com.example.skies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class bookFlight extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_flight);
        user use = new user();
        bankAccountInfo bnn = new bankAccountInfo();
        ArrayList<ArrayList<String>> acc = use.viewBankAccount();
        if(bnn.getCardNumber() == null){
            bnn.setCardNumber(acc.get(1).get(0)); // first cardNum by default
        }
        EditText passName = (EditText) findViewById(R.id.editTextTextPersonName12);
        String flightNumber = getIntent().getStringExtra("FlightNumber");
        Button book = (Button) findViewById(R.id.button);
        Button back = (Button) findViewById(R.id.button2);

        // bookFlight(String flightNumber, String passengerName, String cardNumber)

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(passName.getText().toString())){
                    passName.setError("This entry cannot be empty");
                }
                else{
                    boolean booking = use.bookFlight(flightNumber,passName.getText().toString(),bnn.getCardNumber());
                    if(booking){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(bookFlight.this,"Booked",Toast.LENGTH_LONG).show();
                            }
                        });
                        Intent anotherOne = new Intent(bookFlight.this,bookAnotherOne.class);
                        startActivity(anotherOne);
                        finish();
                    }
                    else{
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(bookFlight.this,"Failed to book",Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                }
                }
        });

        // back to results
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent sR = new Intent( bookFlight.this,searchResults.class);
                    startActivity(sR);
                    finish();
            }
        });
    }
}