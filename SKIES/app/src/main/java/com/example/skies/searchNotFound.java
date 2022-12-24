package com.example.skies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class searchNotFound extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_not_found);

        Button backButton = findViewById(R.id.button2);
        Button backToHome = findViewById(R.id.button5);

        // Back to Status page
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Search = new Intent(searchNotFound.this,Search.class);
                startActivity(Search);
                finish();
            }
        });

        // Back to home
        backToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent home = new Intent(searchNotFound.this,HomeScreenActivity.class);
                startActivity(home);
                finish();
            }
        });

        
    }
}