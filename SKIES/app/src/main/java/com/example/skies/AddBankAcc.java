package com.example.skies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddBankAcc extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bank_acc);

        Button backButton = (Button) findViewById(R.id.button2);
        Button add = (Button) findViewById(R.id.button);
        EditText cardNumber = (EditText) findViewById(R.id.editTextNumber2);
        EditText cardholder = (EditText) findViewById(R.id.editTextTextPersonName7);
        EditText expDate = (EditText) findViewById(R.id.editTextTextPersonName8);
        EditText cvv = (EditText) findViewById(R.id.editTextNumber3);
        user use =  new user();

        // Back to settings page
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent settings = new Intent(AddBankAcc.this,SettingActivity.class);
                startActivity(settings);
                finish();
            }
        });

        // add Acc
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean added = use.addBankAccount(cardNumber.getText().toString(),cardholder.getText().toString(),expDate.getText().toString(),cvv.getText().toString());
                if(added){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(AddBankAcc.this,"Added Successfully",Toast.LENGTH_LONG).show();
                        }
                    });
                }
                else{
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(AddBankAcc.this,"Enter valid data",Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });
    }
}