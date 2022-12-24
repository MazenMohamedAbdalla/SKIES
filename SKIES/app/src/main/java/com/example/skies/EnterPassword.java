package com.example.skies;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EnterPassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_password);


        EditText oldPass = (EditText) findViewById(R.id.editTextTextPersonName10);
        EditText newPass = (EditText) findViewById(R.id.editTextTextPersonName11);
        Button continue_ = (Button) findViewById(R.id.button9);
        user use = new user();

        continue_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean changedPass = use.changePassword(oldPass.getText().toString(),newPass.getText().toString());
                if(changedPass){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(EnterPassword.this,"Password changed successfully",Toast.LENGTH_LONG).show();
                        }
                    });
                }
                else{
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(EnterPassword.this,"Check your old password",Toast.LENGTH_LONG).show();
                        }
                    });                }
            }
        });

    }
}