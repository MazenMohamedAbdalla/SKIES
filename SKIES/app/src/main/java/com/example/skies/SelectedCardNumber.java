package com.example.skies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SelectedCardNumber extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_card_number);

        bankAccountInfo bnn = new bankAccountInfo();
        Button ok = (Button) findViewById(R.id.button16);
        Button back = (Button) findViewById(R.id.button2);
        TextView cardNumber = (TextView) findViewById(R.id.textView38);
        String cN = getIntent().getStringExtra("cardNumber");

        bnn.setCardNumber(cN);
        cardNumber.setText(bnn.getCardNumber());

        // back to bankAcc side
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent bankSide = new Intent(SelectedCardNumber.this,BankAccountSide.class);
                startActivity(bankSide);
                finish();
            }
        });

        // back to bankAcc side
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent bankSide = new Intent(SelectedCardNumber.this,BankAccountSide.class);
                startActivity(bankSide);
                finish();
            }
        });



    }
}