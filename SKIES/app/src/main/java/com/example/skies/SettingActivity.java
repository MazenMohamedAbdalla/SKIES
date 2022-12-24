package com.example.skies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        TextView name = (TextView) findViewById(R.id.textView35);
        TextView email = (TextView) findViewById(R.id.textView36);
        Button editAccInfo = (Button) findViewById(R.id.button11);
        Button bankSide = (Button) findViewById(R.id.button12);
        user use = new user();

        name.setText(use.getName());
        email.setText(use.getEmail());
        
        // go to edit info
        editAccInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent editInfo = new Intent(SettingActivity.this,EditUserInfo.class);
                startActivity(editInfo);
                finish();
            }
        });

        // go to bankSide
        bankSide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent editInfo = new Intent(SettingActivity.this,BankAccountSide.class);
                startActivity(editInfo);
                finish();
            }
        });
    }
}