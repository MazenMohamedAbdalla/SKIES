package com.example.skies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class CreateAccActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creat_acc);

        Button logIn = (Button) findViewById(R.id.log_button);
        Button signUp = (Button) findViewById(R.id.sign_up_button);
        EditText name = (EditText) findViewById(R.id.editTextTextPersonName);
        EditText email = (EditText) findViewById(R.id.editTextEmailAddress);
        EditText pass = (EditText) findViewById(R.id.editTextPassword);
        EditText cPass = (EditText) findViewById(R.id.editTextConfirmPassword);

        user use = new user();
        // move to login page (Already have an account)
        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginPage = new Intent(CreateAccActivity.this,logInActivity.class);
                startActivity(loginPage);
                finish();
            }
        });

        // signup
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String password = pass.getText().toString();
                if(password.length() >= 8){
                    boolean signedUp = use.signUp(name.getText().toString(),email.getText().toString(),password,cPass.getText().toString());
                    if(signedUp){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(CreateAccActivity.this,"Signed up successfully",Toast.LENGTH_LONG).show();
                            }
                        });
                        // move to login
                        Intent loginPage = new Intent(CreateAccActivity.this,logInActivity.class);
                        startActivity(loginPage);
                        finish();
                    }
                }else{
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(CreateAccActivity.this,"Your password should be at least 8 chars",Toast.LENGTH_LONG).show();
                        }
                    });
                }

            }
        });
    }
}