package com.example.skies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class bookAnotherOne extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_another_one);

        Button back = (Button) findViewById(R.id.button2);
        Button yes = (Button) findViewById(R.id.button18);
        Button no = (Button) findViewById(R.id.button19);

        // back to search results
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent searchR = new Intent(bookAnotherOne.this,searchResults.class);
                startActivity(searchR);
                finish();
            }
        });

        // add passenger name (book)
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent book = new Intent(bookAnotherOne.this,bookFlight.class);
                startActivity(book);
                finish();
            }
        });

        // search page
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent searchP = new Intent(bookAnotherOne.this,Search.class);
                startActivity(searchP);
                finish();
            }
        });
    }
}