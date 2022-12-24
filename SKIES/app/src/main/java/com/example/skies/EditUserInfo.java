package com.example.skies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditUserInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user_info);

        user use = new user();
        EditText name = (EditText) findViewById(R.id.editTextTextPersonName9);
        EditText email = (EditText) findViewById(R.id.editTextTextEmailAddress2);
        Button edit = (Button) findViewById(R.id.button9);
        Button editPassword = (Button) findViewById(R.id.button10);

        name.setText(use.getName());
        email.setText(use.getEmail());

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!email.getText().toString().equals(use.getName())){
                    boolean em = use.changeEmail(email.getText().toString(),use.getPass());
                    if(em){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(EditUserInfo.this,"Changed email successfully",Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                }
                if(!name.getText().toString().equals(use.getEmail())){
                    boolean na = use.changeName(name.getText().toString());
                    if(na){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(EditUserInfo.this,"Changed name successfully",Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                }

            }
        });

        editPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent passwordCheck = new Intent(EditUserInfo.this,EnterPassword.class);
                startActivity(passwordCheck);
                finish();
            }
        });
    }
}