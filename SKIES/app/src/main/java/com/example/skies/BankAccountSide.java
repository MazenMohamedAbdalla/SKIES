package com.example.skies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class BankAccountSide extends AppCompatActivity implements BankRecycleInterface{
    user use = new user();
    bankAccountInfo bnn = new bankAccountInfo();
    ArrayList<ArrayList<String>> myAcc = use.viewBankAccount();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_account_side);
        Button back = (Button) findViewById(R.id.button2);
        Button addAnotherAccount = (Button) findViewById(R.id.button15);
        Button selCard = (Button) findViewById(R.id.button17);

        selCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent selectedCard = new Intent(BankAccountSide.this,viewSelected.class);
                startActivity(selectedCard);
                finish();
            }
        });

        // move to add acc
        addAnotherAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addAcc = new Intent(BankAccountSide.this,AddBankAcc.class);
                startActivity(addAcc);
                finish();
            }
        });

        // back to settings
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent setting = new Intent(BankAccountSide.this,SettingActivity.class);
                startActivity(setting);
                finish();
            }
        });

        RecyclerView rcs = (RecyclerView) findViewById(R.id.bankList);
        BankAccAdapter adp = new BankAccAdapter(this,myAcc.get(0),myAcc.get(1),myAcc.get(2),this);
        rcs.setAdapter(adp);
        rcs.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void setClicked(int position) {
        Intent clicked = new Intent(BankAccountSide.this,SelectedCardNumber.class);
        clicked.putExtra("cardNumber",myAcc.get(1).get(position));
        startActivity(clicked);
        finish();
    }

    @Override
    public void updateClicked(int position) {
        Intent updateAcc = new Intent(BankAccountSide.this,UpdateBankAcc.class);
        updateAcc.putExtra("cardNumber",myAcc.get(1).get(position));
        startActivity(updateAcc);
        finish();
    }

    @Override
    public void deletedClicked(int position) {
        Intent deleteAcc = new Intent(BankAccountSide.this,DeleteBankAcc.class);
        deleteAcc.putExtra("cardNumber",myAcc.get(1).get(position));
        startActivity(deleteAcc);
        finish();
    }
}