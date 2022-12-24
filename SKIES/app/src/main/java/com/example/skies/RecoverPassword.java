package com.example.skies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RecoverPassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recover_password);
        Button recover = (Button) findViewById(R.id.button6);
        Button backToLog = (Button) findViewById(R.id.button14);
        TextView yourPass = (TextView) findViewById(R.id.textView42);
        TextView value = (TextView) findViewById(R.id.textView41);
        EditText email = (EditText) findViewById(R.id.editTextTextEmailAddress);
        user user = new user();

        // recover button
        recover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String em = email.getText().toString();
                if(TextUtils.isEmpty(em)){
                    email.setError("Email cannot be empty");
                }
                else{
                    boolean success = user.recoverPassword(em);
                    if(success){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(RecoverPassword.this,"Found",Toast.LENGTH_LONG).show();
                            }
                        });
                        yourPass.setText("Your Password:");
                        value.setText(user.getPass());
                    }else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(RecoverPassword.this,"NOT",Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                }
            }
        });

        // back to login
        backToLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent logIn = new Intent(RecoverPassword.this,logInActivity.class);
                startActivity(logIn);
                finish();
            }
        });
    }
}