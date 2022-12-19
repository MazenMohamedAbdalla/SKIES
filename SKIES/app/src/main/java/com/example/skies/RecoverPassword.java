package com.example.skies;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RecoverPassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recover_password);
        Button recover = (Button) findViewById(R.id.button6);
        EditText email = (EditText) findViewById(R.id.editTextTextEmailAddress);
        user user = new user();
        recover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String em = email.getText().toString();
                boolean success = user.recoverPassword(em);
                if(success){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(RecoverPassword.this,"Found",Toast.LENGTH_LONG).show();
                        }
                    });
                }else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(RecoverPassword.this,"NOT",Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });
    }
}