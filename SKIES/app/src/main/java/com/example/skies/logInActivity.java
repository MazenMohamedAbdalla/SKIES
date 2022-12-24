package com.example.skies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class logInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        EditText email = (EditText) findViewById(R.id.editTextEmailAddress);
        EditText password = (EditText) findViewById(R.id.editTextPassword);
        Button logIn = (Button) findViewById(R.id.log_in_button);
        Button signUp = (Button) findViewById(R.id.sign_button);
        Button forgotPass = (Button) findViewById(R.id.button13);

        user use = new user();
        // login
        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(TextUtils.isEmpty(email.getText().toString())){
                    email.setError("email cannot be empty");
                }
                else if(TextUtils.isEmpty(password.getText().toString())){
                    password.setError("password cannot be empty");
                }
                else{
                    // saving the input
                    boolean logged = use.logIn(email.getText().toString(),password.getText().toString());
                    // if true
                    if(logged){
                        // Display logged in successfully
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(logInActivity.this,"Logged in successfully",Toast.LENGTH_LONG).show();
                            }
                        });
                        // move to home screen
                        Intent homePage = new Intent(logInActivity.this,HomeScreenActivity.class);
                        startActivity(homePage);
                        finish();
                    }
                    // check your email or password
                    else{
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(logInActivity.this,"Check your email or password",Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                }
            }
        });

        // move to signup page
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signup = new Intent(logInActivity.this,CreateAccActivity.class);
                startActivity(signup);
                finish();
            }
        });

        // move to forgot pass
        forgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent forgotP = new Intent(logInActivity.this,RecoverPassword.class);
                startActivity(forgotP);
                finish();
            }
        });
    }
}