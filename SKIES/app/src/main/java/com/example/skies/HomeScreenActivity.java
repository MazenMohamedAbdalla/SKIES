package com.example.skies;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class HomeScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        Button search = (Button) findViewById(R.id.search_button);
        Button status = (Button) findViewById(R.id.button3);
        Button settings = (Button) findViewById(R.id.button4);

    }
}