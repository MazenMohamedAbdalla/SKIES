package com.example.skies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        bankAccountInfo bnn = new bankAccountInfo();
        TextView name = (TextView) findViewById(R.id.textView35);
        TextView email = (TextView) findViewById(R.id.textView36);

        Button editAccInfo = (Button) findViewById(R.id.button11);
        Button bankSide = (Button) findViewById(R.id.button12);
        Button back = (Button) findViewById(R.id.button2);
        Button home = (Button) findViewById(R.id.home_button);
        Button search = (Button) findViewById(R.id.search_button);
        Button status = (Button) findViewById(R.id.button3);

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
                ArrayList<ArrayList<String>> acc = use.viewBankAccount();
                if(acc.get(0).size()>0){
                    Intent editInfo = new Intent(SettingActivity.this,BankAccountSide.class);
                    startActivity(editInfo);
                    finish();
                }
                else{
                    Intent noBank = new Intent(SettingActivity.this,NoBankAccounts.class);
                    startActivity(noBank);
                    finish();
                }
            }
        });

        // go to home
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent home = new Intent(SettingActivity.this,HomeScreenActivity.class);
                startActivity(home);
                finish();
            }
        });

        // move to home
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent home= new Intent(SettingActivity.this,HomeScreenActivity.class);
                startActivity(home);
                finish();
            }
        });

        // move to search
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent search = new Intent( SettingActivity.this,Search.class);
                startActivity(search);
                finish();
            }
        });


        // move to status
        status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent status = new Intent(SettingActivity.this,StatusPageActivity.class);
                startActivity(status);
                finish();
            }
        });

    }
}