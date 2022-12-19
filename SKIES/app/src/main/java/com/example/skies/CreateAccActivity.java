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

        user user = new user();
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String userName = name.getText().toString();
               String userEmail = email.getText().toString();
               String userPass = pass.getText().toString();
               String conPass = cPass.getText().toString();
               boolean success = user.signUp(userName,userEmail,userPass,conPass);

               if(success){
                   runOnUiThread(new Runnable() {
                       @Override
                       public void run() {
                           Toast.makeText(CreateAccActivity.this,"Done successfully",Toast.LENGTH_LONG).show();
                       }
                   });

                   Intent login = new Intent(CreateAccActivity.this,logInActivity.class);
                   startActivity(login);
                   finish();

               }else{
                   runOnUiThread(new Runnable() {
                       @Override
                       public void run() {
                           Toast.makeText(CreateAccActivity.this,"Process Failed",Toast.LENGTH_LONG).show();
                       }
                   });
               }
            }
        });

        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent login = new Intent(CreateAccActivity.this,logInActivity.class);
                startActivity(login);
                finish();
            }
        });

    }
}