package com.example.skies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NoBankAccounts extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_bank_accounts);

        Button back = (Button) findViewById(R.id.button2);
        Button addAccount = (Button) findViewById(R.id.button5);

        // back to settings
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent settings = new Intent(NoBankAccounts.this,SettingActivity.class);
                startActivity(settings);
                finish();
            }
        });

        // addAcc page
        addAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addAcc = new Intent(NoBankAccounts.this,AddBankAcc.class);
                startActivity(addAcc);
                finish();
            }
        });
    }
}