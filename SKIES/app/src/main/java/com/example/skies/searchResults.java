package com.example.skies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class searchResults extends AppCompatActivity implements RecycleInterface{
    user use = new user();
    flights flight = new flights();
    ArrayList<ArrayList<String>> results = use.searchFlight(flight.getSource(),flight.getDestination(),flight.getDepartureDate(),flight.getFlightClass());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        TextView sc = (TextView) findViewById(R.id.sourceTxTV);
        TextView ds = (TextView) findViewById(R.id.destinationTxTV);
        TextView fc = (TextView) findViewById(R.id.FclassTxTV);
        TextView dp = (TextView) findViewById(R.id.DepDateTxtV);
        Button back = (Button) findViewById(R.id.button2);
        sc.setText(flight.getSource());
        ds.setText(flight.getDestination());
        fc.setText(flight.getFlightClass());
        dp.setText(flight.getDepartureDate());

        // back to search
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent src = new Intent(searchResults.this,Search.class);
                startActivity(src);
                finish();
            }
        });

        RecyclerView rcs = (RecyclerView) findViewById(R.id.searchResultsRecycle);

        flightResultsRycAdapter adp = new flightResultsRycAdapter(this,results.get(0),results.get(1),results.get(2),results.get(3),results.get(4),results.get(5),results.get(6),results.get(7),results.get(8),this);
        rcs.setAdapter(adp);
        rcs.setLayoutManager(new LinearLayoutManager(this));

    }


    @Override
    public void clicked(int position) {
        ArrayList<ArrayList<String>> acc = use.viewBankAccount();
        if(acc.get(0).size() > 0){
            Intent bookpage = new Intent(searchResults.this,bookFlight.class);
            bookpage.putExtra("FlightNumber",results.get(0).get(position));
            startActivity(bookpage);
            finish();
        }
        else{
            Intent addAcc = new Intent(searchResults.this,AddBankAcc.class);
            startActivity(addAcc);
            finish();
        }
    }

}