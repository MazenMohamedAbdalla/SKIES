package com.example.skies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DeleteBankAcc extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_bank_acc);

        Button backButton = (Button) findViewById(R.id.button2);
        Button delete = (Button) findViewById(R.id.button);
        EditText cvv = (EditText) findViewById(R.id.editTextNumber3);
        user use = new user();

        // Back to settings page
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent settings = new Intent(DeleteBankAcc.this,SettingActivity.class);
                startActivity(settings);
                finish();
            }
        });

        // delete
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean deleted = use.deleteBankAccount("",cvv.getText().toString());
                if(deleted){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(DeleteBankAcc.this,"Deleted Successfully",Toast.LENGTH_LONG).show();
                        }
                    });
                }
                else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(DeleteBankAcc.this,"Check The CVV number",Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });

    }
}