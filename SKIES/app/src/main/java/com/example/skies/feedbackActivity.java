package com.example.skies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class feedbackActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        user use = new user();
        Button backButton = findViewById(R.id.button2);
        Button submit = findViewById(R.id.button);
        EditText feedback = findViewById(R.id.editTextTextPersonName6);
        // Back to home
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent home = new Intent(feedbackActivity.this,HomeScreenActivity.class);
                startActivity(home);
                finish();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(feedback.getText().toString())){
                    feedback.setError("Empty field");
                }
                else{
                    boolean submitted = use.userFeedback(feedback.getText().toString());
                    if(submitted){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(feedbackActivity.this,"Submitted !",Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                    else{
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(feedbackActivity.this,"Failed to submit check your connection!",Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                }
            }
        });
    }
}