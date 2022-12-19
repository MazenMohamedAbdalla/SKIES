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

public class logInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        EditText email = (EditText) findViewById(R.id.editTextEmailAddress);
        EditText password = (EditText) findViewById(R.id.editTextPassword);
        Button logIn = (Button) findViewById(R.id.log_in_button);
        Button signUp = (Button) findViewById(R.id.sign_button);
        user user = new user();
        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String em = email.getText().toString();
                String pass = password.getText().toString();
                boolean success = user.logIn(em,pass);
                if(success){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(logInActivity.this,"LoggedIn",Toast.LENGTH_LONG).show();
                        }
                    });
                    Intent homePage = new Intent(logInActivity.this,HomeScreenActivity.class);
                    startActivity(homePage);
                    finish();
                }else{
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(logInActivity.this,"Check your email or password",Toast.LENGTH_LONG).show();
                        }
                    });
                    email.setText("");
                    password.setText("");
                }
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signUp = new Intent(logInActivity.this,CreateAccActivity.class);
                startActivity(signUp);
                finish();
            }
        });

    }
}