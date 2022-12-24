package com.example.skies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NotFoundStatus extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_not_found_status);

        Button tryAgain = (Button) findViewById(R.id.button5);
        Button backButton = (Button) findViewById(R.id.button2);

        tryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent statusPage = new Intent(NotFoundStatus.this,StatusPageActivity.class);
                startActivity(statusPage);
                finish();
            }
        });

        // Back to Status page
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent statusPage = new Intent(NotFoundStatus.this,StatusPageActivity.class);
                startActivity(statusPage);
                finish();
            }
        });

    }
}